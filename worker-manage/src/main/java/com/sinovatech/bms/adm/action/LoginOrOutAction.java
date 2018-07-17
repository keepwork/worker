package com.sinovatech.bms.adm.action;

import com.sinovatech.bms.adm.model.dto.TBmsUserDTO;
import com.sinovatech.bms.adm.model.facade.BmsLocationFacade;
import com.sinovatech.bms.adm.model.facade.BmsRscFuncFacade;
import com.sinovatech.bms.adm.model.facade.BmsUserFacade;
import com.sinovatech.bms.adm.model.facade.SessionUserRegister;
import com.sinovatech.bms.util.LogOperator;
import com.sinovatech.bms.util.PubMethod;
import com.sinovatech.captcha.CaptchaTimeoutException;
import com.sinovatech.captcha.service.DefaultCaptchaService;
import com.sinovatech.common.config.GlobalConfig;
import com.sinovatech.common.exception.AppException;
import com.sinovatech.common.util.DateUtil;
import com.sinovatech.common.util.JoinHelper;
import com.sinovatech.common.web.action.BaseAdmAction;
import com.sinovatech.common.web.action.CommonMapping;
import com.sinovatech.common.web.limit.LimitInfo;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 登录与退出
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 28, 2015 12:12:46 PM
 */
public class LoginOrOutAction extends BaseAdmAction
{
  private BmsUserFacade userFacade;
  private SessionUserRegister userRegister;
  private BmsLocationFacade myBmsLocationFacade;
  private BmsRscFuncFacade myBmsRscFuncFacade;

  public ActionForward login(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    String identify = GlobalConfig.getProperty("bms", "identify");
    Boolean UseIdentify = Boolean.valueOf(Boolean.parseBoolean(identify));
//    String yzm = request.getParameter("yzm");
    String userName = "admin";
    //String userName = "test3";
    request.setAttribute("userName", userName);
    String passWord = "test123";
    
    String k = (StringUtils.isBlank(GlobalConfig.getProperty("bms", 
    "sysusercreateKey"))) ? 
    "123456565643450987657689876543267676787651234567" : 
    GlobalConfig.getProperty("bms", "sysusercreateKey");
    passWord = com.sinovatech.common.util.Des.encrytWithBase64("DESede", passWord, k);
    TBmsUserDTO user = this.userFacade.validateUser(userName, passWord);
    if (user == null)
    {
      request.setAttribute("msgs", "用户名或密码错误!");
      return mapping.findForward("login");
    }
    
    if (PubMethod.verifyStr(userName)) {
      request.setAttribute("msgs", "用户名不合法!");
      //记录登录日志
  	  LogOperator.saveLoginLog(user.getId(),"login","0","用户名不合法!");
      return mapping.findForward("login");
    }
    if (PubMethod.verifyStr(passWord)) {
      request.setAttribute("msgs", "密码不合法!");
      //记录登录日志
  	  LogOperator.saveLoginLog(user.getId(),"login","0","密码不合法!");
      return mapping.findForward("login");
    }

//    if ((StringUtils.isBlank(yzm)) && (UseIdentify.booleanValue()))
//    {
//      request.setAttribute("msgs", "请输入验证码!");
//      //记录登录日志
//  	  LogOperator.saveLoginLog(user.getId(),"login","0","请输入验证码!");
//      return mapping.findForward("login");
//    }

//    String yzmN = (String)getSessionAttribute(request, "RAND_IMAGE_VALIDATE_");
//    DefaultCaptchaService captchaService = new DefaultCaptchaService();
//    captchaService.setReq(request.getSession());
//    try
//    {
//      if ((!(captchaService.verifyCaptcha(yzm))) && (UseIdentify.booleanValue()))
//      {
//        request.setAttribute("msgs", "验证码输入错误!");
//        //记录登录日志
//    	LogOperator.saveLoginLog(user.getId(),"login","0","验证码输入错误!");
//        return mapping.findForward("login");
//      }
//    }
//    catch (CaptchaTimeoutException e) {
//      request.setAttribute("msgs", "验证码已过期!");
//      //记录登录日志
//  	  LogOperator.saveLoginLog(user.getId(),"login","0","验证码已过期!");
//      return mapping.findForward("login");
//    }

//    String sameUser = GlobalConfig.getProperty("bms", "sameUserLogin");
//    if (sameUser.equals("false")) {
//      HttpSession session = this.userRegister.getSessionByUid(user.getId());
//      if (session != null) {
//        session.removeAttribute("loginUser");
//        session.invalidate();
//      }
//    }
    if (!(user.isAvaliable()))
    {
      request.setAttribute("msgs", "用户状态不正确!");
      //记录登录日志
  	  LogOperator.saveLoginLog(user.getId(),"login","0","用户状态不正确!");
      return mapping.findForward("login");
    }

    String ipAddr = getIpAddr(request);
    if (subIpAddr(user, ipAddr)) {
      request.getSession().invalidate();
      //记录登录日志
  	  LogOperator.saveLoginLog(user.getId(),"login","0","无效IP!");
      return mapping.findForward("subIpAddr");
    }

    user.setUserLoginDate(DateUtil.format(new Date(), DateUtil.yyyyMMddHHmmSpt));
    user.setIpAddr(ipAddr);
    user.setSessionId(request.getSession().getId());

    getSession(request).setAttribute("loginUser", user);

    if (this.userFacade.roleUserType(user)) {
      getSession(request).setAttribute(GlobalConfig.getProperty("bms", "sessionUserType"), "true");
    }
    
    //记录登录日志
	LogOperator.saveLoginLog(user.getId(),"login","1","");
    
    return mapping.findForward("loginSuccess");
  }

