
var WUIsOtBoot = {getJsPath:function (g) {
	var f = document.getElementsByTagName("script");
	var e = "";
	for (var d = 0, b = f.length; d < b; d++) {
		var h = f[d].src;
		if (h.indexOf(g) != -1) {
			var c = h.split(g);
			e = c[0];
			break;
		}
	}
	var a = location.href;
	a = a.split("#")[0];
	a = a.split("?")[0];
	var c = a.split("/");
	c.length = c.length - 1;
	a = c.join("/");
	if (e.indexOf("https:") == -1 && e.indexOf("http:") == -1 && e.indexOf("file:") == -1 && e.indexOf("/") != 0) {
		e = a + "/" + e;
	}
	return e;
}};
(function () {
	var a = WUIsOtBoot.getJsPath("wuis-other.boot.js");
	document.write("<script src=\"" + a + "otherjs/ext.common.js\" type=\"text/javascript\" charset=\"utf-8\"></script>");
	document.write("<script src=\"" + a + "otherjs/ext.easyui.util.js\" type=\"text/javascript\" charset=\"utf-8\"></script>");
	document.write("<script src=\"" + a + "otherjs/ext.validator.js\" type=\"text/javascript\" defer=\"defer\" charset=\"utf-8\"></script>");
	document.write("<script src=\"" + a + "otherjs/ext.main.webui.js\" type=\"text/javascript\" charset=\"utf-8\"></script>");
}());

