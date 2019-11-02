<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html class="ui-page-login">
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<title>注册</title>
		<link href="${pageContext.request.contextPath}/mui/css/mui.min.css" rel="stylesheet" />
		<link href="${pageContext.request.contextPath}/mui/css/style.css" rel="stylesheet" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/weui/css/weui.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/weui/css/jquery-weui.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/weui/css/style.css">
	<%-- 	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script> --%>
		<script src="${pageContext.request.contextPath}/mui/js/mui.min.js"></script>
		<script src="${pageContext.request.contextPath}/mui/js/app.js"></script>
		<style>
				h5 {
					margin: 5px 7px;
				}
		</style>
		
		<script type="text/javascript">
			function reg(){
				var name = mui("#name")[0].value;//jquery 写法$("#name").val(); 
				var password =mui("#password")[0].value;
				var password_confirm =mui("#password_confirm")[0].value;
				if (password != password_confirm) {
					mui.alert('两次输入密码不一致', function() {
					});
					return;
				}

				mui.ajax('${pageContext.request.contextPath}/toReg',{
					data:{
						name:name,
						password:password
					},
					dataType:'json',//服务器返回json格式数据
					type:'GET',//HTTP请求类型
					timeout:10000,//超时时间设置为10秒；
					//headers:{'Content-Type':'application/json'},
					success:function(data){
						if(data.status==1){
							mui.alert(data.result, function() {
							});
							window.location="${pageContext.request.contextPath}/login?openid="+name;
						}else if(data.status==2){
							mui.alert(data.result, function() {
							});
							window.location="${pageContext.request.contextPath}/login?openid="+name;
						}else{
							mui.alert("注册失败", function() {
							});
						}
						
						//服务器返回响应，根据响应结果，分析是否登录成功；
					},
					error:function(xhr,type,errorThrown){
						alert("注册失败");
						//异常处理；
						console.log(type);
					}
				});
			}
		</script>
	</head>
	<body>
		<header class="mui-bar mui-bar-nav">
			<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
			<h1 class="mui-title">注册</h1>
		</header>
		<div class="mui-content">
			<form class="mui-input-group">
				<div class="mui-input-row">
					<label>账号</label>
					<!-- value=${openid} -->  
					<input id='name' type="text" 
						value=${openid} class="mui-input-clear mui-input" placeholder="请输入账号" readonly>
				</div>
				<div class="mui-input-row">
					<label>密码</label>
					<input id='password' type="password"   class="mui-input-password" placeholder="请输入密码">
				</div>
				<div class="mui-input-row">
					<label>确认</label>
					<input id='password_confirm'   type="password" class="mui-input-password" placeholder="请确认密码">
				</div>
				<!-- <div class="mui-input-row">
					<label>邮箱</label>
					<input id='email' type="email" class="mui-input-clear mui-input" placeholder="请输入邮箱">
				</div> -->
			</form>
			<div class="mui-content-padded">
				<button id='reg' class="mui-btn mui-btn-block mui-btn-primary" onclick="reg()">注册</button>
			</div>
		</div>
	
		<div class="weui-msg__extra-area">
		    <div class="weui-footer">
		      <p class="weui-footer__text">test Copyright ©2019-2079</p>
		    </div>
	    </div>
	</body>

</html>