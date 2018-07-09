// JavaScript Document
/* 20130225 zhouxu */

/*选项卡用js*/
function nTabs(thisObj,Num,active,normal){
	if(thisObj.className == active) return;
	var tabObj = thisObj.parentNode.id;
	var tabList = document.getElementById(tabObj).getElementsByTagName("li");
	for(i=0; i <tabList.length; i++)
	{
		if (i == Num)
		{
		   thisObj.className = active;		  
		   document.getElementById(tabObj+"_Content"+i).style.display="block";
		}else{
		   tabList[i].className = normal;
		   document.getElementById(tabObj+"_Content"+i).style.display="none";
		}
	}
}

function openmenu(sn_p,subnum_p,ttnum_p){
	var t1=document.getElementById("c"+sn_p+"_"+subnum_p);
	if(t1.style.display=="none"){
		for(i=1;i<=ttnum_p;i++){
			document.getElementById("c"+sn_p+"_"+i).style.display="none";
			document.getElementById("m"+sn_p+"_"+i).className="";
		}
		t1.style.display="block";
		document.getElementById("m"+sn_p+"_"+subnum_p).className="on";
	}
}

/*弹出层*/
var useDispAll=0;
var openedLay=0;
function show(cover,id,useDisp){
	var Sys = {};
	var ua = navigator.userAgent.toLowerCase();
	var s;      
	(s = ua.match(/msie ([\d.]+)/)) ? Sys.ie = s[1] : 
	(s = ua.match(/chrome\/([\d.]+)/)) ? Sys.chrome = s[1] :
	(s = ua.match(/version\/([\d.]+).*safari/)) ? Sys.safari = s[1] : 0;		
	//如果是ie6，隐藏页面select
	var objCover=document.getElementById(cover);
	if(Sys.ie=="6.0"){
		/*var n=document.getElementsByTagName("select").length;
		var m=document.getElementById(id).getElementsByTagName("select").length;
		for(var i=0;i<n;i++){
			document.getElementsByTagName("select")[i].style.display='none';}
		for(var j=0;j<m;j++){		
			document.getElementById(id).getElementsByTagName("select")[j].style.display='';}
		*/
		objCover.innerHTML='<iframe style="width:100%;height:100%;"></iframe>';
	}
	
	var objId=document.getElementById(id);
	if( $(objId).css("display")!="none" && $(objId).css("visibility")!="hidden") return;
	objId.style.display="block";
	objId.style.visibility="visible";
	var scrollW=document.documentElement.scrollWidth;
	var scrollH=document.documentElement.scrollHeight;
	if (Sys.safari || Sys.chrome){
		var scrollH=document.body.scrollHeight;
		if(document.documentElement.clientHeight<objId.clientHeight){
			var T=document.body.scrollTop;
		}else{
			var T=(document.documentElement.clientHeight-objId.clientHeight)/2+document.body.scrollTop;
		}
	}else{
		if(document.documentElement.clientHeight<objId.clientHeight){
			var T=document.documentElement.scrollTop || document.body.scrollTop;
		}else{
			var T=(document.documentElement.clientHeight-objId.clientHeight)/2+(document.documentElement.scrollTop || document.body.scrollTop);
		}
	}
	var L=(document.documentElement.clientWidth-objId.clientWidth)/2+document.documentElement.scrollLeft;	
	
	objCover.style.visibility="visible";
	objCover.style.display="";
	objId.style.top=T+"px";
	objId.style.left=L+"px";
	objCover.style.width=scrollW+"px";
	//objCover.style.height=scrollH+"px";
	objCover.style.height=(document.body.scrollHeight>document.documentElement.scrollHeight? document.body.scrollHeight:document.documentElement.scrollHeight)+"px"; //当弹出层出现，高度撑高后，要重新设置遮罩层高度
	useDispAll=useDisp;
	
	if(openedLay>0){
		var newMask=$(objCover).clone(); //alert(newMask.html());
		newMask.css("z-index","2000");
		newMask.insertBefore($(objId));
	}
	openedLay++;
		
	window.onresize=function (){	
		var objCover=document.getElementById(cover);
		var objId=document.getElementById(id);
		var scrollW=document.documentElement.scrollWidth;
		if(document.documentElement.clientHeight >= document.documentElement.scrollHeight){
			var scrollH=document.documentElement.clientHeight;	
		}else{
			var scrollH=document.documentElement.scrollHeight;}
		if (Sys.safari || Sys.chrome) {
			if(document.documentElement.clientHeight<objId.clientHeight){
				var T=document.body.scrollTop;
			}else{
				var T=(document.documentElement.clientHeight-objId.clientHeight)/2+document.body.scrollTop;
			}
		}else{
			if(document.documentElement.clientHeight<objId.clientHeight){
				var T=document.documentElement.scrollTop || document.body.scrollTop;
			}else{
				var T=(document.documentElement.clientHeight-objId.clientHeight)/2+(document.documentElement.scrollTop || document.body.scrollTop);
			}
		}
		var L=(document.documentElement.clientWidth-objId.clientWidth)/2+document.documentElement.scrollLeft;		
		objCover.style.width=scrollW+"px";
		objCover.style.height=scrollH+"px";
		objId.style.top=T+"px";
		objId.style.left=L+"px";
	}
}

