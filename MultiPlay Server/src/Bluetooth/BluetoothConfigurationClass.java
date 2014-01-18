package Bluetooth;

import java.util.UUID;

public class BluetoothConfigurationClass {
	
	private UUID uuid = null;
	private String adress = null;
	
	public BluetoothConfigurationClass(UUID uuid, String adress) {
		super();
		this.uuid = uuid;
		this.adress = adress;
	}
	
	public final UUID getUuid() {
		return uuid;
	}
	public final void setUuid(UUID uuid) {
		this.uuid = uuid;
	}
	public final String getAdress() {
		return adress;
	}
	public final void setAdress(String adress) {
		this.adress = adress;
	}
	
	 
	public static final class Profiles {
		public static final String SSP = 	"00001101-0000-1000-8000";
		public static final String inne = 	"04c6093b-0000-1000-8000";
	}

	public static UUID generateUUIDfromMAC(String btType, String mac) {
		return UUID.fromString(btType+"-"+mac.replace(":", ""));
	}
}
