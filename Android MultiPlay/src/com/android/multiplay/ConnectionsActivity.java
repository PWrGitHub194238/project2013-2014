package com.android.multiplay;

import java.util.ArrayList;
import java.util.Collection;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.p2p.WifiP2pManager;
import android.net.wifi.p2p.WifiP2pManager.Channel;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;

import com.android.extendedWidgets.lists.ElementOfConnectionsList;
import com.android.extendedWidgets.lists.ListOfConections;
import com.android.service.receivers.WiFiDirectBroadcastReceiver;

public class ConnectionsActivity extends Activity implements OnItemClickListener, OnItemLongClickListener {

	WifiP2pManager mManager = null;
	Channel mChannel = null;
	BroadcastReceiver mReceiver = null;
	
	IntentFilter mIntentFilter = null;
	
	String tag = "Connections";
	int count = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_connections);
		
		mManager = (WifiP2pManager) getSystemService(Context.WIFI_P2P_SERVICE);
	    mChannel = mManager.initialize(this, getMainLooper(), null);
	    mReceiver = new WiFiDirectBroadcastReceiver(mManager, mChannel, this);
	    
	    mIntentFilter = new IntentFilter();
	    mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);
	    mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);
	    mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);
	    mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);

		Collection<ElementOfConnectionsList> listOfElements = new ArrayList<ElementOfConnectionsList>();
		listOfElements.add(new ElementOfConnectionsList(
				"LIFEBOOK-PC", "10.0.0.127", ElementOfConnectionsList.STATUS_NOT_IN_RANGE,
				ElementOfConnectionsList.STORED_YES,
				ElementOfConnectionsList.TYPE_WIFI));
		listOfElements.add(new ElementOfConnectionsList(
				"LIFEBOOK-PC", "00:0A:E6:3E:FD:E1", ElementOfConnectionsList.STATUS_WARNING,
				ElementOfConnectionsList.STORED_YES,
				ElementOfConnectionsList.TYPE_BT));
		listOfElements.add(new ElementOfConnectionsList(
				"LIFEBOOK-PC", "10.0.0.127", ElementOfConnectionsList.STATUS_ON,
				ElementOfConnectionsList.STORED_NO,
				ElementOfConnectionsList.TYPE_WIFI));
		listOfElements.add(new ElementOfConnectionsList(
				"LIFEBOOK-PC", "10.0.0.127", ElementOfConnectionsList.STATUS_NOT_IN_RANGE,
				ElementOfConnectionsList.STORED_YES,
				ElementOfConnectionsList.TYPE_WIFI));
		listOfElements.add(new ElementOfConnectionsList(
				"LIFEBOOK-PC", "00:0A:E6:3E:FD:E1", ElementOfConnectionsList.STATUS_WARNING,
				ElementOfConnectionsList.STORED_YES,
				ElementOfConnectionsList.TYPE_BT));
		listOfElements.add(new ElementOfConnectionsList(
				"LIFEBOOK-PC", "10.0.0.127", ElementOfConnectionsList.STATUS_ON,
				ElementOfConnectionsList.STORED_NO,
				ElementOfConnectionsList.TYPE_WIFI));
		
		ListView connectionList = (ListView) this.findViewById(R.id.lv_connections_activity_device_list);
		 
		ListOfConections adapter_listy = new ListOfConections(this, listOfElements);
		connectionList.setOnItemClickListener(this);
		connectionList.setOnItemLongClickListener(this);
		connectionList.setAdapter(adapter_listy);
	}

	/* register the broadcast receiver with the intent values to be matched */
	@Override
	protected void onResume() {
	    super.onResume();
	    registerReceiver(mReceiver, mIntentFilter);
	}
	/* unregister the broadcast receiver */
	@Override
	protected void onPause() {
	    super.onPause();
	    unregisterReceiver(mReceiver);
	}
	
	
	
////////////////////Methods for onClick events



	public void refresh_connections_check_OnClick( View view ) {
		mManager.discoverPeers(mChannel, new WifiP2pManager.ActionListener() {
		    @Override
		    public void onSuccess() {
		    	Log.d("Connections", "OK");
		    }

		    @Override
		    public void onFailure(int reason) {
                count++;

                String err=new String();
                if(reason==WifiP2pManager.BUSY) err="BUSY";
                if(reason==WifiP2pManager.ERROR)err="ERROR";
                if(reason==WifiP2pManager.P2P_UNSUPPORTED) err="P2P_UNSUPPORTED";
                Log.d(tag,"FAIL - couldnt start to discover peers code: "+err+" ("+count+")");
                if(count>=20)return;
                mManager.discoverPeers(mChannel, this);
            }
		});
	}
	
	@Override
	public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int id,
			long arg3) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int id, long arg3) {
		// TODO Auto-generated method stub
		
	}

}
