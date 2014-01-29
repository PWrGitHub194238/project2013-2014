package com.android.extendedWidgets.lists;

import java.util.Collection;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.multiplay.R;
 
/** Adapter for list of elements defined in {@link PreferencesDialogItem}.
 * 
 * @author tomasz
 *
 */
public class PreferencesDialogList extends BaseAdapter {
 
    /**
     * 
     */
    private Context ctx;
    /**
     * 
     */
    private PreferencesDialogItem[] data = null;
 
    /**
     * @param ctx
     * @param listElements
     */
    public PreferencesDialogList(Context ctx, Collection<PreferencesDialogItem> listElements) {
    	data = new PreferencesDialogItem[listElements.size()];
    	listElements.toArray(data);
	    this.ctx = ctx;
    }
 
    /* (non-Javadoc)
     * @see android.widget.Adapter#getCount()
     */
    public int getCount() {
    	return data.length;
    }
 
    /* (non-Javadoc)
     * @see android.widget.Adapter#getItem(int)
     */
    public PreferencesDialogItem getItem(int position) {
    	return data[position];
    }
 
    /* (non-Javadoc)
     * @see android.widget.Adapter#getItemId(int)
     */
    public long getItemId(int position) {
    	return 0;
    }
 
    /** Pattern for list item that holds view configuration to prevent it to load each time new item occurred.
     * 
     * @author tomasz
     *
     */
    private class ViewHolderPattern {
    	private ImageView option_icon = null;
    	private TextView option_name = null;
  
    	
		public void setElement(PreferencesDialogItem preferencesDialogItem) {
			option_icon.setBackgroundResource(preferencesDialogItem.getOption_iconID());
			option_name.setText(preferencesDialogItem.getOption_nameID());
		}
    }
 
    /* (non-Javadoc)
     * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
     */
    public View getView(int position, View convertView, ViewGroup parent) {
 
    ViewHolderPattern view_holder;
 
    if (convertView == null) {
        LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.list_dialog_preferences, parent, false);
 
        view_holder = new ViewHolderPattern();
        view_holder.option_icon = (ImageView) convertView.findViewById(R.id.list_dialog_preferences_icon);
        view_holder.option_name = (TextView) convertView.findViewById(R.id.list_dialog_preferences_icon_name);

        convertView.setTag(view_holder);
    } else {
        view_holder = (ViewHolderPattern) convertView.getTag();
    }
 
    view_holder.setElement(data[position]);
 
    return convertView;
    }
}