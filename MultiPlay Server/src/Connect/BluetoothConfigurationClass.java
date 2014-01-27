package Connect;

import java.util.UUID;
/*
 * @author Tomasz Strzalka
 */
public class BluetoothConfigurationClass {
	
	private UUID uuid = null;
	private String adress = null;
	/*
	 * @param uuid - uuid to bluetooth connection
	 * @param adress - adress to bluetooth connection
	 * @see BluetoothConfigurationClass constructor to class
	 */
	public BluetoothConfigurationClass(UUID uuid, String adress) {
		super();
		this.uuid = uuid;
		this.adress = adress;
	}
	/*
	 * @return uuid return  uuid 
	 * @see getUuid
	 */
	public final UUID getUuid() {
		return uuid;
	}
	/*
	 * @param uuid set a uuid to class
	 * @see setUuid
	 */
	public final void setUuid(UUID uuid) {
		this.uuid = uuid;
	}
	/*
	 * @return adress to bluetooth connection
	 * @see getAdress
	 */
	public final String getAdress() {
		return adress;
	}
	
	/*
	 *  @param uuid set a adress to class
	 *  @see setAdress
	 */
	public final void setAdress(String adress) {
		this.adress = adress;
	}
	
	 
	public static final class Profiles {
		public static final String SSP = 	"00001101-0000-1000-8000";
		public static final String inne = 	"04c6093b-0000-1000-8000";
	}
	/*
	 * @param btType
	 * @param mac
	 * @see generateUUIDfromMAC generate a UUid from MAC
	 * @return UUID.fromString(btType+"-"+mac.replace(":", "")) 
	 */
	public static UUID generateUUIDfromMAC(String btType, String mac) {
		return UUID.fromString(btType+"-"+mac.replace(":", ""));
	}
}
