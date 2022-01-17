
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
package com.tompai.domain;

import java.util.Date;

import com.tompai.annotation.Excel;
import com.tompai.annotation.Excel.Type;
import com.tompai.util.DateUtils;

/**
* @desc: springboot-bootstrap-table
* @name: UserOperateModel.java
* @author: tompai
* @email：liinux@qq.com
* @createTime: 2020年3月29日 下午7:51:41
* @history:
* @version: v1.0
*/

public class UserOperateModel extends BaseEntity {
	private static final long serialVersionUID = 1L;

	private int userId;

	@Excel(name = "用户编号")
	private String userCode;

	@Excel(name = "用户姓名")
	private String userName;

	@Excel(name = "用户性别", readConverterExp = "0=男,1=女,2=未知")
	private String userSex;

	@Excel(name = "用户手机")
	private String userPhone;

	@Excel(name = "用户邮箱")
	private String userEmail;

	@Excel(name = "用户余额")
	private double userBalance;

	@Excel(name = "用户状态", readConverterExp = "0=正常,1=停用")
	private String status;

	@Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss", type = Type.EXPORT)
	private Date createTime;

	public UserOperateModel() {

	}

	public UserOperateModel(int userId, String userCode, String userName, String userSex, String userPhone,
			String userEmail, double userBalance, String status) {
		this.userId = userId;
		this.userCode = userCode;
		this.userName = userName;
		this.userSex = userSex;
		this.userPhone = userPhone;
		this.userEmail = userEmail;
		this.userBalance = userBalance;
		this.status = status;
		this.createTime = DateUtils.getNowDate();
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserSex() {
		return userSex;
	}

	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public double getUserBalance() {
		return userBalance;
	}

	public void setUserBalance(double userBalance) {
		this.userBalance = userBalance;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
