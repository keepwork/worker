package com.pub.menber.action;

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

import com.pub.menber.model.dto.MenberCashDTO;
import com.pub.menber.model.dto.MenberDTO;
import com.pub.menber.model.facade.MenberCashFacade;
import com.pub.menber.model.facade.MenberFacade;
import com.sinovatech.bms.adm.model.dto.TBmsUserDTO;
import com.sinovatech.common.web.action.ActionConstent;
import com.sinovatech.common.web.action.BaseAdmAction;
import com.sinovatech.common.web.action.CommonMapping;
import com.sinovatech.common.web.limit.ExLimitUtil;
import com.sinovatech.common.web.limit.HqlProperty;
import com.sinovatech.common.web.limit.ILimitUtil;
import com.sinovatech.common.web.limit.LimitInfo;

/**
 * 提现记录管理
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 30, 2015 9:55:43 PM
 */
public class MenberCashAction extends BaseAdmAction
{ 
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private MenberCashFacade myMenberCashFacade;
	private MenberFacade myMenberFacade;
	
	public void init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		this.myMenberCashFacade = (MenberCashFacade) this.getBeanContext().getBean("myMenberCashFacade");
		this.myMenberFacade = (MenberFacade) this.getBeanContext().getBean("myMenberFacade");
	}
	
	/**
	 * 后台分页查询
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
		String tableId = "CashList";
		String returnStr = "";

		// 获取分页信息
		ILimitUtil limitUtil = new ExLimitUtil();
		LimitInfo limit = limitUtil.getLimitInfo(request, tableId, 10);
		limit.setSortProperty("createTime");
		limit.setSortType("desc");

		String menId = request.getParameter("menId");
		if(null!=menId && !menId.equals("")){
			limit.addFilterProperty(HqlProperty.getEq("menId",menId));
			returnStr = "listMenberCash";
		}else{
			returnStr = "listAll";
		}
		
		// 查询
		List<MenberCashDTO> list = myMenberCashFacade.list(limit);

		// 设置分页信息
		limitUtil.setLimitInfo(request, limit);
		// 查询过滤、分页状态保留
		this.setActionAttribute(request, "backUrlStore", this.getActionContext(request).getCurentURL());

		List cashList = new ArrayList();
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			MenberCashDTO p = (MenberCashDTO) iterator.next();
			p.setCreateTimeStr(format.format(p.getCreateTime()));
			if(null!=p.getAuditTime()){
				p.setAuditTimeStr(format.format(p.getAuditTime()));
			}
			MenberDTO menber = myMenberFacade.get(p.getMenId());
			p.setMenberName(menber.getRealName());
			p.setMobile(menber.getMobile());
			p.setPid(menber.getPid());
			p.setBalanceFee(menber.getBalanceFee().doubleValue());
			cashList.add(p);
		}
		
		request.setAttribute("list", cashList);
		return mapping.findForward(returnStr);
	}

	
	
	
	/**
	 * 提现申请
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tixian(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		String type = request.getParameter("type");//类型：wap,web
		String menId = request.getParameter("menId");//要提现的会员id
		String price = request.getParameter("price");
		String bankUserName = request.getParameter("bankUserName");
		String bankName = request.getParameter("bankName");
		String bankNum = request.getParameter("bankNum");
		String returnStr = "0";
		
		MenberDTO menber = null;
		if(type.equals("wap")){
			menber = (MenberDTO)request.getSession().getAttribute("wxmenber");
    	}else if(type.equals("web")){
    		menber = (MenberDTO)request.getSession().getAttribute("pcmenber");
    	}
		if(null != menber)
        {
			MenberDTO dto = myMenberFacade.get(menber.getId());
			if(dto.getBalanceFee().doubleValue()<Double.parseDouble(price)){
				returnStr = "2";//余额不足
			}else if(dto.getBalanceFee().doubleValue()<100){
				returnStr = "4";//余额不足100元
			}else{
				MenberCashDTO cash = this.myMenberCashFacade.getByMenId(menber.getId());
				if(null!=cash){
					returnStr = "3";//您当前已有提现申请，请等待审核结果！
				}else{
					MenberCashDTO c = new MenberCashDTO();
					c.setMenId(menId);
					c.setCreateTime(new Date());
					c.setIsDown(1);//申请中
					c.setPrice(new BigDecimal(price));
					c.setBankName(bankName);
					c.setBankNum(bankNum);
					c.setBankUserName(bankUserName);
					myMenberCashFacade.save(c);
					
					returnStr = "1";//申请成功
				}
			}
        }
   
		try{
            ServletUtil.outputXML(response, returnStr);
        }catch (IOException e){
            e.printStackTrace();
        }
		return null;
	}

	
	/**
	 * 提现审核前检查余额是否充足
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward auditCheck(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		String returnStr = "0";
		String id  =request.getParameter("id");
		MenberCashDTO cash = this.myMenberCashFacade.get(id);
		if(null!=cash){
			MenberDTO men = this.myMenberFacade.get(cash.getMenId());
			if(null!=men && men.getBalanceFee().doubleValue()>=cash.getPrice().doubleValue()){
				returnStr = "1";//满足要求
			}
		}
        try{
            ServletUtil.outputXML(response, returnStr);
        }catch (IOException e){
            e.printStackTrace();
        }
		return null;
    }
	
	/**
	 * 提现审请审核  - 后台
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward audit(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		MenberCashDTO dto = (MenberCashDTO) form;
		MenberCashDTO old = this.myMenberCashFacade.get(dto.getId());
		old.setComment(dto.getComment());
		old.setAuditTime(new Date());
		old.setIsDown(dto.getIsDown());
		TBmsUserDTO user = getUser(request);
		old.setAuditor(user.getId());
		this.myMenberCashFacade.audit(old);
		
		CommonMapping mping = new CommonMapping("审核成功!", getRealUri(mapping,"cash/queryList"), ActionConstent.ALERT);
		request.setAttribute("mping", mping);
		return mapping.findForward(ActionConstent.COMMON_MAPPING);
	}

	


}