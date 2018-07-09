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

import com.common.util.ServletUtil;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.pub.menber.model.dto.MenberDTO;
import com.pub.menber.model.dto.MenberShareDTO;
import com.pub.menber.model.facade.MenberFacade;
import com.pub.menber.model.facade.MenberShareFacade;
import com.sinovatech.common.web.action.BaseAdmAction;
import com.sinovatech.common.web.limit.ExLimitUtil;
import com.sinovatech.common.web.limit.HqlProperty;
import com.sinovatech.common.web.limit.ILimitUtil;
import com.sinovatech.common.web.limit.LimitInfo;

/**
 * 分享记录管理
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 30, 2015 9:55:43 PM
 */
public class MenberShareAction extends BaseAdmAction
{ 
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private MenberShareFacade myMenberShareFacade;
	private MenberFacade myMenberFacade;
	
	public void init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		this.myMenberShareFacade = (MenberShareFacade) this.getBeanContext().getBean("myMenberShareFacade");
		this.myMenberFacade = (MenberFacade) this.getBeanContext().getBean("myMenberFacade");
	}
	
	/**
	 * 会员分享列表查询 -后台
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
		String tableId = "ShareList";

		// 获取分页信息
		ILimitUtil limitUtil = new ExLimitUtil();
		LimitInfo limit = limitUtil.getLimitInfo(request, tableId, 10);

		String menId = request.getParameter("menId");
		limit.addFilterProperty(HqlProperty.getEq("menId",menId));
		
		// 查询
		List<MenberShareDTO> list = myMenberShareFacade.list(limit);

		// 设置分页信息
		limitUtil.setLimitInfo(request, limit);

		// 查询过滤、分页状态保留
		this.setActionAttribute(request, "backUrlStore", this.getActionContext(request).getCurentURL());

		List pointList = new ArrayList();
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			MenberShareDTO p = (MenberShareDTO) iterator.next();
			MenberDTO men = myMenberFacade.get(p.getShareMenId());
			p.setRealName(men.getRealName());
			p.setHeadimgurl(men.getHeadimgurl());
			p.setCreateTimeStr(format.format(p.getCreateTime()));
			pointList.add(p);
		}
		
		request.setAttribute("list", pointList);
		return mapping.findForward("list");
	}

}