(function(e){var y={event:{DRAG:"ztree_drag",DROP:"ztree_drop",REMOVE:"ztree_remove",RENAME:"ztree_rename"},id:{EDIT:"_edit",INPUT:"_input",REMOVE:"_remove"},move:{TYPE_INNER:"inner",TYPE_PREV:"prev",TYPE_NEXT:"next"},node:{CURSELECTED_EDIT:"curSelectedNode_Edit",TMPTARGET_TREE:"tmpTargetzTree",TMPTARGET_NODE:"tmpTargetNode"}},k={edit:{enable:false,editNameSelectAll:false,showRemoveBtn:true,showRenameBtn:true,removeTitle:"remove",renameTitle:"rename",drag:{autoExpandTrigger:false,isCopy:true,isMove:true,prev:true,next:true,inner:true,minMoveSize:5,borderMax:10,borderMin:-5,maxShowNodeNum:5,autoOpenTime:500}},view:{addHoverDom:null,removeHoverDom:null},callback:{beforeDrag:null,beforeDragOpen:null,beforeDrop:null,beforeEditName:null,beforeRemove:null,beforeRename:null,onDrag:null,onDrop:null,onRemove:null,onRename:null}},u=function(z){var A=w.getRoot(z);A.curEditNode=null;A.curEditInput=null;A.curHoverNode=null;A.dragFlag=0;A.dragNodeShowBefore=[];A.dragMaskList=new Array();A.showHoverDom=true;},b=function(z){},l=function(z){var A=z.treeObj;var B=o.event;A.unbind(B.RENAME);A.bind(B.RENAME,function(C,E,D){s.apply(z.callback.onRename,[C,E,D]);});A.unbind(B.REMOVE);A.bind(B.REMOVE,function(C,E,D){s.apply(z.callback.onRemove,[C,E,D]);});A.unbind(B.DRAG);A.bind(B.DRAG,function(C,E,D){s.apply(z.callback.onDrag,[C,E,D]);});A.unbind(B.DROP);A.bind(B.DROP,function(D,F,E,G,C){s.apply(z.callback.onDrop,[D,F,E,G,C]);});},m=function(G){var H=G.target,K=w.getSetting(G.data.treeId),I=G.relatedTarget,E="",A=null,B="",F="",z=null,D=null,C=null;if(s.eqs(G.type,"mouseover")){C=s.getMDom(K,H,[{tagName:"a",attrName:"treeNode"+o.id.A}]);if(C){E=C.parentNode.id;B="hoverOverNode";}}else{if(s.eqs(G.type,"mouseout")){C=s.getMDom(K,I,[{tagName:"a",attrName:"treeNode"+o.id.A}]);if(!C){E="remove";B="hoverOutNode";}}else{if(s.eqs(G.type,"mousedown")){C=s.getMDom(K,H,[{tagName:"a",attrName:"treeNode"+o.id.A}]);if(C){E=C.parentNode.id;B="mousedownNode";}}}}if(E.length>0){A=w.getNodeCache(K,E);switch(B){case"mousedownNode":z=j.onMousedownNode;break;case"hoverOverNode":z=j.onHoverOverNode;break;case"hoverOutNode":z=j.onHoverOutNode;break;}}var J={stop:false,node:A,nodeEventType:B,nodeEventCallback:z,treeEventType:F,treeEventCallback:D};return J;},t=function(B,F,E,z,D,A,C){if(!E){return;}E.isHover=false;E.editNameFlag=false;},i=function(A,z){z.addNodes=function(B,C,F){if(!C){return null;}if(!B){B=null;}if(B&&!B.isParent&&A.data.keep.leaf){return null;}var E=s.clone(s.isArray(C)?C:[C]);function D(){h.addNodes(A,B,E,(F==true));}if(A.async.enable&&s.canAsync(A,B)){h.asyncNode(A,B,F,D);}else{D();}return E;};z.cancelEditName=function(C){var B=w.getRoot(A),D=A.data.key.name,E=B.curEditNode;if(!B.curEditNode){return;}h.cancelCurEditNode(A,C?C:E[D]);};z.copyNode=function(F,E,D,G){if(!E){return null;}if(F&&!F.isParent&&A.data.keep.leaf&&D===o.move.TYPE_INNER){return null;}var B=s.clone(E);if(!F){F=null;D=o.move.TYPE_INNER;}if(D==o.move.TYPE_INNER){function C(){h.addNodes(A,F,[B],G);}if(A.async.enable&&s.canAsync(A,F)){h.asyncNode(A,F,G,C);}else{C();}}else{h.addNodes(A,F.parentNode,[B],G);h.moveNode(A,F,B,D,false,G);}return B;};z.editName=function(B){if(!B||!B.tId||B!==w.getNodeCache(A,B.tId)){return;}if(B.parentTId){h.expandCollapseParentNode(A,B.getParentNode(),true);}h.editNode(A,B);};z.moveNode=function(D,C,B,F){if(!C){return C;}if(D&&!D.isParent&&A.data.keep.leaf&&B===o.move.TYPE_INNER){return null;}else{if(D&&((C.parentTId==D.tId&&B==o.move.TYPE_INNER)||e("#"+C.tId).find("#"+D.tId).length>0)){return null;}else{if(!D){D=null;}}}function E(){h.moveNode(A,D,C,B,false,F);}if(A.async.enable&&s.canAsync(A,D)){h.asyncNode(A,D,F,E);}else{E();}return C;};z.removeNode=function(C,B){if(!C){return;}B=!!B;if(B&&s.apply(A.callback.beforeRemove,[A.treeId,C],true)==false){return;}h.removeNode(A,C);if(B){this.setting.treeObj.trigger(o.event.REMOVE,[A.treeId,C]);}};z.removeChildNodes=function(D){if(!D){return null;}var C=A.data.key.children,B=D[C];h.removeChildNodes(A,D);return B?B:null;};z.setEditable=function(B){A.edit.enable=B;return this.refresh();};},n={setSonNodeLevel:function(C,z,E){if(!E){return;}var D=C.data.key.children;E.level=(z)?z.level+1:0;if(!E[D]){return;}for(var B=0,A=E[D].length;B<A;B++){if(E[D][B]){w.setSonNodeLevel(C,E,E[D][B]);}}}},f={},j={onHoverOverNode:function(C,B){var A=w.getSetting(C.data.treeId),z=w.getRoot(A);if(z.curHoverNode!=B){j.onHoverOutNode(C);}z.curHoverNode=B;h.addHoverDom(A,B);},onHoverOutNode:function(C,B){var A=w.getSetting(C.data.treeId),z=w.getRoot(A);if(z.curHoverNode&&!w.isSelectedNode(A,z.curHoverNode)){h.removeTreeDom(A,z.curHoverNode);z.curHoverNode=null;}},onMousedownNode:function(L,F){var T,Q,K=w.getSetting(L.data.treeId),P=w.getRoot(K);if(L.button==2||!K.edit.enable||(!K.edit.drag.isCopy&&!K.edit.drag.isMove)){return true;}var W=L.target,E=w.getRoot(K).curSelectedList,M=[];if(!w.isSelectedNode(K,F)){M=[F];}else{for(T=0,Q=E.length;T<Q;T++){if(E[T].editNameFlag&&s.eqs(W.tagName,"input")&&W.getAttribute("treeNode"+o.id.INPUT)!==null){return true;}M.push(E[T]);if(M[0].parentTId!==E[T].parentTId){M=[F];break;}}}h.editNodeBlur=true;h.cancelCurEditNode(K,null,true);var Z=e(document),S,G,U,V=false,X=K,z,D,N=null,C=null,J=null,A=o.move.TYPE_INNER,R=L.clientX,O=L.clientY,H=(new Date()).getTime();if(s.uCanDo(K)){Z.bind("mousemove",I);}function I(aU){if(P.dragFlag==0&&Math.abs(R-aU.clientX)<K.edit.drag.minMoveSize&&Math.abs(O-aU.clientY)<K.edit.drag.minMoveSize){return true;}var aQ,aO,aq,aK,aC,aJ=K.data.key.children;s.noSel(K);e("body").css("cursor","pointer");if(P.dragFlag==0){if(s.apply(K.callback.beforeDrag,[K.treeId,M],true)==false){Y(aU);return true;}for(aQ=0,aO=M.length;aQ<aO;aQ++){if(aQ==0){P.dragNodeShowBefore=[];}aq=M[aQ];if(aq.isParent&&aq.open){h.expandCollapseNode(K,aq,!aq.open);P.dragNodeShowBefore[aq.tId]=true;}else{P.dragNodeShowBefore[aq.tId]=false;}}P.dragFlag=1;P.showHoverDom=false;s.showIfameMask(K,true);var ab=true,ae=-1;if(M.length>1){var ao=M[0].parentTId?M[0].getParentNode()[aJ]:w.getNodes(K);aC=[];for(aQ=0,aO=ao.length;aQ<aO;aQ++){if(P.dragNodeShowBefore[ao[aQ].tId]!==undefined){if(ab&&ae>-1&&(ae+1)!==aQ){ab=false;}aC.push(ao[aQ]);ae=aQ;}if(M.length===aC.length){M=aC;break;}}}if(ab){z=M[0].getPreNode();D=M[M.length-1].getNextNode();}S=e("<ul class='zTreeDragUL'></ul>");for(aQ=0,aO=M.length;aQ<aO;aQ++){aq=M[aQ];aq.editNameFlag=false;h.selectNode(K,aq,aQ>0);h.removeTreeDom(K,aq);aK=e("<li id='"+aq.tId+"_tmp'></li>");aK.append(e("#"+aq.tId+o.id.A).clone());aK.css("padding","0");aK.children("#"+aq.tId+o.id.A).removeClass(o.node.CURSELECTED);S.append(aK);if(aQ==K.edit.drag.maxShowNodeNum-1){aK=e("<li id='"+aq.tId+"_moretmp'><a>  ...  </a></li>");S.append(aK);break;}}S.attr("id",M[0].tId+o.id.UL+"_tmp");S.addClass(K.treeObj.attr("class"));S.appendTo("body");G=e("<button class='tmpzTreeMove_arrow'></button>");G.attr("id","zTreeMove_arrow_tmp");G.appendTo("body");K.treeObj.trigger(o.event.DRAG,[K.treeId,M]);}if(P.dragFlag==1&&G.attr("id")!=aU.target.id){if(U){U.removeClass(o.node.TMPTARGET_TREE);if(J){e("#"+J+o.id.A,U).removeClass(o.node.TMPTARGET_NODE);}}U=null;J=null;V=false;X=K;var aR=w.getSettings();for(var aL in aR){if(aR[aL].treeId&&aR[aL].edit.enable&&aR[aL].treeId!=K.treeId&&(aU.target.id==aR[aL].treeId||e(aU.target).parents("#"+aR[aL].treeId).length>0)){V=true;X=aR[aL];}}var an=Z.scrollTop(),aT=Z.scrollLeft(),ac=X.treeObj.offset(),aw=X.treeObj.get(0).scrollHeight,aM=X.treeObj.get(0).scrollWidth,aS=(aU.clientY+an-ac.top),aI=(X.treeObj.height()+ac.top-aU.clientY-an),aD=(aU.clientX+aT-ac.left),am=(X.treeObj.width()+ac.left-aU.clientX-aT),ap=(aS<K.edit.drag.borderMax&&aS>K.edit.drag.borderMin),aV=(aI<K.edit.drag.borderMax&&aI>K.edit.drag.borderMin),aG=(aD<K.edit.drag.borderMax&&aD>K.edit.drag.borderMin),ak=(am<K.edit.drag.borderMax&&am>K.edit.drag.borderMin),ad=aS>K.edit.drag.borderMin&&aI>K.edit.drag.borderMin&&aD>K.edit.drag.borderMin&&am>K.edit.drag.borderMin,aA=(ap&&X.treeObj.scrollTop()<=0),az=(aV&&(X.treeObj.scrollTop()+X.treeObj.height()+10)>=aw),ag=(aG&&X.treeObj.scrollLeft()<=0),au=(ak&&(X.treeObj.scrollLeft()+X.treeObj.width()+10)>=aM);if(aU.target.id&&X.treeObj.find("#"+aU.target.id).length>0){var al=aU.target;while(al&&al.tagName&&!s.eqs(al.tagName,"li")&&al.id!=X.treeId){al=al.parentNode;}var at=true;for(aQ=0,aO=M.length;aQ<aO;aQ++){aq=M[aQ];if(al.id===aq.tId){at=false;break;}else{if(e("#"+aq.tId).find("#"+al.id).length>0){at=false;break;}}}if(at){if(aU.target.id&&(aU.target.id==(al.id+o.id.A)||e(aU.target).parents("#"+al.id+o.id.A).length>0)){U=e(al);J=al.id;}}}aq=M[0];if(ad&&(aU.target.id==X.treeId||e(aU.target).parents("#"+X.treeId).length>0)){if(!U&&(aU.target.id==X.treeId||aA||az||ag||au)&&(V||(!V&&aq.parentTId))){U=X.treeObj;}if(ap){X.treeObj.scrollTop(X.treeObj.scrollTop()-10);}else{if(aV){X.treeObj.scrollTop(X.treeObj.scrollTop()+10);}}if(aG){X.treeObj.scrollLeft(X.treeObj.scrollLeft()-10);}else{if(ak){X.treeObj.scrollLeft(X.treeObj.scrollLeft()+10);}}if(U&&U!=X.treeObj&&U.offset().left<X.treeObj.offset().left){X.treeObj.scrollLeft(X.treeObj.scrollLeft()+U.offset().left-X.treeObj.offset().left);}}S.css({"top":(aU.clientY+an+3)+"px","left":(aU.clientX+aT+3)+"px"});var ay=0;var ax=0;if(U&&U.attr("id")!=X.treeId){var aH=J==null?null:w.getNodeCache(X,J),aB=(aU.ctrlKey&&K.edit.drag.isMove&&K.edit.drag.isCopy)||(!K.edit.drag.isMove&&K.edit.drag.isCopy),ai=!!(z&&J===z.tId),aF=!!(D&&J===D.tId),aP=(aq.parentTId&&aq.parentTId==J),aE=(aB||!aF)&&s.apply(X.edit.drag.prev,[X.treeId,M,aH],!!X.edit.drag.prev),ah=(aB||!ai)&&s.apply(X.edit.drag.next,[X.treeId,M,aH],!!X.edit.drag.next),aa=(aB||!aP)&&!(X.data.keep.leaf&&!aH.isParent)&&s.apply(X.edit.drag.inner,[X.treeId,M,aH],!!X.edit.drag.inner);if(!aE&&!ah&&!aa){U=null;J="";A=o.move.TYPE_INNER;G.css({"display":"none"});if(window.zTreeMoveTimer){clearTimeout(window.zTreeMoveTimer);window.zTreeMoveTargetNodeTId=null;}}else{var av=e("#"+J+o.id.A,U);av.addClass(o.node.TMPTARGET_NODE);var aN=aE?(aa?0.25:(ah?0.5:1)):-1,aj=ah?(aa?0.75:(aE?0.5:0)):-1,af=(aU.clientY+an-av.offset().top)/av.height();if((aN==1||af<=aN&&af>=-0.2)&&aE){ay=1-G.width();ax=0-G.height()/2;A=o.move.TYPE_PREV;}else{if((aj==0||af>=aj&&af<=1.2)&&ah){ay=1-G.width();ax=av.height()-G.height()/2;A=o.move.TYPE_NEXT;}else{ay=5-G.width();ax=0;A=o.move.TYPE_INNER;}}G.css({"display":"block","top":(av.offset().top+ax)+"px","left":(av.offset().left+ay)+"px"});if(N!=J||C!=A){H=(new Date()).getTime();}if(aH&&aH.isParent&&A==o.move.TYPE_INNER){var ar=true;if(window.zTreeMoveTimer&&window.zTreeMoveTargetNodeTId!==aH.tId){clearTimeout(window.zTreeMoveTimer);window.zTreeMoveTargetNodeTId=null;}else{if(window.zTreeMoveTimer&&window.zTreeMoveTargetNodeTId===aH.tId){ar=false;}}if(ar){window.zTreeMoveTimer=setTimeout(function(){if(A!=o.move.TYPE_INNER){return;}if(aH&&aH.isParent&&!aH.open&&(new Date()).getTime()-H>X.edit.drag.autoOpenTime&&s.apply(X.callback.beforeDragOpen,[X.treeId,aH],true)){h.switchNode(X,aH);if(X.edit.drag.autoExpandTrigger){X.treeObj.trigger(o.event.EXPAND,[X.treeId,aH]);}}},X.edit.drag.autoOpenTime+50);window.zTreeMoveTargetNodeTId=aH.tId;}}}}else{A=o.move.TYPE_INNER;if(U&&s.apply(X.edit.drag.inner,[X.treeId,M,null],!!X.edit.drag.inner)){U.addClass(o.node.TMPTARGET_TREE);}else{U=null;}G.css({"display":"none"});if(window.zTreeMoveTimer){clearTimeout(window.zTreeMoveTimer);window.zTreeMoveTargetNodeTId=null;}}N=J;C=A;}return false;}Z.bind("mouseup",Y);function Y(aa){if(window.zTreeMoveTimer){clearTimeout(window.zTreeMoveTimer);window.zTreeMoveTargetNodeTId=null;}N=null;C=null;Z.unbind("mousemove",I);Z.unbind("mouseup",Y);Z.unbind("selectstart",B);e("body").css("cursor","auto");if(U){U.removeClass(o.node.TMPTARGET_TREE);if(J){e("#"+J+o.id.A,U).removeClass(o.node.TMPTARGET_NODE);}}s.showIfameMask(K,false);P.showHoverDom=true;if(P.dragFlag==0){return;}P.dragFlag=0;var af,ad,ac,ab=K.data.key.children;for(af=0,ad=M.length;af<ad;af++){ac=M[af];if(ac.isParent&&P.dragNodeShowBefore[ac.tId]&&!ac.open){h.expandCollapseNode(K,ac,!ac.open);delete P.dragNodeShowBefore[ac.tId];}}if(S){S.remove();}if(G){G.remove();}var ah=(aa.ctrlKey&&K.edit.drag.isMove&&K.edit.drag.isCopy)||(!K.edit.drag.isMove&&K.edit.drag.isCopy);if(!ah&&U&&J&&M[0].parentTId&&J==M[0].parentTId&&A==o.move.TYPE_INNER){U=null;}if(U){var ai=J==null?null:w.getNodeCache(X,J);if(s.apply(K.callback.beforeDrop,[X.treeId,M,ai,A],true)==false){return;}var ae=ah?s.clone(M):M;function ag(){if(V){if(!ah){for(var ak=0,aj=M.length;ak<aj;ak++){h.removeNode(K,M[ak]);}}if(A==o.move.TYPE_INNER){h.addNodes(X,ai,ae);}else{h.addNodes(X,ai.getParentNode(),ae);if(A==o.move.TYPE_PREV){for(ak=0,aj=ae.length;ak<aj;ak++){h.moveNode(X,ai,ae[ak],A,false);}}else{for(ak=-1,aj=ae.length-1;ak<aj;aj--){h.moveNode(X,ai,ae[aj],A,false);}}}}else{if(ah&&A==o.move.TYPE_INNER){h.addNodes(X,ai,ae);}else{if(ah){h.addNodes(X,ai.getParentNode(),ae);}if(A==o.move.TYPE_PREV){for(ak=0,aj=ae.length;ak<aj;ak++){h.moveNode(X,ai,ae[ak],A,false);}}else{for(ak=-1,aj=ae.length-1;ak<aj;aj--){h.moveNode(X,ai,ae[aj],A,false);}}}}for(ak=0,aj=ae.length;ak<aj;ak++){h.selectNode(X,ae[ak],ak>0);}e("#"+ae[0].tId+o.id.ICON).focus().blur();}if(A==o.move.TYPE_INNER&&s.canAsync(X,ai)){h.asyncNode(X,ai,false,ag);}else{ag();}K.treeObj.trigger(o.event.DROP,[X.treeId,ae,ai,A]);}else{for(af=0,ad=M.length;af<ad;af++){h.selectNode(X,M[af],af>0);}K.treeObj.trigger(o.event.DROP,[K.treeId,null,null,null]);}}Z.bind("selectstart",B);function B(){return false;}if(L.preventDefault){L.preventDefault();}return true;}},g={getAbs:function(z){var A=z.getBoundingClientRect();return[A.left,A.top];},inputFocus:function(z){if(z.get(0)){z.focus();s.setCursorPosition(z.get(0),z.val().length);}},inputSelect:function(z){if(z.get(0)){z.focus();z.select();}},setCursorPosition:function(A,B){if(A.setSelectionRange){A.focus();A.setSelectionRange(B,B);}else{if(A.createTextRange){var z=A.createTextRange();z.collapse(true);z.moveEnd("character",B);z.moveStart("character",B);z.select();}}},showIfameMask:function(G,E){var D=w.getRoot(G);while(D.dragMaskList.length>0){D.dragMaskList[0].remove();D.dragMaskList.shift();}if(E){var H=e("iframe");for(var C=0,A=H.length;C<A;C++){var B=H.get(C),z=s.getAbs(B),F=e("<div id='zTreeMask_"+C+"' class='zTreeMask' style='background-color:yellow;opacity: 0.3;filter: alpha(opacity=30);    top:"+z[1]+"px; left:"+z[0]+"px; width:"+B.offsetWidth+"px; height:"+B.offsetHeight+"px;'></div>");F.appendTo("body");D.dragMaskList.push(F);}}}},c={addEditBtn:function(A,B){if(B.editNameFlag||e("#"+B.tId+o.id.EDIT).length>0){return;}if(!s.apply(A.edit.showRenameBtn,[A.treeId,B],A.edit.showRenameBtn)){return;}var C=e("#"+B.tId+o.id.A),z="<button type='button' class='edit' id='"+B.tId+o.id.EDIT+"' title='"+s.apply(A.edit.renameTitle,[A.treeId,B],A.edit.renameTitle)+"' treeNode"+o.id.EDIT+" onfocus='this.blur();' style='display:none;'></button>";C.append(z);e("#"+B.tId+o.id.EDIT).bind("click",function(){if(!s.uCanDo(A)||s.apply(A.callback.beforeEditName,[A.treeId,B],true)==false){return true;}h.editNode(A,B);return false;}).show();},addRemoveBtn:function(z,A){if(A.editNameFlag||e("#"+A.tId+o.id.REMOVE).length>0){return;}if(!s.apply(z.edit.showRemoveBtn,[z.treeId,A],z.edit.showRemoveBtn)){return;}var C=e("#"+A.tId+o.id.A),B="<button type='button' class='remove' id='"+A.tId+o.id.REMOVE+"' title='"+s.apply(z.edit.removeTitle,[z.treeId,A],z.edit.removeTitle)+"' treeNode"+o.id.REMOVE+" onfocus='this.blur();' style='display:none;'></button>";C.append(B);e("#"+A.tId+o.id.REMOVE).bind("click",function(){if(!s.uCanDo(z)||s.apply(z.callback.beforeRemove,[z.treeId,A],true)==false){return true;}h.removeNode(z,A);z.treeObj.trigger(o.event.REMOVE,[z.treeId,A]);return false;}).bind("mousedown",function(D){return true;}).show();},addHoverDom:function(z,A){if(w.getRoot(z).showHoverDom){A.isHover=true;if(z.edit.enable){h.addEditBtn(z,A);h.addRemoveBtn(z,A);}s.apply(z.view.addHoverDom,[z.treeId,A]);}},cancelCurEditNode:function(G,H,F){var E=w.getRoot(G),C=G.data.key.name,A=E.curEditNode;if(A){var B=E.curEditInput;var D=H?H:B.val();if(!H&&s.apply(G.callback.beforeRename,[G.treeId,A,D],true)===false){A.editNameFlag=true;return false;}else{A[C]=D?D:B.val();if(!H){G.treeObj.trigger(o.event.RENAME,[G.treeId,A]);}}var z=e("#"+A.tId+o.id.A);z.removeClass(o.node.CURSELECTED_EDIT);B.unbind();h.setNodeName(G,A);A.editNameFlag=false;E.curEditNode=null;E.curEditInput=null;h.selectNode(G,A,false);}E.noSelection=true;return true;},editNode:function(C,D){var z=w.getRoot(C);h.editNodeBlur=false;if(w.isSelectedNode(C,D)&&z.curEditNode==D&&D.editNameFlag){setTimeout(function(){s.inputFocus(z.curEditInput);},0);return;}var B=C.data.key.name;D.editNameFlag=true;h.removeTreeDom(C,D);h.cancelCurEditNode(C);h.selectNode(C,D,false);e("#"+D.tId+o.id.SPAN).html("<input type=text class='rename' id='"+D.tId+o.id.INPUT+"' treeNode"+o.id.INPUT+" >");var A=e("#"+D.tId+o.id.INPUT);A.attr("value",D[B]);if(C.edit.editNameSelectAll){s.inputSelect(A);}else{s.inputFocus(A);}A.bind("blur",function(E){if(!h.editNodeBlur){h.cancelCurEditNode(C);}}).bind("keydown",function(E){if(E.keyCode=="13"){h.editNodeBlur=true;h.cancelCurEditNode(C,null,true);}else{if(E.keyCode=="27"){h.cancelCurEditNode(C,D[B]);}}}).bind("click",function(E){return false;}).bind("dblclick",function(E){return false;});e("#"+D.tId+o.id.A).addClass(o.node.CURSELECTED_EDIT);z.curEditInput=A;z.noSelection=false;z.curEditNode=D;},moveNode:function(J,C,M,B,W,D){var O=w.getRoot(J),H=J.data.key.children;if(C==M){return;}if(J.data.keep.leaf&&C&&!C.isParent&&B==o.move.TYPE_INNER){return;}var R=(M.parentTId?M.getParentNode():O),L=(C===null||C==O);if(L&&C===null){C=O;}if(L){B=o.move.TYPE_INNER;}var z=(C.parentTId?C.getParentNode():O);if(B!=o.move.TYPE_PREV&&B!=o.move.TYPE_NEXT){B=o.move.TYPE_INNER;}var E,F;if(L){E=J.treeObj;F=E;}else{if(!D){if(B==o.move.TYPE_INNER){h.expandCollapseNode(J,C,true,false);}else{h.expandCollapseNode(J,C.getParentNode(),true,false);}E=e("#"+C.tId);F=e("#"+C.tId+o.id.UL);}}var T=e("#"+M.tId).remove();if(F&&B==o.move.TYPE_INNER){F.append(T);}else{if(E&&B==o.move.TYPE_PREV){E.before(T);}else{if(E&&B==o.move.TYPE_NEXT){E.after(T);}}}var Q,P,G=-1,S=0,V=null,A=null,U=M.level;if(M.isFirstNode){G=0;if(R[H].length>1){V=R[H][1];V.isFirstNode=true;}}else{if(M.isLastNode){G=R[H].length-1;V=R[H][G-1];V.isLastNode=true;}else{for(Q=0,P=R[H].length;Q<P;Q++){if(R[H][Q].tId==M.tId){G=Q;break;}}}}if(G>=0){R[H].splice(G,1);}if(B!=o.move.TYPE_INNER){for(Q=0,P=z[H].length;Q<P;Q++){if(z[H][Q].tId==C.tId){S=Q;}}}if(B==o.move.TYPE_INNER){if(L){M.parentTId=null;}else{C.isParent=true;C.open=false;M.parentTId=C.tId;}if(!C[H]){C[H]=new Array();}if(C[H].length>0){A=C[H][C[H].length-1];A.isLastNode=false;}C[H].splice(C[H].length,0,M);M.isLastNode=true;M.isFirstNode=(C[H].length==1);}else{if(C.isFirstNode&&B==o.move.TYPE_PREV){z[H].splice(S,0,M);A=C;A.isFirstNode=false;M.parentTId=C.parentTId;M.isFirstNode=true;M.isLastNode=false;}else{if(C.isLastNode&&B==o.move.TYPE_NEXT){z[H].splice(S+1,0,M);A=C;A.isLastNode=false;M.parentTId=C.parentTId;M.isFirstNode=false;M.isLastNode=true;}else{if(B==o.move.TYPE_PREV){z[H].splice(S,0,M);}else{z[H].splice(S+1,0,M);}M.parentTId=C.parentTId;M.isFirstNode=false;M.isLastNode=false;}}}w.fixPIdKeyValue(J,M);w.setSonNodeLevel(J,M.getParentNode(),M);h.setNodeLineIcos(J,M);h.repairNodeLevelClass(J,M,U);if(!J.data.keep.parent&&R[H].length<1){R.isParent=false;R.open=false;var K=e("#"+R.tId+o.id.UL),N=e("#"+R.tId+o.id.SWITCH),I=e("#"+R.tId+o.id.ICON);h.replaceSwitchClass(R,N,o.folder.DOCU);h.replaceIcoClass(R,I,o.folder.DOCU);K.css("display","none");}else{if(V){h.setNodeLineIcos(J,V);}}if(A){h.setNodeLineIcos(J,A);}if(J.check.enable&&h.repairChkClass){h.repairChkClass(J,R);h.repairParentChkClassWithSelf(J,R);if(R!=M.parent){h.repairParentChkClassWithSelf(J,M);}}if(!D){h.expandCollapseParentNode(J,M.getParentNode(),true,W);}},removeChildNodes:function(E,G){if(!G){return;}var F=E.data.key.children,C=G[F];if(!C){return;}e("#"+G.tId+o.id.UL).remove();for(var D=0,z=C.length;D<z;D++){w.removeNodeCache(E,C[D]);}w.removeSelectedNode(E);delete G[F];if(!E.data.keep.parent){G.isParent=false;G.open=false;var B=e("#"+G.tId+o.id.SWITCH),A=e("#"+G.tId+o.id.ICON);h.replaceSwitchClass(G,B,o.folder.DOCU);h.replaceIcoClass(G,A,o.folder.DOCU);}},removeEditBtn:function(z){e("#"+z.tId+o.id.EDIT).unbind().remove();},removeNode:function(K,A){var I=w.getRoot(K),z=K.data.key.children,G=(A.parentTId)?A.getParentNode():I;if(I.curEditNode===A){I.curEditNode=null;}A.isFirstNode=false;A.isLastNode=false;A.getPreNode=function(){return null;};A.getNextNode=function(){return null;};e("#"+A.tId).remove();w.removeNodeCache(K,A);w.removeSelectedNode(K,A);for(var F=0,B=G[z].length;F<B;F++){if(G[z][F].tId==A.tId){G[z].splice(F,1);break;}}var H,C,E;if(!K.data.keep.parent&&G[z].length<1){G.isParent=false;G.open=false;H=e("#"+G.tId+o.id.UL);C=e("#"+G.tId+o.id.SWITCH);E=e("#"+G.tId+o.id.ICON);h.replaceSwitchClass(G,C,o.folder.DOCU);h.replaceIcoClass(G,E,o.folder.DOCU);H.css("display","none");}else{if(K.view.showLine&&G[z].length>0){var D=G[z][G[z].length-1];D.isLastNode=true;D.isFirstNode=(G[z].length==1);H=e("#"+D.tId+o.id.UL);C=e("#"+D.tId+o.id.SWITCH);E=e("#"+D.tId+o.id.ICON);if(G==I){if(G[z].length==1){h.replaceSwitchClass(D,C,o.line.ROOT);}else{var J=e("#"+G[z][0].tId+o.id.SWITCH);h.replaceSwitchClass(G[z][0],J,o.line.ROOTS);h.replaceSwitchClass(D,C,o.line.BOTTOM);}}else{h.replaceSwitchClass(D,C,o.line.BOTTOM);}H.removeClass(o.line.LINE);}}},removeRemoveBtn:function(z){e("#"+z.tId+o.id.REMOVE).unbind().remove();},removeTreeDom:function(z,A){A.isHover=false;h.removeEditBtn(A);h.removeRemoveBtn(A);s.apply(z.view.removeHoverDom,[z.treeId,A]);},repairNodeLevelClass:function(A,C,B){if(B===C.level){return;}var D=e("#"+C.tId),G=e("#"+C.tId+o.id.A),F=e("#"+C.tId+o.id.UL),z="level"+B,E="level"+C.level;D.removeClass(z);D.addClass(E);G.removeClass(z);G.addClass(E);F.removeClass(z);F.addClass(E);}},q={tools:g,view:c,event:p,data:n};e.extend(true,e.fn.zTree.consts,y);e.extend(true,e.fn.zTree._z,q);var a=e.fn.zTree,s=a._z.tools,o=a.consts,h=a._z.view,w=a._z.data,p=a._z.event;w.exSetting(k);w.addInitBind(l);w.addInitCache(b);w.addInitNode(t);w.addInitProxy(m);w.addInitRoot(u);w.addZTreeTools(i);var r=h.cancelPreSelectedNode;h.cancelPreSelectedNode=function(B,C){var D=w.getRoot(B).curSelectedList;for(var A=0,z=D.length;A<z;A++){if(!C||C===D[A]){h.removeTreeDom(B,D[A]);if(C){break;}}}if(r){r.apply(h,arguments);}};var v=h.createNodes;h.createNodes=function(B,C,A,z){if(v){v.apply(h,arguments);}if(!A){return;}if(h.repairParentChkClassWithSelf){h.repairParentChkClassWithSelf(B,z);}};h.makeNodeUrl=function(z,A){return(A.url&&!z.edit.enable)?A.url:null;};var x=h.selectNode;h.selectNode=function(B,C,A){var z=w.getRoot(B);if(w.isSelectedNode(B,C)&&z.curEditNode==C&&C.editNameFlag){return false;}if(x){x.apply(h,arguments);}h.addHoverDom(B,C);return true;};var d=s.uCanDo;s.uCanDo=function(A,B){var z=w.getRoot(A);if(B&&(s.eqs(B.type,"mouseover")||s.eqs(B.type,"mouseout")||s.eqs(B.type,"mousedown")||s.eqs(B.type,"mouseup"))){return true;}return(!z.curEditNode)&&(d?d.apply(h,arguments):true);};})(jQuery);