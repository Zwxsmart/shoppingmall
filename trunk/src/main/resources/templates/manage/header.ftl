<#--页面头部菜单-->
<#include "../config/constant.ftl" />
<style>
    .my_document >span{ display:block; height:30px; line-height: 30px; color:#bbbbbb;}
    .my_document span > a:hover, .my_document span > a:focus{ background-color:#141C32 !important; color:#cacaca;}
</style>

<style type="text/css">
    .dropdown-submenu {
        position: relative;
    }
    .dropdown-submenu > .dropdown-menu {
        top: 0;
        left: 100%;
        margin-top: -6px;
        margin-left: -1px;
        -webkit-border-radius: 0 6px 6px 6px;
        -moz-border-radius: 0 6px 6px;
        border-radius: 0 6px 6px 6px;
    }
    .dropdown-submenu:hover > .dropdown-menu {
        display: block;
    }
    .right{
        right: 159px!important;
    }
    .float-right{
        float: right;
    }
    .dropdown-submenu > a:after {
        display: block;
        content: " ";
        float: right;
        width: 0;
        height: 0;
        border-color: transparent;
        border-style: solid;
        border-width: 5px 0 5px 5px;
        border-left-color: #ccc;
        margin-top: 5px;
        margin-right: -10px;
        transform:rotate(180deg);
        -ms-transform:rotate(180deg); 	/* IE 9 */
        -moz-transform:rotate(180deg); 	/* Firefox */
        -webkit-transform:rotate(180deg); /* Safari 和 Chrome */
        -o-transform:rotate(180deg); 	/* Opera */

    }
    .dropdown-submenu:hover > a:after {
        border-left-color: #fff;
    }
    .dropdown-submenu.pull-left {
        float: none;
    }
    .dropdown-submenu.pull-left > .dropdown-menu {
        left: -100%;
        margin-left: 10px;
        -webkit-border-radius: 6px 0 6px 6px;
        -moz-border-radius: 6px 0 6px 6px;
        border-radius: 6px 0 6px 6px;
    }
</style>

<header class="header black-bg">
    <!--logo start-->
    <a href="/manage/index" class="logo">Home<span>page</span></a>
    <!--logo end-->
    <div class="top-nav">
        <ul class="nav pull-left navbar-nav nav-header">
            <li class="long-string"></li>
        </ul>
        <ul class="nav nav-pills" role="tablist">
			<li class="dropdown pull-right my_document">
			<span>你好，${(operName)}  <a href="/manage/logout">[退出]</a></span>
			</li>
        </ul>
    </div>
</header>

