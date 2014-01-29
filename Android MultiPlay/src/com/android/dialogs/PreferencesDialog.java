package com.android.dialogs;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.android.extendedWidgets.lists.PreferencesDialogList;

/** Displays the alert dialog box that contains {@link ScrollView}. 
 * 
 * Dialog that extends {@link ScrollViewDialog}. It usually show additional options after log tapping some button.
 *
 */
/**
 * @author Windows 7
 *
 */
public class PreferencesDialog extends ScrollViewDialog {
	
	/**
	 * 
	 */
	private static ScrollView scrollView = null;
	/**
	 * 
	 */
	private static ListView optionElements = null;
	/**
	 * 
	 */
	private static PreferencesDialogList listAdapter = null;
	/**
	 * 
	 */
	private static RelativeLayout.LayoutParams adapterLayoutParams = null;
	
    /**
     * @param listAdapter
     * @return
     */
    protected static PreferencesDialog newInstance(PreferencesDialogList listAdapter) {
    	PreferencesDialog dialog = new PreferencesDialog();
        Bundle args = AlertDialogs.newInstance(null,null,null,null,null,null).getArguments();

        PreferencesDialog.listAdapter = listAdapter;
        
        dialog.setArguments(args);
        return dialog;
    }

	/* (non-Javadoc)
	 * @see com.android.dialogs.ScrollViewDialog#buildDialogContent(android.app.AlertDialog.Builder)
	 */
	@Override
	public void buildDialogContent(Builder builder) {
		if (PreferencesDialog.listAdapter != null) {
			

			LinearLayout layout = new LinearLayout(getActivity());
			layout.setOrientation(LinearLayout.VERTICAL);
			Log.d("Dialogs",String.valueOf(WindowManager.LayoutParams.MATCH_PARENT));
			layout.setMinimumHeight(300);
			LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(400, 400);
			
			
			layout.setLayoutParams(lp);
			scrollView = new ScrollView(getActivity());
			scrollView.setFillViewport(true);
			scrollView.setMinimumHeight(300);
			
			optionElements = new ListView(getActivity());
			optionElements.setAdapter(listAdapter);
			
			adapterLayoutParams = new RelativeLayout.LayoutParams(
					RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);

			scrollView.addView(optionElements, adapterLayoutParams);
			layout.addView(scrollView,new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
			builder.setView(layout);
		}

		if ( ScrollViewDialog.dialogInnerView != null ) {
			dialogInnerViewLogic();
		}
	}
	
	/* (non-Javadoc)
	 * @see com.android.dialogs.AlertDialogs#onShow(android.content.DialogInterface)
	 */
	@Override
	public void onShow(DialogInterface dialog) {
		super.onShow(dialog);
	}

	/**
	 * @param activity
	 * @param dialogIDTag
	 * @param listAdapter
	 */
	public static void showDialog(Activity activity, String dialogIDTag, PreferencesDialogList listAdapter ) {
		PreferencesDialog dialog = PreferencesDialog.newInstance(listAdapter);
        dialog.show(activity.getFragmentManager(), dialogIDTag);
	}
    
	/* (non-Javadoc)
	 * @see com.android.dialogs.ScrollViewDialog#dialogInnerViewLogic()
	 */
	@Override
    public void dialogInnerViewLogic() {
		super.dialogInnerViewLogic();

	}
}