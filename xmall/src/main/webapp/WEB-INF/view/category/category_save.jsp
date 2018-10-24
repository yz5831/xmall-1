<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
	<head>
		<title></title>
		<meta charset="UTF-8">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css/font-awesome.min.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css/ionicons.min.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css/AdminLTE.min.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css/_all-skins.min.css">
		<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/jquery.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/adminlte.min.js"></script>
		<script type="text/javascript">
			$(function() {
				// 页面加载完毕之后，判断此时是否是一级类别
				var level = $("#level").val();
				if (level == 1) {
					// 将父类变为不可用
					$("#parentId").attr("disabled", "disabled");
				} else {
					// 否则可用
					$("#parentId").removeAttr("disabled");
				}
			});
			
			function change() {
				var level = $("#level").val();
				if (level == 1) {
					// 将父类变为不可用
					$("#parentId").attr("disabled", "disabled");
				} else {
					// 否则可用
					$("#parentId").removeAttr("disabled");
				}
			}
			
			function checkText() {
				var text = $("#text").val();
				var categoryId = $("#categoryId").val();
				if (text != null) {
					$.ajax({
						"url": "<%=request.getContextPath()%>/category/text",
						"method": "post",
						"data": {
							"id": categoryId,
							"text": text
						},
						"dataType": "json",
						"success": function(data) {
							if (data) {
								$("#textMsg").html("");
								$("#subbtn").removeAttr("disabled");
							} else {
								$("#textMsg").html("该类别名称已被占用");
								$("#subbtn").attr("disabled", "disabled");
							}
						}
					});
				} else {
					$("#textMsg").html("请填写类别名称");
					$("#subbtn").attr("disabled", "disabled");
				}
			}
			
			function save() {
				var saveform = $("#saveform").serialize();
				$.ajax({
					"url": "<%=request.getContextPath()%>/category/",
					"method": "post",
					"data": saveform,
					"dataType": "json",
					"success": function(data) {
						if (data) {
							alert("保存成功");
						} else {
							alert("保存失败");
						}
						parent.closeModal();
						parent.openiframe("<%=request.getContextPath()%>/category/index");
					}
				});
			}
		</script>
	</head>
	<body>
		<form id="saveform" role="form">
			<input type="hidden" id="categoryId" name="categoryId" value="${category.categoryId }"/>
			<table style="border-collapse: separate; border-spacing: 0px 10px;">
				<tr>
					<td align="right">
						<i class="fa fa-user-secret"></i>&nbsp;
						类别级别：
					</td>
					<td>
						<select id="level" class="form-control" onchange="change();" style="width: 220px;">
							<c:choose>
								<c:when test="${category.parent eq null }">
									<option value="1" selected>一级分类</option>
									<option value="2">二级分类</option>
								</c:when>
								<c:otherwise>
									<option value="1">一级分类</option>
									<option value="2" selected>二级分类</option>
								</c:otherwise>
							</c:choose>
						</select>
					</td>
					<td style="padding-left: 5px;">
						<span style="color: red;">*</span>
						&nbsp;
						<span id="nameMsg" style="color: red;"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<i class="fa fa-user-secret"></i>&nbsp;
						上级类别：
					</td>
					<td>
						<select id="parentId" name="parentId" class="form-control" style="width: 220px;" disabled>
							<c:forEach var="parent" items="${categoryList }">
								<c:choose>
									<c:when test="${category.parent.category eq parent.parent.categoryId }">
										<option value="${parent.categoryId }" selected>${parent.text }</option>
									</c:when>
									<c:otherwise>
										<option value="${parent.categoryId }">${parent.text }</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
					</td>
					<td style="padding-left: 5px;">
						<span style="color: red;">*</span>
						&nbsp;
						<span id="nameMsg" style="color: red;"></span>
					</td>
				</tr>
				<tr style="margin-bottom: 5px;">
					<td align="right">
						<i class="fa fa-code"></i>&nbsp;
						类别名称：
					</td>
					<td>
						<input type="text" id="text" name="text" value="${category.text }" onblur="checkText();" class="form-control" style="width: 220px;" placeholder="请填写类别名称"/>
					</td>
					<td style="padding-left: 5px;">
						<span style="color: red;">*</span>
						&nbsp;
						<span id="textMsg" style="color: red;"></span>
					</td>
				</tr>
				<tr style="margin-bottom: 5px;">
					<td align="right">
						<i class="fa fa-code"></i>&nbsp;
						类别排序：
					</td>
					<td>
						<input type="text" id="sortOrder" name="sortOrder" value="${category.sortOrder }" class="form-control" style="width: 220px;" placeholder="请填写类别排序"/>
					</td>
					<td style="padding-left: 5px;">
						<span style="color: red;">*</span>
						&nbsp;
						<span id="codeMsg" style="color: red;"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<i class="fa fa-user-secret"></i>&nbsp;
						状态：
					</td>
					<td>
						<select id="statusId" name="statusId" class="form-control" style="width: 220px;">
							<c:forEach var="status" items="${statusList }">
								<c:choose>
									<c:when test="${category.status.statusId eq status.statusId }">
										<option value="${status.statusId }" selected>${status.statusName }</option>
									</c:when>
									<c:otherwise>
										<option value="${status.statusId }">${status.statusName }</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
					</td>
					<td style="padding-left: 5px;">
						<span style="color: red;">*</span>
						&nbsp;
						<span id="nameMsg" style="color: red;"></span>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="right">
						<button id="subbtn" type="button" onclick="save();" class="btn btn-success" disabled="disabled">
							<i class="fa fa-plus"></i>&nbsp;
							添加商品类别信息
						</button>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>