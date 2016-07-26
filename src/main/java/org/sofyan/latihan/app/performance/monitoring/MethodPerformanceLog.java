package org.sofyan.latihan.app.performance.monitoring;

public class MethodPerformanceLog {
	
	private String methodName;
	private boolean firstEntries = false;
	
	private Long startTime;
	private Long endTime;
	
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public Double getTotalTime() {
		return ( this.endTime - this.startTime ) / 1000.0;
	}
	public boolean isFirstEntries() {
		return firstEntries;
	}
	public void setFirstEntries(boolean firstEntries) {
		this.firstEntries = firstEntries;
	}
	
	public void start() {
		this.startTime = System.currentTimeMillis();
	}
	
	public void end() {
		this.endTime = System.currentTimeMillis();
	}

}
