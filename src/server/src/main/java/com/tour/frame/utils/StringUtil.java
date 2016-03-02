package com.tour.frame.utils;


import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang.StringUtils;
import org.aspectj.util.LangUtil;

/**
 * 功能说明：字符串辅助工具
 * @author bear
 * @created 2014年6月13日 上午11:22:07
 * @updated
 */
public class StringUtil {

	/**
	 * 功能说明：字符串是否为空 空的定义如下： <br/>
	 * 1、为null <br/>
	 * 2、为不可见字符（如空格）<br/>
	 * 3、""<br/>
	 * 4、字符串"null"<br/>
	 * @author bear
	 * @created 2014年6月13日 上午11:24:12
	 * @updated 
	 * @param str 被检测的字符串
	 * @return 是否为空
	 */
	public static boolean isEmpty(String str) {
		return (str == null) || (str.trim().length() == 0) || ("null".equals(str));
	}

	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}
	/**
	 * 功能说明：获得一个随机的字符串
	 * @author bear
	 * @created 2014年6月13日 上午11:28:28
	 * @updated 
	 * @param length 字符串的长度
	 * @return 随机字符串
	 */
	public static String getRandomString(int length) {
		Random random = new Random();
		StringBuffer sb = new StringBuffer();

		if (length < 1) {
			length = 1;
		}
		String base = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}
	/**
	 * 功能说明：去掉首部指定长度的字符串并将剩余字符串首字母小写<br/>
	 *    例如：str=setName, preLength=3 -> return name
	 * @author bear
	 * @created 2014年6月13日 上午11:31:40
	 * @updated 
	 * @param str 被处理的字符串 
	 * @param preLength 去掉的长度
	 * @return 处理后的字符串，不符合规范返回null
	 */
	public static String cutPreAndLowerFirst(String str, int preLength) {
		if(str == null) {
			return null;
		}
		if(str.length() > preLength) {
			char first = Character.toLowerCase(str.charAt(preLength));
			if(str.length() > preLength +1) {
				return first +  str.substring(preLength +1);
			}
			return String.valueOf(first);
		}
		return null;
	}
	
	/**
	 * 功能说明： 原字符串首字母大写并在其首部添加指定字符串
	 *   例如：str=name, preString=get -> return getName
	 * @author bear
	 * @created 2014年6月13日 上午11:33:00
	 * @updated 
	 * @param str 被处理的字符串
	 * @param preString 添加的首部
	 * @return 处理后的字符串
	 */
	public static String upperFirstAndAddPre(String str, String preString) {
		if(str == null || preString == null) {
			return null;
		}
		return preString + Character.toUpperCase(str.charAt(0)) + str.substring(1);
	}
	/**
	 * 功能说明：切分字符串<br/>
	 *    a#b#c -> [a,b,c]
	 *    a##b#c -> [a,"",b,c]
	 * @author bear
	 * @created 2014年6月13日 上午11:39:26
	 * @updated 
	 * @param str 被切分的字符串
	 * @param separator 分隔符字符
	 * @return 切分后的集合
	 */
	public static List<String> split(String str, char separator) {
		return split(str, separator, 0);
	}
	/**
	 * 功能说明：将以splitStr连接的字符串按splitStr分割拆解到List集合中
	 * @author bear
	 * @created 2014年6月12日 下午9:22:47
	 * @updated 
	 * @param str 要拆解的字符串
	 * @param splitStr  分隔符字符
	 * @return
	 */
	public static List<String> strToList(String str,String splitStr) {
		List<String> list = new ArrayList<String>();
//		if (!Util.isNull(str)) {
//			String specValues[] = str.split(splitStr);
//			list = Arrays.asList(specValues);
//		}
		return list;
	}
	/**
	 * 切分字符串
	 * @param str 被切分的字符串
	 * @param separator 分隔符字符
	 * @param limit 限制分片数
	 * @return 切分后的集合
	 */
	public static List<String> split(String str, char separator, int limit){
		if(str == null) {
			return null;
		}
		List<String> list = new ArrayList<String>(limit == 0 ? 16 : limit);
		if(limit == 1) {
			list.add(str);
			return list;
		}
		
		boolean isNotEnd = true;	//未结束切分的标志
		int strLen = str.length();
		StringBuilder sb = new StringBuilder(strLen);
		for(int i=0; i < strLen; i++) {
			char c = str.charAt(i);
			if(isNotEnd && c == separator) {
				list.add(sb.toString());
				//清空StringBuilder
				sb.delete(0, sb.length());
				
				//当达到切分上限-1的量时，将所剩字符全部作为最后一个串
				if(limit !=0 && list.size() == limit-1) {
					isNotEnd = false;
				}
			}else {
				sb.append(c);
			}
		}
		list.add(sb.toString());
		return list;
	}
	
	/**
	 * 功能说明：重复某个字符
	 * @author bear
	 * @created 2014年6月13日 上午11:40:14
	 * @updated 
	 * @param c 被重复的字符
	 * @param count 重复的数目
	 * @return 重复字符字符串
	 */
	public static String repeat(char c, int count) {
		char[] result = new char[count];
		for (int i = 0; i < count; i++) {
			result[i] = c;
		}
		return new String(result);
	}
	
	/**
	 * 功能说明：给定字符串转换字符编码<br/>
	 *    如果参数为空，则返回原字符串，不报错。
	 * @author bear
	 * @created 2014年6月13日 上午11:40:47
	 * @updated 
	 * @param str 被转码的字符串
	 * @param sourceCharset 原字符集
	 * @param destCharset 目标字符集
	 * @return 转换后的字符串
	 */
	public static String transCharset(String str, String sourceCharset, String destCharset) {
		if(LangUtil.isEmpty(str) || LangUtil.isEmpty(sourceCharset) || LangUtil.isEmpty(destCharset)) {
			return str;
		}
		try {
			return new String(str.getBytes(sourceCharset), destCharset);
		} catch (UnsupportedEncodingException e) {
			return str;
		}
	}
	
	/**
	 * 截取一段字符的长度(汉、日、韩文字符长度为2),不区分中英文,如果数字不正好，则少取一个字符位
	 * 
	 * @param str
	 *            原始字符串
	 * @param specialCharsLength
	 *            截取长度(汉、日、韩文字符长度为2)
	 * @return
	 */
	public static String trim(String str, int specialCharsLength) {
		if (str == null || "".equals(str) || specialCharsLength < 1) {
			return "";
		}
		char[] chars = str.toCharArray();
		int charsLength = getCharsLength(chars, specialCharsLength);
		return new String(chars, 0, charsLength);
	}

	/**
	 * 功能说明:去掉字符串2端空格或空白。如果参数字符串为null，那么返回结果为空白字符串，即"";
	 * 
	 * @param s
	 *            需要过滤的字符串
	 * @return 创建日期: 2011-04-28 修改人： 修改日期: 修改内容:
	 * 
	 */
	public static String trim(String s) {
		return trim(s, false);
	}

	/**
	 * 功能说明： 去掉字符串2端空格或空白。如果参数字符串为null，那么返回结果为空白字符串，即""
	 * @author bear
	 * @created 2014年6月12日 下午9:36:51
	 * @updated 
	 * @param s 需要过滤的字符串
	 * @param isTransferred  是否对html特殊字符转义
	 * @return
	 */
	public static String trim(String s, boolean isTransferred) {
		if (isTransferred) {
			return s == null ? "" : s.trim();
		} else {
			return s == null ? "" : s.trim();
		}
	}

	/**
	 * 功能说明：获取一段字符的长度(个数)
	 *     输入长度中汉、日、韩文字符长度为2，输出长度中所有字符均长度为1
	 * @author bear
	 * @created 2014年6月12日 下午9:33:48
	 * @updated 
	 * @param chars  一段字符
	 * @param specialCharsLength 输入长度，汉、日、韩文字符长度为2
	 * @return 输出长度，所有字符均长度为1
	 */
	private static int getCharsLength(char[] chars, int specialCharsLength) {
		int count = 0;
		int normalCharsLength = 0;
		for (int i = 0; i < chars.length; i++) {
			int specialCharLength = getSpecialCharLength(chars[i]);
			if (count <= specialCharsLength - specialCharLength) {
				count += specialCharLength;
				normalCharsLength++;
			} else {
				break;
			}
		}
		return normalCharsLength;
	}

	/**
	 * 功能说明：获取字符长度
	 *   汉、日、韩文字符长度为2，ASCII码等字符长度为1
	 * @author bear
	 * @created 2014年6月12日 下午9:32:46
	 * @updated 
	 * @param charStr 字符
	 * @return 字符长度
	 */
	private static int getSpecialCharLength(char charStr) {
		if (isLetter(charStr)) {
			return 1;
		} else {
			return 2;
		}
	}

	/**
	 * 功能说明：判断一个字符是Ascill字符
	 * @author bear
	 * @created 2014年6月12日 下午9:30:55
	 * @updated 
	 * @param charStr 需要判断的字符
	 * @return 返回true=Ascill字符，否则是其他字符（如汉，日，韩文字符）
	 */
	private static boolean isLetter(char charStr) {
		int k = 0x80;
		return charStr / k == 0 ? true : false;
	}

	/**
	 * 功能说明：获取字符串长度  
	 *   一个汉字或日韩文长度为2,英文字符长度为1
	 * @author bear
	 * @created 2014年6月12日 下午9:29:50
	 * @updated 
	 * @param str  需要得到长度的字符串
	 * @return 得到的字符串长度
	 */
	public static int length(String str) {
		if (str == null)
			return 0;
		char[] c = str.toCharArray();
		int len = 0;
		for (int i = 0; i < c.length; i++) {
			len++;
			if (!isLetter(c[i])) {
				len++;
			}
		}
		return len;
	}

	/**
	 * 功能说明：截取一段字符的长度,不区分中英文,如果数字不正好，则少取一个字符位
	 *    比如截取一段字符串，截取长度为10，以...结尾
	 *    如果要截取的字符串长度超过了10，则返回   字符串...
	 *    如果长度没超过10，则返回原字符串
	 * @author bear
	 * @created 2014年6月12日 下午9:24:42
	 * @updated 
	 * @param origin 原始字符串
	 * @param len 截取长度(一个汉字长度按2算的)
	 * @param suffix 要拼的后缀
	 * @return
	 */
	public static String substring(String origin, int len, String suffix) {

		String resultString = "";
		if (origin == null || origin.equals("") || len < 1) {
			return resultString;
		} else if (origin.length() <= len) {
			return origin;
		} else if (origin.length() > 2 * len) {
			origin = origin.substring(0, 2 * len);
		}

		if (origin.length() > len) {
			char[] chr = origin.toCharArray();
			int strNum = 0;
			int strGBKNum = 0;
			boolean isHaveDot = false;

			for (int i = 0; i < origin.length(); i++) {
				if (chr[i] >= 0xa1) // 0xa1汉字最小位开始
				{
					strNum = strNum + 2;
					strGBKNum++;
				} else {
					strNum++;
				}

				if (strNum == 2 * len || strNum == 2 * len + 1) {
					if (i + 1 < origin.length()) {
						isHaveDot = true;
					}
					break;
				}
			}
			resultString = origin.substring(0, strNum - strGBKNum);
			if (!isHaveDot) {
			}
			resultString = resultString + suffix;
		}

		return resultString;

	}

	

	public static String replaceEx(String str, String subStr, String reStr) {
		if (str == null) {
			return null;
		}
		if ((subStr == null) || (subStr.equals(""))
				|| (subStr.length() > str.length()) || (reStr == null)) {
			return str;
		}
		StringBuilder sb = new StringBuilder();
		int lastIndex = 0;
		while (true) {
			int index = str.indexOf(subStr, lastIndex);
			if (index < 0) {
				break;
			}
			sb.append(str.substring(lastIndex, index));
			sb.append(reStr);

			lastIndex = index + subStr.length();
		}
		sb.append(str.substring(lastIndex));
		return sb.toString();
	}

	public static String javaEncode(String txt) {
		if ((txt == null) || (txt.length() == 0)) {
			return txt;
		}
		txt = replaceEx(txt, "\\", "\\\\");
		txt = replaceEx(txt, "\r\n", "\n");
		txt = replaceEx(txt, "\r", "\\r");
		txt = replaceEx(txt, "\t", "\\t");
		txt = replaceEx(txt, "\n", "\\n");
		txt = replaceEx(txt, "\"", "\\\"");
		txt = replaceEx(txt, "'", "\\'");
		return txt;
	}

	public static String leftPad(String srcString, char c, int length) {
		if (srcString == null) {
			srcString = "";
		}
		int tLen = srcString.length();

		if (tLen >= length)
			return srcString;
		int iMax = length - tLen;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < iMax; i++) {
			sb.append(c);
		}
		sb.append(srcString);
		return sb.toString();
	}	
	
	public static List<String> toList(String str) {

		List<String> resultList = new ArrayList<String>();
		if (StringUtils.isBlank(str)) {
			return resultList;
		}
		String[] array = StringUtils.split(str, ",");
		for (String element : array) {
			if (StringUtils.isNotBlank(element)) {
				resultList.add(element);
			}
		}
		return resultList;
	}
	
	

	public static void main(String[] agrs) {
//		System.out.println(substring("nihapo", 3, "..."));
//		System.out
//				.println(StringEscapeUtils
//						.escapeHtml(substring(
//								"美特斯邦威 美特斯邦威美特斯邦威美特斯邦威美特斯邦威美特斯邦威美特斯邦威美特斯邦威美特斯邦威美特斯邦威美特斯邦威美特斯邦威美特斯邦威美特斯邦威",
//								50, "...")));
	}
}
