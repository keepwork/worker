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
	

}