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

import com.android.dialogs.elements.DialogListCore;
import com.android.multiplay.ButtonsFocusChangeListener;
import com.android.multiplay.ConnectionsActivity;
import com.android.multiplay.MainActivity;
import com.android.multiplay.R;

/** Displays the basic version of the alert dialog box that contains text message. 
 * 
 * It can can consist of the following elements:
 * 
 * <ul>
 * 	<li> icon - is displayed in the upper left corner of the dialog (see {@link AlertDialogs#TITLE_ICON_ID}), </li>
 * 	<li> title - it is displayed to the right of the icon that was described above (see {@link AlertDialogs#TITLE_ID}), </li>
 * 	<li> message - text displayed in the dialog (see {@link AlertDialogs#MESSAGE_ID}). </li>
 * 	<li> button#1 - a positive button, which is usually responsible for the acceptance / 
 * confirmation / close dialog with a positive result (see {@link AlertDialogs#POSITIVE_BUTTON_ID}). </li>
 * 	<li> button#2 - a neutral button, which is usually responsible for the display of additional help / 
 * execution of custom commands in the dialog box (see {@link AlertDialogs#NEUTRAL_BUTTON_ID}). </li>
 * 	<li> button#3 - a negative button, which is usually responsible for the cancellation of all operations / 
 * refusal to grant permissions (see {@link AlertDialogs#NEGATIVE_BUTTON_ID}). </li>
 * </ul>
 * 
 * In the absence of any component of the above, the dialog will be created 
 * with default values ​​(applies to the text) or without them (e.g. buttons, icons).
 * 
 * {@link Activity}, which contains a dialog must implement {@link ButtonsFocusChangeListener} 
 * in order to receive signals from either {@link #positiveButton}, {@link #neutralButton} or {@link #negativeButton}.
 * 
 * Each activity, using dialog boxes, also should have befriended inner class (e.g. {@link MainActivity.DialogList}),
 * which defines the values ​​of specific dialog boxes that are displayed in that activity. The values ​​that are common 
 * for many dialogs (such as button labels, dialog icons) are stored in {@link DialogListCore} class.
 * 
 * @see DialogListCore
 * @see DialogListCore#ID_TITLE_NO_TITLE
 * @see DialogListCore#ID_MESSAGE_NO_MESSAGE
 */
public class AlertDialogs extends DialogFragment implements OnShowListener {
	
	/** Builder for {@link dialogBuilder}. */
	private static AlertDialog builder = null;
	
	/** Dialog builder for this class.
	 * It builds the entire dialog window.
	 */
	private static AlertDialog.Builder dialogBuilder = null;
    
	/** Key for key-value pair that will be passed to dialog builder method: {@link #onCreateDialog(Bundle)}.
	 * ID resource to the icon will be passed.
	 */
	private static final String TITLE_ICON_ID = "titleIconID";
	/** Key for key-value pair that will be passed to dialog builder method: {@link #onCreateDialog(Bundle)}.
	 * ID resource to the title will be passed.
	 */
	private static final String TITLE_ID = "titleID";
	/** Key for key-value pair that will be passed to dialog builder method: {@link #onCreateDialog(Bundle)}.
	 * ID resource to the message will be passed.
	 */
	private static final String MESSAGE_ID = "messageID";
	/** Key for key-value pair that will be passed to dialog builder method: {@link #onCreateDialog(Bundle)}.
	 * ID resource to the positive button label will be passed.
	 */
	private static final String POSITIVE_BUTTON_ID = "positiveButtonID";
	/** Key for key-value pair that will be passed to dialog builder method: {@link #onCreateDialog(Bundle)}.
	 * ID resource to the neutral button label will be passed.
	 */
	private static final String NEUTRAL_BUTTON_ID = "neutralButtonID";
	/** Key for key-value pair that will be passed to dialog builder method: {@link #onCreateDialog(Bundle)}.
	 * ID resource to the negative button label will be passed.
	 */
	private static final String NEGATIVE_BUTTON_ID = "negativeButtonID";
	
	/** Activity to which dialog will be added.
	 * Activity must implements {@link DialogButtonClickListener}.
	 * @see #buildDialogButtons(Builder, DialogButtonClickListener)
	 */
	private DialogButtonClickListener dialogButtonClickListener = null;
	
	/** A reference to the positive dialog button, if any.
	 * Button, which is usually responsible for the acceptance / confirmation / close dialog with a positive result.
	 */
	private static Button positiveButton = null;
	
	/** A reference to the neutral dialog button, if any.
	 * Button, which is usually responsible for the display of additional help / execution of custom commands in the dialog box.
	 */
	private static Button neutralButton = null;
	
	/** A reference to the negative dialog button, if any.
	 * Button, which is usually responsible for the cancellation of all operations / refusal to grant permissions.
	 */
	private static Button negativeButton = null;
    
