package CodeKey;

import java.math.BigInteger;

/**
 * Configuration class for network communication within Android and PC devices.
 * 
 * It defines 32-bit integer signal type as follow:
 * 
 * <pre>
 * {@code
 * 	DDDDDDDDDDDD S dddddddddddd s C IIIII
 * }
 * </pre>
 * 
 * where:
 * 
 * <ul>
 * <li>The first five, the least significant, bits ("I" bits) define the type
 * (ID) of controller, which signal is sent (the list of controllers is listed
 * here: {@link Device}).</li>
 * <li>Bit "C" keeps track of the amount of data sent by the controller in the
 * signal:
 * <ul>
 * <li>0 - signal includes a single chunk of data, the remaining 12 bits of a
 * signal ("D" bits) is empty and will not be taken into account. The list of
 * available controller signals are listed here: {@link DeviceSignal}.</li>
 * <li>1 - Signal is full and in most common it holds a pair of coordinates; X
 * on the 12 more significant bits ("d" bits) and Y on the remaining 12 ("D"
 * bits).</li>
 * </ul>
 * <li>24 bits are allocated for storing data generated by the controller. They
 * consist of either pair of 12 bits that storing coordinates X and Y (in most
 * common on "d" bits and "D" bits) or single signal stored at 12, more
 * significant, bits ("d" bits).</li>
 * <li>Bit "S" keeps a sign of integer, stored at "D" bits. It can be either:
 * <ul>
 * <li>0 - when integer is positive, including 0,</li>
 * <li>1 - when integer is negative.</li>
 * </ul>
 * <li>Bit "s" keeps a sign of integer, stored at "d" bits. It can be either:
 * <ul>
 * <li>0 - when integer is positive, including 0,</li>
 * <li>1 - when integer is negative.</li>
 * </ul>
 * </ul>
 * 
 * <pre>
 * {
 * 	&#064;code
 * 	int signal = Helper.encodeSignal(N.Device.KEYBOARD,
 * 			N.DeviceDataCounter.SINGLE, N.DeviceSignal.MOUSE_LPM);
 * 	int[] ret = Helper.decodeSignal(signal);
 * }
 * </pre>
 */

public final class N {

	public static final class Shift {
		/** */
		public static final int DEVICE = 0;
		/** */
		public static final int DEV_DATA_COUNTER = DEVICE + 5;
		/** */
		public static final int DEV_SIGNAL_1_SIGN = DEV_DATA_COUNTER + 1;
		/** */
		public static final int DEV_SIGNAL_1 = DEV_SIGNAL_1_SIGN + 1;
		/** */
		public static final int DEV_SIGNAL_2_SIGN = DEV_SIGNAL_1 + 12;
		/** */
		public static final int DEV_SIGNAL_2 = DEV_SIGNAL_2_SIGN + 1;
		
		/** */
		public static final int SYSTEM = 1;
	}

	/**
	 *
	 */
	protected static final class Bitmasks {
		/** */
		public static final int BIT1 = Integer.parseInt("1", 2) << 0;
		public static final int BIT2 = Integer.parseInt("1", 2) << 1;
		public static final int BIT3 = Integer.parseInt("1", 2) << 2;
		public static final int BIT4 = Integer.parseInt("1", 2) << 3;
		public static final int BIT5 = Integer.parseInt("1", 2) << 4;
		public static final int DEVICE = Integer.parseInt("11111", 2);
		public static final int DEV_DATA_COUNTER = Integer.parseInt("1", 2) << Shift.DEV_DATA_COUNTER;
		public static final int DEV_SIGNAL_1_SIGN = Integer.parseInt("1", 2) << Shift.DEV_SIGNAL_1_SIGN;
		public static final int DEV_SIGNAL_1 = Integer.parseInt("111111111111",
				2) << Shift.DEV_SIGNAL_1;
		public static final int DEV_SIGNAL_2_SIGN = Integer.parseInt("1", 2) << Shift.DEV_SIGNAL_2_SIGN;
		public static final int DEV_SIGNAL_2 = Integer.parseInt("111111111111",
				2) << Shift.DEV_SIGNAL_2;

