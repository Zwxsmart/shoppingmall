<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>首页设置</title>
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
            <label class="control-label">首页轮播广告：</label>
            <button class="btn btn-primary" id="addBtn1" type="button">添加首页轮播图</button>
            <button class="btn btn-success" onclick="keepAll(${headerInfo.carousel?size});" type="button">保存所有更改</button>
        </div>
    </div>
    <!-- 轮播图 -->
    <div class="clear" style="border:1px solid #dddddd; padding:10px 10px 0 10px;" id="addIndeximg">
        <#if (headerInfo.carousel?size>0)>
            <#list headerInfo.carousel as c>
                <div class="form-group col-md-12 " style="border:1px solid #eee; padding:10px;">
                    <div class="row">
                        <div class="col-md-1 col-sm-1">
                            <label class="control-label" style="float: left;padding-left: 10px;">图${c_index+1}：</label>
                        </div>
                        <div class="col-md-2 col-sm-3">
                            <form id="carouselAction${c_index}" name="upfile"  method="post" enctype="multipart/form-data">
                                <div id="carouselPreview${c_index}" class="ps-image" style="height:130px;border:1px solid #CCC; background:url(${(c.showPicUrl)!'/aw/img/add_img.png'}) no-repeat;background-size:100% 100%;">
                                    <input type="file" id="carouselPicTitle${c_index}" class="checked" name="upfile" value="${(c.picUrl)!''}" onchange="chage(${c_index}, this)" accept="image" style="filter:alpha(opacity=0);opacity:0;width:100%;height:100%;"/>
                                    <input type="hidden" id="carouselChangeId${c_index}"/>
                                </div>
                            </form>
                        </div>
                        <div class="col-md-4 col-sm-4">
                            <div class="form-group" style="float: left; >
                                <label class="control-label">跳转链接：</label>
                                <input type="text" class="form-control" placeholder="跳转链接"  maxlength="200" value="${(c.gotoUrl)!''}" id="carouselGotoUrl${c_index}">
                            </div>
                            <label style="font-weight:100;font-size:8px;width:100%; text-align: left;">←点击左侧文件框上传图片, 图片大小为1M之内, 建议宽*高为1920*630或3:1</label>
                        </div>
                        <div class="col-md-2 col-sm-1">
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
<div >
   <div class="form-group clear my_form-group" style="padding-left: 20px;">
       <label class="control-label">四个专区板块：</label>
   </div>
</div>
    <!-- 模块 -->
<div class="clear" style="border:1px solid #dddddd; padding:10px 10px 0 10px;" id="addModuleImg">
<#if (headerInfo.prefecture)??>
    <#list headerInfo.prefecture as p>
     <div class="form-group col-md-12 " style="border:1px solid #eee; padding:10px;">
        <div class="row">
            <div class="col-md-1 col-sm-1">
                <label for="title" class="control-label" style="float: left;padding-left: 10px;">左${p_index+1}：</label>
            </div>
            <input type="hidden" value="${(p.id)!''}" id="prefectureId${p_index}"/>
            <div class="col-md-2 col-sm-3">
                <form id="prefectureAction${p_index}" name="upfile"  method="post" enctype="multipart/form-data">
                    <div id="prefecturePreview${p_index}" class="ps-image" style="width:130px;height:130px;border:1px solid #CCC; background:url(${(p.showPicUrl)!'/aw/img/add_img.png'}) no-repeat;background-size:130px 130px"">
                         <input type="file" id="prefecturePicTitle${p_index}" class="checked" name="upfile" value="${(p.picUrl)!''}" onchange="chage1(${p_index}, this)" accept="image" style="filter:alpha(opacity=0);opacity:0;width:100%;height:100%;"/>
                         <input type="hidden" id="prefectureChangeId${p_index}"/>
                    </div>
                </form>
            </div>
            <div class="col-md-5 col-sm-5">
                <div class="form-group" style="float: left; margin-top: 10px;">
                    <label class="control-label">标题：</label>
                    <input type="text" class="form-control" placeholder="标题"  value="${(p.title)!''}" maxlength="100" id="prefectureTitle${p_index}">
                </div>
                <label style="font-weight:100;font-size:8px;width:100%; text-align: left;">←点击左侧文件框上传图片, 图片大小为1M之内, 建议宽*高为270*180或3:2</label>
            </div>
        </div>
     </div>
    </#list>
</#if>
</div>
    <!-- 二维码 -->
<div class="form-group clear my_form-group" style="padding-left: 20px;">
    <label class="control-label">公众号二维码：</label>
