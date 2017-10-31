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
            .cont_group .swiper_group{background:#eee; margin-bottom:30px; padding-top:0px;}
            .swiper_group .swiper-slide img{width:auto; max-height: 500px;}
            .swiper_group .swiper-container.swiper2{padding-bottom:0px;}

		</style>
	</head>

	<body>
		<!-- 页眉 -->
		<#include "head.ftl" />
		
		<div class="cont">
			<div class="container cont_group">
				<div class="cont_title_group">
					<div class="row">
						<div class="col-md-8">
							<p class="title">
								<a href="index.html">首页</a>
							</p>
							<i class="icon_next"></i>
							<p class="title">
								<a href="model_list.html">样板间</a>
							</p>
							<i class="icon_next"></i>
							<p class="title">
								<a>${model.title}</a>
							</p>
						</div>
						<div class="col-md-4">
							<p class="pading">
							</p>
						</div>
					</div>
				</div>
				<!--轮播组件-->
				<div class="swiper_group">
					<div class="swiper-container swiper2">
						<div class="swiper-wrapper">
							<div class="swiper-slide">
								<img src="${imgPath+model.picUrl}">
							</div>
						</div>
						<div class="swiper-pagination .my_pagination"></div>
						<div class="swiper-button-prev "></div>
						<div class="swiper-button-next "></div>
					</div>
				</div>
				<!--end-->
				<div class="cont_store_list">
					<div>
					<#if (model.goodsList)??>
						<#list model.goodsList as goods>
							<#if goods_index%4 == 0>
								<div class="row">
							</#if>
							<div class="col-md-3">
								<a class="store_group box-shadow-1 model_store_group" style="width:100%;">
                                    <div class="model_store_img">
										<#if goods.isCrosswise == true>
                                           		<img class="store_img" src="${imgPath+goods.smallUrl}" style="top:24%;">
										    <#else>
                                                <img class="store_img" src="${imgPath+goods.smallUrl}">
										</#if>

                                    </div>

									<div class="store_box">
										<h2 class="store_box_name"><span class="name">${goods.goodsName}</span></h2>
										<#if (goods.price)??>
										<p class="store_box_text">市场价:<em class="price">￥<span>${goods.price}</span></em></p>
										</#if>
									</div>
								</a>
							</div>
							<#if goods_has_next>
								<#if (goods_index+1)%4 == 0></div></#if>
							<#else>
								</div>
							</#if>
						</#list>
					</#if>
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