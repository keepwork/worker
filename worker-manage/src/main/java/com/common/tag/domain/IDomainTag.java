package com.common.tag.domain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import org.apache.commons.lang.StringUtils;
import com.sinovatech.bms.client.auth.IBmsService;
import com.sinovatech.bms.domain.model.dto.TBmsDomainvalueDTO;
import com.sinovatech.common.web.taglib.IHTMLControlTLD;
import com.sinovatech.hd.tools.cache.CacheFactory;
import com.sinovatech.hd.tools.cache.ICache;

/**
 * 字典标签
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 28, 2015 12:53:21 PM
 */
public abstract class IDomainTag extends IHTMLControlTLD {

	private IBmsService bmsService;
	
	private static ICache cache = CacheFactory.newCache();

	protected void initParas() {
//		if (bmsService == null)
//			bmsService = ComponentLoaderFactory.getCurrentComponentLoader()
//					.getBmsService();
	}

	@Override
	public int doStartTag() throws JspException {
		initParas();
		return super.doStartTag();
	}

	public void setBmsService(IBmsService bmsService) {
		this.bmsService = bmsService;
	}

	public int doEndTag() throws JspException {
		String result = formartResult();
		if (result != null) {
			JspWriter out = pageContext.getOut();
			try {
				out.print(result);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return SKIP_BODY;
	}

	/**
	 * 
	 * <p>
	 * <b>业务处理描述</b>
	 * <ul>
	 * <li>返回一个域列表</li>
	 * </ul>
	 * </p>
	 * 
	 * @param domain
	 * @return
	 */
	public Iterator getCachedDomain(String domain) {
		return listCachedDomain(domain).iterator();
	}

	public List listCachedDomain(String domain) {

		// Map dm = (Map) pageContext.getServletContext().getAttribute(
		// APPLICATION_DOMAIN_CACHE);
		// List a = (List) dm.get(domain);

		//List a = bmsService.listDomainValue(domain);
		List a = (List) cache.get(domain);

		if (a == null) {
			throw new RuntimeException("加载系统中不存在名称为:" + domain + "的域.");
		}

		return a;
	}

	/**
	 * <p>
	 * <ul>
	 * <li>过滤目标级下的字级</li>
	 * </ul>
	 * </p>
	 * 
	 * @param start
	 *            父级别编号
	 * @param source
	 *            数据源
	 * @param levelSplit
	 *            级别分割字符串长度
	 * 
	 * @return
	 */
	public List filterList(String start, List source, int levelSplit) {
		List list = new ArrayList();

		if (!StringUtils.isBlank(start)) {
			int length = start.length() + levelSplit;

			for (Iterator it = source.iterator(); it.hasNext();) {
				TBmsDomainvalueDTO dv = (TBmsDomainvalueDTO) it.next();
				String domainLevel = dv.getLevel();
				if (domainLevel != null && domainLevel.length() == length
						&& domainLevel.startsWith(start)) {
					list.add(dv);
				}
			}
		} else {
			for (Iterator it = source.iterator(); it.hasNext();) {
				TBmsDomainvalueDTO dv = (TBmsDomainvalueDTO) it.next();
				String domainLevel = dv.getLevel();
				if (domainLevel != null && domainLevel.length() == levelSplit) {
					list.add(dv);
				}
			}
		}

		return list;
	}

	/**
	 * <p>
	 * <ul>
	 * <li>过滤后的迭代器</li>
	 * </ul>
	 * </p>
	 * 
	 * @param start
	 * @param source
	 * @param levelSplit
	 * @return
	 */
	public Iterator filterIterator(String start, int levelSplit) {
		List a = listCachedDomain(domain);
		return this.filterList(start, a, levelSplit).iterator();
	}

	/**
	 * <p>
	 * <b>业务处理描述</b>
	 * <ul>
	 * <li>输出</li>
	 * </ul>
	 * </p>
	 * 
	 * @return
	 */
	public abstract String formartResult();

	private String domain;

	private Object value;

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}
}
