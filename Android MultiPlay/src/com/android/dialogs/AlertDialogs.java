package com.android.dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnShowListener;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;


public class AlertDialogs extends DialogFragment implements OnShowListener {
	
	private static AlertDialog builder = null;
	private static AlertDialog.Builder dialogBuilder = null;
    
	private static final String TITLE_ICON_ID = "titleIconID";
	private static final String TITLE_ID = "titleID";
	private static final String MESSAGE_ID = "messageID";
	private static final String POSITIVE_BUTTON_ID = "positiveButtonID";
	private static final String NEUTRAL_BUTTON_ID = "neutralButtonID";
	private static final String NEGATIVE_BUTTON_ID = "negativeButtonID";
	
	private DialogButtonClickListener dialogButtonClickListener = null;
	
	private static Button positiveButton = null;
	private static Button neutralButton = null;
	private static Button negativeButton = null;
    
    public static AlertDialogs newInstance( Integer titleIconID, Integer titleID, Integer messageID, Integer positiveButtonID, Integer neutralButtonID, Integer negativeButtonID ) {
    	AlertDialogs dialog = new AlertDialogs();
    	
        Bundle args = new Bundle();
        args.putInt(AlertDialogs.TITLE_ID, ( titleID != null ) ? titleID : 0);
        args.putInt(AlertDialogs.MESSAGE_ID, ( messageID != null ) ? messageID : 0);
        args.putInt(AlertDialogs.POSITIVE_BUTTON_ID, ( positiveButtonID != null ) ? positiveButtonID : 0);
        args.putInt(AlertDialogs.NEUTRAL_BUTTON_ID, ( neutralButtonID != null ) ? neutralButtonID : 0);
        args.putInt(AlertDialogs.NEGATIVE_BUTTON_ID, ( negativeButtonID != null ) ? negativeButtonID : 0);
        args.putInt(AlertDialogs.TITLE_ICON_ID, ( titleIconID != null ) ? titleIconID : 0);
        dialog.setArguments(args);
        return dialog;
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
    	dialogBuilder = new AlertDialog.Builder(getActivity());
    	
    	Integer argsID = null;
        argsID = super.getArguments().getInt(AlertDialogs.TITLE_ICON_ID);
        if ( argsID !=  0 ) {
        	dialogBuilder.setIcon(argsID);
        }

        argsID = super.getArguments().getInt(AlertDialogs.TITLE_ID);
        if ( argsID !=  0 ) {
        	dialogBuilder.setTitle(
        		super.getArguments().getInt(AlertDialogs.TITLE_ID));
        }
        
        buildDialogContent(dialogBuilder,argsID);
        buildDialogButtons(dialogBuilder,argsID,dialogButtonClickListener);
        
        AlertDialogs.builder = AlertDialogs.dialogBuilder.create();
        AlertDialogs.builder.setOnShowListener(this);
        return AlertDialogs.builder;
    }
    
    public void buildDialogButtons(Builder builder, Integer argsID, final DialogButtonClickListener eventListener ) {
    	argsID = super.getArguments().getInt(AlertDialogs.POSITIVE_BUTTON_ID);
        if ( argsID !=  0 ) {
	        builder.setPositiveButton(
	        		argsID, new DialogInterface.OnClickListener() {
	                   public void onClick(DialogInterface dialog, int id) {
	                	   eventListener.onDialogPositiveClick(AlertDialogs.this);
	                   }
	               });
        }
        
        argsID = super.getArguments().getInt(AlertDialogs.NEUTRAL_BUTTON_ID);
        if ( argsID !=  0 ) {
        builder.setNeutralButton(
        		argsID, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                	   eventListener.onDialogNeutralClick(AlertDialogs.this);
                   }
               });
        }
        
        argsID = super.getArguments().getInt(AlertDialogs.NEGATIVE_BUTTON_ID);
        if ( argsID !=  0 ) {
        builder.setNegativeButton(
        		argsID, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                	   eventListener.onDialogNegativeClick(AlertDialogs.this);
                   }
               });
        }
    }
    
    public void buildDialogContent(Builder builder, Integer argsID) {
    	argsID = super.getArguments().getInt(AlertDialogs.MESSAGE_ID);
        if ( argsID !=  0 ) {
        	builder.setMessage(
            		super.getArguments().getInt(AlertDialogs.MESSAGE_ID));
        }
	}

	public static void showDialog(Activity activity, String dialogIDTag, Integer titleIconID, Integer titleID, Integer messageID, Integer positiveButtonID, Integer neutralButtonID, Integer negativeButtonID ) {
		DialogFragment dialog = AlertDialogs.newInstance(titleIconID,titleID,messageID,positiveButtonID,neutralButtonID,negativeButtonID);
        dialog.show(activity.getFragmentManager(), dialogIDTag);
	}

	@Override
	public void onShow(DialogInterface dialog) {
		positiveButton = ((AlertDialog)dialog).getButton(AlertDialog.BUTTON_POSITIVE);
		neutralButton = ((AlertDialog)dialog).getButton(AlertDialog.BUTTON_NEUTRAL);
		negativeButton = ((AlertDialog)dialog).getButton(AlertDialog.BUTTON_NEGATIVE);
		Log.d("Dialogs","Buttons init");
	}
	
	public static final Button getPositiveButton() {
		return positiveButton;
	}

	public static final Button getNeutralButton() {
		return neutralButton;
	}

	public static final Button getNegativeButton() {
		return negativeButton;
	}
}