<%@page import="javafx.scene.control.Alert"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- <html xmlns="http://www.w3.org/1999/xhtml"> -->
<html>
<head>
	<!-- 包含公共的JSP代码片段 -->
	
<title>无线点餐平台</title>



<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="${pageContext.request.contextPath }/app/style/js/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/app/style/js/page_common.js"></script>
<link href="${pageContext.request.contextPath }/app/style/css/common_style_blue.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/app/style/css/index_1.css" />
	<link href="${pageContext.request.contextPath }/app/style/css/index.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/app/style/css/dis_message.css" />
</head>
<script type="text/javascript">

</script>


<body style="text-align: center">
	<div id="all">
		<!--左边菜品详细信息 -->
		<div id="menu1">
			 <%
				//获得值
				String foodImg = request.getParameter("foodImg");
			    String foodName=new String(request.getParameter("foodName").getBytes("ISO-8859-1"),"UTF-8");
			    String foodRemark=new String(request.getParameter("foodRemark").getBytes("ISO-8859-1"),"UTF-8");
			    String foodPrice = request.getParameter("foodPrice");
			    String foodId = request.getParameter("foodId");
			%>

			<div class="menu2" style="text-align:center;">
				<img src="style/images/order_detials_bg.png" />
			</div>
			<div class="menu3">
				<div class="menu3_left">
					<img src="${pageContext.request.contextPath }/app/foodImg/<%=foodImg  %>"
						style="width:270px; height:290px;" />
				</div>
				<div class="menu3_right">
					<p>菜名:<%=foodName %></p>
					<p>价格:&yen;<%=foodPrice %></p>
					<p>简介:<%=foodRemark %></p>
				</div>
			</div>
			<div class="menu4">
				
				<a href="${pageContext.request.contextPath }/foodTypeServlet?method=addOderDetail&foodId=<%=foodId %>&foodName=<%=foodName %>&foodPrice=<%=foodPrice %>" style="background:url(style/images/img/order_left_corner_bg.png);">放入餐车</a>
				<a href="#" onclick="javascript:history.go(-1);" style="background:url(style/images/img/order_right_corner_bg.png);">返回</a>
			</div>
		</div>
			<!-- 右边菜系列表，菜品搜索框  -->
		<div id="dish_class">
		
			<div id="dish_top">
				<ul>
				<li class="dish_num"></li>
					<li>
						<a href="${pageContext.request.contextPath }/app/clientOrderList.jsp">
							<img src="${pageContext.request.contextPath }/app/style/images/call2.gif" />
						</a>
					</li>
				</ul>
			</div>
			
	        <form action="${pageContext.request.contextPath }/food?method=foodDetail" method="post">
			<div id="dish_2">
				<ul>
					<!-- 迭代菜系列表 -->
					<c:forEach var="foodType" items="${listFoodType}">
					
						<li>
							<a href="${pageContext.request.contextPath }/food?method=foodDetail&foodTypeId=${foodType.id}">${foodType.typeName}</a>
							<input type="hidden" name="foodTypeId" value="${foodType.id}">
						</li>
					</c:forEach>
						
				</ul>
			</div>
			</form>
			<div id="dish_3">
				<!-- 搜索菜品表单  -->
				<form action="${pageContext.request.contextPath }/food?method=foodDetail" method="post">
					<table width="166px">
						<tr>
							<td>
								<input type="text" id="foodname" name="foodName" class="select_value" />
								<%--<input type="hidden" value="selectFood" name="method">--%>
							</td>
						</tr>
						<tr>
							<td><input type="submit" id="sub" value="" /></td>
						</tr>
						<tr>
							<td>
								<a href="#">
									<img src="${pageContext.request.contextPath }/app/style/images/look.gif" />
								</a>
							</td>
						</tr>
					</table>
					</form>
			</div>
		
		
		
	</div>
</body>
</html>
