package com.pub.message.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.common.util.ServletUtil;
import com.common.util.date.DateUtil;
import com.pub.message.model.dto.MessageCategoryDTO;
import com.pub.message.model.dto.MessageDTO;
import com.pub.message.model.facade.MessageCategoryFacade;
import com.pub.message.model.facade.MessageFacade;
import com.sinovatech.common.util.Validate;
import com.sinovatech.common.web.action.ActionConstent;
import com.sinovatech.common.web.action.BaseAdmAction;
import com.sinovatech.common.web.action.CommonMapping;
import com.sinovatech.common.web.limit.ExLimitUtil;
import com.sinovatech.common.web.limit.ILimitUtil;
import com.sinovatech.common.web.limit.LimitInfo;
import com.sinovatech.hd.tools.cache.CacheFactory;
import com.sinovatech.hd.tools.cache.ICache;

/**
 * 消息管理
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 30, 2015 9:55:43 PM
 */
public class MessageAction extends BaseAdmAction
{ 
	private static ICache cache = CacheFactory.newCache();
	
	private MessageFacade myMessageFacade;
	private MessageCategoryFacade myMessageCategoryFacade;
	
	public void init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		this.myMessageFacade = (MessageFacade) this.getBeanContext().getBean("myMessageFacade");
		this.myMessageCategoryFacade = (MessageCategoryFacade) this.getBeanContext().getBean("myMessageCategoryFacade");
	}
	
	/**
     * 文章列表初始化 - 前台
     * @param request
     * @return
     */
    public ActionForward messageList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
    	String type = request.getParameter("type");
    	
    	//查出指定主分类下所有子分类
    	String rootCate = request.getParameter("rootCate");
    	List<MessageCategoryDTO> cateList = myMessageCategoryFacade.listByParent(rootCate);
    	request.setAttribute("cateList", cateList);
    	request.setAttribute("rootCate", rootCate);
    	
    	//已选中的文章分类
    	String cateCode = request.getParameter("cateCode");
    	if(null != cateCode && !cateCode.equals("")){
    		MessageCategoryDTO cate = myMessageCategoryFacade.get(cateCode);
    		request.setAttribute("cateCode", cateCode);
    		request.setAttribute("cateName", cate.getName());
    	}else{
    		request.setAttribute("cateCode", "");
    	}
    	
    	//菜单选中
    	String menu = request.getParameter("menu");
    	request.setAttribute("menu", menu);
    	
    	String pageTitle = "";
    	if(rootCate.equals("2")){
    		pageTitle = "互助公示";
    	}else if(rootCate.equals("4")){
    		pageTitle = "平台公告";
    	}else if(rootCate.equals("5")){
    		pageTitle = "风险观念";
    	}else if(rootCate.equals("6")){
    		pageTitle = "规则介绍";
    	}
    	request.setAttribute("pageTitle", pageTitle);
    	
    	if(type.equals("wap")){
    		return mapping.findForward("messageList_wap");
    	}else if(type.equals("web")){
    		return mapping.findForward("messageList_web");
    	}
		return null;
	}
    
    /**
     * 文章列表-分页模式 - 前台
     * @param request
     * @return
     */
    public ActionForward listMessageForPagination(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
    	Map<String, String> params = new HashMap<String, String>();
    	
    	//文章分类
    	String cateCode = request.getParameter("cateCode");
    	if(null != cateCode && !cateCode.equals("")){
    		params.put("cateCode", "'"+cateCode+"'");
    	}else{
        	//查出指定主分类下所有子分类
        	String rootCate = request.getParameter("rootCate");
        	List<MessageCategoryDTO> cateList = myMessageCategoryFacade.listByParent(rootCate);
        	String cates = "";
        	for (MessageCategoryDTO cate : cateList) {
        		cates += "'"+cate.getCode() + "',";
			}
        	if(cates.length()>0){
        		cates = cates.substring(0, cates.length()-1);
        	}
        	if(!cates.equals("")){
        		params.put("cateCode", cates);
        	}else{
        		params.put("cateCode", rootCate);
        	}
    	}
    	
        StringBuilder sb = new StringBuilder();
        int i = 0;
        String size = request.getParameter("pageCount") == null ? "18" : request.getParameter("pageCount").trim();
        String start = request.getParameter("page") == null ? "0" : request.getParameter("page").trim();
        int startTemp = (start == null || start.isEmpty() ? '0' : Integer.parseInt(start))
            * (size == null || size.isEmpty() ? '0' : Integer.parseInt(size));
        
        params.put("start", startTemp + "");
        params.put("size", size);
        
        List<MessageDTO> list = (List<MessageDTO>)myMessageFacade.listForPagination(params).get("list");
        int count = (Integer)myMessageFacade.listForPagination(params).get("rows");
        
        sb.append("{\"pageCount\":\"" + count + "\",\"pageData\":[");
        for (MessageDTO dto : list)
        {
            if (i > 0)
            {
                sb.append(",");
            }
            sb.append("{");
            sb.append("\"id\":\"" + dto.getId() + "\",");
            if(dto.getTitle().length()>=20){
            	sb.append("\"title\":\"" + dto.getTitle().substring(0,20) + "..\",");
            }else{
            	sb.append("\"title\":\"" + dto.getTitle() + "\",");
            }
            sb.append("\"pic\":\"" + dto.getPic() + "\",");
            sb.append("\"updateTime\":\"" + (DateUtil.format(dto.getUpdateTime(), "yyyy-MM-dd HH:ss:mm")) + "\"");
            sb.append("}");
            i++;
        }
        sb.append("]}");
        try
        {
            ServletUtil.outputXML(response, sb.toString());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
		return null;
    }
    
    /**
     * @method 文章详情页面 - 前台
     * @return 
     */
    public ActionForward messageDetail(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
    	String type = request.getParameter("type");
    	String id = request.getParameter("id");
        MessageDTO a = myMessageFacade.get(id);
    	a.setUpdateTimeStr(a.getUpdateTime() == null ? "" : (DateUtil.format(a.getUpdateTime(), "yyyy-MM-dd HH:ss:mm")));
    	request.setAttribute("message", a);
        
    	request.setAttribute("pageTitle", a.getTitle());
        if(type.equals("wap")){
        	if(a.getTitle().length()>12){
        		a.setTitle(a.getTitle().substring(0,12) + " . . .");
        	}
        	return mapping.findForward("messageDetail_wap");
    	}else if(type.equals("web")){
    		//查出指定主分类下所有子分类
        	String rootCate = request.getParameter("rootCate");
        	if(null!=rootCate && !rootCate.equals("")){
        		List<MessageCategoryDTO> cateList = myMessageCategoryFacade.listByParent(rootCate);
            	request.setAttribute("cateList", cateList);
        	}
        	//菜单选中
        	String menu = request.getParameter("menu");
        	request.setAttribute("menu", menu);
    		return mapping.findForward("messageDetail_web");
    	}
        return null;
    }
    
	
    /**
     * 公司简介
     * 
     * @param request
     * @return
     */
    public ActionForward aboutUs(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
    	String id = request.getParameter("id");
    	MessageDTO message = myMessageFacade.get(id);
    	request.setAttribute("message", message);
    	request.setAttribute("pageTitle", message.getTitle());
    	
    	String type = request.getParameter("type");//类型：wap,web
    	if(type.equals("wap")){
    		return mapping.findForward("aboutUs_wap");
    	}else if(type.equals("web")){
    		
    		//菜单选中
        	String menu = request.getParameter("menu");
        	request.setAttribute("menu", menu);
        	
        	return mapping.findForward("aboutUs_web");
    	}
    	return null;
	}
    
    
    
    
    
    /**
     * 常见问题列表页 - 前台
     * @param request
     * @return
     */
    public ActionForward questionList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
    	String type = request.getParameter("type");
    	String returnStr = "";
    	if(type.equals("wap")){
    		returnStr = "questionList_wap";
    	}else if(type.equals("web")){
    		returnStr = "questionList_web";
    		String rootCate = request.getParameter("rootCate");
        	List<MessageCategoryDTO> cateList = myMessageCategoryFacade.listByParent(rootCate);
        	request.setAttribute("cateList", cateList);
        	
        	//已选中的文章分类
        	String cateCode = request.getParameter("cateCode");
        	if(null != cateCode && !cateCode.equals("")){
        		MessageCategoryDTO cate = myMessageCategoryFacade.get(cateCode);
        		request.setAttribute("cateCode", cateCode);
        		request.setAttribute("cateName", cate.getName());
        	}else{
        		request.setAttribute("cateCode", "");
        	}
    	}
    	
    	String rootCate = request.getParameter("rootCate");
    	request.setAttribute("rootCate", rootCate);
    	request.setAttribute("pageTitle", "常见问题");
    	
    	return mapping.findForward(returnStr);
	}
    /**
     * @method 常见详情页面 - 前台
     * @return 
     */
    public ActionForward questionDetail(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
    	String type = request.getParameter("type");
    	String id = request.getParameter("id");
        MessageDTO a = myMessageFacade.get(id);
    	a.setUpdateTimeStr(a.getUpdateTime() == null ? "" : (DateUtil.format(a.getUpdateTime(), "yyyy-MM-dd HH:ss:mm")));
    	request.setAttribute("message", a);
        
    	request.setAttribute("pageTitle", a.getTitle());
        if(type.equals("wap")){
        	if(a.getTitle().length()>12){
        		a.setTitle(a.getTitle().substring(0,12) + " . . .");
        	}
        	return mapping.findForward("questionDetail_wap");
    	}else if(type.equals("web")){
    		//查出指定主分类下所有子分类
        	String rootCate = request.getParameter("rootCate");
        	if(null!=rootCate && !rootCate.equals("")){
        		List<MessageCategoryDTO> cateList = myMessageCategoryFacade.listByParent(rootCate);
            	request.setAttribute("cateList", cateList);
        	}
        	//菜单选中
//        	String menu = request.getParameter("menu");
//        	request.setAttribute("menu", menu);
    		return mapping.findForward("questionDetail_web");
    	}
        return null;
    }
    /**
     * 查询分类 
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward fetchCatesByRootId(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
    	//查出指定主分类下所有子分类
    	String rootCate = request.getParameter("rootCate");
    	List<MessageCategoryDTO> cateList = myMessageCategoryFacade.listByParent(rootCate);
    	StringBuilder sb = new StringBuilder();
    	int i = 0;
        for (MessageCategoryDTO cate : cateList)
        {
            if (i > 0)
            {
                sb.append(",");
            }
            sb.append("{");
            sb.append("\"code\":\"" + cate.getCode() + "\",");
            if(cate.getName().length()>=20){
            	sb.append("\"name\":\"" + cate.getName().substring(0,20) + "..\",");
            }else{
            	sb.append("\"name\":\"" + cate.getName() + "\"");
            }
            sb.append("}");
            i++;
        }
        
        try{
            ServletUtil.outputXML(response, sb.toString());
        }catch (IOException e){
            e.printStackTrace();
        }
		return null;
    }
    /**
     * 查询文章列表
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward fetchCatesMessages(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
    	//查出指定主分类下所有子分类
    	String cateCode = request.getParameter("cateCode");
    	List<MessageDTO> messageList = myMessageFacade.listByCateCode(cateCode);
    	StringBuilder sb = new StringBuilder();
    	int i = 0;
        for (MessageDTO a : messageList)
        {
            if (i > 0)
            {
                sb.append(",");
            }
            sb.append("{");
            sb.append("\"id\":\"" + a.getId() + "\",");
            if(a.getTitle().length()>=20){
            	sb.append("\"title\":\"" + a.getTitle().substring(0,20) + "..\",");
            }else{
            	sb.append("\"title\":\"" + a.getTitle() + "\"");
            }
            sb.append("}");
            i++;
        }
        
        try{
            ServletUtil.outputXML(response, sb.toString());
        }catch (IOException e){
            e.printStackTrace();
        }
		return null;
    }

  
	

}