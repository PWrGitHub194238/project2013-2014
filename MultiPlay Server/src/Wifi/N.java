package Wifi;



import java.math.BigInteger;

public final class N {

	public static final class Shift {
		public static final int DEVICE = 0;
		public static final int DEV_DATA_COUNTER = DEVICE + 5;
		public static final int DEV_SIGNAL_1 = DEV_DATA_COUNTER + 1;
		public static final int DEV_SIGNAL_2 = DEV_SIGNAL_1 + 12;
		public static final int DEV_OTHER = DEV_SIGNAL_2 + 12;
	}

	protected static final class Bitmasks {
		public static final int BIT1 = Integer.parseInt("1", 2) << 0;
		public static final int BIT2 = Integer.parseInt("1", 2) << 1;
		public static final int BIT3 = Integer.parseInt("1", 2) << 2;
		public static final int BIT4 = Integer.parseInt("1", 2) << 3;
		public static final int BIT5 = Integer.parseInt("1", 2) << 4;
		public static final int DEVICE = Integer.parseInt("11111", 2);
		public static final int DEV_DATA_COUNTER = Integer.parseInt("1", 2) << Shift.DEV_DATA_COUNTER;
		public static final int DEV_SIGNAL_1 = Integer.parseInt("111111111111",
				2) << Shift.DEV_SIGNAL_1;
		public static final int DEV_SIGNAL_2 = Integer.parseInt("111111111111",
				2) << Shift.DEV_SIGNAL_2;
		public static final int DEV_OTHER = Integer.parseInt("11", 2) << Shift.DEV_OTHER;

		public static final int bit_mask(int... bitmasks) {
			int bitmask = Integer.parseInt("0", 2);
			for (int submask : bitmasks) {
				bitmask = bitmask | submask;
			}
			return bitmask;
		}
	}

	public static final class Signal {
		public static final byte NEED_AUTHORIZATION = (byte) Integer.parseInt(
				"00000000", 2);
		public static final byte NEED_CONNECTION = (byte) Integer.parseInt(
				"00000001", 2);
	}

	public static final class Device {
		public static final int MOUSE = Integer.parseInt("00000", 2);
		public static final int KEYBOARD = Integer.parseInt("00001", 2);
		public static final int WHEEL = Integer.parseInt("00010", 2);
		public static final int SPEAKER = Integer.parseInt("00011", 2);//?
		public static final int EXIT = Integer.parseInt("11111", 2);//?

		

	}

	public static final class DeviceDataCounter {
		public static final int SINGLE = Integer.parseInt("0", 2);
		public static final int DOUBLE = Integer.parseInt("1", 2);
	}

	public static final class DeviceSignal {
		public static final int MOUSE_LPM = Integer.parseInt("000000000000", 2);
		public static final int MOUSE_MPM = Integer.parseInt("000000000001", 2);
		public static final int MOUSE_PPM = Integer.parseInt("000000000010", 2);
		public static final int KEYBOARD_LEFT = Integer.parseInt(
				"000000000000", 2);
		public static final int KEYBOARD_RIGHT = Integer.parseInt(
				"000000000001", 2);
		public static final int KEYBOARD_DOWN = Integer.parseInt(
				"000000000010", 2);
		public static final int KEYBOARD_UP = Integer.parseInt("000000000011",
				2);
		public static final int KEYBOARD_SHIFT = Integer.parseInt(
				"000000000100", 2);
		public static final int KEYBOARD_ENTER = Integer.parseInt(
				"000000000101", 2);
		public static final int KEYBOARD_ESC = 27;
		public static final int KEYBOARD_SPACE = 32;
		public static final int KEYBOARD_CONTROL = Integer.parseInt(
				"000000000110", 2);
		public static final int KEYBOARD_BACKSPACE = Integer.parseInt(
				"000000000111", 2);

		public static int KEYBOARD_KEY_TO_INT(String key) {
			int integer;
			BigInteger bi = new BigInteger(key.getBytes());
			integer = bi.intValue();
			return integer;
		}

		// may be useful
		public static String KEYBOARD_KEY_TO_STRING(int i) {
			BigInteger b = BigInteger.valueOf(i);
			String w = new String(b.toByteArray());
			return w;
		}
	}

	public static final class Helper {

		public static final int DEVICE = 0;
		public static final int DEV_DATA_COUNTER = 1;
		public static final int DEV_SIGNAL_1 = 2;
		public static final int DEV_SIGNAL_2 = 3;
		public static final int DEV_OTHER = 4;

		public static final int getSignal(int signal, int signalID) {
			switch (signalID) {
			case Helper.DEVICE:
				return (signal & Bitmasks.DEVICE);
			case Helper.DEV_DATA_COUNTER:
				return (signal & Bitmasks.DEV_DATA_COUNTER) >> Shift.DEV_DATA_COUNTER;
			case Helper.DEV_SIGNAL_1:
				return (signal & Bitmasks.DEV_SIGNAL_1) >> Shift.DEV_SIGNAL_1;
			case Helper.DEV_SIGNAL_2:
				return (signal & Bitmasks.DEV_SIGNAL_2) >> Shift.DEV_SIGNAL_2;
			case Helper.DEV_OTHER:
				return (signal & Bitmasks.DEV_OTHER) >> Shift.DEV_OTHER;
			default:
				return signal;
			}
		}

		public static final int encodeSignal(int device, int dev_data_counter,
				int dev_signal) {
			return device + (dev_data_counter << Shift.DEV_DATA_COUNTER)
					+ (dev_signal << Shift.DEV_SIGNAL_1);
		}

		public static final int encodeSignal(int device, int dev_data_counter,
				int dev_signal_1, int dev_signal_2) {
			return device + (dev_data_counter << Shift.DEV_DATA_COUNTER)
					+ (dev_signal_1 << Shift.DEV_SIGNAL_1)
					+ (dev_signal_2 << Shift.DEV_SIGNAL_2);
		}

		public static final int encodeSignal(int device, int dev_data_counter,
				int dev_signal_1, int dev_signal_2, int other) {
			return device + (dev_data_counter << Shift.DEV_DATA_COUNTER)
					+ (dev_signal_1 << Shift.DEV_SIGNAL_1)
					+ (dev_signal_2 << Shift.DEV_SIGNAL_2)
					+ (other << Shift.DEV_OTHER);
		}

		public static final int[] decodeSignal(int signal) {
			int[] output = new int[5];
			output[0] = getSignal(signal, Helper.DEVICE);
			output[1] = getSignal(signal, Helper.DEV_DATA_COUNTER);
			output[2] = getSignal(signal, Helper.DEV_SIGNAL_1);
			output[3] = getSignal(signal, Helper.DEV_SIGNAL_2);
			output[4] = getSignal(signal, Helper.DEV_OTHER);
			// 11 1 2 23 1
			return output;
		}

	}

}