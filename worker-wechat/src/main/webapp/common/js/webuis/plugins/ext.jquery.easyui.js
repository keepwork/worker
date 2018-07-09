(function($){var getValuesExt=function(value){if(value!=undefined&&value!=""){var values=[];var _tmps=value.split(",");for(var i=0,len=_tmps.length;i<len;i++){values.push(""+_tmps[i]);}return values;}else{return[];}};$.extend($.fn.datagrid.defaults.editors.combobox,{getValue:function(jq){var opts=$(jq).combobox("options");if(opts.multiple){var values=$(jq).combobox("getValues");if(values.length>0){if(values[0]==""||values[0]==" "){return values.join(",").substring(1);}}return values.join(",");}else{return $(jq).combobox("getValue");}},setValue:function(jq,value){var opts=$(jq).combobox("options");if(opts.multiple&&value.indexOf(opts.separator)!=-1){var values=value.split(opts.separator);$(jq).combobox("setValues",values);}else{$(jq).combobox("setValue",value);}}});$.extend($.fn.datagrid.methods,{addEditor:function(jq,param){if(param instanceof Array){$.each(param,function(index,item){var e=$(jq).datagrid("getColumnOption",item.field);e.editor=item.editor;});}else{var e=$(jq).datagrid("getColumnOption",param.field);e.editor=param.editor;}},removeEditor:function(jq,param){if(param instanceof Array){$.each(param,function(index,item){var e=$(jq).datagrid("getColumnOption",item);e.editor={};});}else{var e=$(jq).datagrid("getColumnOption",param);e.editor={};}},keyMove:function(jq){return jq.each(function(){var grid=$(this);grid.datagrid("getPanel").panel("panel").attr("tabindex",1).bind("keydown",function(e){switch(e.keyCode){case 38:var selected=grid.datagrid("getSelected");if(selected){var index=grid.datagrid("getRowIndex",selected);grid.datagrid("selectRow",index-1);}else{var rows=grid.datagrid("getRows");grid.datagrid("selectRow",rows.length-1);}break;case 40:var selected=grid.datagrid("getSelected");if(selected){var index=grid.datagrid("getRowIndex",selected);grid.datagrid("selectRow",index+1);}else{grid.datagrid("selectRow",0);}break;}});});},addToolbars:function(jq,items){return jq.each(function(){var toolbar=$(this).parent().prev("div.datagrid-toolbar");for(var i=0;i<items.length;i++){var item=items[i];if(item==="-"){toolbar.append('<div class="datagrid-btn-separator"></div>');}else{var btn=$('<a href="javascript:void(0)"></a>');btn[0].onclick=eval(item.handler||function(){});btn.css("float","left").appendTo(toolbar).linkbutton($.extend({},item,{plain:true}));}}toolbar=null;});},removeToolbars:function(jq,param){return jq.each(function(){var btns=$(this).parent().prev("div.datagrid-toolbar").children("a");var cbtn=null;if(typeof param=="number"){cbtn=btns.eq(param);}else{if(typeof param=="string"){var text=null;btns.each(function(){text=$(this).data().linkbutton.options.text;if(text==param){cbtn=$(this);text=null;return;}});}}if(cbtn){var prev=cbtn.prev()[0];var next=cbtn.next()[0];if(prev&&next&&prev.nodeName=="DIV"&&prev.nodeName==next.nodeName){$(prev).remove();}else{if(next&&next.nodeName=="DIV"){$(next).remove();}else{if(prev&&prev.nodeName=="DIV"){$(prev).remove();}}}cbtn.remove();cbtn=null;}});}});$.extend($.fn.datagrid.defaults,{onLoadSuccess:function(){var target=$(this);var panel=$(this).datagrid("getPanel");var fields=$(this).datagrid("getColumnFields",false);var headerTds=panel.find(".datagrid-view2 .datagrid-header .datagrid-header-inner table tr:first-child").children();headerTds.each(function(i,obj){var col=target.datagrid("getColumnOption",fields[i]);if(!col.hidden&&!col.checkbox){$("div:first-child",obj).css("text-align","center");}});}});$.extend($.fn.datagrid.defaults.editors,{multcombotree:{init:function(container,options){var editor=$("<input />").appendTo(container);options.multiple=true;editor.combotree(options);return editor;},destroy:function(target){$(target).combotree("destroy");},getValue:function(target){return $(target).combotree("getValues").join(",");},setValue:function(target,value){$(target).combotree("setValues",getValuesExt(value));},resize:function(target,width){$(target).combotree("resize",width);}},datetimebox:{init:function(container,options){var editor=$("<input />").appendTo(container);editor.datetimebox(options);return editor;},destroy:function(target){$(target).datetimebox("destroy");},getValue:function(target){return $(target).datetimebox("getValue");},setValue:function(target,value){$(target).datetimebox("setValue",value);},resize:function(target,width){$(target).datetimebox("resize",width);}},extCalendar:{init:function(container,options){var input=$('<input id="Wdate" class="Wdate" onclick="CalendarWebUI()" />').appendTo(container);return input;},getValue:function(target){return $(target).val();},setValue:function(target,value){$(target).val(value);},resize:function(target,width){var input=$(target);if($.boxModel==true){input.width(width-(input.outerWidth()-input.width()));}else{input.width(width);}}},timespinner:{init:function(container,options){var editor=$("<input />").appendTo(container);editor.timespinner(options);return editor;},destroy:function(target){$(target).timespinner("destroy");},getValue:function(target){return $(target).timespinner("getValue");},setValue:function(target,value){$(target).timespinner("setValue",value);},resize:function(target,width){$(target).timespinner("resize",width);}},numberspinner:{init:function(container,options){var editor=$("<input />").appendTo(container);editor.numberspinner(options);return editor;},destroy:function(target){$(target).numberspinner("destroy");},getValue:function(target){return $(target).numberspinner("getValue");},setValue:function(target,value){$(target).numberspinner("setValue",value);},resize:function(target,width){$(target).numberspinner("resize",width);}},multcombobox:{init:function(container,options){var editor=$("<input />").appendTo(container);options.multiple=true;editor.combobox(options);return editor;},destroy:function(target){$(target).combobox("destroy");},getValue:function(target){return $(target).combobox("getValues").join(",");},setValue:function(target,value){$(target).combobox("setValues",getValuesExt(value));},resize:function(target,width){$(target).combobox("resize",width);}},combogrid:{init:function(container,options){var editor=$("<input />").appendTo(container);options.singleSelect=true;editor.combogrid(options);return editor;},destroy:function(target){$(target).combogrid("destroy");},getValue:function(target){return $(target).combogrid("getValue");},setValue:function(target,value){$(target).combogrid("setValue",value);},resize:function(target,width){$(target).combogrid("resize",width);}},multcombogrid:{init:function(container,options){var editor=$("<input />").appendTo(container);options.singleSelect=false;editor.combogrid(options);return editor;},destroy:function(target){$(target).combogrid("destroy");},getValue:function(target){return $(target).combogrid("getValues").join(",");},setValue:function(target,value){$(target).combogrid("setValues",getValuesExt(value));},resize:function(target,width){$(target).combogrid("resize",width);}},progressbar:{init:function(container,options){var bar=$("<div/>").appendTo(container);bar.progressbar(options);return bar;},getValue:function(target){return $(target).progressbar("getValue");},setValue:function(target,value){$(target).progressbar("setValue",value);},resize:function(target,width){if($.boxModel==true){$(target).progressbar("resize",width-(input.outerWidth()-input.width()));}else{$(target).progressbar("resize",width);}}}});$.fn.tree.defaults.loadFilter=function(data,parent){var opt=$(this).data().tree.options;var idFiled,textFiled,parentField;if(opt.parentField){idFiled=opt.idFiled||"id";textFiled=opt.textFiled||"text";parentField=opt.parentField;var i,l,treeData=[],tmpMap=[];for(i=0,l=data.length;i<l;i++){tmpMap[data[i][idFiled]]=data[i];}for(i=0,l=data.length;i<l;i++){if(tmpMap[data[i][parentField]]&&data[i][idFiled]!=data[i][parentField]){if(!tmpMap[data[i][parentField]]["children"]){tmpMap[data[i][parentField]]["children"]=[];}data[i]["text"]=data[i][textFiled];tmpMap[data[i][parentField]]["children"].push(data[i]);}else{data[i]["text"]=data[i][textFiled];treeData.push(data[i]);}}return treeData;}return data;};$.fn.treegrid.defaults.loadFilter=function(data,parentId){var opt=$(this).data().treegrid.options;var idFiled,textFiled,parentField;if(opt.parentField){idFiled=opt.idFiled||"id";textFiled=opt.textFiled||"text";parentField=opt.parentField;var i,l,treeData=[],tmpMap=[];for(i=0,l=data.length;i<l;i++){tmpMap[data[i][idFiled]]=data[i];}for(i=0,l=data.length;i<l;i++){if(tmpMap[data[i][parentField]]&&data[i][idFiled]!=data[i][parentField]){if(!tmpMap[data[i][parentField]]["children"]){tmpMap[data[i][parentField]]["children"]=[];}data[i]["text"]=data[i][textFiled];tmpMap[data[i][parentField]]["children"].push(data[i]);}else{data[i]["text"]=data[i][textFiled];treeData.push(data[i]);}}return treeData;}return data;};$.fn.combotree.defaults.loadFilter=$.fn.tree.defaults.loadFilter;$.extend($.fn.form.methods,{serialize:function(jq){var arrayValue=$(jq[0]).serializeArray();var json={};$.each(arrayValue,function(){var item=this;if(json[item["name"]]){json[item["name"]]=json[item["name"]]+","+item["value"];}else{json[item["name"]]=item["value"];}});return json;},getValue:function(jq,name){var jsonValue=$(jq[0]).form("serialize");return jsonValue[name];},setValue:function(jq,name,value){return jq.each(function(){_b(this,_29);var data={};data[name]=value;$(this).form("load",data);});}});$.extend($.fn.layout.methods,{remove:function(jq,region){return jq.each(function(){var panel=$(this).layout("panel",region);if(panel){panel.panel("destroy");var panels=$.data(this,"layout").panels;panels[region]=$(">div[region="+region+"]",$(this));$.data(this,"layout").panels=panels;$(this).layout("resize");}});},add:function(jq,params){return jq.each(function(){var container=$(this);var panel=$(">div[region="+params.region+"]",container);if(!panel.length){var pp=$("<div/>").attr("region",params.region).addClass("layout-body").appendTo(container);var cls="layout-panel layout-panel-"+params.region;pp.panel($.extend({},params.options,{cls:cls}));var panels=$.data(this,"layout").panels;panels[params.region]=pp;$.data(this,"layout").panels=panels;$(this).layout("resize");}});}});$.fn.panel.defaults=$.extend({},$.fn.panel.defaults,{onBeforeDestroy:function(){$(this).find(".combo-f").each(function(){var panel=$(this).data().combo.panel;panel.panel("destroy");});}});})(jQuery);