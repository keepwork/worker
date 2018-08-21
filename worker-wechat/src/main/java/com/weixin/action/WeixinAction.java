package com.weixin.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.pub.menber.model.facade.MenberFacade;
//import com.pub.menber.model.facade.MenberFamilyFacade;
import com.sinovatech.common.web.action.BaseAdmAction;

/**
 * 微信网页控制类
 * 
 * @author kevin(keepwork512@163.com)
 * @date Jan 5, 2016 10:52:59 PM
 */
public class WeixinAction extends BaseAdmAction
{
	private static Logger log = Logger.getLogger(WeixinAction.class);
	
    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");

    private MenberFacade myMenberFacade;
//    private MenberFamilyFacade myMenberFamilyFacade;
    
    public void init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
    	this.myMenberFacade = (MenberFacade) this.getBeanContext().getBean("myMenberFacade");
//    	this.myMenberFamilyFacade = (MenberFamilyFacade) this.getBeanContext().getBean("myMenberFamilyFacade");
	}
    
    
    /**
     * 微信首页初始化
     * 
     * @param request
     * @return
     */
    public ActionForward index(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
//    	MenberDTO menber = (MenberDTO)request.getSession().getAttribute("wxmenber");
//    	List familyList = myMenberFamilyFacade.listByMenId(menber.getId());
//    	request.setAttribute("familySize", familyList.size());
    	
    	request.setAttribute("pageTitle", "index");
    	return mapping.findForward("index");
	}
   
    
    /**
     * 清除session 测试用
     * 
     * @param request
     * @return
     * @throws IOException 
     */
    public ActionForward removeSession(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
    	request.getSession().invalidate();//测试用，上线上要删除
    	String openID = (String)request.getSession().getAttribute("openID");
    	log.info("========================= session invalidate success ");
    	log.info(openID);
    	log.info("========================= session invalidate success ");
    	
    	response.setContentType("text/html; charset=UTF-8"); //转码
    	PrintWriter writer = response.getWriter();
    	writer.flush();
    	writer.println("<script>");
    	writer.println("alert('session删除成功,openId:"+openID+"');");
    	writer.println("</script>");
    	
    	return mapping.findForward("indexWap");
	}



}
