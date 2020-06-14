package cn.itcast.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.itcast.entity.DinnerTable;
import cn.itcast.entity.FoodType;
import cn.itcast.factory.BeanFactory;
import cn.itcast.service.IDinnerTableService;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/indexServlet")
public class indexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public indexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    private String uri;
    private IDinnerTableService dinnerTableService =
    		  BeanFactory.getInstance("dinnerTableService", IDinnerTableService.class);
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ��ȡ����������
		String method = request.getParameter("method");
		if (method == null) {
			// Ĭ��ִ�еķ����� ����ǰ̨�б����ҳ
			method = "listTable";
		}

		// �ж�
		if ("listTable".equals(method)) {
			
			allListTable(request, response);
		}else if ("addTable".equals(method)) {
			addTable(request, response);
		}else if ("update".equals(method)) {
			updateTable(request, response);
		}else if ("delete".equals(method)) {
			deleteTable(request, response);
		}
		else if ("noUseTable".equals(method)){
			// 1. ǰ̨��ҳ����ʾ����δԤ���Ĳ���
			listTable(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	// 1. ǰ̨��ҳ����ʾ����δԤ���Ĳ���
		public void  listTable(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
			try {
				// ��ѯ����δԤ������
				List<DinnerTable> list = dinnerTableService.findNoUseTable();
				
				// ���浽request
				request.setAttribute("listDinnerTable", list);
				// ��ת����ҳ��ʾ
				request.getRequestDispatcher("/app/index1.jsp").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		public void  allListTable(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
			// ��ѯ����δԤ������
			List<DinnerTable> list = dinnerTableService.findAllTable();
			// ���浽request
			request.setAttribute("listDinnerTable", list);
			// ��ת����ҳ��ʾ
			request.getRequestDispatcher("/sys/boardList.jsp").forward(request,response);
			
		}

		public void  addTable(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
			try {
				
				// 1. ��ȡ�������ݷ�װ
				String tableName = request.getParameter("tableName");
				DinnerTable dinnerTable = new DinnerTable();
				dinnerTable.setTableName(tableName);
				dinnerTable.setTableStatus(0);
				// 2. ����service����ҵ���߼�
				dinnerTableService.save(dinnerTable);
				// 3. ��ת
				uri = "/indexServlet?method=listTable";
			} catch (Exception e) {
				e.printStackTrace();
				uri="/error/error.jsp";
				
			}
			// ��ת����ҳ��ʾ
			request.getRequestDispatcher(uri).forward(request,response);
			
		}
		
		public void  updateTable(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
			int id = Integer.parseInt(request.getParameter("id"));
			dinnerTableService.update(id);
			// ��ת����ҳ��ʾ
			request.getRequestDispatcher("/indexServlet?method=listTable").forward(request,response);
			
		}
		
		public void  deleteTable(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
			// 1. ��ȡ����id
						String id = request.getParameter("id");
						// 2. ����Service
						dinnerTableService.delete(Integer.parseInt(id));
			// ��ת����ҳ��ʾ
			request.getRequestDispatcher("/indexServlet?method=listTable").forward(request,response);
			
		}
}
