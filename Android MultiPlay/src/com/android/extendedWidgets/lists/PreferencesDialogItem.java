package com.android.extendedWidgets.lists;

import com.android.multiplay.R;

/** Element to display a list of possible options in the dialog box that opens 
 * when you call a menu with additional options for the item.
 * 
 * @author tomasz
 *
 */
public class PreferencesDialogItem {
	
	/** List of id for {@link PreferencesDialogItem} elements.
	 * 
	 * @author tomasz
	 *
	 */
	public static final class ID {
		public static final String ListOfConnections_edit = "ListOfConnections_edit";
		public static final String ListOfConnections_forget = "ListOfConnections_forget";
		public static final String connectionsActivity_setMainPriorityBT = "connectionsActivity_setMainPriorityBT";
		public static final String connectionsActivity_enableBT = "connectionsActivity_enableBT";
		public static final String connectionsActivity_disableBT = "connectionsActivity_disableBT";
		public static final String connectionsActivity_setMainPriorityWIFI = "connectionsActivity_setMainPriorityWIFI";
		public static final String connectionsActivity_enableWIFI = "connectionsActivity_enableWIFI";
		public static final String connectionsActivity_disableWIFI = "connectionsActivity_disableWIFI";
		public static final String returnTAG = "return";
	};
	
	/** List of id for {@link PreferencesDialogItem} icons for elements.
	 * 
	 * @author tomasz
	 *
	 */
	public static final class ICON {
		public static int icon_1 = R.drawable.connections_activity_icon_1;
	};
	
	/** List of id for {@link PreferencesDialogItem} element names.
	 * 
	 * @author tomasz
	 *
	 */
	public static final class NAME {
		public static int dialog_optionID_EMPTY = R.string.dialog_optionID_EMPTY;
		public static int dialog_optionID_EDIT_CONNECTION = R.string.dialog_optionID_EDIT_CONNECTION;
		public static int dialog_optionID_FORGET_IT = R.string.dialog_optionID_FORGET_IT;
		public static int dialog_optionID_SET_BT_PRIORITY = R.string.dialog_optionID_SET_BT_PRIORITY;
		public static int dialog_optionID_ENABLE_BT = R.string.dialog_optionID_ENABLE_BT;
		public static int dialog_optionID_DISABLE_BT = R.string.dialog_optionID_DISABLE_BT;
		public static int dialog_optionID_SET_WIFI_PRIORITY = R.string.dialog_optionID_SET_WIFI_PRIORITY;
		public static int dialog_optionID_ENABLE_WIFI = R.string.dialog_optionID_ENABLE_WIFI;
		public static int dialog_optionID_DISABLE_WIFI = R.string.dialog_optionID_DISABLE_WIFI;
		public static int dialog_optionID_RETURN = R.string.dialog_optionID_RETURN;

	};
	
	
	/**
	 * @param optionTAG
	 * @param option_iconID
	 * @param option_nameID
	 */
	public PreferencesDialogItem(String optionTAG, Integer option_iconID, Integer option_nameID) {
		if (optionTAG != null) {
			this.optionTAG = optionTAG;
		}
		if (option_iconID != null) {
			this.option_iconID = option_iconID;
		}
		if (option_nameID != null) {
			this.option_nameID = option_nameID;
		}
	}
	
	/**
	 * 
	 */
	private String optionTAG = ID.ListOfConnections_edit;
	/**
	 * 
	 */
	private int option_iconID = ICON.icon_1;
	/**
	 * 
	 */
	private int option_nameID = NAME.dialog_optionID_EMPTY;

	/**
	 * @return the option_iconID
	 */
	/**
	 * @return
	 */
	public final int getOption_iconID() {
		return option_iconID;
	}
	/**
	 * @param option_iconID the option_iconID to set
	 */
	/**
	 * @param option_iconID
	 */
	public final void setOption_iconID(int option_iconID) {
		this.option_iconID = option_iconID;
	}
	/**
	 * @return the option_nameID
	 */
	/**
	 * @return
	 */
	public final int getOption_nameID() {
		return option_nameID;
	}
	/**
	 * @param option_nameID the option_nameID to set
	 */
	/**
	 * @param option_nameID
	 */
	public final void setOption_nameID(int option_nameID) {
		this.option_nameID = option_nameID;
	}
	/**
	 * @return the optionTAG
	 */
	/**
	 * @return
	 */
	public final String getOptionTAG() {
		return optionTAG;
	}
	/**
	 * @param optionTAG the optionTAG to set
	 */
	/**
	 * @param optionTAG
	 */
	public final void setOptionTAG(String optionTAG) {
		this.optionTAG = optionTAG;
	}

	
}
