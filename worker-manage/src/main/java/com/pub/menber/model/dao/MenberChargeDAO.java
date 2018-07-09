package com.pub.menber.model.dao;

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

import com.pub.menber.model.dto.MenberChargeDTO;
import com.sinovatech.common.model.dao.DaoSupport;
import com.sinovatech.common.web.limit.LimitInfo;

/**
 * 充值DAO
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 28, 2015 12:36:07 PM
 */
public class MenberChargeDAO extends DaoSupport
{
	public void save(MenberChargeDTO dto)
	{
		this.getHibernateTemplate().save(dto);
	}
	
	public MenberChargeDTO get(java.lang.String id)
	{
		String hql = "from MenberChargeDTO Z where Z.id=?";
		List list = this.getHibernateTemplate().find(hql, id);
		if (list.size() == 1)
			return (MenberChargeDTO) list.get(0);
		else
			return null;
	}

	public void delete(MenberChargeDTO dto)
	{
		this.getHibernateTemplate().delete(dto);
	}

	public void deleteAll(Collection collection)
	{
		this.getHibernateTemplate().deleteAll(collection);
	}

	public void update(MenberChargeDTO dto)
	{
		this.getHibernateTemplate().update(dto);
	}

	public void saveOrUpdate(MenberChargeDTO dto)
	{
		this.getHibernateTemplate().saveOrUpdate(dto);
	}

	public List list(LimitInfo limit)
	{
		//limit.addFilterProperty(HqlProperty.getEq("status", "1"));
		String hql = "from MenberChargeDTO Z ";
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
			String totalHQl = "from MenberChargeDTO Z";
			limit.setTotalNum(this.countHql(
					totalHQl + " where 1=1 " + param[0], (Map) param[1]));

			return list;
		}
	}

	public List listByIds(String codes)
	{
		return this.getHibernateTemplate().find("from MenberChargeDTO Z where Z.id in (" + codes + ")");
	}
	
	/**
	 * 检查会员充值是否已返回
	 * @param menId
	 * @param squeues
	 * @return
	 */
	public boolean checkMenberCharge(String menId,String squeues)
	{
		final String sql = "select t.MEN_ID from pub_menber_charge t where t.MEN_ID = '"+menId+"' and t.squeues = '"+squeues+"' ";
		Integer size = (Integer) this.getHibernateTemplate().execute(
		        new HibernateCallback() {
		            @Override
		            public Object doInHibernate(Session arg0)
		                    throws HibernateException, SQLException {
		                SQLQuery query = arg0.createSQLQuery(sql);
		                query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		                return query.list().size();
		            }
		        });
		if (size>0){
			return false;
		}else{
			return true;
		}
	}
	
	
	
	/**
	 * 分页查询
	 * 
	 * @param params
	 * @return
	 */
	public Map<String,Object> listForPagination(Map<String,String> params){
		Map<String,Object> map = new HashMap<String,Object>();
		
		String menId = params.get("menId");
		String where = "";
		if(null != menId && !menId.equals("")){
			where = " where t.MEN_ID = '"+menId+"' ";
		}
		
		map.put("list", getPageList(params,where));
		map.put("rows", getPageCount(params,where));
		return map;
    }
	public List<MenberChargeDTO> getPageList(Map<String,String> params,String where)
	{
		final String sql = "select t.ID,t.MEN_ID,t.PRICE,t.CREATE_TIME from pub_menber_charge t  " + where + 
        		" order by t.CREATE_TIME desc " +
        		" LIMIT "+params.get("start")+","+params.get("size")+" ";
        
        List<MenberChargeDTO> result = new ArrayList<MenberChargeDTO>();
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
            MenberChargeDTO temp = new MenberChargeDTO();
            temp.setId((String) map.get("ID"));
            temp.setMenId((String) map.get("MEN_ID"));
            temp.setCreateTime((Date) map.get("CREATE_TIME"));
            temp.setPrice((BigDecimal) map.get("PRICE"));
            result.add(temp);
        }
        return result;
	}
	public int getPageCount(Map<String,String> params,String where)
	{
		final String sql = "select t.ID from pub_menber_charge t " + where ;
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