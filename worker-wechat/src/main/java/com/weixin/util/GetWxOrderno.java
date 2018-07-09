package com.weixin.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import com.weixin.http.HttpClientConnectionManager;


public class GetWxOrderno
{
  public static DefaultHttpClient httpclient;

  static
  {
    httpclient = new DefaultHttpClient();
    httpclient = (DefaultHttpClient)HttpClientConnectionManager.getSSLInstance(httpclient);
  }


  public static String getPayNo(String url,String xmlParam){
	  DefaultHttpClient client = new DefaultHttpClient();
	  client.getParams().setParameter(ClientPNames.ALLOW_CIRCULAR_REDIRECTS, true);
	  HttpPost httpost= HttpClientConnectionManager.getPostMethod(url);
	  String prepay_id = "";
     try {
		 httpost.setEntity(new StringEntity(xmlParam, "UTF-8"));
		 HttpResponse response = httpclient.execute(httpost);
	     String jsonStr = EntityUtils.toString(response.getEntity(), "UTF-8");
	     Map<String, Object> dataMap = new HashMap<String, Object>();
	     System.out.println("jsonStr:"+jsonStr);
	     
	    if(jsonStr.indexOf("FAIL")!=-1){
	    	return prepay_id;
	    }
	    Map map = doXMLParse(jsonStr);
	    String return_code  = (String) map.get("return_code");
	    prepay_id  = (String) map.get("prepay_id");
	    System.out.println("return_code:"+return_code);
	    System.out.println("prepay_id:"+prepay_id);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return prepay_id;
  }
  
  
  /*
   * 得到扫码用的二维码
   */
  public static String getPayNoPC(String url,String xmlParam){
	  DefaultHttpClient client = new DefaultHttpClient();
	  client.getParams().setParameter(ClientPNames.ALLOW_CIRCULAR_REDIRECTS, true);
	  HttpPost httpost= HttpClientConnectionManager.getPostMethod(url);
	  String code_url = "";
     try {
		 httpost.setEntity(new StringEntity(xmlParam, "UTF-8"));
		 HttpResponse response = httpclient.execute(httpost);
	     String jsonStr = EntityUtils.toString(response.getEntity(), "UTF-8");
	     System.out.println("jsonStr:"+jsonStr);
	   
	     Map map = doXMLParse(jsonStr);
	     String return_code  = (String) map.get("return_code");
	     String result_code  = (String) map.get("result_code");
	     if("SUCCESS".equals(return_code) && "SUCCESS".equals(result_code)){
	    	 code_url  = (String) map.get("code_url");
	     }else{
	    	 System.out.println("return_code="+return_code+",result_code="+result_code);
	     }
	     System.out.println("code_url:"+code_url);
	     
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return code_url;
  }
  
  
  
   /*public String getPayNo(String xmlParam){
	  String prepay_id = "";
	  String accessUrl = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	  JSONObject jsonObject = CommonUtil.httpsRequest(accessUrl,"POST",xmlParam);
	  if (null != jsonObject) {
			try {
				//这里获得的accessToken是一个特殊的网页授权access_token,与基础支持中的access_token（该access_token用于调用其他接口）不同。
				prepay_id = jsonObject.getString("prepay_id")+"";
			} catch (Exception e) {
				String errorCode = jsonObject.getString("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				System.out.println("getPayNo失败 errcode{},errMsg,errorCode:" + errorCode + ",errorMsg:" + errorMsg);
			}
	  }
	return prepay_id;
  }*/

  /*public static String getPayNo(RequestHandler reqHandler){
	  
	  String requestUrl = reqHandler.getRequestURL();
	  URL orderUrl = new URL("https://api.mch.weixin.qq.com/pay/unifiedorder");
	  HttpURLConnection conn = (HttpURLConnection) orderUrl.openConnection();
	  conn.setConnectTimeout(30000); // 设置连接主机超时（单位：毫秒)
	  conn.setReadTimeout(30000); // 设置从主机读取数据超时（单位：毫秒)
	  conn.setDoOutput(true); // post请求参数要放在http正文内，顾设置成true，默认是false
	  conn.setDoInput(true); // 设置是否从httpUrlConnection读入，默认情况下是true
	  conn.setUseCaches(false); // Post 请求不能使用缓存
	  // 设定传送的内容类型是可序列化的java对象(如果不设此项,在传送序列化对象时,当WEB服务默认的不是这种类型时可能抛java.io.EOFException)
	  conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
	  conn.setRequestMethod("POST");// 设定请求的方法为"POST"，默认是GET
	  conn.setRequestProperty("Content-Length",requestUrl.length()+"");
	  String encode = "utf-8";
	  OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream(), encode);
	  out.write(requestUrl.toString());
	  out.flush();
	  out.close();
	  String result = getOut(conn);
	  System.out.println("result=========返回的xml============="+result);
	  Map<String, String> orderMap = doXMLParse(result);
	  //Map<String, String> orderMap = XMLUtil.doXMLParse(result);
	  System.out.println("orderMap==========================="+orderMap);
	  //得到的预支付id
	  String prepay_id = orderMap.get("prepay_id");
	return prepay_id;
  }*/
  public static String getOut(HttpURLConnection conn) throws IOException{
      if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
          return null;
      }
      // 获取响应内容体
      BufferedReader in = new BufferedReader(new InputStreamReader(
              conn.getInputStream(), "UTF-8"));
      String line = "";
      StringBuffer strBuf = new StringBuffer();
      while ((line = in.readLine()) != null) {
          strBuf.append(line).append("\n");
      }
      in.close();
      return  strBuf.toString().trim();
}
  
  
  
  
  /**
	 * 解析xml,返回第一级元素键值对。如果第一级元素有子节点，则此节点的值是子节点的xml数据。
	 * @param strxml
	 * @return
	 * @throws JDOMException
	 * @throws IOException
	 */
	public static Map doXMLParse(String strxml) throws Exception {
		if(null == strxml || "".equals(strxml)) {
			return null;
		}
		
		Map m = new HashMap();
		InputStream in = String2Inputstream(strxml);
		SAXBuilder builder = new SAXBuilder();
		Document doc = builder.build(in);
		Element root = doc.getRootElement();
		List list = root.getChildren();
		Iterator it = list.iterator();
		while(it.hasNext()) {
			Element e = (Element) it.next();
			String k = e.getName();
			String v = "";
			List children = e.getChildren();
			if(children.isEmpty()) {
				v = e.getTextNormalize();
			} else {
				v = getChildrenText(children);
			}
			
			m.put(k, v);
		}
		
		//关闭流
		in.close();
		
		return m;
	}
	/**
	 * 获取子结点的xml
	 * @param children
	 * @return String
	 */
	public static String getChildrenText(List children) {
		StringBuffer sb = new StringBuffer();
		if(!children.isEmpty()) {
			Iterator it = children.iterator();
			while(it.hasNext()) {
				Element e = (Element) it.next();
				String name = e.getName();
				String value = e.getTextNormalize();
				List list = e.getChildren();
				sb.append("<" + name + ">");
				if(!list.isEmpty()) {
					sb.append(getChildrenText(list));
				}
				sb.append(value);
				sb.append("</" + name + ">");
			}
		}
		
		return sb.toString();
	}
  public static InputStream String2Inputstream(String str) {
		return new ByteArrayInputStream(str.getBytes());
	}
  
}