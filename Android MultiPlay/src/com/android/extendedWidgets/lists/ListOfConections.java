package com.android.extendedWidgets.lists;

import java.util.Collection;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.application.N;
import com.android.multiplay.R;
 
public class ListOfConections extends BaseAdapter {
 
    private Context ctx;
    private ElementOfConnectionsList[] data = null;
 
    public ListOfConections(Context ctx, Collection<ElementOfConnectionsList> listElements) {
    	data = new ElementOfConnectionsList[listElements.size()];
    	listElements.toArray(data);
	    this.ctx = ctx;
    }
 
    public int getCount() {
    	return data.length;
    }
 
    public ElementOfConnectionsList getItem(int position) {
    	return data[position];
    }
 
    public long getItemId(int position) {
    	return 0;
    }
 
    private class ViewHolderPattern {
    	private ImageView device_icon = null;
    	private ImageView connectionType = null;
    	private TextView device_name = null;
    	private ImageView server_status = null;
    	private TextView device_detail_prefix = null;
    	private TextView device_detail = null;
    	private ImageView device_isStored = null;
    	private ImageView system = null;
    	
		public void setElement(ElementOfConnectionsList elementOfConnections) {
			device_icon.setBackgroundResource(elementOfConnections.getIcon_type_id());
			connectionType.setBackgroundResource(elementOfConnections.getConnectionTypeIcon());
			device_name.setText(elementOfConnections.getDeviceName());
			server_status.setBackgroundResource(elementOfConnections.getDeviceDetailStatus());
			device_detail_prefix.setText(elementOfConnections.getDeviceDetailPrefix());
			device_detail.setText(elementOfConnections.getDeviceDetail());
			device_isStored.setBackgroundResource(
					(elementOfConnections.isStored())?ElementOfConnectionsList.ICON_STORED_YES:ElementOfConnectionsList.ICON_STORED_NO);
			system.setBackgroundResource(setSystem(elementOfConnections.getSystem()));
		}
    }
 
    public View getView(int position, View convertView, ViewGroup parent) {
 
    ViewHolderPattern view_holder;
 
    if (convertView == null) {
        LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.list_connections_element, parent, false);
 
        view_holder = new ViewHolderPattern();
        view_holder.device_icon = (ImageView) convertView.findViewById(R.id.list_connections_element_device_icon);
        view_holder.connectionType = (ImageView) convertView.findViewById(R.id.list_connections_element_connection_type);
        view_holder.device_name = (TextView) convertView.findViewById(R.id.list_connections_element_device_name);
        view_holder.server_status = (ImageView) convertView.findViewById(R.id.list_connections_element_connection_server_status);
        view_holder.device_detail_prefix = (TextView) convertView.findViewById(R.id.list_connections_element_device_detail_prefix);
        view_holder.device_detail = (TextView) convertView.findViewById(R.id.list_connections_element_device_detail);
        view_holder.device_isStored = (ImageView) convertView.findViewById(R.id.list_connections_element_is_device_stored);

        convertView.setTag(view_holder);
    } else {
        view_holder = (ViewHolderPattern) convertView.getTag();
    }
 
    view_holder.setElement(data[position]);
 
    return convertView;
    }
    
    private int setSystem(byte system) {
    	if ( system==N.System.BSD) {
    		return ElementOfConnectionsList.ICON_BSD;
    	} else if ( system==N.System.LINUX) {
    		return ElementOfConnectionsList.ICON_LINUX;
    	} else 
    		return ElementOfConnectionsList.ICON_WINDOWS;
    }
}