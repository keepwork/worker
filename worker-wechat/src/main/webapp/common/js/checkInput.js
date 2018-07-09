// 去掉前后空格
String.prototype.trim = function(){
	return this.replace(/(^\s*)|(\s*$)/g, "");
};

// 得到字符串的长度,汉字代表两个字符
String.prototype.getLength = function(){
	if(this.isNull()){
		return 0;
	}
	var cAtt = this.match(/[^\x00-\xff]/ig);
	return this.length + (cAtt==null?0:cAtt.length);
};

// 判断字符串是否为空， 当为空返回true 否则返回false
String.prototype.isNull = function(){
	return null == this || "" == this || 0 == this.length;
}

// 判断字符串是否为浮点数 是==true 否==false
String.prototype.checkFloat = function(){
	if(this.isNull()){
		return true;
	}
	var floatnum=parseFloat(this);	
	if (isNaN(floatnum)){
		return false;
	}else if (floatnum.toString().length==this.length){
		return true;
	}else{
		var location = this.indexOf(".");
		var floatStr = this.substr(0,location);
		var zero = this.substr(location+1);
		reg=/(\D)/;
		if(floatStr.length==floatnum.toString().length && !reg.test(zero)){
			return true;
		}			
	}
	return false;
};

// 判断字符串是否为整数 是==true 否==false
String.prototype.checkInteger = function(){
	if(this.isNull()){
		return true;
	}
	var integer=parseInt(this);
	if (isNaN(integer)){
		return false;
	}else if (integer.toString().length==this.length){
		return true;
	}
	return false;
};

// 检查页面传入到服务器数据合法性
function checkAllInput(){
	try{
		var numargs = arguments.length;
		if(numargs==0){
			alert("要检查的信息为空!");
		}else if(numargs==1){
			var inputs = arguments[0];
			return checkInputs(inputs);
		}else{
			var s = false;
			for(i=0;i<numargs;i++){
				s = checkInputs(arguments[i]);
				if(!s){
					return false;
				}
			}
			return s;
		}
	}catch(e){
		alert("效验信息出错: 错误是："+e);
		throw e;
	}
	return false;
}

// inputs 为document中的对应对象的ID的数组。当为字符串表示是一个document中的对应对象的ID
function checkInputs(inputs){
	if("string"==typeof(inputs)){
		return checkInputOne(inputs);
	}else if("object"==typeof(inputs)){
		var s = false;
		for(i=0;i<inputs.length;i++){
			s = checkInputOne(inputs[i]);
			if(!s){
				return false;
			}
		}
		return s;
	}else{
		alert("要检查的信息不符合要求! "+inputs);
	}
	return false;
}

// 字符串表示是一个document中的对应对象的ID 
// "needFlag" n代表可选,y代表必填 默认是n,
// "needType" String=字符;Float=数值;Integer=整数 默认是String;
// "maxlength" 默认长度是20,
// "alertMessage" 默认是空字符串,
// "epString" 默认是不包括 "|"'<>@#$%^&*"特殊字符,
// "regex" 默认是不检查,"regexMessage" 是检查正则表达式提供的信息
// "focusId" 获取焦点对象ID，默认是自己
function checkInputOne(input){
	var inputObject = document.getElementById(input);
	
	// 输入的值
	var inputValue = inputObject.value.trim();
	// 去空格后返回对应的值
	inputObject.value = inputValue;
	// 是否是必填y==必填 n==可填可不填
	var needFlag = getDefuale(inputObject.getAttribute("needFlag"),"n");
	// 错误提示信息
	var alertMessage =  getDefuale(inputObject.getAttribute("alertMessage"),"");
	// 检查输入是否允许为空
	if("y"==needFlag){
		if(inputValue.isNull()){
			alertMessage = alertMessage+"不能为空!";
			return returnValue(inputObject,alertMessage);
		}
	}
	// 长度
	var inputLength = getDefuale(inputObject.getAttribute("maxlength"),20);
	// 检查输入长度
	if(inputValue.getLength()>inputLength){
		alertMessage = alertMessage+"长度不能超过"+inputLength+"个字符!";
		return returnValue(inputObject,alertMessage);
	}
	
	// 数据类型
	var needType = getDefuale(inputObject.getAttribute("needType"),"String");
	// 检查输入类型
	if(!checkNumber(inputValue,needType)){
		alertMessage = alertMessage+"不是数字类型数据！";
		return returnValue(inputObject,alertMessage);
	}
	if("String"==needType){ // 当输入为字符串时检查不能包含的特殊字符
		// 特殊字符正则表达式
		var inputRegex = getDefuale(inputObject.getAttribute("epString"),"\|\"\'<>@#$%\^&\*");
		// 检查输入正则表达式
		var regExp = new RegExp("^[^"+inputRegex+"]*$");
		if(!regExp.test(inputValue)){
			alertMessage = alertMessage+"不能包含"+inputRegex+"特殊字符!";
			return returnValue(inputObject,alertMessage);
		}
		// 正则表达式
		inputRegex = getDefuale(inputObject.getAttribute("regex"),"");
		if(!inputRegex.isNull()){
			if(!inputValue.isNull()){
				// 检查输入正则表达式
				regExp = new RegExp(inputRegex);
				var regexMessage =  getDefuale(inputObject.getAttribute("regexMessage"),"不符合正则表达式"+inputRegex);
				if(!regExp.test(inputValue)){
					alertMessage = alertMessage+regexMessage;
					return returnValue(inputObject,alertMessage);
				}
			}
		}
	}
	return true;
}

