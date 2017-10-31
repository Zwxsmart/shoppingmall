$(function(){
	getData(1);
});

function getData(page) {
	$.get('/easyhome/activity/list', {
		page: page
	}, function(json) {
		if (json.returnStatus.status == "0") {
			var resp = json.data.pageResponse;
			var activity_list = $(".activity_list");
			activity_list.empty();
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
					div = div + "<div class=\"cont_about_list\">" +
									"<a href=\"javascript:;\" onclick=\"getDetails('" + n.id + "')\">" +
										"<div class=\"cont_about_list_img\"><img src=\"" + n.picTitle + "\" /></div>" +
										"<div class=\"cont_about_list_text\">" +
											"<h2>" + n.title + "</h2><p>" + $(n.content).text() + "</p>" + 
								"</div></a></div>";
				});
				activity_list.append(div);
				
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
				activity_list.append("<div class='col-md-12'><p>暂无数据！</p></div>");
				$(".pageNum").text(0);
				$(".totalPages").text(0);
				btnl.addClass("pointer");
				btnr.addClass("pointer");
			}
		}
	});
}

function getDetails(id) {
	document.location.href = "hot_activity_details.html?id=" + id;
}
