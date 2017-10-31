<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>专区内轮播图设置</title>
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
</head>
<body class="ember-application" style="text-align: center;margin-left: 20px">
<div style="width: 1000px;height: 100%;margin-left: auto;margin-right: auto;margin-top: 10px;">
    <div class="form-horizontal" role="form">
        <div style="width: 700px;height: 50px;">
            <div class="form-group" style="padding-left: 20px;">
                <label class="control-label" style="float: left;padding-left: 20px;">专区内轮播图设置：</label>
            </div>
        </div>
        <!-- 轮播图 -->
        <#if (headerInfo)??>
            <#list headerInfo as c>
                <div class="form-group col-md-12">
                    <label class="control-label" style="float: left;padding-left: 20px;">图${c_index+1}：</label>
                    <div class="form-group" style="float: left; margin-left: 40px">
                        <form id="carouselAction${c_index}" name="upfile"  method="post" enctype="multipart/form-data">
                            <div id="carouselPreview${c_index}" class="ps-image" style="width:130px;height:130px;border:1px solid #CCC; background:url(${(c.showPicUrl)!'/aw/img/add_img.png'}) no-repeat;background-size:130px 130px">
                                <input type="file" id="carouselPicTitle${c_index}" class="checked" name="upfile" value="${(c.picUrl)!''}" accept="image" style="filter:alpha(opacity=0);opacity:0;width:100%;height:100%;"/>
                                <input type="hidden" id="carouselChangeId${c_index}"/>
                            </div>
                        </form>
                    </div>
                    <div class="form-group" style="float: left; margin-top: 20px;">
                        <label style="width:160px;padding-left: 20px;float: left;">轮播图上文字内容：</label>
                        <textarea class="form-control" placeholder="请输入轮播图上文字内容" rows="3" style="width: 490px;" maxlength="200" id="carouselTitle${c_index}">${(c.title)!''}</textarea>
                    </div>
                    <div class="form-group" style="float: left;">
                        <label class="control-label" style="padding-left: 20px;">跳转链接：</label>
                        <input type="text" class="form-control" placeholder="跳转链接" style="width: 400px;" value="${(c.gotoUrl)!''}" maxlength="200" id="carouselGotoUrl${c_index}">
                    </div>
                    <div class="form-group" style="float: left;">
                        <div class="col-sm-offset-2">
                            <button class="btn btn-success radius" onclick="carouselUpdate(${c_index})" value="${(c.id)!''}" id="carouselId${c_index}">修改</button>
                        </div>
                    </div>
                    <#if c.isValid == true>
                        <div class="form-group" style="float: left; margin-left:10px;">
                            <div class="col-sm-offset-2">
                                <button class="btn btn-danger radius" onclick="status(false,${c_index})" value="${(c.id)!''}" id="statusId${c_index}">关闭显示</button>
                            </div>
                        </div>
                    <#else>
                        <div class="form-group" style="float: left;margin-left:10px;">
                            <div class="col-sm-offset-2">
                                <button class="btn btn-success radius" onclick="status(true,${c_index})" value="${(c.id)!''}" id="statusId${c_index}">开启显示</button>
                            </div>
                        </div>
                    </#if>
                </div>
            </#list>
        </#if>
    </div>