	/** Creates a new instance of the dialog box using the specified values.
	 * 
	 * With no data specified, it will return default values. Each activity, using dialog boxes, 
	 * should have befriended inner class (e.g. {@link MainActivity.DialogList}), which defines the values ​​
	 * of specific dialog boxes that are displayed in that activity, 
	 * for instance {@link ConnectionsActivity} has fields like:
	 * 
	 * <ul>
	 * 	<li> {@link ConnectionsActivity.DialogList#ID_TITLE_ADD_CONNECTION}, </li>
	 * 	<li> {@link ConnectionsActivity.DialogList#ID_TITLE_CONNECT_CONFIRMATION}, </li>
	 * </ul>
	 * 
	 * for titles and for icons:
	 * 
	 * <ul>
	 * 	<li> {@link ConnectionsActivity.DialogList#ID_TITLE_ICON_CONNECTION_CREATOR}, </li>
	 * 	<li> {@link ConnectionsActivity.DialogList#ID_TITLE_ICON_BT}, </li>
	 * 	<li> {@link ConnectionsActivity.DialogList#ID_TITLE_ICON_WIFI}. </li>
	 * </ul>
	 * 
	 * that will simplify dialog creation and displaying (see {@link #showDialog(Activity, String, Integer, Integer, Integer, Integer, Integer, Integer)}).
	 * 
	 * @param titleIconID ID resource from {@link R.string} that is uniquely represents a text value
	 * @param titleID ID resource from {@link R.string} that is uniquely represents a text value
	 * @param messageID ID resource from {@link R.string} that is uniquely represents a button's label text value
	 * @param positiveButtonID ID resource from {@link DialogListCore} that is uniquely represents a button's label text value
	 * @param neutralButtonID ID resource from {@link DialogListCore} that is uniquely represents a button's label text value
	 * @param negativeButtonID ID resource from {@link DialogListCore} that is uniquely represents a button's label text value
	 * @return new alert dialog
	 */
    protected static AlertDialogs newInstance( Integer titleIconID, Integer titleID, Integer messageID, Integer positiveButtonID, Integer neutralButtonID, Integer negativeButtonID ) {
    	AlertDialogs dialog = new AlertDialogs();
    	
        Bundle args = new Bundle();
        args.putInt(AlertDialogs.TITLE_ICON_ID, ( titleIconID != null ) ? titleIconID : 0);
        args.putInt(AlertDialogs.TITLE_ID, ( titleID != null ) ? titleID : 0);
        args.putInt(AlertDialogs.MESSAGE_ID, ( messageID != null ) ? messageID : 0);
        args.putInt(AlertDialogs.POSITIVE_BUTTON_ID, ( positiveButtonID != null ) ? positiveButtonID : 0);
        args.putInt(AlertDialogs.NEUTRAL_BUTTON_ID, ( neutralButtonID != null ) ? neutralButtonID : 0);
        args.putInt(AlertDialogs.NEGATIVE_BUTTON_ID, ( negativeButtonID != null ) ? negativeButtonID : 0);
        dialog.setArguments(args);
        return dialog;
    }

