package com.android.carousel;

import java.util.List;

import com.android.multiplay.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader.TileMode;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class CarouselViewAdapter extends BaseAdapter {

	int mGalleryItemBackground;
	private Context context;

	private List<CarouselDataItem> mDocus;
	int m_w, m_h;

	public CarouselViewAdapter(Context context, List<CarouselDataItem> docuList,
			int image_max_w, int image_max_h) {
		m_w = image_max_w;
		m_h = image_max_h;
		mDocus = docuList;
		this.context = context;
	}

	private Bitmap createReflectedImage(Bitmap bmp) {
		if (bmp == null) return null;
		// The gap we want between the reflection and the original image
		final int reflectionGap = 0;
       	int width = bmp.getWidth();
    	int height = bmp.getHeight();
    	
    	
    	//This will not scale but will flip on the Y axis
    	Matrix matrix = new Matrix();
    	matrix.preScale(1, -1);
       
    	//Create a Bitmap with the flip matrix applied to it.
    	int reflection_part = 4;
    	//We only want the bottom half of the image
    	Bitmap reflectionImage = Bitmap.createBitmap(bmp, 0, 
    			height - height/reflection_part, width, height/reflection_part, matrix, false);
    	//Create a new bitmap with same width but taller to fit reflection
    	Bitmap bitmapWithReflection = Bitmap.createBitmap(width , (height + height/reflection_part), Config.ARGB_8888);
    	//Create a new Canvas with the bitmap that's big enough for
    	//the image plus gap plus reflection
    	Canvas canvas = new Canvas(bitmapWithReflection);
    	//Draw in the original image
    	canvas.drawBitmap(bmp, 0, 0, null);
    	//Draw in the gap
    	Paint deafaultPaint = new Paint();
    	canvas.drawRect(0, height, width, height + reflectionGap, deafaultPaint);
    	//Draw in the reflection
    	canvas.drawBitmap(reflectionImage,0, height + reflectionGap, null);
    	//Create a shader that is a linear gradient that covers the reflection
    	Paint paint = new Paint(); 
    	LinearGradient shader = new LinearGradient(0, height, 0, 
    			bitmapWithReflection.getHeight() + reflectionGap, 0x70ffffff, 0x00ffffff, 
    			TileMode.CLAMP); 
    	//Set the paint to use this shader (linear gradient)
    	paint.setShader(shader); 
    	//Set the Transfer mode to be porter duff and destination in
    	paint.setXfermode(new PorterDuffXfermode(Mode.DST_IN)); 
    	//Draw a rectangle using the paint with our linear gradient
    	canvas.drawRect(0, height, width, 
    			bitmapWithReflection.getHeight() + reflectionGap, paint); 
    	
    	return bitmapWithReflection;

	}

     public int getCount() {
         //return mDocus.length;
    	 return Integer.MAX_VALUE; 
     }

	public CarouselDataItem getItem(int position) {
		if (mDocus.size() == 0)
			return null;

		if (position >= mDocus.size()) {
			position = position % mDocus.size();
		}
		// return position;
		return mDocus.get(position);
	}

	public long getItemId(int position) {
		if (mDocus.size() == 0)
			return 0; // fix divide by zero on filtering
		if (position >= mDocus.size()) {
			position = position % mDocus.size();
		}
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		// empty items
		if (mDocus.size() ==0) {
			Bitmap empty = BitmapFactory.decodeResource(context.getResources(), R.drawable.carousel_controller_icon_no_device);
			Bitmap reflection = createReflectedImage(empty);
			if (empty != null) {
				empty.recycle();
			}
			CarouselViewItem scaIv = new CarouselViewItem(
					context, reflection,
					"No devices",
					Color.WHITE,
					Constants.m_nTextSizeMedium,
					m_w, m_h);
			return scaIv; //fix divide by zero on filtering
		}
		
		if (position >= mDocus.size()) {
			position = position % mDocus.size();
		}

		Bitmap originalImage = BitmapFactory.decodeResource(context.getResources(),mDocus.get(position).getIconId());
		Bitmap reflection = createReflectedImage(originalImage);
		if (originalImage != null)
			originalImage.recycle();
		CarouselViewItem scaIv = new CarouselViewItem(
				context, reflection,
				mDocus.get(position).getTitle(),
				Color.WHITE,
				Constants.m_nTextSizeMedium,
				m_w, m_h);

		return scaIv;
	}

	public int checkPosition(int position) {
		if (position >= mDocus.size()) {
			position = position % mDocus.size();
		}
		return position;
	}

	/** Returns the size (0.0f to 1.0f) of the views  depending on the 'offset' to the center. 
     * 
     */ 
	public float getScale(boolean focused, int offset) {
		return Math.max(0, 1.0f / (float) Math.pow(2, Math.abs(offset)));
	}

	public void notifyDataSetChanged() {
		super.notifyDataSetChanged();
	}
}
