if(typeof jQuery === "undefined"){
	console.log("请加载jQuery文件");
}

$(function(){
	mui(".my_bar_nav").on('tap','.mui-tab-item',function(event){
		switch(parseInt(this.getAttribute("type"))){
				case 0:
					url = "index.html"; //首页
					break;
				case 1:
					url = "map.html"; //地图
					break;
				case 2:
					url = "contact_us.html"; //联系我们
					break;
				default:
					url = "personal_center.html"; //个人中心
			}
			document.location.href = url;
	}) 
	
	//打开登录页login
	mui(".login").on('tap','.mui-btn',function(){
		document.location.href="login.html";
	})

	/**************导航栏***************/
	mui(".select").on('tap','#select',function(){
		var sClass = $("#selectCont").attr("class");
		if(sClass.indexOf('g_dp_n')>0){			
			$("#selectCont").removeClass("g_dp_n");
		}else{
			$("#selectCont").addClass("g_dp_n");
		}
		
	})
	
	mui(".select_ul").on('tap',"a",function(event){		
		$(".select_ul").find(".on").removeClass("on");
		$(this).addClass("on");
		var url = $(this).attr("url");
		if (url != undefined) {
			window.location.href = "/mb" + url;
		}
	})
	/***************end***************/
	
	
	
	/*************样板间nav******************/
	var aId = "";
	mui("#segmentedControl").on('tap',"a.mui-control-item",function(event){
		aId = event;
		var id = this.getAttribute("href").substring(1);
		var className = document.getElementById(id).getAttribute("class");		
		if(className.indexOf("mui-active") >0){
			$("#"+id).removeClass("mui-active");
		}else{
			$("#"+id).addClass("mui-active");
		}
	})
	
	mui(".nav_cent").on("tap","a",function(event){
		var id = aId.target.getAttribute("href").substring(1); //父ID
		$(this).parent().parent().find(".on").removeClass("on");
		$(this).addClass("on");
		var text = $(this).html();
		if (text == "全部") {
			switch (id) {
			case "item1":
				text = "风格";
				break;
			case "item2":
				text = "空间";
				break;
			case "item3":
				text = "局部";
				break;
			}
		}
		$(aId.target).html(text);
		$(aId.target).removeClass("mui-active");
		$("#"+id).removeClass("mui-active");
		initItem();
	})
	
	/***************end***************/
})
