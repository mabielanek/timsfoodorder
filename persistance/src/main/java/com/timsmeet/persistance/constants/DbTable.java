package com.timsmeet.persistance.constants;

public final class DbTable {

    public static final class Provider {
        public final static String TABLE = "fo_provider";
        public final static String ID = "id";
        public final static String LAST_MODIFICATION_ID = "last_modification_id";
        public final static String STATUS = "status";
        public final static String NAME = "name";
        public final static String COMMENT = "comment";
        public final static String ADDRESS_ID = "address_id";
        public final static String CONTACT_ID = "contact_id";
        
        private Provider() {}
    }
    
    public static final class Contact {
        public final static String TABLE = "fo_contact";
        public final static String ID = "id";
        public final static String LAST_MODIFICATION_ID = "last_modification_id";
        public final static String STATUS = "status";
        
        private Contact(){}
    }
    
    public static final class AdditionalCost {
        public final static String TABLE = "fo_add_cost";
        public final static String ID = "id";
        public final static String LAST_MODIFICATION_ID = "last_modification_id";
        public final static String STATUS = "status";
        public final static String KIND = "kind";
        public final static String COST = "cost";
        public final static String PROVIDER_ID = "provider_id";
        
        private AdditionalCost(){}
    }
    
    public static final class WebUrl {
        public final static String TABLE = "fo_web_url";
        public final static String ID = "id";
        public final static String LAST_MODIFICATION_ID = "last_modification_id";
        public final static String STATUS = "status";
        public final static String COMMENT_TEXT = "comment_text";
        public final static String DISPLAY_INDEX = "display_index";
        public final static String WEB_URL_ADDRESS = "web_url_address";
        public final static String CONTACT_ID = "contact_id";

        private WebUrl(){}
    }
    
    
    public static final class Address {
        public final static String TABLE = "fo_address";
        public final static String ID = "id";
        public final static String LAST_MODIFICATION_ID = "last_modification_id";
        public final static String STATUS = "status";
        public final static String ADDRESS1 = "address1";
        public final static String ADDRESS2 = "address2";
        public final static String CITY = "city";
        public final static String ZIP_CODE = "zip_code";
        public final static String STATE = "state";
        public final static String COUNTRY = "country";
        public final static String COMMENT_TEXT = "comment_text";
        public final static String DISPLAY_INDEX = "display_index";
        
        private Address() {}
    }
    

    public static final class DishComponent {
        public final static String TABLE = "fo_dish_comp";
        public final static String ID = "id";
        public final static String LAST_MODIFICATION_ID = "last_modification_id";
        public final static String STATUS = "status";
        public final static String DISH_ID = "dish_id";
        public final static String DESCRIPTION = "description";
        public final static String USE_AS_DISH_PRICE = "use_as_dish_price";
        public final static String ELEMENTS_REQUIRED = "elements_required";
        public final static String MAX_ELEMENTS = "max_elements";
        
        private DishComponent() {}
    }
    
    public static final class DishElement {
        public final static String TABLE = "fo_dish_elem";
        public final static String ID = "id";
        public final static String LAST_MODIFICATION_ID = "last_modification_id";
        public final static String STATUS = "status";
        public final static String DISH_COMP_ID = "dish_comp_id";
        public final static String NAME = "name";
        public final static String DESCRIPTION = "description";
        
        private DishElement() {}
    }
    
    public static final class Dish {
        public final static String TABLE = "fo_dish";
        public final static String ID = "id";
        public final static String LAST_MODIFICATION_ID = "last_modification_id";
        public final static String STATUS = "status";
        public final static String PROVIDER_ID = "provider_id";
        public final static String NAME = "name";
        public final static String DESCRIPTION = "description";
        public final static String START_DAY = "start_day";
        public final static String END_DAY = "end_day";
        
        private Dish(){}
    }
    
    public final static class DishGenere {
        public final static String TABLE = "fo_dish_genere";
        public final static String ID = "id";
        public final static String LAST_MODIFICATION_ID = "last_modification_id";
        public final static String DISH_ID = "dish_id";
        public final static String GENERE_ID = "genere_id";
        
        private DishGenere(){}
    }
    
