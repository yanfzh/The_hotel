package cn.itcast.service.impl;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import cn.itcast.dao.IOderDao;
import cn.itcast.dao.impl.OderDao;
import cn.itcast.entity.Food;
import cn.itcast.entity.Oder;
import cn.itcast.entity.OderDetail;
import cn.itcast.factory.BeanFactory;
import cn.itcast.service.IFoodService;
import cn.itcast.service.IOderService;
import cn.itcast.utils.JdbcUtils;

public class OderService implements IOderService{
	    private IFoodService foodService = BeanFactory.getInstance("foodService",IFoodService.class); 
		private IOderDao oderDao = BeanFactory.getInstance(
				"oderDao", IOderDao.class);
		@Override
		public List<Oder> findAllList() {
			try {
				
				return oderDao.findAll();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		@Override
		public List<OderDetail> findDetailList() {
			try {
				
				return oderDao.findDetailList();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		
		@Override
		 public double submitOrders(List<Map<String,Object>> car,int tableId)
		    {   Oder orders=new Oder();
		        Date date=new Date();
		        Timestamp timestamp = new Timestamp(date.getTime());
		        //订单ID
		        int ordersid=(int) ((Math.random() * 9 + 1) * 100000000);
		        orders.setId(ordersid);
		        orders.setOrderDate(timestamp);
		        orders.setOrderStatus(0);
		        orders.setTotalPrice(0);
		        orders.setTable_id(tableId);
		        //将订单信息插入到数据库中
		        oderDao.create(orders);
		        //商品总金额
		        double total=0.0;
		        for(Map item: car)
		        {   //item结构[商品id,数量]
		            String goodsid=String.valueOf(item.get("foodId"));
		            Integer quantity=(Integer)item.get("count");
		            Food food=foodService.findById(Integer.parseInt(goodsid));
		            //小计金额
		            double subtotal=quantity*food.getPrice();
		            total+=subtotal;
		            OderDetail lineItem=new OderDetail();
		            lineItem.setFoodCount(quantity);
		            lineItem.setFood(food);
		            lineItem.setOder(orders);
		            lineItem.setFoodPrice(subtotal);
		           
		            //将订单详细插入到数据库
		            oderDao.create(lineItem);
		        }
		        orders.setTotalPrice(total);
		        //更新订单数据库
		        oderDao.modify(orders);
		        return total;
		    }
		@Override
	    public void payFor(int tableId) {
		    String sql = "update orders set orderStatus=1 where table_id=?";
			try {
				JdbcUtils.getQuerrRunner().update(sql,tableId);
			} catch (Exception e) {
				throw new RuntimeException(e);
			} 
	        
			String sql2 = "update dinnertable set tableStatus=0,orderDate=NULL where id=?";
			try {
				JdbcUtils.getQuerrRunner().update(sql2,tableId);
			} catch (Exception e) {
				throw new RuntimeException(e);
			} 
	    }
}
