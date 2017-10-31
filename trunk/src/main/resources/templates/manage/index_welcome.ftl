<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>后台主页</title>
    <meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, width=device-width">
    <link rel="stylesheet" href="/aw/assets/bootstrap-tables/css/bootstrap-table.css">
    <link rel="stylesheet" href="/aw/css/bootstrap.min.css">
    <!-- Bootstrap core CSS -->
    <link href="/aw/css/bootstrap.min.css" rel="stylesheet">
    <link href="/aw/css/bootstrap-reset.css" rel="stylesheet">
    <!--external css-->
    <link href="/aw/assets/font-awesome/css/font-awesome.css" rel="stylesheet"/>
    <!-- Custom styles for this template -->
    <link href="/aw/css/style.css" rel="stylesheet">
    <link href="/aw/css/style-responsive.css" rel="stylesheet"/>
    <!-- css for echarts -->
    <link href="/aw/css/charts.css" rel="stylesheet"/>
    <!-- tabs style -->
    <link rel="stylesheet" href="/aw/assets/bootstrap-addtabs/css/bootstrap-addtabs.css"/>
    <link rel="stylesheet" href="/aw/assets/bootstrap-dateTimePicker/css/bootstrap-datetimepicker.min.css">
    <style>
        .myPading {
            padding-left: 0;
            padding-right: 0;
            padding-top: 10px;
            padding-bottom: 10px;
        }
        .datetimepicker {
            padding-left: 20px;
            padding-top: 20px;
            padding-bottom: 40px;
        }
        .panel-body {
        	text-align: center;
        }
        .charts {
		    width: 100%;
		    height: 257px;
		}
		.charts3 {
		    width: 100%;
		    height: 400px;
		}
		.panel-heading h4 {text-align: left;}
		.img_w {width: 100px;}
    </style>
</head>
<body  style="text-align: center;margin:0px;" class="panel-body">

<div class="container-fluid" style="margin-top:0px;margin-left:-28px;">
	<div class="row">
        <div class="col-lg-7 col-md-7 col-sm-12">
            <section class="panel" style="overflow-y:auto;">
				<header class="panel-heading" style="color:#008acd;">
       				<h4>会员动态</h4>
    			</header>
			    <div class="panel-body">
			    	<div id="user_count_1" class="charts"></div>
			    	<div id="user_count_2" class="charts"></div>
			    </div>
			</section>
        </div>
        
        <div class="col-lg-5 col-md-5 col-sm-12">
            <section class="panel">
				<header class="panel-heading" style="color:#008acd;">
			       <h4>统计信息</h4>
			    </header>
			    <div class="panel-body">
			    	<div class="row">
			            <div class="col-lg-6 col-md-6 col-sm-6">
			                <img src="/aw/img/user.png" class="img_w">
                            <h4>会员总数</h4>
                            <p>${counter.user}</p>
			            </div>
			            <div class="col-lg-6 col-md-6 col-sm-6">
			                <img src="/aw/img/goods.png" class="img_w">
                            <h4>商品总数</h4>
                            <p>${counter.goods}</p>
			            </div>
			        </div>

			        <div class="row">
			            <div class="col-lg-6 col-md-6 col-sm-6">
			                <img src="/aw/img/store.png" class="img_w">
                            <h4>店铺总数</h4>
                            <p>${counter.store}</p>
			            </div>
			            <div class="col-lg-6 col-md-6 col-sm-6">
			                <img src="/aw/img/order.png" class="img_w">
                            <h4>订单总数</h4>
                            <p>${counter.order}</p>
			            </div>
			        </div>

			    </div>
			</section>
			
			<section class="panel">
				<header class="panel-heading" style="color:#008acd;">
			       <h4>页面流量统计</h4>
			    </header>
			    <div class="panel-body">
			        <div class="row">
			            <div class="col-lg-4 col-md-4 col-sm-4">
			                <h5>${pageview.pv}</h5>
                            <h5>今日PV</h5>
			            </div>
			            <div class="col-lg-4 col-md-4 col-sm-4">
			                <h5>${pageview.uv}</h5>
                            <h5>今日UV</h5>
			            </div>
			            <div class="col-lg-4 col-md-4 col-sm-4">
			                <h5>${pageview.ip}</h5>
                            <h5>今日IP</h5>
			            </div>
			        </div>

			    </div>
			</section>
        </div>
        
    </div>
    <div class="row">
        <div class="col-lg-12 col-md-12 col-sm-12">
            <section class="panel">
				<div class="datetimepicker">
			    	<span style="float:left;margin-top: 7px;">时间：</span>
	               	<input id="startTime" placeholder="请选择开始时间"
	                      readonly="true" type="text" name="startTime"
	                      class="form-control" style="width:120px;float:left"/>
	               	<span style="float:left;margin-top: 7px;margin-left: 3px;margin-right: 3px;">至</span>
	               	<input id="endTime" placeholder="请选择结束时间"
	                      readonly="true" type="text" name="endTime"
	                      class="form-control" style="width:120px;float:left"/>
	                <span style="float:left;margin-left: 3px;">
	                	<button id="statistics" class="btn btn-success">统计</button> (时间间隔不能大于31天)
	                </span>
                </div>
			    <div class="panel-body">
			    	<div id="user_count_3" class="charts3 row"></div>
			    </div>
			</section>
        </div>
    </div>
