/**
 * 
 */
package com.epayroll.model.dto;

/**
 * @author Surendra Jha
 * 
 */
public class City {

	private Long id;
	private String cityName;
	private String state;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