function hide(cover,id){
	//将页面全部select换件设为可用状态
	var Sys = {};
	var ua = navigator.userAgent.toLowerCase();
	var s;    
	(s = ua.match(/msie ([\d.]+)/)) ? Sys.ie = s[1] : 
	(s = ua.match(/version\/([\d.]+).*safari/)) ? Sys.safari = s[1] : 0;	
	if(Sys.ie=="6.0"){
		/*var n=document.getElementsByTagName("select").length;
		for(var i=0;i<n;i++){
			document.getElementsByTagName("select")[i].style.display= '';
		}*/
	}
	var objCover=document.getElementById(cover);
	var objId=document.getElementById(id);
	
	objId.style.visibility="hidden";
	//if(useDispAll==1){
		objId.style.display="none";
	//}
	if(openedLay>1){
		$(objId).prev().remove();
	}else{
		objCover.style.visibility="hidden";
		objCover.style.display="none";
		objCover.style.height="0"; //页面高度变低后防止body判断过高
	}	
	openedLay--;
}
function parenthide(cover,id){
	//将页面全部select换件设为可用状态
	var Sys = {};
	var ua = navigator.userAgent.toLowerCase();
	var s;    
	(s = ua.match(/msie ([\d.]+)/)) ? Sys.ie = s[1] : 
	(s = ua.match(/version\/([\d.]+).*safari/)) ? Sys.safari = s[1] : 0;	
	if(Sys.ie=="6.0"){
		/*var n=document.getElementsByTagName("select").length;
		for(var i=0;i<n;i++){
			document.getElementsByTagName("select")[i].style.display= '';
		}*/
	}
	var objCover=window.parent.document.getElementById(cover);
	var objId=window.parent.document.getElementById(id);
	
	objId.style.visibility="hidden";
	//if(useDispAll==1){
		objId.style.display="none";
	//}
	if(openedLay>1){
		$(objId).prev().remove();
	}else{
		objCover.style.visibility="hidden";
		objCover.style.display="none";
		objCover.style.height="0"; //页面高度变低后防止body判断过高
	}	
	openedLay--;
}
/*表格mouseover变色*/
$(document).ready(function(){
	/*table05*/
	if( $('table.table05').length > 0 ){
		var $tr = $('table.table05 tbody tr');
		$tr.hover(function(){
			$(this).addClass("hover");
		},function(){
			$(this).removeClass("hover");
			});
	}
	/*table06*/
	if( $('table.table06').length > 0 ){
		var $tr = $('table.table06 tr');
		$tr.hover(function(){
			$(this).addClass("hover");
		},function(){
			$(this).removeClass("hover");
			});
	}

});


/*简单切换 -- name:切换的名称前缀； currNum:切换的当前号码； totalNum:切换的总号码; */
function switchSimp(name, currNum, totalNum){
	for(var i=1;i<=totalNum;i++){
		document.getElementById(name + "_" + i).style.display="none";
	}
	document.getElementById(name+"_"+ currNum).style.display="inline";
}

/*活动管理 团购、秒杀、签到-产品选项卡*/
$(function(){
	if ($("#product_list").length==0) return;
	$("#product_list li").click(function(){
		if($(this).hasClass("active")) return;
		if($(this).hasClass("add")) return;
		$(this).addClass("active").siblings().removeClass("active");
	});	
});

/*列表全选checkbox*/
$(function(){
	if($("#check_all").length>0){
		var $key = $("#check_all");
		var name = $key.attr("name");
		$key.click(function(){
			var name=$(this).attr("name");
			if($key.prop("checked")==true){
				$("input[name="+name+"]").prop("checked",true);
			}else{
				$("input[name="+name+"]").prop("checked",false);
			}
		});
		//alert($("input[name=check]").length);
		$("input[name="+name+"]").click(function(e){
			if (e.target.id == "check_all"){return;}
			if($key.prop("checked")==true){
				$key.prop("checked",false);
			}
		});
	}
});
/*列表全选checkbox 新*/
$(function(){
	var selAll=$(".check_all");
	if(selAll.length>0){
		selAll.each(function(index, element){
			var tag=$(this);
			var name=$(this).attr("name");
			$(this).click(function(e) {
				if($(this).prop("checked")==true){
					$("input[name="+name+"]").prop("checked",true);
				}else{
					$("input[name="+name+"]").prop("checked",false);
				}
			});
			
			$("input[name="+name+"]").click(function(e){
				if($(this).hasClass("check_all"))return;
				if(tag.prop("checked")==true){
					tag.prop("checked",false);
				}
			});
		});
	}
});
/*父类列表全选checkbox --check_all2*/
$(function(){
	var selAll=$(".check_all2");
	if(selAll.length>0){
		selAll.each(function(index, element){
			var tag=$(this);
			var rela=$(this).attr("rela");
			var childs=tag.parents("table").find("input."+rela); //本表格下的才操作
			$(this).click(function(e) {
				if($(this).prop("checked")==true){
					childs.each(function(index, element) {
						if($(this).prop("checked")==false)$(this).click();
					});
				}else{
					childs.each(function(index, element) {
						if($(this).prop("checked")==true)$(this).click();
					});
				}
			});
			
			$("input."+rela).click(function(e){
				if($(this).prop("checked")==false){
					tag.prop("checked",false);
				}
			});
		});
	}
});

