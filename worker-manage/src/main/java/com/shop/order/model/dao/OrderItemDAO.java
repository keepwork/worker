package com.shop.order.model.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.shop.order.model.dto.OrderItemDTO;
import com.sinovatech.common.model.dao.DaoSupport;
import com.sinovatech.common.web.limit.LimitInfo;

/**
 * 订单明细DAO
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 28, 2015 12:36:07 PM
 */
public class OrderItemDAO extends DaoSupport
{
	public void save(OrderItemDTO dto)
	{
		this.getHibernateTemplate().save(dto);
	}

	public OrderItemDTO get(java.lang.String id)
	{
		String hql = "from OrderItemDTO Z where Z.id=?";
		List list = this.getHibernateTemplate().find(hql, id);
		if (list.size() == 1)
			return (OrderItemDTO) list.get(0);
		else
			return null;
	}

	public void delete(OrderItemDTO dto)
	{
		this.getHibernateTemplate().delete(dto);
	}

	public void deleteAll(Collection collection)
	{
		this.getHibernateTemplate().deleteAll(collection);
	}

	public void update(OrderItemDTO dto)
	{
		this.getHibernateTemplate().update(dto);
	}

	public void saveOrUpdate(OrderItemDTO dto)
	{
		this.getHibernateTemplate().saveOrUpdate(dto);
	}

	public List list(LimitInfo limit)
	{
		//limit.addFilterProperty(HqlProperty.getEq("status", "1"));
		String hql = "from OrderItemDTO Z ";
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
			String totalHQl = "from OrderItemDTO Z";
			limit.setTotalNum(this.countHql(
					totalHQl + " where 1=1 " + param[0], (Map) param[1]));

			return list;
		}
	}

	public List listByIds(String id)
	{
		return this.getHibernateTemplate().find("from OrderItemDTO Z where Z.id in (" + id + ")");
	}
	
	@SuppressWarnings("unchecked")
	public List<OrderItemDTO> listByOrderId(String id)
	{
		return this.getHibernateTemplate().find("from OrderItemDTO Z where Z.orderId = '" + id + "'");
	}
	

}