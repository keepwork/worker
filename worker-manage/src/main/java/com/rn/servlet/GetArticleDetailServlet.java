package com.rn.servlet;

import com.pub.article.model.dto.ArticleDTO;
import com.pub.article.model.facade.ArticleFacade;
import com.sinovatech.framework.common.helper.SpringBeanHelper;
import net.sf.json.JSONArray;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 根据文章ID获取文章详情
 *
 * @author kevin(keepwork512@163.com)
 * @date May 10, 2016 8:45:33 AM
 */
public class GetArticleDetailServlet extends HttpServlet {

	private static Log log = LogFactory.getLog(GetArticleDetailServlet.class);
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
		log.info("获取文章详情....................................................GetArticlesServlet");
		response.setContentType("text/html;charset=UTF-8"); 
		myArticleFacade = (ArticleFacade) SpringBeanHelper.getBeanContext().getBean("myArticleFacade");
		JSONArray jsonStr = null;
		
		String id = request.getParameter("id");
		log.info("获取文章详情....................................................id:"+id);
		try {
			ArticleDTO a = myArticleFacade.get(id);
			if(null != a){
				//a.setContent(HtmlStringUtil.htmlspecialchars(a.getContent()));
				log.info("获取文章详情....................................................a.content:"+a.getContent());
				jsonStr = JSONArray.fromObject(a);
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
