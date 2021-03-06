<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
		<meta name="viewport" content="width=device-width, initial-scale=1.0">	
		<title>联盟国际-关于我们</title>
		<link rel="stylesheet" href="assets/swiper/swiper.min.css" />
		<link rel="stylesheet" href="assets/Bootstrap/css/bootstrap.min.css" />
		<link rel="stylesheet" href="assets/Bootstrap/css/bootsnav.css" />
		<link rel="stylesheet" href="assets/Bootstrap/css/menu_group.css" />
		<link rel="stylesheet" href="css/master.css" />
		<link rel="stylesheet" href="css/model.css" />
		<style>
            .cont_about_text .row p{text-align:left;}
		</style>
	</head>
	<body>
		<!-- 页眉 -->
		<#include "head.ftl" />
		
		<div class="cont">
			<div class="container cont_group">
				<div class="cont_about_group clear">
					<div class="cont_about_log">

					</div>
					<div class="cont_group_btn" id="modelBtn">
						<a class="btn">集团介绍</a>
						<a class="btn">集团动态</a>
						<a class="btn">企业文化</a>
						<a class="btn">企业荣誉</a>
					</div>
					
				</div>
				<div class="cont_about_text" id="module0">
					${groupProfile}
				</div>
				<div class="cont_about_text" id="module1">
					<div class="row dynamics">
						
					</div>	
					<div class="row">
						<nav aria-label="Page navigation">
						  <ul class="pagination">
						  
						  </ul>
						</nav>
					</div>
				</div>
				<div class="cont_about_text culture " id="module2">
					${corporateCulture}
				</div>
				<div class="cont_about_text " id="module3">
					<#list companyHonor as ch>
						<#if ch_index%4 == 0>
						<div class="row">
						</#if>
						<div class="col-md-3">
							<img src="${imgPath+ch.picTitle}" />
							<p>${ch.title}</p>
						</div>
						<#if ch_has_next>
							<#if (ch_index+1)%4 == 0></div></#if>
						<#else>
							</div>
						</#if>
					</#list>
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
		<script type="text/javascript" src="js/group_dynamics.js"></script>
		<script type="text/javascript" src="/js/statistical.js"></script>
		<script>
			 var mySwiper = new Swiper('.swiper-container',{
				autoplay : 5000,//可选选项，自动滑动
				loop : true,//可选选项，开启循环
			})
		</script>
	</body>
</html>