</div>
<div class="clear" style="border:1px solid #dddddd; padding:10px 10px 0 10px;">
<#if (headerInfo.qrCode)??>
    <#list headerInfo.qrCode as q>
    <div class="form-group col-md-12" style="border:1px solid #EEEEEE; padding:10px;">
        <div class="form-group" style="float: left; margin-left: 40px">
            <input type="hidden" value="${(q.id)!''}" id="qrCodeId"/>
            <form id="qrCodeAction" name="upfile"  method="post" enctype="multipart/form-data">
                <div id="qrCodePreview" class="ps-image" style="width:130px;height:130px;border:1px solid #CCC; background:url(${(q.showPicUrl)!'/aw/img/add_img.png'}) no-repeat;background-size:130px 130px">
                    <input type="file" id="qrCodePicTitle" class="checked" name="upfile" value="${(q.picUrl)!''}" accept="image" onchange="chage2(this)" style="filter:alpha(opacity=0);opacity:0;width:100%;height:100%;"/>
                    <input type="hidden" id="qrCodeChangeId"/>
                </div>
            </form>
        </div>
        <label style="font-weight:100;font-size:8px;text-align: left;float: left;margin-left: 20px;">←点击左侧文件框上传图片, 图片大小为1M之内, 建议宽*高为1:1</label>
    </div>
    </#list>
