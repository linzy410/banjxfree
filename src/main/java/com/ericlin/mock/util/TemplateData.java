package com.ericlin.mock.util;
/**
 * @Description TODO(用一句话描述该文件做什么)
 * @author ruweixia
 * @date 2016年3月15日下午3:56:53
 */
public class TemplateData {
    private String value;
    private String color;
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    
	public TemplateData (String value, String color) {
		this.value = value;
		this.color = color;
	}

}
