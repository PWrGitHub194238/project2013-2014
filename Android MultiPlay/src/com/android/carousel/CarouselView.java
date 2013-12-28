package com.android.carousel;

import android.content.Context;
import android.graphics.Camera;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Transformation;
import android.widget.Gallery;

public class CarouselView extends Gallery  {
	
	private Camera mCamera = new Camera();
	private int mMaxRotationAngle = 60; //60
    private int mCoveflowCenter;
   
	public CarouselView(Context context) {
    	super(context);
    	this.setStaticTransformationsEnabled(true);
    }
    public CarouselView(Context context, AttributeSet attrs) {
    	super(context, attrs);
        this.setStaticTransformationsEnabled(true);
    }
 
    public CarouselView(Context context, AttributeSet attrs, int defStyle) {
    	super(context, attrs, defStyle);
    	this.setStaticTransformationsEnabled(true);   
    }

    public int getMaxRotationAngle() {
    	return mMaxRotationAngle;
    }

    public void setMaxRotationAngle(int maxRotationAngle) {
    	mMaxRotationAngle = maxRotationAngle;
    }

     protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    	 mCoveflowCenter = (getWidth() - getPaddingLeft() - getPaddingRight()) / 2 + getPaddingLeft();
    	 super.onSizeChanged(w, h, oldw, oldh);
     }
    
    protected boolean getChildStaticTransformation(View child, Transformation t) {
    	CarouselViewItem s = (CarouselViewItem)child;
    	final int childCenter = s.getLeft() + s.getMaxWeight()/2;//getCenterOfView(s);//child);
    	final int childWidth = s.getMaxWeight();//child.getWidth() ;
    	//Log.e("db", "getChildStaticTransformation:" +childCenter + ":"+childWidth);
    	
    	int rotationAngle = 0;
  
    	t.clear();
    	t.setTransformationType(Transformation.TYPE_MATRIX);
 
        if (childCenter == mCoveflowCenter) {
        	transformImageBitmap(s, t, 0);
        } else {      
        	rotationAngle = (int) (((float) (mCoveflowCenter - childCenter)/ childWidth) *  mMaxRotationAngle);
            if (Math.abs(rotationAngle) > mMaxRotationAngle) 
            	rotationAngle = (rotationAngle < 0) ? -mMaxRotationAngle : mMaxRotationAngle;   
            transformImageBitmap(s, t, rotationAngle);         
        }     
        return true;
    }
   
     private void transformImageBitmap(CarouselViewItem s, Transformation t, int rotationAngle) {            
    	 mCamera.save();
    	 final Matrix imageMatrix = t.getMatrix();;
    	
    	 
    	 final int imageWidth = s.getMaxWeight();
    	 final int imageHeight = s.getMaxHeight();
     	
    	 final int rotation = Math.abs(rotationAngle);
         //As the angle of the view gets less, zoom in     
    	 float zoomAmount = (float) (rotation * 3);
    	 mCamera.translate(0.0f, 0.0f, zoomAmount);          
    	 
    	 mCamera.rotateY(rotationAngle);
    	 mCamera.getMatrix(imageMatrix);               
    	 imageMatrix.preTranslate(-(imageWidth/2), -(imageHeight/2)); 
    	 imageMatrix.postTranslate((imageWidth/2), (imageHeight/2));
    	 mCamera.restore();
     }
}
