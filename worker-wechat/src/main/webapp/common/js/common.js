String.prototype.trim = function() {
	return this.replace(/(^\s*)|(\s*$)/g, "");
};
function getTop(windowHeight) {
	var top = parseInt((screen.height - windowHeight) / 2, 10);
	return top;
}
function getLeft(windowWidth) {
	var left = parseInt((screen.width - windowWidth) / 2, 10);
	return left;
}
function chsel(i, a, b) {
	if (i == 0) {
		ch_onclick(a, b);
		if (a) {
			if (document.forms[0].delbutton)
				document.forms[0].delbutton.disabled = false;
		} else {
			if (document.forms[0].delbutton)
				document.forms[0].delbutton.disabled = true;
		}
	} else {
		var checkboxs = document.getElementsByName(b);
		var flag = false;
		for ( var i = 0; i < checkboxs.length; i++) {
			if (checkboxs[i].checked) {
				flag = true;
				break;
			}
		}
		if (flag) {
			if (document.forms[0].delbutton)
				document.forms[0].delbutton.disabled = false;
		} else {
			if (document.forms[0].delbutton)
				document.forms[0].delbutton.disabled = true;
		}
	}
}
function ch_onclick(v, a) {
	var checkboxs = document.getElementsByName(a);
	if (!checkboxs.length) {
		checkboxs.checked = v;
	} else {
		for ( var i = 0; i < checkboxs.length; i++) {
			checkboxs[i].checked = v;
		}
	}
}
// 计算哪些复选框被选中,v是boolean,a是check的名,url提交的路径，id是提交带的参数
function countCheck(v, a, url, id) {
	var checkboxs = document.getElementsByName(a);
	var flag = 0;
	var value = "0";
	if (!checkboxs.length) {
		flag = 1;
	} else {
		for ( var i = 0; i < checkboxs.length; i++) {
			if (checkboxs[i].checked == v) {
				flag = flag + 1;
				value = checkboxs[i].value;
			}
		}
	}
	if (parseInt(flag) == 1) {
		document.forms[0].action = url + "&" + id + "=" + value;
		document.forms[0].submit();
	} else {
		alert("编辑时请您选择一条记录");
		return false;
	}
}
// 计算哪些复选框被选中,v是boolean,a是check的名,url提交的路径，id是提交带的参数返。
function countCheckBysave(v, a, url, id) {
	var checkboxs = document.getElementsByName(a);
	var flag = 0;
	var value = "0";
	if (!checkboxs.length) {
		flag = 1;
	} else {
		for ( var i = 0; i < checkboxs.length; i++) {
			if (checkboxs[i].checked == v) {
				flag = flag + 1;
				value = checkboxs[i].value;
			}
		}
	}
	if (parseInt(flag) == 1) {
		url = url + "&" + id + "=" + value;
		return url;
	} else {
		alert("编辑时请您选择一条记录");
		return null;
	}
}
// 判断是否为FireFox
// var IsFireFox = document.getElementById &&! document.all;
// 页面里回车到下一控件的焦点
function Enter2Tab(e) {
	try {
		var ob = IsFireFox ? e.target : event.srcElement;
		if (ob.tagName == "INPUT"
				&& (ob.type == "text" || ob.type == "password"
						|| ob.type == "checkbox" || ob.type == "radio")
				|| ob.tagName == "SELECT") {
			var key = IsFireFox ? e.which : event.keyCode;
			if (key == 13) {
				if (IsFireFox) {
					event.which = 9;
				} else {
					event.keyCode = 9;
				}
			}
		}
	} catch (E) {
	}
}
// 打开此功能请取消下行注释
// document.onkeydown = Enter2Tab;

