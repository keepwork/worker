package com.common.tag;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import com.pub.message.model.dto.MessageCategoryDTO;
import com.pub.message.model.facade.MessageCategoryFacade;
import com.sinovatech.common.exception.AppException;

public class MessageCategoryTag extends TagSupport {
	
	private static final long serialVersionUID = 1L;
	private String id;
	private String value;
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@SuppressWarnings("unchecked")
	public int doStartTag() throws JspException {
		boolean flag1 = false;
		String firLevselected = "";//一级分类默认选中
		String secLevSelected = "";//二级分类默认选中
		StringBuffer firLevOptions= new StringBuffer("<option value='' >===请选择分类===</option>");//一级分类选项
		StringBuffer secLevOptions= new StringBuffer("<option value='' >===请选择分类===</option>");//二级分类选项
		StringBuffer childOptions = new StringBuffer();
		StringBuffer html = new StringBuffer();
		
		//通过HttpServletRequest获取ServletContext
		HttpServletRequest request=(HttpServletRequest) pageContext.getRequest();
		ServletContext servletContext = request.getSession().getServletContext();
		ApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
		MessageCategoryFacade myMessageCategoryFacade = (MessageCategoryFacade)ac.getBean("myMessageCategoryFacade");
		
		try {
			List<MessageCategoryDTO> cateList = myMessageCategoryFacade.listByParent("");
//			System.out.println("========================cateList: " + cateList.size());
			for (MessageCategoryDTO category : cateList) 
			{
				if( value.length()>0){
					MessageCategoryDTO ca = myMessageCategoryFacade.get(value);
					
					if(id.equals("catCode")){//消息列表页
						if(null == ca.getParentCode() || ca.getParentCode().equals("")){
							if(category.getCode().toString().equals(ca.getCode().toString())){
								firLevselected = "selected";
							}else{
								firLevselected = "";
							}
						}else{
							if(category.getCode().toString().equals(ca.getParentCode().toString())){
								firLevselected = "selected";
							}else{
								firLevselected = "";
							}
						}
						
					}else{//文章分类列表页
						if(category.getCode().toString().equals(ca.getCode().toString())){
							firLevselected = "selected";
						}else{
							firLevselected = "";
						}
					}
					
				}else{
					firLevselected = "";
				}
				firLevOptions.append("<option value='" + category.getCode() + "' "+firLevselected+ " >" + category.getName()+ "</option>");
				
				if(id.equals("catCode"))//消息列表修改时要显示第二层
				{
					List<MessageCategoryDTO> category_secondLevel = myMessageCategoryFacade.listByParent(category.getCode());
					// 如果有级联的则将所有级织子项以JS二维数组的形式保存如:["父级编号","值","名称",],[父级编号","值","名称"]
					if (category_secondLevel != null && category_secondLevel.size() > 0) {
						flag1 = true;
						for (MessageCategoryDTO category2 : category_secondLevel) {
							if( value.length()>0){
								MessageCategoryDTO ca = myMessageCategoryFacade.get(value);
								if(category2.getCode().toString().equals(ca.getCode().toString())){
									secLevSelected = "selected";
								}else{
									secLevSelected = "";
								}
							}else{
								secLevSelected = "";
							}
							if(firLevselected.equals("selected")){
								secLevOptions.append("<option value='" + category2.getCode() + "' "+ secLevSelected +" >" + category2.getName()+ "</option>");//如果传递过来的辖区编号的前5位等于市级编号 则该市默认被选中
							}
							
							if (!"".equals(childOptions.toString())) {
								childOptions.append(",");
							}
							childOptions.append("['" + category2.getParentCode()+"',");
							childOptions.append("'" + category2.getCode()+"',");
							childOptions.append("'" + category2.getName()+"']");
						}
					}
					
				}else{//文章分类列表修改时不需要显示第二层
					
				}
	            
				
			}
			
		} catch (AppException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		

		html.append("<select id='" + this.id + "_fistLevel' onchange=\"onChange_(this,'" + this.id + "_secondLevel" + "' ,'"+this.id+"')\" >"+firLevOptions+"</select>");
		if(flag1){
			html.append("<select id='" + this.id + "_secondLevel' onchange=\"onChange_(this,'','"+this.id+"')\" >"+secLevOptions+"</select>");
		}
		html.append("<input type=\"hidden\" id=\"" +this.id+ "\" name=\"" +this.id+ "\" class=\"" +this.id+ "\" value=\"" +this.value+ "\">");
		StringBuffer js = new StringBuffer("<script language=\"javascript\">optionArray=[" + childOptions.toString() + "];</script>");
		
//		System.out.println("======================== ");
//		System.out.println(html.toString());
//		System.out.println("======================== ");
		try {
			pageContext.getOut().write(html.toString());
			pageContext.getOut().write(js.toString());
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("CodeTag标签出错！");
		}
		return super.doStartTag();

	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
