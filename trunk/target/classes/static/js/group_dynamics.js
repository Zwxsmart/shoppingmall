if(typeof jQuery === "undefined") {
	console.log("请加载jQuery文件");
}

$(function() {
	getGroupDynamics(1)
})

function getGroupDynamics(page) {
	if (isNaN(page) || parseInt(page) < 0) {
		page = 1;
	}
	$.get('/group/dynamics', {
		page: page,
	}, function(json) {
		if (json.returnStatus.status == "0") {
			// 集团动态列表
			$(".dynamics").empty();
			$.each(json.data.data, function(i, n) {
				var div = $('<div/>',{ class: 'cont_about_list' });
				var a = $('<a/>',{ href: 'hot_activity.html?id='+n.id });
				
				var div1 = $('<div/>',{ class: 'cont_about_list_img' });
				var img = $('<img/>',{ src: json.data.imgPath + n.picTitle });
				div1.append(img);
				
				var div2 = $('<div/>',{ class: 'cont_about_list_text' });
				var h2 = $('<h2/>',{ text: n.title });
				var p = $('<p/>').append($(n.content).text());
				div2.append(h2).append(p);
				
				a.append(div1).append(div2);
				div.append(a);
				$(".dynamics").append(div);
			});
			
			// 分页条
			$(".pagination").empty();
			var page = json.data.page;
			var size = json.data.size;
			var total = json.data.total;
			var len = total % size == 0 ? total / size : parseInt(total / size) + 1;
			
			// 上一页
			var pre_li = $('<li/>');
			var pre_a = $('<a/>',{ href: 'javascript:;' });
			pre_a.attr('aria-label', 'Previous');
			if (page > 1) {
				pre_a.attr('onclick', 'getGroupDynamics(' + (page-1) + ')');
			} else {
				pre_a.css('pointer-events', 'none');
			}
			var pre_span = $('<span/>',{ text: '«' });
			pre_span.attr('aria-hidden', 'true');
			pre_a.append(pre_span);
			pre_li.append(pre_a);
			$(".pagination").append(pre_li);
			
			// 中间页码
			var limit = 10; // 分页条显示的页数
			var star = 1;
			var end = limit;
			if (len <= limit) {
				end = len;
			} else {
				if (page > 2) {
					if (len - page + 1 > limit) {
						star = page - 1;
						end = star + limit - 1;
					} else {
						star = len - limit + 1;
						end = len;
					}
				}
			}
			for (var i = star - 1; i < end; i++) {
				var li = $('<li/>');
				var a = $('<a/>', {
					text : i + 1,
					href : 'javascript:;',
				});
				if (page == i + 1) {
					a.attr('class', 'g-bg3');
					a.css('pointer-events', 'none');
				} else {
					a.attr('onclick', 'getGroupDynamics(' + (i+1) + ')');
				}
				li.append(a);
				$(".pagination").append(li);
			}
			
			// 下一页
			var next_li = $('<li/>');
			var next_a = $('<a/>',{ href: 'javascript:;' });
			next_a.attr('aria-label', 'Next');
			if (page < len) {
				next_a.attr('onclick', 'getGroupDynamics(' + (page+1) + ')');
			} else {
				next_a.css('pointer-events', 'none');
			}
			var next_span = $('<span/>',{ text: '»' });
			next_span.attr('aria-hidden', 'true');
			next_a.append(next_span);
			next_li.append(next_a);
			$(".pagination").append(next_li);
		}
	});
}