var page = 1, flag = true;

$(function(){
	initItem();
	
	window.onscroll = function() {
		if (getScrollHeight() == getWindowHeight() + getDocumentTop()) {
			getData(++page);
		}
	}
});

function initItem() {
	page = 1;
	flag = true;
	$(".sample_rooms_group").empty();
	getData(page);
}

function getData(page) {
	if (flag) {
		var style = $(".style").find(".on").attr("id");
		var area = $(".area").find(".on").attr("id");
		var part = $(".part").find(".on").attr("id");
		if (style == undefined) { style = ""; }
		if (area == undefined) { area = ""; }
		if (part == undefined) { part = ""; }
		
		$.get('/easyhome/model/list', {
			style: style,
			area: area,
			part: part,
			page: page,
			size: 4
		}, function(json) {
			if (json.returnStatus.status == "0") {
				var div = $(".sample_rooms_group");
				var resp = json.data.pageResponse;
				if (resp.data.length > 0) {
					var a = "";
					$.each(resp.data, function(i, n) {
						var tag = "<div class=\"box caret\">";
						$.each(n.types, function(j, v) {
							tag = tag + "<p>" + v.caseType + "</p>";
						});
						tag = tag + "</div>";
						a = a + "<a class=\"item\" href=\"javascript:;\" onclick=\"getDetails('" + n.caseId + "')\">" +
								"<img src=\"" + n.picUrl + "\" />" +
								"<h2 class=\"title caret\">" + n.title + "</h2>" + tag + "</a>";
					});
					div.append(a);
				} else {
					flag = false;
					div.append("<div><p align='center'>没有更多数据了！</p></div>");
				}
			}
		});
	}
}

function getDetails(caseId) {
	document.location.href = "sample_rooms_details.html?caseId=" + caseId;
}