// ------ajax对象创建-----begain--
var xmlHttp = false;
var selectCtr;
function createXMLHttpRequest() {
	xmlHttp = false;
	if (window.XMLHttpRequest) {// mozilla 浏览器

		xmlHttp = new XMLHttpRequest();
		if (xmlHttp.overrideMimeType) {
			xmlHttp.overrideMimeType("text/xml");
		}
	} else if (window.ActiveXObject) {// IE
		try {
			xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
		} catch (e) {
			try {
				xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (e) {
			}
		}
	}
	if (!xmlHttp) {// 出现异常,XMLHttpRequest对象实例创建失败;
		alert("不能创建XMLHttpRequest对象实例.");
		return false;
	}
}
function handleStateQyChange() {
	if (xmlHttp.readyState == 4) {
		if (xmlHttp.status == 200) {
			selectCtr.innerHTML = xmlHttp.responseText;
		}
	}
}
function toAjax(ctrName, ctrOptName, url) {
	createXMLHttpRequest();
	var inputText = ctrName.value;
	var urlString = "";
	selectCtr = ctrOptName;
	if (inputText != "-1") {
		urlString = url + "&ajaxPer=" + inputText;
		xmlHttp.onreadystatechange = handleStateQyChange;
		xmlHttp.open("GET", urlString, false);
		xmlHttp.send(null);
	}
}
function toAjaxGetText(ctrName, url) {
	createXMLHttpRequest();
	var inputText = ctrName.value;
	var urlString = "";
	urlString = url + "&ajaxPer=" + inputText;
	xmlHttp.onreadystatechange = function getText() {
		if (xmlHttp.readyState == 4) {
			if (xmlHttp.status == 200) {
				selectCtr = xmlHttp.responseText;
			}
		}
	}
	xmlHttp.open("GET", urlString, false);
	xmlHttp.send(null);
	return selectCtr;
}
// ======== end =========//

// 页面调用函数
/*******************************************************************************
 * 函数名称：sendRequest 函数参数：method:传送方式（post/get）, url(发送的url)，
 * 
 * 
 * 
 * 
 * content（传值所带的参数）， callback(返回结果处理函数名称)
 ******************************************************************************/
sendRequest = function(method, url, content, callBack) {
	createXMLHttpRequest();
	xmlHttp.onreadystatechange = callBack;
	if (method.toLowerCase() == "get") {
		xmlHttp.open(method, url, true);
	} else if (method.toLowerCase() == "post") {
		xmlHttp.open(method, url, true);
		xmlHttp.setRequestHeader("Content-Type",
				"application/x-www-form-urlencoded");
	} else {
		window.alert("响应的类别参数错误！");
		return;
	}
	xmlHttp.send(content);
}

// 判断用户名是否符合标准
function IsOkString(s) {
	var re = /^[0-9a-z][\w-.]*[0-9a-z]$/i;
	return re.test(s) ? true : false;
}
// 全选择
function SelectAll() {
	var empty;
	var f = document.forms[0];
	for ( var i = 0; i < f.length; i++) {
		empty = f[i];
		if (empty.type == "checkbox" && empty.disabled == false)
			empty.checked = true;
	}
}
// 反选择
function SelectReverse() {
	var empty;
	var f = document.forms[0];
	for ( var i = 0; i < f.length; i++) {
		empty = f[i];
		if (empty.type == "checkbox" && empty.disabled == false)
			if (empty.checked == true) {
				empty.checked = false;
			} else {
				empty.checked = true;
			}
	}
}

function WoodsAlert(ob, tip) {
	alert(tip);
	// ob.value = "";
	ob.focus();
}
// 处理特殊字符
function invalid_keyword() {
	var keyword = new Array("<", ">", "\%", "\'", "\"");
	var f, ob;
	for ( var form = 0; form < document.forms.length; form++) {
		f = document.forms[form];
		for ( var i = 0; i < f.length; i++) {
			ob = f[i];
			if (ob.type == "text" && ob.disabled == false) {
				for (j = 0; j < keyword.length; j++) {
					if (ob.value.indexOf(keyword[j]) != -1) {
						alert("在输入框聚焦处发现非法字符[" + keyword[j] + "]。");
						// ob.value = "";
						ob.focus();
						return false;
					}
				}
				ob.value = Trim(ob.value);
			}
		}
	}
	return true;
}
// 判断是否为时间字符串
function IsTime(str) {
	var a = str.match(/^(\d{1,2})(:)?(\d{1,2})\2(\d{1,2})$/);
	if (a == null) {
		alert('输入的参数不是时间格式');
		return false;
	}
	if (a[1] > 24 || a[3] > 60 || a[4] > 60) {
		return false
	}
	return true;
}
// 是否为日期（YYYY-MM-DD）类型字符串
function IsDate(str) {
	var r = str.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/);
	if (r == null)
		return false;
	var d = new Date(r[1], r[3] - 1, r[4]);
	return (d.getFullYear() == r[1] && (d.getMonth() + 1) == r[3] && d
			.getDate() == r[4]);
}
// 判断用户给出的日期是否大于当前日期

