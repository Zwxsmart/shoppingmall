//登录
if(typeof jQuery === "undefined") {
	console.log("请加载jQuery文件");
}

if(typeof instrumentality === "undefined") {
	console.log("请加载 instrumentality文件");
}

$(function() {
	var type = instrumentality.getUrl("type");
	console.log("---------------" + type);
	$("#modelBtn >a").each(function(i) {

		if(type == i) {
			$(this).addClass("toget_btn");
			selectModule.module(type);
		}

		$(this).on("click", function() {
			$(".toget_btn").removeClass("toget_btn");
			$(this).addClass("toget_btn");
			selectModule.module(i);
		})
	})

})

var selectModule = {
	module: function(type) {
		console.log("------module-------" + type);
		$(".g_togget").removeClass("g_togget");
		var text = "",
			i = parseInt(type);
		switch(i) {
			case 0:
				text = "module0";
				break;
			case 1:
				text = "module1";
				break;
			case 2:
				text = "module2";
				break;
			case 3:
				text = "module3";
				break;
			default:
				text = "module0";
				console.log("参数错误");

		}
		$("#" + text).addClass("g_togget");
	}
}