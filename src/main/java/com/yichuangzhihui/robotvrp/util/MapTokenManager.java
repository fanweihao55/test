package com.yichuangzhihui.robotvrp.util;


import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class MapTokenManager {

	private static Logger logger = LogManager.getLogger(MapTokenManager.class);
	private long expiration_time = 0;

	/**
	 * MapTokenManager实例化
	 * @param expiration_time Unit is millisecond
	 */
	public MapTokenManager(long expiration_time) {
		this.expiration_time = expiration_time * 1000;
	}

	/**
	 * 生成token
	 * @param userid
	 * @return
	 */
	public static String generateToken(String userid) {
		String str = userid + System.currentTimeMillis() + RandomStringUtils.randomAlphabetic(5);
		String token = Sha256Utils.sha256(str);
		return token;
	}

}
