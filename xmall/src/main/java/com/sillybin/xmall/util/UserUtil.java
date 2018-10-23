package com.sillybin.xmall.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.annotation.Resource;
import com.sillybin.xmall.transport.UserTransport;

public class UserUtil {
	@Resource(name="userTransport")
	private UserTransport userTransport;
	
	
	public static String createUserNo(String no) {
		while (no.length() < 5) {
			no = "0" + no;
		}
		return "AUG" + no;
	}

	public static Date parseBirthday(String idCard) throws Exception {
		// 截取对应的生日字段
		String str = idCard.substring(6, 14);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		return dateFormat.parse(str);
	}
}
