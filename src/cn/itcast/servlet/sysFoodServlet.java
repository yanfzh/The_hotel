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
@MultipartConfig//标识一个Servlet支持文件的上传
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
			// 保存
			request.setAttribute("listFoodType", list);
		// 获取操作的类型
				String method = request.getParameter("method");
				// 判断
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
					
					// 保存到request
					request.setAttribute("listFood", food);
					// 跳转到首页显示
					request.getRequestDispatcher("/sys/foodList.jsp").forward(request, response);
				} catch (Exception e) {
					e.printStackTrace();
					
				}
				
			}

			public void  addFood(HttpServletRequest request,
					HttpServletResponse response) throws ServletException, IOException {
				try {
					
					Part part=request.getPart("img");//获取图片
					//上传图片到项目目录
//					String filePath=getServletContext().getRealPath("upload/food");
					String filePath="E:/eclipse-workspace/the_Hotel/WebContent/app/foodImg";
					System.out.println("filePath:"+filePath);//存储路径
					
					File file=new File(filePath);
					if(!file.exists()){
						file.mkdirs();
					}
					
					int newNum = (int)((Math.random()*9+1)*1000);
					String fileName=String.valueOf(newNum)+".jpg";//存储名字
					part.write(filePath+File.separator+fileName);
					
					request.setCharacterEncoding("utf-8");
					// 1. 获取请求数据封装
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
					// 2. 调用service处理业务逻辑
					foodService.save(food);
				} 
				catch (Exception e) {
					e.printStackTrace();
				}
				// 3. 跳转
					request.getRequestDispatcher("/sysFoodServlet?method=listFood").forward(request,response);
			}
			// c. 进入更新页面
			public void viewUpdate(HttpServletRequest request,
					HttpServletResponse response) throws ServletException, IOException {
				// 1. 获取请求id
				ID = request.getParameter("id");
				
				// 2. 根据id查询对象
				Food f = foodService.findById(Integer.parseInt(ID));
				// 3. 保存
				request.setAttribute("listFood", f);
				// 4. 跳转
				uri ="/sys/type/updateFood.jsp";
				//转发
				request.getRequestDispatcher(uri).forward(request,response);
				
			}
			
			public void  updateFood(HttpServletRequest request,
					HttpServletResponse response) throws ServletException, IOException {
				try {
					String  foodName= request.getParameter("foodName");
					Part part=request.getPart("img");//获取图片
					//上传图片到项目目录
//					String filePath=getServletContext().getRealPath("upload/food");
					String filePath="E:/eclipse-workspace/the_Hotel/WebContent/app/foodImg";
					System.out.println("filePath:"+filePath);//存储路径
					
					File file=new File(filePath);
					if(!file.exists()){
						file.mkdirs();
					}
					String fileName=ID+".jpg";//存储名字
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
					System.out.println("修改后food："+food);
					foodService.update(food);
					
				} catch (Exception e) {
					e.printStackTrace();
					// TODO: handle exception
				}
				request.getRequestDispatcher("/sysFoodServlet?method=listFood").forward(request, response);
			}
			
			public void  deleteFood(HttpServletRequest request,
					HttpServletResponse response) throws ServletException, IOException {
				// 1. 获取请求id
							String id = request.getParameter("id");
							// 2. 调用Service
							foodService.delete(Integer.parseInt(id));
				// 跳转到首页显示
				request.getRequestDispatcher("/sysFoodServlet?method=listFood").forward(request,response);
				
			}
			public void  search(HttpServletRequest request,
					HttpServletResponse response) throws ServletException, IOException {
				
				try {
					String keyword = request.getParameter("keyword");
					System.out.println(keyword);
					List<Food> food = foodService.search(keyword);
					System.out.println(food);
					// 保存到request
					request.setAttribute("listFood", food);
					// 跳转到首页显示
					request.getRequestDispatcher("/sys/foodList.jsp").forward(request, response);
				} catch (Exception e) {
					e.printStackTrace();
					
				}
				
			}
}
