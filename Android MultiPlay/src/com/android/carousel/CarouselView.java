package com.android.carousel;

import android.content.Context;
import android.graphics.Camera;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.Transformation;
import android.widget.Gallery;

@SuppressWarnings("deprecation")
public class CarouselView extends Gallery  {

	/** Graphics Camera used for transforming the matrix of ImageViews.
     * 
     */
	private Camera mCamera = new Camera();
	
	/** The maximum angle the Child ImageView will be rotated by.
	 * 
	 */    
	private int mMaxRotationAngle = 60;
	
	/** The Centre of the Coverflow 
	 * 
	 */
    private int mCoveflowCenter;
    
    /** TODO
     * 
     */
   private float selectedElementSensitive = 0.5f;
    /**
     * 
     * @param context
     */
   public CarouselView(Context context) {
	   super(context);
       this.setStaticTransformationsEnabled(true);
   }
   /**
    * 
    * @param context
    * @param selectedElementSensitive
    */
    public CarouselView(Context context, float selectedElementSensitive) {
    	super(context);
    	this.selectedElementSensitive = selectedElementSensitive;
        this.setStaticTransformationsEnabled(true);
    }
 /**
  * 
  * @param context
  * @param attrs
  * @param defStyle
  */
    public CarouselView(Context context, AttributeSet attrs, int defStyle) {
    	super(context, attrs, defStyle);
    	this.setStaticTransformationsEnabled(true);   
    }
    /**
     * 
     * @param context
     * @param attrs
     * @param defStyle
     * @param selectedElementSensitive
     */
    public CarouselView(Context context, AttributeSet attrs, int defStyle, float selectedElementSensitive) {
    	super(context, attrs, defStyle);
    	this.selectedElementSensitive = selectedElementSensitive;
    	this.setStaticTransformationsEnabled(true);   
    }

    /** Get the max rotational angle of the image.
     * 
     * @return the mMaxRotationAngle
     */
    public int getMaxRotationAngle() {
    	return mMaxRotationAngle;
    }

    /** Set the max rotational angle of each image.
     * 
     * @param maxRotationAngle the mMaxRotationAngle to set
     */
    public void setMaxRotationAngle(int maxRotationAngle) {
    	mMaxRotationAngle = maxRotationAngle;
    }

    /** This is called during layout when the size of this view has changed. 
     * 
     * If you were just added to the view hierarchy, you're called with the old values of 0.
     *
     * @param w Current width of this view.
     * @param h Current height of this view.
     * @param oldw Old width of this view.
     * @param oldh Old height of this view.
     */
     protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    	 mCoveflowCenter = (getWidth() - getPaddingLeft() - getPaddingRight()) / 2 + getPaddingLeft();
    	 super.onSizeChanged(w, h, oldw, oldh);
     }
    
     /**
      * {@inheritDoc}
      *
      * @see #setStaticTransformationsEnabled(boolean) 
      */ 
    protected boolean getChildStaticTransformation(View child, Transformation t) {
    	CarouselViewItem s = (CarouselViewItem)child;
    	final int childCenter = s.getLeft() + s.getMaxWeight()/2;//getCenterOfView(s);//child);
    	final int childWidth = s.getMaxWeight();//child.getWidth() ;
    	//Log.e("db", "getChildStaticTransformation:" +childCenter + ":"+childWidth);
    	final float delta = 0.2f*selectedElementSensitive*((s.getRight()-s.getLeft())/2);
    	final float leftSpaceToCenter = mCoveflowCenter - (s.getLeft()+delta);
    	final float rightSpaceToCenter = (s.getRight()-delta) - mCoveflowCenter;
    	int rotationAngle = 0;

    	t.clear();
    	t.setTransformationType(Transformation.TYPE_MATRIX);
 

    	if ( leftSpaceToCenter > 0 && Math.signum(leftSpaceToCenter) == Math.signum(rightSpaceToCenter) ) {
        	transformImageBitmap(s, t, 0);
        } else {      
        	rotationAngle = (int) (((float) (mCoveflowCenter - childCenter)/ childWidth) *  mMaxRotationAngle);
            if (Math.abs(rotationAngle) > mMaxRotationAngle) 
            	rotationAngle = (rotationAngle < 0) ? -mMaxRotationAngle : mMaxRotationAngle;   
            transformImageBitmap(s, t, rotationAngle);         
        }     
        return true;
    }
   
    
    /** Transform the Image Bitmap by the Angle passed. 
     * 
     * 0 for center item.
     * 
     * @param imageView ImageView the ImageView whose bitmap we want to rotate
     * @param t transformation 
     * @param rotationAngle the Angle by which to rotate the Bitmap
     */
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