function IsBigCurrentDate(str) {
	var today = new Date();
	var tY = String(today.getFullYear());
	var tM = String(today.getMonth() + 1);
	(tM.length <= 1) ? tM = "0" + tM : tM = tM;
	var tD = String(today.getDate());
	(tD.length <= 1) ? tD = "0" + tD : tD = tD;
	var tH = String(today.getHours());
	(tH.length <= 1) ? tH = "0" + tH : tH = tH;
	var tM1 = String(today.getMinutes());
	(tM1.length <= 1) ? tM1 = "0" + tM1 : tM1 = tM1;
	var tdate = tY + "-" + tM + "-" + tD + " " + tH + ":" + tM1 + ":59";// 添加秒的判断
	if (str > tdate) {
		return true;
	} else {
		return false;
	}
}
// 是否为时间日期类型

function IsDateTime(str) {
	var reg = /^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2}) (\d{1,2}):(\d{1,2}):(\d{1,2})$/;
	var r = str.match(reg);
	if (r == null)
		return false;
	var d = new Date(r[1], r[3] - 1, r[4], r[5], r[6], r[7]);
	return (d.getFullYear() == r[1] && (d.getMonth() + 1) == r[3]
			&& d.getDate() == r[4] && d.getHours() == r[5]
			&& d.getMinutes() == r[6] && d.getSeconds() == r[7]);
}
// 判断是否是有效的EMAIL地址
function IsEmail(mail) {
	if (!IsNull(mail)) {
		var myExp = /^[-_a-z0-9]+@([-_a-z0-9]+\.)+[a-z0-9]{2,3}$/;
		if (myExp.test(mail)) {
			return true;
		} else {
			return false;
		}
	}
}
// 判断是否为有效IP地址
function IsIp(s) {
	var check = function(v) {
		try {
			return (v <= 255 && v >= 0);
		} catch (x) {
			return false;
		}
	}
	var re = s.split(".")
	return (re.length == 4) ? (check(re[0]) && check(re[1]) && check(re[2]) && check(re[3]))
			: false
}
// 验证身份证号码
function isIdCard(num) {
	num1 = num.substr(0, num.length - 1);
	if (isNaN(num1)) {
		alert("输入的身份证号码不是数字！");
		return false;
	}
	var len = num.length, re;
	if (len == 15)
		re = new RegExp(/^(\d{6})()?(\d{2})(\d{2})(\d{2})(\d{3})$/);
	else if (len == 18)
		re = new RegExp(/^(\d{6})()?(\d{4})(\d{2})(\d{2})(\d{3})(\d)$/);
	else {
		alert("输入的数字身份证号码位数不对！");
		return false;
	}
	var a = num.match(re);
	if (a != null) {
		if (len == 15) {
			var D = new Date("19" + a[3] + "/" + a[4] + "/" + a[5]);
			var B = D.getYear() == a[3] && (D.getMonth() + 1) == a[4]
					&& D.getDate() == a[5];
		} else {
			var D = new Date(a[3] + "/" + a[4] + "/" + a[5]);
			var B = D.getFullYear() == a[3] && (D.getMonth() + 1) == a[4]
					&& D.getDate() == a[5];
		}
		if (!B) {
			alert("输入的身份证号 " + a[0] + " 里出生日期不对！");
			return false;
		}
	}
	return true;
}
// 判断是否为正整数
function IsInteger(a) {
	if (!/^\d*$/.test(a)) {
		return false;
	}
	if (parseInt(a) < 1) {
		return false;
	}
	return true;
}
function IsInteger2(a) {
	if (!/^\d*$/.test(a)) {
		return false;
	}
	if (parseInt(a) < 0) {
		return false;
	}
	return true;
}
// 判断输入的是否为汉字begin
function isCharsInBag(s, bag) {
	var i, c;
	for (i = 0; i < s.length; i++) {
		c = s.charAt(i);// 字符串s中的字符
		if (bag.indexOf(c) > -1)
			return c;
	}
	return "";
}
function IsCn(s) {
	var errorChar;
	var badChar = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789><,[]{}?/+=|\'\":;~!#$%()`";
	errorChar = isCharsInBag(s, badChar)
	if (errorChar != "") {
		return false;
	}
	return true;
}
// 判断输入的是否为汉字end

