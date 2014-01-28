package com.android.extendedWidgets;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageButton;

public class ImageToggleButton extends ImageButton {

	private boolean isToggle = false;
	private int toggled_on_drawable_id = -1;
	private int toggled_off_drawable_id = -1;
	/**
	 * 
	 * @param context
	 */
	public ImageToggleButton(Context context) {
		super(context);
	}
	/**
	 * 
	 * @param context
	 * @param attrs
	 */
	public ImageToggleButton(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	/**
	 * 
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public ImageToggleButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	/**
	 * 
	 */
	public void toggleButton() {
		this.isToggle = !this.isToggle;
	}
	
	public int getDrawableId() {
		return (this.isToggle) ? toggled_on_drawable_id : toggled_off_drawable_id;
	}

	public boolean isToggle() {
		return isToggle;
	}
/**
 * 
 * @param isToggle
 */
	public void setToggle(boolean isToggle) {
		this.isToggle = isToggle;
	}
/**
 * 
 * @return
 */
	public final int getToggled_on_drawable_id() {
		return toggled_on_drawable_id;
	}
/**
 * 
 * @param toggled_on_drawable_id
 */
	public final void setToggled_on_drawable_id(int toggled_on_drawable_id) {
		this.toggled_on_drawable_id = toggled_on_drawable_id;
	}
/**
 * 
 * @return
 */
	public final int getToggled_off_drawable_id() {
		return toggled_off_drawable_id;
	}
/**
 * 
 * @param toggled_off_drawable_id
 */
	public final void setToggled_off_drawable_id(int toggled_off_drawable_id) {
		this.toggled_off_drawable_id = toggled_off_drawable_id;
	}
}
