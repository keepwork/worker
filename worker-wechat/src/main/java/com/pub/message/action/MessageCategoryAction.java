package com.pub.message.action;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.pub.message.model.dto.MessageCategoryDTO;
import com.pub.message.model.facade.MessageCategoryFacade;
import com.sinovatech.common.util.Validate;
import com.sinovatech.common.web.action.ActionConstent;
import com.sinovatech.common.web.action.BaseAdmAction;
import com.sinovatech.common.web.action.CommonMapping;
import com.sinovatech.common.web.limit.ExLimitUtil;
import com.sinovatech.common.web.limit.ILimitUtil;
import com.sinovatech.common.web.limit.LimitInfo;

/**
 * 消息分类管理
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 30, 2015 9:55:43 PM
 */
public class MessageCategoryAction extends BaseAdmAction
{ 
	private MessageCategoryFacade myMessageCategoryFacade;
	

	public void init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		this.myMessageCategoryFacade = (MessageCategoryFacade) this.getBeanContext().getBean("myMessageCategoryFacade");
	}


}