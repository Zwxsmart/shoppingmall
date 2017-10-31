<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<title>品牌联盟</title>
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">

		<link rel="stylesheet" href="assets/mui/css/mui.min.css">
		<link rel="stylesheet" href="css/master.css" />
		<link rel="stylesheet" href="assets/swiper/css/swiper.min.css" />
		<!--自定义-->
		<link rel="stylesheet" href="css/mb_style.css" />
		<style>
			.swiper-container-horizontal>.swiper-pagination-bullets, .swiper-pagination-custom, .swiper-pagination-fraction{bottom:10px;}
		</style>
	</head>

	<body>
		<div class="mui-content">
			<div class="personal_group_log">
				<div class="brand_bg bg-blur"></div>
				<div class="g_shade_bg" ></div>
				<div class="brand_group">
					<div class="g-h20"></div>
					<!--标题-->
					<div class="title">
						<i class="my_icon"></i>
						<img class="img" src="img/brand_0.png" />
						<i class="my_icon"></i>
					</div>
					<!-- Swiper -->
					<div class="swiper-container">
						<div class="swiper-wrapper">
							<#list selectBrand as brand>
								<#if brand_index%3 == 0>
									<div class="swiper-slide swiper_cont_list">
								</#if>
								<a class="item" href="brand_details.html?brandNo=${brand.brandNo}">
								<div class="img_box">
									<img src="${imgPath+brand.logoUrl}" />
								</div>
									<p>${brand.name}</p>
								</a>
								<#if brand_has_next>
									<#if (brand_index+1)%3 == 0></div></#if>
								<#else>
									<#if (otherBrand?size)%3 != 0>
										<#list 1..(3-((selectBrand?size)%3)) as i>
										    <a class="item"><div class="img_box"></div></a>
										</#list>
									</#if>
									</div>
								</#if>
							</#list>
						</div>
						<!-- Add Pagination -->
						<div class="swiper-pagination"></div>
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
							<#list otherBrand as brand>
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
									<#if (otherBrand?size)%3 != 0>
										<#list 1..(3-((otherBrand?size)%3)) as i>
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
		<nav class="mui-bar mui-bar-tab box-shadow-1 my_bar_nav" >
			<a class="mui-tab-item " type="0">
				<span class="mui-icon iconfont icon-shouye"></span>
				<span class="mui-tab-label">首页</span>
			</a>
			<a class="mui-tab-item " type="1">
				<span class="mui-icon iconfont icon-guanyuwomen"></span>
				<span class="mui-tab-label">关于我们</span>
			</a>
			<a class="mui-tab-item " type="2">
				<span class="mui-icon iconfont icon-lianxi"></span>
				<span class="mui-tab-label">联系我们</span>
			</a>
			<a class="mui-tab-item" type="3">
				<span class="mui-icon iconfont icon-rgzx"></span>
				<span class="mui-tab-label">个人中心</span>
			</a>
		</nav>
		<script type="text/javascript" src="assets/jquery-1.10.2.js"></script>
		<script type="text/javascript" src="assets/mui/js/mui.js"></script>
		<script type="text/javascript" src="assets/swiper/js/swiper.min.js"></script>
		<script type="text/javascript" src="js/nav.js"></script>
		<script type="text/javascript" src="js/jquery.cookie.js"></script>
		<script type="text/javascript" src="/js/statistical.js"></script>
		<script type="text/javascript">
			var swiper = new Swiper('.swiper-container', {
				pagination: '.swiper-pagination',
				paginationClickable: true
			});
		
			
		</script>

	</body>

</html>