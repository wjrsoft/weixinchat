<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>白银价格闹钟设定</title>
<meta name="viewport"  content="width=device-width, minimum-scale=1.0, maximum-scale=2.0; charset=UTF-8">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/weui/css/weui.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/weui/css/jquery-weui.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/weui/css/style.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/weui/js/weui.js">
		<script type="text/javascript" src="js/jquery.js"></script>
		<script src="${pageContext.request.contextPath}/weui/js/jquery.min.js"></script>
	    <script src="${pageContext.request.contextPath}/weui/js/jquery-weui.min.js"></script>
<script type="text/javascript">

	function checkSubmit(){
		 var openid = $("#openid").val(); 
		 var sell_Price= $("#sell_Price").val();
		 var buy_price= $("#buy_price").val();
		 var buy= $("#buy").val();
		 var sell= $("#sell").val();
		 
		 
		$.ajax({
 			url:"${pageContext.request.contextPath}/SettingAGTDPrice.do",
 			type:"post",
 			
 			async:false,
 			data:{"openid":openid,"buy":buy,"sell":sell,"sell_Price":sell_Price ,"buy_price":buy_price},
 			dataType: "json",
 			success:function(respResult){
 				if(respResult.status==1){
 					$.toptip(respResult.result, 2000, 'success');
 					//微信关闭窗口,回到聊天界面
 					setTimeout(function () {
				        	WeixinJSBridge.call('closeWindow');
				    }, 2500);
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
  		<div class="weui-cells__title">白银价格设定:闹钟有效时间为7天，且到价提醒后，需要重新设置最新价格闹钟。。。。。</div>
  		<div class="weui-cell">
  			<div class="weui-cell__hd">
  				<label class="weui-label">openid：</label>
  			</div>
		    <div class="weui-cell__bd">
		      <input   id='openid' class="weui-input" value=${openid} type="text" placeholder="请求输入数字"  readonly>
		  	</div> 
	    </div>
  		<div class="weui-cell">
  			<div class="weui-cell__hd">
  				<label class="weui-label">参考买多价：</label>
  			</div>
		    <div class="weui-cell__bd">
		      <input   id='sell_Price' class="weui-input" value=${sell_price} type="text" placeholder="请求输入数字"  readonly>
		  	</div> 
	    </div>
	    
	    <div class="weui-cell">
  			<div class="weui-cell__hd">
  				<label class="weui-label">参考卖空价：</label>
  			</div>
		    <div class="weui-cell__bd">
		      <input   id='buy_price' class="weui-input" value=${buy_price} type="text" placeholder="请求输入数字"  readonly>
		  	</div> 
	    </div>
	    
	   <div class="weui-cell">
  			<div class="weui-cell__hd">
  				<label class="weui-label">设定买多，看跌：</label>
  			</div>
		    <div class="weui-cell__bd">
		      <input   id='buy' class="weui-input"  type="text" value=${sell_price} placeholder="价格涨破，消息推送"  >
		  	</div> 
	    </div>
	    
	      <div class="weui-cell">
  			<div class="weui-cell__hd">
  				<label class="weui-label">设定卖空，看涨：</label>
  			</div>
		    <div class="weui-cell__bd">
		      <input   id='sell' class="weui-input"  type="text" value=${buy_price} placeholder="价格跌破，消息推送"  >
		  	</div> 
	    </div>
	    
	    <div class="weui-cell"></div>
	    <br>
	    
	    <div class="weui-flex weui-list-btm">
			  <div class="weui-flex__item"><a href="javascript:checkSubmit();" class="weui-btn weui-btn_primary">确定</a></div>
			  <div class="weui-flex__item"><a href="javascript:WeixinJSBridge.call('closeWindow');" class="weui-btn weui-btn_plain-primary">取消</a></div>
		</div>
	    
	  	<!-- <div class="weui-footer weui-footer_fixed-bottom">
            <p class="weui-footer__links">
                <a href="javascript:home();" class="weui-footer__link">test首页</a>
            </p>
            <p class="weui-footer__text">test  Copyright © 2019-2079 </p>
        </div> -->
    </div>
 </body>
</html>