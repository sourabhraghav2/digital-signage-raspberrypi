package com.digitalsinage.model;


import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("StopBrowserRequest")
public class StopBrowserRequest extends Request {
	private String temp;
	public StopBrowserRequest  () {
		setAction(d->d.close());
	}
	public String getTemp() {
		return temp;
	}

	public void setTemp(String temp) {
		this.temp = temp;
	}

}
