package com.common.tag.domain;

import java.util.Iterator;
import org.apache.commons.lang.ArrayUtils;
import com.sinovatech.bms.domain.model.dto.TBmsDomainvalueDTO;
import com.sinovatech.common.util.StringUtils;

/**
 * 显示域值
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 28, 2015 12:54:20 PM
 */
public class SelectDomainTLD extends IDomainTag {
	
	private static final long serialVersionUID = 1L;

	public String formartResult() {
		String exvalue = (String) getExValue(getValue());

		boolean def = !StringUtils.isBlank(exvalue);
		String[] vs = null;
		if (def) {
			vs = exvalue.split("\\|");
		}

		StringBuffer sb = new StringBuffer();

		sb.append("\n<select " + getHtmlAttributes() + ">");

		Iterator it = null;
		if (this.levelSplit > 0) {
			it = this.filterIterator(this.parentLevel, levelSplit);
		} else {
			it = getCachedDomain(getDomain());
		}

		while (it.hasNext()) {
			TBmsDomainvalueDTO dto = (TBmsDomainvalueDTO) it.next();
			sb.append("<option value=\"" + dto.getValue() + "\" ");

			if (def && ArrayUtils.contains(vs, dto.getValue())) {
				sb.append(" selected=\"selected\" ");
			}

			sb.append(">" + dto.getLabel() + "</option>");
		}

		sb.append("</select>\n");

		return sb.toString();
	}

	// 父节点级别编号:第一级可不设置
	private String parentLevel;

	// 级别分割长度
	private int levelSplit = -1;

	/**
	 * @return the parentLevel
	 */
	public String getParentLevel() {
		return parentLevel;
	}

	/**
	 * @param parentLevel
	 *            the parentLevel to set
	 */
	public void setParentLevel(String parentLevel) {
		this.parentLevel = parentLevel;
	}

	/**
	 * @return the levelSplit
	 */
	public int getLevelSplit() {
		return levelSplit;
	}

	/**
	 * @param levelSplit
	 *            the levelSplit to set
	 */
	public void setLevelSplit(int levelSplit) {
		this.levelSplit = levelSplit;
	}
}
