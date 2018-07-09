//动态select查询拼装(请求地址，要追加的位置ID，定位被选择checkbox如：123,234,345,）
function ajaxCheckBox(urlobj, id, checkboxName, objvalue) {
	jQuery("#" + id).text("");// 清空数据
	jQuery
			.ajax({
				type : 'post',
				url : urlobj,
				dateType : 'json',
				success : function(msg) {
					var obj = eval('(' + msg + ')');
					jQuery
							.each(
									obj,
									function(key, value) {
										var arr = objvalue.split(',');
										var checkedBox = "<label class='mr20'><input type='checkbox' name='"
												+ checkboxName
												+ "' class='va_mid mr2'  value='"
												+ key + "' ";
										if (arr.length > 1) {
											for ( var i = 0; i < arr.length - 1; i++) {
												if (key == arr[i]) {
													checkedBox += " checked='checked' ";
												}
											}
										} else {
											if (key == arr) {
												checkedBox += " checked='checked' ";
											}
										}
										checkedBox += "/><span class='va_mid'>"
												+ value + "</span></label>";
										jQuery("#" + id).append(checkedBox);
									});
				}
			});
}
// 动态select查询拼装(请求地址，要追加的位置ID，定位被选择checkbox如：123,234,345,）
function ajaxCheckBoxClick(urlobj, id, checkboxName, onclick, objvalue) {
	jQuery("#" + id).text("");// 清空数据
	var head = "";

	head += "<label class='mr20'><input type='checkbox' class='va_mid mr2' id='acType_allactive' name='acType' value='allactive' onClick='checkAllAcType(this.checked)' ";
	if (objvalue == "allactive") {
		head += " checked='checked' ";
	}
	head += "/><span class='va_mid' for='acType_allactive'>全部</span></lable>";
	jQuery("#" + id).append(head);
	jQuery
			.ajax({
				type : 'post',
				url : urlobj,
				dateType : 'json',
				success : function(msg) {
					var obj = eval('(' + msg + ')');
					jQuery
							.each(
									obj,
									function(key, value) {
										var arr = objvalue.split(',');
										var checkedBox = "<label class='mr20'><input type='checkbox' name='"
												+ checkboxName
												+ "' onclick='"
												+ onclick
												+ "' class='va_mid mr2'  value='"
												+ key + "' ";
										if (arr.length > 1) {

											for ( var i = 0; i < arr.length - 1; i++) {
												if (key == arr[i]) {
													checkedBox += " checked='checked' ";
												}
											}
										} else {

											if (key == arr
													|| arr == "allactive") {
												checkedBox += " checked='checked' ";
											}
										}
										checkedBox += "/><span class='va_mid'>"
												+ value + "</span></label>";
										jQuery("#" + id).append(checkedBox);
									});
				}
			});
}
// 动态select查询拼装(请求地址，要追加的位置ID，定位被选择radio如：123,234,345,）
function ajaxRadio(urlobj, id, radioName, objvalue, postActivityType,state) {
	jQuery("#" + id).text("");// 清空数据
	jQuery
			.ajax({
				type : 'post',
				url : urlobj,
				dateType : 'json',
				async:false,
				success : function(msg) {
					var obj = eval('(' + msg + ')');
					var oLen=0;
					for(var o in obj){
						oLen++;
					}
					jQuery
							.each(
									obj,
									function(key, value) {
										var arr = objvalue.split(',');
										var checkedBox = "<label class='mr20'><input type='radio' name='"
												+ radioName
												+ "' value='"
												+ key
												+ "'  class='va_mid mr2'  onclick='"
												+ postActivityType
												+ "\(\""
												+ key + "\"\)'  ";
										if (arr.length > 1) {
											for ( var i = 0; i < arr.length - 1; i++) {
												if (key == arr[i]) {
													checkedBox += " checked='checked' ";
												//	loadSelfAcInfo(key);
												}
											}
										} else {
											if (key == arr) {
												checkedBox += " checked='checked' ";
											//	loadSelfAcInfo(key);
											}else{
												if(oLen==1){//如果只有一个活动类型，则默认选中
													checkedBox += " checked='checked' ";
												//	loadSelfAcInfo(key);
												}
											}
										}
										if(state=='9'){//暂停
											checkedBox += " disabled='true'";
										}
										checkedBox += "/><span class='va_mid'>"
												+ value + "</span></label>";
										jQuery("#" + id).append(checkedBox);
									});
				}
			});
}
// checkbox 至少被选中一个（'checkbox中name的值','要追加的位置Id','提示消息'）
function verifyCheckbox(inName, outId, altmsg) {
	var platids = "";
	var flag = false;
	jQuery("input[name='" + inName + "']").each(function() {
		if (this.checked) {
			flag = true;
			platids += jQuery(this).val() + ",";
		}
	});
	if (flag) {
		jQuery("#" + outId).val(platids);
		return true;
	} else {
		alert(altmsg);
		return false;
	}
}

