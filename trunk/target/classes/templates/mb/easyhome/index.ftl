<!DOCTYPE html>
<html lang="en">

	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<script type="text/javascript" src="js/flexible.js"></script>
		<link rel="stylesheet" href="css/master.css" />
		<link rel="stylesheet" href="assets/mui/css/mui.css">
		<link rel="stylesheet" href="assets/swiper/css/swiper.min.css" />
		<!--自定义-->
		<link rel="stylesheet" href="css/mb_style.css" />
		
		<title>居然之家</title>
		<style>
			.swiper-container-horizontal>.swiper-pagination-bullets, .swiper-pagination-custom, .swiper-pagination-fraction{bottom:10px;}
		</style>
	</head>

	<body>
		<div class="mui-content">
		
			<!--导航栏-->
		    <div class="nav_group g-bg1">
		    	
		     <div class="select">
		     	<span class="select_span" id="select">-- 请选择 --</span>		     
		     	<ul class="select_ul g_dp_n" id="selectCont">
		     		<li><a url="/index.html">返回主页</a></li>
		     		<#list pavilions as p>
			     		<li><a url="${p.url}">${((p.alias)!'')+' '+p.name}</a></li>
					</#list>
		     	</ul>
		     </div> 
		     <div class="login">
		     	<#if user??>
			     	<span class="my_login g_dp_n">
			     	<#if (user.nick)?? && (user.nick) != ''>${user.nick}<#else>${user.phone}</#if>
			     	</span>
                <#else>
			     	<button id="login" type="button" class="mui-btn mui-icon iconfont icon-rgzx">快速登录</button>
				</#if>
		     </div>
		    </div>
		    <!--轮播-->
		  	<div id="slider" class="mui-slider" >
		  		<div class="mui-slider-group mui-slider-loop">
		  	  		<#if banners??>
					    <!-- 额外增加的一个节点(循环轮播：第一个节点是最后一张轮播) -->
					    <#list banners as b>
					    	<#if !b_has_next>
							<div class="mui-slider-item mui-slider-item-duplicate">
					      		<a href="<#if (b.gotoUrl)?? && (b.gotoUrl) != ''>${b.gotoUrl}<#else>index.html</#if>">
					      			<img src="${imgPath+b.picUrl}">
					      		</a>
					    	</div>
							</#if>
						</#list>
						<#list banners as b>
							<div class="mui-slider-item">
								<a href="<#if (b.gotoUrl)?? && (b.gotoUrl) != ''>${b.gotoUrl}<#else>index.html</#if>">
									<img src="${imgPath+b.picUrl}">
								</a>
							</div>
						</#list>
					    <!-- 额外增加的一个节点(循环轮播：最后一个节点是第一张轮播) -->
					    <#list banners as b>
					    	<#if b_index == 0>
							<div class="mui-slider-item mui-slider-item-duplicate">
					      		<a href="<#if (b.gotoUrl)?? && (b.gotoUrl) != ''>${b.gotoUrl}<#else>index.html</#if>">
					      			<img src="${imgPath+b.picUrl}">
					      		</a>
					    	</div>
							</#if>
						</#list>
					</#if>
		  	  	</div>
				<div class="address">
					<div class="g_shade_bg"></div>
					<div class="address_cont">
						<p>地址：安徽省蚌埠市高新区迎宾大道1511号</p>
						<p>营业时间：9:30-18:00 (周一至周五)   9:30-18:30 (周末及节日)</p>
					</div>		  	  	
				</div>
		  	</div>
			<!--内容-->
			<div class="cont_group">
				<div class="item">
					<div class="g_shade_bg g_bor_rad"></div>
					<img class="g_bor_rad" src="img/bg_01.png">
					<div class="item_text">
						<a href="sample_rooms.html">
						<h1>样板间</h1>
						<h3>SAMPLE ROOMS</h3>
						</a>
					</div>
				</div>
				<div class="item">
					<div class="g_shade_bg g_bor_rad"></div>
					<img class="g_bor_rad" src="img/bg_02.png">
					<div class="item_text">
						<a href="furniture.html">
						<h1>家具</h1>
						<h3>FURNITURE</h3>
						</a>
					</div>
				</div>
				<div class="item">
					<div class="g_shade_bg g_bor_rad"></div>
					<img class="g_bor_rad" src="img/bg_03.png">
					<div class="item_text">
						<a href="hot_activity.html">
							<h1>热门活动</h1>
							<h3>HOT ACTIVITIES</h3>
						</a>						
					</div>
				</div>
				<div class="item">
					<div class="g_shade_bg g_bor_rad"></div>
					<img class="g_bor_rad" src="img/bg_04.png">
					<div class="item_text add_code">
						<a href="">
						<p style="margin-top:18%;">扫码关注</p>
						<p>最新活动</p>
						<img class="code" src="img/code.png" />
						</a>
					</div>
				</div>				
			</div>
			
			<div class="brand_other">
				<div class="brand_group">
					<div class="g-h20"></div>
					<!--标题-->
					<div class="title">
						<i class="my_icon"></i>
						<img class="img" src="img/brand_1.png" />
						<i class="my_icon"></i>
					</div>
					<div class="g-h20"></div>
					<!-- Swiper -->
					<div class="swiper-container">
						<div class="swiper-wrapper">
						<#list selectBrand as brand>
							<#if brand_index%6 == 0>
								<div class="swiper-slide">
							</#if>
							<#if brand_index%3 == 0>
								<div class="swiper_cont_list">
							</#if>
							<a class="item" href="brand_details.html?brandNo=${brand.brandNo}">
							<div class="img_box">
								<img src="${imgPath+brand.logoUrl}" />
							</div>
								<p>${brand.name}</p>
							</a>
							<#if brand_has_next>
								<#if (brand_index+1)%3 == 0></div></#if>
								<#if (brand_index+1)%6 == 0><div class="g-h20"></div></div></#if>
							<#else>
								<#if (selectBrand?size)%3 != 0>
									<#list 1..(3-((selectBrand?size)%3)) as i>
									    <a class="item"><div class="img_box"></div></a>
									</#list>
								</#if>
								</div><div class="g-h20"></div></div>
							</#if>
						</#list>
						</div>
						<!-- Add Pagination -->
						<div class="swiper-pagination"></div>
					</div>
				</div>
			</div>
		</div>
		<nav class="mui-bar mui-bar-tab box-shadow-1 my_bar_nav">
			<a class="mui-tab-item mui-active" type="0">
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
		<script type="text/javascript" src="assets/jquery-1.10.2.js"></script>
		<script type="text/javascript" src="assets/mui/js/mui.js"></script>
		<script type="text/javascript" src="assets/swiper/js/swiper.min.js"></script>
		<script type="text/javascript" src="js/nav.js"></script>
		<script type="text/javascript" src="js/jquery.cookie.js"></script>
		<script type="text/javascript" src="/js/statistical.js"></script>
		<script>
			mui.init({
				swipeBack:true //启用右滑关闭功能
			});
			var swiper = new Swiper('.swiper-container', {
				pagination: '.swiper-pagination',
				paginationClickable: true
			});
		</script>
	</body>

</html>