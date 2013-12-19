package com.android.application;

public final class N {
	public static final class bitmasks  {
		public static final byte bit_1 =						(byte) Integer.parseInt("00000001",2);
		public static final byte bit_2 = 						(byte) Integer.parseInt("00000010",2);
		public static final byte bit_3 = 						(byte) Integer.parseInt("00000100",2);
		public static final byte bit_4 = 						(byte) Integer.parseInt("00001000",2);
		public static final byte bit_5 = 						(byte) Integer.parseInt("00010000",2);
		public static final byte bit_6 = 						(byte) Integer.parseInt("00100000",2);
		public static final byte bit_7 = 						(byte) Integer.parseInt("01000000",2);
		public static final byte bit_8 = 						(byte) Integer.parseInt("10000000",2);
		
		public static final byte bit_mask(byte... bitmasks) {
			byte bitmask = (byte) Integer.parseInt("00000000",2);
			for(byte submask : bitmasks) {
				bitmask = (byte) (bitmask | submask);
			}
			return bitmask;
		}
	}
	
    public static final class signal {
    	public static final byte need_authorization = 			(byte) Integer.parseInt("00000000",2);
    	public static final byte need_to_connect = 				(byte) Integer.parseInt("00000001",2);
    	
    	public static final byte getSignal(byte signal) {
    		byte bitmask = bitmasks.bit_mask(
    				bitmasks.bit_1);
    		return (byte) (signal & bitmask);
    	}
    }
    
    public static final class dev_signal {
    	public static final byte mouse = 						(byte) Integer.parseInt("00000000",2);
    	public static final byte mouse_move = 					(byte) Integer.parseInt("00001000",2);
    	public static final byte mouse_left = 					(byte) Integer.parseInt("00010000",2);
    	public static final byte mouse_middle = 				(byte) Integer.parseInt("00011000",2);
    	public static final byte mouse_right = 					(byte) Integer.parseInt("00100000",2);
    	
    	public static final byte keyboard =						(byte) Integer.parseInt("00000001",2);
    	
    	public static final byte getDevice(byte signal) {
    		byte bitmask = bitmasks.bit_mask(
    				bitmasks.bit_1,
    				bitmasks.bit_2,
    				bitmasks.bit_3);
    		return (byte) (signal & bitmask);
    	}
    	
    	public static final byte getDevSignal(byte signal) {
    		byte bitmask = bitmasks.bit_mask(
    				bitmasks.bit_4,
    				bitmasks.bit_5,
    				bitmasks.bit_6,
    				bitmasks.bit_7,
    				bitmasks.bit_8);
    		return (byte) (signal & bitmask);
    	}
    }
}