<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.sinovatech.bms.adm.model.dto.TBmsUserDTO"%>
<% 
		TBmsUserDTO tBmsUserDTO = (TBmsUserDTO)request.getSession().getAttribute("loginUser");
		String username="";
		if(tBmsUserDTO!=null)
		{
			username=tBmsUserDTO.getUserLoginName();
		}
%>
<script type="text/javascript">
	function exitUser(id){
		if(confirm("确定要退出吗？")){
			location.href="${pageContext.request.contextPath}/sys/main/loadapp.do?id="+id;
		}else{
			return false;
		}
	}
</script>
<div class="l_left">
	<div class="l_left_top01"><b><%=username %></b></div>
	<div class="l_left_top02"><b>后台管理系统</b></div>
	<div class="l_left_cont">
		<div id="tree_area">
			<ul class="simpleTree">
				${tree }
				<!-- 
				<li  class='root'><img src='${pageContext.request.contextPath}/images/app_index.gif'/><span><a href='/bms1.7.3/bms/adm/todo.do'>首页</a></span>
				<ul>
				<li>
					<img src='${pageContext.request.contextPath}/images/1j4.gif'/><span><a href=''>商城商品管理</a></span>
					<ul>
						<li>
							<img src='${pageContext.request.contextPath}/images/1j4.gif'/>
							<span><a href='${pageContext.request.contextPath}/sys/bmsrscfunc/main.do'>功能模块</a></span>
						</li>
					</ul>
				</li>
				</ul>
				</li>
				 -->

			</ul>
		</div>
	</div>
</div>
