package cn.itcast.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.itcast.entity.FoodType;
import cn.itcast.entity.Oder;
import cn.itcast.entity.OderDetail;
import cn.itcast.factory.BeanFactory;
import cn.itcast.service.IFoodTypeService;
import cn.itcast.service.IOderService;
import cn.itcast.service.impl.OderService;

/**
 * Servlet implementation class foodTypeServlet
 */
@WebServlet("/foodTypeServlet")
public class foodTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public foodTypeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    private IFoodTypeService foodTypeService = BeanFactory.getInstance("foodTypeService",IFoodTypeService.class); 
    private IOderService oderService = BeanFactory.getInstance("oderService",IOderService.class); 
    //	  跳转资源 
	  private String uri;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 request.setCharacterEncoding("UTF-8");
		  response.setContentType("text/html;charset=UTF-8");
		  String method = request.getParameter("method");
		  
		  if ("addFoodType".equals(method)) { 
			  // 添加 
			  addFoodType(request, response); 
		  } else if ("list".equals(method)) { 
			  // 列表展示
			  list(request, response); 
		  }else if ("viewUpdate".equals(method)) {
			  // 进入更新页面
			  viewUpdate(request,response);
		  }else if ("delete".equals(method)) { 
			  // 删除
			  delete(request, response); 
		  }else if ("update".equals(method)) { 
			  // 更新 
			  update(request, response);
		  }else if ("listOder".equals(method)) { 
			  // 列表展示
			  listOder(request, response); 
		  }else if ("oderDetail".equals(method)) {
			  
			  oderDetail(request,response);
		  }else if ("addOderDetail".equals(method)) {
			  
			  addOderDetail(request,response);
		  }else if ("submitList".equals(method)) {
			  
			  submitList(request,response);
		  }else if ("payFor".equals(method)) {
			  
			  payFor(request,response);
		  }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	
	// a. 添加菜系
		public void  addFoodType(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
			try {
				
				// 1. 获取请求数据封装
				String foodTypeName = request.getParameter("foodTypeName");
				
				FoodType ft = new FoodType();
				ft.setTypeName(foodTypeName);
				// 2. 调用service处理业务逻辑
				foodTypeService.save(ft);
				// 3. 跳转
				uri = "/foodTypeServlet?method=list";
			} catch (Exception e) {
				e.printStackTrace();
				uri="/error/error.jsp";
				
			}
			//转发
			request.getRequestDispatcher(uri).forward(request,response);

		}

	// b. 菜系列表展示
		public void  list(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			
			try {
				// 调用Service查询所有的类别
				List<FoodType> list = foodTypeService.getAll();
				
				// 保存
				 HttpSession session = request.getSession();
			        //将数据存储到session中
			        session.setAttribute("listFoodType",list);
				
				// 跳转的菜系列表页面
				uri ="/sys/type/foodtype_list.jsp";
			} catch (Exception e) {
				e.printStackTrace();
				uri="/error/error.jsp";
			}

			//转发
					request.getRequestDispatcher(uri).forward(request,response);
		}
		
		// c. 进入更新页面
		public void viewUpdate(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
			// 1. 获取请求id
			String id = request.getParameter("id");
			// 2. 根据id查询对象
			FoodType ft = foodTypeService.findById(Integer.parseInt(id));
			// 3. 保存
			request.setAttribute("foodType", ft);
			// 4. 跳转
			uri ="/sys/type/foodtype_update.jsp";
			//转发
			request.getRequestDispatcher(uri).forward(request,response);
			
		}

		// d. 删除
		public void delete(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
			
			// 1. 获取请求id
			String id = request.getParameter("id");
			// 2. 调用Service
			foodTypeService.delete(Integer.parseInt(id));
			// 3. 跳转
			uri = "/foodTypeServlet?method=list";
			//转发
			request.getRequestDispatcher(uri).forward(request,response);
		}

		// e. 更新
		public void update(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
			// 1. 获取请求数据封装
			int id = Integer.parseInt(request.getParameter("id"));
			
			String name = request.getParameter("foodTypeName");
			FoodType foodType = new FoodType();
			foodType.setId(id);
			foodType.setTypeName(name);

			// 2. 调用Service更新
			foodTypeService.update(foodType);
			// 3. 跳转
			uri = "/foodTypeServlet?method=list";
			//转发
			request.getRequestDispatcher(uri).forward(request,response);
		}
		public void  listOder(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			try {
				// 调用Service查询所有的类别
				List<Oder> list = oderService.findAllList();
				// 保存
				request.setAttribute("listOder", list);
				uri ="/sys/orderList.jsp";
			} catch (Exception e) {
				e.printStackTrace();
				
			}
			//转发
					request.getRequestDispatcher(uri).forward(request,response);
		}
		public void  oderDetail(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			try {
				// 调用Service查询所有的类别
				List<OderDetail> list = oderService.findDetailList();
				// 保存
				request.setAttribute("listDetail", list);
				uri ="/sys/orderDetail.jsp";
			} catch (Exception e) {
				e.printStackTrace();
				
			}
			//转发
					request.getRequestDispatcher(uri).forward(request,response);
		}
		public void  addOderDetail(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			try {
				String foodId=request.getParameter("foodId");
				String foodName=request.getParameter("foodName");
				foodName=new String(foodName.getBytes("ISO-8859-1"), "utf-8");
				float foodPrice=Float.valueOf(request.getParameter("foodPrice"));
				int count ;
				 // 购物车结构是List中包含Map，每一个Map是一个商品
	            // 从Session中取出的购物车
	            List<Map<String, Object>> cart = (List<Map<String, Object>>) request.getSession().getAttribute("cart");

	            if (cart == null) { // 第一次取出是null
	                cart = new ArrayList<Map<String, Object>>();
	                request.getSession().setAttribute("cart", cart);
	            }

	            // 购物车中有选择的商品
	            int flag = 0;
	            
	            for (Map<String, Object> item : cart) {
	            	String goodsid2 = (String) item.get("foodId");
	                if (foodId.equals(goodsid2)) {

	                    Integer quantity = (Integer) item.get("count");
	                    quantity++;
	                    item.put("count", quantity);

	                    flag++;
	                }
	            }
	         // 购物车中没有选择的商品
	            if (flag == 0) {
	                Map<String, Object> item = new HashMap<>();
	                // item 结构是Map [商品id，商品名称，价格，数量]
	                item.put("foodId", foodId);
	                item.put("foodName", foodName);
	                item.put("count", 1);
	                item.put("foodPrice", foodPrice);
	                // 添加到购物车
	                cart.add(item);
	            }
	            List<Map<String, Object>> cart1 = (List<Map<String, Object>>) request.getSession().getAttribute("cart");
	            double total = 0.0;
	            if (cart1 != null) {
	                for (Map<String, Object> item : cart1) {

	                    Integer quantity = (Integer) item.get("count");
	                    Float price = (Float) item.get("foodPrice");
	                    double subtotal = price * quantity;
	                    total += subtotal;
	                }
	            }

	            request.setAttribute("total", total);
				uri ="/app/clientCart.jsp";
			} catch (Exception e) {
				e.printStackTrace();
				
			}
			//转发
					request.getRequestDispatcher(uri).forward(request,response);
		}
		//提交订单
		public void submitList(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
			 // 从Session中取出的购物车
            List<Map<String, Object>> cart = (List<Map<String, Object>>) request.getSession().getAttribute("cart");
            for (Map<String, Object> item : cart) {
                String foodId =String.valueOf( item.get("foodId"));
                String strquantity = request.getParameter("quantity_" + foodId);
                int quantity = 0;
                try {
                    quantity = Integer.valueOf(strquantity);
                } catch (Exception e) {
                }

                item.put("count", quantity);
            }
            HttpSession session = request.getSession();
            String tableId=String.valueOf(session.getAttribute("tableId"));
            // 提交订单
            double totalPrice = oderService.submitOrders(cart,Integer.parseInt(tableId));
            request.setAttribute("totalPrice", totalPrice);
            uri ="/app/clientOrderList.jsp";
            request.getRequestDispatcher(uri).forward(request, response);
            // 清空购物车
            request.getSession().removeAttribute("cart");
			
		}
		public void  payFor(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			try {
				HttpSession session = request.getSession();
				String tableId=String.valueOf(session.getAttribute("tableId"));
				oderService.payFor(Integer.valueOf(tableId));
				uri ="/app/jiezhang.jsp";
			} catch (Exception e) {
				e.printStackTrace();
				
			}
			//转发
					request.getRequestDispatcher(uri).forward(request,response);
		}
}