  public ActionForward ssoLogin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    String bname = request.getParameter("bname");

    String bpwd = request.getParameter("bpwd");

    String appcode = request.getParameter("appcode");

    String urlType = request.getParameter("urlType");

    String targetUrl = request.getParameter("targetUrl");
    String key = GlobalConfig.getProperty("bms", "bms4a");
    String ssologin = GlobalConfig.getProperty("bms", "bms4alogin");
    com.sinovatech.bms.client.utils.Des des = new com.sinovatech.bms.client.utils.Des(key);
    if ((bname != null) && (!("".equals(bname)))) {
      bname = des.decrypt(bname);
    } else {
      response.sendRedirect(ssologin);
      return null;
    }
    if ((bpwd != null) && (!("".equals(bpwd)))) {
      bpwd = des.decrypt(bpwd);
    }
    if ((appcode != null) && (!("".equals(appcode)))) {
      appcode = des.decrypt(appcode);
    }
    if ((urlType != null) && (!("".equals(urlType)))) {
      urlType = des.decrypt(urlType);
    }
    if ((targetUrl != null) && (!("".equals(targetUrl)))) {
      targetUrl = des.decrypt(targetUrl);
    }
    TBmsUserDTO user = this.userFacade.findAppCodeUser(bname, appcode, bpwd);
    if (user == null)
    {
      response.sendRedirect(ssologin);
      return null;
    }

    if (!(user.isAvaliable()))
    {
      response.sendRedirect(ssologin);
      return null;
    }

    String ipAddr = getIpAddr(request);

    user.setUserLoginDate(DateUtil.format(new Date(), DateUtil.yyyyMMddHHmmSpt));
    user.setIpAddr(ipAddr);
    user.setSessionId(request.getSession().getId());

    TBmsUserDTO oldUser = (TBmsUserDTO)getSession(request).getAttribute("loginUser");

    getSession(request).setAttribute("loginUser", user);

    if (this.userFacade.roleUserType(user)) {
      getSession(request).setAttribute(GlobalConfig.getProperty("bms", "sessionUserType"), "true");
    }
//    saveAuditLog(user, "login");

    if (isSuperman(request)) {
      user.setTbTBmsUserLocationsDTOList(this.myBmsLocationFacade
        .list(new LimitInfo()));

      user.setUserCanVisitLocationIds("'" + 
        JoinHelper.join(user.getTbTBmsUserLocationsDTOList(), 
        "','", "id") + 
        "'");
    }
    else
    {
      String sessionUserType = (String)getSession(request).getAttribute(GlobalConfig.getProperty("bms", "sessionUserType"));
      if ((sessionUserType != null) && (!("".equals(sessionUserType))) && ("true".equals(sessionUserType))){
//        user.setTbTBmsUserLocationsDTOList(this.myBmsLocationFacade.initTBmsUserLocationsDTO(user));
      } else {
        user.setTbTBmsUserLocationsDTOList(this.myBmsLocationFacade
          .listLocationsAssoWithUser(user.getId()));
      }

      user.setUserCanVisitLocationIds("'" + 
        JoinHelper.join(user.getTbTBmsUserLocationsDTOList(), 
        "','", "id") + 
        "'");

      List func = this.myBmsRscFuncFacade.listUserCanVisitRsc(user
        .getId());
      if (func.size() == 0) {
        CommonMapping mping = new CommonMapping(
          "alert(\"对不起，您没有进入系统的权限,请与管理员联系。\");top.location.href='" + 
          request.getContextPath() + "/'");
        request.setAttribute("mping", mping);
        return mapping.findForward("commonMapping");
      }

//      Map map = this.myBmsRscFuncFacade.listUserCanVisitRscResouce(func);

      List funcTree = this.myBmsRscFuncFacade.initAsTree(func);
      user.setCanvisitFuncTree(funcTree);

//      user.setUserCanVisitUriMap(map);
    }
    if ((urlType != null) && (urlType.equals("bmsRole"))) {
      request.getRequestDispatcher("/bms/adm/bmsrole/queryBmsRole.do").forward(request, response);
      return null;
    }
    if ((urlType != null) && (urlType.equals("bmsPage"))) {
      if (targetUrl != null) {
        request.getSession().setAttribute("targetUrl", targetUrl);
      }
    }
    else if (targetUrl != null) {
      response.sendRedirect(targetUrl);

      return null;
    }

