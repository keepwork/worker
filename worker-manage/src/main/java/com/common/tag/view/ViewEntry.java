package com.common.tag.view;

public class ViewEntry {

	private Object lable;

	private Object value;

	public ViewEntry() {
		super();
	}

	public ViewEntry(Object lable, Object value) {
		super();
		this.lable = lable;
		this.value = value;
	}

	public Object getLable() {
		return lable;
	}

	public void setLable(Object lable) {
		this.lable = lable;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}


}
