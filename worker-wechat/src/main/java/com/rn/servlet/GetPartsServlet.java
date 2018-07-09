package com.rn.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.rn.dto.Good;
import com.rn.dto.GoodPic;
import com.shop.good.model.dto.GoodDTO;
import com.shop.good.model.dto.GoodPicDTO;
import com.shop.good.model.facade.GoodFacade;
import com.sinovatech.common.config.GlobalConfig;
import com.sinovatech.framework.common.helper.SpringBeanHelper;


/**
 * 获取配件列表
 * 
 * @author chenxin   keepwork512@163.com
 * @date 2015-06-28
 */
public class GetPartsServlet extends HttpServlet {

	private static Log log = LogFactory.getLog(GetPartsServlet.class);
	private GoodFacade myGoodFacade;
	private String prefix = GlobalConfig.getProperty("filePath", "prefix");
    
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
		log.info("获取配件列表....................................................GetPartsServlet");
		response.setContentType("text/html;charset=UTF-8"); 
		myGoodFacade = (GoodFacade) SpringBeanHelper.getBeanContext().getBean("myGoodFacade");
		
		String cateCode = request.getParameter("cateCode");
		if(null==cateCode || cateCode.equals("")){
			
		}
		
		try {
			List<GoodDTO> list = myGoodFacade.listByCateCode(cateCode);
			log.info("获取配件列表....................................................list:"+list.size());
			if(list.size()>0){
				List<Good> jsonList = transList(list);
				JSONArray jsonStr = JSONArray.fromObject(jsonList);
				response.getWriter().print(jsonStr);
			}else{
				response.getWriter().print("1111");//无数据
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
	public List<Good> transList(List<GoodDTO> list){
		List<Good> jsonList = new ArrayList();
		for (GoodDTO d : list) {
			Good g = new Good();
			g.setId(d.getId());
			g.setName(d.getName());
			g.setCode(d.getCode());
			g.setPrice(d.getPrice()+"");
			g.setCatCode(d.getCatCode());
			g.setCatName(d.getCatName());
			g.setStatus(d.getStatus()+"");
			g.setResultCode("0000");
			
			// 商品图片
	        List<GoodPicDTO> picList = this.myGoodFacade.listGoodImages(d.getId());
	        List<GoodPic> pics = new ArrayList<GoodPic>();
	        for (Iterator iterator = picList.iterator(); iterator.hasNext();) {
				GoodPicDTO dto = (GoodPicDTO) iterator.next();
				GoodPic pic = new GoodPic();
				pic.setPic(prefix+"/ProductIMG/"+dto.getPic());
				pic.setOrderNum(dto.getOrderNum());
				pics.add(pic);
			}
			g.setPicList(pics);
			
			jsonList.add(g);
		}
		return jsonList;
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
