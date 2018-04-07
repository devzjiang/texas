package com.tayee.game.common;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.StringTokenizer;

public class TayeeUtils {

	/**
	 * 读取一个属性文件
	 * @param filePath 文件路径
	 * @return
	 */
	public static Properties getProperites(String filePath){
		if(null== filePath || "" == filePath){
			return null;
		}
	  	Properties props = new Properties();
	  	InputStream in=null;
        try {
	          in = new BufferedInputStream (new FileInputStream(filePath));
	          props.load(in);
             return props;
        } catch (Exception e) {
	          e.printStackTrace();
	          return null;
        }finally{
            try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
	 }
	
	/**
	 * 分割字符串
	 * @param tarage 字符串
	 * @param bigSymbol 分隔符
	 * @return
	 */
	public static String[] splitStringToArray(String tarage,String bigSymbol) {
		StringTokenizer token = new StringTokenizer(tarage, bigSymbol);
		String[] rs = new String[token.countTokens()];
		int i = 0;
		while (token.hasMoreTokens()) {
			rs[i] = token.nextToken();
			i += 1;
		}
		return rs;
	}
	
	/**
	 * 合并字符串
	 * @param tarage 字符串数组
	 * @param concatSymbol 合并符号
	 * @return
	 */
	public static String concatArrayToString(String[] tarage,String concatSymbol) {
		StringBuilder buffer = new StringBuilder();
		for (int i = 0; i < tarage.length; i++)
			buffer.append(tarage[i]).append(concatSymbol);
		buffer.deleteCharAt(buffer.lastIndexOf(concatSymbol));
		return buffer.toString().trim();
	}
}