    public final static class DishPrice {
        public final static String TABLE = "fo_dish_price";
        public final static String ID = "id";
        public final static String LAST_MODIFICATION_ID = "last_modification_id";
        public final static String LAST_UPD_TIME = "last_upd_time";
        public final static String COST = "cost";
        public final static String DISH_ID = "dish_id";
        public final static String DISH_COMP_ID = "dish_comp_id";
        public final static String DISH_ELEM_ID = "dish_elem_id";
        
        private DishPrice() {}
    }
    
    public final static class Email {
        public final static String TABLE = "fo_email";
        public final static String ID = "id";
        public final static String LAST_MODIFICATION_ID = "last_modification_id";
        public final static String STATUS = "status";
        public final static String COMMENT_TEXT = "comment_text";
        public final static String DISPLAY_INDEX = "display_index";
        public final static String EMAIL_ADDRESS = "email_address";
        public final static String CONTACT_ID = "contact_id";
        
        private Email() {}
    }
    
    public final static class FoodOrder {
        public final static String TABLE = "fo_food_order";
        public final static String ID = "id";
        public final static String LAST_MODIFICATION_ID = "last_modification_id";
        public final static String PERSON_ID = "person_id";
        public final static String PROVIDER_ID = "provider_id";
        public final static String ORDER_STATUS = "order_status";
        public final static String ADD_TIME = "add_time";
        public final static String ORDER_TIME = "order_time";
        
        private FoodOrder() {}
    }
    
    public final static class Genere {
        public final static String TABLE = "fo_genere";
        public final static String ID = "id";
        public final static String LAST_MODIFICATION_ID = "last_modification_id";
        public final static String NAME = "name";
        
        private Genere() {}
    }
    
    public final static class OrderItem {
        public final static String TABLE = "fo_order_item";
        public final static String ID = "id";
        public final static String LAST_MODIFICATION_ID = "last_modification_id";
        public final static String PERSON_ID = "person_id";
        public final static String FOOD_ORDER_ID = "food_order_id";
        public final static String DISH_ID = "dish_id";
        public final static String COUNT = "count";
        
        private OrderItem() {}
    }
    
    public final static class OrderSubItem {
        public final static String TABLE = "fo_order_sub_item";
        public final static String ID = "id";
        public final static String LAST_MODIFICATION_ID = "last_modification_id";
        public final static String ORDER_ITEM_ID = "order_item_id";
        public final static String DISH_COMP_ID = "dish_comp_id";
        public final static String DISH_ELEM_ID = "dish_elem_id";
        
        private OrderSubItem(){}
    }

    public final static class Person {
        public final static String TABLE = "fo_person";
        public final static String ID = "id";
        public final static String LAST_MODIFICATION_ID = "last_modification_id";
        public final static String LOGIN = "login";
        public final static String PASSWORD = "password";
        
        private Person(){}
    }
    
    public final static class Phone {
        public final static String TABLE = "fo_phone";
        public final static String ID = "id";
        public final static String LAST_MODIFICATION_ID = "last_modification_id";
        public final static String STATUS = "status";
        public final static String PHONE = "phone";
        public final static String PHONE_EXT = "phone_ext";
        public final static String NUMBER_TYPE = "number_type";
        public final static String COMMENT_TEXT = "comment_text";
        public final static String DISPLAY_INDEX = "display_index";
        public final static String CONTACT_ID = "contact_id";
        
        private Phone(){}
    }
    
    public final static class Vacation {
        public final static String TABLE = "fo_vacation";
        public final static String ID = "id";
        public final static String LAST_MODIFICATION_ID = "last_modification_id";
        public final static String START_DAY = "start_day";
        public final static String END_DAY = "end_day";
        public final static String PROVIDER_ID = "provider_id";

        private Vacation(){}
    }
    
    public final static class WorkingHour {
        public final static String TABLE = "fo_working_hour";        
        public final static String ID = "id";
        public final static String LAST_MODIFICATION_ID = "last_modification_id";
        public final static String WEEK_DAY = "week_day";
        public final static String START_TIME = "start_time";
        public final static String END_TIME = "end_time";
        public final static String PROVIDER_ID = "provider_id";
        
        private WorkingHour(){}
    }

    private DbTable(){}
}
