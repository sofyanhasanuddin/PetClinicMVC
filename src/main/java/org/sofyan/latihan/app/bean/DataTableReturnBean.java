package org.sofyan.latihan.app.bean;

import java.util.List;

public class DataTableReturnBean {
	
	private Integer draw;
	private Long recordsTotal;
	private Long recordsFiltered;
	private List<?> data;
	
	public DataTableReturnBean(Integer draw, Long recordsTotal,
			Long recordsFiltered, List<?> data) {
		super();
		this.draw = draw;
		this.recordsTotal = recordsTotal;
		this.recordsFiltered = recordsFiltered;
		this.data = data;
	}
	/**
	 * @return the draw
	 */
	public Integer getDraw() {
		return draw;
	}
	/**
	 * @param draw the draw to set
	 */
	public void setDraw(Integer draw) {
		this.draw = draw;
	}
	/**
	 * @return the recordsTotal
	 */
	public Long getRecordsTotal() {
		return recordsTotal;
	}
	/**
	 * @param recordsTotal the recordsTotal to set
	 */
	public void setRecordsTotal(Long recordsTotal) {
		this.recordsTotal = recordsTotal;
	}
	/**
	 * @return the recordsFiltered
	 */
	public Long getRecordsFiltered() {
		return recordsFiltered;
	}
	/**
	 * @param recordsFiltered the recordsFiltered to set
	 */
	public void setRecordsFiltered(Long recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}
	/**
	 * @return the data
	 */
	public List<?> getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(List<?> data) {
		this.data = data;
	}
	
}
