package com.android.multiplay;

import java.io.IOException;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import com.android.application.MultiPlayApplication;
import com.android.dialogs.AlertDialogs;
import com.android.dialogs.DialogButtonClickListener;
import com.android.dialogs.elements.DialogListCore;

public class MultiplayExplorerActivity extends Activity implements OnClickListener, DialogButtonClickListener {

	ImageButton b_connections_activity_testmouse = null;
	ImageButton b_connections_activity_testmove = null;
	ImageButton b_connections_activity_testleft = null;
	ImageButton b_connections_activity_testright = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_multiplay_explorer);

		try {
			if (MultiPlayApplication.getMainNetworkConfiguration() != null) {
				MultiPlayApplication.runThread(MultiPlayApplication.isConnectedTo());
				b_connections_activity_testmouse = (ImageButton) super.findViewById(R.id.b_connections_activity_testmouse);
				b_connections_activity_testmove = (ImageButton) super.findViewById(R.id.b_connections_activity_testmove);
				b_connections_activity_testleft = (ImageButton) super.findViewById(R.id.b_connections_activity_testleft);
				b_connections_activity_testright = (ImageButton) super.findViewById(R.id.b_connections_activity_testright);

				b_connections_activity_testmouse.setOnClickListener(this);
				b_connections_activity_testmove.setOnClickListener(this);
				b_connections_activity_testleft.setOnClickListener(this);
				b_connections_activity_testright.setOnClickListener(this);
			} else {
				
				AlertDialogs.showDialog(this,
						MultiplayExplorerActivity.DialogList.TAG_NO_CONNECTION_FOUND,
						DialogListCore.IT_TITLE_ICON_WARNING,
						MultiplayExplorerActivity.DialogList.ID_TITLE_NO_CONNECTION_FOUND,
						MultiplayExplorerActivity.DialogList.ID_MESSAGE_NO_CONNECTION_FOUND,
						DialogListCore.ID_BUTTON_OPTIONS,
						null,
						DialogListCore.ID_BUTTON_CANCEL);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onClick(View v) {
		/*switch(v.getId()) {
		case R.id.b_connections_activity_testmouse:
			MultiPlayApplication.add(N.DeviceSignal.MOUSE_LPM);
			break;
		case R.id.b_connections_activity_testmove:
			MultiPlayApplication.add(N.DeviceSignal.MOUSE_LPM);
			break;
		case R.id.b_connections_activity_testleft:
			MultiPlayApplication.add(N.DeviceSignal.MOUSE_MPM);
			break;
		case R.id.b_connections_activity_testright:
			MultiPlayApplication.add(N.DeviceSignal.MOUSE_PPM);
			break;
	}
	return;*/
}

	
	public final class DialogList {
		
		private static final String PACKAGE = "com.android.multiplay.multiplayexploreractivity";
		
		public static final String TAG_NO_CONNECTION_FOUND = PACKAGE + "NO_CONNECTION_FOUND";
		
		public static final int ID_TITLE_NO_CONNECTION_FOUND =
				R.string.dialog_ID_TITLE_NO_CONNECTION_FOUND;
		
		public static final int ID_MESSAGE_NO_CONNECTION_FOUND =
				R.string.dialog_ID_MESSAGE_NO_CONNECTION_FOUND;
	}
	
	@Override
	public void onDialogPositiveClick(DialogFragment dialog) {
		Intent intent = new Intent(this, ConnectionsActivity.class);
		super.startActivity(intent);
		this.finish();
	}

	@Override
	public void onDialogNeutralClick(DialogFragment dialog) {
	}

	@Override
	public void onDialogNegativeClick(DialogFragment dialog) {
		this.finish();
	}
	
	

}