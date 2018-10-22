<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title></title>
		<meta charset="UTF-8">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css/font-awesome.min.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css/ionicons.min.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css/dataTables.bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css/AdminLTE.min.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css/_all-skins.min.css">
		<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/jquery.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/jquery.dataTables.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/dataTables.bootstrap.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/adminlte.min.js"></script>
		<script type="text/javascript">
			var table;
			$(function() {
				table = $("#usertable").DataTable({
					"searching": false,			// 去掉搜索框
					"lengthChange": false,		// 去掉每页显示数量
					"sort": false,				// 去掉自动排序
					// 汉化信息
					"language": {
						"sLengthMenu":   "显示 _MENU_ 项结果",
						"sZeroRecords":  "没有匹配结果",
						"sInfo":         "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
						"sInfoEmpty":    "显示第 0 至 0 项结果，共 0 项",
						"sEmptyTable":     "表中数据为空",
						"sLoadingRecords": "载入中...",
						"sInfoThousands":  ",",
						"oPaginate": {
							"sFirst":    "首页",
							"sPrevious": "上页",
							"sNext":     "下页",
							"sLast":     "末页"
						}
            		},
					// 采用服务器端进行分页
					"serverSide": true,
					// 采用异步的方式获得信息列表
					"ajax": {
						// 请求地址
						"url": "<%=request.getContextPath()%>/user/list",
						"method": "post",			// 请求方式
						// 设定请求时所携带的参数
						"data": function(d) {
							// http://www.datatables.club/manual/server-side.html
							// d为提交服务器时候所携带的参数，其中start为页码，从0开始，length为分页条数默认为10
							// 重新设定查询参数
							var param = {
								// 查询参数
								"userName": $("#userName").val(),
								"pageNum": d.start + 1,
								"pageSize": d.length,
								// 服务器端必须返回的数据，Datatables发送的draw是多少那么服务器就返回多少，
								// 在原始的请求中会该参数
								"draw": d.draw		
							}
							// 如果需要向原有的数据后面追加参数：return $.extend({}, d, {"pageNum": 12});
							// 重新传递数据
							return $.extend({}, param);
						},
						"dataType": "json",				// 服务器返回的数据类型
						"dataFilter": function(json) {
							json = JSON.parse(json);
							// 对于服务器返回的数据进行过滤处理
							var returnData = {};
							returnData.draw = json.page.draw;
							returnData.recordsTotal = json.page.totalCount;
							returnData.recordsFiltered = json.page.totalCount;
							returnData.data = json.page.list;
							// 将模糊查询重新设定到搜索框
							$("#userName").val(json.userName);
							return JSON.stringify(returnData);
						}
					},
					// 定义每一行数据
					"columns": [
						{"data": "userId"},
						{"data": "userNo"},
						{"data": "username"},
						{"data": "loginName"},
						{"data": "gender"},
						{"data": "role.roleName"},
						{"data": "idCard"},
						{"data": "cellphone"},
						{"data": "email"},
						{"data": "birthday"},
						{"data": "status.statusCode"},
						{"data": "operation"}
					],
					// 添加序号和按钮
					// 自定义每一行数据某个单元格的内容
					"columnDefs": [{
						// 添加序号
						"targets": 0,			// 对应单元格的下表
						"render": function(data, type, row, meta) {
							// 对于当前页数的值默认有_iDisplayStart代表
							var pageNum = meta.settings._iDisplayStart;			// 当前页码，从0开始
							var pageSize = meta.settings._iDisplayLength;		// 每页显示数量
							return pageNum * pageSize + meta.row + 1;
						}
					}, {
						// 处理为null情况
						"targets": 4,			// 对应单元格的下表
						"render": function(data, type, row, meta) {
							// 如果父类为null则会报错
							var gender = row.gender;
							if (gender == "M") {
								return "男";
							} else {
								return "女";
							}
						}
					}, {
						// 处理为null情况
						"targets": 10,			// 对应单元格的下表
						"render": function(data, type, row, meta) {
							// 如果父类为null则会报错
							var statusCode = row.status.statusCode;
							if (statusCode == "ENABLE") {
								return "启用";
							} else {
								return "禁用";
							}
						}
					}, {
						// 处理最后的按钮
						"targets": 11,
						"render": function(data, type, row, meta) {
							var btns = "";
							var updateBtn = "<a href='#' class='btn btn-warning btn-xs'><i class='fa fa-wrench'></i>&nbsp;修改</a>";
							var statusBtn = "";
							// 增加启用/禁用按钮，根据该行数据的状态决定
							// row参数就是该行数据内容
							if (row.status.statusCode == "ENABLE") {
								// 如果处于启用状态，那么就变为禁用按钮
								statusBtn = "<a href='javascript:changestatus(\"" + row.userId + "\", \"DISABLE\")' class='btn btn-danger btn-xs'><i class='fa fa-toggle-off'></i>&nbsp;禁用</a>";
							} else {
								// 那么按钮就是启用
								statusBtn = "<a href='javascript:changestatus(\"" + row.userId + "\", \"ENABLE\")' class='btn btn-success btn-xs'><i class='fa fa-toggle-on'></i>&nbsp;启用</a>";
							}
							btns = btns + updateBtn + "&nbsp;" + statusBtn;
							return btns;
						}
					}]
				});
			});
			
			// 更改用户状态
			function changestatus(userId, statusCode) {
				$.ajax({
					url: "<%=request.getContextPath()%>/user/status",
					type: "put",
					data: {
						"userId": userId,
						"statusCode": statusCode
					},
					dataType: "json",
					success: function(data) {
						table.draw();
					}
				});
			}
			
			function openAddModal() {
				// 设定title内容
				var title = "<i class='fa fa-plus'></i>&nbsp;新增用户信息";
				var url = "<%=request.getContextPath()%>/user/create";
				var width = "600px";
				var height = "800px";
				parent.showModal(title, url, width, height);
			}
		</script>
	</head>
	<body>
		<section class="content-header">
      		<h1>后台用户信息管理</h1>
    	</section>
    	<section class="content">
	      	<div class="row">
	        	<div class="col-xs-12">
	          		<div class="box">
	          			<div class="box-body">
              				<table id="usertable" class="table table-bordered table-hover">
              					<thead>
              						<tr>
              							<td colspan="12">
              								<nav class="navbar navbar-default" role="navigation" style="margin: 0px;">
   	 											<div class="container-fluid">
    												<div>
        												<form class="navbar-form navbar-left" role="search">
            												<a href="javascript:openAddModal()" class="btn btn-primary">
            													<i class="fa fa-plus"></i>
            													&nbsp;新增用户信息
            												</a>
        												</form>
        												<form class="navbar-form navbar-right" role="form">
													        <div class="input-group">
													            <input type="text" id="userName" class="form-control" style="width: 220px; height: 36px;" placeholder="请填写用户姓名">
													            <span class="input-group-addon">
													            	<a href="#" class="btn btn-success btn-xs">
													            		<i class="fa fa-search"></i>
													            		&nbsp;搜索
													            	</a>
													            </span>
													        </div>
        												</form>
    												</div>
    											</div>
											</nav>
              							</td>
              						</tr>
                					<tr>
                						<th>序号</th>
                						<th>用户编号</th>
                						<th>用户姓名</th>
                						<th>登录名</th>
                						<th>性别</th>
                						<th>角色</th>
                						<th>身份证号码</th>
                						<th>联系电话</th>
                						<th>电子邮件</th>
                						<th>出生日期</th>
                						<th>状态</th>
                						<th>操作</th>
                					</tr>
                				</thead>
              				</table>
              			</div>
              		</div>
              	</div>
			</div>
		</section>
	</body>
</html>