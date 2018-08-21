package com.weixin.util;

import net.sf.json.JSONObject;
import org.apache.log4j.Logger;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.*;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

public class CommonUtil {

	private static Logger log = Logger.getLogger(CommonUtil.class);

	/**
	 * 发送https请求
	 *
	 * @param requestUrl 请求地址
	 * @param requestMethod 请求方式（GET、POST）
	 * @param //outputStr 提交的数据
	 * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
	 */
	public static JSONObject httpsRequest(String requestUrl,
										  String requestMethod) {
		JSONObject jsonObject = null;
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			//TrustManager[] tm = { new MyX509TrustManager() };
			//SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			//sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			//SSLSocketFactory ssf = sslContext.getSocketFactory();

			//URL url = new URL(requestUrl);
			URL url = new URL(null, requestUrl,new sun.net.www.protocol.https.Handler());
			sun.net.www.protocol.https.HttpsURLConnectionImpl conn = (sun.net.www.protocol.https.HttpsURLConnectionImpl) url.openConnection();
			//conn.setSSLSocketFactory(ssf);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			conn.setRequestMethod(requestMethod);
			conn.setRequestProperty("content-type","application/x-www-form-urlencoded");

			// 从输入流读取返回内容
			InputStream inputStream = conn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String str = null;
			StringBuffer buffer = new StringBuffer();
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			// 释放资源
			bufferedReader.close();
			inputStreamReader.close();
			inputStream.close();
			inputStream = null;
			conn.disconnect();

//			System.out.println("============JSON============================");
//			System.out.println(buffer.toString());
//			System.out.println("============JSON============================");

			jsonObject = JSONObject.fromObject(buffer.toString());
		} catch (ConnectException ce) {
			System.out.println("连接超时：{}" + ce);
		} catch (Exception e) {
			System.out.println("https请求异常：{}" + e);
		}
		return jsonObject;
	}


	/**
	 * 发送https请求
	 *
	 * @param requestUrl 请求地址
	 * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
	 */
	public static byte[] httpsRequestImage(String requestUrl) {
		try {
			URL url = new URL(null, requestUrl,new sun.net.www.protocol.https.Handler());
			sun.net.www.protocol.https.HttpsURLConnectionImpl conn = (sun.net.www.protocol.https.HttpsURLConnectionImpl) url.openConnection();
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("content-type","image/jpg");

			// 从输入流读取返回内容
			InputStream inputStream = conn.getInputStream();
			byte[] btImg = readInputStream(inputStream);//得到图片的二进制数据

			inputStream.close();
			inputStream = null;
			conn.disconnect();

//			System.out.println("============btImg============================");
//			System.out.println(btImg.toString());
//			System.out.println("============btImg============================");

			return btImg;

		} catch (ConnectException ce) {
			System.out.println("连接超时：{}" + ce);
		} catch (Exception e) {
			System.out.println("https请求异常：{}" + e);
		}
		return null;
	}


	/**
	 * 发送http请求
	 *
	 * @param requestUrl 请求地址
	 * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
	 */
	public static byte[] httpRequestImage(String requestUrl) {
		try {
			URL url = new URL(null, requestUrl,new sun.net.www.protocol.http.Handler());
			sun.net.www.protocol.http.HttpURLConnection conn = (sun.net.www.protocol.http.HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("content-type","image/jpg");

			// 从输入流读取返回内容
			InputStream inputStream = conn.getInputStream();
			byte[] btImg = readInputStream(inputStream);//得到图片的二进制数据

			inputStream.close();
			inputStream = null;
			conn.disconnect();

//			System.out.println("============btImg============================");
//			System.out.println(btImg.toString());
//			System.out.println("============btImg============================");

			return btImg;

		} catch (ConnectException ce) {
			System.out.println("连接超时：{}" + ce);
		} catch (Exception e) {
			System.out.println("https请求异常：{}" + e);
		}
		return null;
	}

	/**
	 * 从输入流中获取数据
	 * @param inStream 输入流
	 * @return
	 * @throws Exception
	 */
	public static byte[] readInputStream(InputStream inStream) throws Exception{
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while( (len=inStream.read(buffer)) != -1 ){
			outStream.write(buffer, 0, len);
		}
		inStream.close();
		return outStream.toByteArray();
	}





	/**
	 * 发送https请求
	 *
	 * @param requestUrl 请求地址
	 * @param requestMethod 请求方式（GET、POST）
	 * @param //outputStr 提交的数据
	 * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
	 */
	public static JSONObject httpsRequest(String requestUrl,
										  String requestMethod , String param) {
		JSONObject jsonObject = null;
		try {
			URL url = new URL(null, requestUrl,new sun.net.www.protocol.https.Handler());
			sun.net.www.protocol.https.HttpsURLConnectionImpl conn = (sun.net.www.protocol.https.HttpsURLConnectionImpl) url.openConnection();
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			conn.setRequestMethod(requestMethod);
			conn.setRequestProperty("content-type","application/x-www-form-urlencoded");

			DataOutputStream out = new DataOutputStream(conn.getOutputStream());
			if (param != null) {
				out.writeBytes(param);
			}

			// 从输入流读取返回内容
			InputStream inputStream = conn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String str = null;
			StringBuffer buffer = new StringBuffer();
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			// 释放资源
			bufferedReader.close();
			inputStreamReader.close();
			inputStream.close();
			inputStream = null;
			conn.disconnect();

//			System.out.println("============JSON============================");
//			System.out.println(buffer.toString());
//			System.out.println("============JSON============================");

			jsonObject = JSONObject.fromObject(buffer.toString());
		} catch (ConnectException ce) {
			System.out.println("连接超时：{}" + ce);
		} catch (Exception e) {
			System.out.println("https请求异常：{}" + e);
		}
		return jsonObject;
	}


	/**
	 * 发送https请求
	 *
	 * @param requestUrl 请求地址
	 * @param requestMethod 请求方式（GET、POST）
	 * @param //outputStr 提交的数据
	 * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
	 */
	public static JSONObject httpsRequestJSON(String requestUrl,String requestMethod , String paramJSON) {
		JSONObject jsonObject = null;
		try {
			URL url = new URL(null, requestUrl,new sun.net.www.protocol.https.Handler());
			sun.net.www.protocol.https.HttpsURLConnectionImpl conn = (sun.net.www.protocol.https.HttpsURLConnectionImpl) url.openConnection();
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			conn.setRequestMethod(requestMethod);
			conn.setRequestProperty("content-type","application/json");

			DataOutputStream out = new DataOutputStream(conn.getOutputStream());
			if (paramJSON != null) {
				out.writeBytes(paramJSON);
			}

			// 从输入流读取返回内容
			InputStream inputStream = conn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String str = null;
			StringBuffer buffer = new StringBuffer();
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			// 释放资源
			bufferedReader.close();
			inputStreamReader.close();
			inputStream.close();
			inputStream = null;
			conn.disconnect();

//			System.out.println("============JSON============================");
//			System.out.println(buffer.toString());
//			System.out.println("============JSON============================");

			jsonObject = JSONObject.fromObject(buffer.toString());
		} catch (ConnectException ce) {
			System.out.println("连接超时：{}" + ce);
		} catch (Exception e) {
			System.out.println("https请求异常：{}" + e);
		}
		return jsonObject;
	}




	/**
	 * 发送http请求
	 *
	 * @param requestUrl 请求地址
	 * @param requestMethod 请求方式（GET、POST）
	 * @param //outputStr 提交的数据
	 * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
	 */
	public static JSONObject httpRequest(String requestUrl,String requestMethod) {
		JSONObject jsonObject = null;
		try {
			URL url = new URL(null, requestUrl);
			URLConnection connection = url.openConnection();
			HttpURLConnection conn = (HttpURLConnection)connection;
			// 设置请求方式（GET/POST）
			conn.setRequestMethod(requestMethod);
			conn.setRequestProperty("Accept-Charset", "utf-8");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

			// 从输入流读取返回内容
			InputStream inputStream = conn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String str = null;
			StringBuffer buffer = new StringBuffer();
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			// 释放资源
			bufferedReader.close();
			inputStreamReader.close();
			inputStream.close();
			inputStream = null;
			conn.disconnect();

//			System.out.println("============JSON============================");
//			System.out.println(buffer.toString());
//			System.out.println("============JSON============================");

			jsonObject = JSONObject.fromObject(buffer.toString());
		} catch (ConnectException ce) {
			System.out.println("连接超时：{}" + ce);
		} catch (Exception e) {
			System.out.println("http请求异常：{}" + e);
		}
		return jsonObject;
	}


	/**
	 *
	 * @param requestUrl     接口地址
	 * @param requestMethod  请求方法：POST、GET...
	 * @param output         接口入参
	 * @param needCert       是否需要数字证书
	 * @return
	 */
	private static StringBuffer httpsRequest(String requestUrl, String requestMethod, String output,boolean needCert)
			throws NoSuchAlgorithmException, NoSuchProviderException, KeyManagementException, MalformedURLException,
			IOException, ProtocolException, UnsupportedEncodingException {


		URL url = new URL(requestUrl);
		HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

		//是否需要数字证书
		//if(needCert){
		//设置数字证书
		//	setCert(connection);
		//}
		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setUseCaches(false);
		connection.setRequestMethod(requestMethod);
		if (null != output) {
			OutputStream outputStream = connection.getOutputStream();
			outputStream.write(output.getBytes("UTF-8"));
			outputStream.close();
		}

		// 从输入流读取返回内容
		InputStream inputStream = connection.getInputStream();
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		String str = null;
		StringBuffer buffer = new StringBuffer();
		while ((str = bufferedReader.readLine()) != null) {
			buffer.append(str);
		}

		bufferedReader.close();
		inputStreamReader.close();
		inputStream.close();
		inputStream = null;
		connection.disconnect();
		return buffer;
	}

	/**
	 * 如果返回JSON数据包,转换为 JSONObject
	 * @param requestUrl
	 * @param requestMethod
	 * @param outputStr
	 * @param needCert
	 * @return
	 */
	public static JSONObject httpsRequestToJsonObject(String requestUrl, String requestMethod, String outputStr,boolean needCert) {
//		  log.info("========================= CommonUtil httpsRequestToJsonObject ");
		JSONObject jsonObject = null;
		try {
			StringBuffer buffer = httpsRequest(requestUrl, requestMethod, outputStr,needCert);
			jsonObject = JSONObject.fromObject(buffer.toString());
		} catch (ConnectException ce) {
			System.out.println("连接超时："+ce.getMessage());
		} catch (Exception e) {
			System.out.println("https请求异常："+e.getMessage());
		}

		return jsonObject;
	}
}
