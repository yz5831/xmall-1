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
			function checkRoleName() {
				// 检查角色名称
				var roleName = $("#roleName").val();
				if (roleName != null && roleName.trim() != "") {
					// 异步校验
					$.ajax({
						"url": "<%=request.getContextPath()%>/role/checkRoleName",
						"type": "get",
						"data": {
							"roleName": roleName
						},
						"dataType": "json",
						success: function(data) {
							if (data) {
								var roleCode = $("#roleCode").val();
								if (roleCode != null && roleCode.trim() != "") {
									$("#btnTr").css("display", "block");
								}
								var msg = $("#nameMsg").html("");
							} else {
								var msg = $("#nameMsg").html("该角色名称已被占用");
							}
						}
					});
				} else {
					// 显示错误信息
					var msg = $("#nameMsg").html("请填写角色名称！");
				}
			}
			
			function checkRoleCode() {
				var roleCode = $("#roleCode").val();
				if (roleCode != null && roleCode.trim() != "") {
					// 异步校验
					$.ajax({
						"url": "<%=request.getContextPath()%>/role/checkRoleCode",
						"type": "get",
						"data": {
							"roleCode": roleCode
						},
						"dataType": "json",
						success: function(data) {
							if (data) {
								var roleName = $("#roleName").val();
								if (roleName != null && roleName.trim() != "") {
									$("#btnTr").css("display", "block");
								}
								var msg = $("#codeMsg").html("");
							} else {
								var msg = $("#codeMsg").html("该角色编码已被占用");
							}
						}
					});
				} else {
					// 显示错误信息
					var msg = $("#codeMsg").html("请填写角色编码！");
				}
			}
		</script>
	</head>
	<body>
		<form id="createform" role="form">
			<table style="border-collapse: separate; border-spacing: 0px 10px;">
				<tr>
					<td align="right">
						<i class="fa fa-user-secret"></i>&nbsp;
						角色名称：
					</td>
					<td>
						<input type="text" id="roleName" name="roleName" onblur="checkRoleName();" class="form-control" style="width: 220px;" placeholder="请填写角色名称"/>
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
						角色编码：
					</td>
					<td>
						<input type="text" id="roleCode" name="roleCode" onblur="checkRoleCode();" class="form-control" style="width: 220px;" placeholder="请填写角色编码"/>
					</td>
					<td style="padding-left: 5px;">
						<span style="color: red;">*</span>
						&nbsp;
						<span id="codeMsg" style="color: red;"></span>
					</td>
				</tr>
				<tr style="margin-bottom: 5px;">
					<td align="right">
						<i class="fa fa-user"></i>&nbsp;
						角色状态：
					</td>
					<td>
						<select id="statusId" name="statusId" class="form-control" style="width: 220px;">
							<c:forEach var="status" items="${statusList }">
								<c:choose>
									<c:when test="${status.statusCode eq 'ENABLE' }">
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
					</td>
				</tr>
				<tr>
					<td colspan="2" align="right">
						<a href="javascript:saveform();" class="btn btn-success" re>
							<i class="fa fa-plus"></i>&nbsp;
							添加角色信息
						</a>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>