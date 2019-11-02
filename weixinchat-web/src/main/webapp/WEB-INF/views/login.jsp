<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>账户登录</title>
<meta name="viewport"  content="width=device-width, minimum-scale=1.0, maximum-scale=2.0; charset=UTF-8">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/weui/css/weui.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/weui/css/jquery-weui.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/weui/css/style.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/weui/js/weui.js">
		<script type="text/javascript" src="js/jquery.js"></script>
		<script src="${pageContext.request.contextPath}/weui/js/jquery.min.js"></script>
	    <script src="${pageContext.request.contextPath}/weui/js/jquery-weui.min.js"></script>
<script type="text/javascript">

	function getcode(){
    	$("#code").attr("src","https://weui.shanliwawa.top/php/code.php?random="+Math.random());
	};

	function reg(){
		window.location="${pageContext.request.contextPath}/reg.html";
	}
	function checkSubmit(){
		 var account = $("#account").val(); 
		 var password= $("#password").val();
		
		$.ajax({
 			url:"${pageContext.request.contextPath}/toLogin",
 			type:"post",
 			
 			async:false,
 			data:{"account":account,"password":password},
 			dataType: "json",
 			success:function(respResult){
 				if(respResult.status==1){
 					$.toptip('登录成功', 2000, 'success');
 					//微信关闭窗口,回到聊天界面
 					WeixinJSBridge.call('closeWindow');
 				}else{
 					$.toptip(respResult.result, 2000, 'error');
 				}
 			},
 			error:function(respResult){
 				alert('失败');
 				window.close();
 			}
			});
	}
	
</script>
<style>
</style>
</head>
 <body>
	
    <div style="text-align: center; margin-top: 20px;">
   	 <div class=""><img class="tx"  style="width:150px;height:100px;"  src="${pageContext.request.contextPath}/image/logo.jpg" alt=""></div>
	 
    </div>
	
     
     		<!-- 包括很多常用的表单控件： -->
	<div class=""  style="margin-left:15px;margin-right: 15px;margin-top: 20px;">
  		<div class="weui-cells__title">微信登录</div>
  		<div class="weui-cell">
  			<div class="weui-cell__hd">
  				<label class="weui-label">openid：</label>
  			</div>
		    <div class="weui-cell__bd">
		      <input   id='account' class="weui-input" value=${openid} type="text" placeholder="账户名:E-mail或手机号"  readonly>
		  	</div> 
	    </div>

	    <div class="weui-cell ">
	    	<div class="weui-cell__hd">
  				<label class="weui-label">密码：</label>
  			</div>
		    <div class="weui-cell__bd">
		      <input id='password'  class="weui-input" type="password" placeholder="登录密码">
		    </div>
	    </div>
		
<!-- 		  <div class="weui-cell">
		    <div class="weui-cell__bd">
		      <input class="weui-input" type="text" placeholder="验证码">
		    </div>
		    <div class="weui-cell__ft">
		      <button class="weui-vcode-btn">获取验证码</button>
		    </div>
	   	 </div> -->
	   	 
	 <div class="weui-cell weui-cell_vcode">
        <div class="weui-cell__hd">
        	<label class="weui-label">验证码</label>
        </div>
        <div class="weui-cell__bd">
            <input class="weui-input" placeholder="请输入验证码" >
        </div>
        <div class="weui-cell__ft">
            <img id="code" class="weui-vcode-img" src="https://weui.shanliwawa.top/php/code.php" width="130" height="53" onclick="getcode();">
        </div>
   	 </div>
	   	 
	    <div class="weui-cell"></div>
	    <br>
	    
	    <div class="weui-flex weui-list-btm">
			  <div class="weui-flex__item"><a href="javascript:checkSubmit();" class="weui-btn weui-btn_primary">登录</a></div>
			  <div class="weui-flex__item"><a href="javascript:WeixinJSBridge.call('closeWindow');" class="weui-btn weui-btn_plain-primary">取消</a></div>
		</div>
	    
	  	<div class="weui-footer weui-footer_fixed-bottom">
            <p class="weui-footer__links">
                <a href="javascript:home();" class="weui-footer__link">test首页</a>
            </p>
            <p class="weui-footer__text">test  Copyright © 2019-2079 </p>
        </div>
    </div>
 </body>
</html>