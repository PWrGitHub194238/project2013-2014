package com.android.service.receivers;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pManager;
import android.net.wifi.p2p.WifiP2pManager.Channel;
import android.net.wifi.p2p.WifiP2pManager.PeerListListener;
import android.util.Log;

/**
 * A BroadcastReceiver that notifies of important Wi-Fi p2p events.
 */
public class WiFiDirectBroadcastReceiver extends BroadcastReceiver {

	private PeerListListener myPeerListListener = null;
	private WifiP2pDeviceList peers = null;
    private WifiP2pManager mManager = null;
    private Channel mChannel = null;
    private Activity activity = null;

    public WiFiDirectBroadcastReceiver(WifiP2pManager manager, Channel channel,
    		Activity activity) {
        super();
        this.mManager = manager;
        this.mChannel = channel;
        this.activity = activity;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
    	String action = intent.getAction();
        if (WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION.equals(action)) {
            int state = intent.getIntExtra(WifiP2pManager.EXTRA_WIFI_STATE, -1);
            if (state == WifiP2pManager.WIFI_P2P_STATE_ENABLED) {
            	Log.d("Connections", "Supported");
            } else {
                // Wi-Fi P2P is not enabled
            }
            // Check to see if Wi-Fi is enabled and notify appropriate activity
        } else if (WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION.equals(action)) {
            // Call WifiP2pManager.requestPeers() to get a list of current peers
        	// request available peers from the wifi p2p manager. This is an
        	// asynchronous call and the calling activity is notified with a
        	// callback on PeerListListener.onPeersAvailable()
        	if (mManager != null) {
        		mManager.requestPeers(mChannel, myPeerListListener);
        		myPeerListListener.onPeersAvailable(peers);
        		for ( WifiP2pDevice device : peers.getDeviceList()) {
        			Log.d("Connections", device.deviceAddress);
        		}
        	}
        } else if (WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION.equals(action)) {
            // Respond to new connection or disconnections
        } else if (WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION.equals(action)) {
            // Respond to this device's wifi state changing
        }
    }
}