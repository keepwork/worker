package com.shop.good.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.shop.good.model.dto.GoodCategoryDTO;
import com.shop.good.model.dto.GoodDTO;
import com.shop.good.model.dto.GoodPicDTO;
import com.shop.good.model.facade.GoodCategoryFacade;
import com.shop.good.model.facade.GoodFacade;
import com.sinovatech.common.util.Validate;
import com.sinovatech.common.web.action.ActionConstent;
import com.sinovatech.common.web.action.BaseAdmAction;
import com.sinovatech.common.web.action.CommonMapping;
import com.sinovatech.common.web.limit.ExLimitUtil;
import com.sinovatech.common.web.limit.ILimitUtil;
import com.sinovatech.common.web.limit.LimitInfo;

/**
 * 商品分类管理
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 30, 2015 9:55:43 PM
 */
public class GoodCategoryAction extends BaseAdmAction
{ 
	private GoodCategoryFacade myGoodCategoryFacade;
	private GoodFacade myGoodFacade;
	
	public void init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		this.myGoodCategoryFacade = (GoodCategoryFacade) this.getBeanContext().getBean("myGoodCategoryFacade");
		this.myGoodFacade = (GoodFacade) this.getBeanContext().getBean("myGoodFacade");
	}
	
	/**
	 * 商品分类查询 - 后台
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
		String tableId = "GoodCategoryList";

		// 获取分页信息
		ILimitUtil limitUtil = new ExLimitUtil();
		LimitInfo limit = limitUtil.getLimitInfo(request, tableId, 30);
		limit.setSortProperty("parentCode,code");
		limit.setSortType("asc");
		
		// 查询
		List<GoodCategoryDTO> list = myGoodCategoryFacade.list(limit);

		// 设置分页信息
		limitUtil.setLimitInfo(request, limit);

		// 查询过滤、分页状态保留
		this.setActionAttribute(request, "backUrlStore", this.getActionContext(request).getCurentURL());

		// 保存数据
		List<GoodCategoryDTO> cateList = new ArrayList<GoodCategoryDTO>(); 
		for (GoodCategoryDTO cat1 : list) {
			if(cat1.getParentCode()!=null && !cat1.getParentCode().equals("")){
				GoodCategoryDTO cat2 = myGoodCategoryFacade.get(cat1.getParentCode());  
				cat1.setParentName(cat2.getName());
			}
			cateList.add(cat1);
		}
		request.setAttribute("list", cateList);
		return mapping.findForward("list");
	}

	/**
	 * 新增商品分类初始化 - 后台
	 * 
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
		return mapping.findForward("add");
	}

	/**
	 * 新增商品分类- 后台
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
		GoodCategoryDTO dto = (GoodCategoryDTO) form;
		CommonMapping mping=null;
		if(this.myGoodCategoryFacade.save(dto)){
			mping = new CommonMapping("保存成功!", getRealUri(mapping,"goodCate/queryList"), ActionConstent.ALERT);
		}else{
			mping = new CommonMapping("parent.alert('保存失败!');");
		}

		request.setAttribute("mping", mping);
		return mapping.findForward(ActionConstent.COMMON_MAPPING);
	}

	/**
	 * 修改商品分类初始化 - 后台
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
		String code = request.getParameter("code");
		// 验证方法， 如果为null或者为空则直接返回异常
		Validate.notBlank(code, "common", "errorparameter");
		GoodCategoryDTO m = this.myGoodCategoryFacade.get(code);
		// 将当前编辑的数据中的不需要用户修改的数据保存的ActionContext中
		// 如主键等,代替使用隐藏域发送，更安全
//		setActionAttribute(request, "beforeEdit.code", code);
		request.setAttribute("m", m);
		return mapping.findForward("edit");
	}

	/**
	 * 修改商品分类 - 后台
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
		GoodCategoryDTO dto = (GoodCategoryDTO) form;
		// 设置不需要用户更新的数据，如主键等
//		dto.setCode((String) getActionAttribute(request, "beforeEdit.code"));
		this.myGoodCategoryFacade.update(dto);
		CommonMapping mping = new CommonMapping("保存成功!", getRealUri(mapping,"goodCate/queryList"), ActionConstent.ALERT);
		request.setAttribute("mping", mping);
		return mapping.findForward(ActionConstent.COMMON_MAPPING);
	}

	/**
	 * 删除商品分类初始化 - 后台
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
		List list = this.myGoodCategoryFacade.listByIds(ids);
		this.setActionAttribute(request, "beforeDelete.ids", ids);
		request.setAttribute("list", list);
		return mapping.findForward("deleteConfirm");
	}

	/**
	 * 删除商品分类 - 后台
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
//		String ids = (String)request.getParameter("ids");
		// 验证方法， 如果为null或者为空则直接返回异常
		Validate.notBlank(ids, "common", "errorparameter");
		this.myGoodCategoryFacade.delete(ids);
		CommonMapping mping = new CommonMapping("删除成功!",(String) getActionAttribute(request, "backUrlStore"),ActionConstent.ALERT);
		request.setAttribute("mping", mping);
		return mapping.findForward(ActionConstent.COMMON_MAPPING);
	}

	
	/**
	 * 选择产品分类 
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
    @SuppressWarnings("unchecked")
	public ActionForward firstCates(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
    	String serviceType = request.getParameter("serviceType");
    	request.setAttribute("serviceType", serviceType);
    	List<GoodCategoryDTO> cateList = myGoodCategoryFacade.listByParent("");
        request.setAttribute("firstCateList", cateList);
        
        request.setAttribute("pageTitle", "选择产品分类");
        return mapping.findForward("firstCates_wap");
    }
    
    /**
     * 选择产品类型
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward secondCates(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
    	String serviceType = request.getParameter("serviceType");
    	String firstCateCode = request.getParameter("firstCateCode");
    	request.setAttribute("serviceType", serviceType);
    	request.setAttribute("firstCateCode", firstCateCode);
    	
    	GoodCategoryDTO firstCate = myGoodCategoryFacade.get(firstCateCode);
    	request.setAttribute("firstCate", firstCate);
    	
    	List<GoodCategoryDTO> cateList = myGoodCategoryFacade.listByParent(firstCateCode);
        request.setAttribute("secondCateList", cateList);
        
        request.setAttribute("pageTitle", "选择产品类型");
        return mapping.findForward("secondCates_wap");
    }
	
	
	


}