<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>活动列表</title>
    <meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, width=device-width">
<#--<link rel="stylesheet" href="/bs/css/bootstrap-theme.min.css">-->
    <script type="text/javascript" src="/aw/js/jquery-3.1.0.js"></script>
    <script type="text/javascript" src="/aw/js/jquery.form.js"></script>
    <script src="/aw/js/bootstrap.min.js"></script>
    <script src="/aw/assets/layer/layer.js"></script>
    <link rel="stylesheet" href="/aw/assets/bootstrap-tables/css/bootstrap-table.css">
    <link rel="stylesheet" href="/aw/css/bootstrap.min.css">
</head>
<body class="ember-application" style="text-align: center;margin-left: 20px">
<div class="form-horizontal" role="form">
    <div class="panel-body" style="padding-bottom:0px;">
        <table id="table" style="table-layout: fixed">
            <thead>
            <tr>
                <th data-width="150" data-field="showPicTitle" data-formatter="formatterImg">活动图片</th>
                <th data-width="100" data-field="title">活动标题</th>
                <#--<th data-width="600" data-field="content">热门活动内容</th>-->
                <th data-width="100" data-field="tagName">活动标签</th>
                <th data-width="130" data-field="createDate" data-formatter="formatterDate">活动创建时间</th>
                <th data-width="130" data-field="beginDate" data-formatter="formatterDate">活动开始时间</th>
                <th data-width="130" data-field="finishDate" data-formatter="formatterDate">活动结束时间</th>
                <th data-width="130" data-field="status" data-formatter="formatterStatus">活动状态</th>
                <th data-width="130" data-field="operName">活动创建者</th>
                <th data-width="100" data-field="isValid" data-formatter="statusFormatter">是否发布</th>
                <th data-width="250" data-field="isValid" data-formatter="operateFormatter">
                    操作
                </th>
            </tr>
            </thead>
        </table>
        <div id="toolbar" class="btn-group">
            <button id="btn_add" type="button" class="btn btn-primary" onclick="showAdd();">
                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增活动
            </button>
        </div>
    </div>
