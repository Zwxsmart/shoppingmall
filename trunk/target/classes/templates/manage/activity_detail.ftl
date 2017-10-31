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
<div class="form-horizontal" role="form" style="text-align: center">
        <div class="form-group">
            <label class="control-label">标题：</label>
            <label class="control-label">${(info.title)!''}</label>
        <#if info?? >
            <#if (info.sortIndex ==1) >
                <a class="btn btn-primary radius" style="margin-left: 30px;" href="/manage/activity/queryCheckPage?campaignId=${campaignId}&releasePointId=${releasePointId}&autograph=${autograph}">去登记>>></a>
            </#if>
        </#if>
        </div>
    <p style="clear: both"></p>
<div>
    <label class="control-label">内容： ${(info.content)!''}</label>

</div>
</div>
</body>
</html>