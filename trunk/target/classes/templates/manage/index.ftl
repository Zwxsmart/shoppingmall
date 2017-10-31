<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title> 联盟国际后台管理 | 首页 </title>
    <!-- Bootstrap core CSS -->
    <link href="/aw/css/bootstrap.min.css" rel="stylesheet">
    <link href="/aw/css/bootstrap-reset.css" rel="stylesheet">
    <!--external css-->
    <link href="/aw/assets/font-awesome/css/font-awesome.css" rel="stylesheet"/>
    <link rel="stylesheet" href="/aw/css/bundle.min.css">
    <!-- Custom styles for this template -->
    <link href="/aw/css/style.css" rel="stylesheet">
    <link href="/aw/css/style-responsive.css" rel="stylesheet"/>
    <!-- css for echarts -->
    <link href="/aw/css/charts.css" rel="stylesheet"/>
    <!-- tabs style -->
    <link rel="stylesheet" href="/aw/assets/bootstrap-addtabs/css/bootstrap-addtabs.css"/>
    <!--[if lte IE 9]>
    <link rel="stylesheet" href="/aw/ie-blocker/ie-blocker.css">
    <script src="/aw/ie-blocker/ie-blocker.zhCN.js"></script>
    <![endif]-->
    <script type="text/javascript" src="/aw/js/jquery.js"></script>
    <script type="text/javascript" src="/aw/js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="/aw/js/bootstrap.min.js"></script>
    <style>
        .col-center-block {
            float: none;
            display: block;
            margin-left: auto;
            margin-right: auto;
        }

        .center {
            position: absolute;
            top: 50%;
            -webkit-transform: translateY(-50%);
            -moz-transform: translateY(-50%);
            -ms-transform: translateY(-50%);
            -o-transform: translateY(-50%);
            transform: translateY(-50%);
        }

        .helpDiv {
            background-color: #f1f2f7;
            border: 0px;
        }

        .sideDiv {
            margin-left: -15px;
            margin-right: -15px;
        }

        .myPading {
            padding-left: 0;
            padding-right: 0;
            padding-top: 10px;
            padding-bottom: 10px;
        }
        .panel-body {
        	text-align: center;
        }
        .charts {
		    width: 610px;
		    height: 170px;
		}
		/**/
        .menu_list{width:100%;margin:0 auto;margin-top:75px;}
        .menu_head{
            height: 47px;
            line-height: 47px;
            padding-left: 38px;
            font-size: 14px;
            color: #525252;
            cursor: pointer;
            border-left: 1px solid #e1e1e1;
            border-right: 1px solid #e1e1e1;
            border-bottom: 1px solid #e1e1e1;
            border-top: 1px solid #F1F1F1;
            position: relative;
            margin: 0px;
            font-weight: bold;
            background: #f1f1f1 url(/aw/img/pro_left.png) center right no-repeat;
        }
        .menu_list .current{background:#f1f1f1 url(/aw/img/pro_down.png) center right no-repeat;}
        .menu_body{
            line-height: 38px;
            border-left: 1px solid #e1e1e1;
            backguound: #fff;
            border-right: 1px solid #e1e1e1;
        }
        .menu_body a ,.my_menu_head >a{display:block;height:38px;line-height:38px;padding-left:38px;color:#777777;background:#fff;text-decoration:none;border-bottom:1px solid #e1e1e1;}
        .menu_body a:hover ,.my_menu_head >a:hover{text-decoration:none; background:#dddddd;}
        .menu_body .on_click ,.my_menu_head .on_click{background:#dddddd;}
		.my_menu_head { background: #f1f1f1; padding:0px; }
        .my_menu_head >a{line-height: 45px; height: 45px; background: #f1f1f1; color:#525252; padding-left:38px;border:none; text-align: left;}
		.img_w {width: 100px;}
    </style>
</head>

<body>
<section id="container" style="display: none;overflow:hidden;">
    <!--header start-->
	<#include "header.ftl">
    <!--header end-->
    <!--sidebar start-->
    <aside>
        <div id="sidebar" class="nav-collapse ">
            <!-- 代码部分begin -->
            <div id="firstpane" class="menu_list">
                <h3 class="menu_head my_menu_head"><a data-addtab="1" url="/manage/statistics">数据统计</a></h3>
                <h3 class="menu_head my_menu_head"><a data-addtab="2" url="/manage/user/userPage">会员管理</a></h3>
                <h3 class="menu_head my_menu_head"><a data-addtab="3" url="/manage/store/storePage">店铺管理</a></h3>
                <#--<h3 class="menu_head my_menu_head"><a data-addtab="4" url="/manage/webSetting/activity1">热门活动</a></h3>-->
                <h3 class="menu_head my_menu_head"><a data-addtab="5" url="/manage/webSetting/sell">火热销售</a></h3>
                <#--<h3 class="menu_head my_menu_head"><a data-addtab="26" url="/qrCodeImg">二维码</a></h3>-->
                <#--<h3 class="menu_head my_menu_head"><a data-addtab="27" url="/manage/activity/queryCheckPage?releasePointId=1&campaignId=1">登记页</a></h3>-->
                <h3 class="menu_head">营销管理</h3>
                <div style="display:none" class="menu_body">
                    <a data-addtab="24" url="/manage/webSetting/queryPoint">投放点</a>
                    <a data-addtab="23" url="/manage/webSetting/activity2">活动列表</a>
                    <a data-addtab="25" url="/manage/webSetting/activityCount">统计分析</a>
                </div>
                <h3 class="menu_head">商务合作</h3>
                <div style="display:none" class="menu_body">
                    <a data-addtab="18" url="/manage/webSetting/businessEdu">商务百问</a>
                    <a data-addtab="19" url="/manage/webSetting/formatsDivision">业态划分</a>
                    <a data-addtab="20" url="/manage/webSetting/businessConsulting">商务咨询</a>

                </div>
                <h3 class="menu_head">居然之家</h3>
                <div style="display:none" class="menu_body">
                    <a data-addtab="21" url="/manage/webSetting/activity">热门活动</a>
                    <a data-addtab="22" url="/manage/webSetting/actually">联系我们</a>
                </div>
                <h3 class="menu_head">轮播图设置</h3>
                <div style="display:none" class="menu_body">
                    <a data-addtab="6" url="/manage/webSetting/indexSettingPage">官网首页</a>
                    <a data-addtab="7" url="/manage/webSetting/actuallyPage">居然之家</a>
                    <a data-addtab="9" url="/manage/webSetting/jiaBoPCPage">家博城电脑端</a>
                    <a data-addtab="10" url="/manage/webSetting/jiaBoMBPage">家博城手机端</a>
                    <a data-addtab="8" url="/manage/webSetting/furnitureFactory">家具工厂手机端</a>
                </div>
                <h3 class="menu_head">关于我们</h3>
                <div style="display:none" class="menu_body">
                    <a data-addtab="11" url="/manage/webSetting/groupIntroduction">集团简介</a>
                    <a data-addtab="12" url="/manage/webSetting/groupDynamic">集团动态</a>
                    <a data-addtab="13" url="/manage/webSetting/enterpriseCulture">企业文化</a>
                    <a data-addtab="14" url="/manage/webSetting/enterpriseHonor">企业荣誉</a>
                    <a data-addtab="16" url="/manage/webSetting/inviteJob">人才招聘</a>
                    <a data-addtab="17" url="/manage/webSetting/contactUs">联系我们</a>
                </div>
            </div>
        </div>
    </aside>

    <!--sidebar end-->
    <section id="main-content" style="overflow-y:auto;">
        <section class="wrapper" style="padding-right:0;">
            <div id="container0" class="container-fluid" style="padding-left:0;padding-right:0;">
                <!-- Nav tabs -->
                <ul class="nav nav-tabs" role="tablist">
                    <li role="presentation" class="active">
                        <a href="#home" aria-controls="home" role="tab" data-toggle="tab">后台主页</a></li>
                </ul>
                <!-- Tab panes -->
                <div class="tab-content">
	                <div role="tabpanel" class="tab-pane active" id="home">

						<iframe frameborder=0 width="100%" height="900" style="overflow-y: auto;" marginheight=0 marginwidth=0 src="/manage/index/welcome"></iframe>
                    </div>
                </div>
            </div>
        </section>
    </section>
</section>
    <!-- js placed at the end of the document so the pages load faster -->
    <!--common script for all pages-->
    <script src="/aw/assets/bootstrap-tables/js/jquery.dataTables.min.js"></script>
    <script src="/aw/assets/bootstrap-tables/js/dataTables.bootstrap.min.js"></script>

    <!-- echarts and theme -->
    <script src="/aw/js/echarts.js"></script>
    <script src="/aw/js/macarons.js"></script>

    <script src="/aw/js/jquery.customSelect.min.js"></script>
    <!-- tabs style -->
    <script src="/aw/assets/bootstrap-addtabs/js/bootstrap-addtabs.js"></script>

    <script>
        $(function () {
            $('select.styled').customSelect();
            setTimeout(function () {
                $('#loader').hide();
                $('#container').css('display', 'block');
            }, 0);
            $('#container0').addtabs({monitor: '.nav-collapse'});
        });

		//---
        $(document).ready(function(){
            //$("#firstpane .menu_body:eq(0)").show();
            $("#firstpane h3.menu_head").click(function(){
                $(this).addClass("current").next("div.menu_body").slideToggle(300).siblings("div.menu_body").slideUp("slow");
                $(this).siblings().removeClass("current");
            });

			$("div.menu_body > a").on("click",function(){
				$(".on_click").removeClass("on_click");
				$(this).addClass("on_click");
			})

			$("h3.my_menu_head >a").on("click",function(){
                $(".on_click").removeClass("on_click");
                $(this).addClass("on_click");
            })
        });
	</script>
</body>
</html>
