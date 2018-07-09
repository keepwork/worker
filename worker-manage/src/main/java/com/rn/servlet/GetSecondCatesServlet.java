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
import com.shop.good.model.dto.GoodCategoryDTO;
import com.shop.good.model.facade.GoodCategoryFacade;
import com.sinovatech.framework.common.helper.SpringBeanHelper;


/**
 * 获取产品二级分类列表
 *
 * @author kevin(keepwork512@163.com)
 * @date Jul 23, 2016 4:04:31 PM
 */
public class GetSecondCatesServlet extends HttpServlet {

	private static Log log = LogFactory.getLog(GetSecondCatesServlet.class);
	private GoodCategoryFacade myGoodCategoryFacade;
    
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
		log.info("获取产品二级分类列表....................................................GetSecondCatesServlet");
		response.setContentType("text/html;charset=UTF-8"); 
		myGoodCategoryFacade = (GoodCategoryFacade) SpringBeanHelper.getBeanContext().getBean("myGoodCategoryFacade");
		JSONArray jsonStr = null;
		
		String parentCode = request.getParameter("parentCode");
		try {
			List<GoodCategoryDTO> list = myGoodCategoryFacade.listByParent(parentCode);
			log.info("获取产品二级分类列表....................................................list:"+list.size());
			if(list.size()>0){
				jsonStr = JSONArray.fromObject(list);
			}else{
				GoodCategoryDTO ww = new GoodCategoryDTO();
				ww.setResultCode("1111");//无数据
				jsonStr = JSONArray.fromObject(ww);
			}
			response.getWriter().print(jsonStr);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			GoodCategoryDTO ww = new GoodCategoryDTO();
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
