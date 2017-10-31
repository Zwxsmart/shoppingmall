$(function(){
	$("li.theme").click(function() {
		tag_click("theme", $(this));
	});
	$("li.style").click(function() {
		tag_click("style", $(this));
	});
	$("li.area").click(function() {
		tag_click("area", $(this));
	});
	$("li.part").click(function() {
		tag_click("part", $(this));
	});
	getData(1);
});

function tag_click(name, tag) {
	$("li."+name).removeClass("on");
	tag.addClass("on");
	var id = tag.attr("id");
	var text = tag.text();
	$("p."+name).remove();
	$(".add_model_nav").append("<p id=\"" + id + "\" class=\"add_nav " + name +"\" onclick=\"close_tag('" + id + "')\">" + text + "<i class=\"icon_close\"></i></p>");
	getData(1);
}

function close_tag(id) {
	$("li#"+id).removeClass("on");
	$("p#"+id).remove();
	getData(1);
}

function getData(page) {
	var style = $("p.style").attr("id");
	var theme = $("p.theme").attr("id");
	var area = $("p.area").attr("id");
	var part = $("p.part").attr("id");
	if (style == undefined) { style = ""; }
	if (theme == undefined) { theme = ""; }
	if (area == undefined) { area = ""; }
	if (part == undefined) { part = ""; }
	
	$.get('/easyhome/model/list', {
		style: style,
		theme: theme,
		area: area,
		part: part,
		page: page
	}, function(json) {
		if (json.returnStatus.status == "0") {
			var resp = json.data.pageResponse;
			var model_list = $(".model_list");
			model_list.empty();
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
					var type = "<p class=\"store_box_text\">";
					$.each(n.types, function(j, v) {
						type = type + "<span class=\"store_label\">" + v.caseType + "</span>";
					});
					type = type + "</p>";
					
					if (i%4 == 0) {
						div = div + "<div class='row'>";
					}
					div = div + "<div class=\"col-md-3\">" +
								 "<a class=\"store_group box-shadow-1\" href=\"javascript:;\" onclick=\"getDetails('" + n.caseId + "')\">" +
								   "<img class=\"store_img\" src=\"" + n.picUrl + "\">" +
								   "<div class=\"store_box\">" +
								     "<h2 class=\"store_box_name\"><span>" + n.title + 
								     "</span></h2>" + type + 
								"</div></a></div>";
					
					if ((i+1) < resp.data.length) {
						if ((i+1)%4 == 0) {
							div = div + "</div>";
						}
					} else {
						div = div + "</div>";
					}
				});
				model_list.append(div);
				
				
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
				model_list.append("<div class='col-md-12'><p>查无数据，请减少条件!</p></div>");
				$(".pageNum").text(0);
				$(".totalPages").text(0);
				btnl.addClass("pointer");
				btnr.addClass("pointer");
			}
		}
	});
}

function getDetails(caseId) {
	document.location.href = "model_list_details.html?caseId=" + caseId;
}
