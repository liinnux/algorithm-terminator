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
package com.tompai.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.tompai.entity.User;
import com.tompai.mapper.UserMapper;
import com.tompai.util.PageUtils;

import lombok.extern.slf4j.Slf4j;

/**
* @desc: springboot-mybatis-bootstraptable
* @name: Test.java
* @author: tompai
* @email：liinux@qq.com
* @createTime: 2020年3月28日 下午8:46:06
* @history:
* @version: v1.0
*/
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserMapper userMapper;

	@RequestMapping(value = "/index")
	public String index() {
		return "/index";
	}

	// , produces = "application/json;charset=UTF-8"
	@RequestMapping(value = "/getUser")
	public PageUtils getUser(int pageNumber, int pageSize) {
		log.info("pageNumber={}", pageNumber);
		log.info("pageSize={}", pageSize);
		PageHelper.startPage(pageNumber, pageSize);
		List<User> users = userMapper.getUserList();
		// 获取total值须在PageHelper.startPage调用前
		int total = users.size();
		// 需注意PageHelper.startPage调用后再查询数据只能查到pageSize大小的数据
		PageUtils pageUtils = new PageUtils(total, users);
		return pageUtils;
	}

	@RequestMapping(value = "/addUser")
	public Object addUser(User user) {
		userMapper.addUser(user);
		Map map = new HashMap();
		map.put("state", "success");
		return map;
	}

	@RequestMapping(value = "/updateUser")
	public Object updateUser(User user) {
		userMapper.updateUser(user);
		Map map = new HashMap();
		map.put("state", "success");
		return map;
	}

	@RequestMapping(value = "/deleteUser")
	public Object deleteUser(HttpServletRequest request) {
		String[] ids = request.getParameterValues("ids");
		for (int i = 0; i < ids.length; i++) {
			log.info("id:{}", ids[i]);
			int id = Integer.parseInt(ids[i]);
			userMapper.deleteUser(id);
		}
		Map map = new HashMap();
		map.put("state", "success");
		return map;
	}
}
