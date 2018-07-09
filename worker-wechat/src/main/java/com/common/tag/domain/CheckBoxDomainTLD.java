package com.common.tag.domain;

import java.util.Iterator;
import org.apache.commons.lang.ArrayUtils;
import com.sinovatech.bms.domain.model.dto.TBmsDomainvalueDTO;
import com.sinovatech.common.util.StringUtils;

/**
 * 显示域值
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 28, 2015 12:52:40 PM
 */
public class CheckBoxDomainTLD extends IDomainTag {
	
	private static final long serialVersionUID = 7554748803013432498L;

	public String formartResult() {
		String exvalue = (String) getExValue(getValue());

		// 默认值
		boolean def = !StringUtils.isBlank(exvalue);

		String[] vs = null;

		if (def) {
			vs = exvalue.split("\\|");
		}

		separator = StringUtils.isBlank(separator) ? "&nbsp;&nbsp;" : separator;

		StringBuffer sb = new StringBuffer();

		Iterator it = null;
		if (this.levelSplit > 0) {
			it = this.filterIterator(this.parentLevel, levelSplit);
		} else {
			it = getCachedDomain(getDomain());
		}

		while (it.hasNext()) {
			TBmsDomainvalueDTO dto = (TBmsDomainvalueDTO) it.next();

			sb.append("<input type=\"checkbox\"");

			if (def && ArrayUtils.contains(vs, dto.getValue())) {
				sb.append(" checked=\"checked\" ");
			}

			sb.append(" value=\"" + dto.getValue() + "\" "
					+ getHtmlAttributes() + " />" + dto.getLabel() + separator);
		}

		return sb.toString();
	}

	private String separator;

	public String getSeparator() {
		return separator;
	}

	public void setSeparator(String separator) {
		this.separator = separator;
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
