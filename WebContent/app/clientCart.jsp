<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/app/style/css/index.css" />
	<script type="text/javascript">
		
		// 修改菜品项数量
		function alterSorder(rowid, quantityInput) {
            quantity = quantityInput.value
            if (isNaN(quantity)) {
                alert("不是有效的数值！");
                quantityInput.value = 0;
                quantity = quantityInput.value
                quantityInput.focus();
                // return;
            }
            // 单价id
            var price_id = 'price_' + rowid;
            // 单价
            var price = parseFloat(document.getElementById(price_id).innerText);

            // 小计id
            var subtotal_id = 'subtotal_' + rowid;
            // 小计(更新之前)
            subtotal1 = parseFloat(document.getElementById(subtotal_id).innerText);
            //四舍五入并保留两位小数
            subtotal1 = subtotal1.toFixed(2);
            document.getElementById(subtotal_id).innerText = quantity * price;
            // 小计(更新之后)
            subtotal2 = parseFloat(document.getElementById(subtotal_id).innerText);
            // 合计
            total = parseFloat(document.getElementById('total').innerText);
            // 计算合计
            total = total - subtotal1 + subtotal2;
            //四舍五入并保留两位小数
            total = total.toFixed(2);
            //更新合计
            document.getElementById('total').innerText = total;

		}
		
		// 下单
		function genernateOrder() {
			window.location.href = "${pageContext.request.contextPath }/foodTypeServlet?method=submitList";
		}
	</script>
</head>

<body style="text-align: center">
	<div id="all">
		<div id="menu">
		 <form action="${pageContext.request.contextPath }/foodTypeServlet?method=submitList" method="Post">
			<!-- 餐车div -->
			<div id="count">
				<table align="center" width="100%">
					<tr height="40">
				 		<td align="center" width="20%">菜名</td>
				 		<td align="center" width="20%">单价</td>
				 		<td align="center" width="20%">数量</td>
				 		<td align="center" width="20%">小计</td>
				 	</tr>
				 	<c:forEach var="row" items="${cart}">
					<tr height="60">
							<td align="center" width="20%">${row.foodName }</td>
					 		<td align="center" width="20%">￥<span id="price_${row.foodId}">${row.foodPrice }</span></td>
					 		<td align="center" width="20%">
					 			<input name="quantity_${row.foodId}"  type="text" value="${row.count }" size="3" lang="3" onblur="alterSorder(${row.foodId},this)"/>
					 		</td>
					 		<td align="center" width="20%"><span id="subtotal_${row.foodId}">${row.count * row.foodPrice}</span></td>
				 	</tr>
                    </c:forEach>
                    
					<tr>
						<td colspan="6" align="right">总计:
							<span style="font-size:36px;">&yen;&nbsp;<span id="total">${total}</span></span>
							<label
								id="counter" style="font-size:36px"></label>
						</td>
					</tr>
					<tr>
						<td colspan="6" style="margin-left: 100px; text-align: center;"align="right">
							<input type="hidden" name="bId" value="">
							
								<c:if test="${not empty cart}">
								    <input type="submit" value="下单" class="btn_next"  />
								   <!--  onclick="genernateOrder()" -->
                                </c:if> 
						</td>
					</tr>
				</table>
			</div>
			</form>
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
	</div>
</body>
</html>
