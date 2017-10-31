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
		<style>
            .sample_rooms_title_group{margin-bottom: 0px;}
		</style>
	</head>

	<body>
		<div class="mui-content">			
			<!--轮播图片-->
			<#include "banner_div.ftl" />
			<div class="sample_rooms_title_group">
				<h2 class="title caret">${(model.title)!''}	
					<#if (model.types)??>
						<#list model.types as type>
							<p>${type.caseType}</p>
						</#list>
					</#if>
				</h2>
			</div>
			<!--内容-->
			<div class="furnituer_group clear sample_rooms_details_group">
				<#if (model.goodsList)??>
					<#list model.goodsList as goods>
						<a class="item">
							<#--<img src="${imgPath+goods.smallUrl}" />-->
                            <div class="model_store_img">
								<#if goods.isCrosswise == true>
                                    <img class="store_img" src="${imgPath+goods.smallUrl}" style="top:24%;">
								<#else>
                                    <img class="store_img" src="${imgPath+goods.smallUrl}">
								</#if>

                            </div>
							<h2 class="title">${goods.goodsName}</h2>
							<#if (goods.price)??>
							<p class="text">市场价：￥<span>${goods.price}</span></p>
							</#if>
						</a>
					</#list>
				</#if>
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
		<script type="text/javascript" src="js/nav.js"></script>
		<script type="text/javascript" src="/js/statistical.js"></script>
		<script type="text/javascript">
			mui.init({
				swipeBack: true //启用右滑关闭功能
			});
		</script>
	</body>
</html>