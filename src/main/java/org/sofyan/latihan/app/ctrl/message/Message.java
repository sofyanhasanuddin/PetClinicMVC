package org.sofyan.latihan.app.ctrl.message;

import java.util.List;

public class Message implements MessageConstant {
	
	private Boolean status;
	private String message;
	private List<?> datas;
	
	public static Message successMessage(String message) {
		return new Message( SUCCESS, message);
	}
	
	public static Message failMessage(String message) {
		return new Message( FAIL,message );
	}
	
	public static Message successMessage(String message, List<?> datas) {
		return new Message( SUCCESS, message, datas);
	}
	
	public static Message failMessage(String message, List<?> datas) {
		return new Message( FAIL,message, datas );
	}
	
	private Message(){}
	
	private Message(Boolean status, String message) {
		super();
		this.status = status;
		this.message = message;
	}
	
	public Message(Boolean status, String message, List<?> datas) {
		super();
		this.status = status;
		this.message = message;
		this.datas = datas;
	}

	public Boolean getStatus() {
		return status;
	}
	
	public void setStatus(Boolean status) {
		this.status = status;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}

	public List<?> getDatas() {
		return datas;
	}

	public void setDatas(List<?> datas) {
		this.datas = datas;
	}
	
}
