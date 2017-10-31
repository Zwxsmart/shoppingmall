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
				<a href="/index"><img class="log" src="img/logo.png" /></a>
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
							<li>
								<a href="index.html">首页</a>
							</li>
							<li class="dropdown">
								<a href="model_list.html">样板间</a>
							</li>
							<li class="dropdown">
								<a href="map.html">商场地图</a>						
							</li>
							<li class="dropdown">
								<a href="hot_activity.html">热门活动</a>								
							</li>
							<li>
								<a href="contact_us.html">联系我们</a>
							</li>
						</ul>
					</div>
				</nav>
			</div>
		</div>
	</div>

	<!--轮播组件-->
	<div class="swiper_group">
		<div class="swiper-container swiper1">
			<div class="swiper-wrapper">
				<#if banners??>
					<#list banners as b>
						<div class="swiper-slide"><a href="<#if (b.gotoUrl)?? && (b.gotoUrl) != ''>${b.gotoUrl}<#else>index.html</#if>"><img src="${imgPath+b.picUrl}"></a></div>
					</#list>
				</#if>
			</div>
		</div>
	</div>
</div>