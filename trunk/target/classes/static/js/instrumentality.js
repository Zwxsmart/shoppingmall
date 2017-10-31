/*工具类*/

var instrumentality = {
	//添加cookie: 方法名：setCookie ，参数： c_name:变量名 value ：内容 ，expiredays：存储时间（天）
	setCookie: function(c_name, value, expiredays) {
		var exdate = new Date()
		exdate.setDate(exdate.getDate() + expiredays)
		console.log(escape(value));
		document.cookie = c_name + "=" + escape(value) + ((expiredays == null) ? "" : ";expires=" + exdate.toGMTString())
	},
	//获取cookie: 方法名：getCookie: 参数：c_name:变量名 变量名
	getCookie: function(c_name) {
		if(document.cookie.length > 0) {
			var c_start = document.cookie.indexOf(c_name + "=");
			if(c_start != -1) {
				c_start = c_start + c_name.length + 1
				var c_end = document.cookie.indexOf(";", c_start);
				if(c_end == -1) c_end = document.cookie.length;
				return unescape(document.cookie.substring(c_start, c_end));
			}
		}
		return "";
	},
	//删除cookie：方法名：dalCookie ; 参数 c_name:变量名
	delCookie: function(c_name) {
		if(c_name) {
			console.log("对应的");
			var exp = new Date();
			exp.setTime(exp.getTime() - 1);
			var cval = this.getCookie(c_name);
			console.log("cval:" + cval);
			if(cval != null) {
				document.cookie = c_name + "=" + escape(cval) + ((exp == null) ? "" : ";expires=" + exp.toGMTString())
			}
		} else {
			console.log("清除所有");
			var keys = document.cookie.match(/[^ =;]+(?=\=)/g);
			if(keys) {
				for(var i = keys.length; i--;)
					document.cookie = keys[i] + '=0;expires=' + new Date(0).toUTCString()
			}
		}
	},

	//获取URL传参 方法名：getUrl 参数 c_name :变量名
	getUrl: function(c_name) {
		//构造一个含有目标参数的正则表达式对象
		var c_start, c_end, url = window.location.search.substr(1);
		if(!url) {
			console.log("Url没有带参数");
			return "";
		}
		c_start = url.indexOf(c_name + "=");
		if(c_start != -1) {
			c_start = c_start + c_name.length + 1;
			c_end = url.indexOf("&", c_start);
			if(c_end != -1) {
				url = url.slice(c_start, c_end);
			} else {
				url = url.slice(c_start);
			}
			return unescape(url);
		} else {
			return "";
		}
	}
};