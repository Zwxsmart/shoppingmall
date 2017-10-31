function getQrCode(id) {
	var uuid =Math.random() * 100000000000000000;
    $("#"+id).qrcode({
        render: "canvas",
        width: 200,
        height: 200,
        text: "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx9c8f9ca45ad8086f&redirect_uri=http://shoppingmall.jiajushouye.com/showWechatLogin&response_type=code&scope=snsapi_base&state="+uuid+"#wechat_redirect"
    });
    validateLogin(uuid); // 设置一个参数, 后台开启线程, 当Map中多出一个key为此uuid的键值, 则代表登录成功
}
//
// function getQrCode(id) {
//     var uuid =Math.random() * 100000000000000000;
//     $("#"+id).qrcode({
//         render: "canvas",
//         width: 200,
//         height: 200,
//         text: "https://mp.weixin.qq.com/mp/profile_ext?action=home&__biz=MzIyNTc1MDQ2Mw==&scene=110#wechat_redirect"
//     });
//     validateLogin(uuid); // 设置一个参数, 后台开启线程, 当Map中多出一个key为此uuid的键值, 则代表登录成功
// }

function validateLogin(uuid){
    $.get("/qrCodeStr?uuid="+ uuid , function(data) {
        if(data.code == 0){
            document.location.href = "index.html";
        }else if(data.code == 101){
            $("#openId").val(data.openId);
            $("#openLogin").addClass("g_toget");
            $("#openLogin1").removeClass("g_toget");
        }else if(data.code == 102){
            $("#openId1").val(data.openId);
            $("#openLogin").addClass("g_toget");
            $("#openLogin2").removeClass("g_toget");
        }else if(data.code == 103){
            $("#imgQRcode").empty();
            $("#imgQRcode").qrcode({
                render: "canvas",
                width: 200,
                height: 200,
                text: "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx9c8f9ca45ad8086f&redirect_uri=http://shoppingmall.jiajushouye.com/showWechatLogin&response_type=code&scope=snsapi_base&state="+uuid+"#wechat_redirect"
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
