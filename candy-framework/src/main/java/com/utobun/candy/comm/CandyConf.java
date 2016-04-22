package com.utobun.candy.comm;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CandyConf {
		
		//日志
		protected static Logger log = LoggerFactory.getLogger(CandyConf.class.getName());
	
		//创建properties对象
		private static Properties props=new Properties();
		
		private static InputStream in = Object.class.getResourceAsStream("/config.properties");

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
