package com.android.dialogs;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.os.Bundle;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.android.dialogs.elements.DialogListCore;
import com.android.multiplay.R;

/** Displays the alert dialog box that contains {@link ScrollView}. 
 * 
 * Dialog box, extending the class of {@link AlertDialogs}. Instead of plain text, the contents of the dialog box
 *  (except the title, icon, buttons that was already defined in super class) is defined in a different, 
 *  specially defined {@link ScrollView} (see {@link ScrollViewDialog#VIEW_ID).
 *
 */
public class ScrollViewDialog extends AlertDialogs {

	/** Key for key-value pair that will be passed to dialog builder method in super class: {@link AlertDialogs#onCreateDialog(Bundle)}.
	 * ID resource to the {@link ScrollView} will be passed.
	 */
	protected static final String VIEW_ID = "viewID";
	
	/** View that will be displayed in the dialog box.
	 * 
	 * Any view which logic should be initiated in the {@link #dialogInnerViewLogic()}
	 * and any returning data should be stored as key-value pair in {@link #returnedData}
	 * which can be parsed later. By default, every returning-data element should have:
	 * 
	 * <ul>
	 * 	<li> tag - identifier for element (by default <i>tag</i> is used as key for key-value pair in {@link #returnedData}),</li>
	 * 	<li> pattern -regular expression (by default <i>pattern</i> is used to validate each {@link TextView} data),</li>
	 * 	<li> pattern error message - by default it is displayed when the input data does not match the regular expression.</li>
	 * </ul>
	 * 
	 * The great example is in {@link AddConnectionDialog#afterTextChanged(android.text.Editable)} method
	 * that uses default {@link #validation(EditText, Pattern, String)}.
	 * 
	 * @see Pattern
	 */
	protected static ScrollView dialogInnerView = null;
	
	/** Collects the data returned by the {@link #dialogInnerView}.
	 * 
	 * By default, data is added in the process of validation (see {@link #validation(EditText, Pattern, String)), 
	 * where the key is the tag of validating element, and the value is element's inputted data, which passed validation.
	 * 
	 */
	private Map<String,String> returnedData = null;

	/** Creates a new instance of the dialog box using the specified values.
	 * 
	 * Instead of message that is passed to the {@link AlertDialogs#newInstance(Integer, Integer, Integer, Integer, Integer, Integer)}
	 * , reference to the {@link #dialogInnerView} is passed.
	 * 
	 * @param titleIconID ID resource from {@link R.string} that is uniquely represents a text value
	 * @param titleID ID resource from {@link R.string} that is uniquely represents a text value
	 * @param view ID resource from {@link R.string} that is uniquely represents a {@link ScrollView}
	 * @param positiveButtonID ID resource from {@link DialogListCore} that is uniquely represents a button's label text value
	 * @param neutralButtonID ID resource from {@link DialogListCore} that is uniquely represents a button's label text value
	 * @param negativeButtonID ID resource from {@link DialogListCore} that is uniquely represents a button's label text value
	 * @return new alert dialog
	 */
	protected static ScrollViewDialog newInstance( Integer titleIconID, Integer titleID, ScrollView view, Integer positiveButtonID, Integer neutralButtonID, Integer negativeButtonID ) {
    	ScrollViewDialog dialog = new ScrollViewDialog();

        Bundle args = AlertDialogs.newInstance(titleIconID, titleID, null, positiveButtonID, neutralButtonID, negativeButtonID).getArguments();

        args.putInt(ScrollViewDialog.VIEW_ID, ( view != null ) ? view.getId() : 0);

        dialog.setArguments(args);
        return dialog;
    }

	/** Adds {@link #dialogInnerView} to dialog box.
	 * 
	 * Overrides a part of {@link AlertDialogs#onCreateDialog(Bundle)} that is responsible
	 * for building a main part of the dialog. Instead of simple message, the entire new {@link ScrollView}
	 * will be added, based on value:
     * 
     * <ul>
     * 	<li>{@link #VIEW_ID}</li>
     * </ul>
     * 
     * that was created in {@link #newInstance(Integer, Integer, ScrollView, Integer, Integer, Integer)}.
     * 
     * ID value that this method needs is stored in  the key-value pair 
     * {@literal <}{@link #VIEW_ID},X{@literal >}, where <i>X</i> is that value.
     * 
     * @param builder {@link AlertDialogs#dialogBuilder} that builds entire dialog window
	 * 
	 */
	@Override
	public void buildDialogContent(Builder builder) {
		Integer argsID = super.getArguments().getInt(ScrollViewDialog.VIEW_ID);
		if ( argsID !=  0 ) {
			builder.setView(dialogInnerView);
		}
	}
	
