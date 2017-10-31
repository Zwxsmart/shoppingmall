<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>家博城电脑端</title>
    <meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, width=device-width">
    <link rel="stylesheet" href="/aw/css/bootstrap.min.css">
<#--<link rel="stylesheet" href="/bs/css/bootstrap-theme.min.css">-->
    <script type="text/javascript" src="/aw/js/jquery-3.1.0.js"></script>
    <script type="text/javascript" src="/aw/js/jquery.form.js"></script>
    <script src="/aw/js/bootstrap.min.js"></script>
    <script src="/aw/assets/layer/layer.js"></script>
    <script type="text/javascript" charset="utf-8" src="/aw/assets/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="/aw/assets/ueditor/ueditor.all.js"> </script>
    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <script type="text/javascript" charset="utf-8" src="/aw/assets/ueditor/lang/zh-cn/zh-cn.js"></script>
    <style>
        .clear {zoom:1;}
        .clear:after{clear:both; content:"."; display:block; height:0; visibility:hidden;}
        .my_form-group{margin:0; padding-top:10px;}
        .my_form-group .control-label{display:block; height: 50px; line-height: 50px; text-align: left; font-size:1.5em; float:left; width:50%; padding:0px !important;}
    </style>
</head>
<body class="ember-application" style="text-align: center;margin-left:auto; margin-right:auto;" >
<div class="form-horizontal" role="form">
    <div >
        <div class="form-group clear my_form-group" style="padding-left: 20px;">
            <label class="control-label">家博城电脑端轮播广告：</label>
            <button class="btn btn-primary" id="addBtn1">添加轮播图</button>
            <button class="btn btn-success" onclick="keepAll(${headerInfo?size})">保存所有更改</button>
        </div>
    </div>
    <!-- 轮播图 -->
    <div class="clear" style="border:1px solid #dddddd; padding:10px 10px 0 10px;" id="addIndeximg">
        <#if (headerInfo?size>0)>
            <#list headerInfo as c>
                <div class="form-group col-md-12 " style="border:1px solid #eee; padding:10px;">
                    <div class="row">
                        <div class="col-md-1 col-sm-1">
                            <label for="title" class="control-label" style="float: left;padding-left: 10px;">图${c_index+1}：</label>
                        </div>
                        <div class="col-md-2 col-sm-3">
                            <form id="carouselAction${c_index}" name="upfile"  method="post" enctype="multipart/form-data">
                                <div id="carouselPreview${c_index}" class="ps-image" style="height:130px;border:1px solid #CCC; background:url(${(c.showPicUrl)!'/aw/img/add_img.png'}) no-repeat;background-size:100% 100%">
                                    <input type="file" id="carouselPicTitle${c_index}" class="checked" name="upfile" value="${(c.picUrl)!''}" onchange="chage(${c_index}, this)" accept="image" style="filter:alpha(opacity=0);opacity:0;width:100%;height:100%;"/>
                                    <input type="hidden" id="carouselChangeId${c_index}"/>
                                </div>
                            </form>
                        </div>
                        <div class="col-md-4 col-sm-4">
                            <div class="form-group" style="float: left;">
                                <label class="control-label" >跳转链接：</label>
                                <input type="text" class="form-control" placeholder="跳转链接" style="width: 355px;" maxlength="200" value="${(c.gotoUrl)!''}" id="carouselGotoUrl${c_index}">
                            </div>
                            <label style="font-weight:100;font-size:8px;width:100%; text-align: left;">←点击左侧文件框上传图片, 图片大小为1M之内, 建议宽*高为1920*630或3:1</label>
                        </div>
                        <div class="col-md-4 col-sm-4">
                            <div class="form-group">
                                <label class="control-label" >内容：</label>
                                <textarea class="form-control" placeholder="内容" maxlength="200" minlength="1" style="width: 90%;height:110px;" id="carouselTitle${c_index}">${(c.title)!''}</textarea>
                            </div>
                        </div>
                        <div class="col-md-1 col-sm-1">
                            <button class="btn btn-danger radius" onclick="status(${c_index})" value="${(c.id)!''}" id="carouselId${c_index}">删除</button>
                        </div>
                        <div class="col-md-2 col-sm-2">
                        </div>
                    </div>
                </div>
            </#list>
            <#else>
                <label class="control-label" style="font-size: 20px;">暂无轮播图</label>
        </#if>
    </div>
