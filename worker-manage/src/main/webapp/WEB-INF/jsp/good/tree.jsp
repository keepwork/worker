<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/fn.tld" prefix="fn"%>
<head>
	<title></title>
	<link rel=StyleSheet type=text/css href="<c:url value="/common/dtree/dtree.css"/>">
	<script type="text/javascript" src="<c:url value="/common/dtree/dtree.js"/>" charset="UTF-8"></script>
	<style type="text/css">
	ol li img {
	    margin: 0 0px;
	}
	</style>
</head>
<div class=dtree>
<script type=text/javascript>
	d = new dTree('d','${pageContext.request.contextPath}');
	d.add(0,-1,'${tree.name}','<c:url value="${tree.url}"/>','','${tree.target}','','','','${tree.identity}','${tree.status1}','${tree.type}');
	<c:if test="${fn:length(tree.trees)>0}">
	<c:forEach var ="tre" items="${tree.trees}" varStatus="idx">
	   d.add('${tre.id}','${tre.pid}','${tre.name}','<c:url value="${tre.url}"/>','','${tre.target}','','','','${tre.identity}','${tre.status1}','${tre.type}');
	   <c:if test="${fn:length(tre.trees)>0}">
	      <c:forEach var ="tre1" items="${tre.trees}" varStatus="idx">
	      d.add('${tre1.id}','${tre1.pid}','${tre1.name}','<c:url value="${tre1.url}"/>','','${tre1.target}','${tre1.icon}','','','${tre1.identity}','${tre1.status1}','${tre1.type}');
	          	<c:if test="${fn:length(tre1.trees)>0}">
	            <c:forEach var ="tre2" items="${tre1.trees}" varStatus="idx">
	               d.add('${tre2.id}','${tre2.pid}','${tre2.name}','<c:url value="${tre2.url}"/>','','${tre2.target}','${tre2.icon}','','','${tre2.identity}','${tre2.status1}','${tre2.type}');
	            	<c:if test="${fn:length(tre2.trees)>0}">
		            <c:forEach var ="tre3" items="${tre2.trees}" varStatus="idx">
		               d.add('${tre3.id}','${tre3.pid}','${tre3.name}','<c:url value="${tre3.url}"/>','','${tre3.target}','${tre3.icon}','','','${tre3.identity}','${tre3.status1}','${tre3.type}');
		            </c:forEach> 
		         	</c:if>
	            </c:forEach> 
	         	</c:if>
	      </c:forEach> 
	   </c:if>
	</c:forEach>
	</c:if>
	d.modifyColor();
	document.write(d);
</script>
</div>