function verifyCheckboxFlag(inName, outId) {
	var platids = "";
	var flag = false;
	jQuery("input[name='" + inName + "']").each(function() {
		if (this.checked) {
			flag = true;
			platids += jQuery(this).val() + ",";
		}
	});
	if (flag) {
		jQuery("#" + outId).val(platids);
		return true;
	} else {
		return false;
	}
}
// form表单异步提交
function submitAjaxForm(formId, url, trId) {
	jQuery
			.ajax({
				url : url, // 提交的页面
				data : $("#" + formId).serialize(), // 从表单中获取数据
				type : "POST", // 设置请求类型为"POST"，默认为"GET"
				dataType : "json",
				async : false,
				contentType : "application/x-www-form-urlencoded;charset=UTF-8", // 必须要设置为UTF-8，否则提交数据乱码
				success : function(msg) {
					var obj = eval(msg);
					var td1 = "";
					var td = "";
					jQuery.each(obj, function(key, value) {
						td += "<td>" + key + "</td>";
						td1 += "<td>" + value + "</td>";
					});
					window.parent.jQuery("#" + trId + " span").replaceWith(td);
					var tr = "<tr id="
							+ obj.id
							+ " class='even'>"
							+ td1
							+ "<td><input type='button' value='删除' onclick='removeThis(this)' ></input></td></tr>";
					window.parent.jQuery("#" + trId).after(tr);
				},
				cache : false
			});
}
// 异步提交form表单
function ajaxForm(formId, url) {
	var flag=false;
	jQuery.ajax({
		url : url, // 提交的页面
		data : $("#" + formId).serialize(), // 从表单中获取数据
		type : "POST", // 设置请求类型为"POST"，默认为"GET"
		dataType : "json",
		async : false,
		contentType : "application/x-www-form-urlencoded;charset=UTF-8", // 必须要设置为UTF-8，否则提交数据乱码
		success : function(msg) {
			var obj = eval(msg);
			if (obj.msg == 'ok') {
				flag = true;
			} else {
				flag = false;
			}
		},
		cache : false
	});
	return flag;
}

