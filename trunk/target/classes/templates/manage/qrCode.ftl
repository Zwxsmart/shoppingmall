<!DOCTYPE html>
<html>
<head>
  <head>
    <title>Title</title>
      <script type="text/javascript" src="/aw/js/jquery.js"></script>
      <script type="text/javascript" src="/aw/js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="/aw/assets/qrCode/jquery.qrcode.js"></script>
    <script>
        //绑定微信登陆

        $(function() {
            var uuid =Math.random() * 100000000000000000;
            alert(uuid)
            $("#imgQRcode").qrcode({
                render: "canvas",
                width: 300,
                height: 300,
                text: "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx120e149fbd0421e7&redirect_uri=http://zwx.jiajushouye.com/showWechatLogin&response_type=code&scope=snsapi_base&state="+uuid+"#wechat_redirect"
            });
            validateLogin(uuid); // 设置一个参数, 后台开启线程, 当Map中多出一个key为此uuid的键值, 则代表登录成功
        })

        function validateLogin(uuid){
            $.get("/manage/webSetting/qrCodeStr?uuid="+ uuid , function(data) {
                if(data.code == 0){
                    alert("登录成功了");
                }else if(data.code == 101){
                    alert("新增用户");
                }else if(data.code == 102){
                    alert("修改用户");
                }else if(data.code == 103){
                    $("#imgQRcode").empty();
                    $("#imgQRcode").qrcode({
                        render: "canvas",
                        width: 300,
                        height: 300,
                        text: "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx120e149fbd0421e7&redirect_uri=http://zwx.jiajushouye.com/showLose&response_type=code&scope=snsapi_base&state="+uuid+"#wechat_redirect"
                    });
                }else{
                    validateLogin(uuid);
                }
            });
        }

        // 当text为中文时, 使用此方法转换
        function toUtf8(str) {
            var out, i, len, c;
            out = "";
            len = str.length;
            for(i = 0; i < len; i++) {
                c = str.charCodeAt(i);
                if ((c >= 0x0001) && (c <= 0x007F)) {
                    out += str.charAt(i);
                } else if (c > 0x07FF) {
                    out += String.fromCharCode(0xE0 | ((c >> 12) & 0x0F));
                    out += String.fromCharCode(0x80 | ((c >>  6) & 0x3F));
                    out += String.fromCharCode(0x80 | ((c >>  0) & 0x3F));
                } else {
                    out += String.fromCharCode(0xC0 | ((c >>  6) & 0x1F));
                    out += String.fromCharCode(0x80 | ((c >>  0) & 0x3F));
                }
            }
            return out;
        }
    </script>
  </head>
<body>
<div id="imgQRcode"></div>
</body>
</html>
