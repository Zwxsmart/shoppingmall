<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<title>个人中心</title>
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">

		<link rel="stylesheet" href="assets/mui1/css/mui.min.css">
		<link rel="stylesheet" href="css/master.css" />
		<!--自定义-->
		<link rel="stylesheet" href="css/mb_style.css" />

	</head>

	<body>
		
		<div class="mui-content">
			
			<div class="personal_group_log">
				<div class="brand_bg bg-blur"></div>
				<div class="personal_log">
					<img class="log" src="${(showPortrait)!''}" />
					<p class="name">${(user.nick)!''}</p>
				</div>
			</div>
			<div id="item1" class="mui-control-content mui-active">
				<div class="personal_cont g-m10">
					<div class="head_portrait">
						<p class="title">头像</p>
						<div class="head_portrait_img">
							<img src="${(showPortrait)!''}" />
						</div>
					</div>
				</div>
				<div class="personal_cont">
					<ul>
						<li>
							<div class="head_portrait">
								<p class="title">昵称</p>
								<div class="head_portrait_img">
									<p id="nick" class="title">${(user.nick)!''}</p>
								</div>
							</div>
						</li>
						<li>
							<div class="head_portrait">
								<p class="title">手机号</p>
								<div class="head_portrait_img">
									<p id="phone" class="title">${(user.phone)!''}</p>
								</div>
							</div>
						</li>
						<li>
							<div class="head_portrait">
								<p class="title">性别</p>
								<div class="head_portrait_img">
									<p id="gender" class="title">
									<#if (user.gender)??>
										<#if user.gender == 'M'>
											男
										<#elseif user.gender == 'F'>
											女
										</#if>
									</#if>
									</p>
								</div>
							</div>
						</li>
						<li>
							<div class="head_portrait">
								<p class="title">邮箱</p>
								<div class="head_portrait_img">
									<p id="email" class="title">${(user.email)!''}</p>
								</div>
							</div>
						</li>
					</ul>
					<button id="compile" class="mui-btn" type="button">编辑</button>
				</div>
			</div>
			<div id="item2" class="mui-control-content">
				<div class="personal_cont g-m10">
					<div class="head_portrait">
						<p class="title">头像</p>
                        <form id="action" name="upfile"  method="post" enctype="multipart/form-data">
							<div id="carouselPreview" class="ps-image" style="height:50px;width:50px;border:1px solid #CCC; background:url(${(showPortrait)!'/aw/img/add_img.png'}) no-repeat;background-size:100% 100%">
								<input type="file" id="carouselPicTitle" class="checked" name="upfile" value="${(user.portrait)!''}" onchange="chage(this)"  multiple="multiple" accept="image/*" style="filter:alpha(opacity=0);opacity:0;width:100%;height:100%;"/>
								<input type="hidden" id="carouselChangeId"/>
							</div>
						</form>
                        <#--<img href="#picture" class="mui-media-object mui-pull-left head-img" id="head-img" style="width: 30px;height: 30px" src="img/0.png">-->
						<#--<a href="#picture">打开</a>-->
                        <#--<button type="button" onclick="openb()">打开</button>-->
					</div>
				</div>
				<div class="personal_cont">
					<ul>
						<li>
							<div class="head_portrait">
								<p class="title">昵称</p>
								<div class="head_portrait_img">
									<input id="nick_" type="text" value="${(user.nick)!''}"/>
									<span class="mui-icon iconfont icon-xiezi"></span>
								</div>
							</div>
						</li>
						<li>
							<div class="head_portrait">
								<p class="title">手机号</p>
								<div class="head_portrait_img">									
									<input id="phone_" type="tel" value="${(user.phone)!''}"/>
									<span class="mui-icon iconfont icon-xiezi"></span>
								</div>
						
							</div>
						</li>
						<li>
							<div class="head_portrait">
								<p class="title">性别</p>
								<div class="head_portrait_img"style="display: flex;">
									<div class="mui-input-row mui-radio ">
										<label>男</label>
										<input name="radio1" type="radio" value="M" <#if ((user.gender)!'') == 'M'>checked</#if>>
									</div> 
									<div class="mui-input-row mui-radio ">
										<label>女</label>
										<input name="radio1" type="radio" value="F" <#if ((user.gender)!'') == 'F'>checked</#if>>
									</div> 
								</div>
							</div>
						</li>
						<li>
							<div class="head_portrait">
								<p class="title">邮箱</p>
								<div class="head_portrait_img">						
									<input id="email_" type="email" value="${(user.email)!''}"/>
									<span class="mui-icon iconfont icon-xiezi"></span>
								</div>
							</div>
						</li>
					</ul>
					<button id="save" class="mui-btn" type="button">保存</button>
				</div>
			</div>
		</div>
		<nav class="mui-bar mui-bar-tab box-shadow-1 my_bar_nav" >
			<a class="mui-tab-item " type="0">
				<span class="mui-icon iconfont icon-shouye"></span>
				<span class="mui-tab-label">首页</span>
			</a>
			<a class="mui-tab-item " type="1">
				<span class="mui-icon iconfont icon-guanyuwomen"></span>
				<span class="mui-tab-label">关于我们</span>
			</a>
			<a class="mui-tab-item " type="2">
				<span class="mui-icon iconfont icon-lianxi"></span>
				<span class="mui-tab-label">联系我们</span>
			</a>
			<a class="mui-tab-item mui-active" type="3">
				<span class="mui-icon iconfont icon-rgzx"></span>
				<span class="mui-tab-label">个人中心</span>
			</a>
		</nav>

        <div id="picture" class="mui-popover mui-popover-action mui-popover-bottom">
            <ul class="mui-table-view">
                <li class="mui-table-view-cell">
                    <a href="#">拍照或录像</a>
                </li>
                <li class="mui-table-view-cell">
                    <a href="#">选取现有的</a>
                </li>
            </ul>
            <ul class="mui-table-view">
                <li class="mui-table-view-cell">
                    <a href="#picture"><b>取消</b></a>
                </li>
            </ul>
        </div>
		<script type="text/javascript" src="assets/jquery-3.1.0.js"></script>
        <script type="text/javascript" src="js/jquery.form.js"></script>
		<script type="text/javascript" src="assets/mui1/js/mui.js"></script>
		<script type="text/javascript" src="js/nav.js"></script>
		<script type="text/javascript" src="js/jquery.cookie.js"></script>
		<script type="text/javascript" src="js/layer.js"></script>
		<script type="text/javascript" src="js/personal.js"></script>
		<script type="text/javascript" src="/js/statistical.js"></script>
		<script type="text/javascript">
			mui.init({
				swipeBack: true //启用右滑关闭功能
			});

			//打开编辑模块
			$(document).on("click","#compile",function(){
				$("#item1").removeClass("mui-active");
				$("#item2").addClass("mui-active");
			})
			//打开展示模块
			$(document).on("click","#save",function(){
				$("#item1").addClass("mui-active");
				$("#item2").removeClass("mui-active");				
			})

            function chage(obj){
                var file = obj.files[0];
                if(!/image\/\w+/.test(file.type)){
                    layer.msg("请确保文件为图像类型");
                    return false;
                }
                if(file.size >1048576){
                    layer.msg('文件不允许超过1M！')
                    return false;
                }
                if (typeof(FileReader) === 'undefined' ){
                    layer.msg("抱歉，你的浏览器不支持 FileReader，请使用现代浏览器操作！");
                    return false;
                }else {
                    $carouselLogoImg=document.getElementById("carouselPicTitle");
                    $carouselPreview=document.getElementById("carouselPreview" );
                    readFile($carouselLogoImg, $carouselPreview, "carouselChangeId", file)
                }
            }

            function readFile(logoImg, preview, changeId, file){
                //这里我们判断下类型如果不是图片就返回 去掉就可以上传任意文件
                if(logoImg.value==''){
                    preview.style.height='50px';
                    preview.style.width='50px';
                    preview.style.border='1px solid #CCC';
                    preview.style.background="url('/aw/img/add_img.png') no-repeat";
                    preview.style.backgroundSize='100% 100%';
                }else{
                    $("#"+changeId).val("change");
                    var reader = new FileReader();
                    reader.readAsDataURL(file);///
                    reader.onload = function(e){
                        preview.style.height='50px';
                        preview.style.width='50px';
                        preview.style.border='1px solid #CCC';
                        preview.style.background="url('"+this.result+"') no-repeat";
                        preview.style.backgroundSize='100% 100%';
                    }
                }
            }
