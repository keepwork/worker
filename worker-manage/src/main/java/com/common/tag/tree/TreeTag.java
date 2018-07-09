package com.common.tag.tree;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import com.sinovatech.common.web.taglib.IBaseTag;

/**
 * 树标签
 * 
 * @author kevin(keepwork512@163.com)
 * @date Dec 28, 2015 12:55:36 PM
 */
public class TreeTag extends IBaseTag
{
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

	/**
	 * <p>
	 * <ul>
	 * <li>格式化列表为树结构</li>
	 * </ul>
	 * </p>
	 * 
	 * @return
	 */
	private String formartResult()
	{
		HttpServletRequest request = (HttpServletRequest) pageContext
				.getRequest();

		List list = (List) request.getAttribute(item);
		List tree = sortListAsTree(list, nodeIdProperty, rootValue);
		String contextPath = request.getContextPath();

		StringBuffer sb = new StringBuffer();
		// <script type="text/javascript"
		// src="mootools/mootools.v1.11.js"></script>
		// <script type="text/javascript" src="mootree.js"></script>
		//
		// <script type="text/javascript">
		sb.append("<script type=\"text/javascript\" src=\"" + contextPath
				+ "/common/tree/mootools.v1.11.js\"></script>\n");
		sb.append("<script type=\"text/javascript\" src=\"" + contextPath
				+ "/common/tree/mootree.js\"></script>\n");
		sb.append("<script type=\"text/javascript\">");

		sb.append("var tree;");
		sb.append("function displayTree() ");
		sb.append("{");// tree config
		sb.append("		mode: 'files',");
		sb.append("		div: '" + div + "',");
		if (!StringUtils.isBlank(grid))
			sb.append("		grid: '" + this.grid + "',");
		if (!StringUtils.isBlank(onClick))
			sb.append("		onClick: '" + onClick + "',");

		if (!StringUtils.isBlank(onExpand))
			sb.append("		onExpand: '" + onExpand + "',");

		if (!StringUtils.isBlank(onSelect))
			sb.append("		onSelect: '" + onSelect + "',");

		
		sb.append("},");
		sb.append("{");// node config
		sb.append("})");

		sb.append("</script>");

		// TODO 树
		return tree.toString();

	}

	/**
	 * <p>
	 * <ul>
	 * <li>目的：comments</li>
	 * </ul>
	 * </p>
	 * 
	 * @param list
	 * @param name
	 * @param value
	 * @return
	 */
	private List sortListAsTree(List list, String name, String value)
	{
		List sub = subList(list, name, value);
		if (sub.size() == 0)
		{
			return null;
		}

		List childs = new ArrayList();
		childs.addAll(sub);
		for (Iterator it = sub.iterator(); it.hasNext();)
		{
			Object node = it.next();
			String nodeId = this.getBeanProperty(node, nodeIdProperty);
			List childss = sortListAsTree(list, parentNodeIdProperty, nodeId);
			if (childss != null)
			{
				childs.addAll(childss);
			}
		}

		return childs;
	}

	/**
	 * <p>
	 * <ul>
	 * <li>使用属性值过滤出一个子列表,并在原有列表中删除</li>
	 * </ul>
	 * </p>
	 * 
	 * @param source
	 * @param propertyName
	 * @param propertyValue
	 * @return
	 */
	private List subList(List source, String propertyName, String propertyValue)
	{
		List list = new ArrayList();
		Iterator it = source.iterator();
		while (it.hasNext())
		{
			Object o = it.next();
			String value = getBeanProperty(o, propertyName);

			if (propertyValue.equals(value))
			{
				list.add(o);
				it.remove();
			}
		}
		return list;
	}

	/**
	 * <p>
	 * <ul>
	 * <li>获取bean里面的一个属性值:String转换的</li>
	 * </ul>
	 * </p>
	 * 
	 * @param bean
	 * @param propertyName
	 * @return
	 */
	private String getBeanProperty(Object bean, String propertyName)
	{
		try
		{
			return BeanUtils.getProperty(bean, propertyName);
		} catch (IllegalAccessException e)
		{
			e.printStackTrace();
		} catch (InvocationTargetException e)
		{
			e.printStackTrace();
		} catch (NoSuchMethodException e)
		{
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 放置树的DIV
	 */
	private String div;

	/**
	 * tree items list, the pojos in the item list must implements ITreeNode
	 * interface.
	 */
	private String item;

	/**
	 * 跟节点的值
	 */
	private String rootValue;

	/**
	 * 树名称
	 */
	private String title;
	/**
	 * 节点编号的属性
	 */
	private String nodeIdProperty;

	/**
	 * 节点的父节点编号
	 */
	private String parentNodeIdProperty;
	private String theme;
	private String loader;
	private String grid;
	private String onExpand;
	private String onSelect;
	private String onClick;

	public String getTheme()
	{
		return theme;
	}

	public void setTheme(String theme)
	{
		this.theme = theme;
	}

	public String getLoader()
	{
		return loader;
	}

	public void setLoader(String loader)
	{
		this.loader = loader;
	}

	public String getGrid()
	{
		return grid;
	}

	public void setGrid(String grid)
	{
		this.grid = grid;
	}

	public String getOnExpand()
	{
		return onExpand;
	}

	public void setOnExpand(String onExpand)
	{
		this.onExpand = onExpand;
	}

	public String getOnSelect()
	{
		return onSelect;
	}

	public void setOnSelect(String onSelect)
	{
		this.onSelect = onSelect;
	}

	public String getOnClick()
	{
		return onClick;
	}

	public void setOnClick(String onClick)
	{
		this.onClick = onClick;
	}

	public String getItem()
	{
		return item;
	}

	public void setItem(String item)
	{
		this.item = item;
	}

	/**
	 * @return the div
	 */
	public String getDiv()
	{
		return div;
	}

	/**
	 * @param div
	 *            the div to set
	 */
	public void setDiv(String div)
	{
		this.div = div;
	}
}
