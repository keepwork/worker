package com.common.tag.view;

import java.util.List;

public class SelectViewTLD extends ListViewTLD {

	public String formartResult() {

		StringBuffer sb = new StringBuffer();
		sb.append("\n<select " + getHtmlAttributes() + ">");
		List<ViewEntry> list = super.listViewEntries();
		for (ViewEntry entry : list) {
			sb.append("<option value=\"" + entry.getValue() + "\" ");
			if (entry.getValue() != null && getValue() != null
					&& entry.getValue().equals(getValue())) {
				sb.append(" selected=\"selected\" ");
			}
			sb.append(">" + entry.getLable() + "</option>");
		}
		sb.append("</select>\n");
		return sb.toString();
	}

	private static final long serialVersionUID = -304011642474663949L;

}
