$(function(){
	getData(1);
});

function getData(page) {
	$.get('/easyhome/store/list', {
		page: page
	}, function(json) {
		if (json.returnStatus.status == "0") {
			var resp = json.data.pageResponse;
			var store_list = $(".store_list");
			store_list.empty();
			var pageBar = $("ul.pagination");
			pageBar.empty();
			var btnl = $(".icon_btn_l");
			btnl.removeClass("pointer");
			btnl.removeAttr("onclick");
			var btnr = $(".icon_btn_r");
			btnr.removeClass("pointer");
			btnr.removeAttr("onclick");
			
			if (resp.data.length > 0) {
				$(".pageNum").text(resp.pageNum);
				$(".totalPages").text(resp.totalPages);
				// 数据展示
				var div = "";
				$.each(resp.data, function(i, n) {
					if (i%3 == 0) {
						div = div + "<div class='row'>";
					}
					div = div + "<div class=\"col-md-4\">" +
									"<a class=\"store_group box-shadow-1\">" +
										"<img class=\"store_img\" src=\"" + n.showUrl + "\">" +
										"<div class=\"store_box\">" +
											"<h2 class=\"store_box_name\"><span>" + n.name + "</span>" +
													"<span class=\"floatRight\">" + n.floor + "F</span></h2>" + 
											"<p class=\"store_box_text\"><span>咨询热线：" + n.storePhone + "</span></p>" + 
								"</div></a></div>";
					if ((i+1) < resp.data.length) {
						if ((i+1)%3 == 0) {
							div = div + "</div>";
						}
					} else {
						div = div + "</div>";
					}
				});
				store_list.append(div);
				
				// 上一页
				btnl.attr("onclick", "getData('" + resp.beforePage + "')");
				var beforePage = "<li><a href=\"javascript:;\" aria-label=\"Previous\" onclick=\"getData('" + resp.beforePage + "')\"><span aria-hidden=\"true\">&laquo;</span></a></li>";
				if (resp.beforePage == resp.pageNum) {
					beforePage = "<li><a href=\"javascript:;\" aria-label=\"Previous\" class=\"pointer\"><span aria-hidden=\"true\">&laquo;</span></a></li>";
					btnl.addClass("pointer");
				}
				pageBar.append(beforePage);
				// 中间页码 pageBar
				$.each(resp.pageBar, function(i, n) {
					var page = "<li><a href=\"javascript:;\" onclick=\"getData('" + n + "')\">" + n + "</a></li>";
					if (n == resp.pageNum) {
						page = "<li><a href=\"javascript:;\" class=\"pointer g-bg3\">" + n + "</a></li>";
					}
					pageBar.append(page);
				});
				// 下一页
				btnr.attr("onclick", "getData('" + resp.nextPage + "')");
				var nextPage = "<li><a href=\"javascript:;\" aria-label=\"Next\" onclick=\"getData('" + resp.nextPage + "')\"><span aria-hidden=\"true\">&raquo;</span></a></li>";
				if (resp.nextPage == resp.pageNum) {
					nextPage = "<li><a href=\"javascript:;\" aria-label=\"Next\" class=\"pointer\"><span aria-hidden=\"true\">&raquo;</span></a></li>";
					btnr.addClass("pointer");
				}
				pageBar.append(nextPage);
			} else {
				store_list.append("<div class='col-md-12'><p>暂无家具店铺！</p></div>");
				$(".pageNum").text(0);
				$(".totalPages").text(0);
				btnl.addClass("pointer");
				btnr.addClass("pointer");
			}
		}
	});
}
