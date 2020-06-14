package cn.itcast.servlet;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.xml.ws.RequestWrapper;

import org.apache.tomcat.util.buf.Utf8Decoder;
import cn.itcast.entity.DinnerTable;
import cn.itcast.entity.Food;
import cn.itcast.entity.FoodType;
import cn.itcast.factory.BeanFactory;
import cn.itcast.service.IFoodService;
import cn.itcast.service.IFoodTypeService;
import cn.itcast.service.impl.FoodService;
import cn.itcast.utils.JdbcUtils;

/**
 * Servlet implementation class sysFoodServlet
 */
@WebServlet("/sysFoodServlet")
@MultipartConfig//��ʶһ��Servlet֧���ļ����ϴ�
public class sysFoodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public sysFoodServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    private IFoodService foodService = BeanFactory.getInstance("foodService",IFoodService.class);  
    private IFoodTypeService foodTypeService = BeanFactory.getInstance("foodTypeService",IFoodTypeService.class); 
    private String uri;
    private String ID;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 request.setCharacterEncoding("UTF-8");
		  response.setContentType("text/html;charset=UTF-8");
		  List<FoodType> list = foodTypeService.getAll();
			// ����
			request.setAttribute("listFoodType", list);
		// ��ȡ����������
				String method = request.getParameter("method");
				// �ж�
				if ("listFood".equals(method)) {
					
					listFood(request, response);
				}else if ("addFood".equals(method)) {
					addFood(request, response);
				}else if ("updateFood".equals(method)) {
					updateFood(request, response);
				}else if ("deleteFood".equals(method)) {
					deleteFood(request, response);
				}else if ("viewUpdate".equals(method)) {
					viewUpdate(request, response);
				}else if ("search".equals(method)) {
					search(request, response);
				}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
			public void  listFood(HttpServletRequest request,
					HttpServletResponse response) throws ServletException, IOException {
				
				try {
					
					List<Food> food = foodService.findAllFood();
					
					// ���浽request
					request.setAttribute("listFood", food);
					// ��ת����ҳ��ʾ
					request.getRequestDispatcher("/sys/foodList.jsp").forward(request, response);
				} catch (Exception e) {
					e.printStackTrace();
					
				}
				
			}

			public void  addFood(HttpServletRequest request,
					HttpServletResponse response) throws ServletException, IOException {
				try {
					
					Part part=request.getPart("img");//��ȡͼƬ
					//�ϴ�ͼƬ����ĿĿ¼
//					String filePath=getServletContext().getRealPath("upload/food");
					String filePath="E:/eclipse-workspace/the_Hotel/WebContent/app/foodImg";
					System.out.println("filePath:"+filePath);//�洢·��
					
					File file=new File(filePath);
					if(!file.exists()){
						file.mkdirs();
					}
					
					int newNum = (int)((Math.random()*9+1)*1000);
					String fileName=String.valueOf(newNum)+".jpg";//�洢����
					part.write(filePath+File.separator+fileName);
					
					request.setCharacterEncoding("utf-8");
					// 1. ��ȡ�������ݷ�װ
					String foodType_id = request.getParameter("foodTypeId");
					String  foodName= request.getParameter("foodName");
					String price = request.getParameter("price");
					String mprice = request.getParameter("mprice");
					String remark = request.getParameter("remark");
					//String img = request.getParameter("img");
					Food food = new Food();
					food.setFoodType_id(Integer.parseInt(foodType_id));
					food.setFoodName(foodName);
					food.setPrice( Double.parseDouble(price));
					food.setMprice( Double.parseDouble(mprice));
					food.setRemark(remark);
					food.setImg(fileName);
					// 2. ����service����ҵ���߼�
					foodService.save(food);
				} 
				catch (Exception e) {
					e.printStackTrace();
				}
				// 3. ��ת
					request.getRequestDispatcher("/sysFoodServlet?method=listFood").forward(request,response);
			}
			// c. �������ҳ��
			public void viewUpdate(HttpServletRequest request,
					HttpServletResponse response) throws ServletException, IOException {
				// 1. ��ȡ����id
				ID = request.getParameter("id");
				
				// 2. ����id��ѯ����
				Food f = foodService.findById(Integer.parseInt(ID));
				// 3. ����
				request.setAttribute("listFood", f);
				// 4. ��ת
				uri ="/sys/type/updateFood.jsp";
				//ת��
				request.getRequestDispatcher(uri).forward(request,response);
				
			}
			
			public void  updateFood(HttpServletRequest request,
					HttpServletResponse response) throws ServletException, IOException {
				try {
					String  foodName= request.getParameter("foodName");
					Part part=request.getPart("img");//��ȡͼƬ
					//�ϴ�ͼƬ����ĿĿ¼
//					String filePath=getServletContext().getRealPath("upload/food");
					String filePath="E:/eclipse-workspace/the_Hotel/WebContent/app/foodImg";
					System.out.println("filePath:"+filePath);//�洢·��
					
					File file=new File(filePath);
					if(!file.exists()){
						file.mkdirs();
					}
					String fileName=ID+".jpg";//�洢����
					part.write(filePath+File.separator+fileName);
					
					request.setCharacterEncoding("utf-8");
					String id =ID;
					String foodType_id = request.getParameter("foodTypeId");
					
					String price = request.getParameter("price");
					String mprice = request.getParameter("mprice");
					String remark = request.getParameter("remark");
					String img = request.getParameter("img");
					System.out.println("get"+img);
					Food food = new Food();
					food.setId(Integer.parseInt(id));
					food.setFoodType_id(Integer.parseInt(foodType_id));
					food.setFoodName(foodName);
					food.setPrice( Double.parseDouble(price));
					food.setMprice( Double.parseDouble(mprice));
					food.setRemark(remark);
					food.setImg(fileName);
					System.out.println("�޸ĺ�food��"+food);
					foodService.update(food);
					
				} catch (Exception e) {
					e.printStackTrace();
					// TODO: handle exception
				}
				request.getRequestDispatcher("/sysFoodServlet?method=listFood").forward(request, response);
			}
			
			public void  deleteFood(HttpServletRequest request,
					HttpServletResponse response) throws ServletException, IOException {
				// 1. ��ȡ����id
							String id = request.getParameter("id");
							// 2. ����Service
							foodService.delete(Integer.parseInt(id));
				// ��ת����ҳ��ʾ
				request.getRequestDispatcher("/sysFoodServlet?method=listFood").forward(request,response);
				
			}
			public void  search(HttpServletRequest request,
					HttpServletResponse response) throws ServletException, IOException {
				
				try {
					String keyword = request.getParameter("keyword");
					System.out.println(keyword);
					List<Food> food = foodService.search(keyword);
					System.out.println(food);
					// ���浽request
					request.setAttribute("listFood", food);
					// ��ת����ҳ��ʾ
					request.getRequestDispatcher("/sys/foodList.jsp").forward(request, response);
				} catch (Exception e) {
					e.printStackTrace();
					
				}
				
			}
}
