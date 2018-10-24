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
				table = $("#mytable").DataTable({
					"searching": false,			// 去掉搜索框
					"lengthChange": false,		// 去掉每页显示数量
					"sort": false,				// 去掉自动排序
					"serverSide": true,			// 采用服务器端分页获得数据
					"ajax": {
						// 请求路径
						"url": "<%=request.getContextPath()%>/category/list",
						"method": "post",			// 请求方式
						"dataType": "json",			// 服务器端返回数据格式
						// 请求携带参数
						"data": function(d) {
							var queryParam = {
								"pageNum": d.start + 1,				// 开始页码
								"pageSize": d.length,				// 每页显示数量，默认为10条
								"query": $("#query").val(),	// 查询参数
								"draw": d.draw
							};
							return $.extend({}, queryParam);
						},
						// 对于服务器返回结果进行过滤
						"dataFilter": function(json) {
							json = JSON.parse(json);
							// 对于服务器返回的数据进行过滤处理
							var returnData = {};
							returnData.draw = json.page.draw;
							returnData.recordsTotal = json.page.totalCount;
							returnData.recordsFiltered = json.page.totalCount;
							returnData.data = json.page.list;
							
							// 设定模糊查询结果
							$("#queryName").val(json.queryName);
							return JSON.stringify(returnData);
						}
					},
					// 将查询数据对应到表格中
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
						// 添加序号
						"targets": 1,			// 对应单元格的下表
						"render": function(data, type, row, meta) {
							// 判断是否有上级信息
							var parent = row.parent;
							if (parent != null) {
								return parent.categoryName
							} else {
								return "";
							}
						}
					}, {
						// 添加序号
						"targets": 2,			// 对应单元格的下表
						"render": function(data, type, row, meta) {
							return row.text
						}
					}, {
						// 添加序号
						"targets": 3,			// 对应单元格的下表
						"render": function(data, type, row, meta) {
							return row.sortOrder;
							// 创建时间
							var date = row.createTime;
							return date.substring(0, 10);
						}
					}, {
						"targets": 4,			// 对应单元格的下表
						"render": function(data, type, row, meta) {
							var date = row.createTime;
							if (date != null) {
								return date.substring(0, 10);
							} else {
								return "";
							}
						}
					}, {
						"targets": 5,			// 对应单元格的下表
						"render": function(data, type, row, meta) {
							if (row.status != null) {
								return row.status.statusName;
							} else {
								return "";
							}
						}
					}, {
						// 添加序号
						"targets": 6,			// 对应单元格的下表
						"render": function(data, type, row, meta) {
							var updateBtn = "<a href='javascript:openSaveModel(\"" + row.categoryId + "\")' class='btn btn-warning btn-xs'><i class='fa fa-wrench'></i>&nbsp;修改</a>"
							// 判断状态按钮
							var statusBtn = "";
							if (row.status != null && row.status.statusCode == "ENABLE") {
								// 显示禁用按钮
								statusBtn = "<a href='javascript:changeStatus(\"" + row.categoryId + "\", \"" + row.text + "\", \"DISABLE\")' class='btn btn-danger btn-xs'><i class='fa fa-toggle-off'></i>&nbsp;禁用</a>";
							} else if (row.status != null && row.status.statusCode == "DISABLE") {
								// 显示启用按钮
								statusBtn = "<a href='javascript:changeStatus(\"" + row.categoryId + "\", \"" + row.text + "\", \"ENABLE\")' class='btn btn-success btn-xs'><i class='fa fa-toggle-on'></i>&nbsp;启用</a>";
							}
							return updateBtn + "&nbsp;" + statusBtn;
						}
					}],
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
	        		}
				});
			});
			
			function openSaveModel(id) {
				// 开启添加页面
				var title = "<i class='fa fa-plus'></i>&nbsp;添加商品类别信息";
				var url = "<%=request.getContextPath()%>/category/" + id;
				var width = "600px";
				var height = "300px";
				parent.showModal(title, url, width, height);
			}
			
			function search() {
				table.draw();
			}
			
			function changeStatus(id, name, code) {
				// 更新状态
				var flag;
				if (code == "DISABLE") {
					flag = confirm("确定要禁用" + name + "？");
				} else {
					flag = confirm("确定要启用" + name + "？");
				}
				if (flag) {
					$.ajax({
						"url": "<%=request.getContextPath()%>/category/status",
						"method": "put",
						"data": {
							"id": id,
							"code": code
						},
						"dataType": "json",
						success: function(data) {
							if (data) {
								// 提示更改成功
								alert("状态更改成功！");
							} else {
								alert("状态更改失败！");
							}
							// 刷新列表
							table.draw();
						}
					});
				} else {
					return;
				}
			}
		</script>
	</head>
	<body>
		<section class="content-header">
      		<h1>
      			<i class="fa fa-bars"></i>&nbsp;
      			商品类别信息管理
      		</h1>
    	</section>
    	
    	<section class="content">
	      	<div class="row">
	        	<div class="col-xs-12">
	          		<div class="box">
	          			<div class="box-body">
              				<table id="mytable" class="table table-bordered table-hover">
              					<thead>
              						<tr>
              							<td colspan="7">
              								<nav class="navbar navbar-default" role="navigation" style="margin: 0px;">
   	 											<div class="container-fluid">
    												<div>
        												<form class="navbar-form navbar-left" role="search">
        													<a href="javascript:openSaveModel('0');" class="btn btn-success">
        														<i class="fa fa-plus"></i>
            													&nbsp;新增商品类别信息
        													</a>
        												</form>
        												<form class="navbar-form navbar-right" role="form">
													        <div class="input-group">
													            <input type="text" id="query" class="form-control" style="width: 220px; height: 36px;" placeholder="请填写商品类别名称">
													            <span class="input-group-addon">
													            	<a href="javascript:search();" class="btn btn-success btn-xs">
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
              							<th>上级类别</th>
              							<th>类别名称</th>
              							<th>顺序字段</th>
              							<th>创建时间</th>
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