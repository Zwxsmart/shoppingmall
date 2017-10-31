<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
        <script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
        <script type="text/javascript" src="mb/js/jquery.min.js"></script>
		<script type="text/javascript" src="mb/js/flexible.js"></script>
		<link rel="stylesheet" href="mb/css/master.css" />
		<link rel="stylesheet" href="mb/assets/mui/css/mui.css">
		<!--自定义-->
		<link rel="stylesheet" href="mb/css/mb_style.css" />
		<title>联盟国际</title>
	</head>
	<body>
		<div class="mui-content">
		    <div class="login_ok_group">
		    	 <div class="login_ok_log">
		    	 	<a class="box">
		    	 		<img src="img/logo2.png">
		    	 		<h1>联盟国际</h1>	
		    	 	</a>		    	 	
		    	 </div>
		    	 <div class="login_ok_cont">
		    	 	<h2>即将登录联盟国际，请确认是本人操作</h2>
		    	 	<p>使用你的帐号登录该应用</p>
		    	 	<button type="button" class="mui-btn mui-btn-green mui-btn-block" onclick="showlogin()">确认登录</button>
		    		<button type="button" class="mui-btn  mui-btn-block" onclick="showclose()">取消</button>
		    	 </div>
		    </div>
		</div>
	</body>
	<script type="text/javascript" src="assets/jquery-1.10.2.js"></script>
	<script type="text/javascript" src="js/jquery.cookie.js"></script>
	<script type="text/javascript" src="/js/statistical.js"></script>
	<script type="text/javascript">
	            $(function(){
	
	            });
	
	            function showlogin(){
	                $.get("/wechatLogin?openId="+'${openId}'+"&uuid="+'${uuid}', function (res) {
	                    wx.closeWindow();
	                })
	                wx.closeWindow();
	            }
	            function showclose(){
	                wx.closeWindow();
	            }
	</script>
</html>
