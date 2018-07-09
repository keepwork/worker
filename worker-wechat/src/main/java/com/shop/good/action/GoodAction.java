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