package cn.itcast.servlet;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.itcast.entity.DinnerTable;
import cn.itcast.entity.Food;
import cn.itcast.entity.FoodType;
import cn.itcast.utils.Condition;
import cn.itcast.utils.PageBean;
import jdk.nashorn.internal.ir.RuntimeNode.Request;

public class FoodServlet extends BaseServlet {

	/**
	 * 1. ������ҳ����ʾ��Ʒ�Լ���ϵ
	 */
	
	public Object foodDetail(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		//1.1 ��ȡ����ID,����ID��ѯ���ٰѲ�ѯ���Ľ�����浽session �����ɶ����ã�
		// ֻ��Ҫִ��һ�μ���: �ȴ�session��ȡ����û�в������� ���û�У���ִ�и���������ѯ��
		// ���sesison���Ѿ��в������󣬾Ͳ�ִ��������ѯ
		Object obj = session.getAttribute("dinnerTable");
		// �ж�
		if (obj == null){
			// ֻ�ڵ�һ��ִ�е�ʱ�򣬲�ѯ��������
			String tableId = request.getParameter("tableId");
			//���ò���״̬
			dinnerTableService.setStatus(Integer.valueOf(tableId));
			session.setAttribute("tableId",tableId);
			
			DinnerTable dt = dinnerTableService.findById(Integer.parseInt(tableId));
			// ���浽session
			session.setAttribute("dinnerTable", dt);
//		
		}
		
		//1.2 ��ѯ���еġ���Ʒ��Ϣ��, ����
		PageBean<Food> pb = new PageBean<Food>();
		// ��ҳ������ ��ȡ��ǰҳ����
		String curPage = request.getParameter("currentPage");
		// �ж�
		if (curPage == null || "".equals(curPage.trim())) {
			// ��һ�η��ʣ����õ�ǰҳΪ1
			pb.setCurrentPage(1);
		} else {
			// �����õ�ǰҳ������
			pb.setCurrentPage(Integer.parseInt(curPage));
		}
		
		// ��������
		Condition condition = new Condition();
		// ��ҳ������ ��ϵid
		String foodTypeId = request.getParameter("foodTypeId");
		
		if (foodTypeId != null) {  // ������Ϊnull,����Ϊ�������ǾͲ�ѯȫ��
			// ��������
			condition.setFoodTypeId(Integer.parseInt(foodTypeId));
		}
		// ��ҳ������ ������
		request.setCharacterEncoding("utf-8");
		String foodName = request.getParameter("foodName");
		//System.out.println("set"+new String(foodName.getBytes("ISO-8859-1"), "utf-8"));
		// ���ò�Ʒ����
		if (foodName != null && !"".equals(foodName.trim())) {
			condition.setFoodName(new String(foodName.getBytes("ISO-8859-1"), "utf-8"));//ת��
		}else {
			condition.setFoodName(foodName);
		}
		
		// ��������������pb�С�
		pb.setCondition(condition);

		// ---->��ҳ��ѯ
		foodService.getAll(pb);
		// �����ѯ���pb����
		request.setAttribute("pb", pb);
		
		//1.3 ��ѯ���еġ���ϵ��Ϣ���� ����
		List<FoodType> listFoodType = foodTypeService.getAll();
		
	        //�����ݴ洢��session��
	        session.setAttribute("listFoodType",listFoodType);
		//request.setAttribute("listFoodType", listFoodType);
		
		//1.4 ��ת(ת��)
		return request.getRequestDispatcher("/app/caidan.jsp");
	}
}












