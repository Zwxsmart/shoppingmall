var page = 1;
var size = 6;
var flag = true;
$(function(){
	getData(page);
	
	window.onscroll = function() {
		//监听事件内容
		if (getScrollHeight() == getWindowHeight() + getDocumentTop()) {
			//当滚动条到底时,这里是触发内容
			//异步请求数据,局部刷新dom
			getData(++page)
		}
	}
});

function getData(page) {
	var dynamics = $(".dynamics");
	if (flag) {
		$.get('/group/dynamics', {
			page: page,
			size: size
		}, function(json) {
			if (json.returnStatus.status == "0") {
				var resp = json.data;
				if (resp.data.length > 0) {
					// 数据展示
					var li = "";
					$.each(resp.data, function(i, n) {
						li = li + "<li class=\"mui-table-view-cell mui-media contact_cont\">" +
									"<a href=\javascript:;\" onclick=\"getDetails('" + n.id + "')\">" +
										"<img class=\"mui-media-object mui-pull-left\" src=\"" + resp.imgPath + n.picTitle + "\">" +
										"<div class=\"mui-media-body\">" +
											"<h1 class=\"title mui-ellipsis\">" + n.title + "</h1>" +
											"<p class=\"clear\"><span></span> <span class=\"g-f-right\">" + formatTime(n.createDate) + "</span></p>" +
								  "</div></a></li>";
					});
					dynamics.append(li);
				} else {
					flag = false;
					dynamics.append("<div><p align='center'>没有更多数据了！</p></div>");
				}
			}
		});
	}
}

function getDetails(id) {
	document.location.href = "group_dynamics.html?id=" + id;
}

function formatTime(datestring){
	return new Date(datestring).Format("yyyy-MM-dd");
}

Date.prototype.Format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "h+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}
