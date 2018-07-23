package com.shop.order.model.facade;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import net.sf.json.JSONObject;
import com.baidu.yun.push.exception.PushClientException;
import com.baidu.yun.push.exception.PushServerException;
import com.baidu.yun.push.model.PushMsgToAllResponse;
import com.baidu.yun.push.test.BaiDuMsg;
import com.common.util.string.StringUtil;
import com.shop.order.model.bpo.OrderBPO;
import com.shop.order.model.dto.OrderDTO;
import com.sinovatech.common.config.GlobalConfig;
import com.sinovatech.common.exception.AppException;
import com.sinovatech.common.web.limit.HqlProperty;
import com.sinovatech.common.web.limit.LimitInfo;

/**
 * 维护OrderDTO的门面类, 使用单例模式，请勿设置实例化属性
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 28, 2015 12:43:45 PM
 */
public class OrderFacade { 
	
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static Logger log = Logger.getLogger(OrderFacade.class);
	private OrderBPO myOrderBPO;
	
	public String save(OrderDTO dto) throws AppException {
		return myOrderBPO.saveTX(dto);
	}

	
	public void update(OrderDTO dto) {
		myOrderBPO.updateTX(dto);
	}
	
	public void saveOrUpdate(OrderDTO dto) throws AppException {
		myOrderBPO.saveOrUpdateTX(dto);
	}

	
	public void delete(String ids) throws AppException {
		myOrderBPO.deleteTX(ids);
	}

	
	public OrderDTO get(java.lang.String id){
		return myOrderBPO.get(id);
	}
	
	public OrderDTO getOrderBySN(java.lang.String sn){
		return myOrderBPO.getOrderBySN(sn);
	}


	public List list(LimitInfo limit) throws AppException {
		return myOrderBPO.list(limit);
	}

	
	public List listByIds(String ids) throws AppException {
		return myOrderBPO.listByIds(ids);
	}
	
	public List listByMenId(String merId) throws AppException {
		return myOrderBPO.listByMenId(merId);
	}
	
	public List listByWorkerId(String workerId) throws AppException {
		return myOrderBPO.listByWorkerId(workerId);
	}
	
	public List listByStatus(String status) throws AppException {
		return myOrderBPO.listByStatus(status);
	}

	public OrderBPO getMyOrderBPO() {
		return myOrderBPO;
	}
	public void setMyOrderBPO(OrderBPO myOrderBPO) {
		this.myOrderBPO = myOrderBPO;
	}


	/**
	 * 查询过滤条件
	 * @param order
	 * @param limit
	 * @return
	 * @throws ParseException
	 */
	public LimitInfo dtoFilterProperty(OrderDTO order, LimitInfo limit)
		throws ParseException {
		if (!StringUtil.stringVerify(order.getOrderSn())) {
			limit.addFilterProperty(HqlProperty.getEq("orderSn",order.getOrderSn()));
		}
		if (!StringUtil.stringVerify(order.getOrderStatus())) {
			limit.addFilterProperty(HqlProperty.getEq("orderStatus",order.getOrderStatus()));
		}
		if (!StringUtil.stringVerify(order.getPayType())) {
			limit.addFilterProperty(HqlProperty.getEq("payType",order.getPayType()));
		}
		if (!StringUtil.stringVerify(order.getPayStatus())) {
			limit.addFilterProperty(HqlProperty.getEq("payStatus",order.getPayStatus()));
		}
		if (!StringUtil.stringVerify(order.getShippingStatus())) {
			limit.addFilterProperty(HqlProperty.getEq("shippingStatus",order.getShippingStatus()));
		}
		if (!StringUtil.stringVerify(order.getBeginTime())) {
			limit.addFilterProperty(HqlProperty.getCompare("orderTime", null,order.getBeginTime()));
		}
		if (!StringUtil.stringVerify(order.getEndTime())) {
			limit.addFilterProperty(HqlProperty.getCompare("orderTime", order.getEndTime(), null));
		}
		if (!StringUtil.stringVerify(order.getMenId())) {
			limit.addFilterProperty(HqlProperty.getEq("menId",order.getMenId()));
		}
		if (!StringUtil.stringVerify(order.getWorkerId())) {
			limit.addFilterProperty(HqlProperty.getEq("workerId",order.getWorkerId()));
		}
		if (null != order.getTbTBmsLocationDTO() && !StringUtil.stringVerify(order.getTbTBmsLocationDTO().getId())) {
			limit.addFilterProperty(HqlProperty.getEq("tbTBmsLocationDTO.id",order.getTbTBmsLocationDTO().getId()));
		}
		return limit;
	}
	
	
	public Map<String,Object> listForPagination(Map<String,String> params){
		return myOrderBPO.listForPagination(params);
    }
	
