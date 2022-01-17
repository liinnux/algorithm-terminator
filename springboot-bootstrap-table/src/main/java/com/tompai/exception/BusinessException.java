package com.tompai.exception;

/**
* @desc: 业务异常
* @name: BusinessException.java
* @author: tompai
* @email：liinux@qq.com
* @createTime: 2020年3月29日 下午7:05:57
* @history:
* @version: v1.0
*/
public class BusinessException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	protected final String message;

	public BusinessException(String message) {
		this.message = message;
	}

	public BusinessException(String message, Throwable e) {
		super(message, e);
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
