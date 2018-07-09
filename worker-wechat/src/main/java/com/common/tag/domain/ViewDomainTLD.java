package com.common.tag.domain;

import java.io.IOException;
import java.util.List;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.sinovatech.bms.client.bean.IDomainValue;
import com.sinovatech.bms.domain.model.dto.TBmsDomainvalueDTO;
import com.sinovatech.common.util.JoinHelper;
import com.sinovatech.common.util.StringUtils;

/**
 * 显示域值
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 28, 2015 12:55:03 PM
 */
public class ViewDomainTLD extends IDomainTag {
	
	private static Log log = LogFactory.getLog(ViewDomainTLD.class);
	
	private String domain;
	
	private Object value;

	private String separator;

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

	public String formartResult() {
		String exvalue = (String) getExValue(value);
		if (!StringUtils.isBlank(exvalue)) {

			if (getCachedDomain(domain) == null) {
				log.error("Can'nt find domain: " + domain
						+ " in application domain scope.");
				return "";
			}
			List list = listCachedDomain(domain);

			if (exvalue.indexOf("|") != -1) {// 多个
				String[] ds = exvalue.split("\\|");
				String[] results = new String[ds.length];

				for (int i = 0; i < ds.length; i++) {
					IDomainValue dto = (IDomainValue) findByValue(list, ds[i]);
					if (dto != null)
						results[i] = dto.getLabel();
				}

				return JoinHelper.join(results,
						StringUtils.isBlank(separator) ? "," : separator);
			} else {
				TBmsDomainvalueDTO dto = findByValue(list, exvalue);
				if (dto != null)
					return dto.getLabel();
			}
		}
		return "";
	}

	private TBmsDomainvalueDTO findByValue(List list, String value) {
		java.util.Iterator iter = list.iterator();
		while (iter.hasNext()) {
			TBmsDomainvalueDTO dv = (TBmsDomainvalueDTO) iter.next();
			if (value.equals(dv.getValue()))
				return dv;
		}
		return null;
	}

	public String getSeparator() {
		return separator;
	}

	public void setSeparator(String separator) {
		this.separator = separator;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

}
