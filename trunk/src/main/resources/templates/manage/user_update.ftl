<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>会员新增或修改</title>
    <meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, width=device-width">
    <link rel="stylesheet" href="/aw/assets/bootstrap-tables/css/bootstrap-table.css">
    <link rel="stylesheet" href="/aw/assets/bootstrap-dateTimePicker/css/bootstrap-datetimepicker.min.css">
    <link rel="stylesheet" href="/aw/css/bootstrap.min.css">
    <link rel="stylesheet" href="/aw/assets/city-data/css/city-picker.css">
    <style>
        .city-picker-span{
            width: 230px;height:34px;line-height:33px;
        }
    </style>
</head>
<body>
    <div class="form-horizontal" role="form" id="userForm" style="text-align: center">
        <div style="padding: 20px 20px 20px 20px">
            <input type="hidden" value="${(user.userNo)!''}" id="userNo">
            <div class="form-group">
                <label class="control-label" >姓名：</label>
                <input type="text" class="form-control" minlength="1" maxlength="19" placeholder="请输入姓名" style="width: 230px;" value="${(user.name)!''}" id="name">
            </div>
            <div class="form-group" >
                <label class="control-label" >昵称：</label>
                <input type="text" class="form-control" minlength="1" maxlength="29" placeholder="请输入昵称"  style="width: 230px;" value="${(user.nick)!''}" id="nick">
            </div>
            <div class="form-group">
                <label class="control-label" >手机：</label>
                <input type="number" class="form-control" minlength="11" maxlength="11" placeholder="请输入手机" style="width: 230px;" value="${(user.phone)!''}" id="phone">
            </div>
            <div class="form-group" >
                <label class="control-label">性别：</label>
                <select id="gender" class="js-example-tags form-control" style="width: 230px;" name="maintainOrFix">
                    <#if user??>
                        <#if user.gender == 'M'>
                            <option value="M" selected="selected">男</option><option value="F">女</option>
                        <#else>
                            <option value="F" selected="selected">女</option><option value="M">男</option>
                        </#if>
                    <#else>
                        <option value="M">男</option>
                        <option value="F">女</option>
                    </#if>
                </select>
            </div>
            <div class="form-group" style="margin-left: 145px;" >
                <label class="control-label" style="float: left">地址：</label>
                <div style="position: relative;float:left">
                    <input id="address" type="text"  name="companyAddress"/>
                </div>
            </div>
            <div class="form-group" >
                <label class="control-label" >出生年月：</label>
                <input type="text" class="form-control" maxlength="8" value="${(user.birth)!''}" style="width: 200px;" placeholder="请输入出生年月"  id="birth">
            </div>
        </div>
        <p style="clear:both"/>
        <div class="form-group" style="margin-left: 230px">
        <#if (user.name)??>
            <button class="btn btn-success radius" id="update" onclick="update('修改成功!', '修改失败!');" value="${(user.userNo)!''}">修改</button>
        <#else>
            <button class="btn btn-success radius" id="add" onclick="update('添加成功!', '添加失败!');">添加</button>
        </#if>
        </div>
    </div>
    </div>
<script type="text/javascript" src="/aw/js/jquery-1.8.3.min.js"></script>
<script src="/aw/js/bootstrap.min.js"></script>
<script src="/aw/assets/layer/layer.js"></script>
<script src="/aw/assets/icheck/jquery.icheck.min.js"></script>
<script src="/aw/assets/bootstrap-tables/js/bootstrap-table.min.js"></script>
<script src="/aw/assets/bootstrap-tables/js/bootstrap-table-zh-CN.min.js"></script>
<script src="/aw/assets/bootstrap-dateTimePicker/js/bootstrap-datetimepicker.min.js"></script>
<script src="/aw/assets/bootstrap-dateTimePicker/js/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="/aw/assets/bootstrap-tables/js/jquery.dataTables.min.js"></script>
<script src="/aw/assets/bootstrap-tables/js/dataTables.bootstrap.min.js"></script>
<script src="/aw/assets/city-data/js/city-picker.data.js"></script>
<script src="/aw/assets/city-data/js/city-picker.js"></script>
<script type="text/javascript" charset="utf-8">

    $(function () {
            if('${user.country}' != "kong"){
                var country = '${user.country}';
                var province ='${user.province}';
                var city = '${user.city}';
                var address = country+"/"+province+"/"+city;
                $("#address").val(address)
            }
            initCityPicker('address');
    });

    function refresh() {
        setTimeout(function(){
            location.replace(location.href);
        },800);
    }

    function blurredQuery(){
        initTable('table', '/manage/user/userList');
    }

    function update(message1, message2){
        var userNo = $("#userNo").val();
        var name = $("#name").val();
        var nick = $("#nick").val();
        var gender = $("#gender").val();
        var birth = $("#birth").val();
        var phone = $("#phone").val();
        var address = $("#address").val();
        var pattern = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
            if(name!=undefined&&name!=""&&nick!=undefined&&nick!=""&&gender!=undefined&&gender!=""&&birth!=undefined&&birth!=""&&phone!=undefined&&phone!=""&&address!=undefined&&address!="") {
                if(pattern.test(phone)){
                    $.post('/manage/user/saveOrUpdate', {
                    userNo: userNo,
                    name: name,
                    nick: nick,
                    gender: gender,
                    birth: birth,
                    phone: phone,
                    address:address,
                }, function (res) {
                    if (res.count == 1) {
                        layer.msg(message1)
                        refresh()
                        $('#table').bootstrapTable('refresh');
                    } else {
                        layer.msg(message2);
                    }
                });
            }else{
                    layer.msg('手机格式不正确')
            }
        }else{
                layer.msg('数据不完整')
        }
    }

    /** 初始化三级地区联动 */
    function initCityPicker(id) {
        $('#' + id).citypicker('destroy');
        $('#' + id).citypicker();
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
            return year + "-" + month + "-" + day + " " + hour + ":" + minutes + ":" + seconds;
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

</script>
</body>
</html>

