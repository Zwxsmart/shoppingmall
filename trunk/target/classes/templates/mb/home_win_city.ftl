<!DOCTYPE html>
<html lang="en">

	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<script type="text/javascript" src="js/flexible.js"></script>
		<link rel="stylesheet" href="css/master.css" />
		<link rel="stylesheet" href="assets/mui/css/mui.css" />
		<link rel="stylesheet" href="assets/swiper/css/swiper.min.css" />
		<!--自定义-->
		<link rel="stylesheet" href="css/mb_style.css" />
		<title>家博城</title>
		<style>
			html,
			body {
				position: relative;
				height: 100%;
			}
			
			body {
				background: #eee;
				font-family: Helvetica Neue, Helvetica, Arial, sans-serif;
				font-size: 14px;
				color: #000;
				margin: 0;
				padding: 0;
			}
			
		</style>
	</head>

	<body>
		<!-- Swiper -->
		<div class="swiper-container">
			<div class="swiper-wrapper">
				<#if modules??>
					<#list modules as m>
						<div class="swiper-slide">
							<img src="${imgPath+m.picUrl}" />
							<div class="swiper_cont">
								<div class="g_shade_bg"></div>
								<div class="swiper_cont_text">
									<p>${m.title}</p>
								</div>
							</div>
						</div>
					</#list>
				</#if>
			</div>
			<!-- Add Pagination -->
			<div class="swiper-pagination"></div>
		</div>
		<nav class="mui-bar mui-bar-tab box-shadow-1 my_bar_nav" >
			<a class="mui-tab-item" type="0">
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
		<script type="text/javascript" src="assets/swiper/js/swiper.min.js"></script>
		<script type="text/javascript" src="assets/jquery-1.10.2.js"></script>
		<script type="text/javascript" src="js/nav.js"></script>
		<script type="text/javascript" src="js/jquery.cookie.js"></script>
		<script type="text/javascript" src="/js/statistical.js"></script>
		<script>
			var swiper = new Swiper('.swiper-container', {
				pagination: '.swiper-pagination',
				paginationClickable: true
			});
		</script>
	</body>

</html>