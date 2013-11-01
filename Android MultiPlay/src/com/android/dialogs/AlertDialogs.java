package com.android.dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;


public class AlertDialogs extends DialogFragment {
    
	private static final String TITLE_ICON_ID = "titleIconID";
	private static final String TITLE_ID = "titleID";
	private static final String MESSAGE_ID = "messageID";
	private static final String POSITIVE_BUTTON_ID = "positiveButtonID";
	private static final String NEUTRAL_BUTTON_ID = "neutralButtonID";
	private static final String NEGATIVE_BUTTON_ID = "negativeButtonID";
	
	private DialogButtonClickListener dialogButtonClickListener = null;
    
    public static AlertDialogs newInstance( Integer titleIconID, Integer titleID, Integer messageID, Integer positiveButtonID, Integer neutralButtonID, Integer negativeButtonID ) {
    	AlertDialogs mainActivityDialog = new AlertDialogs();

        Bundle args = new Bundle();
        args.putInt(AlertDialogs.TITLE_ID, ( titleID != null ) ? titleID : 0);
        args.putInt(AlertDialogs.MESSAGE_ID, ( messageID != null ) ? messageID : 0);
        args.putInt(AlertDialogs.POSITIVE_BUTTON_ID, ( positiveButtonID != null ) ? positiveButtonID : -1);
        args.putInt(AlertDialogs.NEUTRAL_BUTTON_ID, ( neutralButtonID != null ) ? neutralButtonID : -1);
        args.putInt(AlertDialogs.NEGATIVE_BUTTON_ID, ( negativeButtonID != null ) ? negativeButtonID : -1);
        args.putInt(AlertDialogs.TITLE_ICON_ID, ( titleIconID != null ) ? titleIconID : -1);
        mainActivityDialog.setArguments(args);
        return mainActivityDialog;
    }
    
    
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
        	dialogButtonClickListener = (DialogButtonClickListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement DialogButtonClickListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
    	Integer argsID = null;
    	AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        
        argsID = super.getArguments().getInt(AlertDialogs.TITLE_ICON_ID);
        if ( argsID !=  -1 ) {
            builder.setIcon(argsID);
        }

        builder.setTitle(
        		super.getArguments().getInt(AlertDialogs.TITLE_ID));
        builder.setMessage(
        		super.getArguments().getInt(AlertDialogs.MESSAGE_ID));
        
        argsID = super.getArguments().getInt(AlertDialogs.POSITIVE_BUTTON_ID);
        if ( argsID !=  -1 ) {
        builder.setPositiveButton(
        		argsID, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                	   dialogButtonClickListener.onDialogPositiveClick(AlertDialogs.this);
                   }
               });
        }
        
        argsID = super.getArguments().getInt(AlertDialogs.NEUTRAL_BUTTON_ID);
        if ( argsID !=  -1 ) {
        builder.setNeutralButton(
        		argsID, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                	   dialogButtonClickListener.onDialogNeutralClick(AlertDialogs.this);
                   }
               });
        }
        
        argsID = super.getArguments().getInt(AlertDialogs.NEGATIVE_BUTTON_ID);
        if ( argsID !=  -1 ) {
        builder.setNegativeButton(
        		argsID, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                	   dialogButtonClickListener.onDialogNegativeClick(AlertDialogs.this);
                   }
               });
        }
        
        return builder.create();
    }
    
    public static void showDialog(Activity activity, String dialogIDTag, Integer titleIconID, Integer titleID, Integer messageID, Integer positiveButtonID, Integer neutralButtonID, Integer negativeButtonID ) {
		DialogFragment dialog = AlertDialogs.newInstance(titleIconID,titleID,messageID,positiveButtonID,neutralButtonID,negativeButtonID);
        dialog.show(activity.getFragmentManager(), dialogIDTag);
	}
}