</#if>
</div>
</body>
<script type="text/javascript">
    $(document).ready(function() {

    } );

    function showPrefecture(){
        layer.open({
            type: 2,
            title: '专区内轮播图设置',
            shadeClose: false,
            shade: 0.8,
            area: ['1500px', '100%'],
            content: "/manage/webSetting/showPrefecture", //iframe的url
//            end: function () {
//                location.reload();
//            }
        });
    }

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

    function chage1(index, obj){
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
            $carouselLogoImg=document.getElementById("prefecturePicTitle" + index);
            $carouselPreview=document.getElementById("prefecturePreview" + index);
            readFile($carouselLogoImg, $carouselPreview, "prefectureChangeId" + index, file)
        }
    }

    function chage2(obj){
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
            $carouselLogoImg=document.getElementById("qrCodePicTitle");
            $carouselPreview=document.getElementById("qrCodePreview");
            readFile($carouselLogoImg, $carouselPreview, "qrCodeChangeId", file)
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
        for(var i =0; i<size; i++){ // 先判断所有数据是否完整
            var gotoUrl = $("#carouselGotoUrl"+i).val();
            var id = $("#carouselId"+i).val();
            if(gotoUrl!=undefined&&gotoUrl!=""&&id!=undefined&&id!=""){// 判断原始轮播图是否完整
                if(addIndex.length>size){
                    for(var k=size;k<addIndex.length;k++){// 判断新增轮播图是否完整
                        var newgotoUrl = $("#carouselGotoUrl"+size).val();
                        if(newgotoUrl!=undefined&&newgotoUrl!=""&&$("#carouselChangeId"+size).val()!=''&&$("#carouselChangeId"+size).val()!=undefined&&$("#carouselChangeId"+size).val()!=null){
                        }else{
                            layer.msg('轮播图'+(k+1)+'数据不完整!')
                            return false;
                        }
                    }
                }
                for(var index=0;index<4;index++){// 判断专区数据是否完整
                    var prefecturetitle = $("#prefectureTitle"+index).val();
                    var prefectureid = $("#prefectureId"+index).val();
                    if(prefecturetitle!=undefined&&prefecturetitle!=""&&prefectureid!=undefined&&prefectureid!=""){
                        var qrCodeid = $("#qrCodeId").val();
                        if(qrCodeid!=undefined&&id!=""){
                        }else{
                            layer.msg('二维码数据不完整!')
                            return false;
                        }
                    }else{
                        layer.msg('专区板块左'+(index+1)+'数据不完整!')
                        return false;
                    }
                }
            }else{
                layer.msg('轮播图'+(i+1)+'数据不完整!')
                return false;
            }
        }
        for(var j =0; j<size; j++){// 再保存数据
                if($("#carouselChangeId"+j).val()!=''&&$("#carouselChangeId"+j).val()!=undefined&&$("#carouselChangeId"+j).val()!=null){
                    $("#carouselAction"+j).ajaxSubmit({
                        type: 'post',
                        dataType: "jsonp",
                        async: false,
                        url: "/ueditor/controller?action=uploadimage",
                        success: function(data) {
                            if(data.state=='SUCCESS'){
                                var picUrl = data.url;
                                $.post('/manage/webSetting/updateCarousel',{id:$("#carouselId"+j).val(),gotoUrl:$("#carouselGotoUrl"+j).val(),picUrl:data.url},function (res) {
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
                    $.post('/manage/webSetting/updateCarousel',{id:$("#carouselId"+j).val(),gotoUrl:$("#carouselGotoUrl"+j).val()},function (res) {
                        if(res.count ==1){
                        }else{
                            layer.msg('修改轮播图'+(j+1)+'数据时失败！');
                        }
                    });
                }
        }
        if(addIndex.length>size){
            for(var k=size;k<addIndex.length;k++){
                var gotoUrl2 = $("#carouselGotoUrl"+size).val();
                        $("#carouselAction"+size).ajaxSubmit({
                            type: 'post',
                            dataType: "jsonp",
                            async: false,
                            url: "/ueditor/controller?action=uploadimage",
                            success: function(data) {
                                if(data.state=='SUCCESS'){
                                    var picUrl2 = data.url;
                                    $.post('/manage/webSetting/insertCarousel',{gotoUrl:gotoUrl2, picUrl:picUrl2, type:"1", typeName:"轮播图", title:"轮播图"},function (res) {
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
        prefectureUpdate();
        qrCodeUpdate();
        layer.msg('保存成功！', {time : 1000});
        refresh()
    }

    function prefectureUpdate() {
        for(var index=0;index<4;index++){
            var title = $("#prefectureTitle"+index).val();
            var id = $("#prefectureId"+index).val();
                if($("#prefectureChangeId"+index).val()!=''&&$("#prefectureChangeId"+index).val()!=undefined&&$("#prefectureChangeId"+index).val()!=null){
                    $("#prefectureAction"+index).ajaxSubmit({
                        type: 'post',
                        dataType: "jsonp",
                        async: false,
                        url: "/ueditor/controller?action=uploadimage",
                        success: function(data) {
                            if(data.state=='SUCCESS'){
                                var picUrl = data.url;
                                $.post('/manage/webSetting/updatePrefecture',{id:id,title:title, picUrl:picUrl},function (res) {
                                    if(res.count ==1){
                                    }else{
                                        layer.msg('修改专区板块左'+(index+1)+'数据时失败！');
                                    }
                                });
                            }else{
                                layer.msg(data.state);
                            }
                        },
                        error : function(XmlHttpRequest, textStatus, errorThrown){
                        }
                    })
                }else{
                    $.post('/manage/webSetting/updatePrefecture',{id:id,title:title},function (res) {
                        if(res.count ==1){
                        }else{
                            layer.msg('修改专区板块左'+(index+1)+'数据时失败！');
                        }
                    });
                }
        }
    }

    function qrCodeUpdate() {
        var id = $("#qrCodeId").val();
            if($("#qrCodeChangeId").val()!=''&&$("#qrCodeChangeId").val()!=undefined&&$("#qrCodeChangeId").val()!=null){
                $("#qrCodeAction").ajaxSubmit({
                    type: 'post',
                    dataType: "jsonp",
                    async: false,
                    url: "/ueditor/controller?action=uploadimage",
                    success: function(data) {
                        if(data.state=='SUCCESS'){
                            var picUrl = data.url;
                            $.post('/manage/webSetting/updateQrCode',{id:id,picUrl:picUrl},function (res) {
                                if(res.count ==1){
                                }else{
                                    layer.msg('修改二维码时失败！');
                                }
                            });
                        }else{
                            layer.msg(data.state);
                        }
                    },
                    error : function(XmlHttpRequest, textStatus, errorThrown){
                    }
                })
            }else{
            }
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
            var html = "<div class='form-group col-md-12' style='border:1px solid #eee; padding:10px;'><div class='row'>";
            html +="<div class='col-md-1 col-sm-1'> <label class='control-label' style='float: left;padding-left: 10px;'>图"+((+c_index)+1)+"：</label></div>";
            html +="<div class='col-md-2 col-sm-3'> <form id='carouselAction"+c_index+"' name='upfile'  method='post' enctype='multipart/form-data'>";
            html +="<div id='carouselPreview"+c_index+"' class='ps-image' style='height:130px;border:1px solid #CCC; background:url("+imgUrl+") no-repeat;background-size:100% 100%;'>";
            html +="<input type='file' id='carouselPicTitle"+c_index+"' class='checked' name='upfile' onchange='chage("+c_index+", this)' accept='image' style='filter:alpha(opacity=0);opacity:0;width:100%;height:100%;'/>";
            html +="<input type='hidden' id='carouselChangeId"+c_index+"'/> </div> </form> </div> <div class='col-md-4 col-sm-4'> <div class='form-group' style='float: left;'>";
            html +="<label class='control-label'>跳转链接：</label> <input type='text' class='form-control' placeholder='跳转链接'  maxlength='200' id='carouselGotoUrl"+c_index+"'> </div>";
            html +="<label style='font-weight:100;font-size:8px;width:100%; text-align: left;'>←点击左侧文件框上传图片, 图片大小为1M之内, 建议宽*高为1920*630或3:1</label> </div>";
            html +=" <div class='col-md-2 col-sm-2'> </div> </div></div>";
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