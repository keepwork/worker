package com.shop.good.action;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.shop.good.model.dto.GoodCategoryDTO;
import com.shop.good.model.facade.GoodCategoryFacade;
import com.shop.good.model.facade.GoodFacade;
import com.sinovatech.common.web.action.BaseAdmAction;

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