</div>
</body>
<script type="text/javascript">
    $(document).ready(function() {
        // 轮播图
        $carouselLogoImg0=document.getElementById("carouselPicTitle0");
        $carouselLogoImg1=document.getElementById("carouselPicTitle1");
        $carouselLogoImg2=document.getElementById("carouselPicTitle2");
        $carouselLogoImg3=document.getElementById("carouselPicTitle3");
        $carouselLogoImg4=document.getElementById("carouselPicTitle4");
        $carouselLogoImg5=document.getElementById("carouselPicTitle5");
        $carouselPreview0=document.getElementById("carouselPreview0");
        $carouselPreview1=document.getElementById("carouselPreview1");
        $carouselPreview2=document.getElementById("carouselPreview2");
        $carouselPreview3=document.getElementById("carouselPreview3");
        $carouselPreview4=document.getElementById("carouselPreview4");
        $carouselPreview5=document.getElementById("carouselPreview5");
        initFileReader();
    } );

    function refresh() {
        setTimeout(function(){
            location.replace(location.href);
        },800);
    }

    function initFileReader(){
        if ( typeof(FileReader) === 'undefined' ){
            layer.msg("抱歉，你的浏览器不支持 FileReader，请使用现代浏览器操作！");
            //$logoImg.setAttribute( 'disabled','disabled' );
        } else {
            $carouselLogoImg0.addEventListener('change',function() {var file = this.files[0];readFile($carouselLogoImg0, $carouselPreview0, "carouselChangeId0", file)},false);
            $carouselLogoImg1.addEventListener('change',function() {var file = this.files[0];readFile($carouselLogoImg1, $carouselPreview1, "carouselChangeId1", file)},false);
            $carouselLogoImg2.addEventListener('change',function() {var file = this.files[0];readFile($carouselLogoImg2, $carouselPreview2, "carouselChangeId2", file)},false);
            $carouselLogoImg3.addEventListener('change',function() {var file = this.files[0];readFile($carouselLogoImg3, $carouselPreview3, "carouselChangeId3", file)},false);
            $carouselLogoImg4.addEventListener('change',function() {var file = this.files[0];readFile($carouselLogoImg4, $carouselPreview4, "carouselChangeId4", file)},false);
            $carouselLogoImg5.addEventListener('change',function() {var file = this.files[0];readFile($carouselLogoImg5, $carouselPreview5, "carouselChangeId5", file)},false);
        }
        function readFile(logoImg, preview, changeId, file){
            //这里我们判断下类型如果不是图片就返回 去掉就可以上传任意文件
            if(logoImg.value==''){
                preview.style.width='130px';
                preview.style.height='130px';
                preview.style.border='1px solid #CCC';
                preview.style.background="url('/aw/img/add_img.png') no-repeat";
                preview.style.backgroundSize='130px 130px';
            }else{
                if(!/image\/\w+/.test(file.type)){
                    layer.msg("请确保文件为图像类型");
                    return false;
                }
                if(file.size >1048576){
                    layer.msg('文件不允许超过1M！')
                    return false;
                }
                $("#"+changeId).val("change");
                var reader = new FileReader();
                reader.readAsDataURL(file);
                reader.onload = function(e){
                    preview.style.width='130px';
                    preview.style.height='130px';
                    preview.style.border='1px solid #CCC';
                    preview.style.background="url('"+this.result+"') no-repeat";
                    preview.style.backgroundSize='130px 130px';
                }
            }
        }
    }

    function carouselUpdate(index) {
        var gotoUrl = $("#carouselGotoUrl"+index).val();
        var title = $("#carouselTitle"+index).val();
        var id = $("#carouselId"+index).val();
        if(title!=undefined&&title!=""&&gotoUrl!=undefined&&gotoUrl!=""&&id!=undefined&&id!=""){
            if($("#carouselChangeId"+index).val()!=''&&$("#carouselChangeId"+index).val()!=undefined&&$("#carouselChangeId"+index).val()!=null){
                $("#carouselAction"+index).ajaxSubmit({
                    type: 'post',
                    dataType: "jsonp",
                    url: "/ueditor/controller?action=uploadimage",
                    success: function(data) {
                        if(data.state=='SUCCESS'){
                            picUrl = data.url;
                            $.post('/manage/webSetting/updatePrefecture',{id:id,gotoUrl:gotoUrl, picUrl:picUrl, title:title},function (res) {
                                if(res.count ==1){
                                    layer.msg('修改成功！')
                                    refresh()
                                }else{
                                    layer.msg('修改失败！');
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
                $.post('/manage/webSetting/updatePrefecture',{id:id,gotoUrl:gotoUrl, title:title},function (res) {
                    if(res.count ==1){
                        layer.msg('修改成功！')
                        refresh()
                    }else{
                        layer.msg('修改失败！');
                    }
                });
            }
        }else{
            layer.msg('数据不完整!')
        }
    }

    function status(status, index) {
        var id =$("#statusId"+index).val();
        if(status){
            $.post("/manage/webSetting/headerInfoStatus?status=1&id="+id, function(res){
                if(res.count ==1){
                    layer.msg('开启成功！', {time : 1000});
                    refresh()
                }else{
                    layer.msg('开启失败！', {time : 1000});
                }
            },"json");
        }else{
            $.post("/manage/webSetting/headerInfoStatus?status=0&id="+id, function(res){
                if(res.count ==1){
                    layer.msg('关闭成功！', {time : 1000});
                    refresh()
                }else{
                    layer.msg('关闭失败！', {time : 1000});
                }
            },"json");
        }
    }
</script>
</html>