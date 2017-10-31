<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>居然之家</title>
		<link rel="stylesheet" href="assets/swiper/swiper.min.css" />
		<link rel="stylesheet" href="assets/Bootstrap/css/bootstrap.min.css" />
		<link rel="stylesheet" href="assets/Bootstrap/css/font-awesome.min.css" />
		<link rel="stylesheet" href="assets/Bootstrap/css/bootsnav.css" />
		<link rel="stylesheet" href="assets/Bootstrap/css/menu_group.css" />
		<link rel="stylesheet" href="css/master.css" />
		<link rel="stylesheet" href="css/model.css" />
	</head>

	<body>
		<!-- 页眉 -->
		<#include "head.ftl" />
		
		<div class="foot_bottom  address_text">
			<div class="container">
				<div class="row">
					<div class="col-md-4">
						<p>地址：安徽省蚌埠市高新区迎宾大道1511号</p>
					</div>

					<div class="col-md-6">
						<p>营业时间：9:30-18:00 (周一至周五) 9:30-18:30 (周末及节日)</p>
					</div>

				</div>
			</div>
		</div>
		<div class="cont">
			<div class="container cont_group">
				<div class="row">
					<div class="col-md-4">
						<a href="model_list.html" class="rooms  bg_01 transition g-transition">
							<!--<img src="img/cont_01.png" />-->
							<i class="icon_title title_02"></i>
						</a>
					</div>
					<div class="col-md-8">
						<div class="row cont_h140">
							<div class="col-md-12">
								<a href="store_list.html" class="rooms bg_02 transition g-transition">
									<!--<img src="img/cont_02.png" />-->
									<i class="icon_title title_01"></i>
								</a>
							</div>
						</div>
						<div class="row cont_h140 pad10">
							<div class="col-md-4">
								<a class="rooms">
									<img src="img/cont_03.png" />
									<div class="g_bg"></div>
									<div class="cont_code">
										<div class="row">
											<div class="col-md-6">
												<p class="g-m-t20">扫码关注</p>
												<p><strong>最新活动</strong></p>
											</div>
											<div class="col-md-6">
												<img src="img/code.png">
											</div>
										</div>
									</div>
								</a>
							</div>
							<div class="col-md-8">
								<a href="hot_activity.html" class="rooms transition bg_04 g-transition">
									<!--<img src="img/cont_04.png" />-->
									<i class="icon_title title_03"></i>
								</a>
							</div>
						</div>
					</div>
				</div>
			</div>
			
			<div class="container cont_group">
				<!--标题-->
				<div class="brand_title">
					<div class="row text_style">
						<div class="col-md-5 col-sm-4 text_style_border">
							<i class="border"></i>
						</div>
						<div class="col-md-2 col-sm-4 text_style_img">
							<img src="img/title_1.png" />
						</div>
						<div class="col-md-5 col-sm-4 text_style_border">
							<i class="border"></i>
						</div>
					</div>
				</div>
				<div class="g-m-t20"></div>
				<!--轮播组件-->
				<div class="swiper_group">
					<div class="swiper-container brand_list_log swiper2">
						<div class="swiper-wrapper">
							<#list selectBrand as brand>
								<#if brand_index%12 == 0>
									<div class="swiper-slide">
									<div class="row border_list">
								</#if>
								<div class="col-md-2 col-sm-3">
									<a href="brand_details.html?brandNo=${brand.brandNo}">
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
						<div class="swiper-pagination"></div>
					</div>
					<div class="swiper-button-prev "></div>
					<div class="swiper-button-next "></div>
				</div>
			</div>

		</div>
		<div class="foot">
			<iframe frameborder=0 width="100%" height="58" marginheight=0 marginwidth=0 scrolling=no src="footer.html"></iframe>
		</div>
		
		<!--登录-->
		<#include "login_div.ftl" />
		
		<script type="text/javascript" src="assets/jquery-1.11.0.min.js"></script>
		<script type="text/javascript" src="assets/swiper/swiper.min.js"></script>
		<script type="text/javascript" src="assets/Bootstrap/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="assets/Bootstrap/js/bootsnav.js"></script>
		<script type="text/javascript" src="js/jquery.cookie.js"></script>
		<script type="text/javascript" src="js/layer.js"></script>
		<script type="text/javascript" src="/aw/assets/qrCode/jquery.qrcode.js"></script>
		<script type="text/javascript" src="/js/qrcode.js"></script>
		<script type="text/javascript" src="js/login.js"></script>
		<script type="text/javascript" src="/js/statistical.js"></script>
		<script>
			var mySwiper = new Swiper('.swiper1', {
				autoplay: 5000, //可选选项，自动滑动
				loop: true, //可选选项，开启循环
			})

			var mySwiper = new Swiper('.swiper2', {
				pagination: '.swiper-pagination',
				paginationClickable: true,
				//autoplay: 5000, //可选选项，自动滑动
				loop: true, //可选选项，开启循环
				prevButton: '.swiper-button-prev',
				nextButton: '.swiper-button-next'
			})
		</script>
	</body>

</html>