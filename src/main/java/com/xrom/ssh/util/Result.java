package com.xrom.ssh.util;
/**
 * 通用的返回结构
 * @author hemincan
 * @date 2018-6-8
 * @description
 */
public class Result<T> {
	
	private String code;
	private String message;
	private T result;
	public Result() {
		this.code = "0";
		this.message = "成功";
	}
	public Result(String code, String message, T result) {
		this.code = code;
		this.message = message;
		this.result = result;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getResult() {
		return result;
	}
	public void setResult(T result) {
		this.result = result;
	}
	
}
