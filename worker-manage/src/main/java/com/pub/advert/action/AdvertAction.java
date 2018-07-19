package com.pub.advert.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.common.util.DateUtil;
import com.common.util.ServletUtil;
import com.common.util.string.StringUtil;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.sinovatech.common.util.Validate;
import com.sinovatech.common.web.action.ActionConstent;
import com.sinovatech.common.web.action.BaseAdmAction;
import com.sinovatech.common.web.action.CommonMapping;
import com.sinovatech.common.web.limit.ExLimitUtil;
import com.sinovatech.common.web.limit.HqlProperty;
import com.sinovatech.common.web.limit.ILimitUtil;
import com.sinovatech.common.web.limit.LimitInfo;
//import com.sinovatech.marketplat.common.util.StringUtil;
import com.pub.advert.model.dto.AdvertDTO;
import com.pub.advert.model.facade.AdvertFacade;
//import com.weixin.util.DateUtil;
//import com.weixin.util.ServletUtil;

/**
 * 广告管理
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 30, 2015 9:55:43 PM
 */
public class AdvertAction extends BaseAdmAction
{ 
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private AdvertFacade myAdvertFacade;
	
	public void init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		this.myAdvertFacade = (AdvertFacade) this.getBeanContext().getBean("myAdvertFacade");
	}
	
	/**
	 * 分布查询 - 后台
	 * 
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
		String tableId = "AdvertList";

		// 获取分页信息
		ILimitUtil limitUtil = new ExLimitUtil();
		LimitInfo limit = limitUtil.getLimitInfo(request, tableId, 10);
		limit.setSortProperty("orderNum");
		limit.setSortType("asc");
		
		String cate = request.getParameter("cate");
		if (!StringUtil.stringVerify(cate)) {
			limit.addFilterProperty(HqlProperty.getEq("cate", Integer.parseInt(cate)));
			request.setAttribute("cate", cate);
		}
		
		// 查询
		List<AdvertDTO> list = myAdvertFacade.list(limit);
		// 设置分页信息
		limitUtil.setLimitInfo(request, limit);
		// 查询过滤、分页状态保留
		this.setActionAttribute(request, "backUrlStore", this.getActionContext(request).getCurentURL());

		// 保存数据
		List<AdvertDTO> artList = new ArrayList<AdvertDTO>(); 
		for (AdvertDTO a : list) {
			a.setCreateTimeStr(format.format(a.getCreateTime()));
			artList.add(a);
		}
		request.setAttribute("list", artList);
		return mapping.findForward("list");
	}

	/**
	 * 新增初始化 - 后台
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward beforeAdd(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		String cate = request.getParameter("cate");
		if (!StringUtil.stringVerify(cate)) {
			request.setAttribute("cate", cate);
		}
		return mapping.findForward("add");
	}

	/**
	 * 新增 - 后台
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		AdvertDTO dto = (AdvertDTO) form;
		CommonMapping mping=null;
		
		String cate = request.getParameter("cate");
		if (!StringUtil.stringVerify(cate)) {
			dto.setCate(Integer.parseInt(cate));
		}
		dto.setPoint(10);//默认每次点击奖励10积分
		dto.setCount(0);
		dto.setCreateTime(new Date());
		if(this.myAdvertFacade.save(dto)){
			mping = new CommonMapping("保存成功!", getRealUri(mapping,"advert/queryList") + "?cate=" + cate, ActionConstent.ALERT);
		}else{
			mping = new CommonMapping("parent.alert('保存失败!');");
		}

		request.setAttribute("mping", mping);
		return mapping.findForward(ActionConstent.COMMON_MAPPING);
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
		AdvertDTO m = this.myAdvertFacade.get(id);
		// 将当前编辑的数据中的不需要用户修改的数据保存的ActionContext中
		// 如主键等,代替使用隐藏域发送，更安全
		setActionAttribute(request, "beforeEdit.id", id);
		setActionAttribute(request, "beforeEdit.point", m.getPoint());
		setActionAttribute(request, "beforeEdit.createTime", m.getCreateTime());
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
		AdvertDTO dto = (AdvertDTO) form;
		// 设置不需要用户更新的数据，如主键等
//		dto.setId((String) getActionAttribute(request, "beforeEdit.id"));
		dto.setPoint((Integer) getActionAttribute(request, "beforeEdit.point"));
		dto.setCreateTime((Date) getActionAttribute(request, "beforeEdit.createTime"));
		
		this.myAdvertFacade.update(dto);
		CommonMapping mping = new CommonMapping("保存成功!", getRealUri(mapping,"advert/queryList") + "?cate=" + dto.getCate(), ActionConstent.ALERT);
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
		String cate = (String)request.getParameter("cate");
		request.setAttribute("cate",cate);
		// 验证方法， 如果为null或者为空则直接返回异常
		Validate.notBlank(ids, "common", "errorparameter");
		List list = this.myAdvertFacade.listByIds(ids);
		this.setActionAttribute(request, "beforeDelete.ids", ids);
		request.setAttribute("list", list);
		return mapping.findForward("deleteConfirm");
	}

	/**
	 * 删除 - 后台
	 * 
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
		String cate = (String)request.getParameter("cate");
		// 验证方法， 如果为null或者为空则直接返回异常
		Validate.notBlank(ids, "common", "errorparameter");
		this.myAdvertFacade.delete(ids);
		CommonMapping mping = new CommonMapping("删除成功!",(String) getActionAttribute(request, "backUrlStore"),ActionConstent.ALERT);
		request.setAttribute("mping", mping);
		return mapping.findForward(ActionConstent.COMMON_MAPPING);
	}

	
	
	
	
	
	
	
	/**
     * 广告列表初始化 - 前台
     * @param request
     * @return
     */
    public ActionForward advertList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
    	String type = request.getParameter("type");
    	String cate = request.getParameter("cate");
    	request.setAttribute("cate", cate);
    	request.setAttribute("pageTitle", "广告中心");
    	
    	if(type.equals("wap")){
    		return mapping.findForward("advertList_wap");
    	}else if(type.equals("web")){
    		return mapping.findForward("advertList_web");
    	}
		return null;
	}
    
    /**
     * 广告列表-分页模式 - 前台
     * @param request
     * @return
     */
    public ActionForward listAdvertForPagination(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
    	Map<String, String> params = new HashMap<String, String>();
    	
    	String type = request.getParameter("type");
    	if(null != type && !type.equals("")){
    		params.put("type", type);
    	}
    	
    	String cate = request.getParameter("cate");
    	if(null != cate && !cate.equals("")){
    		params.put("cate", cate);
    	}
    	
        StringBuilder sb = new StringBuilder();
        int i = 0;
        String size = request.getParameter("pageCount") == null ? "18" : request.getParameter("pageCount").trim();
        String start = request.getParameter("page") == null ? "0" : request.getParameter("page").trim();
        int startTemp = (start == null || start.isEmpty() ? '0' : Integer.parseInt(start))
            * (size == null || size.isEmpty() ? '0' : Integer.parseInt(size));
        
        params.put("start", startTemp + "");
        params.put("size", size);
        
        List<AdvertDTO> list = (List<AdvertDTO>)myAdvertFacade.listForPagination(params).get("list");
        int count = (Integer)myAdvertFacade.listForPagination(params).get("rows");
        
        sb.append("{\"pageCount\":\"" + count + "\",\"pageData\":[");
        for (AdvertDTO dto : list)
        {
            if (i > 0)
            {
                sb.append(",");
            }
            sb.append("{");
            sb.append("\"id\":\"" + dto.getId() + "\",");
            sb.append("\"title\":\"" + dto.getTitle() + "\",");
            sb.append("\"pic\":\"" + dto.getPic() + "\",");
            sb.append("\"content\":\"" + dto.getContent() + "\",");
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