(function(e){var w={event:{CHECK:"ztree_check"},id:{CHECK:"_check"},checkbox:{STYLE:"checkbox",DEFAULT:"chk",DISABLED:"disable",FALSE:"false",TRUE:"true",FULL:"full",PART:"part",FOCUS:"focus"},radio:{STYLE:"radio",TYPE_ALL:"all",TYPE_LEVEL:"level"}},k={check:{enable:false,autoCheckTrigger:false,chkStyle:w.checkbox.STYLE,nocheckInherit:false,radioType:w.radio.TYPE_LEVEL,chkboxType:{"Y":"ps","N":"ps"}},data:{key:{checked:"checked"}},callback:{beforeCheck:null,onCheck:null}},t=function(x){var y=v.getRoot(x);y.radioCheckedList=[];},c=function(x){},l=function(x){var y=x.treeObj,z=o.event;y.unbind(z.CHECK);y.bind(z.CHECK,function(B,C,A){r.apply(x.callback.onCheck,[B,C,A]);});},m=function(D){var E=D.target,G=v.getSetting(D.data.treeId),B="",y=null,z="",C="",x=null,A=null;if(r.eqs(D.type,"mouseover")){if(G.check.enable&&r.eqs(E.tagName,"button")&&E.getAttribute("treeNode"+o.id.CHECK)!==null){B=E.parentNode.id;z="mouseoverCheck";}}else{if(r.eqs(D.type,"mouseout")){if(G.check.enable&&r.eqs(E.tagName,"button")&&E.getAttribute("treeNode"+o.id.CHECK)!==null){B=E.parentNode.id;z="mouseoutCheck";}}else{if(r.eqs(D.type,"click")){if(G.check.enable&&r.eqs(E.tagName,"button")&&E.getAttribute("treeNode"+o.id.CHECK)!==null){B=E.parentNode.id;z="checkNode";}}}}if(B.length>0){y=v.getNodeCache(G,B);switch(z){case"checkNode":x=j.onCheckNode;break;case"mouseoverCheck":x=j.onMouseoverCheck;break;case"mouseoutCheck":x=j.onMouseoutCheck;break;}}var F={stop:false,node:y,nodeEventType:z,nodeEventCallback:x,treeEventType:C,treeEventCallback:A};return F;},s=function(A,E,D,x,C,z,B){if(!D){return;}var y=A.data.key.checked;if(typeof D[y]=="string"){D[y]=r.eqs(D[y],"true");}D[y]=!!D[y];D.checkedOld=D[y];D.nocheck=!!D.nocheck||(A.check.nocheckInherit&&x&&!!x.nocheck);D.chkDisabled=!!D.chkDisabled||(x&&!!x.chkDisabled);if(typeof D.halfCheck=="string"){D.halfCheck=r.eqs(D.halfCheck,"true");}D.halfCheck=!!D.halfCheck;D.check_Child_State=-1;D.check_Focus=false;D.getCheckStatus=function(){return v.getCheckStatus(A,D);};if(z){v.makeChkFlag(A,x);}},a=function(z,B,y){var x=z.data.key.checked;if(z.check.enable){v.makeChkFlag(z,B);if(z.check.chkStyle==o.radio.STYLE&&z.check.radioType==o.radio.TYPE_ALL&&B[x]){var A=v.getRoot(z);A.radioCheckedList.push(B);}y.push("<button type='button' ID='",B.tId,o.id.CHECK,"' class='",h.makeChkClass(z,B),"' treeNode",o.id.CHECK," onfocus='this.blur();' ",(B.nocheck===true?"style='display:none;'":""),"></button>");}},i=function(z,y){y.checkNode=function(E,D,F,C){var A=this.setting.data.key.checked;if(E.chkDisabled===true){return;}if(D!==true&&D!==false){D=!E[A];}C=!!C;if(E[A]===D&&!F){return;}else{if(C&&r.apply(this.setting.callback.beforeCheck,[this.setting.treeId,E],true)==false){return;}}if(r.uCanDo(this.setting)&&this.setting.check.enable&&E.nocheck!==true){E[A]=D;var B=e("#"+E.tId+o.id.CHECK);if(F||this.setting.check.chkStyle===o.radio.STYLE){h.checkNodeRelation(this.setting,E);}h.setChkClass(this.setting,B,E);h.repairParentChkClassWithSelf(this.setting,E);if(C){z.treeObj.trigger(o.event.CHECK,[z.treeId,E]);}}};y.checkAllNodes=function(A){h.repairAllChk(this.setting,!!A);};y.getCheckedNodes=function(B){var A=this.setting.data.key.children;B=(B!==false);return v.getTreeCheckedNodes(this.setting,v.getRoot(z)[A],B);};y.getChangeCheckedNodes=function(){var A=this.setting.data.key.children;return v.getTreeChangeCheckedNodes(this.setting,v.getRoot(z)[A]);};y.setChkDisabled=function(B,A){A=!!A;h.repairSonChkDisabled(this.setting,B,A);if(!A){h.repairParentChkDisabled(this.setting,B,A);}};var x=y.updateNode;y.updateNode=function(C,D){if(x){x.apply(y,arguments);}if(!C||!this.setting.check.enable){return;}var A=e("#"+C.tId);if(A.get(0)&&r.uCanDo(this.setting)){var B=e("#"+C.tId+o.id.CHECK);if(D==true||this.setting.check.chkStyle===o.radio.STYLE){h.checkNodeRelation(this.setting,C);}h.setChkClass(this.setting,B,C);h.repairParentChkClassWithSelf(this.setting,C);}};},n={getRadioCheckedList:function(A){var z=v.getRoot(A).radioCheckedList;for(var y=0,x=z.length;y<x;y++){if(!v.getNodeCache(A,z[y].tId)){z.splice(y,1);y--;x--;}}return z;},getCheckStatus:function(y,A){if(!y.check.enable||A.nocheck){return null;}var x=y.data.key.checked,z={checked:A[x],half:A.halfCheck?A.halfCheck:(y.check.chkStyle==o.radio.STYLE?(A.check_Child_State===2):(A[x]?(A.check_Child_State>-1&&A.check_Child_State<2):(A.check_Child_State>0)))};return z;},getTreeCheckedNodes:function(C,z,E,B){if(!z){return[];}var D=C.data.key.children,y=C.data.key.checked;B=!B?[]:B;for(var A=0,x=z.length;A<x;A++){if(z[A].nocheck!==true&&z[A][y]==E){B.push(z[A]);}v.getTreeCheckedNodes(C,z[A][D],E,B);}return B;},getTreeChangeCheckedNodes:function(C,z,B){if(!z){return[];}var D=C.data.key.children,y=C.data.key.checked;B=!B?[]:B;for(var A=0,x=z.length;A<x;A++){if(z[A].nocheck!==true&&z[A][y]!=z[A].checkedOld){B.push(z[A]);}v.getTreeChangeCheckedNodes(C,z[A][D],B);}return B;},makeChkFlag:function(G,A){if(!A){return;}var z=G.data.key.children,x=G.data.key.checked,C=-1;if(A[z]){var y=false;for(var E=0,B=A[z].length;E<B;E++){var F=A[z][E];var D=-1;if(G.check.chkStyle==o.radio.STYLE){if(F.nocheck===true){D=F.check_Child_State;}else{if(F.halfCheck===true){D=2;}else{if(F.nocheck!==true&&F[x]){D=2;}else{D=F.check_Child_State>0?2:0;}}}if(D==2){C=2;break;}else{if(D==0){C=0;}}}else{if(G.check.chkStyle==o.checkbox.STYLE){if(F.nocheck===true){D=F.check_Child_State;}else{if(F.halfCheck===true){D=1;}else{if(F.nocheck!==true&&F[x]){D=(F.check_Child_State===-1||F.check_Child_State===2)?2:1;}else{D=(F.check_Child_State>0)?1:0;}}}if(D===1){C=1;break;}else{if(D===2&&y&&D!==C){C=1;break;}else{if(C===2&&D>-1&&D<2){C=1;break;}else{if(D>-1){C=D;}}}}if(!y){y=(F.nocheck!==true);}}}}}A.check_Child_State=C;}},f={},j={onCheckNode:function(B,A){if(A.chkDisabled===true){return false;}var z=v.getSetting(B.data.treeId),x=z.data.key.checked;if(r.apply(z.callback.beforeCheck,[z.treeId,A],true)==false){return true;}A[x]=!A[x];h.checkNodeRelation(z,A);var y=e("#"+A.tId+o.id.CHECK);h.setChkClass(z,y,A);h.repairParentChkClassWithSelf(z,A);z.treeObj.trigger(o.event.CHECK,[z.treeId,A]);return true;},onMouseoverCheck:function(A,z){if(z.chkDisabled===true){return false;}var y=v.getSetting(A.data.treeId),x=e("#"+z.tId+o.id.CHECK);z.check_Focus=true;h.setChkClass(y,x,z);return true;},onMouseoutCheck:function(A,z){if(z.chkDisabled===true){return false;}var y=v.getSetting(A.data.treeId),x=e("#"+z.tId+o.id.CHECK);z.check_Focus=false;h.setChkClass(y,x,z);return true;}},g={},d={checkNodeRelation:function(G,A){var E,C,B,z=G.data.key.children,y=G.data.key.checked,x=o.radio;if(G.check.chkStyle==x.STYLE){var F=v.getRadioCheckedList(G);if(A[y]){if(G.check.radioType==x.TYPE_ALL){for(C=F.length-1;C>=0;C--){E=F[C];E[y]=false;F.splice(C,1);h.setChkClass(G,e("#"+E.tId+o.id.CHECK),E);if(E.parentTId!=A.parentTId){h.repairParentChkClassWithSelf(G,E);}}F.push(A);}else{var D=(A.parentTId)?A.getParentNode():v.getRoot(G);for(C=0,B=D[z].length;C<B;C++){E=D[z][C];if(E[y]&&E!=A){E[y]=false;h.setChkClass(G,e("#"+E.tId+o.id.CHECK),E);}}}}else{if(G.check.radioType==x.TYPE_ALL){for(C=0,B=F.length;C<B;C++){if(A==F[C]){F.splice(C,1);break;}}}}}else{if(A[y]&&(!A[z]||A[z].length==0||G.check.chkboxType.Y.indexOf("s")>-1)){h.setSonNodeCheckBox(G,A,true);}if(!A[y]&&(!A[z]||A[z].length==0||G.check.chkboxType.N.indexOf("s")>-1)){h.setSonNodeCheckBox(G,A,false);}if(A[y]&&G.check.chkboxType.Y.indexOf("p")>-1){h.setParentNodeCheckBox(G,A,true);}if(!A[y]&&G.check.chkboxType.N.indexOf("p")>-1){h.setParentNodeCheckBox(G,A,false);}}},makeChkClass:function(y,B){var x=y.data.key.checked,D=o.checkbox,A=o.radio,C="";if(B.chkDisabled===true){C=D.DISABLED;}else{if(B.halfCheck){C=D.PART;}else{if(y.check.chkStyle==A.STYLE){C=(B.check_Child_State<1)?D.FULL:D.PART;}else{C=B[x]?((B.check_Child_State===2||B.check_Child_State===-1)?D.FULL:D.PART):((B.check_Child_State<1)?D.FULL:D.PART);}}}var z=y.check.chkStyle+"_"+(B[x]?D.TRUE:D.FALSE)+"_"+C;z=(B.check_Focus&&B.chkDisabled!==true)?z+"_"+D.FOCUS:z;return D.DEFAULT+" "+z;},repairAllChk:function(B,E){if(B.check.enable&&B.check.chkStyle===o.checkbox.STYLE){var z=B.data.key.checked,D=B.data.key.children,y=v.getRoot(B);for(var A=0,x=y[D].length;A<x;A++){var C=y[D][A];if(C.nocheck!==true){C[z]=E;}h.setSonNodeCheckBox(B,C,E);}}},repairChkClass:function(y,z){if(!z){return;}v.makeChkFlag(y,z);var x=e("#"+z.tId+o.id.CHECK);h.setChkClass(y,x,z);},repairParentChkClass:function(y,z){if(!z||!z.parentTId){return;}var x=z.getParentNode();h.repairChkClass(y,x);h.repairParentChkClass(y,x);},repairParentChkClassWithSelf:function(x,z){if(!z){return;}var y=x.data.key.children;if(z[y]&&z[y].length>0){h.repairParentChkClass(x,z[y][0]);}else{h.repairParentChkClass(x,z);}},repairSonChkDisabled:function(B,D,A){if(!D){return;}var C=B.data.key.children;if(D.chkDisabled!=A){D.chkDisabled=A;if(D.nocheck!==true){h.repairChkClass(B,D);}}if(D[C]){for(var z=0,y=D[C].length;z<y;z++){var x=D[C][z];h.repairSonChkDisabled(B,x,A);}}},repairParentChkDisabled:function(y,z,x){if(!z){return;}if(z.chkDisabled!=x){z.chkDisabled=x;if(z.nocheck!==true){h.repairChkClass(y,z);}}h.repairParentChkDisabled(y,z.getParentNode(),x);},setChkClass:function(x,z,y){if(!z){return;}if(y.nocheck===true){z.hide();}else{z.show();}z.removeClass();z.addClass(h.makeChkClass(x,y));},setParentNodeCheckBox:function(H,A,G,D){var z=H.data.key.children,x=H.data.key.checked,E=e("#"+A.tId+o.id.CHECK);if(!D){D=A;}v.makeChkFlag(H,A);if(A.nocheck!==true&&A.chkDisabled!==true){A[x]=G;h.setChkClass(H,E,A);if(H.check.autoCheckTrigger&&A!=D&&A.nocheck!==true){H.treeObj.trigger(o.event.CHECK,[H.treeId,A]);}}if(A.parentTId){var F=true;if(!G){var y=A.getParentNode()[z];for(var C=0,B=y.length;C<B;C++){if((y[C].nocheck!==true&&y[C][x])||(y[C].nocheck===true&&y[C].check_Child_State>0)){F=false;break;}}}if(F){h.setParentNodeCheckBox(H,A.getParentNode(),G,D);}}},setSonNodeCheckBox:function(H,A,G,D){if(!A){return;}var z=H.data.key.children,x=H.data.key.checked,E=e("#"+A.tId+o.id.CHECK);if(!D){D=A;}var y=false;if(A[z]){for(var C=0,B=A[z].length;C<B&&A.chkDisabled!==true;C++){var F=A[z][C];h.setSonNodeCheckBox(H,F,G,D);if(F.chkDisabled===true){y=true;}}}if(A!=v.getRoot(H)&&A.chkDisabled!==true){if(y&&A.nocheck!==true){v.makeChkFlag(H,A);}if(A.nocheck!==true){A[x]=G;if(!y){A.check_Child_State=(A[z]&&A[z].length>0)?(G?2:0):-1;}}else{A.check_Child_State=-1;}h.setChkClass(H,E,A);if(H.check.autoCheckTrigger&&A!=D&&A.nocheck!==true){H.treeObj.trigger(o.event.CHECK,[H.treeId,A]);}}}},q={tools:g,view:d,event:f,data:n};e.extend(true,e.fn.zTree.consts,w);e.extend(true,e.fn.zTree._z,q);var b=e.fn.zTree,r=b._z.tools,o=b.consts,h=b._z.view,v=b._z.data,p=b._z.event;v.exSetting(k);v.addInitBind(l);v.addInitCache(c);v.addInitNode(s);v.addInitProxy(m);v.addInitRoot(t);v.addBeforeA(a);v.addZTreeTools(i);var u=h.createNodes;h.createNodes=function(z,A,y,x){if(u){u.apply(h,arguments);}if(!y){return;}h.repairParentChkClassWithSelf(z,x);};})(jQuery);