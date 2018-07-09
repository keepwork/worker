package com.shop.good;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.shop.good.model.facade.GoodFacade;

/** 
 * @author  chenxin
 * @date    2015-08-06 
 */
public class UploadProductImagesServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//System.out.println("===========================================================机型上传终端图片开始");
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		
		boolean isMultipart = ServletFileUpload.isMultipartContent(req);
		if (!isMultipart) {
			System.out.println(">> This wasn't a file upload request!");
			return;
		}
		
		ApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(req.getSession().getServletContext());
		GoodFacade myGoodFacade= (GoodFacade) ac.getBean("myGoodFacade");
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String filePath = this.getServletContext().getRealPath("/ProductIMG/");
		String fileNameStr = formatter.format(new Date()); 
		String goodId = req.getParameter("goodId");//商品id
		
		PrintWriter out = resp.getWriter();
		FileItemFactory factory = new DiskFileItemFactory();  
		ServletFileUpload upload = new ServletFileUpload(factory);  

		// save upload file to disk  
		try {
			List<FileItem> items = upload.parseRequest(req);
			String fileName = null;
			File savefile = null;
			for (FileItem item : items) 
			{
				if (!item.isFormField()) {
					// 确定是文件而不是一个普通的表单字段  
					fileName = item.getName();
					String prefix=fileName.substring(fileName.lastIndexOf(".")+1);
					String prefileName = fileName.substring(0, fileName.lastIndexOf("."));
					prefileName = converterToSpell(prefileName);
					fileName = prefileName + "_" + fileNameStr +"."+prefix;
					System.out.println("================上传图片名： " + fileName);
					//String type = item.getName().split("\\.")[1];//获取文件类型  
					savefile = new File(filePath + "/" + fileName);
					
					//保存图片到文件夹
					item.write(savefile);
					
					//保存路径到数据库
					myGoodFacade.pictureMltUpload(fileName,goodId);
					
					//System.out.println(">> [save] " + savefile.getAbsolutePath());

					// to client info  
					out.print("fileId=" + savefile.getAbsolutePath());
					out.flush();
					System.out.println("================上传成功");
					//resp.sendRedirect("/pub/good/beforeEdit.do?id="+goodId);
				}
			}
		      
		} catch (Exception e) {
			System.out.println(">> " + e.getMessage());
			throw new IOException(e.getMessage());
		}
		//System.out.println("===========================================================机型上传终端图片结束");
	}

	/** 
	 * Return the WEB-INF directory. 
	 * @return 
	 */
	private String getBaseDir() {
		return this.getServletContext().getRealPath("/WEB-INF");
	}
	
	/** 
	    * 汉字转换位汉语拼音，英文字符不变 
	    * @param chines 汉字 
	    * @return 拼音 
	    */  
	private static String converterToSpell(String chines){          
	        String pinyinName = "";  
	        char[] nameChar = chines.toCharArray();  
	        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();  
	        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);  
	        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);  
	        for (int i = 0; i < nameChar.length; i++) {  
	            if (nameChar[i] > 128) {  
	                try {  
	                    pinyinName += PinyinHelper.toHanyuPinyinStringArray(nameChar[i], defaultFormat)[0];  
	                } catch (BadHanyuPinyinOutputFormatCombination e) {  
	                    e.printStackTrace();  
	                }  
	            }else{  
	                pinyinName += nameChar[i];  
	            }  
	        }  
	        return pinyinName;  
	    }  
}