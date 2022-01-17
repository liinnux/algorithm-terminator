
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
package com.tompai.eventbus;

/**
* @desc: spring-mybatis-demo
* @name: MyPublisher.java
* @author: tompai
* @email：liinux@qq.com
* @createTime: 2020年12月15日 下午10:13:08
* @history:
* @version: v1.0
*/

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nepxion.eventbus.core.Event;
import com.nepxion.eventbus.core.EventControllerFactory;

@Service
public class MyPublisher {
    private static final Logger LOG = LoggerFactory.getLogger(MyPublisher.class);

    @Autowired
    private EventControllerFactory eventControllerFactory;

    public void publish() {
        LOG.info("发送事件...");

        // 异步模式下(默认)，子线程中收到派发的事件
        eventControllerFactory.getAsyncController().post("Sync Event String Format");

        // 同步模式下，主线程中收到派发的事件
        // 事件派发接口中eventControllerFactory.getSyncController(identifier)必须和@EnableEventBus参数保持一致，否则会收不到事件
        eventControllerFactory.getSyncController().post("Sync Event String Format");

        // 异步模式下(默认)，子线程中收到派发的事件
        eventControllerFactory.getAsyncController("identifier1").post(12345L);
        
        eventControllerFactory.getAsyncController("identifier2").post(67890L);
        

        // 同步模式下，主线程中收到派发的事件
        // 事件派发接口中eventControllerFactory.getSyncController(identifier)必须和@EnableEventBus参数保持一致，否则会收不到事件
        eventControllerFactory.getSyncController().post(Boolean.TRUE);

        // 异步模式下(默认)，子线程中收到派发的事件
        eventControllerFactory.getAsyncController().postEvent(new Event("Async Event"));

        // 同步模式下，主线程中收到派发的事件
        // 事件派发接口中eventControllerFactory.getSyncController(identifier)必须和@EnableEventBus参数保持一致，否则会收不到事件
        eventControllerFactory.getSyncController().postEvent(new Event("Sync Event"));
    }
}

