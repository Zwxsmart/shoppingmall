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
		<style>
			.swiper_group {
				padding-top: 0px;
			}
			
			.swiper-container-horizontal>.swiper-pagination-bullets,
			.swiper-pagination-custom,
			.swiper-pagination-fraction {
				bottom: 45px;
			}
			
			.swiper_group .swiper-pagination-bullet {
				width: 8px;
				height: 8px;
			}
			
			.swiper_group .swiper-button-next,
			.swiper_group .swiper-button-prev {
				width: 66px;
				height: 58px;
			}
			
			.swiper_group .swiper-button-prev {
				background: url(img/left_btn_1.png) center no-repeat;
				left: 23px;
			}
			
			.swiper_group .swiper-button-prev:hover {
				background: url(img/left_btn.png) center no-repeat;
				left: 23px;
			}
			
			.swiper_group .swiper-button-next {
				background: url(img/right_btn_1.png) center no-repeat;
				right: 23px;
			}
			
			.swiper_group .swiper-button-next:hover {
				background: url(img/right_btn.png) center no-repeat;
				right: 23px;
			}
			.b_color {background: #ddd;}
		</style>
	</head>

	<body>
		<!-- 页眉 -->
		<#include "head.ftl" />
		
		<div class="cont">
			<div class="container cont_group">
				<div class="brand_details_group">
					<div class="brand_title clear">
						<img class="brand_log box-shadow-1 b_color" src="${(imgPath+brand.logoUrl)!''}" />
						<div class="brand_title_box clear">
							<h1 class="name">${(brand.name)!''}<span>${(brand.abbreviation)!''}</span></h1>
							<#if contact??>
							<p class="text">商场${contact.floor}F</p>
							<p class="text"><span>咨询热线：${contact.storePhone}</span></p>
							</#if>
						</div>
					</div>
					<div class="brand_text">
						<p>${(brand.intro)!''}</p>
					</div>
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
				autoplay: 5000, //可选选项，自动滑动
				loop: true, //可选选项，开启循环
				prevButton: '.swiper-button-prev',
				nextButton: '.swiper-button-next'
			})
		</script>
	</body>

</html>