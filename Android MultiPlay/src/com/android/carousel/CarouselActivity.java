package com.android.carousel;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.application.C;
import com.android.application.ControllerDefinition;
import com.android.dialogs.DialogButtonClickListener;
import com.android.dialogs.ScrollViewDialog;
import com.android.dialogs.ScrollViewSwitchDialog;
import com.android.dialogs.elements.DialogListCore;
import com.android.multiplay.ConnectionsActivity;
import com.android.multiplay.R;

public class CarouselActivity extends Activity implements OnItemSelectedListener, OnItemClickListener, OnClickListener, DialogButtonClickListener {
	
	private Context context = null;
	private RelativeLayout panel = null;
	private Singleton m_Inst = Singleton.getInstance();
	private CarouselView carouselView = null;
	private CarouselViewAdapter carouselViewAdapter = null;
	private ArrayList<CarouselDataItem> carouselItemList = null;
	
	private float carouselSizeScale = 0.2f;
	private float carouselRotationSensitive = 0.2f;
	private String layoutColor = Color.GREEN;
	
	protected static final class Color {
		public static final String GREEN = "green";
	}
	
	private RelativeLayout mainLayout = null;
	private RelativeLayout.LayoutParams mainLayoutParams = null;
	private int mainLayoutBaeckgroundColor = R.color.transparent;
	private TextView tv_activity_subtitle_system_controllers = null;
	private ImageView iv_system_controllers_miniature_icon = null;
	private ImageButton b_system_controllers_search = null;
	private ImageButton b_system_controllers_cancel = null;
	
	private Map<String,String> filterData = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	public void initCarouselView(int contentViewtID, int mainRealtiveLayoutID) {
		initCarouselView(contentViewtID, mainRealtiveLayoutID, carouselSizeScale, carouselRotationSensitive,layoutColor);
	}
	
	public void initCarouselView(int contentViewtID, int mainRealtiveLayoutID, String layoutColor) {
		initCarouselView(contentViewtID, mainRealtiveLayoutID, carouselSizeScale, carouselRotationSensitive,layoutColor);
	}
	