// 追加 内容到父页面
function addContent(tableId, trId) {
	jQuery("input[name='ckids']")
			.each(
					function() {
						if (this.checked) {
							var id = jQuery(this).val();

							window.parent.jQuery("#" + tableId + " tr").each(
									function() {
										window.parent.jQuery("#" + id).hide();
									});
							var tr = "<tr id="
									+ id
									+ " class='even'>"
									+ jQuery(this).parent().parent().html()
									+ "<td><input type='button' value='删除' onclick='removeThis(this)' ></input></td></tr>";
							window.parent.jQuery("#" + trId).after(tr);
						}
					});
	window.parent.hideCover('cover', 'pop');
}
// 唯一验证（urlobj 是请求url，data 是传递参数）
function verifyNo(urlobj, data) {
	var flag=false;
	jQuery.ajax({
		type : 'post',
		url : urlobj,
		data : {
			ActivityNo : data
		},
		async : false,
		success : function(msg) {
			if (msg == 'ok') {
				flag = true;
			} else if (msg == 'no') {
				flag = false;
			} else if (msg == 'error') {
				// 参数为空
				flag = false;
			}
		}
	});
	return flag;
}
// 唯一验证（urlobj 是请求url，参数1，参数2 ）
function upOrdownAjax(urlobj, acid, upordown) {
	var flag="true";
	jQuery.ajax({
		type : 'post',
		url : urlobj,
		data : {
			acId : acid,
			upOrdown : upordown
		},
		async : false,
		success : function(msg) {
			if (msg == 'ok') {
				flag = "true";
			}else{			// 参数为空
				flag = msg;
			}
		}
	});
	return flag;
}
// 有返回值的异步请求
function verifyReturnobj(urlobj, data) {

	var flag = '';
	jQuery.ajax({
		type : 'post',
		url : urlobj,
		data : {
			typeId : data
		},
		async : false,
		success : function(msg) {
			flag = msg;
		}
	});
	return flag;
}
// 动态select查询拼装(请求地址，要追加的位置ID，定位选择id）
function ajaxSelect(urlobj, id, objvalue) {
	jQuery("#" + id).text("");// 清空数据
	jQuery.ajax({
		type : 'post',
		url : urlobj,
		dateType : 'json',
		success : function(msg) {
			var obj = eval('(' + msg + ')');
			jQuery("#" + id).append("<option value=''>--请选择--</option>");
			jQuery.each(obj, function(key, value) {
				var opetion = "";
				if (key == objvalue) {
					opetion = "<option value='" + key
							+ "' selected='selected'>" + value + "</option>";
				} else {
					opetion = "<option value='" + key + "'>" + value
							+ "</option>";
				}
				jQuery("#" + id).append(opetion);
			});
		}
	});
}
// 动态select查询拼装含参数 (请求地址，要追加的位置ID，定位选择id，查询参数)
function ajaxSelectData(urlobj, id, objvalue, dataobj) {
	jQuery("#" + id).text("");// 清空数据
	jQuery.ajax({
		type : 'post',
		url : urlobj,
		data : {
			objectFilter : dataobj
		},
		dateType : 'json',
		success : function(msg) {
			var obj = eval('(' + msg + ')');
			jQuery("#" + id).append("<option value=''>--请选择--</option>");
			jQuery.each(obj, function(key, value) {
				var opetion = "";
				if (key == objvalue) {
					opetion = "<option value='" + key
							+ "' selected='selected'>" + value + "</option>";
				} else {
					opetion = "<option value='" + key + "'>" + value
							+ "</option>";
				}
				jQuery("#" + id).append(opetion);
			});
		}
	});
}
// textarea 长度限制
// 使用方法 <textarea maxlength="100" onkeyup="return isMaxLen(this)/>"
function isMaxLen(o) {
	var nMaxLen = o.getAttribute ? parseInt(o.getAttribute("maxlength")) : "";
	if (o.getAttribute && o.value.length > nMaxLen) {
		o.value = o.value.substring(0, nMaxLen);
	}
}
// input 控制
// 使用方法
// verifyInput("id","提示语")
//
function verifyInput(id, msg) {
	var obj = trim_str(jQuery("#" + id).val());
	if (obj == "" || obj == null) {
		alert(msg);
		return false;
	} else {
		return true;
	}
}
// 只能是英文或数字
function verifyInputCode(id, msg) {
	var obj = trim_str(jQuery("#" + id).val());
	var reg = "^[a-zA-Z0-9]+$";
	if (obj.search(reg) == -1) {
		alert(msg);
		return false;
	} else {
		return true;
	}
}
function trim_str(obj) {
	return obj.replace(/(^\s*)|(\s*$)/g, "");
}
// 结束

// 日期控制
// 使用方法：
// verifyDate("id1","id2");
function verifyDate(startId, endId) {
	var date = new Date();
	var sysdate = date.format("yyyy-MM-dd");
	var startTime = jQuery("#" + startId).val();
	var endTime = jQuery("#" + endId).val();
	if (startTime < sysdate) {
		alert("开始时间不能小于系统时间");
		return false;
	} else if (startTime >= endTime) {
		alert("结束时间不能小于开始时间");
		return false;
	} else {
		return true;
	}
}
function verifyDateBetween(startId, endId) {
	var date = new Date();
	var sysdate = date.format("yyyy-MM-dd");
	var startTime = jQuery("#" + startId).val();
	var endTime = jQuery("#" + endId).val();
	if (startTime >= endTime) {
		alert("结束时间不能小于或等于开始时间！");
		return false;
	} else {
		return true;
	}
}
// 再用
function verifySysDate(dateId) {
	var date = new Date();
	var sysdate = date.format("yyyy-MM-dd");
	var startTime = jQuery("#" + dateId).val();
	if (startTime < sysdate) {
		alert("选择时间不能小于系统时间");
		return false;
	} else {
		return true;
	}
}
// js日期格式化
// 使用方法
// var date = new Date();
// var sysdate = date.format("yyyy-MM-dd");
Date.prototype.format = function(format) {
	var o = {
		"M+" : this.getMonth() + 1, // month
		"d+" : this.getDate(), // day
		"h+" : this.getHours(), // hour
		"m+" : this.getMinutes(), // minute
		"s+" : this.getSeconds(), // second
		"q+" : Math.floor((this.getMonth() + 3) / 3), // quarter
		"S" : this.getMilliseconds()
	// millisecond
	};

	if (/(y+)/.test(format)) {
		format = format.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	}

	for ( var k in o) {
		if (new RegExp("(" + k + ")").test(format)) {
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
					: ("00" + o[k]).substr(("" + o[k]).length));
		}
	}
	return format;
};

