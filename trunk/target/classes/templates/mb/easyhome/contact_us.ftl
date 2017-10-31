<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<title>联系我们</title>
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">

		<link rel="stylesheet" href="assets/mui/css/mui.min.css">
		<link rel="stylesheet" href="css/master.css" />
		<!--自定义-->
		<link rel="stylesheet" href="css/mb_style.css" />
	
	</head>

	<body>
		<div class="mui-content">
			<!--轮播图片-->
			<#include "banner_div.ftl" />
			<!--内容-->
			<div class="contact_group">
				${contactUs!''}
			</div>
				
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
			<a class="mui-tab-item mui-active" type="2">
				<span class="mui-icon iconfont icon-lianxi"></span>
				<span class="mui-tab-label">联系我们</span>
			</a>	
		</nav>
		<script type="text/javascript" src="assets/mui/js/mui.js"></script>
		<script type="text/javascript" src="assets/jquery-1.10.2.js"></script>
		<script type="text/javascript" src="js/jquery.cookie.js"></script>
		<script type="text/javascript" src="js/nav.js"></script>
		<script type="text/javascript" src="/js/statistical.js"></script>
		<script type="text/javascript">
			mui.init({
				swipeBack: true //启用右滑关闭功能
			});
			
		</script>

	</body>

</html>