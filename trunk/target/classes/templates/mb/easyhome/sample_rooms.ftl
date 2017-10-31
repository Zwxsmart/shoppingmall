<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<title>样板间</title>
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
			<div id="segmentedControl" class="mui-segmented-control contact_segented_control box-shadow-1">
				<a class="mui-control-item" href="#item1">风格</a>
				<a class="mui-control-item" href="#item2">空间</a>
				<a class="mui-control-item" href="#item3">局部</a>
			</div>
			<div class="nav_cent mui-control-content" id="item1">
				<div class="g_shade_bg"></div>
				<ul class="nav_cent_item style">
					<li><a class="on">全部</a></li>
					<#if (types.style)??>
						<#list types.style as type>
							<li><a id="${type.caseTypeNo}">${type.caseType}</a></li>
						</#list>
					</#if>
				</ul>
			</div>
			<div class="nav_cent mui-control-content" id="item2">
				<div class="g_shade_bg"></div>
				<ul class="nav_cent_item area">
					<li><a class="on">全部</a></li>
					<#if (types.area)??>
						<#list types.area as type>
							<li><a id="${type.caseTypeNo}">${type.caseType}</a></li>
						</#list>
					</#if>
				</ul>
			</div>
			<div class="nav_cent mui-control-content" id="item3">
				<div class="g_shade_bg"></div>
				<ul class="nav_cent_item part">
					<li><a class="on">全部</a></li>
					<#if (types.part)??>
						<#list types.part as type>
							<li><a id="${type.caseTypeNo}">${type.caseType}</a></li>
						</#list>
					</#if>
				</ul>
			</div>
			<!--内容-->
			<div>
				<div class="furnituer_group clear sample_rooms_group">
				
				</div>
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
			<a class="mui-tab-item" type="2">
				<span class="mui-icon iconfont icon-lianxi"></span>
				<span class="mui-tab-label">联系我们</span>
			</a>	
		</nav>
		<script type="text/javascript" src="assets/mui/js/mui.js"></script>
		<script type="text/javascript" src="assets/jquery-1.10.2.js"></script>
		<script type="text/javascript" src="js/jquery.cookie.js"></script>
		<script type="text/javascript" src="js/scroll.js"></script>
		<script type="text/javascript" src="js/model.js"></script>
		<script type="text/javascript" src="js/nav.js"></script>
		<script type="text/javascript" src="/js/statistical.js"></script>
		<script type="text/javascript">
			mui.init({
				swipeBack: true //启用右滑关闭功能
			});
		</script>
	</body>
</html>
