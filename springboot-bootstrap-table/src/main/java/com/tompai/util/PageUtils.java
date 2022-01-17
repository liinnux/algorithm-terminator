/** 
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.tompai.util;

import java.io.Serializable;
import java.util.List;

/**
* @desc:  前端需要分页显示信息类
* @name: PageUtils.java
* @author: tompai
* @email：liinux@qq.com
* @createTime: 2020年3月28日 下午8:46:06
* @history:
* @version: v1.0
*/
public class PageUtils implements Serializable {

	private static final long servialVersionUID = 1L;
	private int total;
	private List<?> rows;

	public PageUtils(int total, List<?> rows) {
		this.total = total;
		this.rows = rows;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}
}
