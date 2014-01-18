package com.android.asyncs;

public interface OnAsyncTaskFinished {

	public static final class TAG {
		public final static int ConnectionActivity_Empty = -2;

		public final static int ConnectionActivity_Error = -1;
		public final static int ConnectionActivity_onItemClick_BT = 0;
		public final static int ConnectionActivity_onItemClick_WIFI = 1;
		public final static int ConnectionActivity_onDialogPositiveClick = 2;
	};
	
	public void onBackgroundFinished(int activityTAG);
}
