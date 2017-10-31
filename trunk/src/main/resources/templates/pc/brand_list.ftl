<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
		<meta name="viewport" content="width=device-width, initial-scale=1.0">	
		<title>联盟国际-品牌联盟</title>
		<link rel="stylesheet" href="assets/swiper/swiper.min.css" />
		<link rel="stylesheet" href="assets/Bootstrap/css/bootstrap.min.css" />
		<link rel="stylesheet" href="assets/Bootstrap/css/bootsnav.css" />
		<link rel="stylesheet" href="assets/Bootstrap/css/menu_group.css" />
		<link rel="stylesheet" href="css/master.css" />
		<link rel="stylesheet" href="css/model.css" />
		<style>
			.swiper-container-horizontal>.swiper-pagination-bullets, .swiper-pagination-custom, .swiper-pagination-fraction{ bottom:0px;}
			.p_color p{color:#3b3b3b;}
		</style>
	</head>
	<body>
		<div class="my-nav">
			<div class="container">
				<div class="row">
					<div class="col-md-6">
						<div class="nav_group_select">
							<span class="name floatLeft">选择场馆:</span>
							<label class="select_style floatLeft">
								<select id="pavilion" class="select">
									<option value="0">-- 请选择 --</option>
									<#list pavilions as p>
										<option value="${p.url}">${((p.alias)!'')+' '+p.name}</option>
									</#list>
								</select>
							</label>				
						</div>
					</div>
					<div class="col-md-6">
						<div class="nav_group_login">
							<#if user??>
			                    <p class="login_name floatRight">
			                    <#if (user.nick)?? && (user.nick) != ''>${user.nick}<#else>${user.phone}</#if>
			                    </p>
			                <#else>
								<button class="btn floatRight" id="login">快速登录</button>
							</#if>
							<i class="lcon floatRight"></i>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="menu box-shadow-3">
			<div class="menu_group_bg h84"></div>
			<div class="container menu_group">
				<div class="row">
					<!--log-->
					<div class="col-md-4">
						<a href="index.html"><img class="log" src="img/logo.png" /></a>
					</div>
					<!--菜单-->
					<div class="col-md-8">
						<nav class="navbar navbar-default navbar-mobile bootsnav">
							<div class="navbar-header">
								<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar-menu">
									<i class="fa fa-bars"></i>
								</button>
							</div>
							<div class="collapse navbar-collapse" id="navbar-menu">
								<ul class="nav navbar-nav" data-in="fadeInDown" data-out="fadeOutUp">
									<li><a href="index.html">首页</a></li>
									<li class="dropdown">
										<a href="about_us.html?type=0" class="dropdown-toggle" data-toggle="dropdown">关于我们</a>
										<ul class="dropdown-menu">
											<li><a href="about_us.html?type=0">集团简介</a></li>
											<li><a href="about_us.html?type=1">集团动态</a></li>
											<li><a href="about_us.html?type=2">企业文化</a></li>
											<li><a href="about_us.html?type=3">企业荣誉</a></li>
										</ul>										
									</li>
									<li class="dropdown">
										<a href="business_cooperation.html?type=0" class="dropdown-toggle" data-toggle="dropdown">商务合作</a>
										<ul class="dropdown-menu">
											<li><a href="business_cooperation.html?type=0">商务百问</a></li>
											<li><a href="business_cooperation.html?type=1">业态划分</a></li>
											<li><a href="business_cooperation.html?type=2">商务咨询</a></li>
										</ul>
									</li>
									<li class="dropdown">
										<a href="talent_recruitment.html?type=0" class="dropdown-toggle" data-toggle="dropdown">人才招聘</a>
										<ul class="dropdown-menu">
											<li><a href="talent_recruitment.html?type=0">招聘职位</a></li>
										</ul>
									</li>
									<li><a href="contact_us.html?type=0">联系我们</a></li>
								</ul>
							</div>
						</nav>
					</div>
				</div>
			</div>
		
			<div class="brand_bg bg-blur"></div>
			<div class="menu_group_bg border-bg"></div>
			<!--内容-->
			<div class="container brand_cont">
				<div class="brand_title">
					<div class="row text_style">
						<div class="col-md-1 col-sm-1 text_style_border">
							<i class="border"></i>
						</div>
						<div class="col-md-2 col-sm-4 text_style_img">
							<img src="img/text01.png" />
						</div>
						<div class="col-md-9 col-sm-7 text_style_border">
							<i class="border"></i>
						</div>
					</div>
				</div>
				
				<!--轮播组件-->
				<div class="swiper_group">
					<div class="swiper-container swiper2 brand_list_log new_swiper1" id="swiper">
						<div class="swiper-wrapper">
							<#list selectBrand as brand>
								<#if brand_index%12 == 0>
									<div class="swiper-slide">
									<div class="row border_list">
								</#if>
								<div class="col-md-2 col-sm-3">
									<a href="brand_list_details.html?brandNo=${brand.brandNo}">
										<img src="${imgPath+brand.logoUrl}" />
										<p>${brand.name}</p>
									</a>
								</div>
								<#if brand_has_next>
									<#if (brand_index+1)%12 == 0></div></div></#if>
								<#else>
									</div></div>
								</#if>
							</#list>
						</div>
						<div class="swiper-pagination swiper-pagination1"></div>
					</div>
					<div class="swiper-button-prev my_prev swiper-button-prev1"></div>
					<div class="swiper-button-next my_next swiper-button-next1"></div>
				</div>
				<!--end-->
			</div>
		</div>
		<div class="cont">
			<!--内容-->
			<div class="container brand_cont  brand_cont_other">
				<div class="brand_title">
					<div class="row text_style">
						<div class="col-md-1 col-sm-1 text_style_border">
							<i class="border"></i>
						</div>
						<div class="col-md-2 col-sm-4 text_style_img">
							<img src="img/text02.png" />
						</div>
						<div class="col-md-9 col-sm-7 text_style_border">
							<i class="border"></i>
						</div>
					</div>
				</div>
				<!--轮播组件-->
				<div class="swiper_group other">
					<div class="swiper-container swiper2 brand_list_log p_color new_swiper2" id="swiper1">
						<div class="swiper-wrapper">
							<#list otherBrand as brand>
								<#if brand_index%12 == 0>
									<div class="swiper-slide">
									<div class="row border_list">
								</#if>
								<div class="col-md-2 col-sm-3">
									<a href="brand_list_details.html?brandNo=${brand.brandNo}">
										<img src="${imgPath+brand.logoUrl}" />
										<p>${brand.name}</p>
									</a>
								</div>
								<#if brand_has_next>
									<#if (brand_index+1)%12 == 0></div></div></#if>
								<#else>
									</div></div>
								</#if>
							</#list>
						</div>
						<div class="swiper-pagination swiper-pagination2"></div>
					</div>
					<div class="swiper-button-prev my_prev swiper-button-prev2"></div>
					<div class="swiper-button-next my_next swiper-button-next2"></div>
				</div>
				<!--end-->
			</div>
		</div>
		<div class="foot">
			<iframe frameborder=0  width="100%" height="278" marginheight=0 marginwidth=0 scrolling=no src="footer.html"></iframe>
		</div>
		
		<!--登录-->
		<#include "login_div.ftl" />
		
		<script type="text/javascript" src="assets/jquery-1.11.0.min.js" ></script>
		<script type="text/javascript" src="assets/swiper/swiper.min.js" ></script>
		<script type="text/javascript" src="assets/Bootstrap/js/bootstrap.min.js" ></script>
		<script type="text/javascript" src="assets/Bootstrap/js/bootsnav.js" ></script>
		<script type="text/javascript" src="js/jquery.cookie.js"></script>
		<script type="text/javascript" src="js/layer.js"></script>
		<script type="text/javascript" src="js/login.js"></script>
		<script type="text/javascript" src="/js/statistical.js"></script>
		<script>
			var mySwiper1 = new Swiper('.new_swiper1', {
				pagination: '.swiper-pagination1',
				paginationClickable: true,
				//autoplay: 5000, //可选选项，自动滑动
				loop: true, //可选选项，开启循环
				prevButton: '.swiper-button-prev1',
				nextButton: '.swiper-button-next1'
			})
			var mySwiper2 = new Swiper('.new_swiper2', {
				pagination: '.swiper-pagination2',
				paginationClickable: true,
				//autoplay: 5000, //可选选项，自动滑动
				loop: true, //可选选项，开启循环
				prevButton: '.swiper-button-prev2',
				nextButton: '.swiper-button-next2'
			})
		</script>
	</body>
</html>
