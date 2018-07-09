package com.common.tag.domain;

import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import com.sinovatech.bms.domain.model.dto.TBmsDomainvalueDTO;

/**
 * 树控件:根节点和分割长度必须设定一个，如根节点为‘001’，则显示结果为根据step显示树的级别, 如果制定levelSplit则系统根据level分割
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 28, 2015 12:54:43 PM
 */
public class TreeDomainTag extends IDomainTag {
	
	/**
	 * 树类型：单选、多选
	 */
	private String type;

	/**
	 * 根节点编号
	 */
	private String rootid;

	/**
	 * 显示树级别，从根节点为1级
	 */

	private int step;

	/**
	 * 级别分割长度
	 */
	private int levelSplit;

	private String[] values = null;

	public String formartResult() {
		// Map dm = (Map) pageContext.getServletContext().getAttribute(
		// BmsConsts.APPLICATION_DOMAIN_CACHE);
		// List dovs = (List) dm.get(this.getDomain());
		List dovs = super.listCachedDomain(this.getDomain());

		super.getCachedDomain(this.getDomain());

		if (!StringUtils.isBlank((String) getValue())) {
			this.values = ((String) getValue()).split("\\|");
		}

		if (dovs == null) {
			return "";
		}
		StringBuffer sb = new StringBuffer();

		sb.append("<style type=\"text/css\">");
		sb.append(".domainTree_c{");
		sb.append("border:0; padding:0; margin:0;");
		sb.append("}");
		sb.append(".domainTree_c *{");
		sb.append("border:0; padding:0; margin:0;");
		sb.append("}");
		sb.append(".domainTree_c b{");
		sb.append("font-size:14px; color:blue;");
		sb.append("}");
		sb.append("</style>");
		sb.append("<script type=\"text/javascript\">");
		sb.append("function domainTreeSH_(id)");
		sb.append("{");
		sb.append("var node = document.getElementById(id);");
		sb.append("node.style.display = node.style.display=='none'?'block':'none';");
		sb.append("}");
		sb.append("</script>");

		sb.append("<div class=\"domainTree_c\">");
		sb.append(this.rendarTree(this.rootid, dovs, 0));
		sb.append("</div>");

		return sb.toString();
	}

	private String rendarTree(String start, List source, int step) {
		// 级别
		if (step == this.step)
			return null;

		StringBuffer sb = new StringBuffer();
		List list = this.filterList(start, source, this.levelSplit);

		if (list.size() == 0) {
			return null;
		} else {
			for (Iterator it = list.iterator(); it.hasNext();) {
				TBmsDomainvalueDTO dv = (TBmsDomainvalueDTO) it.next();
				String str = rendarTree(dv.getLevel(), source, (step + 1));
				sb.append("<div  style=\"padding-left:" + step * 20 + ";\">");
				if (str == null) {
					sb.append("<div onMouseOver=\"this.style.backgroundColor='#fdecae';\" onMouseOut=\"this.style.backgroundColor='#fff';\">");
					sb.append("<b>&nbsp;</b><input type=\"" + this.type
							+ "\" name=\"" + getName() + "\" value=\""
							+ dv.getValue() + "\" "
							+ (checked(dv.getValue()) ? "checked" : "") + " />"
							+ dv.getLabel());
					sb.append("</div>");
				} else {
					sb.append("<div onClick=\"domainTreeSH_('d_"
							+ dv.getId()
							+ "')\" onMouseOver=\"this.style.backgroundColor='#fdecae';\" onMouseOut=\"this.style.backgroundColor='#fff';\">");
					sb.append("<b>+</b><input type=\"" + this.type
							+ "\" name=\"" + getName() + "\"  value=\""
							+ dv.getValue() + "\" "
							+ (checked(dv.getValue()) ? "checked" : "") + " />"
							+ dv.getLabel());
					sb.append("</div>");

					sb.append("<div id = \"d_" + dv.getId()
							+ "\" style=\"display:none;\">");
					sb.append(str);
					sb.append("</div>");
				}
				sb.append("</div>");
			}
		}
		return sb.toString();
	}

	private boolean checked(String v) {
		if (values == null)
			return false;
		for (int i = 0; i < values.length; i++) {
			if (v != null && v.equals(values[i])) {
				return true;
			}
		}

		return false;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the rootid
	 */
	public String getRootid() {
		return rootid;
	}

	/**
	 * @param rootid
	 *            the rootid to set
	 */
	public void setRootid(String rootid) {
		if (!StringUtils.isBlank(rootid)) {
			this.rootid = rootid;
			this.levelSplit = rootid.length();
		}
	}

	/**
	 * @return the step
	 */
	public int getStep() {
		return step;
	}

	/**
	 * @param step
	 *            the step to set
	 */
	public void setStep(int step) {
		this.step = step;
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