	/**
	 * 将订单消息发送给所有安装工列队 android
	 * @param order
	 */
	public void sendBaiduMessageAndroid(OrderDTO order,String addr){
		
    	JSONObject o = new JSONObject();
	    o.put("title", "惠达订单");//通知标题，可以为空；如果为空则设为appid对应的应用名; 
	    o.put("description","测试新订单");//通知文本内容，不能为空; 
	    o.put("notification_builder_id", 0);//android客户端自定义通知样式，如果没有设置默认为0; 
	    o.put("notification_basic_style", 4);//只有notification_builder_id为0时有效，可以设置通知的基本样式包括(响铃：0x04;振动：0x02;可清除：0x01;),这是一个flag整形，每一位代表一种样式,如果想选择任意两种或三种通知样式，notification_basic_style的值即为对应样式数值相加后的值。
	    o.put("open_type", 2);//点击通知后的行为(1：打开Url; 2：自定义行为；);
	    //o.put("url", "http://push.baidu.com");//需要打开的Url地址，open_type为1时才有效; 
	    o.put("pkg_content", "");//open_type为2时才有效，Android端SDK会把pkg_content字符串转换成Android Intent,通过该Intent打开对应app组件，所以pkg_content字符串格式必须遵循Intent uri格式，最简单的方法可以通过Intent方法toURI()获取
	    //自定义内容，key-value
	    JSONObject custorm = new JSONObject();
	    custorm.put("order_id", order.getOrderId()); 
//	    custorm.put("order_sn", order.getOrderSn()); 
//	    custorm.put("order_status", order.getOrderStatus()); 
//	    custorm.put("order_time", format.format(order.getOrderTime())); 
//	    custorm.put("order_desc", order.getOrderDesc()); 
//	    custorm.put("order_price", order.getTotalPrice()); 
//	    custorm.put("order_addr", addr); 
//	    custorm.put("service_type", order.getServiceType()); 
//	    
//	    String domain = GlobalConfig.getProperty("weixin", "server.domain");
//	    String service_image = "http://"+domain+"/wap/images/service/";
//	    if(order.getServiceType().equals("1")){
//	    	service_image += "az.png";
//	    }else if(order.getServiceType().equals("2")){
//	    	service_image += "wx.png";
//	    }else if(order.getServiceType().equals("3")){
//	    	service_image += "by.png";
//	    }else if(order.getServiceType().equals("4")){
//	    	service_image += "cl.png";
//	    }else if(order.getServiceType().equals("5")){
//	    	service_image += "zx.png";
//	    }
//	    custorm.put("service_image", service_image); 
//	    
//	    custorm.put("first_cate", order.getFirstCate()); 
//	    custorm.put("first_cate_name", order.getFirstCateName()); 
//	    custorm.put("second_cate", order.getSecondCate()); 
//	    custorm.put("second_cate_name", order.getSecondCateName()); 
	    o.put("custom_content", custorm);//自定义内容，键值对，Json对象形式(可选)；在android客户端，这些键值对将以Intent中的extra进行传递。 
	    log.info("sendBaiduMessageAndroid============ json: " + o.toString());
		try {
			PushMsgToAllResponse resp = BaiDuMsg.PushMsgToAllAndroid(o,3);
			// Http请求返回值解析
			log.info("sendBaiduMessageAndroid============ 返回 msgId: " + resp.getMsgId() + ",sendTime: " + resp.getSendTime() + ",timerId: "+ resp.getTimerId());
			
		} catch (PushClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PushServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 将订单消息发送给所有安装工列队 ios
	 * @param order
	 */
	public void sendBaiduMessageIOS(OrderDTO order,String addr){
		JSONObject o = new JSONObject();
		JSONObject json1 = new JSONObject();
		json1.put("alert", "订单消息"); //其内容可以为字符串或者字典，如果是字符串，那么将会在通知中显示这条内容;
		json1.put("sound", ""); //指定通知展现时伴随的提醒音文件名。如果找不到指定的文件或者值为 default，那么默认的系统音将会被使用。如果为空，那么将没有声音;
		json1.put("badge", "0"); //其值为数字，表示当通知到达设备时，应用的角标变为多少。如果没有使用这个字段，那么应用的角标将不会改变。设置为 0 时，会清除应用的角标;
	    o.put("aps", json1);
	    
	    //自定义内容，key-value
	    o.put("order_id", order.getOrderId()); 
//	    o.put("order_sn", order.getOrderSn()); 
//	    o.put("order_status", order.getOrderStatus()); 
//	    o.put("order_time", format.format(order.getOrderTime())); 
//	    o.put("order_desc", order.getOrderDesc()); 
//	    o.put("order_price", order.getTotalPrice()); 
//	    o.put("order_addr", addr); 
//	    o.put("service_type", order.getServiceType()); 
//	    
//	    String domain = GlobalConfig.getProperty("weixin", "server.domain");
//	    String service_image = "http://"+domain+"/wap/images/service/";
//	    if(order.getServiceType().equals("1")){
//	    	service_image += "az.png";
//	    }else if(order.getServiceType().equals("2")){
//	    	service_image += "wx.png";
//	    }else if(order.getServiceType().equals("3")){
//	    	service_image += "by.png";
//	    }else if(order.getServiceType().equals("4")){
//	    	service_image += "cl.png";
//	    }else if(order.getServiceType().equals("5")){
//	    	service_image += "zx.png";
//	    }
//	    o.put("service_image", service_image); 
//	    
//	    o.put("first_cate", order.getFirstCate()); 
//	    o.put("first_cate_name", order.getFirstCateName()); 
//	    o.put("second_cate", order.getSecondCate()); 
//	    o.put("second_cate_name", order.getSecondCateName()); 
	    log.info("sendBaiduMessageIOS============ json: " + o.toString());
		try {
			PushMsgToAllResponse resp = BaiDuMsg.PushMsgToAllIOS(o,4);
			// Http请求返回值解析
			log.info("sendBaiduMessageIOS============ 返回 msgId: " + resp.getMsgId() + ",sendTime: " + resp.getSendTime() + ",timerId: "+ resp.getTimerId());
			
		} catch (PushClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PushServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public String getprojectProgress(OrderDTO m){
		String projectProgress = "0";//项目进度
		if("4".equals(m.getOrderStatus())){//已上门，施工进行中
			Integer totalCycle = m.getCycleInit()+(m.getCycleAdd()==null?0:m.getCycleAdd());
			long totalUseTime =  totalCycle*24*3600000;//总工期毫秒数
			long usedTime =new Date().getTime()-m.getActualTime().getTime();//已使用工期毫秒数
			String r = ((double) usedTime/totalUseTime)*100 + "";
			projectProgress = new BigDecimal(r).setScale(0, BigDecimal.ROUND_HALF_UP).toString();
		}else if("5".equals(m.getOrderStatus())){//已完成施工
			projectProgress = "100";
		}
		return projectProgress;
	}

	public int getWorkerOrderTotalNum(String workerId) throws AppException {
		return myOrderBPO.getWorkerOrderTotalNum(workerId);
	}
	
}