    /** Displays alert dialog in given {@link Activity}.
     * 
     * Usage example:
     * 
     * <pre>
     * {@code
     * ScrollViewDialog.showDialog(this,
					ConnectionsActivity.DialogList.TAG_CONNECT_CONFIRMATION,
					ConnectionsActivity.DialogList.ID_TITLE_ICON_WIFI,
					ConnectionsActivity.DialogList.ID_TITLE_CONNECT_CONFIRMATION,
					ScrollViewDialog.getViewFromResource(this,R.layout.dialog_check_requirements),
					DialogListCore.ID_BUTTON_CONNECT,
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
     * Neutral button will not be even displayed as null value is set. View can be set either by passing:
     * 
     * <ul>
     * 	<li> layout ID which contains {@link ScrollView} as a root element (see {@link #getViewFromResource(Activity, int)}),</li>
     * 	<li> view ID by {@link Activity#findViewById(int)}.</li>
     * </ul>
     * 
     * @param activity activity where dialog will be displayed
     * @param dialogIDTag a unique value to distinguish dialog boxes where more than one is displayed in one activity
     * @param titleIconID see {@link #newInstance(Integer, Integer, ScrollView, Integer, Integer, Integer)} parameters
     * @param titleID see {@link #newInstance(Integer, Integer, ScrollView, Integer, Integer, Integer)} parameters
     * @param view see {@link #newInstance(Integer, Integer, ScrollView, Integer, Integer, Integer)} parameters
     * @param positiveButtonID see {@link #newInstance(Integer, Integer, ScrollView, Integer, Integer, Integer)} parameters
     * @param neutralButtonID see {@link #newInstance(Integer, Integer, ScrollView, Integer, Integer, Integer)} parameters
     * @param negativeButtonID see {@link #newInstance(Integer, Integer, ScrollView, Integer, Integer, Integer)} parameters
     */
	public static void showDialog(Activity activity, String dialogIDTag, Integer titleIconID, Integer titleID, ScrollView view, Integer positiveButtonID, Integer neutralButtonID, Integer negativeButtonID ) {
		ScrollViewDialog dialog = ScrollViewDialog.newInstance(titleIconID,titleID,view,positiveButtonID,neutralButtonID,negativeButtonID);
        dialog.show(activity.getFragmentManager(), dialogIDTag);
	}

	/** Returns {@link ScrollView} view from layout that will be displayed in the dialog.
	 * 
	 * @param activity need for context
	 * @param id layout's identifier that is defined in a XML file and consists {@link ScrollView} layout as a root layout.
	 * @return
	 */
    public static ScrollView getViewFromResource(Activity activity, int id) {
    	dialogInnerView = (ScrollView) activity.getLayoutInflater().inflate(id, null).getRootView();
		return dialogInnerView;
    }
    
    /** Defines entire logic for custom {@link ScrollView} added to the dialog.
     * 
     * Base logic is to initialize {@link #returnedData} to store returned data. Extra logic commonly will consist of:
     * 
     * <ul>
     * 	<li> searching a view by {@link Activity#findViewById(int)}</li>
     * 	<li> setting a tag for the found view (and key for {@link #returnedData})</li>
     * 	<li> adding additional listeners to the view e.g.
     * 	<ul>
     * 		<li> {@link OnCheckedChangeListener}</li>
     * 		<li> {@link TextWatcher}</li>
     * 		<li> {@link OnSeekBarChangeListener}</li>
     * 	</ul>
     * </li>
     * 	<li> setting default values for a key-value pair.</li>
     * </ul>
     * 
     * See example: {@link AddConnectionDialog#dialogInnerViewLogic()}.
     * 
     */
    public void dialogInnerViewLogic() {
    	returnedData = new HashMap<String,String>();
    }
    
	/** Getter for {@link #returnedData}
	 * 
	 * @return {@link #returnedData}
	 */
	public final Map<String, String> getReturnedData() {
		return returnedData;
	}

	/** Getter for {@link #dialogInnerView}
	 * 
	 * @return {@link #dialogInnerView}
	 */
	public final ScrollView getDialogInnerView() {
		return dialogInnerView;
	}
	
	/** Default validation method for {@link EditText} fields.
	 * 
	 * 
	 * 
	 * @param toValid {@link EditText} field to valid
	 * @param regexp {@link Pattern} used to validate <i>toValid</i> value
	 * @param raiseError error raised when validation failed
	 * @return
	 */
	protected boolean validation(EditText toValid, Pattern regexp, String raiseError) {
		String text = toValid.getText().toString();
		String tag = toValid.getTag().toString();
		
		Log.d("Dialogs",tag+" validatation");
		
		if (regexp.matcher(text).matches() == true) {
			if (text.isEmpty() == false ) {
				returnedData.put(tag, text);
			}
			Log.d("Dialogs","validatation: OK ("+text+")");
			return true;
		} else {
			toValid.setError(raiseError);
			Log.d("Dialogs",tag+" validatation: FAIL");
			return false;
		}
	}
}