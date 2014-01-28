package Connect;

import java.util.UUID;
/**
 * @author Tomasz Strzalka
 */
public class BluetoothConfigurationClass {
	
	private UUID uuid = null;
	private String adress = null;
	/**
	 * 
	 * @param uuid
	 * @param adress
	 */
	public BluetoothConfigurationClass(UUID uuid, String adress) {
		super();
		this.uuid = uuid;
		this.adress = adress;
	}
	/**
	 * 
	 * @return uuid
	 */
	public final UUID getUuid() {
		return uuid;
	}
	/**
	 * 
	 * @param uuid
	 */
	public final void setUuid(UUID uuid) {
		this.uuid = uuid;
	}
	/**
	 * 
	 * @return adress
	 */
	public final String getAdress() {
		return adress;
	}
	
	/**
	 * 
	 * @param adress
	 */
	public final void setAdress(String adress) {
		this.adress = adress;
	}
	
	public static final class Profiles {
		public static final String SSP = 	"00001101-0000-1000-8000";
		public static final String inne = 	"04c6093b-0000-1000-8000";
	}
/**
 * 
 * @param btType
 * @param mac
 * @return method return uuid from mac
 */
	public static UUID generateUUIDfromMAC(String btType, String mac) {
		return UUID.fromString(btType+"-"+mac.replace(":", ""));
	}
}
