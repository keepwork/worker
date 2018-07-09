<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page import="java.io.PrintWriter" %>
<%
PrintWriter writer = response.getWriter();
writer.flush();
writer.println("success");
%>                