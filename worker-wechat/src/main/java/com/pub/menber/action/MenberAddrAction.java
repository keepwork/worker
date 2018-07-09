package com.pub.menber.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.common.util.ServletUtil;
import com.pub.menber.model.dto.MenberAddrDTO;
import com.pub.menber.model.dto.MenberDTO;
import com.pub.menber.model.facade.MenberAddrFacade;
import com.sinovatech.common.util.Validate;
import com.sinovatech.common.web.action.ActionConstent;
import com.sinovatech.common.web.action.BaseAdmAction;
import com.sinovatech.common.web.action.CommonMapping;
import com.sinovatech.common.web.limit.ExLimitUtil;
import com.sinovatech.common.web.limit.ILimitUtil;
import com.sinovatech.common.web.limit.LimitInfo;

/**
 * 配送地址管理
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 30, 2015 9:55:43 PM
 */
public class MenberAddrAction extends BaseAdmAction
{ 
	private MenberAddrFacade myMenberAddrFacade;
	public void init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		this.myMenberAddrFacade = (MenberAddrFacade) this.getBeanContext().getBean("myMenberAddrFacade");
	}
	
	/**
	 * 添加、修改发货地址 -  前台
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveAddress(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		String type = request.getParameter("type");//类型：wap,web
		MenberDTO menber = null;
		if(type.equals("wap")){
			menber = (MenberDTO)request.getSession().getAttribute("wxmenber");
    	}else if(type.equals("web")){
    		menber = (MenberDTO)request.getSession().getAttribute("pcmenber");
    	}
        if(null != menber)
        {
        	String consignee = request.getParameter("consignee");
        	String mobile = request.getParameter("mobile");	
        	String province = request.getParameter("province");
        	String city = request.getParameter("city");
        	String county = request.getParameter("county");
        	String street = request.getParameter("street");
        	
        	// 判断是保存还是修改
            String act = request.getParameter("act");
            if ("add".equals(act))
            {
            	MenberAddrDTO address = new MenberAddrDTO(); 
            	address.setMenId(menber.getId());
            	address.setConsignee(consignee);
            	address.setMobile(mobile);
            	address.setProvince(province);
            	address.setCity(city);
            	address.setCounty(county);
            	address.setStreet(street);
            	myMenberAddrFacade.save(address);
            }
            else if ("update".equals(act))
            {
                // 修改送货地址
                String areaId = request.getParameter("areaId");
                MenberAddrDTO addr = myMenberAddrFacade.get(areaId);
                addr.setId(areaId);
                addr.setMenId(addr.getMenId());
                addr.setConsignee(consignee);
                addr.setMobile(mobile);
                addr.setProvince(province);
                addr.setCity(city);
                addr.setCounty(county);
                addr.setStreet(street);
                myMenberAddrFacade.update(addr);
            }
            else if ("del".equals(act))
            {
                // 删除送货地址
                String areaId = request.getParameter("areaId");
                myMenberAddrFacade.delete(areaId);
            }
            
            // 返回数据
            List<MenberAddrDTO> addressList = myMenberAddrFacade.listByMenId(menber.getId());
            StringBuilder sb = new StringBuilder();
        	int i = 0;
        	//sb.append("{\"returnData\":[");
            for (MenberAddrDTO addr : addressList)
            {
                if (i > 0)
                {
                    sb.append(",");
                }
                sb.append("{");
                sb.append("\"id\":\"" + addr.getId() + "\",");
                sb.append("\"consignee\":\"" + addr.getConsignee() + "\",");
                sb.append("\"mobile\":\"" + addr.getMobile() + "\",");
                sb.append("\"province\":\"" + addr.getProvince() + "\",");
                sb.append("\"city\":\"" + addr.getCity() + "\",");
                sb.append("\"county\":\"" + addr.getCounty() + "\",");
                sb.append("\"street\":\"" + addr.getStreet() + "\"");
                sb.append("}");
                i++;
            }
            //sb.append("]}");
            
            try{
                ServletUtil.outputXML(response, sb.toString());
            }catch (IOException e){
                e.printStackTrace();
            }
        }
		return null;
    }


}