// 文本框只能输入数字小数点
function clearNoNum(obj) {
	obj.value = obj.value.replace(/[^\d.]/g, ""); // 清除“数字”和“.”以外的字符
	obj.value = obj.value.replace(/^\./g, ""); // 验证第一个字符是数字而不是.
	obj.value = obj.value.replace(/\.{2,}/g, "."); // 只保留第一个. 清除多余的.
	obj.value = obj.value.replace(".", "$#$").replace(/\./g, "").replace("$#$",
			".");
}

// 加法函数
function accAdd(arg1, arg2) {
	var r1, r2, m;
	try {
		r1 = arg1.toString().split(".")[1].length;
	} catch (e) {
		r1 = 0;
	}
	try {
		r2 = arg2.toString().split(".")[1].length;
	} catch (e) {
		r2 = 0;
	}
	m = Math.pow(10, Math.max(r1, r2));
	return (arg1 * m + arg2 * m) / m;
}
// 减法函数
function accDel(arg1, arg2) {
	var r1, r2, m;
	try {
		r1 = arg1.toString().split(".")[1].length;
	} catch (e) {
		r1 = 0;
	}
	try {
		r2 = arg2.toString().split(".")[1].length;
	} catch (e) {
		r2 = 0;
	}
	m = Math.pow(10, Math.max(r1, r2));
	return (arg1 * m - arg2 * m) / m;
}
// ec全选全不选bug修改
function checkClick() {
	var sign = true;
	jQuery("input[name='ckids']").each(function() {
		if (!this.checked) {
			sign = false;
		}
	});
	jQuery("#ckids_selector").attr("checked", sign);
}
function hideCover(cover, id) {
	$("#" + id).floatDiv({
		onOff : false
	});
};
function showCover(cover, id) {
	$("#" + id).floatDiv({
		cover : true
	});
};

// 页面table相同内容相同列合并
// 开始
// 使用方法<table id="tablerowId"></table>
// $('#tablerowId').mergeCell({
// 目前只有cols这么一个配置项, 用数组表示列的索引,从0开始
// 然后根据指定列来处理(合并)相同内容单元格
// cols: [0, 3]
// });
(function($) {
	// 看过jquery源码就可以发现$.fn就是$.prototype, 只是为了兼容早期版本的插件
	// 才保留了jQuery.prototype这个形式
	$.fn.mergeCell = function mergeCell(options) {
		return $(this).each(function() {
			var cols = options.cols;
			if (cols) {
				for ( var i = cols.length - 1; cols[i] != undefined; i--) {
					// fixbug console调试
					// console.debug(cols[i]);
					mergeCell($(this), cols[i]);
				}
			}
			dispose($(this));
		});
	};
	// 如果对javascript的closure和scope概念比较清楚, 这是个插件内部使用的private方法
	// 具体可以参考本人前一篇随笔里介绍的那本书
	function mergeCell($table, colIndex) {

		$table.data('col-content', ''); // 存放单元格内容
		$table.data('col-rowspan', 1); // 存放计算的rowspan值 默认为1
		$table.data('col-td', $()); // 存放发现的第一个与前一行比较结果不同td(jQuery封装过的),
		// 默认一个"空"的jquery对象
		$table.data('trNum', $('tbody tr', $table).length); // 要处理表格的总行数,
		// 用于最后一行做特殊处理时进行判断之用

		// 我们对每一行数据进行"扫面"处理 关键是定位col-td, 和其对应的rowspan
		$('tbody tr', $table).each(
				function(index) {
					// td:eq中的colIndex即列索引
					var $td = $('td:eq(' + colIndex + ')', this);

					// 取出单元格的当前内容
					var currentContent = $td.html();

					// 第一次时走此分支
					if ($table.data('col-content') == '') {

						$table.data('col-content', currentContent);
						$table.data('col-td', $td);

					} else {
						// 上一行与当前行内容相同
						if ($table.data('col-content') == currentContent) {
							// 上一行与当前行内容相同则col-rowspan累加, 保存新值
							var rowspan = $table.data('col-rowspan') + 1;
							$table.data('col-rowspan', rowspan);
							// 值得注意的是 如果用了$td.remove()就会对其他列的处理造成影响
							$td.hide();

							// 最后一行的情况比较特殊一点
							// 比如最后2行 td中的内容是一样的,
							// 那么到最后一行就应该把此时的col-td里保存的td设置rowspan
							if (++index == $table.data('trNum'))
								$table.data('col-td').attr('rowspan',
										$table.data('col-rowspan'));

						} else { // 上一行与当前行内容不同
							// col-rowspan默认为1, 如果统计出的col-rowspan没有变化, 不处理
							if ($table.data('col-rowspan') != 1) {
								$table.data('col-td').attr('rowspan',
										$table.data('col-rowspan'));
							}
							// 保存第一次出现不同内容的td, 和其内容, 重置col-rowspan
							$table.data('col-td', $td);
							$table.data('col-content', $td.html());
							$table.data('col-rowspan', 1);
						}
					}
				});
	}

	// 同样是个private函数 清理内存之用
	function dispose($table) {
		$table.removeData();
	}
})(jQuery);
// 结束

