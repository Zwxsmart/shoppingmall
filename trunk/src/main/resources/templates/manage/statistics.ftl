<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>数据统计</title>
    <link rel="stylesheet" href="/aw/assets/bootstrap-tables/css/bootstrap-table.min.css">
    <link rel="stylesheet" href="/aw/assets/datepicker/css/bootstrap-datepicker.min.css">
    <link rel="stylesheet" href="/aw/assets/icheck/icheck.css">
    <link rel="stylesheet" href="/aw/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/tableSimple.css">
    <script type="text/javascript" src="/aw/js/jquery-3.1.0.js"></script>
    <script src="/aw/js/bootstrap.min.js"></script>
    <script src="/aw/assets/layer/layer.js"></script>
    <script src="/aw/assets/icheck/jquery.icheck.min.js"></script>
    <script src="/aw/assets/bootstrap-tables/js/bootstrap-table.min.js"></script>
    <script src="/aw/assets/bootstrap-tables/js/bootstrap-table-zh-CN.min.js"></script>
    <script src="/aw/assets/datepicker/js/bootstrap-datepicker.min.js"></script>
    <script src="/aw/assets/datepicker/locales/bootstrap-datepicker.zh-CN.min.js"></script>
</head>
<body>
<div class="table-width">
    <div class="navbar-form navbar-left" id="toolbar">
        <div class="input-group input-daterange">
        	<span class="input-group-addon" style="background-color: white;border:0px;">时间：</span>
        	<input type="text" class="form-control" id="startTime" placeholder="开始时间">
            <span class="input-group-addon" style="background-color: white;border:0px;">至</span>
            <input type="text" class="form-control" id="endTime"  placeholder="结束时间">
        </div>
        <span style="margin-left:20px;">类目：</span>
        <select id="category" class="form-control">
            <#list category as c>
        		<option value="${c.categoryNo}">${c.categoryName}</option>
        	</#list>
        </select>
        <button id="statistics" class="btn btn-success">统计</button>
    </div>
    
    <table id="statisticsTable" class="table table-striped"></table>
</div>

<script type="text/javascript">
    (function () {
        var $ = jQuery;
        $sTable = $("#statisticsTable"),
        $statistics = $("#statistics");
        window.onload = function () {
            init()
        }

        $('.input-daterange').datepicker({
            format: "yyyy-mm-dd",
            startDate: "2017-01-01",
            language: "zh-CN"
        });

        function init() {
            $sTable.bootstrapTable({
            	url: "/manage/statistics/data?categoryNo="+$("#category").find('option:selected').val(),
                paginationLoop: false,
                smartDisplay: false,
                sidePagination: "server",
                pagination: false,
                uniqueId: 'id',
                showFooter: true,
                clickToSelect: false,
                columns: [{
                    field: 'id',
                    title: '序号',
                    formatter: function (value, row, index) {
                    	row.id = index;
                        return index + 1;
                    }
                },{
                    field: 'categoryName',
                    title: '类目'
                }, {
                    field: 'total',
                    title: '数量'
                }]
            });
            
            $statistics.on('click',function () {
                var startTime = $("#startTime").val();
                var endTime = $("#endTime").val();
                var categoryNo = $("#category").find('option:selected').val();
				$sTable.bootstrapTable("refreshOptions", {
					url : "/manage/statistics/data?categoryNo="+categoryNo+"&startTime="+startTime+"&endTime="+endTime
				});
			});
		}

	})()
</script>
</body>
</html>