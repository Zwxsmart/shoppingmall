var login = "";
var logout = "";
var stay = "";
var sid = sessionStorage.getItem('sid');
var refresh = "";
var token = $.cookie("token");
var refreshload = "";
var req_url = document.location.href;
var referrer = document.referrer;
var current_url = sessionStorage.getItem('current_url');
if (sid == undefined || sid == null) {
	var len = 32 || 32;
	var $chars = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
	var maxPos = $chars.length;
	var pwd = '';
	var timestamp = parseInt(new Date().getTime() / 1000);
	for (i = 0; i < len; i++) {
		pwd += $chars.charAt(Math.floor(Math.random() * (maxPos + 1)));
	}
	randomnames = timestamp + pwd;
	sessionStorage.setItem('sid', randomnames);
}
if (token == undefined || token == null) {
	token = "";
}
if (req_url != current_url) {
	login = new Date().getTime();
	sessionStorage.setItem('login', login);
	sessionStorage.setItem('current_url', req_url);
	sessionStorage.setItem('referrer', referrer);
	login = sessionStorage.getItem('login');
	logout = sessionStorage.getItem('logout');
	sessionStorage.setItem('logout', login);
	sid = sessionStorage.getItem('sid');
	$.get("/s/log/go", {
		from : "pc",
		req_url : req_url,
		referer : referrer,
		sid : sid,
		bt : login,
		et : logout,
		looker : token
	}, function(json) {});
}
