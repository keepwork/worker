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
import com.pub.menber.model.dto.MenberPointDTO;
import com.pub.menber.model.facade.MenberFacade;
import com.pub.menber.model.facade.MenberPointFacade;
import com.sinovatech.common.util.Validate;
import com.sinovatech.common.web.action.ActionConstent;
import com.sinovatech.common.web.action.BaseAdmAction;
import com.sinovatech.common.web.action.CommonMapping;
import com.sinovatech.common.web.limit.ExLimitUtil;
import com.sinovatech.common.web.limit.HqlProperty;
import com.sinovatech.common.web.limit.ILimitUtil;
import com.sinovatech.common.web.limit.LimitInfo;

/**
 * 会员积分管理
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 30, 2015 9:55:43 PM
 */
public class MenberPointAction extends BaseAdmAction
{ 
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
	private MenberPointFacade myMenberPointFacade;
	private MenberFacade myMenberFacade;
	
	public void init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		this.myMenberPointFacade = (MenberPointFacade) this.getBeanContext().getBean("myMenberPointFacade");
		this.myMenberFacade = (MenberFacade) this.getBeanContext().getBean("myMenberFacade");
	}
	
	/**
	 * 查看我的积分 - 前台会员中心
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward pointList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		String type = request.getParameter("type");//类型：wap,web
		String returnPage = "";
		MenberDTO menber = null;
		if(type.equals("wap")){
			menber = (MenberDTO)request.getSession().getAttribute("wxmenber");
			returnPage = "pointList_wap";
    	}else if(type.equals("web")){
    		menber = (MenberDTO)request.getSession().getAttribute("pcmenber");
    		returnPage = "pointList_web";
    		String leftMenu = request.getParameter("leftMenu");
    		request.setAttribute("leftMenu", leftMenu);
    	}
        if(null != menber)
        {
//        	List list = myMenberPointFacade.listByMenId(menber.getId());
//        	List pointList = new ArrayList();
//    		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
//    			MenberPointDTO p = (MenberPointDTO) iterator.next();
//    			p.setCreateTimeStr(format2.format(p.getCreateTime()));
//    			pointList.add(p);
//    		}
//        	request.setAttribute("pointList", pointList);
//        	
        	MenberDTO m = this.myMenberFacade.get(menber.getId());
    		request.setAttribute("m", m);
        	
    		return mapping.findForward(returnPage);
        }
		return null;
	}
	
	
	
	
	
	/**
	 * 我的分享列表-分页模式
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
    public ActionForward listMyPointsForPagination(ActionMapping mapping,
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
            
            List<MenberPointDTO> list = (List<MenberPointDTO>)myMenberPointFacade.listForPagination(params).get("list");
            int count = (Integer)myMenberPointFacade.listForPagination(params).get("rows"); 
            
            sb.append("{\"pageCount\":\"" + count + "\",\"pageData\":[");
            for (MenberPointDTO point : list)
            {
                if (i > 0)
                {
                    sb.append(",");
                }
                sb.append("{");
                sb.append("\"pointDesc\":\"" + point.getPointDesc() + "\",");
                sb.append("\"point\":\"" + point.getPoint() + "\",");
                sb.append("\"createTime\":\"" + format.format(point.getCreateTime()) + "\"");
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

}