<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="/aw/img/favicon.html">
    <title>服务异常</title>
    <!-- Bootstrap core CSS -->
    <link href="/aw/css/bootstrap.min.css" rel="stylesheet">
    <link href="/aw/css/bootstrap-reset.css" rel="stylesheet">
    <!--external css-->
    <link href="/aw/assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
    <!-- Custom styles for this template -->
    <link href="/aw/css/style.css" rel="stylesheet">
    <link href="/aw/css/style-responsive.css" rel="stylesheet" />
    <!--[if lte IE 9]>
    <link rel="stylesheet" href="ie-blocker/ie-blocker.css">
    <script src="./ie-blocker/ie-blocker.zhCN.js"></script>
    <![endif]-->
    <![endif]-->
</head>
<body class="body-500">
<div class="container">
    <section class="error-wrapper">
        <i class="icon-500"></i>
        <h3>抱歉，无法响应您的请求！</h3>
        <br>
        <#if error??>
            <div>你请求的访问：${(error.url)!''}</div>
            <div>${(error.message)!''}</div>
        </#if>
        <br>
        <p class="page-500"><a href="/">返回首页</a></p>
    </section>
</div>
</body>
</html>