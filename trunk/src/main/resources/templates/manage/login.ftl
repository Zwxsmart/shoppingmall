<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title> 联盟国际后台管理 | 登陆</title>
    <meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, width=device-width">
    <link rel="stylesheet" href="/aw/css/bootstrap-3.2.0.css" />
    <link rel="stylesheet" href="/aw/css/icons.css"  />
    <script type="text/javascript" src="/aw/js/jquery-3.1.0.js"></script>
    <script type="text/javascript" src="/aw/js/loaders.css.js"></script>
    <script src="/aw/assets/layer/layer.js"></script>
    <!--[if lte IE 9]>
    <link rel="stylesheet" href="/aw/ie-blocker/ie-blocker.css">
    <script src="/aw/ie-blocker/ie-blocker.zhCN.js"></script>
    <![endif]-->
</head>
<body class="ember-application">
<div class="ember-view" id="ember438">
    <div class="ember-view" id="ember457">
        <div class="light-gray-bckd" style="background-image: url('/pic/bg4.jpg'); -moz-background-size:100% 100%; background-size:100% 100%;">
            <div class="content">
                <div class="login-wrapper">
                    <img class="particle-login-logo" src="/pic/logo.png">
                        <label for="identification">用户账号</label>
                        <input class="ember-view ember-text-field" aria-required="true" required="" style="background:none !important" placeholder="用户账号" id="username" type="text">
                        <label for="password">登陆密码</label>
                        <input class="ember-view ember-text-field" aria-required="true" required="" style="background:none !important" placeholder="••••••••" id="password" type="password">
                        <button class="btn" onclick="login()" id="login">登陆</button>
                    <a href="#" target="_blank" class="forgot-password"></a>
                    <a href="#" target="_blank" class="forgot-password"></a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    $(function () {
        var val = window.parent.document.getElementById("login")
        if(val==null){
            parent.location.replace(location.href)
        }
        document.onkeydown = function(e){
            if(!e){
                e = window.event;
            }
            if((e.keyCode || e.which) == 13){
                login();
            }
        }
    })

    function login() {
        var username = document.getElementById("username").value;
        var password = document.getElementById("password").value;
        $.post("/manage/login",{"username":username.trim(),"password":password},function(res){
            if(res.returnStatus.status == 0){
                console.log(res)
                location.href = "/manage/index";
            }else if(res.returnStatus.status == 1){
                layer.msg(res.returnStatus.info);
            }
        })
    }
</script>
</html>

