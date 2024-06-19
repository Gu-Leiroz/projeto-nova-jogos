package br.com.nova.jogos.exceptions;

import java.io.Serializable;
import java.util.Date;

public class ExceptionResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private Date timestamp;
	private String msg;
	private String datails;

	public ExceptionResponse(Date timestamp, String msg, String datails) {
		this.timestamp = timestamp;
		this.msg = msg;
		this.datails = datails;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getDatails() {
		return datails;
	}

	public void setDatails(String datails) {
		this.datails = datails;
	}

}
