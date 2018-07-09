<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="org.apache.commons.fileupload.FileItemFactory"%>
<%@page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@page import="org.apache.commons.fileupload.FileItem"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.io.File"%>
<%@page import="java.io.PrintWriter" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	  <title>商品添加/修改</title>
	</head>
	<body>
		<%
		response.setContentType("text/html; charset=UTF-8"); //转码
		PrintWriter writer = response.getWriter();
		writer.flush();
		writer.println("<script>");
		writer.println("alert('基本信息保存成功!');");
		writer.println("</script>");
		
			 System.out.println("===========================================================上传文件开始");
			 String uploadSign = request.getParameter("upload");  
			 String rootPath = application.getRealPath("/ProductIMG");  
			 System.out.println("uploadSign:"+uploadSign);
			 System.out.println("rootPath:"+rootPath);
			  
			 //上传操作  
			 if(null != uploadSign && !"".equals(uploadSign)){  
			  FileItemFactory factory = new DiskFileItemFactory();  
			  ServletFileUpload upload = new ServletFileUpload(factory);  
			  //upload.setHeaderEncoding("UTF-8");  
			  try{  
			      List items = upload.parseRequest(request);  
			      if(null != items){  
			          Iterator itr = items.iterator();  
			          while(itr.hasNext()){  
			              FileItem item = (FileItem)itr.next();  
			              if(item.isFormField()){  
			                  continue;  
			              }else{  
			                                        //以当前精确到秒的日期为上传的文件的文件名  
			                  SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");  
			                  String type = item.getName().split("\\.")[1];//获取文件类型  
			                  File savedFile = new File(rootPath,sdf.format(new Date())+"."+type);  
			                  item.write(savedFile);  
			              }  
			          }  
			      }  
			  }catch(Exception e){  
			      e.printStackTrace();  
			  }  
			 }  
			 System.out.println("===========================================================上传文件结束");
		%>
	</body>
</html>