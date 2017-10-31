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
		<!--自定义-->
		<link rel="stylesheet" href="css/mb_style.css" />
		<title>联盟国际</title>
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
			     	<span class="my_login">
			     	<#if (user.nick)?? && (user.nick) != ''>${user.nick}<#else>${user.phone}</#if>
			     	</span>
                <#else>
			     	<button id="login" type="button" class="mui-btn mui-icon iconfont icon-rgzx">快速登录</button>
				</#if>
		     </div>
		     
		    </div>
		    <!--轮播-->
		    <#include "banner_div.ftl" />
		  	
			<!--内容-->
			<div class="cont_group">
				<div class="item">
					<div class="g_shade_bg g_bor_rad"></div>
					<img class="g_bor_rad" src="img/bg_01.png">
					<div class="item_text">
						<a href="business_cooperation.html">
						<h1>商务合作</h1>
						<h3>BUSINESS COOPERATION</h3>
						</a>
					</div>
				</div>
				<div class="item">
					<div class="g_shade_bg g_bor_rad"></div>
					<img class="g_bor_rad" src="img/bg_02.png">
					<div class="item_text">
						<a href="fiery_market.html">
						<h1>火热销售</h1>
						<h3>HOT SALES</h3>
						</a>
					</div>
				</div>
				<div class="item">
					<div class="g_shade_bg g_bor_rad"></div>
					<img class="g_bor_rad" src="img/bg_03.png">
					<div class="item_text">
						<a href="home_win_city.html">
							<h1>家博城</h1>
							<h3>JIA BO CHENG</h3>
						</a>						
					</div>
				</div>
				<div class="item">
					<div class="g_shade_bg g_bor_rad"></div>
					<img class="g_bor_rad" src="img/bg_04.png">
					<div class="item_text">
						<a href="easyhome/index.html">
						<h1>居然之家</h1>
						<h3>EASYHOME</h3>
						</a>
					</div>
				</div>
				<div class="item">
					<div class="g_shade_bg g_bor_rad"></div>
					<img class="g_bor_rad" src="img/bg_05.png">
					<div class="item_text">
						<a href="hot_activity.html">
						<h1>热门活动</h1>
						<h3>HOT CAMPAIGN</h3>
						</a>
					</div>
				</div>
				<div class="item">
					<div class="g_shade_bg g_bor_rad"></div>
					<img class="g_bor_rad" src="img/bg_06.png">
					<div class="item_text">
						<a href="furniture_plant.html">
						<h1>家具工厂</h1>
						<h3>FURNITURE FACTORY</h3>
						</a>
					</div>
				</div>
				<div class="item">
					<div class="g_shade_bg g_bor_rad"></div>
					<img class="g_bor_rad" src="img/bg_07.png">
					<div class="item_text">
						<a href="brand_list.html">
						<h1>品牌联盟</h1>
						<h3>BRAND COOPERATION</h3>
						</a>
					</div>
				</div>
				<div class="item">
					<div class="g_shade_bg g_bor_rad"></div>
					<img class="g_bor_rad" src="img/bg_08.png">
					<div class="item_text">
						<a href="talent_recruitment.html">
						<h1>人才招聘</h1>
						<h3>TALENT RECRUITMENT</h3>
						</a>
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
				<span class="mui-icon iconfont icon-guanyuwomen"></span>
				<span class="mui-tab-label">关于我们</span>
			</a>
			<a class="mui-tab-item" type="2">
				<span class="mui-icon iconfont icon-lianxi"></span>
				<span class="mui-tab-label">联系我们</span>
			</a>
			<a class="mui-tab-item" type="3">
				<span class="mui-icon iconfont icon-rgzx"></span>
				<span class="mui-tab-label">个人中心</span>
			</a>
		</nav>
		<script type="text/javascript" src="assets/mui/js/mui.js"></script>
		<script type="text/javascript" src="assets/jquery-1.10.2.js"></script>
		<script type="text/javascript" src="js/jquery.cookie.js"></script>
		<script type="text/javascript" src="js/nav.js"></script>
		<script type="text/javascript" src="/js/statistical.js"></script>
		<script>
			mui.init({
				swipeBack:true //启用右滑关闭功能
			});
		</script>
	</body>

</html>