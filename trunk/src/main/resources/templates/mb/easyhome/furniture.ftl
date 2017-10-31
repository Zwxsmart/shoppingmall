<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<title>家具店铺</title>
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">

		<link rel="stylesheet" href="assets/mui/css/mui.min.css">
		<link rel="stylesheet" href="css/master.css" />
		<!--自定义-->
		<link rel="stylesheet" href="css/mb_style.css" />
		<style>
            .furnituer_group .item .text{font-size: 12px;}
			.furnituer_group .item{position: relative; overflow: hidden;}
            .furnituer_group .item .title{overflow: hidden; white-space: nowrap; text-overflow: ellipsis; }
			.furnituer_group .item .item_img{position: relative; width:100%; padding-top:70%;}
            .furnituer_group .item .item_img img{position: absolute; top:0px; right:0px; left:0px; width:100%;}
			.furnituer_group .item .item_icon{position: absolute; border:1px solid #f0ae60; top:5px; right:5px; width: 24px; height: 24px; background: #ffffff; color:#444444; font-size: 12px; line-height: 24px; text-align:center; border-radius:50%;}
		</style>
	</head>

	<body>
		<div class="mui-content">
			<!--轮播图片-->
			<#include "banner_div.ftl" />
			<!--内容-->
			<div class="furnituer_group clear store_list">
				
			</div>
			<div><p class="end" align="center"></p></div>
		</div>

		<nav class="mui-bar mui-bar-tab box-shadow-1 my_bar_nav" >
			<a class="mui-tab-item" type="0">
				<span class="mui-icon iconfont icon-shouye"></span>
				<span class="mui-tab-label">首页</span>
			</a>
			<a class="mui-tab-item" type="1">
				<span class="mui-icon mui-icon-location"></span>
				<span class="mui-tab-label">商场地图</span>
			</a>
			<a class="mui-tab-item" type="2">
				<span class="mui-icon iconfont icon-lianxi"></span>
				<span class="mui-tab-label">联系我们</span>
			</a>	
		</nav>
		<script type="text/javascript" src="assets/mui/js/mui.js"></script>
		<script type="text/javascript" src="assets/jquery-1.10.2.js"></script>
		<script type="text/javascript" src="js/jquery.cookie.js"></script>
		<script type="text/javascript" src="js/nav.js"></script>
		<script type="text/javascript" src="js/scroll.js"></script>
		<script type="text/javascript" src="js/store.js"></script>
		<script type="text/javascript" src="/js/statistical.js"></script>
		<script type="text/javascript">
			mui.init({
				swipeBack: true //启用右滑关闭功能
			});
		</script>

	</body>
</html>