	public void initCarouselView(int contentViewtID, int mainRealtiveLayoutID, float carouselSizeScale, float carouselRotationSensitive, String layoutColor ) {
		setContentView(contentViewtID);
		context  = super.getApplicationContext();

		this.layoutColor = layoutColor;
		this.carouselSizeScale = carouselSizeScale;
		this.carouselRotationSensitive = carouselRotationSensitive;
		
		if ( mainLayoutParams == null ) {
			mainLayoutParams = new RelativeLayout.LayoutParams(
					RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
			mainLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
		}
		
		//no keyboard unless requested by user
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		
		// compute screen size and scaling
		m_Inst.InitGUIFrame(this,carouselSizeScale);
		
		generateCarouselItemList();
		
		// create the interface : full screen container
		initMainRelativeLayout(mainRealtiveLayoutID);
		createRelativeLayout(m_Inst.Scale(10),mainLayoutBaeckgroundColor,mainLayoutParams);

		createCarouselView();
	}
	
	public void initCarouselView(int contentViewtID, int mainRealtiveLayoutID, RelativeLayout.LayoutParams mainLayoutParams, 
			int mainLayoutBaeckgroundColor, float carouselSizeScale, float carouselRotationSensitive, String layoutColor ) {

		this.mainLayoutParams = mainLayoutParams;
		this.mainLayoutBaeckgroundColor = mainLayoutBaeckgroundColor;

		initCarouselView(contentViewtID, mainRealtiveLayoutID, carouselSizeScale, carouselRotationSensitive,layoutColor);
	}
	
	private void initMainRelativeLayout(int id) {
		mainLayout = (RelativeLayout) super.findViewById(id);
		iv_system_controllers_miniature_icon = (ImageView) mainLayout.findViewById(
				R.id.iv_system_controllers_miniature_icon);
		tv_activity_subtitle_system_controllers = (TextView) mainLayout.findViewById(
				R.id.tv_activity_subtitle_system_controllers);
		b_system_controllers_search = (ImageButton) mainLayout.findViewById(
				R.id.b_system_controllers_search);
		b_system_controllers_search.setOnClickListener(this);
		b_system_controllers_cancel = (ImageButton) mainLayout.findViewById(
				R.id.b_system_controllers_cancel);
		b_system_controllers_cancel.setOnClickListener(this);
	}
	private void createRelativeLayout (int padding, int mainLayoutBaeckgroundColor, RelativeLayout.LayoutParams mainLayoutParams) {
		
		panel = new RelativeLayout(this);
		panel.setPadding(padding, padding, padding, padding);
		panel.setBackgroundResource(mainLayoutBaeckgroundColor);
		mainLayout.addView(panel, mainLayoutParams);
		super.setContentView(mainLayout);
	}
	
	private void createCarouselView() {
		carouselView = new CarouselView(this,carouselRotationSensitive);
		
		// create adapter and specify device independent items size (scaling)
		carouselViewAdapter = new CarouselViewAdapter(this, carouselItemList,
				m_Inst.Scale(400), m_Inst.Scale(300));
		carouselView.setAdapter(carouselViewAdapter);
		//carouselView.setSpacing(-1 * m_Inst.Scale(150));
		carouselView.setSelection(Integer.MAX_VALUE / 2, true);
		//carouselView.setAnimationDuration(1000);
		
		// Create Carousel Click Listener
		setCarouselListeners();
		
		AppUtils.AddView(panel, carouselView, LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT,
				new int[][] { new int[] { RelativeLayout.CENTER_IN_PARENT, } },
				-1, -1);
	}
	
	private void setCarouselListeners() {
		if (carouselView != null) {
			carouselView.setOnItemSelectedListener(this);
			carouselView.setOnItemClickListener(this);
		}
	}
	
	public final void addCarouselItem (CarouselDataItem carouselDataItem) {
		if (carouselItemList == null ) {
			carouselItemList = new ArrayList<CarouselDataItem>();
		} else {
			carouselItemList.add(carouselDataItem);
		}
	}
	
	public final void generateCarouselItemList() {
		carouselItemList = new ArrayList<CarouselDataItem>();
		for ( ControllerDefinition controller : C.CONTROLLER_LIST ) {
				carouselItemList.add(
						new CarouselDataItem(
								controller.getName(), 
								controller.getIconID(),
								controller.getNextActivity()));
		}
	}

	public final void setCarouselItemList(ArrayList<CarouselDataItem> carouselItemList) {
		this.carouselItemList = carouselItemList;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		startActivity(
				new Intent(
						context, carouselViewAdapter.getItem(position).getNextActivity()));
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
		
		String miniature_icon_resource_ID = "carousel_controller_icon_" 
				+ carouselViewAdapter.getItem(position).getTitle().replace(" ", "_").toLowerCase(Locale.getDefault())
				+ "_miniature_" + layoutColor;
		
		tv_activity_subtitle_system_controllers.setText(carouselViewAdapter.getItem(position).getTitle());
		iv_system_controllers_miniature_icon.setBackgroundResource(
				getResources().getIdentifier(miniature_icon_resource_ID,"drawable", getPackageName()));
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		//TODO
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.b_system_controllers_search:
			ScrollViewDialog.showDialog(this,
					ConnectionsActivity.DialogList.TAG_CONNECT_CONFIRMATION,
					ConnectionsActivity.DialogList.ID_TITLE_ICON_WIFI,
					ConnectionsActivity.DialogList.ID_TITLE_CONNECT_CONFIRMATION,
					ScrollViewDialog.getViewFromResource(this,R.layout.dialog_check_requirements_1),
					DialogListCore.ID_BUTTON_CONNECT,
					null,
					DialogListCore.ID_BUTTON_CANCEL);
			break;
		case R.id.b_system_controllers_cancel:
			ScrollViewSwitchDialog.showDialog(this,
					ConnectionsActivity.DialogList.TAG_CONNECT_CONFIRMATION,
					ConnectionsActivity.DialogList.ID_TITLE_ICON_WIFI,
					ConnectionsActivity.DialogList.ID_TITLE_CONNECT_CONFIRMATION,
					ScrollViewSwitchDialog.getViewFromResource(this,R.layout.dialog_add_new_connection),
					R.id.s_connections_activity_connection_type_switch,
					R.id.rl_connections_activity_wifi_configurator_layout,
					R.id.rl_connections_activity_bt_configurator_layout,
					DialogListCore.ID_BUTTON_CONNECT,
					null,
					DialogListCore.ID_BUTTON_CANCEL);
			break;
		}
		
	}

	@Override
	public void onDialogPositiveClick(DialogFragment dialog) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDialogNeutralClick(DialogFragment dialog) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDialogNegativeClick(DialogFragment dialog) {
		// TODO Auto-generated method stub
		
	}
}
