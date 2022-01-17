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
package com.tompai.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tompai.domain.BaseEntity;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
* @desc: springboot-mybatis-bootstraptable
* @name: Test.java
* @author: tompai
* @email：liinux@qq.com
* @createTime: 2020年3月28日 下午8:46:06
* @history:
* @version: v1.0
*/
@Data
@NoArgsConstructor
public class User extends BaseEntity {

	private Integer id;
	private String name;
	private String nickname;
	private String password;
	private Integer age;
	private Integer state;
	private String salt;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date inputTime;

}
