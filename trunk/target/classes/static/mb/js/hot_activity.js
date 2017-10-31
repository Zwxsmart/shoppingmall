var page = 1;
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
	var activity_list = $(".hot_group");
	if (flag) {
		$.get('/activity/list', {
			page: page
		}, function(json) {
			if (json.returnStatus.status == "0") {
				var resp = json.data.pageResponse;
				if (resp.data.length > 0) {
					// 数据展示
					var div = "";
					$.each(resp.data, function(i, n) {
						div = div + "<a class=\"item\" href=\javascript:;\" onclick=\"getDetails('" + n.id + "')\">" +
								"<img src=\"" + n.picTitle + "\" /></a>";
					});
					activity_list.append(div);
				} else {
					flag = false;
					activity_list.append("<div><p align='center'>没有更多数据了！</p></div>");
				}
			}
		});
	}
}

function getDetails(id) {
	document.location.href = "hot_activity_details.html?id=" + id;
}
