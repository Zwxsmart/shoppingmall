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
	var store_list = $(".store_list");
	if (flag) {
		$.get('/easyhome/store/list', {
			page: page
		}, function(json) {
			if (json.returnStatus.status == "0") {
				var resp = json.data.pageResponse;
				if (resp.data.length > 0) {
					// 数据展示
					var div = "";
					$.each(resp.data, function(i, n) {
						div = div + "<a class=\"item\">" +
								"<div class='item_img'><img src=\"" + n.showUrl + "\" /></div>" +
								"<h2 class=\"title\">" + n.name + "</h2>" +
								"<p class=\"text\">咨询热线：" + n.storePhone + "</p> <i class='item_icon'>" + n.floor + "F</i></a>";
					});
					store_list.append(div);
				} else {
					flag = false;
					$(".end").text("没有更多数据了！");
				}
			}
		});
	}
}