</div>

<script type="text/javascript" src="/aw/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="/aw/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/aw/assets/bootstrap-tables/js/dataTables.bootstrap.min.js"></script>
<script type="text/javascript" src="/aw/js/echarts.js"></script>
<script type="text/javascript" src="/aw/js/macarons.js"></script>
<script type="text/javascript" src="/aw/js/jquery.customSelect.min.js"></script>
<script type="text/javascript" src="/js/layer.js"></script>
<script type="text/javascript" src="/aw/assets/bootstrap-addtabs/js/bootstrap-addtabs.js"></script>
<script src="/aw/assets/bootstrap-dateTimePicker/js/bootstrap-datetimepicker.min.js"></script>
<script src="/aw/assets/bootstrap-dateTimePicker/js/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript">
	$(function () {
	    // 会员动态统计表一
	    var user_chart_1 = echarts.init(document.getElementById('user_count_1'), 'macarons');
	    var user_chart_data = [<#list userCount.total as list>${list?c}<#if list_index!=userCount.total?size-1>,</#if></#list>];
	    user_chart_1.setOption({
	    	title: {
    	        text: "会员增长量",
    	        textStyle: {
	                fontSize: 16
	            },
	            left:'10%',
    	    },
		    tooltip : {
		      	show: false,
		    },
	   		grid: {
	   	        left: '1%',
	   	        bottom: '10%',
	   	        top: '20%',
	   	        containLabel: true
	   	    },
		    xAxis : [{
	            type : 'category',
	            data : ['今年','本月','本周','今天']
		    }],
		    yAxis : [{
	            show : false,
	            type : 'value'
	        }],
		    series : [{
	            type:'bar',
				width:'100%',
	          	z : 10,
	            data: user_chart_data,
	            itemStyle: {
	                normal: {
	                    label: {
	                        show: true,
	                        position: 'top',
	                        textStyle: {
	                          fontSize: 14
	                      	}
	                    }
	                }
	            }
		    }]
	    });
	    
	 	// 会员动态统计表二
	    var user_chart_2 = echarts.init(document.getElementById('user_count_2'), 'macarons');
	 	//var iconPath = "path://M432.45,595.444c0,2.177-4.661,6.82-11.305,6.82c-6.475,0-11.306-4.567-11.306-6.82s4.852-6.812,11.306-6.812C427.841,588.632,432.452,593.191,432.45,595.444L432.45,595.444z M421.155,589.876c-3.009,0-5.448,2.495-5.448,5.572s2.439,5.572,5.448,5.572c3.01,0,5.449-2.495,5.449-5.572C426.604,592.371,424.165,589.876,421.155,589.876L421.155,589.876z M421.146,591.891c-1.916,0-3.47,1.589-3.47,3.549c0,1.959,1.554,3.548,3.47,3.548s3.469-1.589,3.469-3.548C424.614,593.479,423.062,591.891,421.146,591.891L421.146,591.891zM421.146,591.891";
	 	
	 	var y_title = '今年会员动态比较';
	    var y_x_data = ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月'];
	    var y_s_data = [<#list userCount.y as list>${list?c}<#if list_index!=userCount.y?size-1>,</#if></#list>];
	    
	    var m_title = '本月会员动态比较';
	    var m_x_data = [<#list userCount.m_x as list>'${list}'<#if list_index!=userCount.m_x?size-1>,</#if></#list>];
	    var m_s_data = [<#list userCount.m_y as list>${list?c}<#if list_index!=userCount.m_y?size-1>,</#if></#list>];
	    
	    var w_title = '本周会员动态比较';
	    var w_x_data = ['周一','周二','周三','周四','周五','周六','周日'];
	    var w_s_data = [<#list userCount.w as list>${list?c}<#if list_index!=userCount.w?size-1>,</#if></#list>];
	 	
	 	user_chart_2.setOption({
	    	title: {
	            textStyle: {
	                fontSize: 16
	            },
	            left:'10%'
	        },
	   		grid: {
	   	        left: '5%',
	   	        bottom: '1%',
	   	        top: '20%',
	   	        containLabel: true
	   	    },
			tooltip: {
			    trigger: 'axis'
			},
			toolbox: {
				right: '9%',
		        feature: {
		            myTool1: {
		                show: true,
		                title: '年动态比较',
		                icon: "image:///img/year.png",
		                onclick: function (){
		                    chart2(y_title, y_x_data, y_s_data);
		                }
		            },
		            myTool2: {
		                show: true,
		                title: '月动态比较',
		                icon: "image:///img/month.png",
		                onclick: function (){
		                    chart2(m_title, m_x_data, m_s_data);
		                }
		            },
		            myTool3: {
		                show: true,
		                title: '周动态比较',
		                icon: "image:///img/week.png",
		                onclick: function (){
		                    chart2(w_title, w_x_data, w_s_data);
		                }
		            }
		        }
		    },
			xAxis:  {
			    type: 'category',
			    data: []
			},
			yAxis: {
			    type: 'value',
			},
			series: [
			    {
			        name: '会员',
			        type:'line',
			        data:[]
			    }
			]
	    });
	    chart2(w_title, w_x_data, w_s_data);
	    
	    function chart2(title, x_data, s_data) {
	    	user_chart_2.setOption({
	    		title: {
	    	        text: title,
	    	    },
				xAxis:  {
				    data: x_data
				},
				series: [{
					data: s_data
				}]
	        });
	    }
	    
	    var user_chart_3 = echarts.init(document.getElementById('user_count_3'), 'macarons');
	    user_chart_3.setOption({
	    	color: ['#5793f3', '#d14a61', '#675bba'],
	        title : {
	            text: '页面流量统计',
	            left:'7%'
	        },
	        grid: {
	   	        left: '5%',
	   	     	bottom: '2%',
	   	        containLabel: true
	   	    },
	        tooltip : {
	            trigger: 'axis'
	        },
	        legend: {
	            data:['PV','UV','IP']
	        },
	        xAxis : [
	            {
	                type : 'category',
	                boundaryGap : false,
	                data : []
	            }
	        ],
	        yAxis : [
	            {
	                type : 'value'          
	            }
	        ],
	        series : [
	            {
	                name:'PV',
	                type:'line',
	                data:[],
	            },
	            {
	                name:'UV',
	                type:'line',
	                data:[],
	            },
	          	{
	                name:'IP',
	                type:'line',
	                data:[],
	            }
	        ]
	    });
	    
	    initDatePicker("startTime");
        initDatePicker("endTime");
        
        $("#statistics").on('click',function () {
            var startTime = $("#startTime").val();
            var endTime = $("#endTime").val();
            if (startTime == '' || endTime == '') {
            	layer.open({
					time : 2,
					content : '请选择日期！'
				})
            } else {
            	if (startTime > endTime) {
            		layer.open({
    					time : 2,
    					content : '开始日期不能大于结束日期！'
    				})
            	} else {
            		if (dateDiff(startTime, endTime) > 30) {
            			layer.open({
        					time : 2,
        					content : '时间间隔超过31天，请重新选择！'
        				})
            		} else {
            			initDateChart3(startTime, endTime);
            		}
            	}
            }
		});
        
        init_chart_3();

		function init_chart_3(){
			var now = new Date();
			var endTime = new Date(now).Format("yyyy-MM-dd");
			now.setDate(now.getDate()-30);
			var startTime = new Date(now).Format("yyyy-MM-dd");
	    	$("#startTime").val(startTime);
	    	$("#endTime").val(endTime);
	    	initDateChart3(startTime, endTime);
		}
		
		function initDateChart3(startTime, endTime){
			user_chart_3.setOption({
	    		title: {
	    	        subtext: startTime + '至' + endTime
	    	    }
	        });
			$.get('/manage/index/pageview', {
				startTime: startTime,
				endTime: endTime
	        }, function(json) {
	        	if (json.returnStatus.status == "0") {
	        		var x_data = json.data.x_data;
	        		var pv = json.data.pv;
	        		var uv = json.data.uv;
	        		var ip = json.data.ip;
	        		user_chart_3.setOption({
	        			xAxis : [{
	    			    	data : x_data
	    			    }],
	    			    series : [{
					        	name:'PV',
					            type:'line',
					            data: pv
					        },
				        {
				            name:'UV',
				            type:'line',
				            data:uv,
				         },
					         {
					            name:'IP',
					            type:'line',
					            data:ip,
					         }
					        ]
	    	        });
	        	}
	        });
		}
	});
	
	
	// 初始化没有分秒的时间框
    function initDatePicker(fieldId){
        $("#"+fieldId).datetimepicker({
            minView: "month", //选择日期后，不会再跳转去选择时分秒
            language: 'zh-CN',
            format: 'yyyy-mm-dd',
            initialDate: new Date(),
            autoclose: true,
            todayHighligh:true,
            todayBtn :true, // 显示今日按钮
            autoclose: 1
        })
    }

    // 两个日期的差值(d2 - d1).
    function dateDiff(d1, d2){
    	var day = 24 * 60 * 60 * 1000;
    	try{
    	    var dateArr = d1.split("-");
    	  	var checkDate = new Date();
    	    checkDate.setFullYear(dateArr[0], dateArr[1]-1, dateArr[2]);
    	  	var checkTime = checkDate.getTime();
    	 
	    	var dateArr2 = d2.split("-");
	    	var checkDate2 = new Date();
    	    checkDate2.setFullYear(dateArr2[0], dateArr2[1]-1, dateArr2[2]);
    	  	var checkTime2 = checkDate2.getTime();
    	  
    	  	var cha = (checkTime2 - checkTime)/day; 
    	    return cha;
    	}catch(e){
    	  	return false;
    	}
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
</script>
</body>
</html>