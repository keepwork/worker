package com.shop.order.model.dao;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.shop.order.model.dto.OrderDTO;
import com.sinovatech.common.model.dao.DaoSupport;
import com.sinovatech.common.web.limit.LimitInfo;

/**
 * 订单DAO
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 28, 2015 12:36:07 PM
 */
public class OrderDAO extends DaoSupport
{
	public void save(OrderDTO dto)
	{
		this.getHibernateTemplate().save(dto);
	}

	public OrderDTO get(java.lang.String id)
	{
		String hql = "from OrderDTO Z "
				+" left join fetch Z.tbTBmsLocationDTO B  where Z.orderId=?";
		List list = this.getHibernateTemplate().find(hql, id);
		if (list.size() == 1)
			return (OrderDTO) list.get(0);
		else
			return null;
	}
	
	public OrderDTO getOrderBySN(java.lang.String sn)
	{
		String hql = "from OrderDTO Z where Z.orderSn=?";
		List list = this.getHibernateTemplate().find(hql, sn);
		if (list.size() == 1)
			return (OrderDTO) list.get(0);
		else
			return null;
	}

	public void delete(OrderDTO dto)
	{
		this.getHibernateTemplate().delete(dto);
	}

	public void deleteAll(Collection collection)
	{
		this.getHibernateTemplate().deleteAll(collection);
	}

	public void update(OrderDTO dto)
	{
		this.getHibernateTemplate().update(dto);
	}

	public void saveOrUpdate(OrderDTO dto)
	{
		this.getHibernateTemplate().saveOrUpdate(dto);
	}

	public List list(LimitInfo limit)
	{
		//limit.addFilterProperty(HqlProperty.getEq("status", "1"));
		String hql = "from OrderDTO Z "
				+ "left join fetch Z.tbTBmsLocationDTO B  ";
		Object[] param = limit.getWhereHQL("Z");
		if (limit.getRowDisplayed() < 1)
		{// 非分页, 每页记录小于1时表示不进行分页
			return this.listWithNamePrams(hql + " where 1=1 " + param[0] + " "
					+ limit.getOrder("Z"), (Map) param[1]);
		} else
		{// 分页查询
			// 查询记录
			List list = this.listWithLimit(hql + " where 1=1 " + param[0] + " "
					+ limit.getOrder("Z"), (Map) param[1], limit
					.getStartLineNum(), limit.getRowDisplayed());
			// 查询并设置记录总数
			String totalHQl = "from OrderDTO Z";
			limit.setTotalNum(this.countHql(
					totalHQl + " where 1=1 " + param[0], (Map) param[1]));

			return list;
		}
	}

