<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
   <!-- 包含公共的JSP代码片段 -->
	
<title>无线点餐平台</title>



<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="${pageContext.request.contextPath }/sys/style/js/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/sys/style/js/page_common.js"></script>
<link href="${pageContext.request.contextPath }/sys/style/css/common_style_blue.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/sys/style/css/index_1.css" />
</head>
<body>

<!-- 页面标题 -->
<div id="TitleArea">
	<div id="TitleArea_Head"></div>
	<div id="TitleArea_Title">
		<div id="TitleArea_Title_Content">
			
				
					<img border="0" width="13" height="13" src="style/images/title_arrow.gif"/> 更新新菜品
				
				
			
		</div>
    </div>
	<div id="TitleArea_End"></div>
</div>

<!-- 主内容区域（数据列表或表单显示） -->
<div id="MainArea">
	<!-- 表单内容 -->
	<form action="${pageContext.request.contextPath }/sysFoodServlet?method=updateFood" method="post" enctype="multipart/form-data">
		<!-- 本段标题（分段标题） -->
		<div class="ItemBlock_Title">
        	<img width="4" height="7" border="0" src="style/images/item_point.gif"> 菜品信息&nbsp;
        </div>
		<!-- 本段表单字段 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
				<div class="ItemBlock2">
					<table cellpadding="0" cellspacing="0" class="mainForm" >
                    <tr>
							<td width="80px">菜系</td>
							<td>
                            <select name="foodTypeId" style="width:80px var="food" id="food" items="${requestScope.listFood}">
	                            <c:forEach var="food" items="${requestScope.listFood}">
								
							</c:forEach>
			   						<option value="1" 
			   							selected="selected"
			   						>粤菜</option>
			   						
			   					
			   						<option value="2" 
			   							
			   						>川菜</option>
			   						
			   					
			   						<option value="3" 
			   							
			   						>湘菜</option>
			   						
			   					
			   						<option value="4" 
			   							
			   						>东北菜</option>
			   						
			   					
                            </select>
                             *<input type="hidden" name="ild" value="1" /></td>
						</tr>
						<tr>
							<td width="80px">菜名</td>
							<td><input type="text" name="foodName" id="foodName" class="InputStyle" value="${food.foodName}"/> *</td>
						</tr>
						<tr>
							<td>价格</td>
							<td><input type="text" name="price" class="InputStyle" id="price" value="${food.price}"/> *</td>
						</tr>
                        <tr>
							<td>会员价格</td>
							<td><input type="text" name="mprice" id="mprice" class="InputStyle" value="${food.mprice}"/> *</td>
						</tr>
						
						<tr>
							<td>简介</td>
							<td><textarea name="remark" id="remark" class="TextareaStyle"></textarea>${food.remark}</td>
						</tr>
						<tr>
							<td width="80px">菜品图片</td>
							<td>
									<input type="file" name="img" > *
							</td>
						</tr>
						<%-- <tr style="text-align: center;">
							<td colspan="2">
							    <img id="preview" src="${pageContext.request.contextPath }/upload/food/${food.img}" width=100px height=100px style="diplay:none" />
							</td>
						</tr> --%>
					</table>
				</div>
            </div>
        </div>
		
		
		<!-- 表单操作 -->
		<div id="InputDetailBar">
            
				
					 <input type="submit" value="修改" class="FunctionButtonInput">
				
				
			
            
            <a href="javascript:history.go(-1);" class="FunctionButton">返回</a>
        </div>
	</form>
</div>
</body>
</html>
