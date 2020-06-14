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
    //	  ��ת��Դ 
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
			  // ��� 
			  addFoodType(request, response); 
		  } else if ("list".equals(method)) { 
			  // �б�չʾ
			  list(request, response); 
		  }else if ("viewUpdate".equals(method)) {
			  // �������ҳ��
			  viewUpdate(request,response);
		  }else if ("delete".equals(method)) { 
			  // ɾ��
			  delete(request, response); 
		  }else if ("update".equals(method)) { 
			  // ���� 
			  update(request, response);
		  }else if ("listOder".equals(method)) { 
			  // �б�չʾ
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
	
	
	// a. ��Ӳ�ϵ
		public void  addFoodType(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
			try {
				
				// 1. ��ȡ�������ݷ�װ
				String foodTypeName = request.getParameter("foodTypeName");
				
				FoodType ft = new FoodType();
				ft.setTypeName(foodTypeName);
				// 2. ����service����ҵ���߼�
				foodTypeService.save(ft);
				// 3. ��ת
				uri = "/foodTypeServlet?method=list";
			} catch (Exception e) {
				e.printStackTrace();
				uri="/error/error.jsp";
				
			}
			//ת��
			request.getRequestDispatcher(uri).forward(request,response);

		}

	// b. ��ϵ�б�չʾ
		public void  list(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			
			try {
				// ����Service��ѯ���е����
				List<FoodType> list = foodTypeService.getAll();
				
				// ����
				 HttpSession session = request.getSession();
			        //�����ݴ洢��session��
			        session.setAttribute("listFoodType",list);
				
				// ��ת�Ĳ�ϵ�б�ҳ��
				uri ="/sys/type/foodtype_list.jsp";
			} catch (Exception e) {
				e.printStackTrace();
				uri="/error/error.jsp";
			}

			//ת��
					request.getRequestDispatcher(uri).forward(request,response);
		}
		
		// c. �������ҳ��
		public void viewUpdate(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
			// 1. ��ȡ����id
			String id = request.getParameter("id");
			// 2. ����id��ѯ����
			FoodType ft = foodTypeService.findById(Integer.parseInt(id));
			// 3. ����
			request.setAttribute("foodType", ft);
			// 4. ��ת
			uri ="/sys/type/foodtype_update.jsp";
			//ת��
			request.getRequestDispatcher(uri).forward(request,response);
			
		}

		// d. ɾ��
		public void delete(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
			
			// 1. ��ȡ����id
			String id = request.getParameter("id");
			// 2. ����Service
			foodTypeService.delete(Integer.parseInt(id));
			// 3. ��ת
			uri = "/foodTypeServlet?method=list";
			//ת��
			request.getRequestDispatcher(uri).forward(request,response);
		}

		// e. ����
		public void update(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
			// 1. ��ȡ�������ݷ�װ
			int id = Integer.parseInt(request.getParameter("id"));
			
			String name = request.getParameter("foodTypeName");
			FoodType foodType = new FoodType();
			foodType.setId(id);
			foodType.setTypeName(name);

			// 2. ����Service����
			foodTypeService.update(foodType);
			// 3. ��ת
			uri = "/foodTypeServlet?method=list";
			//ת��
			request.getRequestDispatcher(uri).forward(request,response);
		}
		public void  listOder(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			try {
				// ����Service��ѯ���е����
				List<Oder> list = oderService.findAllList();
				// ����
				request.setAttribute("listOder", list);
				uri ="/sys/orderList.jsp";
			} catch (Exception e) {
				e.printStackTrace();
				
			}
			//ת��
					request.getRequestDispatcher(uri).forward(request,response);
		}
		public void  oderDetail(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			try {
				// ����Service��ѯ���е����
				List<OderDetail> list = oderService.findDetailList();
				// ����
				request.setAttribute("listDetail", list);
				uri ="/sys/orderDetail.jsp";
			} catch (Exception e) {
				e.printStackTrace();
				
			}
			//ת��
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
				 // ���ﳵ�ṹ��List�а���Map��ÿһ��Map��һ����Ʒ
	            // ��Session��ȡ���Ĺ��ﳵ
	            List<Map<String, Object>> cart = (List<Map<String, Object>>) request.getSession().getAttribute("cart");

	            if (cart == null) { // ��һ��ȡ����null
	                cart = new ArrayList<Map<String, Object>>();
	                request.getSession().setAttribute("cart", cart);
	            }

	            // ���ﳵ����ѡ�����Ʒ
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
	         // ���ﳵ��û��ѡ�����Ʒ
	            if (flag == 0) {
	                Map<String, Object> item = new HashMap<>();
	                // item �ṹ��Map [��Ʒid����Ʒ���ƣ��۸�����]
	                item.put("foodId", foodId);
	                item.put("foodName", foodName);
	                item.put("count", 1);
	                item.put("foodPrice", foodPrice);
	                // ��ӵ����ﳵ
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
			//ת��
					request.getRequestDispatcher(uri).forward(request,response);
		}
		//�ύ����
		public void submitList(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
			 // ��Session��ȡ���Ĺ��ﳵ
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
            // �ύ����
            double totalPrice = oderService.submitOrders(cart,Integer.parseInt(tableId));
            request.setAttribute("totalPrice", totalPrice);
            uri ="/app/clientOrderList.jsp";
            request.getRequestDispatcher(uri).forward(request, response);
            // ��չ��ﳵ
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
			//ת��
					request.getRequestDispatcher(uri).forward(request,response);
		}
}
