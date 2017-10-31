//登录
if(typeof jQuery === "undefined"){
	console.log("请加载jQuery文件");
}

$(function(){
	// 获取登录验证码
	$(".login_get_code").click(function() {
		var iphone = $("#phone").val(); //获取输入框里的手机号码
		var reg = /^1[3578]\d{9}$/;
		if(reg.test(iphone)) {
			var btn = $(this);
			var count = 60;
			var resend = setInterval(function() {
				count--;
				if(count > 0) {
					btn.text(count + "秒后获取");
				} else {
					clearInterval(resend);
					btn.text("获取验证码").removeAttr('disabled style');
				}
			}, 1000);
			btn.attr('disabled', true).css('cursor', 'not-allowed');
			$.get('/login/code', {
				phone: iphone,
			}, function(json) {});
		} else {
			layer.open({
				time : 2,
				content : '请输入正确的手机号码！'
			})
		}
	});
	
	// 快速登录
	$("#quick_login").click(function() {
		var iphone = $("#phone").val(); //获取输入框里的手机号码
		var code = $("#phone_code").val(); //获取输入框里的验证码
		var reg = /^1[3578]\d{9}$/;
		if(reg.test(iphone)) {
			if (code == undefined || code == null || code == "") {
				layer.open({
					time : 2,
					content : '验证码不能为空！'
				})
			} else { // 登录
				$.post('/login', {
					phone: iphone,
					checkCode: code
				}, function(json) {
					if (json.returnStatus.status == "0") {
						$.cookie('token', json.data.access_token, { expires: 7, path: '/' });
						document.location.href = "index.html";
					} else {
						layer.open({
							time : 2,
							content : json.returnStatus.info
						})
					}
				});
			}
		} else {
			layer.open({
				time : 2,
				content : '请输入正确的手机号码！'
			})
		}
	});
	
})
