package com.pub.advert.model.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import com.sinovatech.common.web.limit.LimitInfo;
import java.util.Map;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.HibernateCallback;
import com.sinovatech.common.model.dao.DaoSupport;
import com.pub.advert.model.dto.AdvertDTO;

/**
 * 广告DAO
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 30, 2015 10:00:54 PM
 */
public class AdvertDAO extends DaoSupport
{
	public void save(AdvertDTO dto)
	{
		this.getHibernateTemplate().save(dto);
	}

	public AdvertDTO get(java.lang.String id)
	{
		String hql = "from AdvertDTO Z where Z.id=?";
		List list = this.getHibernateTemplate().find(hql, id);
		if (list.size() == 1)
			return (AdvertDTO) list.get(0);
		else
			return null;
	}

	public void delete(AdvertDTO dto)
	{
		this.getHibernateTemplate().delete(dto);
	}

	public void deleteAll(Collection collection)
	{
		this.getHibernateTemplate().deleteAll(collection);
	}

	public void update(AdvertDTO dto)
	{
		this.getHibernateTemplate().update(dto);
	}

	public void saveOrUpdate(AdvertDTO dto)
	{
		this.getHibernateTemplate().saveOrUpdate(dto);
	}

	public List list(LimitInfo limit)
	{
		//limit.addFilterProperty(HqlProperty.getEq("status", "1"));
		String hql = "from AdvertDTO Z ";
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
			String totalHQl = "from AdvertDTO Z";
			limit.setTotalNum(this.countHql(
					totalHQl + " where 1=1 " + param[0], (Map) param[1]));

			return list;
		}
	}

	
	public List listByIds(String ids)
	{
		return this.getHibernateTemplate().find("from AdvertDTO Z where Z.id in (" + ids + ")");
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
		
		String type = params.get("type");
		if(null != type && !type.equals("")){
			where += " and t.TYPE = '"+type+"' ";
		}
		
		String cate = params.get("cate");
		if(null != cate && !cate.equals("")){
			where += " and t.CATE = '"+cate+"' ";
		}
		
		map.put("list", getPageList(params,where));
		map.put("rows", getPageCount(params,where));
		return map;
    }
	public List<AdvertDTO> getPageList(Map<String,String> params,String where)
	{
		final String sql = "select t.ID,t.TITLE,t.PIC,t.TYPE,t.CATE,t.COUNT,t.CONTENT,t.POINT,t.CREATE_TIME from pub_advert t  " + where + 
        		" order by t.ORDER_NUM asc,t.CREATE_TIME desc " +
        		" LIMIT "+params.get("start")+","+params.get("size")+" ";
        
        List<AdvertDTO> result = new ArrayList<AdvertDTO>();
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
            AdvertDTO temp = new AdvertDTO();
            temp.setId((String) map.get("ID"));
            temp.setTitle((String) map.get("TITLE"));
            temp.setPic((String) map.get("PIC"));
            temp.setContent((String) map.get("CONTENT"));
            temp.setType((Integer) map.get("TYPE"));
            temp.setCate((Integer) map.get("CATE"));
            temp.setCount((Integer) map.get("COUNT"));
            temp.setPoint((Integer) map.get("POINT"));
            temp.setCreateTime((Date) map.get("CREATE_TIME"));
            result.add(temp);
        }
        return result;
	}
	public int getPageCount(Map<String,String> params,String where)
	{
		final String sql = "select t.ID from pub_advert t " + where ;
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