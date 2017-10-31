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
</head>
<body class="ember-application" style="text-align: center;margin-left: 20px">
<div style="width: 1000px;height: 100%;margin-left: auto;margin-right: auto;margin-top: 10px;">

<div class="form-horizontal" role="form">
    <div style="width: 1000px;height: 50px;">
        <div class="form-group" style="float: left; margin-left: 40px">
                <form id="action" enctype="multipart/form-data">
                    <div class="form-group">
                        <input type="hidden" value="${(info.id)!''}" id="id" name="id"/>
                        <input type="hidden" id="content" name="content"/>
                        <label class="control-label" style="float:left;margin-top:50px;">${(tableText)!''}内容：</label>
                        <textarea placeholder="请输入内容" class="form-control" id="content" name="content" maxlength="1000" value="${(info.content)!''}" style="width: 600px;height:300px;margin-left: 20px;">${(info.content)!''}</textarea>
                        <br/><button class="btn btn-success radius" type="button" onclick="edit()">保存</button>
                    </div>
                </form>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    function refresh() {
        setTimeout(function(){
            location.replace(location.href);
        },800);
    }

    function edit(){
        var content = $("#content")
        if(content!=undefined&&content!=""){
            $('#action').ajaxSubmit({
                url: '/manage/webSetting/updateContent',
                type: 'post',
                dataType: 'json',
                success: function (data) {
                    if(data.count==1){
                        layer.msg('保存成功!')
                        refresh();
                    }else{
                        layer.msg('保存失败!')
                    }
                }
            })
        }else{
            layer.msg('数据不完整!')
        }
    }
</script>
</html>