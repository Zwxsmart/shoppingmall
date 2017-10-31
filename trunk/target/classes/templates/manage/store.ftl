<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>店铺管理</title>
    <meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, width=device-width">
    <link rel="stylesheet" href="/aw/assets/bootstrap-tables/css/bootstrap-table.css">
    <link rel="stylesheet" href="/aw/assets/bootstrap-dateTimePicker/css/bootstrap-datetimepicker.min.css">
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
                <#--<th data-width="150" data-field="showUrl1" data-formatter="formatterImg">店铺图片</th>-->
                <th data-width="100" data-field="name">店铺名称</th>
                <th data-width="200" data-field="brandName">店铺品牌</th>
                <th data-width="110" data-field="principal">店铺负责人</th>
                <#--<th data-width="110" data-field="contacter">店铺联系人</th>-->
                <th data-width="125" data-field="contactPhone">联系电话</th>
                <#--<th data-width="125" data-field="storePhone">店铺电话</th>-->
                <th data-width="80" data-field="gender">浏览次数</th>
                <th data-width="80" data-field="goodsTotal">商品数量</th>
                <th data-width="140" data-field="provideService">提供服务</th>
                <th data-width="160" data-formatter="addressFormatter">店铺地址</th>
                <#--<th data-width="140" data-field="address">详细地址</th>-->
                <th data-width="100" data-field="floor" data-formatter="floorFormatter">商场楼层</th>
                <th data-width="110" data-field="doorplate">店铺门牌号</th>
                <th data-width="130" data-field="openingHours">营业时间</th>
                <#--<th data-width="150" data-field="createDate" data-formatter="formatterDate">创建时间</th>-->
                <#--<th data-width="150" data-field="updateDate" data-formatter="formatterDate">更新日期</th>-->
                <th data-width="80" data-field="isValid" data-formatter="statusFormatter">状态</th>
                <th data-width="170" data-field="isValid" data-formatter="operateFormatter">
                    操作
                </th>
            </tr>
            </thead>
        </table>
        <div id="toolbar" class="btn-group">
                <span style="float: left;margin-top: 7px;">时间：</span>
                <input id="startTime" placeholder="请选择开始时间"
                       readonly="true" type="text" name="startTime"
                       class="form-control" style="width:170px;float: left"/>
                <span style="float: left;margin-top: 7px;margin-left: 3px; margin-right: 3px;">至</span>
                <input id="endTime" placeholder="请选择结束时间"
                       readonly="true" type="text" name="endTime"
                       class="form-control" style="width:170px;float: left"/>

                <span style="padding-left: 15px;float: left;margin-top: 7px;">店铺风格：</span>
                <select id="storeStyle" class="js-example-tags form-control"  style="width: 110px;float: left" >
                    <option value=0>所有风格</option>
                    <#list styles as s>
                        <option value=${s.goodsStyleNo}>${s.styleName}</option>
                    </#list>
                </select>
                <span style="padding-left: 15px;float: left;margin-top: 7px;">场馆位置：</span>
                <select id="floor" class="js-example-tags form-control"  style="width: 110px;float: left" name="floor">
                    <option value='0'>所有位置</option>
                    <#list 1..9 as t>
                            <option value=${t}>${t}F</option>
                    </#list>
                </select>
                <span style="padding-left: 15px;float: left;margin-top: 7px;">品牌名称：</span>
                <input id="brandName" class="form-control" style="width: 110px;float: left">

                <button id="btn_add" type="button" class="btn btn-default" onclick="blurredQuery();" style="margin-left: 30px;float: left">
                    <span class="glyphicon glyphicon-search" aria-hidden="true" ></span>查找
                </button>
                <button id="btn_add" type="button" class="btn btn-default" onclick="showAdd();" style="float: left">
                    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
                </button>
                <form id="action"  method="post" enctype="multipart/form-data" style="float:left;">
                    <div class="report-file" id="btn_file" style="border-left:none;" onmousemove="this.style.backgroundColor = '#eee';" onmouseout="this.style.backgroundColor = '#fff'">
                        <span class="glyphicon glyphicon-save">导入</span>
                        <input tabindex="3" size="3" name="txt_file" class="file-prew" id="fileExcel" type="file" onchange="toLeading(this);">
                    </div>
                    <input type="text" id="textName" readonly="true" placeholder="点击导入按钮选择文件" style="height: 34px;float:left;border:1px solid #ccc" />
                    <button id="btn_leading" type="button" class="btn btn-default" onclick="lead();"style="float:left;display: none;border-left:none;">
                        <span aria-hidden="true"></span>上传
                    </button>
                </form>
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
<script src="/aw/assets/bootstrap-export/bootstrap-table-export.min.js"></script>
<script src="/aw/assets/tableExport/tableExport.min.js"></script>
<script src="/aw/assets/bootstrap-dateTimePicker/js/bootstrap-datetimepicker.min.js"></script>
<script src="/aw/assets/bootstrap-dateTimePicker/js/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript" charset="utf-8">
    $(document).ready(function() {
       initTable("table", "/manage/store/storeList");
        initDatePicker("startTime");
        initDatePicker("endTime");
    } );

    function blurredQuery(){
        initTable('table', '/manage/store/storeList');
    }

    function toLeading(o) {
        $("#textName").val(o.value)
        $("#btn_leading").css("display","block");
    }

    function lead() {
        //首先验证文件格式
        var fileName = $('#fileExcel').val();
        if (fileName == '' || fileName == undefined) {
            layer.msg('请先选择文件！');
            return false;
        }
        var fileType = (fileName.substring(fileName
                .lastIndexOf(".") + 1, fileName.length))
                .toLowerCase();
        if (fileType != 'xls' && fileType != 'xlsx') {
            layer.msg('文件格式不正确,必须是excel文件！');
            return false;
        }

        $("#action").ajaxSubmit({
            url: "/manage/store/readExcel",
            type: "POST",
            success : function(data) {
                if (data.returnStatus.code=='0') {
                    layer.msg('导入成功！');
                    $('#table').bootstrapTable('refresh');
                }else {
                    layer.msg('导入失败！');
                }
                return false;
            }
        });
        return false;
    }

    function showAdd(){
        layer.open({
            type: 2,
            title: '添加店铺信息',
            shadeClose: false,
            shade: 0.8,
            area: ['90%', '90%'],
            content: "/manage/store/queryById", //iframe的url
            end: function () {
                location.reload();
            }
        });
    }

    function showEdit(url){
        layer.open({
            type: 2,
            title: '修改店铺信息',
            shadeClose: false,
            shade: 0.8,
            area: ['90%', '90%'],
            content: url, //iframe的url
            end: function () {
                location.reload();
            }
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

    // 激活或冻结
    function operateFormatter(value, row, index) {
        if(value == '1') {
            return "<button type='button' class='btn btn-default' onclick='showEdit(\""+'/manage/store/queryById?storeId='+row.storeId+"\")'>编辑</button>&nbsp;&nbsp;<button type='button' class='btn btn-danger' onclick='inactive(\""+'/manage/store/status?storeId='+row.storeId+'&status=Y'+ "\")'>冻结</button>";
        } else {
            return "<button type='button' class='btn btn-default' onclick='showEdit(\""+'/manage/store/queryById?storeId='+row.storeId+"\")'>编辑</button>&nbsp;&nbsp;<button type='button' class='btn btn-success' onclick='active(\""+'/manage/store/status?storeId='+ row.storeId+'&status=N'+ "\")'>激活</button>";
        }
    }

    // 冻结
    function inactive(url) {
        $.post(url, function(data){
                    if(data.count == 1){
                        layer.msg('冻结成功！', {time : 1000});
                        $('#table').bootstrapTable('refresh');
                    }else{
                        layer.msg('冻结失败！', {time : 1000});
                    }
                },"json");
    }
    // 激活
    function active(url) {
        $.post(url, function(data){
                    if(data.count == 1){
                        $('#table').bootstrapTable('refresh');
                        layer.msg('激活成功！', {time : 1000});
                    }else{
                        layer.msg('激活失败！', {time : 1000});
                    }
                },"json");
    }

    function statusFormatter(value, row, index) {
        if(value == '1') {
            return "可用";
        } else {
            return "冻结";
        }
    }

    function floorFormatter(value, row, index) {
        if(value!= null){
            return value+"F";
        }
        return "-";
    }

    function genderFormatter(value, row, index) {
        if(value == 'M') {
            return "男";
        } else {
            return "女";
        }
    }

    function addressFormatter(value, row, index) {
        if(row.country!=""&&row.province!=""&&row.city!=""){
            return row.country+"/"+row.province+"/"+row.city;
        }
        if(row.country!=""&&row.province!=""){
            return row.country+"/"+row.province;
        }
        if(row.country!=""){
            return row.country;
        }
        return "";
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

    /**
     * 时间格式化，传递进来的时间
     */
    function formatterDate1(value) {
        if (value == undefined || value == null || value == '') {
            return "";
        }
        else {
            var date = new Date(value);
            var year = date.getFullYear().toString();
            var month = (date.getMonth() + 1);
            var day = date.getDate().toString();
            if (month < 10) {
                month = "0" + month;
            }
            if (day < 10) {
                day = "0" + day;
            }
            return year + "/" + month + "/" + day;
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

    function formatterImg(value, row, index) {
        return [
            '<img style="width:120px;height:60px;" src="' + value + '">'
        ]
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
            pageList: [10, 15, 20, 50, 100],  //记录数可选列表
            showColumns: true,  //显示下拉框勾选要显示的列
            showRefresh: true,  //显示刷新按钮
            showToggle: true, // 显示详情
            showExport: true,  //是否显示导出按钮
            exportDataType:'all',
            Icons:'glyphicon-export',
            exportTypes:['excel'],
            exportOptions:{
                ignoreColumn: [12],  //忽略某一列的索引
                fileName: '所有店铺信息',  //文件名称设置
                worksheetName: 'sheet1',  //表格工作区名称
                tableName: '所有店铺信息',
                excelstyles: ['background-color', 'color', 'font-size', 'font-weight'],
            },
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
                    floor : $("#floor").val(),
                    storeStyle : $("#storeStyle").val(),
                    brandName : $("#brandName").val(),
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

