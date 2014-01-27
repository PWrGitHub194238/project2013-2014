package com.android.asyncs;

public interface OnAsyncTaskFinished {

	public static final class TAG {
		public final static int ConnectionActivity_Empty = -2;

		public final static int ConnectionActivity_onItemClick_BT = 0;
		public final static int ConnectionActivity_onItemClick_WIFI = 1;
		public final static int ConnectionActivity_onDialogPositiveClick = 2;
		public final static int ConnectionActivity_BT_search = 3;

		public final static int ConnectionActivity_Error = -1;
		public static final int ConnectionActivity_BT_search_failed = -3;
	};
	
	public void onBackgroundFinished(int activityTAG);
}
