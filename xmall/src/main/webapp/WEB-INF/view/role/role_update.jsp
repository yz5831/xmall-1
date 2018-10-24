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
				var roleId = $("#roleId").val();
				if (roleName != null && roleName.trim() != "") {
					// 异步校验
					$.ajax({
						"url": "<%=request.getContextPath()%>/role/checkRoleName",
						"type": "get",
						"data": {
							"roleId": roleId,
							"roleName": roleName
						},
						"dataType": "json",
						success: function(data) {
							if (data) {
								var roleCode = $("#roleCode").val();
								if (roleCode != null && roleCode.trim() != "") {
									$("#subbtn").removeAttr("disabled");
								} else {
									$("#codeMsg").html("请填写角色编码");
									$("#subbtn").attr("disabled", "disabled");
								}
								$("#nameMsg").html("");
							} else {
								$("#nameMsg").html("该角色名称已被占用");
								$("#subbtn").attr("disabled", "disabled");
							}
						}
					});
				} else {
					// 显示错误信息
					$("#nameMsg").html("请填写角色名称");
					$("#subbtn").attr("disabled", "disabled");
				}
			}
			
			function checkRoleCode() {
				var roleCode = $("#roleCode").val();
				var roleId = $("#roleId").val();
				if (roleCode != null && roleCode.trim() != "") {
					// 异步校验
					$.ajax({
						"url": "<%=request.getContextPath()%>/role/checkRoleCode",
						"type": "get",
						"data": {
							"roleId": roleId,
							"roleCode": roleCode
						},
						"dataType": "json",
						success: function(data) {
							if (data) {
								var roleName = $("#roleName").val();
								if (roleName != null && roleName.trim() != "") {
									$("#subbtn").removeAttr("disabled");
								} else {
									$("#nameMsg").html("请填写角色名称");
									$("#subbtn").attr("disabled", "disabled");
								}
								$("#codeMsg").html("");
							} else {
								$("#codeMsg").html("该角色编码已被占用");
								$("#subbtn").attr("disabled", "disabled");
							}
						}
					});
				} else {
					// 显示错误信息
					$("#codeMsg").html("请填写角色编码");
					$("#subbtn").attr("disabled", "disabled");
				}
			}
			
			function save() {
				// 使用异步的方式进行保存信息
				var updateform = $("#updateform").serialize();
				$.ajax({
					"url": "<%=request.getContextPath()%>/role/update",
					"type": "put",
					"data": updateform,
					"dataType": "json",
					"success": function(data) {
						if (data) {
							alert("修改成功");
						} else {
							alert("修改失败");
						}
						// 重新刷新列表
						parent.closeModal();
						parent.openiframe("<%=request.getContextPath()%>/role/index");
					}
				});
			}
		</script>
	</head>
	<body>
		<form id="updateform" role="form">
			<table style="border-collapse: separate; border-spacing: 0px 10px;">
				<tr>
					<td align="right">
						<i class="fa fa-user-secret"></i>&nbsp;
						角色名称：
					</td>
					<td>
						<input type="text" id="roleName" name="roleName" value="${role.roleName }" onblur="checkRoleName();" class="form-control" style="width: 220px;" placeholder="请填写角色名称"/>
						<input type="hidden" id="roleId" name="roleId" value="${role.roleId }">
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
						<input type="text" id="roleCode" name="roleCode" value="${role.roleCode }" onblur="checkRoleCode();" class="form-control" style="width: 220px;" placeholder="请填写角色编码"/>
					</td>
					<td style="padding-left: 5px;">
						<span style="color: red;">*</span>
						&nbsp;
						<span id="codeMsg" style="color: red;"></span>
					</td>
				</tr>
				<tr style="margin-bottom: 5px;">
					<td align="right">
						<i class="fa fa-unlock-al"></i>&nbsp;
						角色状态：
					</td>
					<td>
						<select id="statusId" name="statusId" class="form-control" style="width: 220px;">
							<c:forEach var="status" items="${statusList }">
								<c:choose>
									<c:when test="${status.statusCode eq role.status.statusCode }">
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
						<button id="subbtn" type="button" onclick="save();" class="btn btn-success" disabled="disabled">
							<i class="fa fa-plus"></i>&nbsp;
							修改角色信息
						</button>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>