<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<title>关于我们</title>
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
			<div>
				<div id="segmentedControl" class="mui-segmented-control contact_segented_control box-shadow-1">
					<a class="mui-control-item mui-active" href="#item1">集团介绍</a>
					<a class="mui-control-item" href="#item2">集团动态</a>
					<a class="mui-control-item" href="#item3">企业文化</a>
					<a class="mui-control-item" href="#item4">企业荣誉</a>
				</div>
				<div id="item1" class="mui-control-content mui-active">
					<div class="about_details_group">
						<div class="cont">
							${groupProfile}
						</div>
					</div>
				</div>
				<div id="item2" class="mui-control-content">
					<ul class="mui-table-view g-before dynamics">
						
					</ul>
				</div>
				
				<div id="item3" class="mui-control-content">
					<div class="enterpise_culture_group">
						${corporateCulture}
					</div>
				</div>
				<div id="item4" class="mui-control-content">
					<div class="honor_group clear">
						<#list companyHonor as ch>
							<a>
								<img src="${imgPath + ch.picTitle}" />
								<p>${ch.title}</p>
							</a>
						</#list>
					</div>
				</div>
			</div>
		</div>
		<nav class="mui-bar mui-bar-tab box-shadow-1 my_bar_nav" >
			<a class="mui-tab-item " type="0">
				<span class="mui-icon iconfont icon-shouye"></span>
				<span class="mui-tab-label">首页</span>
			</a>
			<a class="mui-tab-item mui-active" type="1">
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
		<script type="text/javascript" src="js/jquery.cookie.js"></script>
		<script type="text/javascript" src="js/nav.js"></script>
		<script type="text/javascript" src="js/scroll.js"></script>
		<script type="text/javascript" src="js/group_dynamics.js"></script>
		<script type="text/javascript" src="/js/statistical.js"></script>
		<script type="text/javascript">
			mui.init({
				swipeBack: true //启用右滑关闭功能
			});
		</script>

	</body>

</html>