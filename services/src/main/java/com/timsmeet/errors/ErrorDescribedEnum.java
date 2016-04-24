package com.timsmeet.errors;


public enum ErrorDescribedEnum implements ErrorSpecification {

    PAGE_PARAM_IS_NEGATIVE("1001", "Param page can not be negative, current value: %d"),
    PER_PAGE_SHOULD_BE_POSITIVE("1002", "Param perPage should be positive, current value: %d"),
    INVALID_EMBEDED_PARAM("1003", "Param embed contains invalid values: %s"),
    INVALID_SORT_PARAM("1004", "Param sort contains invalid values: %s"),

	ADD_ORDER_ONLY_FOR_ACTIVE_PROVIDER("0100", "Order can be created only for active provider. %s is not active."),
	ADD_ORDER_ITEM_ONLY_FOR_ACTIVE_ORDER("0101", "Item can be added only to actctive order."),
	ADD_OPERATION_ONLY_FOR_ADDED_RECORD("0102", "Add operation called with entity not in ADDED state, %s"),
	UPDATE_OPERATION_ONLY_FOR_UPDATED_RECORD("0103", "Update operation called with entity not in MODIFIED state, %s"),

    PROVIDER_TO_READ_NOT_FOUND("0201", "Read Porvider error: Provider with id: %s not found."),
    PROVIDER_TO_DELETE_NOT_FOUND("0202", "Delete Porvider error: Provider with id: %s not found."),
    PROVIDER_TO_UPDATE_NOT_FOUND("0203", "Update Porvider error: Provider with id: %s not found."),
	PROVIDER_TO_READ_DISH_NOT_FOUND("0204", "Read Dish for provider error: Provider with id: %s not found."),
	PROVIDER_TO_SAVE_DISH_NOT_FOUND("0205", "Save Dish for provider error: Provider with id: %s not found."),
	DISH_TO_SAVE_NOT_FOUND("0206", "Save Dish error: Dish with id: %s not found."),
	PROVIDER_TO_DELETE_DISH_NOT_FOUND("0207", "Delete Dish error: Dish Provider with id %s not found."),
	DISH_TO_DELETE_NOT_FOUND("0208", "Delete Dish error: Dish with id %s not found."),
	ORGANIZATION_TO_DELETE_NOT_FOUND("0209", "Delete Organization error: Organization with id: %s not found."),
	ORGANIZATION_TO_UPDATE_NOT_FOUND("0210", "Update Organization error: Organization with id: %s not found."),
	ORGANIZATION_TO_SAVE_LOCATION_NOT_FOUND("0211", "Save location for organization error: Organization with id: %s not found."),
	LOCATION_TO_SAVE_NOT_FOUND("0212", "Save Location error: Location with id: %s not found."),
	ORGANIZATION_TO_DELETE_LOCATION_NOT_FOUND("0213", "Delete Location error: Organization with id: %s not found."),
	LOCATION_TO_DELETE_NOT_FOUND("0214", "Delete Location error: Location with id: %s not found."),

	FOOD_ORDER_TO_READ_NOT_FOUND("0301", "Read Food Order error: Food Order with id: %s not found."),
	;



	private String errorCode;
	private String errorMessage;

	private ErrorDescribedEnum(String errorCode, String errorMessage) {
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
