package com.shop.appraise.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.shop.order.model.dto.OrderDTO;
import com.shop.order.model.facade.OrderFacade;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.pub.menber.model.dto.MenberDTO;
import com.pub.menber.model.facade.MenberFacade;
import com.shop.appraise.model.dto.AppraiseDTO;
import com.shop.appraise.model.facade.AppraiseFacade;
import com.sinovatech.common.web.action.BaseAdmAction;

/**
 * 评价管理
 * 
 * @author BruceKobe(javalc@163.com)
 * @date Dec 30, 2018 9:55:43 PM
 */
public class AppraiseAction extends BaseAdmAction
{ 
	private AppraiseFacade myAppraiseFacade;
	private MenberFacade myMenberFacade;
	private OrderFacade myOrderFacade;
	
	public void init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		this.myAppraiseFacade = (AppraiseFacade) this.getBeanContext().getBean("myAppraiseFacade");
		this.myMenberFacade = (MenberFacade) this.getBeanContext().getBean("myMenberFacade");
		this.myOrderFacade = (OrderFacade) this.getBeanContext().getBean("myOrderFacade");
	}

    /**
     * 保存评价 - 前台
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward saveAppraise(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{

    	MenberDTO m = (MenberDTO)request.getSession().getAttribute("wxmenber");
		if(null != m) {
			String orderId = request.getParameter("orderId");
			OrderDTO orderDTO = myOrderFacade.get(orderId);
			MenberDTO worker = myMenberFacade.get(orderDTO.getWorkerId());
			String appriseDesc = request.getParameter("appriseDesc");
			String zhiliangScore = request.getParameter("zhiliangScore");
			String taiduScore = request.getParameter("taiduScore");
			String zhunshiScore = request.getParameter("zhunshiScore");

			AppraiseDTO dto = new AppraiseDTO();
			dto.setCreateTime(new Date());
			dto.setcFrom("1");
			dto.setContent(appriseDesc);
			dto.setIsShow("1");
			dto.setScoreTaiDu(taiduScore);
			dto.setScoreZhunShi(zhunshiScore);
			dto.setScoreZhiLiang(zhiliangScore);
			dto.setMenId(m.getId());
			dto.setMenName(m.getRealName());
			dto.setWorkerId(worker.getId());
			dto.setWorkerName(worker.getRealName());
			dto.setOrderId(orderId);

			orderDTO.setOrderStatus("6");
			try {
				//保存评价并更新订单状态
				myAppraiseFacade.saveAppraiseAanUpdateOrder(dto,orderDTO);
				PrintWriter out = response.getWriter();
				out.print("success");
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
				PrintWriter out = response.getWriter();
				out.print("error");
				out.close();
			}
		}
		return null;
    }


}