package com.android.dialogs;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Switch;

import com.android.dialogs.elements.DialogListCore;

/** Displays the alert dialog box that contains {@link ScrollView}. 
 * 
 * Dialog that extends AlertDialogs
 *
 */
public class ScrollViewSwitchDialog extends ScrollViewDialog implements OnCheckedChangeListener {

	private static final String VIEW_SWITCH_OFF_ID = "view_switchOffID";
	private static final String VIEW_SWITCH_ON_ID = "view_switchOnID";
	private static final String SWITCHER_ID = "switcherID";
	
	private Switch viewSwitcher = null;
	private boolean viewSwitcherState = false;

	private View view_switchOff = null;
	private View view_switchOn = null;

    protected static ScrollViewSwitchDialog newInstance( Integer titleIconID, Integer titleID, ScrollView view, Integer switcherID, Integer view_switchOffID, Integer view_switchOnID, Integer positiveButtonID, Integer neutralButtonID, Integer negativeButtonID ) {
    	ScrollViewSwitchDialog dialog = new ScrollViewSwitchDialog();
        Bundle args = ScrollViewDialog.newInstance(titleIconID, titleID, view, positiveButtonID, neutralButtonID, negativeButtonID).getArguments();

        args.putInt(ScrollViewSwitchDialog.VIEW_SWITCH_OFF_ID, ( view_switchOffID != null ) ? view_switchOffID : 0);
        args.putInt(ScrollViewSwitchDialog.VIEW_SWITCH_ON_ID, ( view_switchOnID != null ) ? view_switchOnID : 0);
        args.putInt(ScrollViewSwitchDialog.SWITCHER_ID, ( switcherID != null ) ? switcherID : 0);
        
        dialog.setArguments(args);
        return dialog;
    }

	@Override
	public void buildDialogContent(Builder builder) {
		super.buildDialogContent(builder);
		if ( ScrollViewDialog.dialogInnerView != null ) {
			dialogInnerViewLogic();
			loadCurrentView(false);
		}
	}