// 判断输入的字符串是不是英文begin
function isCharsInBagEn(s, bag) {
	var i, c;
	for (i = 0; i < s.length; i++) {
		c = s.charAt(i);// 字符串s中的字符
		if (bag.indexOf(c) < 0)
			return c;
	}
	return "";
}
function IsEn(s) {
	var errorChar;
	var badChar = " ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	errorChar = isCharsInBagEn(s, badChar)
	return (errorChar != "") ? false : true;
}
// 判断输入的字符串是不是英文end

/*******************************************************************************
 * 函数名称：IsTelephone 函数功能：固话，手机号码检查函数，合法返回true,反之,返回false 函数参数：obj,待检查的号码 检查规则：
 * (1)电话号码由数字、"("、")"和"-"构成 (2)电话号码为3到8位
 * 
 * 
 * 
 * (3)如果电话号码中包含有区号，那么区号为三位或四位
 * 
 * 
 * 
 * (4)区号用"("、")"或"-"和其他部分隔开 (5)移动电话号码为11或12位，如果为12位,那么第一位为0
 * (6)11位移动电话号码的第一位和第二位为"13" (7)12位移动电话号码的第二位和第三位为"13"
 ******************************************************************************/
function IsTelephone(obj) {
	var pattern = /^(([0\+]\d{2,3}-)?(0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$/;
	return (pattern.test(obj)) ? true : false;
}

/*******************************************************************************
 * 函数名称：IsNumber 函数功能：检测字符串是否全为数字 函数参数：str,需要检测的字符串
 * 
 * 
 * 
 ******************************************************************************/
function IsNumber(str) {
	var number_chars = "1234567890";
	var i;
	for (i = 0; i < str.length; i++) {
		if (number_chars.indexOf(str.charAt(i)) == -1)
			return false;
	}
	return true;
}

/*******************************************************************************
 * 函数名称：Trim 函数功能：去除字符串两边的空格
 * 
 * 
 * 
 * 函数参数：str,需要处理的字符串
 * 
 * 
 * 
 ******************************************************************************/
function Trim(str) {
	return str.replace(/(^\s*)|(\s*$)/g, "");
}

/*******************************************************************************
 * 函数名称：LTrim 函数功能：去除左边的空格 函数参数：str,需要处理的字符串
 * 
 * 
 * 
 ******************************************************************************/
function LTrim(str) {
	return str.replace(/(^\s*)/g, "");
}

/*******************************************************************************
 * 函数名称：RTrim 函数功能：去除右边的空格 函数参数：str,需要处理的字符串
 * 
 * 
 * 
 ******************************************************************************/
function RTrim(str) {
	return this.replace(/(\s*$)/g, "");
}

/*******************************************************************************
 * 函数名称：IsNull 函数功能：判断给定字符串是否为空 函数参数：str,需要处理的字符串
 * 
 * 
 * 
 ******************************************************************************/
function IsNull(str) {
	return (str == null || Trim(str) == "") ? true : false;
}

/*******************************************************************************
 * 函数名：checkLength 功能介绍：检查表单输入的长度 参数说明：str：要检查的字符串； s：表单的最大长度
 * 
 * 
 * 
 * 返回值：无
 * 
 * 
 * 
 ******************************************************************************/
function checkLength(str, s) {
	var i = 0;
	var sum = 0;
	var fobj = eval('document.forms[0].' + str);
	i = strlen(fobj.value);
	if (i > s) {
		return false;
	} else {
		return true;
	}
}

/*******************************************************************************
 * 函数名：strlen 功能介绍：检查表单输入的长度 参数说明：str：要检查的字符串；
 ******************************************************************************/
function strlen(str) {
	var len = 0;
	for ( var i = 0; i < str.length; i++) {
		if (str.charCodeAt(i) > 255)
			len += 2;
		else
			len++;
	}
	return len;
}

// 获取cookie
function getCookie(name) {
	var search = name + "=";
	if (document.cookie.length > 0) {
		offset = document.cookie.indexOf(search);
		if (offset != -1) {
			offset += search.length;
			end = document.cookie.indexOf(";", offset);
			if (end == -1)
				end = document.cookie.length;
			return unescape(document.cookie.substring(offset, end));
		} else
			return "";
	}
}

// 存储cookie
function setCookie(name, value, days) {
	if (days) {
		var date = new Date();
		date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
		var expires = ";expires=" + date.toGMTString();
	} else
		var expires = "";
	document.cookie = name + "=" + value + expires + ";path=/;domain="
			+ document.location.hostname;
}

function eventFalse() {
	window.event.returnValue = false;
}

// 检查输入的是否是浮点数
// 参数说明：obj:name值,num:小数点保留几位

function checkNumber(obj, num) { // 过滤掉数字以外的键,48-57是数字键,46是".",num是小数点后几位

	if (event.keyCode == 46) {
		if (obj.value.trim().length <= 0) { // 如果第一个键是".",屏蔽掉

			event.keyCode = 0;
			return;
		}// end if
		else {
			for ( var n = 0; n < obj.value.trim().length; n++) { // 如果"."过多,屏蔽掉

				if (obj.value.trim().substring(n, n + 1) == ".") {
					event.keyCode = 0;
					return;
				}// end if
			}// end for n
			return;
		}// end else
	}// end if
	if (event.keyCode < 48 || event.keyCode > 57) { // 过滤其他键

		event.keyCode = 0;
		return;
	} else { // 控制小数点后位数
		for ( var n = 0; n < obj.value.trim().length; n++) {
			if (obj.value.trim().substring(n, n + 1) == ".") {
				if ((obj.value.trim().length - n) > num) {
					event.keyCode = 0;
					return;
				}// end if
			}// end if
		}// end for n
	}
}
// 判断是否为浮点数
function IsFloat(str) {
	var number_chars = "1234567890.";
	var i;
	for (i = 0; i < str.length; i++) {
		if (number_chars.indexOf(str.charAt(i)) == -1)
			return false;
	}
	return true;
}
// 判断是否为邮编

function IsPostCode(postCode) {
	if (!IsNull(postCode)) {
		if (postCode.length != 6) {
			return false;
		}
		if (!IsInteger(postCode)) {
			return false;
		} else {
			return true;
		}
	}
}
// 移动电话(手机），样式:13531214732或013531214732
function IsMoveTel(elem) {
	var pattern = /^0{0,1}13[0-9]{9}$/;
	var pattern1 = /^0{0,1}15[0-9]{9}$/;
	var pattern2 = /^[0-9]{11}$/;
	if (!IsNull(elem)) {
		if (pattern.test(elem) || pattern1.test(elem) || pattern2.test(elem)) {
			return true;
		} else {
			return false;
		}
	}
}

// 验证合法手机号

String.prototype.isMobile = function() {
	return (/^(?:13\d|15[89])-?\d{5}(\d{3}|\*{3})$/.test(this.trim()));
}
/*----------------------------------------*\ 
 * Javascript prototype 格式化日期 By shawl.qiu 
 * 2006-10-21 
 * http://blog.csdn.net/btbtd
 *例子如下：



 * 		 var dt=new Date(); 
 *      Response.Write(dt.formatDate()+'<br/>'); 
 *      Response.Write(dt.formatDate('yyyy-nn-dd hh:mm:ss ms 周wd ')); 
 *-------------客户端使用----------------  
 *      document.write(dt.formatDate()+'<br/>'); 
 *      document.write(dt.formatDate('yyyy-nn-dd hh:mm:ss ms 周wd ')); 
 *结果预览如下：



 *2006-10-21
 *2006-10-21 00:06:50 734 周六
 *
 \*----------------------------------------*/
Date.prototype.formatDate = function(cdti) {

	var dt = this;
	if (cdti !== undefined) {
		return cdti.replace(/(yyyy|nn|dd|hh|mm|ss|ms|wd)/ig, function($1) {
			switch ($1.toLowerCase()) {
			case 'yyyy':
				return dt.getFullYear();
			case 'nn':
				return padNum(dt.getMonth() + 1, 0, 2);
			case 'dd':
				return padNum(dt.getDate(), 0, 2);
			case 'hh':
				return padNum(dt.getHours(), 0, 2);
			case 'mm':
				return padNum(dt.getMinutes(), 0, 2);
			case 'ss':
				return padNum(dt.getSeconds(), 0, 2);
			case 'ms':
				return padNum(dt.getMilliseconds(), 0, 3);
			case 'wd':
				return week(dt.getDay());
			}
		})
	} else {
		return dt.getFullYear() + '-' + padNum(dt.getMonth() + 1, 0, 2) + '-'
				+ padNum(dt.getDate(), 0, 2);
	}
	function week(day) {
		switch (day) {
		case 0:
			day = '日';
			break;
		case 1:
			day = '一';
			break;
		case 2:
			day = '二';
			break;
		case 3:
			day = '三';
			break;
		case 4:
			day = '四';
			break;
		case 5:
			day = '五';
			break;
		case 6:
			day = '六';
			break;
		}
		return day;
	}
	function padNum(str, num, len) {
		var temp = ''
		for ( var i = 0; i < len; temp += num, i++)
			;
		return temp = (temp += str).substr(temp.length - len);
	}
}
// -->

function createXmlObject() {
	var xmlHttpRequest;
	try {
		xmlHttpRequest = new ActiveXObject("Msxml2.XMLHTTP");
	} catch (e1) {
		try {
			xmlHttpRequest = new ActiveXObject("Microsoft.XMLHTTP");
		} catch (e2) {
			xmlHttpRequest = false;
		}
	}
	if (!xmlHttpRequest) {
		xmlHttpRequest = new XMLHttpRequest();
	}
	return xmlHttpRequest;
}

// 下拉框根据内容长度自动构建DIV，显示全部内容
function opts(selectObj) {
	if (getOs() != 1)
		return false;
	var optDivs = document.createElement("div");
	var objTable = document.createElement("table");
	var objTbody = document.createElement("tbody");
	optDivs.style.zIndex = "100";
	objTable.style.zIndex = "100";
	objTable.width = selectObj.style.width;

	// 确定div的位置刚好在selectObj下
	var e = selectObj;
	var absTop = e.offsetTop;
	var absLeft = e.offsetLeft;
	var absWidth = e.offsetWidth;
	var absHeight = e.offsetHeight;

	while (e = e.offsetParent) {
		absTop += e.offsetTop;
		absLeft += e.offsetLeft;
	}
	with (objTable.style) {
		position = "absolute";
		top = (absTop + absHeight) + "px";
		left = absLeft + "px";
		border = "1px solid black";
		// tableLayout="fixed";//定长,不想要就注释掉
		// wordBreak="break-all";//多行，需要IE5.5+支持
		wordBreak = "keep-all";// 单行，需要IE5.5+支持
	}

	var options = selectObj.options;
	var val = selectObj.value;
	if (options.length > 0) {
		for ( var i = 0; i < options.length; i++) {
			var newOptDiv = document.createElement("td");
			var objRow = document.createElement("tr");
			newOptDiv.name = options[i].value;
			newOptDiv.innerText = options[i].innerText;
			newOptDiv.title = options[i].title;
			// newOptDiv.onclick=function() {choose(selectObj,val,optDivs)};
			newOptDiv.onmouseout = function() {
				this.className = 'mouseOut';
				val = selectObj.value
			};
			newOptDiv.onmouseover = function() {
				this.className = 'mouseOver';
				val = this.name;
			};
			newOptDiv.className = "mouseOut";
			newOptDiv.style.width = selectObj.style.width;

			objRow.appendChild(newOptDiv);
			objTbody.appendChild(objRow);
		}
	}
	objTbody.appendChild(objRow);
	objTable.appendChild(objTbody);
	optDivs.appendChild(objTable);
	document.body.appendChild(optDivs);
	// 用ifram覆盖下面的select
	var IfrRef = document.createElement("iframe");
	IfrRef.style.position = "absolute";
	IfrRef.style.width = objTable.offsetWidth;
	IfrRef.style.height = objTable.offsetHeight;
	IfrRef.style.top = objTable.style.top;
	IfrRef.style.left = objTable.style.left;
	IfrRef.style.zIndex = optDivs.style.zIndex - 1;
	IfrRef.style.display = "block";
	document.body.appendChild(IfrRef);

	objTable.focus();
	objTable.onblur = function() {
		choose(selectObj, val, optDivs, IfrRef)
	};
}
function choose(objselect, val, delobj, delobj2) {
	objselect.value = val;
	document.body.removeChild(delobj);
	document.body.removeChild(delobj2);
}

// 判断浏览器
function getOs() {
	if (navigator.userAgent.indexOf("MSIE") > 0)
		return 1;
	if (isFirefox = navigator.userAgent.indexOf("Firefox") > 0)
		return 2;
	if (isSafari = navigator.userAgent.indexOf("Safari") > 0)
		return 3;
	if (isCamino = navigator.userAgent.indexOf("Camino") > 0)
		return 4;
	if (isMozilla = navigator.userAgent.indexOf("Gecko/") > 0)
		return 5;
	return 0;
}
// 显示被省略的文本信息
function view_all(obj) {
	var temp = getOs();
	if (temp == 2) { // 如果为FF
		obj.title = obj.textContent;
		// alert("FF");
	} else { // 其它情况
		obj.title = obj.innerText;
		// alert("IE")
	}
}

/**
 * //弹出层的方式，目前效果不是很好 function view_all(obj){
 * 
 * var optDivs=document.createElement("div");
 * 
 * optDivs.style.zIndex = "100"; optDivs.style.borderStyle="solid";
 * optDivs.style.borderWidth="1pt"; optDivs.style.borderColor="black";
 * optDivs.style.position="absolute"; optDivs.style.wordWrap="break-word";
 * optDivs.style.background="#FFFFBB"; optDivs.style.wordBreak="break-all"; var
 * e = obj;
 * 
 * var absLeft = event.clientX + document.documentElement.scrollLeft; var absTop =
 * event.clientY + document.documentElement.scrollTop;
 * 
 * var absWidth = e.offsetWidth; var absHeight = e.offsetHeight;
 * optDivs.style.left=absLeft; optDivs.style.top=absTop;
 * optDivs.style.width=absWidth; optDivs.style.height=absHeight; while(e =
 * e.offsetParent) { absTop += e.offsetTop; absLeft += e.offsetLeft; }
 * 
 * optDivs.innerText=obj.innerText;
 * 
 * document.body.appendChild(optDivs);
 * 
 * obj.onmouseout=function() {removeDiv(optDivs)}; }
 * 
 * function removeDiv(divs){ if(divs){ document.body.removeChild(divs); } }
 */
