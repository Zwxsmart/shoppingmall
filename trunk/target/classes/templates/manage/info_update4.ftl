<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>联盟国际活动添加或修改</title>
    <meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, width=device-width">
    <link rel="stylesheet" href="/aw/css/bootstrap.min.css">
    <link rel="stylesheet" href="/aw/assets/bootstrap-dateTimePicker/css/bootstrap-datetimepicker.min.css">
    <script type="text/javascript" src="/aw/js/jquery-3.1.0.js"></script>
    <script type="text/javascript" src="/aw/js/jquery.form.js"></script>
    <script src="/aw/js/bootstrap.min.js"></script>
    <script src="/aw/assets/layer/layer.js"></script>
    <script type="text/javascript" src="/aw/assets/fileInput/fileinput.js"></script>
    <script type="text/javascript" charset="utf-8" src="/aw/assets/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="/aw/assets/ueditor/ueditor.all.js"></script>
    <script type="text/javascript" charset="utf-8" src="/aw/assets/ueditor/lang/zh-cn/zh-cn.js"></script>
    <script src="/aw/assets/bootstrap-dateTimePicker/js/bootstrap-datetimepicker.min.js"></script>
    <script src="/aw/assets/bootstrap-dateTimePicker/js/bootstrap-datetimepicker.zh-CN.js"></script>
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
        <div class="form-group" style="float: left;margin-left: 20px; ">
            <div class="checkbox">
                <label>
                <#if (info.isValid)!false=true>
                    <input type="checkbox" checked="checked" id="isuntrue"> 是否发布
                <#else>
                    <input type="checkbox" id="isuntrue"> 是否发布
                </#if>
                </label>
            </div>
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
        <div class="form-group" style="float: left; ">
            <label class="control-label" style="float: left;margin-left: 20px;">标签：</label>
            <input type="text" class="form-control" placeholder="标签" style="width: 400px;float: left;" maxlength="100" minlength="1" value="${(info.tagName)!''}" id="tagName">
            <#if info?? >
                <#if (info.sortIndex ==0) >
                    <button class="btn btn-primary radius" style="margin-left: 10px;" onclick="active('/manage/webSetting/checkInlet?id='+ ${(info.id)!''}+'&status=N')">详情中显示登记入口</button>
                <#else>
                    <button class="btn btn-danger radius" style="margin-left: 10px;" onclick="inactive('/manage/webSetting/checkInlet?id='+ ${(info.id)!''}+'&status=Y')">详情中关闭登记入口</button>
                </#if>
            </#if>
        </div>
        <div class="form-group" style="float: left; ">
            <label class="control-label" style="float: left;margin-left: 20px;">开始时间：</label>
            <input id="startTime" placeholder="请选择开始时间"
                   readonly="true" type="text" name="startTime"
                   class="form-control" style="width:200px;float:left" value="${(info.beginDate)!''}"/>
            <label class="control-label" style="float: left;margin-left: 20px;">结束时间：</label>
            <input id="endTime" placeholder="请选择结束时间"
                   readonly="true" type="text" name="endTime"
                   class="form-control" style="width:200px;float:left" value="${(info.finishDate)!''}"/>
        </div>
        <div class="form-group" style="float: left;padding-left: 160px;">
            <label style="font-weight:100;font-size:8px;padding-left: 20px;">←点击左侧正方形上传图片, 图片大小为1M之内</label>
        </div>
<div style="float:left;width: 900px;height: 600px">
    <!-- 加载编辑器的容器 -->
    <script id="container" name="content" type="text/plain" >
    ${(info.content)!''}
    </script>
    <!-- 实例化编辑器 -->
    <script type="text/javascript">
        var ue = UE.getEditor('container');
    </script>
