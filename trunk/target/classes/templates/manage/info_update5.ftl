<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>投放点新增或修改</title>
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
<div class="form-horizontal" role="form" style="padding-left: 200px;padding-top: 300px;">
    <input type="hidden" value="${(info.id)!''}" id="id">
        <div class="form-group" style="float: left; ">
            <label class="control-label" style="float: left;margin-left: 20px;">投放点名称：</label>
            <input type="text" class="form-control" placeholder="投放点名称" style="width: 400px;float: left;" maxlength="100" minlength="1" value="${(info.pointName)!''}" id="title">
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
</body>
<script type="text/javascript">
    $(document).ready(function() {
        initFileReader();
    } );

    function refresh() {
        setTimeout(function(){
            location.replace(location.href);
        },800);
    }

    function add(){
        var title = $("#title").val();
        var id = $("#id").val();
        if(title!=undefined&&title!=""){
                            $.post('/manage/webSetting/saveOrUpdate5',{id:id,title:title},function (res) {
                                if(res.count ==1){
                                    layer.msg('添加成功！')
                                    refresh()
                                }else{
                                    layer.msg('添加失败！');
                                }
                            });
        }else{
            layer.msg('数据不完整!')
        }
    }

    function edit(){
        var title = $("#title").val();
        var id = $("#id").val();
        if(title!=undefined&&title!=""){
                $.post('/manage/webSetting/saveOrUpdate5',{id:id,title:title},function (res) {
                    if(res.count ==1){
                        layer.msg('保存成功！')
                        refresh()
                    }else{
                        layer.msg('保存失败！');
                    }
                });
        }else{
            layer.msg('数据不完整!')
        }
    }
</script>
</html>