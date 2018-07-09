package com.rn.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
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
 * 根据文章分类获取文章列表
 * 
 * @author chenxin   keepwork512@163.com
 * @date 2015-06-28
 */
public class LoginServlet extends HttpServlet {

	private static Log log = LogFactory.getLog(LoginServlet.class);
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
		log.info("安装工登录接口....................................................LoginServlet");
		response.setContentType("text/html;charset=UTF-8"); 
		myMenberFacade = (MenberFacade) SpringBeanHelper.getBeanContext().getBean("myMenberFacade");
		JSONArray jsonStr = null;
		String result = "";
		Menber m = new Menber();
		
		String loginName = request.getParameter("loginName");
		String password = request.getParameter("password");
		log.info("安装工登录接口....................................................loginName:"+loginName+",password:"+password);
		
		if(null==loginName || loginName.equals("")){
			result = "1001";//登录名不能为空
		}
		if(null==password || password.equals("")){
			result = "1002";//密码不能为空 
		}
		
		try {
			if(result.equals(""))
			{
				String k = (StringUtils.isBlank(GlobalConfig.getProperty("bms", 
			      "sysusercreateKey"))) ? 
			      "123456565643450987657689876543267676787651234567" : 
			      GlobalConfig.getProperty("bms", "sysusercreateKey");
			    String pw = com.sinovatech.common.util.Des.encrytWithBase64("DESede", password, k);
			    MenberDTO menber = this.myMenberFacade.validateMenber(loginName, pw);
			    if (menber == null){
			    	response.getWriter().print("1003");//登录名或密码错误
			    }else{
			    	transData(m,menber);
			    	m.setResultCode("0000");//登录成功
			    	jsonStr = JSONArray.fromObject(m);
					response.getWriter().print(jsonStr);
			    }
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
