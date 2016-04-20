package com.utobun.candy.comm;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CandyConf {
		
		//日志
		protected static Logger log = LogManager.getLogger(CandyConf.class.getName());
	
		//创建properties对象
		private static Properties props=new Properties();
		
		private static InputStream in = Object.class.getResourceAsStream("/config/config.properties");

		//取properties中的键值
		public static String getValue(String key){
			try {
				props.load(in);
			} catch (IOException e) {
				log.info("读取配置文件出错 ",e);
			}
			return props.getProperty(key);
		}
}
