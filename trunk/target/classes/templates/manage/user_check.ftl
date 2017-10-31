<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>活动登记页</title>
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
    <div id="userForm">
        <div style="padding: 20px 20px 20px 20px">
            <input type="hidden" id="userNo">
            <div class="form-group">
                <label class="control-label" >姓名：</label>
                <input type="text" class="form-control" minlength="1" maxlength="19" placeholder="请输入姓名" style="width: 230px;" id="name">
            </div>
            <div class="form-group">
                <label class="control-label" >手机：</label>
                <input type="number" class="form-control" minlength="11" maxlength="11" placeholder="请输入手机" style="width: 230px;" id="phone">
                <button class="btn btn-success radius" id="code" type="button">发送验证码</button>
            </div>
            <div class="form-group">
                <label class="control-label" >验证码：</label>
                <input type="number" class="form-control" placeholder="请输入短信验证码" style="width: 230px;" id="codeStr">
            </div>
            <#--<div class="form-group" >-->
                <#--<label class="control-label">性别：</label>-->
                <#--<select id="gender" class="js-example-tags form-control" style="width: 230px;" name="maintainOrFix">-->
                        <#--<option value="M">男</option>-->
                        <#--<option value="F">女</option>-->
                <#--</select>-->
            <#--</div>-->
            <div class="form-group">
                <label class="control-label" style="float: left">地址：</label>
                <div style="position: relative;float:left">
                    <input id="address" type="text"  name="companyAddress"/>
                </div>
            </div>
        </div>
        <p style="clear:both"/>
        <div class="form-group" style="margin-left: 230px">
            <button class="btn btn-success radius" id="add" onclick="update('提交成功!', '提交失败!');">提交</button>
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
            initCityPicker('address');
    });

    function refresh() {
        setTimeout(function(){
            location.replace(location.href);
        },800);
    }

    // 获取登录验证码
    $("#code").click(function() {
        var iphone = $("#phone").val(); //获取输入框里的手机号码
        var reg = /^1[3578]\d{9}$/;
        if(reg.test(iphone)) {
            var btn = $(this);
            var count = 60;
            var resend = setInterval(function() {
                count--;
                if(count > 0) {
                    btn.text(count + "秒后获取");
                } else {
                    clearInterval(resend);
                    btn.text("获取验证码").removeAttr('disabled style');
                }
            }, 1000);
            btn.attr('disabled', true).css('cursor', 'not-allowed');
            $.get('/login/code', {
                phone: iphone,
            }, function(json) {});
        } else {
            layer.msg('请输入正确的手机号码！')
        }
    });

    function update(message1, message2){
        var name = $("#name").val();
        var phone = $("#phone").val();
        var address = $("#address").val();
        var code = $("#codeStr").val();
        var pattern = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
            if(name!=undefined&&name!=""&&phone!=undefined&&phone!="") {
                if(pattern.test(phone)){
                    $.post('/manage/activity/checkComit?releasePointId='+"${releasePointId}"+'&campaignId='+"${campaignId}", {
                    name: name,
                    phone: phone,
                    address:address,
                    code : code,
                }, function (res) {
                    if (res.count == 1) {
                        layer.msg(message1)
                        refresh()
                    } else {
                        layer.msg(message2);
                    }
                });
            }else{
                    layer.msg('手机格式不正确！')
            }
        }else{
                layer.msg('数据不完整！')
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