    return mapping.findForward("loginSuccess");
  }

  private boolean subIpAddr(TBmsUserDTO dto, String ip)
  {
    boolean in = false;
    String ips = dto.getCode();
    if ((ips != null) && (!("".equals(ips.trim())))) {
      String[] ipCodes = ips.split("\n");
      String t = dto.getType();
      if ("1".equals(dto.getFlag())) {
        for (int i = 0; i < ipCodes.length; ++i) {
          if (("1".equals(t)) && (!(ipIsValid(ipCodes[i].trim(), ip)))) {
            in = true;
            break; }
          if (("2".equals(t)) && (ipIsValid(ipCodes[i].trim(), ip))) {
            in = true;
            break;
          }
        }
      }
    }
    return in;
  }

  public ActionForward logout(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws AppException
  {
//    String exitMap = GlobalConfig.getProperty("bms", "exitmap");
//    Map map = (Map)request.getSession().getAttribute(exitMap);
//    Object obj = request.getSession().getAttribute("loginUser");
//    if (obj != null) {
//      request.setAttribute("exitType", "exit");
//    }
//    request.setAttribute("list", map);
//    return mapping.findForward("exit");
	  
	//记录退出日志
	TBmsUserDTO user = getUser(request);
	LogOperator.saveLoginLog(user.getId(),"logout","1","");
    
    request.getSession().invalidate();
    return mapping.findForward("login");
  }

  public ActionForward exitPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws AppException
  {
    CommonMapping mping = new CommonMapping("parent.alert('此操作没有权限!');");
    request.setAttribute("mping", mping);
    return mapping.findForward("commonMapping");
  }

  public ActionForward exitBms(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws IOException
  {
    String exitType = request.getParameter("exitType");
    TBmsUserDTO user = (TBmsUserDTO)request.getSession().getAttribute("loginUser");
//    saveAuditLog(user, "logout");
    request.getSession().removeAttribute("loginUser");
    request.getSession().invalidate();
    request.setAttribute("exitType", exitType);
    return mapping.findForward("logout");
  }
 

  protected void init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    this.userFacade = ((BmsUserFacade)getBeanContext().getBean(
      "myBmsUserFacade"));
    this.userRegister = ((SessionUserRegister)getBeanContext().getBean("mySessionUserRegister"));
    this.myBmsLocationFacade = 
      ((BmsLocationFacade)getBeanContext()
      .getBean("myBmsLocationFacade"));
    this.myBmsRscFuncFacade = 
      ((BmsRscFuncFacade)getBeanContext()
      .getBean("myBmsRscFuncFacade"));
  }

  public static String getIpAddr(HttpServletRequest req) {
    String ip = req.getHeader("x-forwarded-for");
    if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
      ip = req.getHeader("X-Forwarded-For");
    }
    if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
      ip = req.getHeader("X-Real-IP");
    }
    if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
      ip = req.getHeader("Proxy-Client-IP");
    }
    if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
      ip = req.getHeader("WL-Proxy-Client-IP");
    }
    if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
      ip = req.getRemoteAddr();
    }
    if ((ip != null) && (ip.indexOf(",") != -1)) {
      String[] ips = ip.split(",");
      if ((ips.length > 0) && (!(ips[0].equalsIgnoreCase("unknown")))) {
        ip = ips[0];
      }
    }
    return ip;
  }

  public static boolean ipIsValid(String ipSection, String ip)
  {
    if (ipSection == null)
      throw new NullPointerException("IP段不能为空！");
    if (ip == null)
      throw new NullPointerException("IP不能为空！");
    ipSection = ipSection.trim();
    ip = ip.trim();
    String REGX_IP = "((25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|\\d)\\.){3}(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|\\d)";
    String REGX_IPB = "((25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|\\d)\\.){3}(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|\\d)\\-((25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|\\d)\\.){3}(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|\\d)";
    if ((!(ipSection.matches("((25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|\\d)\\.){3}(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|\\d)\\-((25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|\\d)\\.){3}(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|\\d)"))) || (!(ip.matches("((25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|\\d)\\.){3}(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|\\d)"))))
      return false;
    int idx = ipSection.indexOf(45);
    String[] sips = ipSection.substring(0, idx).split("\\.");
    String[] sipe = ipSection.substring(idx + 1).split("\\.");
    String[] sipt = ip.split("\\.");
    long ips = 0L; long ipe = 0L; long ipt = 0L;
    for (int i = 0; i < 4; ++i) {
      ips = ips << 8 | Integer.parseInt(sips[i]);
      ipe = ipe << 8 | Integer.parseInt(sipe[i]);
      ipt = ipt << 8 | Integer.parseInt(sipt[i]);
    }
    if (ips > ipe) {
      long t = ips;
      ips = ipe;
      ipe = t;
    }
    return ((ips <= ipt) && (ipt <= ipe));
  }
}