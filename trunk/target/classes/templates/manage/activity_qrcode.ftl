<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>活动二维码</title>
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
                <th data-width="150" data-field="pointName">投放点名称</th>
                <th data-width="70" data-formatter="formatterCode">详情二维码图片</th>
                <th data-width="70" data-formatter="formatterCode1">登记二维码图片</th>
                <th data-width="70" data-field="status" data-formatter="operateFormatter">
                    操作
                </th>
            </tr>
            </thead>
        </table>
        <div id="qrCodeDiv" style="display: none"></div>
    </div>
</body>
<script type="text/javascript" charset="utf-8" src="/aw/assets/qrCode/jquery.qrcode.js"></script>
<script type="text/javascript" charset="utf-8" src="/aw/assets/md5/md5.js"></script>
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
        initTable("table", "/manage/webSetting/queryQrCodeList?id="+'${id}');
    } );

    function refresh() {
        setTimeout(function(){
            location.replace(location.href);
        },800);
    }

    function formatterCode(value, row, index) { // 0为详情
        return [
            "<button type='button' class='btn btn-primary' onclick='showDow(\""+row.id+'","'+row.campaignId+'","'+'details'+"\")'>下载</button>"
        ]
    }

    function formatterCode1(value, row, index) { // 1为登记
        return [
            "<button type='button' class='btn btn-primary' onclick='showDow(\""+row.id+'","'+row.campaignId+'","'+'check'+"\")'>下载</button>"
        ]
    }

    function operateFormatter(value, row, index) {
        if(value >0 ) {
            return "已投放";
        } else {
            return "<button type='button' class='btn btn-success' onclick='showPoint(\""+'/manage/webSetting/putActivity?pointId='+ row.id+'&camId='+'${id}'+"\")'>投放</button>";
        }
    }

    // 投放
    function showPoint(url) {
        $.post(url, function(data){
            if(data.count == 1){
                $('#table').bootstrapTable('refresh');
                layer.msg('投放成功！');
            }else{
                layer.msg('投放失败！');
            }
        },"json");
    }

    function showDow(releasePointId,campaignId, codeType) {
        $("#qrCodeDiv").html("");
        var fileName="";
        var autograph = hex_md5("campaignId="+campaignId+"&releasePointId="+releasePointId);
        if(codeType=='details'){// 0为详情
            $("#qrCodeDiv").qrcode({
                render: "canvas",
                width: 200,
                height: 200,
                text: "http://shoppingmall.jiajushouye.com/manage/activity/queryActivityPage?campaignId="+campaignId+"&releasePointId="+releasePointId+"&autograph="+autograph
//                text: "http://192.168.1.24:12080/manage/activity/queryActivityPage?campaignId="+campaignId+"&releasePointId="+releasePointId+"&autograph="+autograph
            });
            fileName="详情二维码_";
        }else{
            $("#qrCodeDiv").qrcode({
                render: "canvas",
                width: 200,
                height: 200,
                text: "http://shoppingmall.jiajushouye.com/manage/activity/queryCheckPage?campaignId="+campaignId+"&releasePointId="+releasePointId+"&autograph="+autograph
//                text: "http://192.168.1.24:12080/manage/activity/queryCheckPage?campaignId="+campaignId+"&releasePointId="+releasePointId+"&autograph="+autograph
            });
            fileName="登记二维码_";
        }
        var canvas = $("#qrCodeDiv").find("canvas").get(0);
        // 图片导出为 png 格式
        var type = 'png';
        // 返回一个包含JPG图片的<img>元素
                var img_png_src = canvas.toDataURL("image/png");  //将画板保存为图片格式的函数
        // 加工image data，替换mime type
                imgData = img_png_src.replace(_fixType(type),'image/octet-stream');
        // 下载后的问题名
                var filename = fileName + (new Date()).getTime() + '.' + type;
        // download
        saveFile(imgData,filename);
    }

    /**
     * 在本地进行文件保存
     * @param  {String} data     要保存到本地的图片数据
     * @param  {String} filename 文件名
     */
    var saveFile = function(data, filename){
        var save_link = document.createElementNS('http://www.w3.org/1999/xhtml', 'a');
        save_link.href = data;
        save_link.download = filename;

        var event = document.createEvent('MouseEvents');
        event.initMouseEvent('click', true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);
        save_link.dispatchEvent(event);
    };

    /**
     * 获取mimeType
     * @param  {String} type the old mime-type
     * @return the new mime-type
     */
    var _fixType = function(type) {
        type = type.toLowerCase().replace(/jpg/i, 'jpeg');
        var r = type.match(/png|jpeg|bmp|gif/)[0];
        return 'image/' + r;
    };

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

</script>
</html>