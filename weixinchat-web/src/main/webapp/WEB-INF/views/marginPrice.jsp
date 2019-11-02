<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%><!doctype html><html><head>    <meta charset="UTF-8">  	<meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no">    <title>外汇交易中心价格</title>      <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery-easyui-1.7.0/themes/metro/easyui.css">      <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery-easyui-1.7.0/hemes/mobile.css">      <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/query-easyui-1.7.0/themes/icon.css">      <script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.7.0/jquery.min.js"></script>      <script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.7.0/jquery.easyui.min.js"></script>     <script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.7.0/jquery.easyui.mobile.js"></script> 	<meta content="text/html; charset=utf-8" http-equiv="Content-Type" /></head><body>    <table id="dg" data-options="header:'#hh',singleSelect:true,border:false,fit:true,fitColumns:true,scrollbarSize:0">          <thead>              <tr>                  <th data-options="field:'ccyPair',width:80">货币对</th>                  <th data-options="field:'askPrc',width:100">买价</th>                  <th data-options="field:'bidPrc',width:80,align:'right'">卖价</th>                  <th data-options="field:'midprice',width:80,align:'right'">中间价</th>              </tr>        </thead>      </table>    <div id="hh">    	<div class="m-toolbar">    		<div class="m-title">外汇交易中心价格</div>    	</div>    </div>	<script>		var data = 	[			{"productid":"FI-SW-01","productname":"Koi","unitcost":10.00,"status":"P","listprice":36.50,"attr1":"Large","itemid":"EST-1"},			{"productid":"AV-CB-01","productname":"Amazon Parrot","unitcost":92.00,"status":"P","listprice":63.50,"attr1":"Adult Male","itemid":"EST-18"}		];				var url="http://1j6712x825.iask.in/weixinchat-web/getMarginPrice.do";		var marprice="";		$(function(){			getMarginPriceFromApp();			$('#dg').datagrid({				data: marprice			});					});					function getMarginPriceFromApp(){			  $.ajax({                url: url,                type: 'post',				dataType:'json',				async: false,                success: function (msg) {				  marprice=msg;                },				error:function(XMLHttpRequest, textStatus, errorThrown) {					   alert(textStatus); 				 }});						};</script> </body>	</html>