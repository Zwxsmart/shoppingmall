<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>统计详情</title>
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
                <th data-width="100" data-field="pointName">投放点</th>
                <th data-width="100" data-field="title">活动</th>
                <th data-width="100" data-field="checkCount">登记人数</th>
                <th data-width="100" data-field="browseQuantity">浏览次数</th>
            </tr>
            </thead>
        </table>
    </div>
    <div class="form-group" style="float: left; ">
        <label class="control-label" style="float: left;margin-left: 20px;">登记用户信息：</label>
    </div>
    <p/>
    <div class="form-group" id="activityDiv">
        <label class="control-label" style="margin-top: 50px;font-weight:100;font-size:16px;">请在上方表格中任意选中一行！</label>
    </div>
    <div class="panel-body" id="activityTable" style="padding-bottom:0px;display: none">
        <table id="table1" style="table-layout: fixed">
            <thead>
            <tr>
                <th data-width="100" data-field="pointName">投放点</th>
                <th data-width="100" data-field="name">用户</th>
                <th data-width="100" data-field="phone">手机</th>
                <th data-width="100" data-formatter="addressFormatter">地址</th>
            </tr>
            </thead>
        </table>
    </div>
</div>
<script type="text/javascript" src="/aw/js/jquery-3.1.0.js"></script>
<script type="text/javascript" src="/aw/js/jquery.form.js"></script>
<script src="/aw/js/bootstrap.min.js"></script>
<script src="/aw/assets/layer/layer.js"></script>
<script src="/aw/assets/icheck/jquery.icheck.min.js"></script>
<script src="/aw/assets/bootstrap-tables/js/bootstrap-table.min.js"></script>
<script src="/aw/assets/bootstrap-tables/js/bootstrap-table-zh-CN.min.js"></script>
<script src="/aw/assets/bootstrap-tables/js/jquery.dataTables.min.js"></script>
<script src="/aw/assets/bootstrap-tables/js/dataTables.bootstrap.min.js"></script>
<script type="text/javascript" charset="utf-8">
    $(document).ready(function() {
       initTable("table", "/manage/webSetting/queryByActivityIdList?id="+'${id}');
    } );


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
            onClickRow: function (row) {
                $("#activityDiv").css("display","none");
                $("#activityTable").css("display","block");
                initTable1("table1", "/manage/webSetting/queryByOnclick?releasePointId="+row.releasePointId+"&campaignId="+row.campaignId);
            }
        });
    }

    function initTable1(tableId, url) {
        //先销毁表格
        $('#' + tableId).bootstrapTable('destroy');
        //初始化表格,动态从服务器加载数据
        $("#" + tableId).bootstrapTable({
            method: "get",  //使用get请求到服务器获取数据
            url: url, //获取数据的Servlet地址
            striped: false,  //表格显示条纹
            pagination: false, //启动分页
            showColumns: false,  //显示下拉框勾选要显示的列
            showRefresh: false,  //显示刷新按钮
            showToggle: false, // 显示详情
            strictSearch: true,
            clickToSelect: true,  //是否启用点击选中行
            uniqueId: "id",                     //每一行的唯一标识，一般为主键列
            sortable: true,                     //是否启用排序
            sortOrder: "asc",                   //排序方式
            sidePagination: "server", //表示服务端请求
            //设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder
            //设置为limit可以获取limit, offset, search, sort, order
        });
    }

    function addressFormatter(value, row, index) {
        var country;
        var province;
        var city;
        if(row.country ==null | row.country ==""){
            country = "";
        }else{
            country = row.country;
        }
        if(row.province ==null | row.province ==""){
            province="";
        }else{
            province = "/"+row.province;
        }
        if(row.city ==null | row.city ==""){
            city="";
        }else{
            city = "/"+row.city;
        }
        return country+province+city;
    }
</script>
</body>
</html>

