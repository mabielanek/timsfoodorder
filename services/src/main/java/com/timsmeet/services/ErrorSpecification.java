package com.timsmeet.services;

public enum ErrorSpecification implements ErrorDescribedEnum {
	
	ADD_ORDER_ONLY_FOR_ACTIVE_PROVIDER("0100", "Order can be created only for active provider. %s is not active."),
	ADD_ORDER_ITEM_ONLY_FOR_ACTIVE_ORDER("0101", "Item can be added only to actctive order."),
	ADD_OPERATION_ONLY_FOR_ADDED_RECORD("0102", "Add operation called with entity not in ADDED state, %s"),
	UPDATE_OPERATION_ONLY_FOR_UPDATED_RECORD("0103", "Update operation called with entity not in MODIFIED state, %s"),
	;
	
	
	
	private String errorCode;
	private String errorMessage;

	private ErrorSpecification(String errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	
	@Override
	public String getErrorCode() {
		return errorCode;
	}

	@Override
	public String getErrorMessage() {
		return errorMessage;
	}

}
