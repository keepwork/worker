package com.shop.order.action;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.common.util.ServletUtil;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.pub.menber.model.dto.MenberDTO;
import com.shop.good.model.dto.GoodDTO;
import com.shop.good.model.dto.GoodPicDTO;
import com.shop.good.model.facade.GoodFacade;
import com.shop.order.model.dto.ShopCarDTO;
import com.shop.order.model.facade.ShopCarFacade;
import com.sinovatech.common.web.action.BaseAdmAction;

/**
 * 购物车管理
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 30, 2015 9:55:43 PM
 */
public class ShopCarAction extends BaseAdmAction
{ 
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private ShopCarFacade myShopCarFacade;
	private GoodFacade myGoodFacade;
	
	public void init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		this.myShopCarFacade = (ShopCarFacade) this.getBeanContext().getBean("myShopCarFacade");
		this.myGoodFacade = (GoodFacade) this.getBeanContext().getBean("myGoodFacade");
	}
	
	
	/**
     * 查看购物车
     * @param request
     * @return
     */
	public ActionForward shopCarView(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		String type = request.getParameter("type");//类型：wap,web
		String returnPage = "";
		MenberDTO menber = null;
		if(type.equals("wap")){
			menber = (MenberDTO)request.getSession().getAttribute("wxmenber");
			returnPage = "shopcar_wap";
			request.setAttribute("pageTitle", "购物车");
    	}else if(type.equals("web")){
    		menber = (MenberDTO)request.getSession().getAttribute("pcmenber");
    		returnPage = "shopcar_web";
    		request.setAttribute("menu", "jfsc");
    	}
        if(null != menber)
        {
        	List<ShopCarDTO> returnList = new ArrayList<ShopCarDTO>();
        	List<ShopCarDTO> shopCarList = myShopCarFacade.listByMenId(menber.getId());
            int selectNum = 0;
            double totalPrice = 0.00;
            int totalPoint = 0;
            for (Iterator iterator = shopCarList.iterator(); iterator.hasNext();) {
            	ShopCarDTO car = (ShopCarDTO) iterator.next();
    			selectNum += car.getAmount();
    			totalPrice = totalPrice + car.getTotalPrice().doubleValue();
    			totalPoint = totalPoint + car.getTotalPoint();
    			returnList.add(car);
    		}
            request.setAttribute("shopCarList", returnList);
            request.setAttribute("selectNum", selectNum);
            request.setAttribute("totalPrice", totalPrice);
            request.setAttribute("totalPoint", totalPoint);
        }
    	
    	return mapping.findForward(returnPage);
	}
    
    
    
    /**
     * 根据ID删除购物车记录 前台
     * 
     * @param response
     * @param request
     */
	public ActionForward delShopCar(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		String type = request.getParameter("type");//类型：wap,web
		String returnPage = "";
		MenberDTO menber = null;
		if(type.equals("wap")){
			menber = (MenberDTO)request.getSession().getAttribute("wxmenber");
			returnPage = "shopCarView.do?type=wap";
    	}else if(type.equals("web")){
    		menber = (MenberDTO)request.getSession().getAttribute("pcmenber");
    		returnPage = "shopCarView.do?type=web";
    	}
        if(null != menber)
        {
        	String id = request.getParameter("id");
        	myShopCarFacade.delete(id);
        }
        response.sendRedirect(returnPage);
        return null;
	}
    
	
    /**
     * 清空购物车记录
     * 
     * @param response
     * @param request
     */
	public ActionForward delShopCarAll(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		String type = request.getParameter("type");//类型：wap,web
		String returnPage = "";
		MenberDTO menber = null;
		if(type.equals("wap")){
			menber = (MenberDTO)request.getSession().getAttribute("wxmenber");
			returnPage = "shopCarView.do?type=wap";
    	}else if(type.equals("web")){
    		menber = (MenberDTO)request.getSession().getAttribute("pcmenber");
    		returnPage = "shopCarView.do?type=web";
    	}
        if(null != menber)
        {
        	myShopCarFacade.delShopCarAll(menber.getId());
        }
        response.sendRedirect(returnPage);
        return null;
	}
	
	
	
	/**
     * 结算前检查购物车是否合法
     * 
     * @param request
     * @param response
     */
	public ActionForward checkShopCar(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
    	StringBuilder sb = new StringBuilder();
    	String type = request.getParameter("type");//类型：wap,web
		MenberDTO menber = null;
		if(null!=type){
			if(type.equals("wap")){
				menber = (MenberDTO)request.getSession().getAttribute("wxmenber");
	    	}else if(type.equals("web")){
	    		menber = (MenberDTO)request.getSession().getAttribute("pcmenber");
	    	}
		}
        if(null != menber)
        {
        	List<ShopCarDTO> shopCars = myShopCarFacade.listByMenId(menber.getId());// 获取购物车集合
        	if(shopCars.size()>0){
        		 //
        	}else{
        		sb.append("1");//请先选择商品！
        	}
        }else{
        	sb.append("4");//请先登录微信！
        }
        try{
            ServletUtil.outputXML(response, sb.toString());
        }catch (IOException e){
            e.printStackTrace();
        }
		return null;
    }
	
	
	
	/**
     * DWR 修改购物车
     *
     * @param request
     * @param response
     * @return
     */
	public void updateShopCar(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		String type = request.getParameter("type");//类型：wap,web
		MenberDTO menber = null;
		String openId = "";
		String orderType = "";
		if(type.equals("wap")){
			menber = (MenberDTO)request.getSession().getAttribute("wxmenber");
			openId = (String)request.getSession().getAttribute("openID");
			orderType = "1";
			
    	}else if(type.equals("web")){
    		menber = (MenberDTO)request.getSession().getAttribute("pcmenber");
    		orderType = "2";
    	}
        if(null != menber)
        {
        	String shopType = request.getParameter("shopType");
        	String goodId = request.getParameter("goodId");
        	GoodDTO good = myGoodFacade.get(goodId);
        	
        	List<ShopCarDTO> shopCarList = myShopCarFacade.listShopCar(menber.getId(),goodId);
            if (shopCarList.size()<=0)
            {
                // 如果购物车中没有该商品就将该商品加入到购物车
            	if(shopType.equals("add")){
            		ShopCarDTO shopCar = new ShopCarDTO();
                	shopCar.setOpenId(openId);// 微信用户保存openID 
                	String amount = request.getParameter("amount");
                	if(null!=amount && !amount.equals("")){
                		shopCar.setAmount(Integer.parseInt(amount));// 数量
                	}else{
                		shopCar.setAmount(1);// 数量
                	}
            	    shopCar.setGoodId(goodId);// 商品ID
            	    shopCar.setGoodName(good.getName());// 商品名称
            	    
            	    shopCar.setPrice(good.getPrice());// 单价
            	    shopCar.setPoint(good.getPoint());//积分
            	    shopCar.setTotalPrice(good.getPrice()); //商品总价
            	    shopCar.setTotalPoint(good.getPoint()); //商品总积分
            	    shopCar.setMenId(menber.getId());
            	    shopCar.setOrderType(orderType);//订单类型（1、微信订单；2、网站订单）
            	    shopCar.setCreateTime(new Date());
            	    
            	    String goodPicture = "";
            	    List<GoodPicDTO> imgList = myGoodFacade.listGoodImages(good.getId());
            		if(null!=imgList && imgList.size()>0){
            			GoodPicDTO pic = imgList.get(0);
            			goodPicture = pic.getPic();
            		}else{
            			goodPicture = "none.gif";
            		}
            		shopCar.setGoodPicture(goodPicture);// 图片
            		//shopCar.setGoodPicture(good.getSpic());// 图片
            		
            	    myShopCarFacade.save(shopCar);
            	}
            }else{
            	ShopCarDTO shopCar = shopCarList.get(0);
            	if(shopType.equals("add")){
            		// 如果购物车中已经有了该商品就将数量加1
                    shopCar.setAmount(shopCar.getAmount() + 1); 
                    double totalPrice = shopCar.getTotalPrice().doubleValue() + shopCar.getPrice().doubleValue();
                    shopCar.setTotalPrice(new BigDecimal(totalPrice));
                    Integer totalPoint = shopCar.getTotalPoint() + shopCar.getPoint();
                    shopCar.setTotalPoint(totalPoint);
                    myShopCarFacade.update(shopCar);
            	}
            	if(shopType.equals("del")){
            		// 如果购物车中已经有了该商品就将数量减1
            		if(shopCar.getAmount()>1){
            			shopCar.setAmount(shopCar.getAmount() - 1);
            			double totalPrice = shopCar.getTotalPrice().doubleValue() - shopCar.getPrice().doubleValue();
                        shopCar.setTotalPrice(new BigDecimal(totalPrice));
                        Integer totalPoint = shopCar.getTotalPoint() - shopCar.getPoint();
                        shopCar.setTotalPoint(totalPoint);
                        myShopCarFacade.update(shopCar); 
            		}else{
            			myShopCarFacade.delete(shopCar.getId());
            		}
            	}
            }
        }
        ServletUtil.outputXML(response, "0");
    }
}