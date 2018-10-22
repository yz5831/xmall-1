package com.sillybin.xmall.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UserUtil {
	public static String createUserNo(Long no) {
		String prefix = "ZH";
		// 查询所有的对象信息，按照主键由大到小排序，获取第一个
		String noStr = String.valueOf(no);
		if (noStr.length() < 5) {
			while (noStr.length() < 5) {
				noStr = "0" + noStr;
			}
		}
		return prefix + noStr;
	}

	public static Date parseBirthday(String idCard) throws Exception {
		// 截取对应的生日字段
		String str = idCard.substring(6, 14);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		return dateFormat.parse(str);
	}
}
