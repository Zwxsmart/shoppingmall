<div class="login g_toget" id="openLogin">
	<div class="login_bg"></div>
	<div class="login_group">
		<div class="login_log">
			<i class="icon_close" id="closeLogin"></i>
			<img class="login_img" src="img/logo2.png" />
		</div>
		
		<div class="login_cont">
			<div class="row login_cont_select_btn">
				<div class="col-md-6">
					<button class="btn on_btn" type="0">快速登录</button>
				</div>
				<div class="col-md-6">
					<button  class="btn" type="1">扫码登录</button>
				</div>
			</div>
			<!--快速登录-->
			<div class="login_01 " id="loginMode">
				<div class="login_01_input">
					<input type="text"  id="phone" class="phone"  placeholder="手机号" >
					<button class="login_get_code">获取验证码</button>
				</div>
				<div class="login_01_input">
					<input type="text"  id="phone_code" class="phone_code"  placeholder="请输入手机验证码" >
				</div>
				<button type="button" class="btn btn-default btn-lg btn-block g-m-t20" id="quick_login">登&nbsp;录</button>
			</div>
			<!--扫码登录-->
			<div class="login_01 logein_02 g_toget" id="loginMode1">
				<div id="imgQRcode"></div>
			</div>
		</div>
	</div>
</div>