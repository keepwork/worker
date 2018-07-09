package com.shop.good.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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

import com.shop.good.model.dto.GoodCategoryDTO;
import com.shop.good.model.dto.GoodDTO;
import com.shop.good.model.dto.GoodPicDTO;
import com.shop.good.model.dto.MyTree;
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
 * 商品管理
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 30, 2015 9:55:43 PM
 */
public class GoodAction extends BaseAdmAction
{ 
	private GoodFacade myGoodFacade;
	private GoodCategoryFacade myGoodCategoryFacade;
	
	public void init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		this.myGoodFacade = (GoodFacade) this.getBeanContext().getBean("myGoodFacade");
		this.myGoodCategoryFacade = (GoodCategoryFacade) this.getBeanContext().getBean("myGoodCategoryFacade");
	}
	
	/**
	 * 查询商品列表 - 后台
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
		String tableId = "GoodList";

		// 获取分页信息
		ILimitUtil limitUtil = new ExLimitUtil();
		LimitInfo limit = limitUtil.getLimitInfo(request, tableId, 30);

		// 查询
		List<GoodDTO> list = myGoodFacade.list(limit);

		// 设置分页信息
		limitUtil.setLimitInfo(request, limit);

		// 查询过滤、分页状态保留
		this.setActionAttribute(request, "backUrlStore", this.getActionContext(request).getCurentURL());

		// 保存数据
		List<GoodDTO> artList = new ArrayList<GoodDTO>(); 
		for (GoodDTO good : list) {
			if(good.getCatCode()!=null && !good.getCatCode().equals("")){
				String[] catCodes = good.getCatCode().split(",");
				Map rootCateMap = new HashMap();
				for (int i = 0; i < catCodes.length; i++) {
					GoodCategoryDTO cate = myGoodCategoryFacade.get(catCodes[i]);  
					GoodCategoryDTO parentCate = myGoodCategoryFacade.get(cate.getParentCode());  
					rootCateMap.put(parentCate.getName(), parentCate.getName());
				}
				String catRootNames = "";
				for(Iterator<String> i = rootCateMap.keySet().iterator(); i.hasNext();) { 
					Object obj = i.next(); 
					String rootName = (String)rootCateMap.get(obj);
					catRootNames += rootName + ",";
				}
				if (!catRootNames.equals("") && catRootNames.substring(catRootNames.length() - 1, catRootNames.length()).equals(",")){
					catRootNames = catRootNames.substring(0, catRootNames.length() - 1);
			    }
				good.setCatRootName(catRootNames);
				
				List<GoodPicDTO> imgList = myGoodFacade.listGoodImages(good.getId());
        		if(null!=imgList && imgList.size()>0){
        			GoodPicDTO pic = imgList.get(0);
        			good.setSpic(pic.getPic());
        		}else{
        			good.setSpic("none.gif");
        		}
			}
			artList.add(good);
		}
		request.setAttribute("list", artList);
		return mapping.findForward("list");
	}

	/**
	 * 新增商品初始化 - 后台
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
		MyTree tree = myGoodFacade.getComponentTree();// 商品类型树
        request.setAttribute("tree", tree);
		return mapping.findForward("add");
	}

	/**
	 * 新增商品 - 后台
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		GoodDTO good = (GoodDTO) form;
		
		// 得到商品类型id集合
		String[] cateMap = request.getParameterValues("cateMap");
		String cateIds = "";
		String cateNames = "";
		for (int i = 0; i < cateMap.length; i++) {
			cateIds += cateMap[i] + ",";
			GoodCategoryDTO cate = myGoodCategoryFacade.get(cateMap[i]);
			cateNames += cate.getName() + ",";
		}
		if (!cateIds.equals("") && cateIds.substring(cateIds.length() - 1, cateIds.length()).equals(",")){
			cateIds = cateIds.substring(0, cateIds.length() - 1);
	    }
		if (!cateNames.equals("") && cateNames.substring(cateNames.length() - 1, cateNames.length()).equals(",")){
			cateNames = cateNames.substring(0, cateNames.length() - 1);
	    }
        good.setCatCode(cateIds);
        good.setCatName(cateNames);
        
		CommonMapping mping=null;
		good.setUpdateTime(new Date());
		if(this.myGoodFacade.save(good)){
			mping = new CommonMapping("保存成功!", getRealUri(mapping,"good/queryList"), ActionConstent.ALERT);
		}else{
			mping = new CommonMapping("parent.alert('保存失败!');");
		}

		request.setAttribute("mping", mping);
		return mapping.findForward(ActionConstent.COMMON_MAPPING);
	}

	/**
	 * 修改商品初始化 - 后台
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
		GoodDTO m = this.myGoodFacade.get(id);
		// 将当前编辑的数据中的不需要用户修改的数据保存的ActionContext中
		// 如主键等,代替使用隐藏域发送，更安全
//		setActionAttribute(request, "beforeEdit.id", id);
		request.setAttribute("m", m);
		
		// 构建商品类型树
        Map cateMap = myGoodFacade.getCategoryIdsMap(m.getCatCode());
        request.setAttribute("cateMap", cateMap);
        MyTree tree = myGoodFacade.getComponentTree();
        request.setAttribute("tree", tree);
        
        // 商品图片
        List picList = this.myGoodFacade.listGoodImages(id);
        request.setAttribute("picList", picList);
        
		return mapping.findForward("edit");
	}
	
	/**
	 * 删除商品图片
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward deleteProductImg(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		String result = "0";
		String imgId = request.getParameter("imgId");
		this.myGoodFacade.deleteProductImg(imgId);
		result = "1";
        try{
            ServletUtil.outputXML(response, result);
        }catch (IOException e){
            e.printStackTrace();
        }
		return null;
    }

	/**
	 * 修改商品 - 后台
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		GoodDTO good = (GoodDTO) form;
		// 设置不需要用户更新的数据，如主键等
//		good.setId((String) getActionAttribute(request, "beforeEdit.id"));
		good.setUpdateTime(new Date());
		
		// 得到商品类型id集合
		String[] cateMap = request.getParameterValues("cateMap");
		String cateIds = "";
		String cateNames = "";
		for (int i = 0; i < cateMap.length; i++) {
			cateIds += cateMap[i] + ",";
			GoodCategoryDTO cate = myGoodCategoryFacade.get(cateMap[i]);
			cateNames += cate.getName() + ",";
		}
		if (!cateIds.equals("") && cateIds.substring(cateIds.length() - 1, cateIds.length()).equals(",")){
			cateIds = cateIds.substring(0, cateIds.length() - 1);
	    }
		if (!cateNames.equals("") && cateNames.substring(cateNames.length() - 1, cateNames.length()).equals(",")){
			cateNames = cateNames.substring(0, cateNames.length() - 1);
	    }
        good.setCatCode(cateIds);
        good.setCatName(cateNames);
		
		this.myGoodFacade.update(good);
		CommonMapping mping = new CommonMapping("保存成功!", getRealUri(mapping,"good/queryList"), ActionConstent.ALERT);
		request.setAttribute("mping", mping);
		return mapping.findForward(ActionConstent.COMMON_MAPPING);
	}

	/**
	 * 删除商品初始化 - 后台
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
		List list = this.myGoodFacade.listByIds(ids);
		this.setActionAttribute(request, "beforeDelete.ids", ids);
		request.setAttribute("list", list);
		return mapping.findForward("deleteConfirm");
	}

	/**
	 * 删除商品 - 后台
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
		this.myGoodFacade.delete(ids);
		CommonMapping mping = new CommonMapping("删除成功!",(String) getActionAttribute(request, "backUrlStore"),ActionConstent.ALERT);
		request.setAttribute("mping", mping);
		return mapping.findForward(ActionConstent.COMMON_MAPPING);
	}

	
	
	/**
	 * 商品列表 - 前台
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
    public ActionForward goodList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
    	String type = request.getParameter("type");//类型：wap,web
    	
    	// 商品所属分类的一级分类
        List<GoodCategoryDTO> firstList = myGoodCategoryFacade.listByParent("");
        request.setAttribute("firstList", firstList);
        if(firstList.size()>0)
        {
        	GoodCategoryDTO firstCate = firstList.get(0);
            
        	// 默认显示第一个二级分类的第一个三级分类
        	List<GoodCategoryDTO> secondList = myGoodCategoryFacade.listByParent(firstCate.getCode());
            request.setAttribute("secondList", secondList);
            
            // 默认显示第一个二级分类的所有商品
            List<GoodDTO> goodsList = new ArrayList<GoodDTO>();
            for (Iterator iterator = secondList.iterator(); iterator.hasNext();) {
            	GoodCategoryDTO secondCate = (GoodCategoryDTO) iterator.next();
                List<GoodDTO> list = myGoodFacade.listByCateCode(secondCate.getCode());
                goodsList.addAll(list);
			}
            
            // 去重
            List<String> idList = new ArrayList<String>();
            List<GoodDTO> returnList = new ArrayList<GoodDTO>();
            for (Iterator iterator = goodsList.iterator(); iterator.hasNext();) {
            	GoodDTO good = (GoodDTO) iterator.next();
            	if(!idList.contains(good.getId())){
            		//good.setDescr2(html(good.getDescr2()));
            		List<GoodPicDTO> imgList = myGoodFacade.listGoodImages(good.getId());
            		if(null!=imgList && imgList.size()>0){
            			GoodPicDTO pic = imgList.get(0);
            			good.setSpic(pic.getPic());
            		}else{
            			good.setSpic("none.gif");
            		}
            		returnList.add(good);
            		idList.add(good.getId());
            	}
			}
            request.setAttribute("goodsList", returnList);
        }
        
		String returnPage = "";
		if(type.equals("wap")){
			returnPage = "goodList_wap";
			request.setAttribute("pageTitle", "商品列表");
			
    	}else if(type.equals("web")){
    		returnPage = "goodList_web";
    		String menu = request.getParameter("menu");
    		request.setAttribute("menu", menu);
    	}
		
        return mapping.findForward(returnPage);
    }
    
    
    
    public ActionForward goodDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception
	{
    	String type = request.getParameter("type");//类型：wap,web
    	String goodId = request.getParameter("goodId");
    	GoodDTO good = myGoodFacade.get(goodId);
    	request.setAttribute("good", good);
    	
    	List<GoodPicDTO> picList = myGoodFacade.listGoodImages(good.getId());
    	request.setAttribute("picList", picList);
    	
    	String returnPage = "";
		if(type.equals("wap")){
			returnPage = "goodDetail_wap";
			request.setAttribute("pageTitle", "商品详情");
			
    	}else if(type.equals("web")){
    		returnPage = "goodDetail_web";
    		String menu = request.getParameter("menu");
    		request.setAttribute("menu", menu);
    		
    		// 商品所属分类的一级分类
            List<GoodCategoryDTO> firstList = myGoodCategoryFacade.listByParent("");
            request.setAttribute("firstList", firstList);
            if(firstList.size()>0)
            {
            	GoodCategoryDTO firstCate = firstList.get(0);
                
            	// 默认显示第一个二级分类的第一个三级分类
            	List<GoodCategoryDTO> secondList = myGoodCategoryFacade.listByParent(firstCate.getCode());
                request.setAttribute("secondList", secondList);
            }
    	}
		
		return mapping.findForward(returnPage);
	}

}