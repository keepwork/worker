if($.fn.pagination){$.fn.pagination.defaults.beforePageText="\u7b2c";$.fn.pagination.defaults.afterPageText="\u9875 | \u5171 {pages} \u9875";$.fn.pagination.defaults.displayMsg="| \u7b2c&nbsp;{from}&nbsp;\u81f3&nbsp;{to}&nbsp;&nbsp;|&nbsp;&nbsp;\u5171&nbsp;{total}&nbsp;\u8bb0\u5f55&nbsp;";}if($.fn.datagrid){$.fn.datagrid.defaults.loadMsg="\u6b63\u5728\u5904\u7406\uff0c\u8bf7\u7a0d\u7b49\u3002\u3002\u3002";}if($.fn.treegrid&&$.fn.datagrid){$.fn.treegrid.defaults.loadMsg=$.fn.datagrid.defaults.loadMsg;}if($.messager){$.messager.defaults.ok="\u786e \u5b9a";$.messager.defaults.cancel="\u53d6 \u6d88";}if($.fn.validatebox){$.fn.validatebox.defaults.missingMessage="\u8be5\u8f93\u5165\u9879\u4e3a\u5fc5\u586b\u9879\uff01";$.fn.validatebox.defaults.rules.email.message="\u8bf7\u8f93\u5165\u6709\u6548\u7684\u7535\u5b50\u90ae\u4ef6\u5730\u5740\uff01";$.fn.validatebox.defaults.rules.url.message="\u8bf7\u8f93\u5165\u6709\u6548\u7684URL\u5730\u5740\uff01";$.fn.validatebox.defaults.rules.length.message="\u8f93\u5165\u5185\u5bb9\u957f\u5ea6\u5fc5\u987b\u4ecb\u4e8e{0}\u548c{1}\u4e4b\u95f4\uff01";$.fn.validatebox.defaults.rules.remote.message="\u8bf7\u4fee\u6b63\u8be5\u5b57\u6bb5\uff01";}if($.fn.numberbox){$.fn.numberbox.defaults.missingMessage="\u8be5\u8f93\u5165\u9879\u4e3a\u5fc5\u586b\u9879\uff01";}if($.fn.combobox){$.fn.combobox.defaults.missingMessage="\u8be5\u8f93\u5165\u9879\u4e3a\u5fc5\u586b\u9879\uff01";}if($.fn.combotree){$.fn.combotree.defaults.missingMessage="\u8be5\u8f93\u5165\u9879\u4e3a\u5fc5\u586b\u9879\uff01";}if($.fn.combogrid){$.fn.combogrid.defaults.missingMessage="\u8be5\u8f93\u5165\u9879\u4e3a\u5fc5\u586b\u9879\uff01";}if($.fn.calendar){$.fn.calendar.defaults.weeks=["\u65e5","\u4e00","\u4e8c","\u4e09","\u56db","\u4e94","\u516d"];$.fn.calendar.defaults.months=["\u4e00\u6708","\u4e8c\u6708","\u4e09\u6708","\u56db\u6708","\u4e94\u6708","\u516d\u6708","\u4e03\u6708","\u516b\u6708","\u4e5d\u6708","\u5341\u6708","\u5341\u4e00\u6708","\u5341\u4e8c\u6708"];}if($.fn.datebox){$.fn.datebox.defaults.currentText="\u4eca \u5929";$.fn.datebox.defaults.closeText="\u5173 \u95ed";$.fn.datebox.defaults.okText="\u786e \u5b9a";$.fn.datebox.defaults.missingMessage="\u8be5\u8f93\u5165\u9879\u4e3a\u5fc5\u586b\u9879\uff01";$.fn.datebox.defaults.formatter=function(b){var e=b.getFullYear();var a=b.getMonth()+1;var c=b.getDate();return e+"-"+(a<10?("0"+a):a)+"-"+(c<10?("0"+c):c);};$.fn.datebox.defaults.parser=function(c){if(!c){return new Date();}var b=c.split("-");var f=parseInt(b[0],10);var a=parseInt(b[1],10);var e=parseInt(b[2],10);if(!isNaN(f)&&!isNaN(a)&&!isNaN(e)){return new Date(f,a-1,e);}else{return new Date();}};}if($.fn.datetimebox&&$.fn.datebox){$.extend($.fn.datetimebox.defaults,{currentText:$.fn.datebox.defaults.currentText,closeText:$.fn.datebox.defaults.closeText,okText:$.fn.datebox.defaults.okText,missingMessage:$.fn.datebox.defaults.missingMessage});}