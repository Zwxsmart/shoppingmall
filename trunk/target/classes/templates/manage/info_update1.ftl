<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>网站设置</title>
    <meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, width=device-width">
    <link rel="stylesheet" href="/aw/css/bootstrap.min.css">
<#--<link rel="stylesheet" href="/bs/css/bootstrap-theme.min.css">-->
    <script type="text/javascript" src="/aw/js/jquery-3.1.0.js"></script>
    <script type="text/javascript" src="/aw/js/jquery.form.js"></script>
    <script src="/aw/js/bootstrap.min.js"></script>
    <script src="/aw/assets/layer/layer.js"></script>
    <script type="text/javascript" src="/aw/assets/fileInput/fileinput.js"></script>
    <script type="text/javascript" charset="utf-8" src="/aw/assets/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="/aw/assets/ueditor/ueditor.all.js"></script>
    <script type="text/javascript" charset="utf-8" src="/aw/assets/ueditor/lang/zh-cn/zh-cn.js"></script>
</head>
<body class="ember-application" style="text-align: center;margin-left: 20px">
<div class="form-horizontal" role="form">
    <input type="hidden" value="${(info.id)!''}" id="id">
            <div class="form-group" style="float: left; margin-left: 40px">
                <form id="action" name="upfile"  method="post" enctype="multipart/form-data">
                    <div id="preview" class="ps-image" style="width:130px;height:130px;border:1px solid #CCC;background:url(${(info.showPicTitle)!'/aw/img/add_img.png'}) no-repeat;background-size:130px 130px">
                        <input type="file" id="picTitle" class="checked" name="upfile" accept="image" value="${(info.picTitle)!''}" style="filter:alpha(opacity=0);opacity:0;width:100%;height:100%;"/>
                        <input type="hidden" id="changeId"/>
                        <input type="hidden" value="${(info.picTitle)!''}" id="picTitleId"/>
                    </div>
                </form>
            </div>
    <div class="form-group" style="float: left; ">
        <label class="control-label" style="float: left;margin-left: 20px;">标题：</label>
        <input type="text" class="form-control" placeholder="标题" style="width: 400px;float: left;" maxlength="100" minlength="1" value="${(info.title)!''}" id="title">
    </div>
    <div class="form-group" style="float: left; margin-left: 40px">
        <label style="font-weight:100;font-size:8px;width:100%; text-align: left;">←点击左侧文件框上传图片, 图片大小为1M之内, 建议宽*高为290*210</label>
    </div>
        <div class="form-group" style="float: left;margin-left: 20px">
            <div class="col-sm-offset-2 col-sm-10">
            <#if info?? >
                <button class="btn btn-success radius" onclick="edit()">保存</button>
            <#else>
                <button class="btn btn-success radius" onclick="add()">添加</button>
            </#if>
            </div>
        </div>
</div>
</body>
<script type="text/javascript">
    $(document).ready(function() {
        $preview = document.getElementById("preview"),
         $logoImg = document.getElementById("picTitle"),
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
            $logoImg.setAttribute( 'disabled','disabled' );
        } else {
            $logoImg.addEventListener('change',readFile,false );
        }
        function readFile(){
            var file = this.files[0];
            //这里我们判断下类型如果不是图片就返回 去掉就可以上传任意文件
            if($logoImg.value==''){
                $preview.style.width='130px';
                $preview.style.height='130px';
                $preview.style.border='1px solid #CCC';
                $preview.style.background="url('/aw/img/add_img.png') no-repeat";
                $preview.style.backgroundSize='130px 130px';
            }else{
                if(!/image\/\w+/.test(file.type)){
                    layer.msg("请确保文件为图像类型");
                    return false;
                }
                if(file.size >1048576){
                    layer.msg('文件不允许超过1M！')
                    return false;
                }
                $("#changeId").val("change");
                var reader = new FileReader();
                reader.readAsDataURL(file);
                reader.onload = function(e){
                    $preview.style.width='130px';
                    $preview.style.height='130px';
                    $preview.style.border='1px solid #CCC';
                    $preview.style.background="url('"+this.result+"') no-repeat";
                    $preview.style.backgroundSize='130px 130px';
                }
            }
        }
    }

    function add(){
        var id = $("#id").val();
        var title = $("#title").val();
            if(title!=""&&title!=undefined&&title!=null&&$("#changeId").val()!=''&&$("#changeId").val()!=undefined&&$("#changeId").val()!=null){
                    $("#action").ajaxSubmit({
                        type: 'post',
                        dataType: "jsonp",
                        url: "/ueditor/controller?action=uploadimage",
                        success: function(data) {
                            if(data.state=='SUCCESS'){
                                var picTitle = data.url;
                                $.post('/manage/webSetting/saveOrUpdate1',{id:id,picTitle:picTitle,title:title},function (res) {
                                    if(res.count ==1){
                                        layer.msg('添加成功！')
                                        refresh()
                                    }else{
                                        layer.msg('添加失败！');
                                    }
                                });
                            }else{
                                layer.msg('添加失败！');
                            }

                        },
                        error : function(XmlHttpRequest, textStatus, errorThrown){
                            layer.msg('添加失败！');
                        }
                    })
            }else{
                layer.msg('数据不完整!')
            }
    }

    function edit(){
        var id = $("#id").val();
        var title = $("#title").val();
        if(title!=""&&title!=undefined&&title!=null){
            if($("#changeId").val()!=''&&$("#changeId").val()!=undefined&&$("#changeId").val()!=null){
                $("#action").ajaxSubmit({
                    type: 'post',
                    dataType: "jsonp",
                    url: "/ueditor/controller?action=uploadimage",
                    success: function(data) {
                        if(data.state=='SUCCESS'){
                            picTitle = data.url;
                            $.post('/manage/webSetting/saveOrUpdate1',{id:id,picTitle:picTitle, title:title},function (res) {
                                if(res.count ==1){
                                    layer.msg('保存成功！')
                                    refresh()
                                }else{
                                    layer.msg('保存失败！');
                                }
                            });
                        }else{
                            layer.msg('保存失败！');
                        }
                    },
                    error : function(XmlHttpRequest, textStatus, errorThrown){
                        layer.msg('保存失败！');
                    }
                })
            }else{
                var picTitle = $("#picTitleId").val();
                $.post('/manage/webSetting/saveOrUpdate1',{id:id,title:title, picTitle:picTitle},function (res) {
                    if(res.count ==1){
                        layer.msg('保存成功！')
                        refresh()
                    }else{
                        layer.msg('保存失败！');
                    }
                });
            }
        }else{
            layer.msg('数据不完整!')
        }
    }
</script>
</html>