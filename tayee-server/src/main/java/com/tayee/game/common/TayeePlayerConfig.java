package com.tayee.game.common;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

public final class TayeePlayerConfig {

	public final static Map<String, Object> AttrMap = new HashMap<String, Object>();
	public final static Map<String, Object> BuffMap = new HashMap<String, Object>();
	public final static Map<String, Object> PosMap = new HashMap<String, Object>();
	public final static Map<String,Object> ModuleMap = new HashMap<String,Object>();
	public final static Map<String,Object> SettingMap =new HashMap<String, Object>();

	public static void loadConfig(String dir) {
		
		System.out.println("read dir:" + dir);
		
		String attrProp = dir + File.separator + "playerAttribute.properties";
		readToMap(AttrMap, TayeeUtils.getProperites(attrProp));
		
		String  buffProp =dir + File.separator + "playerBuffer.properties";
		readToMap(BuffMap, TayeeUtils.getProperites(buffProp));
		
		String  posProp =dir + File.separator + "playerBuffer.properties";
		readToMap(PosMap, TayeeUtils.getProperites(posProp));
		
		String  moduleProp =dir + File.separator + "playerModule.properties";
		readToMap(ModuleMap, TayeeUtils.getProperites(moduleProp));
		
		String  settingProp =dir + File.separator + "playerSetting.properties";
		readToMap(SettingMap, TayeeUtils.getProperites(settingProp));
		
	}

	static void readToMap(Map<String, Object> map, Properties properties) {
		Iterator<Entry<Object, Object>> it = properties.entrySet().iterator();
		while (it.hasNext()) {
			Entry<Object, Object> entry = it.next();
			String key = entry.getKey().toString();
			Object value = entry.getValue();
			map.put(key, value);
		}
		for (String key : map.keySet()) {
			System.out.println("key = " + key + "  value = " + map.get(key));
		}
		System.out.println(" ");
	}

	public static void main(String[] args) {
		String dir = System.getProperty("user.dir") + "//src//main//res//conf//player";

		TayeePlayerConfig.loadConfig(dir);
	}

}
