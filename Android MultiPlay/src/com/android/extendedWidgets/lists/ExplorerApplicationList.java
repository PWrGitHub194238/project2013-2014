package com.android.extendedWidgets.lists;

import java.util.Collection;
import java.util.Locale;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.multiplay.R;
 
public class ExplorerApplicationList extends BaseAdapter {
 
    private Context ctx;
    private ExplorerApplicationItem[] data = null;
 
    public ExplorerApplicationList(Context ctx, Collection<ExplorerApplicationItem> listElements) {
    	data = new ExplorerApplicationItem[listElements.size()];
    	listElements.toArray(data);
	    this.ctx = ctx;
    }
 
    public int getCount() {
    	return data.length;
    }
 
    public ExplorerApplicationItem getItem(int position) {
    	return data[position];
    }
 
    public long getItemId(int position) {
    	return 0;
    }
 
    private class ViewHolderPattern {
    	private ImageView application_icon = null;
    	private TextView application_name = null;
  
    	
		public void setElement(ExplorerApplicationItem explorerApplicationItem) {
			String app_icon_resource_name = "app_icon_"+explorerApplicationItem.getApplicationName()
					.toLowerCase(Locale.getDefault()).replace(" ", "_");
			int app_icon_resource_id = ctx.getResources().getIdentifier(
					app_icon_resource_name, "drawable",
					ctx.getPackageName());
			
			if ( app_icon_resource_id == 0 ) {
				explorerApplicationItem.setApplicationIconID(R.drawable.app_icon_unknow);
			} else {
				explorerApplicationItem.setApplicationIconID(app_icon_resource_id);
			}
			application_icon.setBackgroundResource(explorerApplicationItem.getApplicationIconID());
			application_name.setText(explorerApplicationItem.getApplicationName());
		}
    }
 
    public View getView(int position, View convertView, ViewGroup parent) {
 
    ViewHolderPattern view_holder;
 
    if (convertView == null) {
        LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.list_explorer_application, parent, false);
 
        view_holder = new ViewHolderPattern();
        view_holder.application_icon = (ImageView) convertView.findViewById(R.id.list_explorer_application_icon);
        view_holder.application_name = (TextView) convertView.findViewById(R.id.list_explorer_application_name);

        convertView.setTag(view_holder);
    } else {
        view_holder = (ViewHolderPattern) convertView.getTag();
    }
 
    view_holder.setElement(data[position]);
 
    return convertView;
    }
}