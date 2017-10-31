
var ie=document.all;
var nn6=document.getElementByIdx&&!document.all;
var isdrag=false;
var y,x;
var oDragObj;
var mWidth = 400;
var mTop = 0;
function moveMouse(e) {
 if (isdrag) {
 oDragObj.style.top  =  (nn6 ? nTY + e.clientY - y : nTY + event.clientY - y)+"px";
 oDragObj.style.left  =  (nn6 ? nTX + e.clientX - x : nTX + event.clientX - x)+"px";
 return false;
 }
}
function initDrag(e) {
console.log(e);
 var oDragHandle = nn6 ? e.target : event.srcElement;
 console.log(oDragHandle);
 var topElement = "HTML";
 while (oDragHandle.tagName != topElement && oDragHandle.className != "dragAble") {
 oDragHandle = nn6 ? oDragHandle.parentNode : oDragHandle.parentElement;
 }
 if (oDragHandle.className=="dragAble") {
 isdrag = true;
 oDragObj = oDragHandle;
 nTY = parseInt(oDragObj.style.top+mTop);
 y = nn6 ? e.clientY : event.clientY;
 nTX = parseInt(oDragObj.style.left+mWidth);
 x = nn6 ? e.clientX : event.clientX;
 document.onmousemove=moveMouse;
 return false;
 }
}
document.onmousedown=initDrag;
document.onmouseup=new Function("isdrag=false");


/*function onWheelZoom(obj){  //滚轮缩放
zoom = parseFloat(obj.style.zoom);
console.log("zoom:"+zoom);
tZoom = zoom + (event.wheelDelta>0 ? 0.05 : -0.05);
if(tZoom<0.1 ) return true;
obj.style.zoom=tZoom;
return false;
}*/


function bigit(id){
	console.log(id);
	mWidth = 600;
	mTop = -200;
	document.getElementById(id).style.width="100%";
	document.getElementById(id).style.top=mTop+"px";
	document.getElementById(id).style.marginLeft="-"+mWidth+"px";
	$("."+id).removeClass(id);
     
}  

function back(id,obj){
	console.log(id);
	mWidth = 400;
	mTop = 0;
	document.getElementById(id).style.width="800px";
	document.getElementById(id).style.top=mTop+"px";
	document.getElementById(id).style.marginLeft="-"+mWidth+"px";
	$(obj).addClass(id);

}  