// 全选
function allChecked() {
	var flag = true;
	jQuery("input[name=ckids]").each(function() {
		if (!this.checked) {
			flag = false;
		}
	});
	jQuery("#ckids_selector").attr("checked", flag);

}
//动态select查询拼装(请求地址，要追加的位置ID，定位被选择objvalue如：123,234,345,;dataId:后台查询条件） add 2013-12-12 14:14:19
function ajaxCheckBoxNew(urlobj, id, checkboxName, objvalue,dataId) {
	jQuery
			.ajax({
				type : 'post',
				url : urlobj,
				data:{dataId:dataId},
				async : false,
				dateType : 'json',
				success : function(msg) {
					var obj = eval('(' + msg + ')');
					jQuery
							.each(
									obj,
									function(key, value) {
										var arr = objvalue.split(',');
										var checkedBox = "<label class='mr20'><input type='checkbox' name='"
												+ checkboxName
												+ "' class='va_mid mr2'  value='"
												+ key + "' ";
										if (arr.length > 1) {
											for ( var i = 0; i < arr.length - 1; i++) {
												if (key == arr[i]) {
													checkedBox += " checked='checked' ";
												}
											}
										} else {
											if (key == arr) {
												checkedBox += " checked='checked' ";
											}
										}
										checkedBox += "/><span class='va_mid'>"
												+ value + "</span></label>";
										jQuery("#" + id).append(checkedBox);
									});
				}
			});
}

function clearNoNumInteger(obj) {
	obj.value = obj.value.replace(/[^\d]/g, ""); // 清除“数字”和“.”以外的字符
	obj.value = obj.value.replace(/^\./g, ""); // 验证第一个字符是数字而不是.
	obj.value = obj.value.replace(/\.{2,}/g, "."); // 只保留第一个. 清除多余的.
	obj.value = obj.value.replace(".", "$#$").replace(/\./g, "").replace("$#$",
			".");
}
function clearNoNumIntegerZero(obj) {
	obj.value = obj.value.replace(/[^\d]/g, ""); // 清除“数字”和“.”以外的字符
	obj.value = obj.value.replace(/^\./g, ""); // 验证第一个字符是数字而不是.
	obj.value = obj.value.replace(/\.{2,}/g, "."); // 只保留第一个. 清除多余的.
	obj.value = obj.value.replace(".", "$#$").replace(/\./g, "").replace("$#$",".");
	if( obj.value.length = 1 && obj.value == 0){
		obj.value = obj.value.replace("0", "");
	}
};

$(function(){
//IE也能用textarea
$("textarea[maxlength]").keyup(function(){
	var area=$(this);
	var max=parseInt(area.attr("maxlength"),10); //获取maxlength的值
	if(max>0){
		if(area.val().length>max){ //textarea的文本长度大于maxlength
			area.val(area.val().substr(0,max)); //截断textarea的文本重新赋值
		}
	}
});
//复制的字符处理问题
$("textarea[maxlength]").blur(function(){
	var area=$(this);
	var max=parseInt(area.attr("maxlength"),10); //获取maxlength的值
	if(max>0){
		if(area.val().length>max){ //textarea的文本长度大于maxlength
			area.val(area.val().substr(0,max)); //截断textarea的文本重新赋值
		}
	}
});
}) 
