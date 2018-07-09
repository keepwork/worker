package com.rn.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.pub.menber.model.dto.MenberDTO;
import com.pub.menber.model.facade.MenberFacade;
import com.rn.dto.Menber;
import com.sinovatech.bms.util.PubMethod;
import com.sinovatech.common.config.GlobalConfig;
import com.sinovatech.common.exception.AppException;
import com.sinovatech.framework.common.helper.SpringBeanHelper;


/**
 * 安装工注册接口
 * 
 * @author chenxin   keepwork512@163.com
 * @date 2015-06-28
 */
public class RegistServlet extends HttpServlet {

	private static Log log = LogFactory.getLog(RegistServlet.class);
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private MenberFacade myMenberFacade;
    
	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.info("安装工注册接口....................................................RegistServlet");
		response.setContentType("text/html;charset=UTF-8"); 
		myMenberFacade = (MenberFacade) SpringBeanHelper.getBeanContext().getBean("myMenberFacade");
		JSONArray jsonStr = null;
		String result = "";
		Menber m = new Menber();
		
		String loginName = request.getParameter("loginName");
		String password = request.getParameter("password");
		String realName = request.getParameter("realName");
		String areaCode = request.getParameter("areaCode");
		String serviceType = request.getParameter("serviceType");
		log.info("安装工注册接口....................................................loginName:"+loginName+",password:"+password+",areaCode:"+areaCode+",serviceType:"+serviceType+",realName:"+realName);
        
		if(null==loginName || loginName.equals("")){
			result = "1001";//登录名不能为空
		}else{
			MenberDTO men = this.myMenberFacade.findMenberByLoginName(loginName);
			if(null!=men){
				result = "1002";//登录名已经存在
			}
		}
		if(null==password || password.equals("")){
			result = "1003";//密码不能为空 
		}
		if(null==realName || realName.equals("")){
			result = "1004";//真实姓名不能为空
		}
		if(null==areaCode || areaCode.equals("")){
			result = "1005";//服务区域不能为空
		}
		if(null==serviceType || serviceType.equals("")){
			result = "1006";//服务类型不能为空
		}
		
		try {
			if(result.equals(""))
			{
				String k = (StringUtils.isBlank(GlobalConfig.getProperty("bms", 
			      "sysusercreateKey"))) ? 
			      "123456565643450987657689876543267676787651234567" : 
			      GlobalConfig.getProperty("bms", "sysusercreateKey");
			    String pw = com.sinovatech.common.util.Des.encrytWithBase64("DESede", password, k);
			    
			    MenberDTO menber = new MenberDTO();
			    menber.setLoginName(loginName);
			    menber.setPassword(pw);
			    menber.setRegTime(new Date());
			    menber.setStatus(0);//正常
			    menber.setType(2);
			    menber.setMobile(loginName);
			    menber.setRealName(realName);
			    menber.setAreaCode(areaCode);
			    serviceType = serviceType.replaceAll("_", ",");
			    menber.setServiceType(serviceType);
			    menber.setPid("");
			    menber.setEmail("");
			    menber.setPoint(0);
			    menber.setSex("");
			    menber.setCity("");
			    menber.setBalanceFee(new BigDecimal(0.00));
				this.myMenberFacade.save(menber);
				
				m.setResultCode("0000");//注册成功
				transData(m,menber);
				jsonStr = JSONArray.fromObject(m);
				response.getWriter().print(jsonStr);
			}else{
				response.getWriter().print(result);
			}
			
			response.getWriter().flush();
			response.getWriter().close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			response.getWriter().print("9999");//系统异常
			response.getWriter().flush();
			response.getWriter().close();
			e.printStackTrace();
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public void transData(Menber m,MenberDTO menber) throws AppException{
		m.setId(menber.getId());
		m.setLoginName(menber.getLoginName());
		m.setEmail(menber.getEmail());
		m.setRealName(menber.getRealName());
		m.setAreaCode(menber.getAreaCode());
		m.setMobile(menber.getMobile());
		m.setPid(menber.getPid());
		m.setPoint(menber.getPoint()+"");
		m.setType(menber.getType()+"");
		m.setStatus(menber.getStatus()+"");
		m.setServiceType(menber.getServiceType());
		m.setBalanceFee(menber.getBalanceFee() + "");
		m.setRegTime(format.format(menber.getRegTime()));
		m.setResultCode("0000");
	}
	

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
