package com.timsmeet.rest.controllers.constants;

public final class Endpoint {
    
    public static final String ORGANIZATION = "/organizations";
    public static final String ORGANIZATION_ID_PARAM = "/{organizationId}";

    public static final String LOCATION = "/locations";
    public static final String LOCATION_ID_PARAM = "/{locationId}";

    public static final String PROVIDER = "/providers";
    public static final String PROVDER_ID_PARAM = "/{providerId}";

    public static final String DISH = "/dishes";
    public static final String DISH_ID_PARAM = "/{dishId}";

    public static final String FOOD_ORDER = "/foodOrders";
    public static final String FOOD_ORDER_ID_PARAM = "/{foodOrderId}";

    private Endpoint() {
    }
}
