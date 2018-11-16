package com.zhejiang.manage.util;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultVo<T> {
 
	private int code;
	private String msg;
	private boolean status;
	private T data;
	public ResultVo(){
		super();
	}

	public ResultVo(boolean status){
		super();
		this.status = status;
	}

	public ResultVo(int code, String msg, boolean status) {
		super();
		this.code = code;
		this.msg = msg;
		this.status = status;
	}
 
	public ResultVo(int code, String msg, T data, boolean status) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
		this.status = status;
	}
 
	/**
	 * 报异常调用该方法
	 * 
	 * @param msg
	 * @return
	 */
	public static <T> ResultVo getFailed(String msg) {
		return new ResultVo(10000, msg,false);
	}

	public static <T> ResultVo getFailed() {
		return new ResultVo(10000, null,false);
	}
 
	/**
	 * 报异常调用该方法
	 * 
	 * @param msg
	 * @return
	 */
	public static <T> ResultVo getFailed(String msg, T data) {
		return new ResultVo(10000, msg, data,false);
	}
 
	/**
	 * 成功请求调用该方法
	 * 
	 * @param msg
	 * @return
	 */
	public static <T> ResultVo getSuccess(String msg) {
		return new ResultVo(20000, msg,true);
	}

	public static <T> ResultVo getSuccess() {
		return new ResultVo(20000, null,true);
	}
	
	/**
	 * 成功请求调用该方法
	 * 
	 * @param data
	 * @return
	 */
	public static <T> ResultVo getSuccess(T data) {
		return new ResultVo(20000, null, data,true);
	}
 
	/**
	 * 成功请求调用该方法
	 * 
	 * @param msg
	 * @param data
	 * @return
	 */
	public static <T> ResultVo getSuccess(String msg, T data) {
		return new ResultVo(20000, msg, data,true);
	}
 
	/**
	 * 未报异常，但未达到最终数据，中途返回调用该方法
	 * 
	 * @param msg
	 * @return
	 */
	public static <T> ResultVo getBroken(String msg) {
		return new ResultVo(10000, msg,false);
	}
 
	/**
	 * 未报异常，但未达到最终数据，中途返回调用该方法
	 * 
	 * @param msg 
	 * @param data
	 * @return
	 */
	public static <T> ResultVo getBroken(String msg, T data) {
		return new ResultVo(10000, msg, data,false);
	}
 
	
	
	public int getCode() {
		return code;
	}
 
	public void setCode(int code) {
		this.code = code;
	}
 
	public String getMsg() {
		return msg;
	}
 
	public void setMsg(String msg) {
		this.msg = msg;
	}
 
	public T getData() {
		return data;
	}
 
	public void setData(T data) {
		this.data = data;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
}