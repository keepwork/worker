package com.pub.seo.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.pub.seo.model.dto.SeoDTO;
import com.pub.seo.model.facade.SeoFacade;
import com.sinovatech.common.web.action.ActionConstent;
import com.sinovatech.common.web.action.BaseAdmAction;
import com.sinovatech.common.web.action.CommonMapping;
import com.sinovatech.hd.tools.cache.CacheFactory;
import com.sinovatech.hd.tools.cache.ICache;

/**
 * SEO管理
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 30, 2015 9:55:43 PM
 */
public class SeoAction extends BaseAdmAction
{ 
	private static ICache cache = CacheFactory.newCache();
	
	private SeoFacade mySeoFacade;
	
	public void init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		this.mySeoFacade = (SeoFacade) this.getBeanContext().getBean("mySeoFacade");
	}
	

}