    /** Displays alert dialog in given {@link Activity}.
     * 
     * Usage example:
     * 
     * <pre>
     * {@code
     * AddConnectionDialog.showDialog(this,
				ConnectionsActivity.DialogList.TAG_ADD_CONNECTION,
				ConnectionsActivity.DialogList.ID_TITLE_ICON_WIFI,
				ConnectionsActivity.DialogList.ID_TITLE_CONNECT_CONFIRMATION,
				ScrollViewSwitchDialog.getViewFromResource(this,R.layout.dialog_add_new_connection),
				R.id.s_connections_activity_connection_type_switch,
				R.id.rl_connections_activity_wifi_configurator_layout,
				R.id.rl_connections_activity_bt_configurator_layout,
				DialogListCore.ID_BUTTON_CONNECT,
				null,
				DialogListCore.ID_BUTTON_CANCEL);
     * }
     * </pre>
     * 
     * In the above code in <i>this</i> Activity dialog box is displayed, which is containing 2 of 3 buttons:
     * 
     * <ul>
     * 	<li> Positive button with label defined under {@link DialogListCore.ID_BUTTON_CONNECT},</li>
     * 	<li> Negative button with label defined under {@link DialogListCore.ID_BUTTON_CANCEL}.</li> 
     * </ul>
     * 
     * Neutral button will not be even displayed as null value is set. View can be set either by passing:
     * 
     * <ul>
     * 	<li> layout ID which contains {@link ScrollView} as a root element (see {@link #getViewFromResource(Activity, int)}),</li>
     * 	<li> view ID by {@link Activity#findViewById(int)}.</li>
     * </ul>
     * 
     * Next three IDs refers to:
     * 
     * <ul>
     * 	<li> switcher ID thanks to which views can be toggle,</li>
     * 	<li> relative layout that contains the first inner view for dialog,</li>
     * 	<li> relative layout that contains the second inner view for dialog.</li>
     * </ul>
     *
     * @param activity activity where dialog will be displayed
     * @param dialogIDTag a unique value to distinguish dialog boxes where more than one is displayed in one activity
     * @param titleIconID see {@link #newInstance(Integer, Integer, ScrollView, Integer, Integer, Integer, Integer, Integer, Integer)} parameters
     * @param titleID see {@link #newInstance(Integer, Integer, ScrollView, Integer, Integer, Integer, Integer, Integer, Integer)} parameters
     * @param view see {@link #newInstance(Integer, Integer, ScrollView, Integer, Integer, Integer, Integer, Integer, Integer)} parameters
     * @param switcherID see {@link #newInstance(Integer, Integer, ScrollView, Integer, Integer, Integer, Integer, Integer, Integer)} parameters
     * @param view_switchOffID see {@link #newInstance(Integer, Integer, ScrollView, Integer, Integer, Integer, Integer, Integer, Integer)} parameters
     * @param view_switchOnID see {@link #newInstance(Integer, Integer, ScrollView, Integer, Integer, Integer, Integer, Integer, Integer)} parameters
     * @param positiveButtonID see {@link #newInstance(Integer, Integer, ScrollView, Integer, Integer, Integer, Integer, Integer, Integer)} parameters
     * @param neutralButtonID see {@link #newInstance(Integer, Integer, ScrollView, Integer, Integer, Integer, Integer, Integer, Integer)} parameters
     * @param negativeButtonID see {@link #newInstance(Integer, Integer, ScrollView, Integer, Integer, Integer, Integer, Integer, Integer)} parameters
     */
	public static void showDialog(Activity activity, String dialogIDTag, Integer titleIconID, Integer titleID, ScrollView view, 
			Integer switcherID, Integer view_switchOffID, Integer view_switchOnID, 
			Integer positiveButtonID, Integer neutralButtonID, Integer negativeButtonID ) {
		ScrollViewSwitchDialog dialog = ScrollViewSwitchDialog.newInstance(
				titleIconID,titleID,view,switcherID,view_switchOffID,view_switchOnID,
				positiveButtonID,neutralButtonID,negativeButtonID);
        dialog.show(activity.getFragmentManager(), dialogIDTag);
	}
	
    
	@Override
    public void dialogInnerViewLogic() {
		super.dialogInnerViewLogic();

		Integer argsID = super.getArguments().getInt(ScrollViewSwitchDialog.SWITCHER_ID);
		if ( argsID !=  0 ) {
			viewSwitcher = (Switch) dialogInnerView.findViewById(argsID);
		}
		
    	argsID = super.getArguments().getInt(ScrollViewSwitchDialog.VIEW_SWITCH_OFF_ID);
		if ( argsID !=  0 ) {
	    	view_switchOff = dialogInnerView.findViewById(argsID);
		}
		
		argsID = super.getArguments().getInt(ScrollViewSwitchDialog.VIEW_SWITCH_ON_ID);
		if ( argsID !=  0 ) {
	    	view_switchOn = dialogInnerView.findViewById(argsID);
		}

    	if (viewSwitcher != null) {
	    	viewSwitcher.setOnCheckedChangeListener(this);
    	}
    }

	@Override
	public void onCheckedChanged(CompoundButton view, boolean switchState) {
		if (view == viewSwitcher) {
			loadCurrentView(switchState);
			viewSwitcher.setSelected(switchState);
		}
	}
	
	protected void loadCurrentView(boolean switchState) {
		viewSwitcherState = switchState;

		if (viewSwitcher != null && view_switchOff != null && view_switchOn != null) {
			if (switchState == false) {
				loadCurrentViewOff();
			} else {
				loadCurrentViewOn();
			}
		}
	}
	
	protected void loadCurrentViewOff() {
		view_switchOff.setVisibility(RelativeLayout.VISIBLE);
		view_switchOn.setVisibility(RelativeLayout.INVISIBLE);
	}

	protected void loadCurrentViewOn() {
		view_switchOff.setVisibility(RelativeLayout.INVISIBLE);
		view_switchOn.setVisibility(RelativeLayout.VISIBLE);
	}
	
	/**
	 * @return the viewSwitcher
	 */
	protected final Switch getViewSwitcher() {
		return viewSwitcher;
	}

	/**
	 * @return the view_switchOff
	 */
	protected final View getView_switchOff() {
		return view_switchOff;
	}

	/**
	 * @return the view_switchOn
	 */
	protected final View getView_switchOn() {
		return view_switchOn;
	}

	public final boolean isViewSwitcherState() {
		return viewSwitcherState;
	}

	protected final void setViewSwitcherState(boolean viewSwitcherState) {
		this.viewSwitcherState = viewSwitcherState;
	}
}