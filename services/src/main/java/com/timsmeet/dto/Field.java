package com.timsmeet.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Field {

	private Long id;
	private Long lastModificationId;
	private BigDecimal valueDecimal;
	private Long valueInteger;
	private Timestamp valueDateTime;
	private String valueString;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getLastModificationId() {
		return lastModificationId;
	}

	public void setLastModificationId(Long lastModificationId) {
		this.lastModificationId = lastModificationId;
	}

	public BigDecimal getValueDecimal() {
		return valueDecimal;
	}

	public void setValueDecimal(BigDecimal valueDecimal) {
		this.valueDecimal = valueDecimal;
	}

	public Long getValueInteger() {
		return valueInteger;
	}

	public void setValueInteger(Long valueInteger) {
		this.valueInteger = valueInteger;
	}

	public Timestamp getValueDateTime() {
		return valueDateTime;
	}

	public void setValueDateTime(Timestamp valueDateTime) {
		this.valueDateTime = valueDateTime;
	}

	public String getValueString() {
		return valueString;
	}

	public void setValueString(String valueString) {
		this.valueString = valueString;
	}

}