</div>
</body>
<script type="text/javascript">
    $(document).ready(function() {

    } );

    function chage(index, obj){
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
            $carouselLogoImg=document.getElementById("carouselPicTitle" + index);
            $carouselPreview=document.getElementById("carouselPreview" + index);
            readFile($carouselLogoImg, $carouselPreview, "carouselChangeId" + index, file)
        }
    }


    function readFile(logoImg, preview, changeId, file){
        //这里我们判断下类型如果不是图片就返回 去掉就可以上传任意文件
        if(logoImg.value==''){
            preview.style.height='130px';
            preview.style.border='1px solid #CCC';
            preview.style.background="url('/aw/img/add_img.png') no-repeat";
            preview.style.backgroundSize='100% 100%';
        }else{
            $("#"+changeId).val("change");
            var reader = new FileReader();
            reader.readAsDataURL(file);
            reader.onload = function(e){
                preview.style.height='130px';
                preview.style.border='1px solid #CCC';
                preview.style.background="url('"+this.result+"') no-repeat";
                preview.style.backgroundSize='100% 100%';
            }
        }
    }

    function refresh() {
        setTimeout(function(){
            location.replace(location.href);
        },800);
    }

    function keepAll(size) {
        var addIndex = $("#addIndeximg").children();
        for(var i =0; i<size; i++){
            var gotoUrl = $("#carouselGotoUrl"+i).val();
            var id = $("#carouselId"+i).val();
            var title = $("#carouselTitle"+i).val();
            if(gotoUrl!=undefined&&gotoUrl!=""&&id!=undefined&&id!=""&&title!=undefined&&title!=""){// 判断原始轮播图是否完整
                if(addIndex.length>size){
                    for(var k=size;k<addIndex.length;k++){// 判断新增轮播图是否完整
                        var newgotoUrl = $("#carouselGotoUrl"+k).val();
                        var newtitle = $("#carouselTitle"+k).val();
                        if(newgotoUrl!=undefined&&newgotoUrl!=""&&newtitle!=undefined&&newtitle!=""&&$("#carouselChangeId"+k).val()!=''&&$("#carouselChangeId"+k).val()!=undefined&&$("#carouselChangeId"+k).val()!=null){
                        }else{
                            layer.msg('轮播图'+(k+1)+'数据不完整!')
                            return false;
                        }
                    }
                }
            }else{
                layer.msg('轮播图'+(i+1)+'数据不完整!')
                return false;
            }
        }
        for(var i =0; i<size; i++){
            var gotoUrl = $("#carouselGotoUrl"+i).val();
            var id = $("#carouselId"+i).val();
            var title = $("#carouselTitle"+i).val();
                if($("#carouselChangeId"+i).val()!=''&&$("#carouselChangeId"+i).val()!=undefined&&$("#carouselChangeId"+i).val()!=null){
                    $("#carouselAction"+i).ajaxSubmit({
                        type: 'post',
                        dataType: "jsonp",
                        async: false,
                        url: "/ueditor/controller?action=uploadimage",
                        success: function(data) {
                            if(data.state=='SUCCESS'){
                                var picUrl = data.url;
                                $.post('/manage/webSetting/updateCarousel1',{id:id,gotoUrl:gotoUrl, picUrl:picUrl, title:title},function (res) {
                                    if(res.count ==1){
                                    }else{
                                        layer.msg('修改轮播图'+(i+1)+'数据时失败！');
                                    }
                                });
                            }else{
                                layer.msg('修改轮播图'+(i+1)+'数据时失败！');
                            }
                        },
                        error : function(XmlHttpRequest, textStatus, errorThrown){
                            layer.msg('修改轮播图'+(i+1)+'数据时失败！');
                        }
                    })
                }else{
                    $.post('/manage/webSetting/updateCarousel1',{id:id,gotoUrl:gotoUrl,title:title},function (res) {
                        if(res.count ==1){
                        }else{
                            layer.msg('修改轮播图'+(i+1)+'数据时失败！');
                        }
                    });
                }
        }
        if(addIndex.length>size){
            for(var k=size;k<addIndex.length;k++){
                var gotoUrl = $("#carouselGotoUrl"+size).val();
                var title = $("#carouselTitle"+size).val();
                        $("#carouselAction"+size).ajaxSubmit({
                            type: 'post',
                            dataType: "jsonp",
                            async: false,
                            url: "/ueditor/controller?action=uploadimage",
                            success: function(data) {
                                if(data.state=='SUCCESS'){
                                    var picUrl1 = data.url;
                                    $.post('/manage/webSetting/insertCarousel',{gotoUrl:gotoUrl, picUrl:picUrl1, type:"6", typeName:"家博城电脑端", title:title},function (res) {
                                        if(res.count ==1){
                                        }else{
                                            layer.msg('新增轮播图'+(k+1)+'数据时失败！');
                                        }
                                    });
                                }else{
                                    layer.msg('新增轮播图'+(k+1)+'数据时失败！');
                                }
                            },
                            error : function(XmlHttpRequest, textStatus, errorThrown){
                                layer.msg('新增轮播图'+(k+1)+'数据时失败！');
                            }
                        })
            }
        }
        layer.msg('保存成功！', {time : 1000});
        refresh()
    }

    function status(index) {
        var addIndex = $("#addIndeximg").children();
        if(addIndex.length==1){
            layer.msg('最少要有一张轮播图！');
        }else{
            var id =$("#carouselId"+index).val();
            if(id!=undefined&&id!=""){
                $.post("/manage/webSetting/headerInfoStatus?id="+id, function(res){
                    if(res.count ==1){
                        layer.msg('删除成功！', {time : 1000});
                        refresh()
                    }else{
                        layer.msg('删除失败！', {time : 1000});
                    }
                },"json");
            }
        }
    }

    //添加首页轮播广告
    var addIndexImg = {
        addIndeximg:function(c_index){
            var imgUrl = "/aw/img/add_img.png";
            var html = "<div class='form-group col-md-12 ' style='border:1px solid #eee; padding:10px;'><div class='row'><div class='col-md-1 col-sm-1'>";
            html +=" <label for='title' class='control-label' style='float: left;padding-left: 10px;'>图"+((+c_index)+1)+"：</label> </div>";
            html +=" <div class='col-md-2 col-sm-3'><form id='carouselAction"+c_index+"' name='upfile'  method='post' enctype='multipart/form-data'>";
            html +=" <div id='carouselPreview"+c_index+"' class='ps-image' style='height:130px;border:1px solid #CCC; background:url("+imgUrl+") no-repeat;background-size:100% 100%'>";
            html +=" <input type='file' id='carouselPicTitle"+c_index+"' class='checked' name='upfile' onchange='chage("+c_index+", this)' accept='image' style='filter:alpha(opacity=0);opacity:0;width:100%;height:100%;'/>";
            html +=" <input type='hidden' id='carouselChangeId"+c_index+"'/></div></form></div>";
            html +=" <div class='col-md-4 col-sm-4'><div class='form-group' style='float: left;'>";
            html +=" <label class='control-label'>跳转链接：</label><input type='text' class='form-control' placeholder='跳转链接' style='width: 355px; maxlength=200' id='carouselGotoUrl"+c_index+"'></div>";
            html +=" <label style='font-weight:100;font-size:8px;width:100%; text-align: left;'>←点击左侧文件框上传图片, 图片大小为1M之内, 建议宽*高为1920*630或3:1</label></div>";
            html +=" <div class='col-md-4 col-sm-4'><div class='form-group'><label class='control-label'>内容：</label>";
            html +=" <textarea class='form-control' placeholder='内容' maxlength='200' minlength='1' style='width: 90%;height:110px;' id='carouselTitle"+c_index+"'></textarea></div></div>";
            html +=" <div class='col-md-2 col-sm-2'> </div>";
            $("#addIndeximg").append(html);
        },
    }

    //添加按钮
    $(document).on("click","#addBtn1",function(){
        var addIndex = $("#addIndeximg").children();
        if(addIndex.length<10){
            addIndexImg.addIndeximg(addIndex.length);
        }else{
            layer.msg('轮播图图片不能超过10张!')
        }
    })
</script>
</html>