		/**
		 *
		 */
		public static final int bit_mask(int... bitmasks) {
			int bitmask = Integer.parseInt("0", 2);
			for (int submask : bitmasks) {
				bitmask = bitmask | submask;
			}
			return bitmask;
		}
	}

	/**
	 *
	 */
	public static final class System {
		public static final byte UNKNOW = 		(byte) Integer.parseInt("00000000", 2);
		public static final byte LINUX = 		(byte) Integer.parseInt("00000001", 2);
		public static final byte WINDOWS = 		(byte) Integer.parseInt("00000010", 2);
		public static final byte BSD = 			(byte) Integer.parseInt("00000011", 2);
	}
	
	/**
	 * 		byte signal = N.Signal.encodeSignal(N.Signal.NEED_CONNECTION, N.System.WINDOWS);
		Log.d("APP",String.valueOf(
				signal));
		Log.d("APP",String.valueOf(
				N.Signal.decodeSignal(signal)));
		Log.d("APP",String.valueOf(
				N.Signal.decodeSystem(signal)));
	 */
	public static final class Signal {
		public static final byte NEED_AUTHORIZATION = (byte) Integer.parseInt(
				"00000000", 2);
		public static final byte NEED_CONNECTION = (byte) Integer.parseInt(
				"00000001", 2);
		
		public static final byte encodeSignal(byte signal, byte system) {
			return (byte) (signal + (system << Shift.SYSTEM));
		}
		
		public static final byte decodeSignal(byte signal) {
			return (byte) (signal & Bitmasks.bit_mask(Bitmasks.BIT1));
		}
		
		public static final byte decodeSystem(byte signal) {
			return (byte) ((signal & Bitmasks.bit_mask(Bitmasks.BIT2,Bitmasks.BIT3)) >> Shift.SYSTEM);
		}
		
	}

	/**
	 * 
	 */
	public static final class Device {
		public static final int MOUSE = Integer.parseInt("00000", 2);
		public static final int KEYBOARD = Integer.parseInt("00001", 2);
		public static final int WHEEL = Integer.parseInt("00010", 2);
		public static final int SPEAKER = Integer.parseInt("00011", 2);// ?
		public static final int VJOY = Integer.parseInt("00100", 2);// ?

		public static final int EXIT = Integer.parseInt("11111", 2);// ?
	}

	/**
	 * 
	 */
	public static final class DeviceDataCounter {
		public static final int SINGLE = Integer.parseInt("0", 2);
		public static final int DOUBLE = Integer.parseInt("1", 2);
	}

	/**
	 * 
	 */
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
		public static final int KEYBOARD_ESC = Integer.parseInt("000000011011",
				2);
		public static final int KEYBOARD_SPACE = Integer.parseInt(
				"000000100000", 2);
		public static final int KEYBOARD_CONTROL = Integer.parseInt(
				"000000000110", 2);
		public static final int KEYBOARD_BACKSPACE = Integer.parseInt(
				"000000000111", 2);
		public static final int KEYBOARD_HOME = Integer.parseInt(
				"000000001000", 2);
		public static final int KEYBOARD_END = Integer.parseInt(
				"000000001001", 2);
		public static final int KEYBOARD_DELETE = Integer.parseInt(
				"000000001010", 2);
		
		
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
		public static final int SPEAKER_RUN= Integer.parseInt("000000000000",
				2);
		
