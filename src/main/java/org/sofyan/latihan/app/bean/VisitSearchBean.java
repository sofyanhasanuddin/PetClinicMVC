package org.sofyan.latihan.app.bean;

import java.util.Date;

public class VisitSearchBean extends SearchBean {

	private Date entryDate;
	private Date leaveDate;
	
	public Date getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}
	public Date getLeaveDate() {
		return leaveDate;
	}
	public void setLeaveDate(Date leaveDate) {
		this.leaveDate = leaveDate;
	}
	
}
