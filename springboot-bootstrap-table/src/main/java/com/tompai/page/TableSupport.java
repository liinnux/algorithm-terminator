package com.tompai.page;

import com.tompai.util.Constants;
import com.tompai.util.ServletUtils;

/**
* @desc: 表格数据处理
* @name: TableSupport.java
* @author: tompai
* @email：liinux@qq.com
* @createTime: 2020年3月29日 下午7:05:57
* @history:
* @version: v1.0
*/
public class TableSupport {
	/**
	 * 封装分页对象
	 */
	public static PageDomain getPageDomain() {
		PageDomain pageDomain = new PageDomain();
		pageDomain.setPageNum(ServletUtils.getParameterToInt(Constants.PAGE_NUM));
		pageDomain.setPageSize(ServletUtils.getParameterToInt(Constants.PAGE_SIZE));
		pageDomain.setOrderByColumn(ServletUtils.getParameter(Constants.ORDER_BY_COLUMN));
		pageDomain.setIsAsc(ServletUtils.getParameter(Constants.IS_ASC));
		return pageDomain;
	}

	public static PageDomain buildPageRequest() {
		return getPageDomain();
	}
}
