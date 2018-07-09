
var WebUIsBoot = {getJsPath:function (g) {
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
}, getCookie:function (f) {
	var g = document.cookie.split("; ");
	var b = null;
	for (var d = 0, a = g.length; d < a; d++) {
		var e = g[d].split("=");
		if (f == e[0]) {
			b = e;
		}
	}
	if (b) {
		var c = b[1];
		if (c == undefined) {
			return c;
		}
		return unescape(c);
	}
	return null;
}};
(function () {
	var a = WebUIsBoot.getJsPath("easyui.boot.js");
	document.write("<script src=\"" + a + "jquery.min.js\" type=\"text/javascript\" charset=\"utf-8\"></script>");
	document.write("<script src=\"" + a + "plugins/ext.jquery.cookie.js\" type=\"text/javascript\" charset=\"utf-8\"></script>");
	document.write("<script src=\"" + a + "jquery.easyui.js\" type=\"text/javascript\" charset=\"utf-8\"></script>");
	document.write("<script src=\"" + a + "locale/easyui-lang-zh_CN.js\" type=\"text/javascript\" charset=\"utf-8\"></script>");
	document.write("<script src=\"" + a + "plugins/ext.jquery.easyui.js\" type=\"text/javascript\" charset=\"utf-8\"></script>");
	document.write("<script src=\"" + a + "plugins/ext.jquery.edatagrid.js\" type=\"text/javascript\" charset=\"utf-8\"></script>");
	document.write("<script src=\"" + a + "plugins/ext.jquery.etreegrid.js\" type=\"text/javascript\" charset=\"utf-8\"></script>");
	document.write("<link href=\"" + a + "themes/default/easyui.css\" rel=\"stylesheet\" type=\"text/css\" charset=\"utf-8\"/>");
	document.write("<link href=\"" + a + "themes/icon.css\" rel=\"stylesheet\" type=\"text/css\" charset=\"utf-8\"/>");
	var b = WebUIsBoot.getCookie("webuisTheme");
	if (b) {
		document.write("<link href=\"" + a + "themes/" + b + "easyui.css\" rel=\"stylesheet\" type=\"text/css\" charset=\"utf-8\"/>");
		$.cookie("webuisTheme", b, {expires:3});
	}
}());