    /** Called when a fragment is first attached to its activity. 
     * 
     * It assigns the passed activity to {@link #dialogButtonClickListener}.
     * 
     * @throws ClassCastException where the passed activity does not implement {@link DialogButtonClickListener}.
     */
	@Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
        	dialogButtonClickListener = (DialogButtonClickListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement DialogButtonClickListener");
        }
    }
	
	/** Creates dialog window based on given values.
	 * 
	 * @see #newInstance(Integer, Integer, Integer, Integer, Integer, Integer)
	 * @see #buildDialogContent(Builder)
	 * @see #buildDialogButtons(Builder, DialogButtonClickListener)
	 * 
	 */
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
        	dialogBuilder.setTitle(argsID);
        } else {
        	dialogBuilder.setTitle(DialogListCore.ID_TITLE_NO_TITLE);
        }
        
        buildDialogContent(dialogBuilder);
        buildDialogButtons(dialogBuilder,dialogButtonClickListener);
        
        AlertDialogs.builder = AlertDialogs.dialogBuilder.create();
        AlertDialogs.builder.setOnShowListener(this);
        return AlertDialogs.builder;
    }
    
    /** Part of {@link #onCreateDialog(Bundle)} that creates dialog buttons.
     * 
     * Creates a dialog buttons based on values:
     * 
     * <ul>
     * 	<li>{@link AlertDialogs#POSITIVE_BUTTON_ID}</li>
     * 	<li>{@link AlertDialogs#NEUTRAL_BUTTON_ID}</li>
     * 	<li>{@link AlertDialogs#NEGATIVE_BUTTON_ID}</li>
     * </ul>
     * 
     * that was created in {@link #newInstance(Integer, Integer, Integer, Integer, Integer, Integer)}.
     * 
     * In the key-value pairs (e.g. {@literal <}{@link AlertDialogs#POSITIVE_BUTTON_ID},0{@literal >}) 
     * dialog button labels are stored. If the value of given key is not set - the button will not be created.
     * Otherwise, the method will add {@link DialogInterface.OnClickListener} to the created buttons, 
     * which will execute appropriate method in <i>eventListener</i> each time user taps one of them.
     * 
     * @param builder {@link #dialogBuilder} that builds entire dialog window
     * @param eventListener activity that implements {@link DialogButtonClickListener} and will receive data from alert dialog
     */
    public void buildDialogButtons(Builder builder, final DialogButtonClickListener eventListener ) {
    	Integer argsID = super.getArguments().getInt(AlertDialogs.POSITIVE_BUTTON_ID);
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
    
    /** Part of {@link #onCreateDialog(Bundle)} that creates dialog message.
     * 
     * Creates a dialog message based on value:
     * 
     * <ul>
     * 	<li>{@link AlertDialogs#MESSAGE_ID}</li>
     * </ul>
     * 
     * that was created in {@link #newInstance(Integer, Integer, Integer, Integer, Integer, Integer)}.
     * 
     * ID value that this method needs is stored in  the key-value pair 
     * {@literal <}{@link AlertDialogs#MESSAGE_ID},X{@literal >}, where <i>X</i> is that value.
     * 
     * @param builder {@link #dialogBuilder} that builds entire dialog window
     */
    public void buildDialogContent(Builder builder) {
    	Integer argsID = super.getArguments().getInt(AlertDialogs.MESSAGE_ID);
        if ( argsID !=  0 ) {
        	builder.setMessage(argsID);
        } else {
        	builder.setMessage(DialogListCore.ID_MESSAGE_NO_MESSAGE);
        }
	}

    /** Displays alert dialog in given {@link Activity}.
     * 
     * Usage example:
     * 
     * <pre>
     * {@code
     *  AlertDialogs.showDialog(this,
						MultiplayExplorerActivity.DialogList.TAG_NO_CONNECTION_FOUND,
						DialogListCore.IT_TITLE_ICON_WARNING,
						MultiplayExplorerActivity.DialogList.ID_TITLE_NO_CONNECTION_FOUND,
						MultiplayExplorerActivity.DialogList.ID_MESSAGE_NO_CONNECTION_FOUND,
						DialogListCore.ID_BUTTON_OPTIONS,
						null,
						DialogListCore.ID_BUTTON_CANCEL);
     * }
     * </pre>
     * 
     * In the above code in <i>this</i> Activity dialog box is displayed, which is containing 2 of 3 buttons:
     * 
     * <ul>
     * 	<li> Positive button with label defined under {@link DialogListCore.ID_BUTTON_OPTIONS},</li>
     * 	<li> Negative button with label defined under {@link DialogListCore.ID_BUTTON_CANCEL}.</li> 
     * </ul>
     * 
     * Neutral button will not be even displayed as null value is set.
     * 
     * @param activity activity where dialog will be displayed
     * @param dialogIDTag a unique value to distinguish dialog boxes where more than one is displayed in one activity
     * @param titleIconID see {@link #newInstance(Integer, Integer, Integer, Integer, Integer, Integer)} parameters
     * @param titleID see {@link #newInstance(Integer, Integer, Integer, Integer, Integer, Integer)} parameters
     * @param messageID see {@link #newInstance(Integer, Integer, Integer, Integer, Integer, Integer)} parameters
     * @param positiveButtonID see {@link #newInstance(Integer, Integer, Integer, Integer, Integer, Integer)} parameters
     * @param neutralButtonID see {@link #newInstance(Integer, Integer, Integer, Integer, Integer, Integer)} parameters
     * @param negativeButtonID see {@link #newInstance(Integer, Integer, Integer, Integer, Integer, Integer)} parameters
     */
	public static void showDialog(Activity activity, String dialogIDTag, Integer titleIconID, Integer titleID, Integer messageID, Integer positiveButtonID, Integer neutralButtonID, Integer negativeButtonID ) {
		DialogFragment dialog = AlertDialogs.newInstance(titleIconID,titleID,messageID,positiveButtonID,neutralButtonID,negativeButtonID);
        dialog.show(activity.getFragmentManager(), dialogIDTag);
	}

	/** Assigns a reference to each of the dialog buttons.
	 * 
	 * It is performed when the dialog box appears. Assigns button's references to variables
	 * {@link #positiveButton}, {@link #neutralButton} and {@link #positiveButton}, so that 
	 * later can be used (for example, to lock the keys in the event of failure of the data validation).
	 * 
	 */
	@Override
	public void onShow(DialogInterface dialog) {
		positiveButton = ((AlertDialog)dialog).getButton(AlertDialog.BUTTON_POSITIVE);
		neutralButton = ((AlertDialog)dialog).getButton(AlertDialog.BUTTON_NEUTRAL);
		negativeButton = ((AlertDialog)dialog).getButton(AlertDialog.BUTTON_NEGATIVE);
		Log.d("Dialogs","Buttons init");
	}
	
	/** Getter for {@link #positiveButton}
	 * 
	 * @return {@link #positiveButton}
	 */
	public static final Button getPositiveButton() {
		return positiveButton;
	}

	/** Getter for {@link #neutralButton}
	 * 
	 * @return {@link #neutralButton}
	 */
	public static final Button getNeutralButton() {
		return neutralButton;
	}

	/** Getter for {@link #negativeButton}
	 * 
	 * @return {@link #negativeButton}
	 */
	public static final Button getNegativeButton() {
		return negativeButton;
	}
}