</body>
<script src="/aw/assets/bootstrap-tables/js/bootstrap-table.min.js"></script>
<script src="/aw/assets/bootstrap-tables/js/bootstrap-table-zh-CN.min.js"></script>
<script type="text/javascript" charset="utf-8" src="/aw/assets/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="/aw/assets/ueditor/ueditor.all.js"> </script>
<script type="text/javascript" charset="utf-8" src="/aw/assets/fileInput/fileinput.js"></script>
<!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
<script type="text/javascript" charset="utf-8" src="/aw/assets/ueditor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        initTable("table", "/manage/webSetting/activityList2");
    } );

    function refresh() {
        setTimeout(function(){
            location.replace(location.href);
        },800);
    }

    function formatterImg(value, row, index) {
        return [
            '<img style="width:120px;height:60px;" src="' + value + '">'
        ]
    }

    function operateFormatter(value, row, index) {
        if(value == '1') {
            return "<button type='button' class='btn btn-primary' onclick='showEdit(\""+'/manage/webSetting/queryById4?id='+row.id+"\")'>编辑</button>&nbsp;&nbsp;<button type='button' class='btn btn-primary' onclick='showQrCode(\""+'/manage/webSetting/queryQrCode?id='+row.id+"\")'>生成二维码</button>&nbsp;&nbsp;<button type='button' class='btn btn-danger' onclick='showCanleRelease(\""+'/manage/webSetting/release1?id='+ row.id+'&status=Y'+ "\")'>取消发布</button>";
        } else {
            return "<button type='button' class='btn btn-primary' onclick='showEdit(\""+'/manage/webSetting/queryById4?id='+row.id+"\")'>编辑</button>&nbsp;&nbsp;<button type='button' class='btn btn-primary' onclick='showQrCode(\""+'/manage/webSetting/queryQrCode?id='+row.id+"\")'>生成二维码</button>&nbsp;&nbsp;<button type='button' class='btn btn-success' onclick='showRelease(\""+'/manage/webSetting/release1?id='+ row.id+'&status=N'+ "\")'>发布</button>";
        }
    }

    // 取消发布
    function showCanleRelease(url) {
        $.post(url, function(data){
            if(data.count == 1){
                layer.msg('取消发布成功！');
                $('#table').bootstrapTable('refresh');
            }else{
                layer.msg('取消发布失败！');
            }
        },"json");
    }
    // 发布
    function showRelease(url) {
        $.post(url, function(data){
            if(data.count == 1){
                $('#table').bootstrapTable('refresh');
                layer.msg('发布成功！');
            }else{
                layer.msg('发布失败！');
            }
        },"json");
    }

    function showAdd() {
        layer.open({
            type: 2,
            title: '添加热门活动',
            shadeClose: false,
            shade: 0.8,
            area: ['1000px', '80%'],
            content: "/manage/webSetting/queryById4", //iframe的url
            end: function () {
                location.reload();
            }
        });
    }

    function showEdit(url) {
        layer.open({
            type: 2,
            title: '修改热门活动',
            shadeClose: false,
            shade: 0.8,
            area: ['1000px', '80%'],
            content: url, //iframe的url
            end: function () {
                location.reload();
            }
        });
    }

    function showQrCode(url) {
        layer.open({
            type: 2,
            title: '活动二维码',
            shadeClose: false,
            shade: 0.8,
            area: ['1500px', '90%'],
            content: url, //iframe的url
            end: function () {
                location.reload();
            }
        });
    }

    function initTable(tableId, url) {
        //先销毁表格
        $('#' + tableId).bootstrapTable('destroy');
        //初始化表格,动态从服务器加载数据
        $("#" + tableId).bootstrapTable({
            method: "get",  //使用get请求到服务器获取数据
            url: url, //获取数据的Servlet地址
            striped: false,  //表格显示条纹
            pagination: true, //启动分页
            pageSize: 10,  //每页显示的记录数
            pageNumber:1, //当前第几页
            pageList: [10, 15, 20, 25, 30],  //记录数可选列表
            showColumns: true,  //显示下拉框勾选要显示的列
            showRefresh: true,  //显示刷新按钮
            showToggle: true, // 显示详情
            strictSearch: true,
            clickToSelect: true,  //是否启用点击选中行
            uniqueId: "id",                     //每一行的唯一标识，一般为主键列
            sortable: true,                     //是否启用排序
            sortOrder: "asc",                   //排序方式
            toolbar : "#toolbar",// 指定工具栏
            sidePagination: "server", //表示服务端请求


            //设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder
            //设置为limit可以获取limit, offset, search, sort, order
            queryParamsType : "undefined",
            queryParams: function queryParams(params) {   //设置查询参数
                var param = {
                    pageNumber: params.pageNumber,
                    pageSize: params.pageSize,
                };
                return param;
            },
        });
    }

    /**
     * 时间格式化，传递进来的时间
     */
    function formatterDate(value) {
        if (value == undefined || value == null || value == '') {
            return "";
        }
        else {
            var date = new Date(value);
            var year = date.getFullYear().toString();
            var month = (date.getMonth() + 1);
            var day = date.getDate().toString();
            var hour = date.getHours().toString();
            var minutes = date.getMinutes().toString();
            var seconds = date.getSeconds().toString();
            if (month < 10) {
                month = "0" + month;
            }
            if (day < 10) {
                day = "0" + day;
            }
            if (hour < 10) {
                hour = "0" + hour;
            }
            if (minutes < 10) {
                minutes = "0" + minutes;
            }
            if (seconds < 10) {
                seconds = "0" + seconds;
            }
            return year + "-" + month + "-" + day + "/" + hour + ":" + minutes + ":" + seconds;
        }
    }

    function formatterStatus(value, row, index) {
        if(value==1) {
            return "<span style='color: green'>进行中</span>";
        }else if(value==2){
            return "<span style='color: red'>已结束</span>";
        }else if(value==3){
            return "<span style='color: yellow'>未开始</span>";
        }
    }

    function statusFormatter(value, row, index) {
        if(value) {
            return "已发布";
        }else {
            return "未发布";
        }
    }
</script>
</html>