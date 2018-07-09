//post
var TimeTag = {
	// url：请求地址，acid:活动Id，dataid：模块数据ID,dataTCode：模块数据类型，acTCode：活动类型编码，与活动类型的编码对应
	addConfig : function(url, acid, dataid, dataTCode, acTCode, htmlId) {
		jQuery.post(url, {
			acId : acid,
			dataId : dataid,
			dataTypeCode : dataTCode,
			acTypeCode : acTCode,
			TimeTag_Name : htmlId
		}, function(msg) {
			jQuery("#" + htmlId).html(msg);
		});
	},
	// （循环次数，要选中的值,要追加的点）
	initSelectTime : function(num, selected, attrId) {
		for ( var i = 1; i <= num; i++) {
			var selectTime = "";
			if (selected == i) {
				selectTime += "<option value='" + i + "' selected='selected'>"
						+ i + "</option>";
			} else {
				selectTime += "<option value='" + i + "'>" + i + "</option>";
			}
			jQuery("#" + attrId).append(selectTime);
		}
	},
	// checkbox复选框拼装
	input_checkBox : function(num, checked, checkboxName, attrId) {
		var bbr = "";
		var arr = checked.split(",");
		var conts = "";
		for ( var i = 1; i <= num; i++) {
			var checkedBox = "";
			for ( var j = 0; j < arr.length; j++) {
				if (arr[j] == i) {
					checkedBox = " checked='checked' ";
				}
			}
			if (i % 10 == 0) {
				bbr = "</br>";
			}
			conts += "<input type='checkbox' name='" + checkboxName
					+ "' class='va_mid mr2'  value='" + i + "' " + checkedBox
					+ "/><span class='va_mid'>" + i + "</span>&nbsp;" + bbr;
			bbr = "";
		}
		jQuery("#" + attrId).append(conts);
	},
	// checkbox 至少被选中一个（'checkbox中name的值','要追加的位置Id','提示消息'）
	verifyCheckbox : function(inName) {
		var platids = "";
		jQuery("input[name='" + inName + "']").each(function() {
			if (this.checked) {
				platids += jQuery(this).val() + ",";
			}
		});
		return platids;
	},
	// 删除时间控件时间段表（urlobj 是请求url，data 是传递参数）
	delAssigned : function(urlobj, data) {
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
				} else {
					// 参数为空
					flag = false;
				}
			}
		});
		return flag;
	}
};