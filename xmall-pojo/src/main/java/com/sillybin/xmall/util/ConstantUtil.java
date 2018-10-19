package com.sillybin.xmall.util;

import java.util.Properties;

public class ConstantUtil {
	private static Properties props = new Properties();
	
	static {
		try {
			props.load(ConstantUtil.class.getClassLoader().getResourceAsStream("xmall.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static final String GENDER_MALE = props.getProperty("system.gender.male");
	public static final String GENDER_FEMALE = props.getProperty("system.gender.female");
	public static final String STATUS_ISABLE = props.getProperty("system.status.isable");
	public static final String STATUS_ENABLE = props.getProperty("system.status.enable");
	public static final String STATUS_DISABLE = props.getProperty("system.status.disable");
	public static final Integer PAGE_NUM = Integer.parseInt(props.getProperty("system.page.num"));
	public static final Integer PAGE_SIZE = Integer.parseInt(props.getProperty("system.page.size"));
}