// 检查输入当为数字时 数字分为：数值或者整数
function checkNumber(inputValue,needType){
	if("Float"==needType){
		return inputValue.checkFloat();
	}else if("Integer"==needType){
		return inputValue.checkInteger();
	}else{
		return true;
	}
}
// 返回值以及对象获取焦点
function returnValue(inputObject,alertMessage){
	var focusId = getDefuale(inputObject.getAttribute("focusId"),"");
	var focusObject = inputObject;
	if(!focusId.isNull()){
		focusObject = document.getElementById(focusId); 
	}
	var typeString = typeof(focusObject);
	if("object"==typeString){
		if("hidden"==focusObject.type){
		}else{
			try{
				focusObject.focus();
				focusObject.select();
			}catch(e){
				// 当此Object没有此方法时不做任何操作
			}
		}
	}
	alert(alertMessage);
	return false;
}

// obj为空或者不识别则默认值 否则返回obj
function getDefuale(obj,value){
	if(null==obj){
		return value;
	}
	if("undefined"==typeof(obj)){
		return value;
	}
	return obj;
} 
//验证是否含有特殊字符
function ValidateSpecialCharacter(aa) {  
	if(aa==undefined||aa=='undefined'){return true;}
	var reg_exp = /([~!@#$%&*()[]{}`=+,.;?<>-_^]|\\|\/|\'|\")/;   
	if (reg_exp.test(aa))  {    
	    return true;   
	}  else{
		return false;
	}
}
function Trim(str){
	return str.replace(/(^\s*)|(\s*$)/g, "");
}
	
	
function IsNull(str){
	if(str==undefined||str=='undefined'){return false;}
	return (str == null || Trim(str) == "") ? true:false;
}
//判断是否是有效的EMAIL地址
function IsEmail(mail){
	if(mail==undefined||mail=='undefined'){return false;}
	if(!IsNull(mail)){
		var myExp = /^[-_a-z0-9]+@([-_a-z0-9]+\.)+[a-z0-9]{2,3}$/;
		if(myExp.test(mail)){
			return true;
		}else{
			return false;
		}
	}
}
//判断是否为有效IP地址
function IsIp(s){
	if(s==undefined||s=='undefined'){return false;}
	var check = function(v){
		try{return (v <= 255 && v >= 0);}
		catch(x){return false;}
	}
	var re = s.split(".")
	return (re.length==4)?(check(re[0]) && check(re[1]) && check(re[2]) && check(re[3])):false
}

//判断是否为正整数
function IsInteger(a){	
	if(a==undefined||a=='undefined'||a==''){return false;}
	if(!/^\d*$/.test(a))
	{return false;}
	if (parseInt(a)<1)
	{return false;}
	return true;
}
function IsInteger2(a){	
	if(a==undefined||a=='undefined'){return false;}
	if(!/^\d*$/.test(a))
	{return false;}
	if (parseInt(a)<0)
	{return false;}
	return true;
}
//判断输入的是否为汉字begin
function isCharsInBag (s, bag){
	var i,c;
	for (i = 0; i < s.length; i++){
		c = s.charAt(i);//字符串s中的字符
		if (bag.indexOf(c) > -1)
			return c;
	}
	return "";
}
function IsTelephone(obj){
	if(obj==undefined||obj=='undefined'){return false;}
	var pattern=/^(([0\+]\d{2,3}-)?(0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$/;
	return (pattern.test(obj)) ? true : false;
}
//验证合法手机号
function isMobile(obj){
	if(obj==undefined||obj=='undefined'){return false;}
	var pattern=/^(1\d{10})$/;
	return (pattern.test(obj)) ? true : false;
}
//判断是否为浮点数
function IsFloat(str){
	if(str==undefined||str=='undefined'){return false;}
	var number_chars = "1234567890.";
	var i;
	for (i = 0; i < str.length; i++){
		if (number_chars.indexOf(str.charAt(i))==-1)	
		return false;
	}
	return true;
}
//判断是否为邮编
function IsPostCode(postCode) {
	if(postCode==undefined||postCode=='undefined'){return false;}
    if (!IsNull(postCode)) {
	    if (postCode.length != 6) {
	        return false;}
	    if (!IsInteger(postCode)){
	        return false;
	    }else {return true;}
    }
}
//移动电话(手机），样式:13531214732或013531214732
function IsMoveTel(elem){
	if(elem==undefined||elem=='undefined'){return false;}
	var pattern=/^0{0,1}13[0-9]{9}$/;
	var pattern1=/^0{0,1}15[0-9]{9}$/;
	var pattern2=/^[0-9]{11}$/;
	if(!IsNull(elem)){
		if(pattern.test(elem) || pattern1.test(elem) ||pattern2.test(elem)){
			return true;
		}else{
			return false;
		}
	}
}
//验证是否正确访问地址
function IsURL(str_url){ 
        var strRegex = "^((https|http|ftp|rtsp|mms)?://)"  
        + "?(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?" //ftp的user@  
        + "(([0-9]{1,3}\.){3}[0-9]{1,3}" // IP形式的URL- 199.194.52.184  
        + "|" // 允许IP和DOMAIN（域名） 
        + "([0-9a-z_!~*'()-]+\.)+" // 域名- www.  
        + "([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\." // 二级域名  
        + "[a-z]{2,6})" // first level domain- .com or .museum  
        + "(:[0-9]{1,4})?" // 端口- :80  
        + "((/?)|" // a slash isn't required if there is no file name  
        + "(/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+/?)$";  
        var re=new RegExp(strRegex);  
        if (re.test(str_url)){
            return (true); 
        }else{ 
            return (false); 
        }
} 
    
//身份证号码的验证
function isIdCardNo(num)
{
	var error="";
 	
	var len = num.length, re;
	if (len == 15){
		re = new RegExp(/^(\d{6})()?(\d{2})(\d{2})(\d{2})(\d{3})$/);
	}else if (len == 18){
		re = new RegExp(/^(\d{6})()?(\d{4})(\d{2})(\d{2})(\d{3})(\d)$/);
	}else {
		re = new RegExp(/^(\d{6})()?(\d{4})(\d{2})(\d{2})(\d{3})(\d)$/);
	}
	var a = num.match(re);
	if (a != null)
	{
		if (len==15)
		{
			var D = new Date("19"+a[3]+"/"+a[4]+"/"+a[5]);
			var B = D.getYear()==a[3]&&(D.getMonth()+1)==a[4]&&D.getDate()==a[5];
		}else{
			var D = new Date(a[3]+"/"+a[4]+"/"+a[5]);
			var B = D.getFullYear()==a[3]&&(D.getMonth()+1)==a[4]&&D.getDate()==a[5];
		}
		if (!B) {
			return false;
		}
		return true;
	}
	return false;	
} 

	function isFloat(checkstr, bEmpty, tcount)
	{
		if(checkstr==null||trim(checkstr)=="")
		{
			return bEmpty;
		}

		var str	= trim(checkstr);
		if(str.substring(0,1)==".")
		{
			return false;
		}
		var temp=0;
		for(var i=0;i<str.length;i++)
		{
			var ch=str.substring(i,i+1);
			if(!((ch>="0" && ch<="9") || ch=="."))
			{
				return false;
			}
			if(ch==".")
				temp++;
			if(temp>1)
			{
				return false;
			}
		}

		if(tcount != null && tcount > 0)
		{
			if(str.indexOf(".") != -1 && str.length - (str.indexOf(".")+1) > tcount)
			{
				return false;
			}
		}

		var start1 = checkstr.substring(0,1);
		var start2 = checkstr.substring(1,2);
		if(start1 == 0 && start2!=".")
		{
		    for(var i=0;i<str.length;i++)
		    {
		     var ch=str.substring(i,i+1);
		     if (ch==0)
		     temp++;
		     }
		   if (temp == str.length)
		    {
		      return true;
		    }
		    return false;
		  }


		return true;
	}
	
		function trim(Str , Flag)
	{

		Str	= ""+Str;
		if( Flag == "l" || Flag == "L" )/*trim left side only*/
		{
			RegularExp	= /^\s+/gi;
			return Str.replace( RegularExp,"" );
		}
		else if( Flag == "r" || Flag == "R" )/*trim right side only*/
		{
			RegularExp	= /\s+$/gi;
			return Str.replace( RegularExp,"" );
		}
		else/*defautly, trim both left and right side*/
		{
			RegularExp	= /^\s+|\s+$/gi;
			return Str.replace( RegularExp,"" );
		}
	}