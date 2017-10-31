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
		
		<div class="cont">
			<div class="container cont_group">
				<div class="cont_title_group">
					<div class="row">
						<div class="col-md-6">
							<p class="title">
								<a href="index.html">首页</a>
							</p>
							<i class="icon_next"></i>
							<p class="title">
								<a>家具店铺</a>
							</p>
						</div>
						<div class="col-md-6">
							<p class="pading_btn"><i class="icon_btn icon_btn_r"></i> <i class="icon_btn icon_btn_l"></i></p>
							<p class="pading"><span class="at pageNum">0</span>/<span class="totalPages">0</span></p>
						</div>
					</div>
				</div>
				<div class="cont_store_list">
					<div class="store_list">
						
						<div class="row">
							<div class="col-md-4">
								<a class="store_group box-shadow-1">
									<img class="store_img" src="img/store.png">
									<div class="store_box">
										<h2 class="store_box_name"><span>幕斯家具</span><span class="floatRight">1F</span></h2>
										<p class="store_box_text"><span>咨询热线：123456</span><em class="style floatRight">惠：<span class="text">买一送一</span></em></p>
									</div>
								</a>
							</div>
						</div>
						
					</div>
					<!--分页-->
					<nav aria-label="Page navigation">
						<ul class="pagination">
							<li>
								<a href="#" aria-label="Previous">
									<span aria-hidden="true">&laquo;</span>
								</a>
							</li>
							<li>
								<a href="#">1</a>
							</li>
							<li>
								<a href="#">2</a>
							</li>
							<li>
								<a href="#">3</a>
							</li>
							<li>
								<a href="#" aria-label="Next">
									<span aria-hidden="true">&raquo;</span>
								</a>
							</li>
						</ul>
					</nav>
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
		<script type="text/javascript" src="js/store.js"></script>
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