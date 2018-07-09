package com.rn.servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.pub.article.model.dto.ArticleDTO;
import com.pub.article.model.facade.ArticleFacade;
import com.sinovatech.framework.common.helper.SpringBeanHelper;


/**
 * 根据文章分类获取文章列表
 * 
 * @author chenxin   keepwork512@163.com
 * @date 2015-06-28
 */
public class GetArticlesServlet extends HttpServlet {

	private static Log log = LogFactory.getLog(GetArticlesServlet.class);
	private ArticleFacade myArticleFacade;
    
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
		log.info("获取文章列表....................................................GetArticlesServlet");
		response.setContentType("text/html;charset=UTF-8"); 
		myArticleFacade = (ArticleFacade) SpringBeanHelper.getBeanContext().getBean("myArticleFacade");
		JSONArray jsonStr = null;
		
		String cateCode = request.getParameter("cateCode");
		log.info("获取文章列表....................................................cateCode:"+cateCode);
		try {
			List<ArticleDTO> list = myArticleFacade.listByCateCode(cateCode);
			log.info("获取文章列表....................................................list:"+list.size());
			if(list.size()>0){
				jsonStr = JSONArray.fromObject(list);
			}else{
				ArticleDTO ww = new ArticleDTO();
				ww.setResultCode("1111");//无数据
				jsonStr = JSONArray.fromObject(ww);
			}
			response.getWriter().print(jsonStr);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ArticleDTO ww = new ArticleDTO();
			ww.setResultCode("9999");//系统异常
			jsonStr = JSONArray.fromObject(ww);
			response.getWriter().print(jsonStr);
			response.getWriter().flush();
			response.getWriter().close();
			e.printStackTrace();
		}
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
