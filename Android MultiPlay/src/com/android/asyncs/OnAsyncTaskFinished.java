package com.android.asyncs;

/** It lets you communicate a specific class of application threads. 

Thread should be taken as a parameter specified TAG and return it.
 Allows the application to react to the end of the working thread.

 * @author tomasz
 *
 */
public interface OnAsyncTaskFinished {

	/**
	 * @author tomasz
	 *
	 */
	public static final class TAG {
		public final static int ConnectionActivity_Empty = -2;

		public final static int ConnectionActivity_onItemClick_BT = 0;
		public final static int ConnectionActivity_onItemClick_WIFI = 1;
		public final static int ConnectionActivity_onDialogPositiveClick = 2;
		public final static int ConnectionActivity_BT_search = 3;
		public final static int MultiplayExplorerActivity_application_search = 4;
		public static final int MultiplayExplorerActivity_application_run = 5;

		public final static int ConnectionActivity_Error = -1;
		public static final int ConnectionActivity_BT_search_failed = -3;
		public static final int MultiplayExplorerActivity_application_search_failed = -4;
		public static final int MultiplayExplorerActivity_application_run_failed = -5;
	};
	
	/**
	 * @param activityTAG
	 */
	public void onBackgroundFinished(int activityTAG);
}
