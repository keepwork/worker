package com.shop.complaint.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.common.util.ServletUtil;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.pub.menber.model.dto.MenberDTO;
import com.pub.menber.model.facade.MenberFacade;
import com.shop.complaint.model.dto.ComplaintDTO;
import com.shop.complaint.model.facade.ComplaintFacade;
import com.sinovatech.bms.adm.model.dto.TBmsUserDTO;
import com.sinovatech.common.util.Validate;
import com.sinovatech.common.web.action.ActionConstent;
import com.sinovatech.common.web.action.BaseAdmAction;
import com.sinovatech.common.web.action.CommonMapping;
import com.sinovatech.common.web.limit.ExLimitUtil;
import com.sinovatech.common.web.limit.ILimitUtil;
import com.sinovatech.common.web.limit.LimitInfo;
import com.common.util.date.DateUtil;


/**
 * 投诉与建议管理
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 30, 2015 9:55:43 PM
 */
public class ComplaintAction extends BaseAdmAction
{ 
	private ComplaintFacade myComplaintFacade;
	private MenberFacade myMenberFacade;
	
	public void init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		this.myComplaintFacade = (ComplaintFacade) this.getBeanContext().getBean("myComplaintFacade");
		this.myMenberFacade = (MenberFacade) this.getBeanContext().getBean("myMenberFacade");
	}
	
	/**
	 * 分页查询 - 后台
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward queryList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		// 列表控件的TableId值
		String tableId = "ComplaintList";

		// 获取分页信息
		ILimitUtil limitUtil = new ExLimitUtil();
		LimitInfo limit = limitUtil.getLimitInfo(request, tableId, 10);

		// 查询
		List<ComplaintDTO> list = myComplaintFacade.list(limit);

		// 设置分页信息
		limitUtil.setLimitInfo(request, limit);

		// 查询过滤、分页状态保留
		this.setActionAttribute(request, "backUrlStore", this.getActionContext(request).getCurentURL());

		List<ComplaintDTO> returnList = new ArrayList<ComplaintDTO>(); 
		for (ComplaintDTO dto : list) {
			MenberDTO men = myMenberFacade.get(dto.getMenId());
			if(null==men || null==men.getRealName() || men.getRealName().equals("")){
				dto.setMenName("");
			}else{
				dto.setMenName(men.getRealName());
			}
			
			returnList.add(dto);
		}
		
		request.setAttribute("list", returnList);
		return mapping.findForward("list");
	}

	
	/**
	 * 修改初始化 - 后台
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward beforeEdit(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		String id = request.getParameter("id");
		// 验证方法， 如果为null或者为空则直接返回异常
		Validate.notBlank(id, "common", "errorparameter");
		ComplaintDTO m = this.myComplaintFacade.get(id);
		// 将当前编辑的数据中的不需要用户修改的数据保存的ActionContext中
		// 如主键等,代替使用隐藏域发送，更安全
		setActionAttribute(request, "beforeEdit.id", id);
		setActionAttribute(request, "beforeEdit.menId", m.getMenId());
		setActionAttribute(request, "beforeEdit.compTime", m.getCompTime());
		request.setAttribute("m", m);
		return mapping.findForward("edit");
	}

	/**
	 * 修改 - 后台
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		ComplaintDTO dto = (ComplaintDTO) form;
		// 设置不需要用户更新的数据，如主键等
		dto.setId((String) getActionAttribute(request, "beforeEdit.id"));
		dto.setMenId((String) getActionAttribute(request, "beforeEdit.menId"));
		dto.setCompTime((Date) getActionAttribute(request, "beforeEdit.compTime"));
		
		TBmsUserDTO user = getUser(request);
		dto.setUserId(user.getId());
		dto.setCommitTime(new Date());
		dto.setStatus(1);
		this.myComplaintFacade.update(dto);
		CommonMapping mping = new CommonMapping("保存成功!", getRealUri(mapping,"complaint/queryList"), ActionConstent.ALERT);
		request.setAttribute("mping", mping);
		return mapping.findForward(ActionConstent.COMMON_MAPPING);
	}

	/**
	 * 删除初始化 - 后台
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward beforeDelete(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		String ids = request.getParameter("ids");
		// 验证方法， 如果为null或者为空则直接返回异常
		Validate.notBlank(ids, "common", "errorparameter");
		List list = this.myComplaintFacade.listByIds(ids);
		this.setActionAttribute(request, "beforeDelete.ids", ids);
		request.setAttribute("list", list);
		return mapping.findForward("deleteConfirm");
	}

	/**
	 * 删除 - 后台
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		String ids = (String) this.getActionAttribute(request,"beforeDelete.ids");
//		String ids = (String)request.getParameter("ids");
		// 验证方法， 如果为null或者为空则直接返回异常
		Validate.notBlank(ids, "common", "errorparameter");
		this.myComplaintFacade.delete(ids);
		CommonMapping mping = new CommonMapping("删除成功!",(String) getActionAttribute(request, "backUrlStore"),ActionConstent.ALERT);
		request.setAttribute("mping", mping);
		return mapping.findForward(ActionConstent.COMMON_MAPPING);
	}

	
	
	
	
	
	
	
	
	
	
	
	/**
     * 建议投诉初始化 - 前台
     * @param request
     * @return
     */
    public ActionForward complaintList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
    	String type = request.getParameter("type");
    	
    	request.setAttribute("pageTitle", "投诉与建议");
    	if(type.equals("wap")){
    		return mapping.findForward("complaintList_wap");
    	}else if(type.equals("web")){
    		return mapping.findForward("complaintList_web");
    	}
		return null;
	}
    
    /**
     * 建议投诉-分页模式 - 前台
     * @param request
     * @return
     */
    public ActionForward listComplaintForPagination(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
    	Map<String, String> params = new HashMap<String, String>();
        MenberDTO wxmenber = (MenberDTO)request.getSession().getAttribute("wxmenber");
        if(null != wxmenber){
        	params.put("menId", wxmenber.getId());
        }
        
        StringBuilder sb = new StringBuilder();
        int i = 0;
        String size = request.getParameter("pageCount") == null ? "18" : request.getParameter("pageCount").trim();
        String start = request.getParameter("page") == null ? "0" : request.getParameter("page").trim();
        int startTemp = (start == null || start.isEmpty() ? '0' : Integer.parseInt(start))
            * (size == null || size.isEmpty() ? '0' : Integer.parseInt(size));
        
        params.put("start", startTemp + "");
        params.put("size", size);
        
        List<ComplaintDTO> list = (List<ComplaintDTO>)myComplaintFacade.listForPagination(params).get("list");
        int count = (Integer)myComplaintFacade.listForPagination(params).get("rows");
        
        sb.append("{\"pageCount\":\"" + count + "\",\"pageData\":[");
        for (ComplaintDTO complaint : list)
        {
            if (i > 0)
            {
                sb.append(",");
            }
            sb.append("{");
            sb.append("\"id\":\"" + complaint.getId() + "\",");
            sb.append("\"compTime\":\"" + (DateUtil.formatDate(complaint.getCompTime(), "yyyy-MM-dd HH:ss:mm")) + "\",");
            sb.append("\"status\":\"" + complaint.getStatus() + "\"");
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
     * @method 建议投诉详情页面 - 前台
     * @return 
     */
    public ActionForward complaintDetail(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
    	String type = request.getParameter("type");
    	String id = request.getParameter("id");
        ComplaintDTO c = myComplaintFacade.get(id);
        c.setCommitTimeStr(c.getCommitTime() == null ? "" : (DateUtil.date2string(c.getCommitTime(),"yyyy-MM-dd HH:ss:mm")));
        c.setCompTimeStr(c.getCompTime() == null ? "" : (DateUtil.date2string(c.getCompTime(), "yyyy-MM-dd HH:ss:mm")));
        c.setHandTimeStr(c.getHandTime() == null ? "" : (DateUtil.date2string(c.getHandTime(), "yyyy-MM-dd HH:ss:mm")));
        request.setAttribute("complaint", c);
        
        request.setAttribute("pageTitle", "投诉与建议详情");
        if(type.equals("wap")){
    		return mapping.findForward("complaintDetail_wap");
    	}else if(type.equals("web")){
    		return mapping.findForward("complaintDetail_web");
    	}
        return null;
    }
    
    /**
     * 保存建议投诉 - 前台
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward saveComplaint(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
    	String type = request.getParameter("type");
    	ComplaintDTO c = (ComplaintDTO) form;
    	MenberDTO m = (MenberDTO)request.getSession().getAttribute("wxmenber");
        if(null != m)
        {
        	c.setMenId(m.getId());
            c.setStatus(0);// 默认未处理
            c.setCompTime(new Date());
            myComplaintFacade.save(c);
            try
            {
            	response.setContentType("text/html; charset=UTF-8"); //转码
            	PrintWriter writer = response.getWriter();
            	writer.flush();
            	writer.println("<script>");
            	writer.println("alert('您的建议已提交，我们会尽快处理，谢谢!');");
            	writer.println("</script>");
            	
            	if(type.equals("wap")){
            		return mapping.findForward("complaintList_wap");
            	}else if(type.equals("web")){
            		return mapping.findForward("complaintList_web");
            	}
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
		return null;
    }


}