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
			// 设定全局变量，用于引用dataTables对象
			var mytable;
			$(function() {
				// 加载数据表格
				mytable = $("#mytable").DataTable({
					// 采用服务器端进行分页
					"serverSide": true,
					// 采用异步的方式获得信息列表
					"ajax": {
						// 请求地址
						"url": "<%=request.getContextPath()%>/category/list",
						"method": "post",			// 请求方式
						// 设定请求时所携带的参数
						"data": function(d) {
							// http://www.datatables.club/manual/server-side.html
							// d为提交服务器时候所携带的参数，其中start为页码，从0开始，length为分页条数默认为10
							// 重新设定查询参数
							var param = {
								"pageNum": d.start + 1,
								"pageSize": d.length,
								"categoryName": $("#categoryName").val(),
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
							$("#categoryName").html(json.categoryName);
							return JSON.stringify(returnData);
						}
					},
					// 自定义每一列数据显示结果
					"columnDefs": [{
						"targets": 0,			// 定位需要使用的列，下标从0开始
						// 自定义该列数据
						"render": function(data, type, row, meta) {
							var pageNum = meta.settings._iDisplayStart;			// 当前页码，从0开始
							var pageSize = meta.settings._iDisplayLength;		// 每页显示数量
							return pageNum * pageSize + meta.row + 1;
						}
					}]
				});
			});
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
              							<th>序号</th>
              							<th>上级类别</th>
              							<th>类别名称</th>
              							<th>排序</th>
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