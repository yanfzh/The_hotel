package cn.itcast.dao.impl;

import java.util.List;

import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.itcast.entity.Food;
import cn.itcast.entity.Oder;
import cn.itcast.entity.OderDetail;
import cn.itcast.utils.JdbcUtils;
import org.apache.commons.dbutils.handlers.BeanHandler;
import cn.itcast.dao.IOderDao;
import cn.itcast.dao.impl.OderDao;


public class OderDao implements IOderDao{
	@Override
	public List<Oder> findAll() {
		String sql = "select * from orders";
	
		try {
			
			return JdbcUtils.getQuerrRunner().query(sql, new BeanListHandler<Oder>(Oder.class));
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	@Override
	public List<OderDetail> findDetailList() {
		String sql = "select * from orderdetail";
	
		try {
			
			return JdbcUtils.getQuerrRunner().query(sql, new BeanListHandler<OderDetail>(OderDetail.class));
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	 @Override
	    public void create(Oder orders) {

	        String sql = "insert into orders (id,table_id,orderDate,totalPrice,orderStatus) values (?,?,?,?,?)";
	        Object[] params={orders.getId(),orders.getTable_id(),orders.getOrderDate(),orders.getTotalPrice(),orders.getOrderStatus()};
			
			try {
				JdbcUtils.getQuerrRunner().update(sql,params);
			} catch (Exception e) {
				e.printStackTrace();
				
			}
	        
	    }
	 @Override
	    public void create(OderDetail lineItem) {
		// System.out.println("oderDetail的所有属性值:订单编号"+lineItem.getOder().getId()+" 食物编号"+lineItem.getFood().getId()+" 数量"+lineItem.getFoodCount()+" 价格"+lineItem.getFoodPrice());
	        String sql = "insert into orderdetail(orderId,food_id,foodCount,foodPrice) values (?,?,?,?)";
	        Object[] params={lineItem.getOder().getId(),lineItem.getFood().getId(),lineItem.getFoodCount(),lineItem.getFoodPrice()};
			
			try {
				JdbcUtils.getQuerrRunner().update(sql,params);
			} catch (Exception e) {
				e.printStackTrace();
				
			}
	        
	    }
	 @Override
	    public void modify(Oder orders) {
		    String sql = "update orders set totalPrice=? where id=?";
			try {
				JdbcUtils.getQuerrRunner().update(sql, orders.getTotalPrice(),orders.getId());
			} catch (Exception e) {
				throw new RuntimeException(e);
			} 
	        
	    }
}
