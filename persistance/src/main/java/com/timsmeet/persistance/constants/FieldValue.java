package com.timsmeet.persistance.constants;

public final class FieldValue {

    public static final class ActivityStatus {
        public final static String ACTIVE = "A";
        public final static String INACTIVE = "I";
        public final static String DELETED = "D";
        
        private ActivityStatus(){}
    }

    public static final class EmailPreferences {
        public final static String PLAIN_TEXT = "P";
        public final static String RICH_TEXT = "R";
        
        private EmailPreferences(){}
    }

    public static final class PhoneNumberType {
        public final static String MOBILE = "M";
        public final static String FAX = "F";
        public final static String LANDLINE = "L";
        
        private PhoneNumberType(){}
    }

    public static final class YesNo {
        public final static String YES = "Y";
        public final static String NO = "N";
        
        private YesNo(){}
    }

    public static final class TimeUnit {
        public final static String MINUTE = "M";
        public final static String HOUR = "H";
        public final static String DAY = "D";
        
        private TimeUnit(){}
    }

    public static final class WeekDay {
        public final static String MONDAY = "MONDAY";
        public final static String TUESDAY = "TUESDAY";
        public final static String WEDNESDAY = "WEDNESDAY";
        public final static String THURSDAY = "THURSDAY";
        public final static String FRIDAY = "FRIDAY";
        public final static String SATURDAY = "SATRUDAY";
        public final static String SUNDAY = "SUNDAY";
        
        private WeekDay(){}
    }

    public static final class AdditionalCostKind {
        public final static String MINIMAL_VALUE = "MINVAL";
        public final static String DELIVERY = "DELIVERY";
        public final static String PACKAGING = "PACK";
        
        private AdditionalCostKind(){}
    }

    public static final class FoodOrderStatus {
        public final static String ACTIVE = "A";
        public final static String CLOSED = "C";
        public final static String DELIVERED = "D";
        public final static String CANCELLED = "L";
        
        private FoodOrderStatus(){}
    }
    
    private FieldValue(){}
}