/*排序箭头切换*/
$(function(){
	if ($("span.desc").length>0){
		var $arrow = $("span.desc");
		$arrow.click(function(e){
			var $target = $(e.target);
			$target.toggleClass("asce");
		});
	}
});
/*高级搜索*/
function show_select_row(){
	$(".select_row_more").toggle();
}
/*主体自适应高度*/
//$(function(){
//	//if( $("div.top_bar").length<1 && $("div.count1").length<1 && $("div.count").length>0 ){ //alert($("div.count1").length);
//	if( $("div.count1").length<1 && $("div.count").length>0 ){
//		var height=$(window).height(); //alert("K1");
//		var top=$("div.count").offset().top+10;
//		$("div.count").css("height",height-top);
//		$(window).resize(function(){
//			height=$(window).height();
//			$("div.count").css("height",height-top);
//		});
//	}
//	if(mustExe_adaptHeight2){
//		adaptHeight2(spaceBottom);
//	}
//});
///*主体自适应高度 - 额外2
//para: spaceBot:底部预留像素
//*/
//var mustExe_adaptHeight2=false;
//var spaceBottom=0;
//function adaptHeight2(spaceBot){
//	mustExe_adaptHeight2=true;
//	spaceBottom=spaceBot;
//	var height=$(window).height(); //alert("K2");
//	var top=$("div.count").offset().top+10;
//	$("div.count").css("height",height-top-spaceBot);
//	$(window).resize(function(){
//		height=$(window).height();
//		$("div.count").css("height",height-top-spaceBot);
//	});
//}


/*iframe自动高度*/
function iFrameHeight(tagName) { 
	var ifm= document.getElementById(tagName); 
//	alert( document.frames[tagName].document.body.scrollHeight);
	var subWeb = document.frames ? document.frames[tagName].document : ifm.contentDocument; 
	if(ifm != null && subWeb != null) { 
		ifm.height = subWeb.body.scrollHeight; 
	} 
}

//muliSelect左右添加删除
function muliSelAddDel(fLeft,fRight,btnAdd,btnDel){
	var fL=$(fLeft);
	var fR=$(fRight);
	var btnAdd=$(btnAdd);
	var btnDel=$(btnDel);
	
	btnAdd.click(function(e) {
		if(fL.get(0).selectedIndex!=-1){
			fR.append(fL.children(":selected"));
			fR.get(0).selectedIndex=-1;
		}
	});
	btnDel.click(function(e) {
		if(fR.get(0).selectedIndex!=-1){
			fL.append(fR.children(":selected"));
			fL.get(0).selectedIndex=-1;
		}
	});
}

//表行折叠（带+-号）
function initTableFolder(){
	$(".query_result table .tr2 .iconFold").click(function(e) {
		if($(this).hasClass("on")){
			$(this).removeClass("on");
			$(this).parent().parent().nextUntil(".tr2").addClass("dis_none");
		}else{
			$(this).addClass("on");
			$(this).parent().parent().nextUntil(".tr2").removeClass("dis_none");
		}
	});
}
/* 表格mouseover变色 */
$(document).ready(function() {
	/* table05 */
	if ($("table.table05").length > 0) {
		var $tr = $("table.table05 tbody tr");
		$tr.hover(function() {
			$(this).addClass("hover");
		}, function() {
			$(this).removeClass("hover");
		});
	}
	/* eXtremeTable */
	if ($(".eXtremeTable").length > 0) {
		var $tr = $(".tableBody tr");
		$tr.hover(function() {
			$(this).addClass("highlight");
		}, function() {
			$(this).removeClass("highlight");
		});
	}
	/* table06 */
	if ($("table.table06").length > 0) {
		var $tr = $("table.table06 tr");
		$tr.hover(function() {
			$(this).addClass("hover");
		}, function() {
			$(this).removeClass("hover");
		});
	}
});
//所有table10的odd表行增加背景色
$(".table10 tbody").each(function(e){ $(this).children("tr:odd").addClass("odd"); });