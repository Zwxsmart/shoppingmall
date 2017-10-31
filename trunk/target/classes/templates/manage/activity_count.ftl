<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>统计分析</title>
    <meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, width=device-width">
    <link rel="stylesheet" href="/aw/assets/bootstrap-dateTimePicker/css/bootstrap-datetimepicker.min.css">
    <link rel="stylesheet" href="/aw/assets/bootstrap-tables/css/bootstrap-table.css">
    <link rel="stylesheet" href="/aw/css/bootstrap.min.css">
    <style>
        .report-file {
            display: block;
            position: relative;
            width: 70px;
            height: 34px;
            overflow: hidden;
            border: 1px solid #ccc;
            background: none repeat scroll 0 0 #fff;
            text-align: center;
            float: left;
            margin-right:5px;
        }
        .report-file span {
            display: block;
            line-height: 28px;
        }
        .file-prew {
            position: absolute;
            top: 0;
            left:0;
            width: 120px;
            height: 30px;
            font-size: 100px;
            opacity: 0;
            filter: alpha(opacity=0);
        }
    </style>
</head>
<body>
<div >
    <div class="panel-body" style="padding-bottom:0px;">
        <table id="table" style="table-layout: fixed">
            <thead>
            <tr>
                <th data-width="100" data-field="title">活动</th>
                <th data-width="100" data-field="checkCount">登记人数</th>
                <th data-width="100" data-field="browseQuantity">浏览次数</th>
                <th data-width="100" data-field="putCount">投放数</th>
                <th data-width="100" data-field="beginDate" data-formatter="formatterDate">活动开始时间</th>
                <th data-width="140" data-field="finishDate" data-formatter="formatterDate">活动结束时间</th>
                <th data-width="50" data-field="isValid" data-formatter="operateFormatter">
                    操作
                </th>
            </tr>
            </thead>
        </table>
        <div id="toolbar" class="btn-group">
                <span style="float:left;margin-top: 7px;">时间：</span>
                <input id="startTime" placeholder="请选择开始时间"
                       readonly="true" type="text" name="startTime"
                       class="form-control" style="width:200px;float:left"/>
                <span style="float:left;margin-top: 7px;margin-left: 3px;margin-right: 3px;">至</span>
                <input id="endTime" placeholder="请选择结束时间"
                       readonly="true" type="text" name="endTime"
                       class="form-control" style="width:200px;float:left"/>
                <span style="padding-left: 30px;float:left;margin-top: 7px;">活动名称：</span>
                <input id="userName" class="form-control" style="width: 90px;float:left">
                <button id="btn_add" type="button" class="btn btn-default" onclick="blurredQuery();" style="margin-left: 30px;float:left">
                    <span class="glyphicon glyphicon-search" aria-hidden="true" ></span>查找
                </button>
        </div>
    </div>
</div>
<script type="text/javascript" src="/aw/js/jquery-3.1.0.js"></script>
<script type="text/javascript" src="/aw/js/jquery.form.js"></script>
<script src="/aw/js/bootstrap.min.js"></script>
<script src="/aw/assets/layer/layer.js"></script>
<script src="/aw/assets/icheck/jquery.icheck.min.js"></script>
<script src="/aw/assets/bootstrap-tables/js/bootstrap-table.min.js"></script>
<script src="/aw/assets/bootstrap-tables/js/bootstrap-table-zh-CN.min.js"></script>
<script src="/aw/assets/bootstrap-dateTimePicker/js/bootstrap-datetimepicker.min.js"></script>
<script src="/aw/assets/bootstrap-dateTimePicker/js/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="/aw/assets/bootstrap-tables/js/jquery.dataTables.min.js"></script>
<script src="/aw/assets/bootstrap-tables/js/dataTables.bootstrap.min.js"></script>
<script type="text/javascript" charset="utf-8">
    $(document).ready(function() {
       initTable("table", "/manage/webSetting/activityCountList");
        initDatePicker("startTime");
        initDatePicker("endTime");
    } );

    function blurredQuery(){
        initTable('table', '/manage/webSetting/activityCountList');
    }

    function showEdit(url){
        layer.open({
            type: 2,
            title: '活动详情',
            shadeClose: false,
            shade: 0.8,
            area: ['1500px', '70%'],
            content: url, //iframe的url
        });
    }

    // 激活或冻结
    function operateFormatter(value, row, index) {
        return "<button type='button' class='btn btn-default' onclick='showEdit(\""+'/manage/webSetting/queryByActivityId?id='+row.id+"\")'>详情</button>";
    }

    // 初始化带有分秒的时间框
    function initDateTimePicker(fieldId){
        $("#"+fieldId).datetimepicker({
            language: 'zh-CN',
            format: 'yyyy-mm-dd hh:ii',
            initialDate: new Date(),
            autoclose: true,
            todayHighligh:true,
            todayBtn :true, // 显示今日按钮
            autoclose: 1
        })
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

    // 初始化没有分秒的时间框
    function initDatePicker(fieldId){
        $("#"+fieldId).datetimepicker({
            minView: "month", //选择日期后，不会再跳转去选择时分秒
            language: 'zh-CN',
            format: 'yyyy-mm-dd',
            initialDate: new Date(),
            autoclose: true,
            todayHighligh:true,
            todayBtn :true, // 显示今日按钮
            autoclose: 1
        })
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
            showExport: true,  //是否显示导出按钮
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
                    userName : $("#userName").val(),
                    startTime: $("#startTime").val(),
                    endTime: $("#endTime").val()
                };
                return param;
            },
        });
    }
</script>
</body>
</html>

