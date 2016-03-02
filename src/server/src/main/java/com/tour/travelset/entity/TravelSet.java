package com.tour.travelset.entity;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import com.tour.frame.utils.DateUtils;
import com.tour.frame.utils.StringUtil;

/**
 * 
 * @author <Auto generate>
 * @version 2015-07-15
 * @see com.tour.account.entity.locations
 */

public class TravelSet {

	private String day;

	private String date;

	private List<TravelItem> item;

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public List<TravelItem> getItem() {
		return item;
	}

	public void setItem(List<TravelItem> item) {
		this.item = item;
	}

	/**
	 * 组装前端需要的结果集 [{ "day": 第一天, "date": "2015-09-10", "item":
	 * [{图片内容、或文字},{图片内容、或文字}] }]
	 * 
	 * @param itemList
	 * @return
	 */
	public static List<TravelSet> assembleTravelSet(List<TravelItem> itemList) {
		List<TravelSet> retlist = new ArrayList<TravelSet>();
		if (itemList != null && !itemList.isEmpty()) {
			/*
			 * 提取时间集合
			 */
			List<String> dateList = new ArrayList<String>();
			for (TravelItem item : itemList) {
				String linkDate = DateUtils.date2Str(item.getLinkDate(), "yyyy-MM-dd");
				if (!dateList.contains(linkDate)) {
					dateList.add(linkDate);
				}
			}
			Collections.sort(dateList);// 对时间进行排序
			String firstDate = null;// 第一天时间
			for (String linkdate : dateList) {
				int diff = 1;// 第N天
				List<TravelItem> items = new ArrayList<TravelItem>();// 明细的集合体
				if (StringUtil.isEmpty(firstDate)) {
					firstDate = linkdate;
				} else {
					try {
						// 计算当前时间和第一天之间的时间差
						Calendar link = DateUtils.parseCalendar(linkdate, "yyyy-MM-dd");
						Calendar first = DateUtils.parseCalendar(firstDate, "yyyy-MM-dd");
						diff = 1 + DateUtils.dateDiff('d', link, first);
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				for (TravelItem item : itemList) {
					String d = DateUtils.date2Str(item.getLinkDate(), "yyyy-MM-dd");
					if (linkdate.equals(d)) {
						items.add(item);
					}
				}
				TravelSet set = new TravelSet();
				set.setDate(linkdate);
				set.setDay("第" + diff + "天");
				set.setItem(items);
				retlist.add(set);
			}
		}

		return retlist;
	}
}
