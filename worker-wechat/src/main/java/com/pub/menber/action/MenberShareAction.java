package com.pub.menber.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.common.util.ServletUtil;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.pub.menber.model.dto.MenberDTO;
import com.pub.menber.model.dto.MenberShareDTO;
import com.pub.menber.model.facade.MenberFacade;
import com.pub.menber.model.facade.MenberShareFacade;
import com.sinovatech.common.web.action.BaseAdmAction;
import com.sinovatech.common.web.limit.ExLimitUtil;
import com.sinovatech.common.web.limit.HqlProperty;
import com.sinovatech.common.web.limit.ILimitUtil;
import com.sinovatech.common.web.limit.LimitInfo;

/**
 * 分享记录管理
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 30, 2015 9:55:43 PM
 */
public class MenberShareAction extends BaseAdmAction
{ 
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private MenberShareFacade myMenberShareFacade;
	private MenberFacade myMenberFacade;
	
	public void init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		this.myMenberShareFacade = (MenberShareFacade) this.getBeanContext().getBean("myMenberShareFacade");
		this.myMenberFacade = (MenberFacade) this.getBeanContext().getBean("myMenberFacade");
	}

	
	/**
     * 我的分享列表初始化
     * @param request
     * @return
     */
    public ActionForward myShareListInit(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
    	String type = request.getParameter("type");//类型：wap,web
		String returnPage = "";
		MenberDTO menber = null;
		if(type.equals("wap")){
			menber = (MenberDTO)request.getSession().getAttribute("wxmenber");
			returnPage = "shareList_wap";
			request.setAttribute("pageTitle", "我的好友列表");
    	}else if(type.equals("web")){
    		menber = (MenberDTO)request.getSession().getAttribute("pcmenber");
    		returnPage = "shareList_web";
    		String leftMenu = request.getParameter("leftMenu");
    		request.setAttribute("leftMenu", leftMenu);
    	}
        if(null != menber)
        {
    		return mapping.findForward(returnPage);
        }
		return null;
	}
    /**
     * @method 我的分享列表-分页模式
     */
    public ActionForward listMyShareMenbersForPagination(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
    	StringBuilder sb = new StringBuilder();
        Map<String, String> params = new HashMap<String, String>();
        
        String type = request.getParameter("type");//类型：wap,web
        MenberDTO menber = null;
        if(type.equals("wap")){
			menber = (MenberDTO)request.getSession().getAttribute("wxmenber");
    	}else if(type.equals("web")){
    		menber = (MenberDTO)request.getSession().getAttribute("pcmenber");
    	}
        if(null != menber)
        {
        	params.put("menId", menber.getId().toString());
        	
            int i = 0;
            String size = request.getParameter("pageCount") == null ? "18" : request.getParameter("pageCount").trim();
            String start = request.getParameter("page") == null ? "0" : request.getParameter("page").trim();
            int startTemp = (start == null || start.isEmpty() ? '0' : Integer.parseInt(start))
                * (size == null || size.isEmpty() ? '0' : Integer.parseInt(size));
            params.put("start", startTemp + "");
            params.put("size", size);
            
            List<MenberShareDTO> list = (List<MenberShareDTO>)myMenberShareFacade.listForPagination(params).get("list");
            int count = (Integer)myMenberShareFacade.listForPagination(params).get("rows"); 
            
            sb.append("{\"pageCount\":\"" + count + "\",\"pageData\":[");
            for (MenberShareDTO share : list)
            {
                if (i > 0)
                {
                    sb.append(",");
                }
                MenberDTO menber2 = myMenberFacade.get(share.getShareMenId());
                String realName = "";
                if(null!=menber2.getRealName()){
                	realName = menber2.getRealName();
                }
                String img = "";
                if(null!=menber2.getHeadimgurl()){
                	img = menber2.getHeadimgurl();
                }
                sb.append("{");
                sb.append("\"headimgurl\":\"" + img + "\",");
                sb.append("\"balanceFee\":\"" + menber2.getBalanceFee() + "\",");
                sb.append("\"realName\":\"" + realName + "\"");
                sb.append("}");
                i++;
            }
            sb.append("]}");
        }
        
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
	 * 查看我的分享 - 前台会员中心
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward shareList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		String type = request.getParameter("type");//类型：wap,web
		MenberDTO wxmenber = (MenberDTO)request.getSession().getAttribute("wxmenber");
        if(null != wxmenber)
        {
        	List shareList = myMenberShareFacade.listByMenId(wxmenber.getId());
        	request.setAttribute("shareList", shareList);
        	
        	if(type.equals("wap")){
        		return mapping.findForward("shareList_wap");
        	}else if(type.equals("web")){
        		return mapping.findForward("shareList_web");
        	}
        }
		return null;
	}


}