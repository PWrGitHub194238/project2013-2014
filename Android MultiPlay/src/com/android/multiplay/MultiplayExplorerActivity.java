package com.android.multiplay;

import java.util.ArrayList;
import java.util.Collection;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.android.application.MultiPlayApplication;
import com.android.application.N;
import com.android.asyncs.CheckOutServerApplications;
import com.android.asyncs.OnAsyncTaskFinished;
import com.android.asyncs.RunServerApplications;
import com.android.dialogs.AlertDialogs;
import com.android.dialogs.AsyncTaskDialog;
import com.android.dialogs.DialogButtonClickListener;
import com.android.dialogs.elements.DialogListCore;
import com.android.extendedWidgets.lists.ExplorerApplicationItem;
import com.android.extendedWidgets.lists.ExplorerApplicationList;

public class MultiplayExplorerActivity extends Activity implements OnItemClickListener, DialogButtonClickListener, OnAsyncTaskFinished {

	Collection<ExplorerApplicationItem> listElements = null;
	ExplorerApplicationList explorerApplicationList = null;
	ListView applicationsList = null;
	
	ExplorerApplicationItem selectedApplication = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_multiplay_explorer);

		if (MultiPlayApplication.getMainNetworkConfiguration() != null) {
			listElements = new ArrayList<ExplorerApplicationItem>();
			applicationsList = (ListView) findViewById(R.id.lv_multiplay_explorer_activity_applications);
			applicationsList.setOnItemClickListener(this);
			
			runLogAsyncThread(
					OnAsyncTaskFinished.TAG.MultiplayExplorerActivity_application_search,
					OnAsyncTaskFinished.TAG.MultiplayExplorerActivity_application_search_failed);
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
	}


	
	public final class DialogList {
		
		private static final String PACKAGE = "com.android.multiplay.multiplayexploreractivity";
		
		public static final String TAG_NO_CONNECTION_FOUND = PACKAGE + "NO_CONNECTION_FOUND";
		public static final String TAG_APPLICATION_CHECKOUT_FAILURE = PACKAGE + "APPLICATION_CHECKOUT_FAILURE";
		public static final String TAG_APPLICATION_RUN_FAILURE = PACKAGE + "APPLICATION_RUN_FAILURE";
		
		public static final int ID_TITLE_NO_CONNECTION_FOUND =
				R.string.dialog_ID_TITLE_NO_CONNECTION_FOUND;
		public static final int ID_TITLE_APPLICATION_CHECKOUT_FAILURE =
				R.string.dialog_ID_TITLE_APPLICATION_CHECKOUT_FAILURE;
		public static final int ID_TITLE_APPLICATION_RUN_FAILURE = 
				R.string.dialog_ID_TITLE_APPLICATION_RUN_FAILURE;
		
		public static final int ID_MESSAGE_NO_CONNECTION_FOUND =
				R.string.dialog_ID_MESSAGE_NO_CONNECTION_FOUND;
		public static final int ID_MESSAGE_APPLICATION_CHECKOUT_FAILURE =
				R.string.dialog_ID_MESSAGE_APPLICATION_CHECKOUT_FAILURE;
		public static final int ID_MESSAGE_APPLICATION_RUN_FAILURE = 
				R.string.dialog_ID_MESSAGE_APPLICATION_RUN_FAILURE;
		
	}
	
	@Override
	public void onDialogPositiveClick(DialogFragment dialog) {
		String dialogTag = dialog.getTag();
		
		if ( dialogTag.equals(MultiplayExplorerActivity.DialogList.TAG_NO_CONNECTION_FOUND)) {
			super.startActivity(
					new Intent(this, ConnectionsActivity.class));
			this.finish();
		} else if ( dialogTag.equals(MultiplayExplorerActivity.DialogList.TAG_APPLICATION_CHECKOUT_FAILURE)) {
			runLogAsyncThread(
					OnAsyncTaskFinished.TAG.MultiplayExplorerActivity_application_search,
					OnAsyncTaskFinished.TAG.MultiplayExplorerActivity_application_search_failed);
		} else if ( dialogTag.equals(MultiplayExplorerActivity.DialogList.TAG_APPLICATION_RUN_FAILURE)) {
			
		}
	}

	@Override
	public void onDialogNeutralClick(DialogFragment dialog) {
	}

	@Override
	public void onDialogNegativeClick(DialogFragment dialog) {
		String dialogTag = dialog.getTag();
		
		if ( dialogTag.equals(MultiplayExplorerActivity.DialogList.TAG_NO_CONNECTION_FOUND)) {
			this.finish();
		} else if ( dialogTag.equals(MultiplayExplorerActivity.DialogList.TAG_APPLICATION_CHECKOUT_FAILURE)) {
			this.finish();
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Log.d("ListView","Click index: "+position);
		
		selectedApplication = (ExplorerApplicationItem) parent.getItemAtPosition(position);
		
		runLogAsyncThread(
				OnAsyncTaskFinished.TAG.MultiplayExplorerActivity_application_run,
				OnAsyncTaskFinished.TAG.MultiplayExplorerActivity_application_run_failed);
	}
	
	
	private void runLogAsyncThread(int asyncCallReason, int asyncCallOnError) {
		AsyncTaskDialog asyncTaskDialog = new AsyncTaskDialog(this);
		synchronized (asyncTaskDialog ) {
			asyncTaskDialog.show();
			switch (asyncCallReason) {
				case OnAsyncTaskFinished.TAG.MultiplayExplorerActivity_application_search:
					new CheckOutServerApplications(this,
							asyncTaskDialog,asyncCallReason,
							asyncCallOnError,listElements).execute(N.Signal.NEED_APPLICATIONS);
					break;	
				case OnAsyncTaskFinished.TAG.MultiplayExplorerActivity_application_run:
					new RunServerApplications(this,
							asyncTaskDialog,asyncCallReason,
							asyncCallOnError).execute(selectedApplication);
					break;	
			}
		}
	}

	@Override
	public void onBackgroundFinished(int activityTAG) {
		switch (activityTAG) {
		case OnAsyncTaskFinished.TAG.MultiplayExplorerActivity_application_search:
			afterApplicationsChechOut();
			break;
		case OnAsyncTaskFinished.TAG.MultiplayExplorerActivity_application_search_failed:
			AlertDialogs.showDialog(
					this,
					MultiplayExplorerActivity.DialogList.TAG_APPLICATION_CHECKOUT_FAILURE,
					null,
					MultiplayExplorerActivity.DialogList.ID_TITLE_APPLICATION_CHECKOUT_FAILURE,
					MultiplayExplorerActivity.DialogList.ID_MESSAGE_APPLICATION_CHECKOUT_FAILURE,
					DialogListCore.ID_BUTTON_RECHECK,
					null,
					DialogListCore.ID_BUTTON_CANCEL);
			break;	
		case OnAsyncTaskFinished.TAG.MultiplayExplorerActivity_application_run:
			super.startActivity(
					new Intent(this, SystemControllerActivity.class));
			break;
		case OnAsyncTaskFinished.TAG.MultiplayExplorerActivity_application_run_failed:
			AlertDialogs.showDialog(
					this,
					MultiplayExplorerActivity.DialogList.TAG_APPLICATION_RUN_FAILURE,
					null,
					MultiplayExplorerActivity.DialogList.ID_TITLE_APPLICATION_RUN_FAILURE,
					MultiplayExplorerActivity.DialogList.ID_MESSAGE_APPLICATION_RUN_FAILURE,
					DialogListCore.ID_BUTTON_OK,
					null,
					null);
			break;
		}
	}

	private void afterApplicationsChechOut() {
		explorerApplicationList = new ExplorerApplicationList(getApplicationContext(), listElements);
		applicationsList.setAdapter(explorerApplicationList);
	}
	

}