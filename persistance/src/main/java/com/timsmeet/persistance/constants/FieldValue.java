package com.timsmeet.persistance.constants;

public interface FieldValue {

	public static interface ActivityStatus {
		public final static String Active = "A";
		public final static String Inactive = "I";
		public final static String Deleted = "D";
	}

	public static interface EmailPreferences {
		public final static String PlainText = "P";
		public final static String RichText = "R";
	}

	public static interface PhoneNumberType {
		public final static String Mobile = "M";
		public final static String Fax = "F";
		public final static String Landline = "L";
	}

	public static interface YesNo {
		public final static String Yes = "Y";
		public final static String No = "N";
	}

	public static interface TimeUnit {
		public final static String Minute = "M";
		public final static String Hour = "H";
		public final static String Day = "D";
	}

	public static interface WeekDay {
		public final static String Monday = "MONDAY";
		public final static String Tuesday = "TUESDAY";
		public final static String Wednesday = "WEDNESDAY";
		public final static String Thursday = "THURSDAY";
		public final static String Friday = "FRIDAY";
		public final static String Saturday = "SATRUDAY";
		public final static String Sunday = "SUNDAY";
	}

	public static interface AdditionalCostKind {
		public final static String MinimalValue = "MINVAL";
		public final static String Delivery = "DELIVERY";
		public final static String Packaging = "PACK";
	}
	
	public static interface FoodOrderStatus {
		public final static String Active = "A";
		public final static String Closed = "C";
		public final static String Delivered = "D";
		public final static String Cancelled = "L";
	}

}