</div>
</div>
</body>
<script type="text/javascript">
    $(document).ready(function() {
        $preview = document.getElementById("preview"),
         $logoImg = document.getElementById("picTitle"),
        initFileReader();
        initDateTimePicker("startTime");
        initDateTimePicker("endTime");
        initDateTimePicker("currentTime");
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
        var startTime = $("#startTime").val();
        var title = $("#title").val();
        var id = $("#id").val();
        var tagName = $("#tagName").val();
        var endTime = $("#endTime").val();
        var content = ue.getContent();
        if(title!=null&&title!=""&&content!=null&&content!=""&&tagName!=null&&tagName!=""
                &&startTime!=null&&startTime!=""&&endTime!=null&&endTime!=""
                &&$("#changeId").val()!=''&&$("#changeId").val()!=null&&$("#changeId").val()!=null){
                if(endTime >startTime){
                    $("#action").ajaxSubmit({
                        type: 'post',
                        dataType: "jsonp",
                        url: "/ueditor/controller?action=uploadimage",
                        success: function(data) {
                            if(data.state=='SUCCESS'){
                                var isValid = 0;
                                if($("#isuntrue").is(':checked')){
                                    isValid= 1;
                                }
                                var picTitle = data.url;
                                $.post('/manage/webSetting/saveOrUpdate4',{id:id,title:title,picTitle:picTitle,content:content,isValid:isValid, tagName:tagName,startTime:startTime, endTime:endTime},function (res) {
                                    if(res.count ==1){
                                        layer.msg('添加成功！')
                                        refresh()
                                    }else{
                                        layer.msg('添加失败！');
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
                    layer.msg("开始时间不能大于结束时间！");
                }
        }else{
            layer.msg('数据不完整!')
        }
    }

    function edit(){
        var title = $("#title").val();
        var id = $("#id").val();
        var content = ue.getContent();
        var tagName = $("#tagName").val();
        var startTime = $("#startTime").val();
        var endTime = $("#endTime").val();
        var isValid = 0;
        if($("#isuntrue").is(':checked')){
            isValid= 1;
        }
        if(title!=undefined&&title!=""&&content!=undefined&&content!=""&&tagName!=null&&tagName!=""
                &&startTime!=null&&startTime!=""&&endTime!=null&&endTime!=""){
            if(endTime >startTime){
                if ($("#changeId").val() != '' && $("#changeId").val() != undefined && $("#changeId").val() != null) {
                    $("#action").ajaxSubmit({
                        type: 'post',
                        dataType: "jsonp",
                        url: "/ueditor/controller?action=uploadimage",
                        success: function (data) {
                            if (data.state == 'SUCCESS') {
                                var picTitle = data.url;
                                $.post('/manage/webSetting/saveOrUpdate4', {
                                    id: id,
                                    title: title,
                                    picTitle: picTitle,
                                    content: content,
                                    isValid: isValid,
                                    tagName:tagName,
                                    startTime:startTime,
                                    endTime:endTime
                                }, function (res) {
                                    if (res.count == 1) {
                                        layer.msg('保存成功！')
                                        refresh()
                                    } else {
                                        layer.msg('保存失败！');
                                    }
                                });
                            } else {
                                layer.msg(data.state);
                            }
                        },
                        error: function (XmlHttpRequest, textStatus, errorThrown) {
                        }
                    })
                } else {
                    picTitle = $("#picTitleId").val();
                    $.post('/manage/webSetting/saveOrUpdate4', {
                        id: id,
                        title: title,
                        picTitle: picTitle,
                        content: content,
                        isValid: isValid,
                        tagName:tagName,
                        startTime:startTime,
                        endTime:endTime
                    }, function (res) {
                        if (res.count == 1) {
                            layer.msg('保存成功！')
                            refresh()
                        } else {
                            layer.msg('保存失败！');
                        }
                    });
                }
            }else{
                layer.msg("开始时间不能大于结束时间！");
            }
        }else{
            layer.msg('数据不完整!')
        }
    }

    // 初始化带有分秒的时间框
    function initDateTimePicker(fieldId){
        $("#"+fieldId).datetimepicker({
            language: 'zh-CN',
            format: 'yyyy-mm-dd hh:ii',
            initialDate: new Date(),
            autoclose: true,
            todayHighligh:true,
            startDate:new Date(),//结束时间，在这时间之后都不可选
            todayBtn :true, // 显示今日按钮
            autoclose: 1
        })
    }

    // 初始化没有分秒的时间框
    function initDatePicker(fieldId){
        $("#"+fieldId).datetimepicker({
            minView: "month", //选择日期后，不会再跳转去选择时分秒
            language: 'zh-CN',
            format: 'yyyy-mm-dd',
            initialDate: new Date(),
            autoclose: true,
            todayHighligh:true,
            startDate:new Date(),//结束时间，在这时间之后都不可选
            todayBtn :true, // 显示今日按钮
            autoclose: 1
        })
    }

    // 冻结
    function inactive(url) {
        $.post(url, function(data){
            if(data.count == 1){
                refresh()
                layer.msg('关闭成功！', {time : 1000});
            }else{
                layer.msg('关闭失败！', {time : 1000});
            }
        },"json");
    }
    // 激活
    function active(url) {
        $.post(url, function(data){
            if(data.count == 1){
                refresh()
                layer.msg('显示成功！', {time : 1000});
            }else{
                layer.msg('显示失败！', {time : 1000});
            }
        },"json");
    }
</script>
</html>