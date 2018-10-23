<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
	<head>
		<title></title>
		<meta charset="UTF-8">
  		<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  		<link rel="stylesheet" type="text/css" type="text/css" type="text/css" href="<%=request.getContextPath() %>/static/css/bootstrap.min.css">
  		<link rel="stylesheet" type="text/css" type="text/css" href="<%=request.getContextPath() %>/static/css/font-awesome.min.css">
  		<link rel="stylesheet" type="text/css" type="text/css" href="<%=request.getContextPath() %>/static/css/ionicons.min.css">
  		<link rel="stylesheet" type="text/css" type="text/css" href="<%=request.getContextPath() %>/static/css/AdminLTE.min.css">
  		<link rel="stylesheet" type="text/css" type="text/css" href="<%=request.getContextPath() %>/static/css/_all-skins.min.css">
  		<link rel="stylesheet" type="text/css" type="text/css" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
	
		<script src="<%=request.getContextPath() %>/static/js/jquery.min.js"></script>
		<script src="<%=request.getContextPath() %>/static/js/jquery-ui.min.js"></script>
		<script src="<%=request.getContextPath() %>/static/js/bootstrap.min.js"></script>
		<script src="<%=request.getContextPath() %>/static/js/adminlte.min.js"></script>
	
		<script type="text/javascript">
			function openiframe(url) {
				var iframe="<iframe onscroll='true' src='" + url + "' style='width: 100%; height: 100%; min-height:  424px; border: 0px;'></iframe>";
				$("#myframe").html(iframe);
			}
			
			function showModal(title, url, width, height) {
				// 设定模态框标题
				$("#myModalTitile").html(title);
				$("#modalContent").css("width", width);
				// 设定url
				var iframe = "<iframe onscroll='true' src='" + url + "' style='width: 100%; height: " + height + "; min-height:  424px; border: 0px;'></iframe>";
				// 设定模态框具体内容
				$("#myModalBody").html(iframe);
				// 展示模态框
				$("#myModal").modal({
					keyboard: false
				});
			}
			
			function closeModal() {
				$("#myModal").modal("hide");
			}
		</script>
	</head>
	<body class="hold-transition skin-blue sidebar-mini">
		<div class="wrapper">
  			<header class="main-header">
    			<a href="#" class="logo">
      				<span class="logo-mini"><b>XML</b></span>
      				<span class="logo-lg"><b>XMALL</b>&nbsp;Manager</span>
    			</a>
    			<nav class="navbar navbar-static-top">
      				<a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
        				<span class="sr-only">Toggle navigation</span>
      				</a>
      				<div class="navbar-custom-menu">
        				<ul class="nav navbar-nav">
          					<li class="dropdown messages-menu">
            					<a href="#" class="dropdown-toggle" data-toggle="dropdown">
              						<i class="fa fa-user"></i>
              						<b>欢迎：${sessionScope.user.username }</b>
            					</a>
          					</li>
          					<li class="dropdown notifications-menu">
            					<a href="#" class="dropdown-toggle" data-toggle="dropdown">
             						<i class="fa fa-sign-out"></i>
             						<b>退出</b>
           						</a>
          					</li>
        				</ul>
      				</div>
    			</nav>
  			</header>
  			<aside class="main-sidebar">
    			<section class="sidebar">
      				<div class="user-panel" style="height: 40px;">
        				<div class="pull-left info">
          					<p>2018-10-11</p>
        				</div>
      				</div>
      				<ul class="sidebar-menu" data-widget="tree">
      					<c:forEach var="menu" items="${menuList }">
      						<li class="treeview">
      							<a href="#">
      								<i class="${menu.icon }"></i>
      								<span>${menu.text }</span>
      								<span class="pull-right-container">
	            						<i class="fa fa-angle-left pull-right"></i>
	          						</span>
      							</a>
      							<ul class="treeview-menu">
      								<c:forEach var="second" items="${menu.menuList }">
      									<li>
      										<a href="javascript:openiframe('${second.url }')">
      											<i class="${second.icon }"></i> ${second.text }
      										</a>
      									</li>
      								</c:forEach>
      							</ul>
      						</li>
      					</c:forEach>
      				</ul>
    			</section>
  			</aside>
  			<div id="myframe" class="content-wrapper"></div>
  			<footer class="main-footer">
    			<div class="pull-right hidden-xs">
      				<b>Version</b> 2.4.0
    			</div>
    			<strong>Copyright &copy; 2014-2016 <a href="https://adminlte.io">Almsaeed Studio</a>.</strong> All rights
    			reserved.
  			</footer>
  			<div class="control-sidebar-bg"></div>
		</div>
		
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    		<div class="modal-dialog">
        		<div class="modal-content" id="modalContent">
            		<div class="modal-header">
                		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                		<h4 class="modal-title" id="myModalTitile"></h4>
            		</div>
            		<div class="modal-body" id="myModalBody"></div>
        		</div>
    		</div>
		</div>
	</body>
</html>