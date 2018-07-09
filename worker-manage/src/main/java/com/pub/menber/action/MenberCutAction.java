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

import com.common.util.DateUtil;
import com.common.util.ServletUtil;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.pub.menber.model.dto.MenberCutDTO;
import com.pub.menber.model.dto.MenberDTO;
import com.pub.menber.model.facade.MenberCutFacade;
import com.sinovatech.common.util.Validate;
import com.sinovatech.common.web.action.ActionConstent;
import com.sinovatech.common.web.action.BaseAdmAction;
import com.sinovatech.common.web.action.CommonMapping;
import com.sinovatech.common.web.limit.ExLimitUtil;
import com.sinovatech.common.web.limit.HqlProperty;
import com.sinovatech.common.web.limit.ILimitUtil;
import com.sinovatech.common.web.limit.LimitInfo;

/**
 * 扣款记录管理
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 30, 2015 9:55:43 PM
 */
public class MenberCutAction extends BaseAdmAction
{ 
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private MenberCutFacade myMenberCutFacade;
	public void init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		this.myMenberCutFacade = (MenberCutFacade) this.getBeanContext().getBean("myMenberCutFacade");
	}
	
	
	public ActionForward queryList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		// 列表控件的TableId值
		String tableId = "CutList";

		// 获取分页信息
		ILimitUtil limitUtil = new ExLimitUtil();
		LimitInfo limit = limitUtil.getLimitInfo(request, tableId, 10);

		String menId = request.getParameter("menId");
		limit.addFilterProperty(HqlProperty.getEq("menId",menId));
		
		// 查询
		List<MenberCutDTO> list = myMenberCutFacade.list(limit);

		// 设置分页信息
		limitUtil.setLimitInfo(request, limit);
		// 查询过滤、分页状态保留
		this.setActionAttribute(request, "backUrlStore", this.getActionContext(request).getCurentURL());

		List pointList = new ArrayList();
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			MenberCutDTO p = (MenberCutDTO) iterator.next();
			p.setCreateTimeStr(format.format(p.getCreateTime()));
			pointList.add(p);
		}
		
		request.setAttribute("list", pointList);
		return mapping.findForward("list");
	}

	
	public ActionForward beforeAdd(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return mapping.findForward("add");
	}

	
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		MenberCutDTO dto = (MenberCutDTO) form;
		CommonMapping mping=null;
		if(this.myMenberCutFacade.save(dto)){
			mping = new CommonMapping("保存成功!", getRealUri(mapping,"menber/queryList"), ActionConstent.ALERT);
		}else{
			mping = new CommonMapping("parent.alert('保存失败!');");
		}

		request.setAttribute("mping", mping);
		return mapping.findForward(ActionConstent.COMMON_MAPPING);
	}

	
	public ActionForward beforeEdit(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		String id = request.getParameter("id");
		// 验证方法， 如果为null或者为空则直接返回异常
		Validate.notBlank(id, "common", "errorparameter");
		MenberCutDTO m = this.myMenberCutFacade.get(id);
		// 将当前编辑的数据中的不需要用户修改的数据保存的ActionContext中
		// 如主键等,代替使用隐藏域发送，更安全
		setActionAttribute(request, "beforeEdit.id", id);
		request.setAttribute("m", m);
		return mapping.findForward("edit");
	}

	
	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		MenberCutDTO dto = (MenberCutDTO) form;
		// 设置不需要用户更新的数据，如主键等
		dto.setId((String) getActionAttribute(request, "beforeEdit.id"));
		this.myMenberCutFacade.update(dto);
		CommonMapping mping = new CommonMapping("保存成功!", getRealUri(mapping,"menber/queryList"), ActionConstent.ALERT);
		request.setAttribute("mping", mping);
		return mapping.findForward(ActionConstent.COMMON_MAPPING);
	}

	
	public ActionForward beforeDelete(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		String ids = request.getParameter("ids");
		// 验证方法， 如果为null或者为空则直接返回异常
		Validate.notBlank(ids, "common", "errorparameter");
		List list = this.myMenberCutFacade.listByIds(ids);
		this.setActionAttribute(request, "beforeDelete.ids", ids);
		request.setAttribute("list", list);
		return mapping.findForward("deleteConfirm");
	}

	
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		String ids = (String) this.getActionAttribute(request,"beforeDelete.ids");
//		String ids = (String)request.getParameter("ids");
		// 验证方法， 如果为null或者为空则直接返回异常
		Validate.notBlank(ids, "common", "errorparameter");
		this.myMenberCutFacade.delete(ids);
		CommonMapping mping = new CommonMapping("删除成功!",(String) getActionAttribute(request, "backUrlStore"),ActionConstent.ALERT);
		request.setAttribute("mping", mping);
		return mapping.findForward(ActionConstent.COMMON_MAPPING);
	}

	


	/**
     * 扣款记录列表初始化 - 前台
     * @param request
     * @return
     */
    public ActionForward myCutList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
    	String type = request.getParameter("type");//类型：wap,web
    	String menId = request.getParameter("menId");
		String returnPage = "";
		MenberDTO menber = null;
		if(type.equals("wap")){
			menber = (MenberDTO)request.getSession().getAttribute("wxmenber");
			returnPage = "myCutList_wap";
    	}else if(type.equals("web")){
    		menber = (MenberDTO)request.getSession().getAttribute("pcmenber");
    		returnPage = "myCutList_web";
    	}
        if(null != menber)
        {
        	request.setAttribute("menId", menId);
        	return mapping.findForward(returnPage);
        }
		return null;
	}
    
    /**
     * 扣款记录列表-分页模式 - 前台
     * @param request
     * @return
     */
    public ActionForward listMyCutForPagination(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
    	Map<String, String> params = new HashMap<String, String>();
		String menId = request.getParameter("menId");
		params.put("menId", menId);
    	
        StringBuilder sb = new StringBuilder();
        int i = 0;
        String size = request.getParameter("pageCount") == null ? "18" : request.getParameter("pageCount").trim();
        String start = request.getParameter("page") == null ? "0" : request.getParameter("page").trim();
        int startTemp = (start == null || start.isEmpty() ? '0' : Integer.parseInt(start))
            * (size == null || size.isEmpty() ? '0' : Integer.parseInt(size));
        
        params.put("start", startTemp + "");
        params.put("size", size);
        
        List<MenberCutDTO> list = (List<MenberCutDTO>)myMenberCutFacade.listForPagination(params).get("list");
        int count = (Integer)myMenberCutFacade.listForPagination(params).get("rows");
        
        sb.append("{\"pageCount\":\"" + count + "\",\"pageData\":[");
        for (MenberCutDTO dto : list)
        {
            if (i > 0)
            {
                sb.append(",");
            }
            sb.append("{");
            sb.append("\"id\":\"" + dto.getId() + "\",");
            String type = "";
            if(dto.getType() == 1){
            	type = "保证金扣款";
            }else if(dto.getType() == 2){
            	type = "平摊扣款";
            }else if(dto.getType() == 3){
            	type = "购物扣款";
            }
            sb.append("\"type\":\"" + type + "\",");
            //sb.append("\"helpMenId\":\"" + dto.getHelpMenId() + "\",");
            sb.append("\"price\":\"" + dto.getPrice() + "\",");
            sb.append("\"createTime\":\"" + (DateUtil.formatDate(dto.getCreateTime(), "yyyy-MM-dd HH:ss:mm")) + "\"");
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

}