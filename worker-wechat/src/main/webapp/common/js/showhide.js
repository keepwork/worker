// JavaScript Document
/* 20130225 zhouxu */
/*选项卡用js*/
function nTabs(thisObj, Num, active, normal) {
	if (thisObj.className == active) {
		return;
	}
	var tabObj = thisObj.parentNode.id;
	var tabList = document.getElementById(tabObj).getElementsByTagName("li");
	for (i = 0; i < tabList.length; i++) {
		if (i == Num) {
			thisObj.className = active;
			document.getElementById(tabObj + "_Content" + i).style.display = "block";
		} else {
			tabList[i].className = normal;
			document.getElementById(tabObj + "_Content" + i).style.display = "none";
		}
	}
}
/* 弹出层*/
function show(cover, id) {
	
	var objCover = document.getElementById(cover);
	var objId = document.getElementById(id);
	var scrollW = jQuery(document).width();
	var scrollH = jQuery(document).height();
	var T = (jQuery(window).height() - jQuery("#" + id).height()) / 2 + jQuery(document).scrollTop();
	var L = (jQuery(window).width() - jQuery("#" + id).width()) / 2 + jQuery(document).scrollLeft();
	objCover.style.width = scrollW + "px";
	objCover.style.height = scrollH  + "px";
	objCover.style.visibility = "visible";
	if(new Number(T)<0){
	      T=0;
	}
	objId.style.top = T + "px";
	objId.style.left = L + "px";
	objId.style.display = "block";
}
function hide(cover, id) {
	var objCover = document.getElementById(cover);
	var objId = document.getElementById(id);
	objCover.style.visibility = "hidden";
	objCover.style.width = "0px";
	objCover.style.height = "0px";
	objId.style.display = "none";
}
function resesizeShow(alertmsg){
	try {var disp = document.getElementById(alertmsg).style.display; if("block" == disp){show('cover1',alertmsg);}
	} catch (e) {}
}
try {//浏览器窗口变更事件
	window.onresize=function(){resesizeShow("pop_JPXZ");resesizeShow("pop_zjgl");resesizeShow("pop_jp");}
} catch (e) {}

//ifame
function AddConfigIframe(url,obj,htmlId){
        var random = Math.random();
        jQuery("#"+htmlId).attr("src",url+"?random="+random+"&Id="+obj);
     }
	     //文本框只能输入数字小数点
 function clearNoNum(obj) 
		{ 
		   obj.value = obj.value.replace(/[^\d.]/g,"");  //清除“数字”和“.”以外的字符 
		   obj.value = obj.value.replace(/^\./g,"");  //验证第一个字符是数字而不是. 
		   obj.value = obj.value.replace(/\.{2,}/g,"."); //只保留第一个. 清除多余的. 
		   obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$","."); 
		} 
	
