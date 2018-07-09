package com.common.tag.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.apache.commons.beanutils.PropertyUtils;

import com.sinovatech.common.web.taglib.IHTMLControlTLD;

public abstract class ListViewTLD extends IHTMLControlTLD {

	private Object items = null;

	private String valueProperty = null;

	private String displayProperty = null;
	
	private Object value=null;

	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	public  abstract String formartResult() ;
	public int doEndTag() throws JspException
	{
		String result = formartResult();
		if (result != null)
		{
			JspWriter out = pageContext.getOut();
			try
			{
				out.print(result);
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		return SKIP_BODY;
	}
	
	public List listViewEntries() {
		List<ViewEntry> list = new ArrayList<ViewEntry>();
		ViewEntry entry = null;
		Object obj = null;
		if (items instanceof Collection) {
			Iterator iter = ((Collection) items).iterator();
			try {
				while (iter.hasNext()) {
					obj = iter.next();
					entry = new ViewEntry();
					entry.setValue(PropertyUtils
							.getProperty(obj, valueProperty));
					entry.setLable(PropertyUtils
							.getProperty(obj, displayProperty));
					list.add(entry);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public String getDisplayProperty() {
		return displayProperty;
	}

	public void setDisplayProperty(String displayProperty) {
		this.displayProperty = displayProperty;
	}

	public String getValueProperty() {
		return valueProperty;
	}

	public void setValueProperty(String valueProperty) {
		this.valueProperty = valueProperty;
	}


	private static final long serialVersionUID = 4083308274128286689L;

	public Object getItems() {
		return items;
	}

	public void setItems(Object items) {
		this.items = items;
	}

}