//
//            function openb() {
//                mui.plusReady(function() {
//                    alert("..")
//                })
//            }

            //				mui.plusReady(function() {
//					/*点击头像触发*/
//					alert("..")
//					document.getElementById('head-img').addEventListener('tap', function() {
//						alert("...")
//						if(mui.os.ios||(mui.os.android&&parseFloat(mui.os.version)<4.4)){
//						alert("window.plus:"+window.plus)
//							alert("plus:"+plus)
//							var a = [{
//								title: "拍照"
//							}, {
//								title: "从手机相册选择"
//							}];
//							plus.nativeUI.actionSheet({
//								title: "修改用户头像",
//								cancel: "取消",
//								buttons: a
//							}, function(b) { /*actionSheet 按钮点击事件*/
//								switch(b.index) {
//									case 0:
//										break;
//									case 1:
//										getImage(); /*拍照*/
//										break;
//									case 2:
//										galleryImg(); /*打开相册*/
//										break;
//									default:
//										break;
//								}
//							})
//						}
//					});
//				})
//
//
//            //拍照
//            function getImage() {
//                var cmr = plus.camera.getCamera();
//                var res = cmr.supportedImageResolutions[0];
//                var fmt = cmr.supportedImageFormats[0];
//                cmr.captureImage(function(path) {
//                    //plus.io.resolveLocalFileSystemURL(path, function(entry) {
//                    plus.io.resolveLocalFileSystemURL(path, function(entry) {
//                        var localUrl = entry.toLocalURL();
//                        uploadHead(localUrl + "?version=" + new Date().getTime());
//                    }, function(err) {
//                        console.error("拍照失败：" + err.message);
//                    }, {
//                        index: 1
//                    });
//                });
//            }
//            //本地相册选择
//            function galleryImg() {
//                plus.gallery.pick(function(a) {
//                    plus.io.resolveLocalFileSystemURL(a, function(entry) {
//                        plus.io.resolveLocalFileSystemURL("_doc/", function(root) {
//                            root.getFile("head.png", {}, function(file) {
//                                //文件已存在
//                                file.remove(function() {
//                                    console.log("file remove success");
//                                    entry.copyTo(root, 'head.png', function(e) {
//                                        var e = e.fullPath + "?version=" + new Date().getTime();
//                                        uploadHead(e); /*上传图片*/
//                                        //变更大图预览的src
//                                        //目前仅有一张图片，暂时如此处理，后续需要通过标准组件实现
//                                    },function(e) {
//                                        console.log('copy image fail:' + e.message);
//                                    });
//                                }, function() {
//                                    console.log("delete image fail:" + e.message);
//                                });
//                            }, function() {
//                                //文件不存在
//                                entry.copyTo(root, 'head.png', function(e) {
//                                    var path = e.fullPath + "?version=" + new Date().getTime();
//                                    uploadHead(path); /*上传图片*/
//                                },function(e) {
//                                    console.log('copy image fail:' + e.message);
//                                });
//                            });
//                        }, function(e) {
//                            console.log("get _www folder fail");
//                        })
//                    }, function(e) {
//                        console.log("读取拍照文件错误：" + e.message);
//                    });
//                }, function(a) {}, {
//                    filter: "image"
//                })
//            };
//
//            //上传头像图片 B5教程网 www.bcty365.com
//            function uploadHead(imgPath) {
//                var image = new Image();
//                image.src = imgPath;
//                image.onload = function() {
//                    var imgData = getBase64Image(image);
//                    console.log(imgData);
//                    /*在这里调用上传接口*/
//                    //mui.ajax("图片上传接口", {
//                    //data: {
//                    //img: imgData
//                    //},
//                    //dataType: 'json',
//                    //type: 'post',
//                    //timeout: 10000,
//                    //success: function(data) {
//                    //mui.toast('上传成功',{
//                    //duration:'long',
//                    //type:'div'
//                    //});
//                    //document.getElementById('head-img').src = imgPath;
//                    //document.getElementById('head-img1').src = imgPath;
//                    //document.getElementById('head-img2').src=imgPath;
//                    //},
//                    //error: function(xhr, type, errorThrown) {
//                    //mui.toast('网络异常，请稍后再试！');
//                    //}
//                    //});
//                }
//            }
//            //将图片压缩转成base64
//            function getBase64Image(img) {
//                var canvas = document.createElement("canvas");
//                var width = img.width;
//                var height = img.height;
//                // calculate the width and height, constraining the proportions
//                if(width > height) {
//                    if(width > 100) {
//                        height = Math.round(height *= 100 / width);
//                        width = 100;
//                    }
//                } else {
//                    if(height > 100) {
//                        width = Math.round(width *= 100 / height);
//                        height = 100;
//                    }
//                }
//                canvas.width = width; /*设置新的图片的宽度*/
//                canvas.height = height; /*设置新的图片的长度*/
//                var ctx = canvas.getContext("2d");
//                ctx.drawImage(img, 0, 0, width, height); /*绘图*/
//                var dataURL = canvas.toDataURL("image/png", 0.8);
//                return dataURL.replace("data:image/png;base64,", "");
//            }

		</script>

	</body>
</html>