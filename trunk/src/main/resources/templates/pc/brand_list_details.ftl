<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
		<meta name="viewport" content="width=device-width, initial-scale=1.0">	
		<title>联盟国际-品牌详情</title>
		<link rel="stylesheet" href="assets/swiper/swiper.min.css" />
		<link rel="stylesheet" href="assets/Bootstrap/css/bootstrap.min.css" />
		<link rel="stylesheet" href="assets/Bootstrap/css/bootsnav.css" />
		<link rel="stylesheet" href="assets/Bootstrap/css/menu_group.css" />
		<link rel="stylesheet" href="css/master.css" />
		<link rel="stylesheet" href="css/model.css" />
		<style type="text/css">
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
						</div>
					</div>
					<div class="brand_text"><p>${(brand.intro)!''}</p></div>
				</div>
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
			 var mySwiper = new Swiper('.swiper-container',{
			 	pagination : '.swiper-pagination',
			 	paginationClickable :true,
				autoplay : 5000,//可选选项，自动滑动
				loop : true,//可选选项，开启循环
				prevButton:'.swiper-button-prev',
				nextButton:'.swiper-button-next'
			})
		</script>
	</body>
</html>