		public static final int SPEAKER_PUNCTUATION= Integer.parseInt("000000000001",
				2);
		public static final int SPEAKER_COMMANDS= Integer.parseInt("000000000010",
				2);
		public static final int SPEAKER_SEMICOLON= Integer.parseInt("000000000011",
				2);
		public static final int SPEAKER_COMMA= Integer.parseInt("000000000100",
				2);
		public static final int SPEAKER_POINT= Integer.parseInt("000000000101",
				2);
	}

	public static final class Exit {
		public static final int EXIT_NO_ERROR = Helper.encodeSignal(
				N.Device.EXIT, N.DeviceDataCounter.SINGLE, 0);
	}

	/**
	 * 
	 */
	public static final class Helper {

		public static final int DEVICE = 0;
		public static final int DEV_DATA_COUNTER = 1;
		public static final int DEV_SIGNAL_1_SIGN = 2;
		public static final int DEV_SIGNAL_1 = 3;
		public static final int DEV_SIGNAL_2_SIGN = 4;
		public static final int DEV_SIGNAL_2 = 5;

		/**
		 * 
		 */
		public static final int getSignal(int signal, int signalID) {
			switch (signalID) {
			case Helper.DEVICE:
				return (signal & Bitmasks.DEVICE);
			case Helper.DEV_DATA_COUNTER:
				return (signal & Bitmasks.DEV_DATA_COUNTER) >> Shift.DEV_DATA_COUNTER;
			case Helper.DEV_SIGNAL_1_SIGN:
				return (signal & Bitmasks.DEV_SIGNAL_1_SIGN) >> Shift.DEV_SIGNAL_1_SIGN;
			case Helper.DEV_SIGNAL_1:
				return (signal & Bitmasks.DEV_SIGNAL_1) >> Shift.DEV_SIGNAL_1;
			case Helper.DEV_SIGNAL_2_SIGN:
				return (signal & Bitmasks.DEV_SIGNAL_2_SIGN) >> Shift.DEV_SIGNAL_2_SIGN;
			case Helper.DEV_SIGNAL_2:
				return (signal & Bitmasks.DEV_SIGNAL_2) >> Shift.DEV_SIGNAL_2;
			default:
				return signal;
			}
		}

		/**
    	 * 
    	 */
		public static final int encodeSignal(int device, int dev_data_counter,
				int dev_signal_1) {
			return device + (dev_data_counter << Shift.DEV_DATA_COUNTER)
					+ (((dev_signal_1 < 0) ? 1 : 0) << Shift.DEV_SIGNAL_1_SIGN)
					+ (Math.abs(dev_signal_1) << Shift.DEV_SIGNAL_1);
		}

		/**
    	 * 
    	 */
		public static final int encodeSignal(int device, int dev_data_counter,
				int dev_signal_1, int dev_signal_2) {
			return device + (dev_data_counter << Shift.DEV_DATA_COUNTER)
					+ (((dev_signal_1 < 0) ? 1 : 0) << Shift.DEV_SIGNAL_1_SIGN)
					+ (Math.abs(dev_signal_1) << Shift.DEV_SIGNAL_1)
					+ (((dev_signal_2 < 0) ? 1 : 0) << Shift.DEV_SIGNAL_2_SIGN)
					+ (Math.abs(dev_signal_2) << Shift.DEV_SIGNAL_2);
		}

		/**
    	 * 
    	 */
		public static final int[] decodeSignal(int signal) {
			int[] output = new int[4];
			output[0] = getSignal(signal, Helper.DEVICE);
			output[1] = getSignal(signal, Helper.DEV_DATA_COUNTER);
			if (getSignal(signal, Helper.DEV_SIGNAL_1_SIGN) == 0) {
				output[2] = getSignal(signal, Helper.DEV_SIGNAL_1);
			} else {
				output[2] = -1 * getSignal(signal, Helper.DEV_SIGNAL_1);
			}
			if (getSignal(signal, Helper.DEV_SIGNAL_2_SIGN) == 0) {
				output[3] = getSignal(signal, Helper.DEV_SIGNAL_2);
			} else {
				output[3] = -1 * getSignal(signal, Helper.DEV_SIGNAL_2);
			}
			return output;
		}

		/*((!!!@++++++??__((<!!!!!!@@@@@@###&&&<<<<<<>>>>
		 * 
		 */
		public static final int[] decodeSignalFull(int signal) {
			int[] output = new int[6];
			output[0] = getSignal(signal, Helper.DEVICE);
			output[1] = getSignal(signal, Helper.DEV_DATA_COUNTER);
			output[4] = getSignal(signal, Helper.DEV_SIGNAL_1_SIGN);
			if (output[4] == 0) {
				output[2] = getSignal(signal, Helper.DEV_SIGNAL_1);
			} else {
				output[2] = -1 * getSignal(signal, Helper.DEV_SIGNAL_1);
			}
			output[5] = getSignal(signal, Helper.DEV_SIGNAL_2_SIGN);
			if (output[5] == 0) {
				output[3] = getSignal(signal, Helper.DEV_SIGNAL_2);
			} else {
				output[3] = -1 * getSignal(signal, Helper.DEV_SIGNAL_2);
			}
			return output;
		}
	}
}