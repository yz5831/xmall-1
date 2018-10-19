<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title></title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  		<link rel="stylesheet" href="<%=request.getContextPath() %>/static/css/bootstrap.min.css">
  		<link rel="stylesheet" href="<%=request.getContextPath() %>/static/css/font-awesome.min.css">
  		<link rel="stylesheet" href="<%=request.getContextPath() %>/static/css/ionicons.min.css">
  		<link rel="stylesheet" href="<%=request.getContextPath() %>/static/css/AdminLTE.min.css">
  		<link rel="stylesheet" href="<%=request.getContextPath() %>/static/css/blue.css">
  		<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
	
		<script src="<%=request.getContextPath() %>/static/js/jquery-3.3.1.min.js"></script>
		<script src="<%=request.getContextPath() %>/static/js/bootstrap.min.js"></script>
		<script src="<%=request.getContextPath() %>/static/js/icheck.min.js"></script>
		
		<script type="text/javascript">
			$(function() {
				$('input').iCheck({
					checkboxClass: 'icheckbox_square-blue',
					radioClass: 'iradio_square-blue',
					increaseArea: '20%' /* optional */
				});
			});
		</script>
	</head>
	<body class="hold-transition login-page">
		<div class="login-box">
  			<div class="login-logo">
    			<b>XMALL</b>&nbsp;管理平台
  			</div>
  			<div class="login-box-body">
    			<p class="login-box-msg">请输入您的认证信息进行系统登录操作</p>
    			<form action="<%=request.getContextPath() %>/user/login" method="post">
      				<div class="form-group has-feedback">
        				<input type="text" id="username" name="username" class="form-control" placeholder="请输入您的登录名">
        				<span class="glyphicon glyphicon-user form-control-feedback"></span>
      				</div>
      				<div class="form-group has-feedback">
        				<input type="password" id="password" name="password" class="form-control" placeholder="请输入您的登录密码">
        				<span class="glyphicon glyphicon-lock form-control-feedback"></span>
      				</div>
   					<div class="row">
     					<div class="col-xs-8"></div>
     					<div class="col-xs-4">
       						<button type="submit" class="btn btn-primary btn-block btn-flat">登&nbsp;&nbsp;录</button>
     					</div>
   					</div>
    			</form>

    			<div class="social-auth-links text-center">
      				<p>-- OR --</p>
      				<a href="#" class="btn btn-block btn-social btn-facebook btn-flat">登录XMALL购物平台</a>
      				<a href="#" class="btn btn-block btn-social btn-google btn-flat">注册XMALL购物平台</a>
    			</div>

    			<a href="#">忘记密码</a><br>
    			<a href="#" class="text-center">联系管理员</a>
  			</div>
		</div>
	</body>
</html>