
var ExtCommWebUI = jQuery.extend({}, ExtCommWebUI);
var _titleMsg = "\u4fe1\u606f\u63d0\u793a\uff01";
ExtCommWebUI.showMsg = function (a) {
	jQuery.messager.show({title:_titleMsg, msg:a, showType:"slide"});
};
ExtCommWebUI.errorMsg = function (a) {
	jQuery.messager.alert(_titleMsg, a, "error");
};
ExtCommWebUI.infoMsg = function (a) {
	jQuery.messager.alert(_titleMsg, a, "info");
};
ExtCommWebUI.quesMsg = function (a) {
	jQuery.messager.alert(_titleMsg, a, "question");
};
ExtCommWebUI.warnMsg = function (a) {
	jQuery.messager.alert(_titleMsg, a, "warning");
};
ExtCommWebUI.confirmMsg = function (a, b) {
	jQuery.messager.confirm(_titleMsg, b, function (c) {
		if (c) {
			ExtCommWebUI.submitUrl(a);
		}
	});
};
ExtCommWebUI.confirmRUrlMsg = function (a, c, b) {
	jQuery.messager.confirm(_titleMsg, b, function (d) {
		if (d) {
			c = a;
		}
		ExtCommWebUI.submitUrl(c);
	});
};
ExtCommWebUI.confirmBkMsg = function (a, b) {
	jQuery.messager.confirm(_titleMsg, b, function (c) {
		if (c) {
			ExtCommWebUI.submitUrl(a);
		} else {
			window.history.back();
		}
	});
};
ExtCommWebUI.confirmFmMsg = function (b, a, c) {
	jQuery.messager.confirm(_titleMsg, c, function (d) {
		if (d) {
			ExtCommWebUI.submitUrl(a);
		} else {
			if (b != undefined) {
				b.reset();
			}
		}
	});
};
ExtCommWebUI.confirmRoMsg = function (c, a, b) {
	jQuery.messager.confirm(_titleMsg, b, function (e) {
		if (e) {
			ExtCommWebUI.submitUrl(a);
		} else {
			if (c != undefined) {
				for (var d in c) {
					if (c[d] != null) {
						if (c[d].checked) {
							c[d].checked = false;
							break;
						}
					}
				}
			}
		}
	});
};
ExtCommWebUI.confirmRoMsg2 = function (d, b, a, c) {
	jQuery.messager.confirm(_titleMsg, c, function (f) {
		if (f) {
			ExtCommWebUI.submitUrl(a);
		} else {
			if (d != undefined) {
				for (var e in d) {
					if (d[e] != null) {
						if (d[e].checked) {
							d[e].checked = false;
							b.value = "";
							break;
						}
					}
				}
			}
		}
	});
};
ExtCommWebUI.promptMsg = function (title, msg, cbfunc) {
	jQuery.messager.prompt(title, msg, function (text) {
		if (text) {
			eval(cbfunc.replace("?", "'" + text + "'"));
		}
	});
};
ExtCommWebUI.submitUrl = function (a) {
	window.location.href = a;
};
ExtCommWebUI.submitUrls = function (a, b) {
	window.location.href = a + b;
};
ExtCommWebUI.submitPaUrl = function (a) {
	window.parent.location.href = a;
};
ExtCommWebUI.submitPaUrls = function (a, b) {
	window.parent.location.href = a + b;
};
ExtCommWebUI.submitTopUrl = function (a) {
	top.window.location.href = a;
};
ExtCommWebUI.submitTargUrl = function (b, a) {
	if (b != undefined) {
		b.location.href = a;
	} else {
		window.location.href = a;
	}
};
ExtCommWebUI.submitLogout = function (a) {
	ExtCommWebUI.confirmMsg(a, "\u60a8\u786e\u5b9a\u9000\u51fa\u7cfb\u7edf\uff1f");
};
ExtCommWebUI.ajaxLoginFormId = function (b, a) {
	if (ExtCommWebUI.checkJsvForm(b)) {
		jQuery.post(a, function (c) {
			var d = c.msg;
			if (d + "" != "null") {
				ExtCommWebUI.errorMsg(d);
			} else {
				jQuery("#" + b).submit();
			}
		});
	}
};
ExtCommWebUI.subtJsvForm = function () {
	ExtCommWebUI.subtJsvFormCsNe("ext-jsvform");
};
ExtCommWebUI.subtJsvFormCsNe = function (a) {
	var b = jQuery("." + a).form("validate", {validate:function () {
		return jQuery(this).validate();
	}});
	if (b) {
		jQuery("." + a).submit();
	}
};
ExtCommWebUI.subtJsvFormId = function (b) {
	var a = jQuery("#" + b).form("validate", {validate:function () {
		return jQuery(this).validate();
	}});
	if (a) {
		jQuery("#" + b).submit();
	}
};
ExtCommWebUI.urlInfoMsg = function (a, b) {
	jQuery.messager.alert(_titleMsg, b, "info", function () {
		ExtCommWebUI.submitUri(a);
	});
};
ExtCommWebUI.backInfoMsg = function (a) {
	jQuery.messager.alert(_titleMsg, a, "info", function () {
		window.history.back(-1);
	});
};
ExtCommWebUI.subtJsvFormIdFn = function (id, cbfn) {
	var _flag = jQuery("#" + id).form("validate", {validate:function () {
		return jQuery(this).validate();
	}});
	if (_flag) {
		if (cbfn) {
			if (eval(cbfn)) {
				jQuery("#" + id).submit();
			}
		}
	}
};
ExtCommWebUI.checkJsvForm = function (a) {
	return jQuery("#" + a).form("validate", {validate:function () {
		return jQuery(this).validate();
	}});
};
ExtCommWebUI.progressBar = function () {
	jQuery.messager.progress();
};
ExtCommWebUI.closeProgBar = function () {
	jQuery.messager.progress("close");
};
ExtCommWebUI.titleProgBar = function (a) {
	jQuery.messager.progress({title:a});
};
ExtCommWebUI.msgProgBar = function (a) {
	jQuery.messager.progress({msg:a});
};
ExtCommWebUI.loadProgBar = function () {
	jQuery.messager.progress({title:"\u6b63\u5728\u52a0\u8f7d\u6570\u636e\uff0c\u8bf7\u7a0d\u7b49\u3002\u3002\u3002"});
};
ExtCommWebUI.loadProgBar2 = function () {
	jQuery.messager.progress({msg:"<font size=4>\u6b63\u5728\u52a0\u8f7d\u6570\u636e\uff0c\u8bf7\u7a0d\u7b49\u3002\u3002\u3002</font>"});
};
ExtCommWebUI.checkProgBar = function () {
	jQuery.messager.progress({title:"\u6b63\u5728\u6821\u9a8c\u6570\u636e\uff0c\u8bf7\u7a0d\u7b49\u3002\u3002\u3002"});
};
ExtCommWebUI.checkProgBar2 = function () {
	jQuery.messager.progress({msg:"<font size=4>\u6b63\u5728\u6821\u9a8c\u6570\u636e\uff0c\u8bf7\u7a0d\u7b49\u3002\u3002\u3002</font>"});
};
ExtCommWebUI.subtLoadJsvFormId = function (a) {
	ExtCommWebUI.loadBodyMask();
	setTimeout(function () {
		ExtCommWebUI.removeBodyMask();
		var b = jQuery("#" + a).form("validate", {validate:function () {
			return jQuery(this).validate();
		}});
		if (b) {
			jQuery("#" + a).submit();
		}
	}, 2000);
};
ExtCommWebUI.subtLoadJsvFmIdFn = function (id, cbfn) {
	ExtCommWebUI.loadBodyMask();
	setTimeout(function () {
		ExtCommWebUI.removeBodyMask();
		var _flag = jQuery("#" + id).form("validate", {validate:function () {
			return jQuery(this).validate();
		}});
		if (_flag) {
			if (cbfn) {
				if (eval(cbfn)) {
					jQuery("#" + id).submit();
				}
			}
		}
	}, 2000);
};
ExtCommWebUI.loadBodyMask = function () {
	var a = "\u6b63\u5728\u52a0\u8f7d\u6570\u636e\uff0c\u8bf7\u7a0d\u7b49\u3002\u3002\u3002";
	jQuery("<div class='body-mask'></div>").css({display:"block", width:"" + (window.screen.width) - 35 + "px", height:"" + (window.screen.height / 1.4) + "px"}).appendTo(jQuery("body"));
	jQuery("<div class='body-mask-msg'></div>").html(a).appendTo(jQuery("body")).css({display:"block", left:"" + (window.screen.width / 3) + "px", top:"" + (window.screen.height / 8) + "px"});
};
ExtCommWebUI.checkBodyMask = function () {
	var a = "\u6b63\u5728\u6821\u9a8c\u6570\u636e\uff0c\u8bf7\u7a0d\u7b49\u3002\u3002\u3002";
	jQuery("<div class='body-mask'></div>").css({display:"block", width:"" + (window.screen.width) - 35 + "px", height:"" + (window.screen.height / 1.4) + "px"}).appendTo(jQuery("body"));
	jQuery("<div class='body-mask-msg'></div>").html(a).appendTo(jQuery("body")).css({display:"block", left:"" + (window.screen.width / 3) + "px", top:"" + (window.screen.height / 8) + "px"});
};
ExtCommWebUI.removeBodyMask = function () {
	jQuery("body").find("div.body-mask-msg").remove();
	jQuery("body").find("div.body-mask").remove();
};
ExtCommWebUI.loadBMJsvFormId = function (b) {
	ExtCommWebUI.loadBodyMask();
	var a = jQuery("#" + b).form("validate", {validate:function () {
		return jQuery(this).validate();
	}});
	if (a) {
		jQuery("#" + b).submit();
	}
	setTimeout(function () {
		ExtCommWebUI.removeBodyMask();
	}, 5000);
};
ExtCommWebUI.loadSubmitUrl = function (a) {
	ExtCommWebUI.loadBodyMask();
	setTimeout(function () {
		ExtCommWebUI.submitUrl(a);
	}, 1000);
};
ExtCommWebUI.subtDelForm = function (b, d, a) {
	var c = jQuery("#" + d);
	if (c != undefined && b != undefined) {
		if ("" != c.val()) {
			a = a + c.val();
			ExtCommWebUI.confirmFmMsg(b, a, "\u60a8\u786e\u5b9a\u5220\u9664\u8be5\u8bb0\u5f55\uff1f");
		} else {
			ExtCommWebUI.infoMsg("\u8bf7\u60a8\u9009\u62e9\u5220\u9664\u7684\u8bb0\u5f55\uff01");
		}
	}
};
ExtCommWebUI.subtDelRadboxId = function (e, b, a) {
	var d = jQuery("#" + e);
	var c = jQuery("#" + b);
	if (d != undefined && c != undefined) {
		if ("" != d.val()) {
			a = a + d.val();
			ExtCommWebUI.confirmRoMsg2(c, d, a, "\u60a8\u786e\u5b9a\u5220\u9664\u8be5\u8bb0\u5f55\uff1f");
		} else {
			ExtCommWebUI.infoMsg("\u8bf7\u60a8\u9009\u62e9\u5220\u9664\u7684\u8bb0\u5f55\uff01");
		}
	}
};
ExtCommWebUI.subtSelectId = function (c, a) {
	var b = jQuery("#" + c);
	if (b != undefined) {
		if ("" != b.val()) {
			a = a + b.val();
			ExtCommWebUI.submitUrl(a);
		} else {
			ExtCommWebUI.infoMsg("\u8bf7\u60a8\u9009\u62e9\u9700\u8981\u7684\u8bb0\u5f55\uff01");
		}
	}
};
ExtCommWebUI.replaceHtml = function (c, a) {
	var b = document.getElementById("" + c + "");
	if (b != undefined) {
		b.innerHTML = a;
	}
}, ExtCommWebUI.mouseOverTip = function (b) {
	var a = document.getElementById("" + b + "");
	a.style.top = event.clientY + 18;
	a.style.left = (event.clientX - (event.clientX / 6));
	a.style.display = "block";
};
ExtCommWebUI.mouseOutTip = function (a) {
	document.getElementById("" + a + "").style.display = "none";
};
ExtCommWebUI.goBack = function () {
	window.history.back(-1);
};
ExtCommWebUI.mergeRows = function (e, d, b, c) {
	var a = jQuery("#" + e);
	if (a != undefined) {
		if ("auto" == "" + c + "") {
			c = a.datagrid("getRows").length;
		}
		if ("auto" == "" + d + "") {
			d = 0;
		}
		a.datagrid("mergeCells", {index:d, field:b, rowspan:c, colspan:null});
	}
};
ExtCommWebUI.mergeCols = function (e, d, c, a) {
	var b = jQuery("#" + e);
	if (b != undefined) {
		if ("auto" == "" + a + "") {
			a = b.datagrid("getColumnFields").length;
		}
		if ("auto" == "" + d + "") {
			d = 0;
		}
		b.datagrid("mergeCells", {index:d, field:c, rowspan:null, colspan:a});
	}
};
ExtCommWebUI.HashMap = function () {
	var a = 0;
	var b = new Object();
	this.put = function (d, e) {
		b[d] = e;
		a++;
	};
	this.putAll = function (e) {
		if (typeof e == "object" && !e.sort) {
			for (var d in e) {
				this.put(d, e[d]);
			}
		} else {
			throw "type is error!";
		}
	};
	this.get = function (d) {
		return b[d];
	};
	this.remove = function (d) {
		if (a == 0) {
			return;
		}
		delete b[d];
		a--;
	};
	this.containsKey = function (d) {
		if (b[d]) {
			return true;
		}
		return false;
	};
	this.containsValue = function (e) {
		for (var d in b) {
			if (b[d] == e) {
				return true;
			}
		}
		return false;
	};
	this.clear = function () {
		b = new Object();
		a = 0;
	};
	this.isEmpty = function () {
		return a == 0;
	};
	this.size = function () {
		return a;
	};
	this.keySet = function () {
		var e = new Array();
		for (var d in b) {
			e.push(d);
		}
		return e;
	};
	this.entrySet = function () {
		var e = new Array();
		for (var d in b) {
			var f = new Object();
			f[d] = b[d];
			e.push(f);
		}
		return e;
	};
	this.values = function () {
		var d = new Array();
		for (var e in b) {
			d.push(b[e]);
		}
		return d;
	};
	this.each = function (d) {
		for (var e in b) {
			d.call(this, e, b[e]);
		}
	};
	this.toString = function () {
		return c(b);
	};
	function c(f) {
		var e = [];
		if (typeof f == "string") {
			return "\"" + f.replace(/([\'\"\\])/g, "\\$1").replace(/(\n)/g, "\\n").replace(/(\r)/g, "\\r").replace(/(\t)/g, "\\t") + "\"";
		}
		if (typeof f == "object") {
			for (var d in f) {
				e.push("\"" + d + "\":" + c(f[d]));
			}
			if (!!document.all && !/^\n?function\s*toString\(\)\s*\{\n?\s*\[native code\]\n?\s*\}\n?\s*$/.test(f.toString)) {
				e.push("toString:" + f.toString.toString());
			}
			e = "{" + e.join() + "}";
			return e;
		}
		return f.toString();
	}
};
ExtCommWebUI.onEnterKey = function (a) {
	jQuery(document.body).unbind("keyup");
	jQuery(document.body).bind("keyup", function (b) {
		if (b.keyCode == 13) {
			jQuery("#" + a).click();
			jQuery(document.body).unbind("keyup");
		}
	});
};
ExtCommWebUI.asynLoadCbtree = function (id, idField) {
	jQuery("#" + id).combotree({onBeforeExpand:function (node) {
		var _opts = jQuery(this).tree("options");
		if (_opts) {
			var _url = _opts.url;
			if (_url) {
				if (_url.indexOf("" + idField + "") > 0) {
					_url = _url.substring(0, _url.indexOf("" + idField + "") - 1);
				}
				if (_url.indexOf("?") > 0) {
					_url = _url + "&" + idField + "=";
				} else {
					_url = _url + "?" + idField + "=";
				}
			}
			_opts.url = _url + node.id;
		}
	}});
};
ExtCommWebUI.asynACodeCbtree = function (id, pid, idField) {
	jQuery("#" + id).combotree({onBeforeExpand:function (node) {
		var _opts = jQuery(this).tree("options");
		if (_opts) {
			var _url = _opts.url;
			if (_url) {
				if (_url.indexOf("" + idField + "") > 0) {
					_url = _url.substring(0, _url.indexOf("" + idField + "") - 1);
				}
				if (_url.indexOf("?") > 0) {
					_url = _url + "&" + idField + "=";
				} else {
					_url = _url + "?" + idField + "=";
				}
			}
			//set parent id
			ExtCommWebUI.setCbtreePid(jQuery(this), pid, node.target);
			var _nId = node.id;
			var _currPid = jQuery("#" + pid).val();
			_nId = ("" + _nId).length == 2 ? "0" + _nId : _nId;
			if (_currPid != "") {
				_opts.url = _url + _currPid + "_" + _nId;
				jQuery("#" + pid).val("");
			}
			//alert("_opts.url=:" + _opts.url);
		}
	}});
};
ExtCommWebUI.setCbtreePid = function (obj, pid, target) {
	if (obj != null) {
		var _ptObj = obj.tree("getParent", target);
		if (_ptObj != null) {
			var _nPid = _ptObj.id;
			var _currPid = jQuery("#" + pid).val();
			if (_currPid != "") {
				_nPid = ("" + _nPid).length == 2 ? _currPid + "_0" + _nPid : _currPid + "_" + _nPid;
			} else {
				_nPid = ("" + _nPid).length == 2 ? "0" + _nPid : _nPid;
			}
			//save parent id
			jQuery("#" + pid).val(_nPid);
		}
	}
};
ExtCommWebUI.getACodeCbtree = function (id) {
	var _value = "";
	var _tree = jQuery("#" + id).combotree("tree");
	if (_tree) {
		var _nodes = _tree.tree("getChecked");
		jQuery.each(_nodes, function (i, _cn) {
			if ((_cn.id + "").length == 2) {
				_cn.id = "0" + _cn.id;
			}
			var _pn = _tree.tree("getParent", _cn.target);
			if (_pn) {
				if ((_pn.id + "").length == 2) { 
					_pn.id = "0" + _pn.id;
				}
				//alert("pn=:" + _pn.id + ",cn=:" + _pn.id + "_" + _cn.id);
				_value += "," + _pn.id + "_" + _cn.id;
			}else{
				_value+=","+_cn.id;
			}
		});
		return _value.substring(1, _value.length);
	}
};
