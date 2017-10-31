<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
		<meta name="viewport" content="width=device-width, initial-scale=1.0">	
		<title>联盟国际-商务合作</title>
		<link rel="stylesheet" href="assets/swiper/swiper.min.css" />
		<link rel="stylesheet" href="assets/Bootstrap/css/bootstrap.min.css" />
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
				<div class="cont_about_group clear">
					<div class="cont_about_log cooperation_img">

					</div>
					<div class="cont_group_btn" id="modelBtn">
						<a class="btn">商务百问</a>
						<a class="btn">业态划分</a>
						<a class="btn">商务咨询</a>
					</div>
					
				</div>
			
				<div class="cont_about_text g_toget" id="module0">
					${businessQuestions}
				</div>
				
				<div class="cont_about_text g_toget" id="module1">
					${formatDivision}
				</div>
				
				<div class="cont_about_text " id="module2">
					${businessConsulting}
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
		<script type="text/javascript" src="js/instrumentality.js"></script>
		<script type="text/javascript" src="js/jquery.cookie.js"></script>
		<script type="text/javascript" src="js/layer.js"></script>
		<script type="text/javascript" src="js/login.js"></script>
		<script type="text/javascript" src="js/select_module.js"></script>
		<script type="text/javascript" src="/js/statistical.js"></script>
		<script>
			 var mySwiper = new Swiper('.swiper-container',{
				autoplay : 5000,//可选选项，自动滑动
				loop : true,//可选选项，开启循环
			})
		</script>
	</body>
</html>