	public List listByIds(String ids)
	{
		return this.getHibernateTemplate().find("from OrderDTO Z where Z.orderId in (" + ids + ")");
	}
	
	
	public List listByMenId(String merId)
	{
		return this.getHibernateTemplate().find("from OrderDTO Z where Z.menId = '" + merId + "'");
	}
	
	
	public List listByWorkerId(String workerId)
	{
		return this.getHibernateTemplate().find("from OrderDTO Z where Z.workerId = '" + workerId + "'");
	}
	
	
	public List listByStatus(String status)
	{
		return this.getHibernateTemplate().find("from OrderDTO Z where Z.orderStatus = '" + status + "'");
	}
	
	
	
	
	/**
	 * 分页查询
	 * 
	 * @param params
	 * @return
	 */
	public Map<String,Object> listForPagination(Map<String,String> params){
		Map<String,Object> map = new HashMap<String,Object>();
		String where = " where 1=1 ";

		String orderStatus = params.get("orderStatus");
		if(null != orderStatus && !orderStatus.equals("")){
			where += " and t.ORDER_STATUS = '"+orderStatus+"' ";
		}

		String status = params.get("status");

		if(null != status && !status.equals("")){
			if (status.equals("1")) {
				where += " and t.ORDER_STATUS in('1','2','3','4') ";//进行中状态
			} else if (status.equals("2")) {
				where += " and t.ORDER_STATUS = '5' and t.PAY_STATUS = '0' ";//待支付（已完成施工且未支付）
			} else if (status.equals("3")) {
				where += " and t.ORDER_STATUS = '5' and t.PAY_STATUS = '1' ";//待评价（已完成施工且已支付）
			}else if (status.equals("4")) {
				where += " and t.ORDER_STATUS in('6','7') ";//已完成（包括已评价和已取消）
			}
		}

		String shippingStatus = params.get("shippingStatus");
		if(null != shippingStatus && !shippingStatus.equals("")){
			where += " and t.SHIPPING_STATUS = '"+shippingStatus+"' ";
		}

		String payStatus = params.get("payStatus");
		if(null != payStatus && !payStatus.equals("")){
			where += " and t.PAY_STATUS = '"+payStatus+"' ";
		}

		String payType = params.get("payType");
		if(null != payType && !payType.equals("")){
			where += " and t.PAY_TYPE = '"+payType+"' ";
		}

		String orderType = params.get("orderType");
		if(null != orderType && !orderType.equals("")){
			where += " and t.ORDER_TYPE = '"+orderType+"' ";
		}

		String menId = params.get("menId");
		if(null != menId && !menId.equals("")){
			where += " and t.MEN_ID = '"+menId+"' ";
		}

		String workerId = params.get("workerId");
		if(null != workerId && !workerId.equals("")){
			where += " and t.WORKER_ID = '"+workerId+"' ";
		}

		map.put("list", getPageList(params,where));
		map.put("rows", getPageCount(params,where));
		return map;
	}
	public List<OrderDTO> getPageList(Map<String,String> params,String where)
	{
		final String sql = "select t.ORDER_ID,t.ORDER_SN,t.MEN_ID,t.USER_ID,t.OPEN_ID,t.ADDR_ID," +
				"t.INVOICE,t.TOTAL_PRICE,t.SHIPPING_PRICE,t.TOTAL_POINT,t.ORDER_STATUS,t.SHIPPING_STATUS," +
				"t.PAY_STATUS,t.PAY_TYPE,t.ORDER_TYPE,t.AMOUNT,t.ORDER_TIME,t.PAY_TIME,t.TAKE_TIME,t.SURE_TIME,t.END_TIME,t.SERVICE_TYPE,"+
				"t.FIRST_CATE_NAME,t.SECOND_CATE_NAME,t.DESC1,t.DESC2 from pub_order t  " + where +
				" order by t.ORDER_TIME  desc " +
				" LIMIT "+params.get("start")+","+params.get("size")+" ";

		List<OrderDTO> result = new ArrayList<OrderDTO>();
		List list = (List) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					@Override
					public Object doInHibernate(Session arg0)
							throws HibernateException, SQLException {
						SQLQuery query = arg0.createSQLQuery(sql);
						query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
						return query.list();
					}
				});
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Map map = (Map) iterator.next();
			OrderDTO temp = new OrderDTO();
			temp.setOrderId((String) map.get("ORDER_ID"));
			temp.setOrderSn((String) map.get("ORDER_SN"));
			temp.setMenId((String) map.get("MEN_ID"));
			temp.setUserId((String) map.get("USER_ID"));
			temp.setOpenId((String) map.get("OPEN_ID"));
			temp.setAddrId((String) map.get("ADDR_ID"));
			temp.setInvoice((String) map.get("INVOICE"));
			String totalPrice = (String) map.get("TOTAL_PRICE");
			temp.setTotalPrice(new BigDecimal(totalPrice));
//            temp.setShippingPrice((BigDecimal) map.get("SHIPPING_PRICE"));
			temp.setTotalPoint((Integer) map.get("TOTAL_POINT"));
			temp.setOrderStatus((String) map.get("ORDER_STATUS"));
			temp.setShippingStatus((String) map.get("SHIPPING_STATUS"));
			temp.setPayStatus((String) map.get("PAY_STATUS"));
			temp.setPayType((String) map.get("PAY_TYPE"));
			temp.setOrderType((String) map.get("ORDER_TYPE"));
			temp.setAmount((Integer) map.get("AMOUNT"));
			temp.setOrderTime((Date) map.get("ORDER_TIME"));
			temp.setPayTime((Date) map.get("PAY_TIME"));
			temp.setTakeTime((Date) map.get("TAKE_TIME"));
			temp.setSureTime((Date) map.get("SURE_TIME"));
			temp.setEndTime((Date) map.get("END_TIME"));
			temp.setServiceType((String) map.get("SERVICE_TYPE"));
			temp.setFirstCateName((String) map.get("FIRST_CATE_NAME"));
			temp.setSecondCateName((String) map.get("SECOND_CATE_NAME"));
			temp.setDesc1((String) map.get("DESC1"));
			temp.setDesc2((String) map.get("DESC2"));
			result.add(temp);
		}
		return result;
	}
	public int getPageCount(Map<String,String> params,String where)
	{
		final String sql = "select t.ORDER_ID from pub_order t " + where ;
		List list = (List) this.getHibernateTemplate().execute(
		        new HibernateCallback() {
		            @Override
		            public Object doInHibernate(Session arg0)
		                    throws HibernateException, SQLException {
		                SQLQuery query = arg0.createSQLQuery(sql);
		                return query.list();
		            }
		        });
		return list.size();
	}
	
	

}