<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<title>火热销售</title>
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">

		<link rel="stylesheet" href="assets/mui/css/mui.min.css">
		<link rel="stylesheet" href="css/master.css" />
		<!--自定义-->
		<link rel="stylesheet" href="css/mb_style.css" />
		<style>img {width: 100%}</style>
	</head>

	<body>
		<div class="mui-content">
			<!--轮播图片-->
			<#include "banner_div.ftl" />
			
			<!--内容-->
			<div>
				<div class="enterpise_culture_group">
					${content}
					<!-- <h1 class="title"><i class="g-vertical"></i>优惠政策</h1>
					<div class="g-h20"></div>	
					<img class="business_cont" src="img/demo_06.png">			 -->			
				</div>
			</div>
		</div>
		<nav class="mui-bar mui-bar-tab box-shadow-1 my_bar_nav" >
			<a class="mui-tab-item " type="0">
				<span class="mui-icon iconfont icon-shouye"></span>
				<span class="mui-tab-label">首页</span>
			</a>
			<a class="mui-tab-item" type="1">
				<span class="mui-icon iconfont icon-guanyuwomen"></span>
				<span class="mui-tab-label">关于我们</span>
			</a>
			<a class="mui-tab-item" type="2">
				<span class="mui-icon iconfont icon-lianxi"></span>
				<span class="mui-tab-label">联系我们</span>
			</a>
			<a class="mui-tab-item" type="3">
				<span class="mui-icon iconfont icon-rgzx"></span>
				<span class="mui-tab-label">个人中心</span>
			</a>
		</nav>
		<script type="text/javascript" src="assets/mui/js/mui.js"></script>
		<script type="text/javascript" src="assets/jquery-1.10.2.js"></script>
		<script type="text/javascript" src="js/nav.js"></script>
		<script type="text/javascript" src="js/jquery.cookie.js"></script>
		<script type="text/javascript" src="/js/statistical.js"></script>
		<script type="text/javascript">
			mui.init({
				swipeBack: true //启用右滑关闭功能
			});
		</script>

	</body>

</html>