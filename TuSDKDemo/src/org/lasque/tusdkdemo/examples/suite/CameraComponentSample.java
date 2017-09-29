/** 
 * TuSdkDemo
 * CameraComponentSimple.java
 *
 * @author 		Clear
 * @Date 		2015-4-21 PM 1:40:10 
 * @Copyright 	(c) 2015 tusdk.com. All rights reserved.
 * 
 */
package org.lasque.tusdkdemo.examples.suite;

import android.app.Activity;

import org.lasque.tusdk.core.TuSdkResult;
import org.lasque.tusdk.core.utils.TLog;
import org.lasque.tusdk.core.utils.TuSdkWaterMarkOption;
import org.lasque.tusdk.core.utils.TuSdkWaterMarkOption.TextPosition;
import org.lasque.tusdk.core.utils.TuSdkWaterMarkOption.WaterMarkPosition;
import org.lasque.tusdk.core.utils.hardware.CameraHelper;
import org.lasque.tusdk.core.utils.image.BitmapHelper;
import org.lasque.tusdk.impl.activity.TuFragment;
import org.lasque.tusdk.impl.components.camera.TuCameraFragment;
import org.lasque.tusdk.impl.components.camera.TuCameraFragment.TuCameraFragmentDelegate;
import org.lasque.tusdk.impl.components.camera.TuCameraOption;
import org.lasque.tusdk.modules.components.TuSdkHelperComponent;
import org.lasque.tusdkdemo.R;
import org.lasque.tusdkdemo.SampleBase;
import org.lasque.tusdkdemo.SampleGroup.GroupType;

/**
 * Camera Component Sample
 * 
 * @author Clear
 */
public class CameraComponentSample extends SampleBase implements TuCameraFragmentDelegate
{
	/** Camera Component Sample */
	public CameraComponentSample()
	{
		super(GroupType.SuiteSample, R.string.sample_CameraComponent);
	}

	/** Show Sample */
	@Override
	public void showSample(Activity activity)
	{
		if (activity == null) return;

		// If your device don't support camera, application will show a warning message.
		if (CameraHelper.showAlertIfNotSupportCamera(activity)) return;
		// Component Option
		// @see-http://tusdk.com/docs/android/api/org/lasque/tusdk/impl/components/camera/TuCameraOption.html
		TuCameraOption option = new TuCameraOption();

		// Set component class
		// option.setComponentClazz(TuCameraFragment.class);

		// Set root view layout ID
		// option.setRootViewLayoutId(TuCameraFragment.getLayoutId());

		// Set picture saved as temporary file 
		// Default is false, if set to true, use TuSdkResult.imageFile to get the result. Original picture will be cleaned up when finish
		// option.setSaveToTemp(false);

		// Set picture saved to album
		// Default is false, if set to true, use TuSdkResult.imageSqlInfo to get the result.
		option.setSaveToAlbum(true);

		// Set album name
		// option.setSaveToAlbumName("TuSdk");

		// Set output picture compression ratio
		// Default is 90, compression ratio range from 0 to 100
		// option.setOutputCompress(90);

		// Set camera facing (Default: CameraFacing.Back)
		// option.setAvPostion(CameraFacing.Front);

		// Set output picture size (Default：Full Screen Size)
		// option.setOutputSize(new TuSdkSize(1440, 1920));
		// Set flash mode
		// option.setDefaultFlashMode(CameraFlash.Off);

		// Enable filter function(Default: false, disable filter function)
		option.setEnableFilters(true);

		// Set filter bar display state
		// Default is hidden, if you have set mEnableFilters to false, this option will lose efficacy
		option.setShowFilterDefault(true);

		// Set filter cell width (Unit:DP)
		// option.setGroupFilterCellWidthDP(60);

		// Set filter selection bar height (Unit:DP)
		// option.setFilterBarHeightDP(80);

		// Set filter group layout ID 
		// Default is tusdk_impl_component_widget_group_filter_group_view, If you want to customize layout, 
		// create a class extend GroupFilterGroupView and override getLayoutId() method
		// option.setGroupTableCellLayoutId(GroupFilterGroupView.getLayoutId());

		// Set filter group item layout ID\
		// Default is tusdk_impl_component_widget_group_filter_item_view, If you want to customize layout, 
		// create a class extend GroupFilterItemView, and override getLayoutId() method
		// option.setFilterTableCellLayoutId(GroupFilterItemView.getLayoutId());

		// Enable filter configure option (Default：true)
		option.setEnableFilterConfig(true);

		// Customize filter list (If list is null, default use all filter.)
		// You can refer to TuSDK.bundle/others/lsq_tusdk_configs.json for filter name
		// filterGroups[]->filters[]->name lsq_filter_%{Brilliant}
		// String[] filters = { "SkinNature", "SkinPink", "SkinJelly",
		// "SkinNoir",
		// "SkinRuddy", "SkinPowder", "SkinSugar" };
		// option.setFilterGroup(Arrays.asList(filters));

		// Set save last filter
		option.setSaveLastFilter(true);

		// Set automatically select default filter when you expand group of filters
		option.setAutoSelectGroupDefaultFilter(true);

		// Set enable filters history
		option.setEnableFiltersHistory(true);

		// Set filter subtitle view display status
		option.setDisplayFiltersSubtitles(true);

		// Set focus view ID (Default: tusdk_impl_component_camera_focus_touch_view)
		// option.setFocusTouchViewId(TuFocusTouchView.getLayoutId());

		// Set camera view ratio (Default: 0, Full Screen)
		// option.setCameraViewRatio(0);

		// Set whether output image data directly (Default is false, so you can get Bitmap)
		// If true, you can get byte[] from TuSdkResult.imageData
		// option.setOutputImageData(false);

		// Disable continuous focus (Default：false)
		// option.setDisableContinueFoucs(true);

		// Disable capture sound (Default:false)
		// option.setDisableCaptureSound(true);

		// Customize capture sound ID. By default, system sound is shut down
		// option.setCaptureSoundRawId(R.raw.lsq_camera_focus_beep);

		// Enable the function that capture with volume keys (Default: false)
		option.setEnableCaptureWithVolumeKeys(true);
		// Set automatically release camera after capture image
		// option.setAutoReleaseAfterCaptured(false);

		// Enable the function that capture by long touching (Default：false)
		option.setEnableLongTouchCapture(true);

		// Enable camera to adjust focal distance (Default：true)
		// option.setEnableFocalDistance(false);
		// Set focal distance default value (Default：0, 0-getMaxZoom())
		// option.setFocalDistanceScale(0);
		
		// Disable focus sound (Default:false)
		// option.setDisableFocusBeep(true);

		// Set use unified parameters (Default: false, cancel samsung's default noise reduction, sharpening)
		// option.setUnifiedParameters(false);

		// Set preview scale (Default:0.75f, reduce to full-screen size when you previewing in real time, 
		// to improving preview efficiency. scale <= 1)
		// option.setPreviewEffectScale(0.7f);

		// Set video view color (Default：0xFF000000)
		// option.setRegionViewColor(0xFF000000);

		// Disable the front camera automatic horizontal mirroring (Default: false)
		// option.setDisableMirrorFrontFacing(true);

		// Enable face detection (Face Detection requires permission, please go to console(tusdk.com) to access permission)
		option.enableFaceDetection = true;
		
		// Set output picture with water mark (By default water mark option is null, if not null, output picture will have water mark)
		// option.setWaterMarkOption(getWaterMarkOption(activity));
		
		TuCameraFragment fragment = option.fragment();
		fragment.setDelegate(this);

		// see-http://tusdk.com/docs/android/api/org/lasque/tusdk/impl/components/base/TuSdkHelperComponent.html
		this.componentHelper = new TuSdkHelperComponent(activity);
		// Open Camera
		this.componentHelper.presentModalNavigationActivity(fragment, true);
	}
	
	/**
	 * Water Mark Option
	 * 
	 * @return
	 */
	public TuSdkWaterMarkOption getWaterMarkOption(Activity activity)
	{
		TuSdkWaterMarkOption option = new TuSdkWaterMarkOption();
		
		// You must ensure that setMarkText or setMarkImage is not null.
	    // Set water mark text. Support text, image, and both text and image.
	    option.setMarkText("");
	    
	    // Set water mark text color (Default:#FFFFFF)
	    option.setMarkTextColor("#FFFFFF");
	    
	    // Set water mark text size (Default: 24 SP)
	    option.setMarkTextSize(24);
	    
	    // Set water mark text shadow color (Default:#000000)
	    option.setMarkTextShadowColor("#000000");
	    
	    // Set water mark image.
	    option.setMarkImage(BitmapHelper.getBitmapFormRaw(activity, R.raw.sample_watermark));
	    
	    // Set text or image position(Only when text and image are both not null. Default: TextPosition.Right)
	    option.setMarkTextPosition(TextPosition.Right);

	    // Set water mark position (Default: WaterMarkPosition.BottomRight)
	    option.setMarkPosition(WaterMarkPosition.BottomRight);
	    
	    // Set the margin between water mark and picture (Default: 6dp)
	    option.setMarkMargin(6);
	    
	    // Set the padding between text and picture(Default: 2dp)
	    option.setMarkTextPadding(5);
	    
	    return option;
	}

	/**
	 * Get the picture captured.
	 * 
	 * You can get Bitmap, File, or ImageSqlInfo from TuSdkResult object.
	 * In the sample, camera is closed after capture, and you can pass the picture into edit component here.
	 * Welcome to visit document(http://tusdk.com/doc)to get more sample.
	 * 
	 * @param fragment
	 *            A default camera view
	 * @param result
	 */
	@Override
	public void onTuCameraFragmentCaptured(TuCameraFragment fragment, TuSdkResult result)
	{
		fragment.hubDismissRightNow();
		fragment.dismissActivityWithAnim();
		TLog.d("onTuCameraFragmentCaptured: %s", result);
		
		// Output Bitmap by default -> result.image
		
		// Output temporary file if you enable mSaveToTemp, get the result using result.imageFile
		// option.setSaveToTemp(true);  ->  result.imageFile

		// Output ImageSqlInfo if you enable mSaveToAlbum, get the result using result.image
		// option.setSaveToAlbum(true);  -> result.image
	}

	/**
	 * Get a result (Asynchronous Method)
	 * 
	 * @param fragment
	 *            A default camera view
	 * @param result
	 *            you can get picture from result.
	 * @return true if you consume the event (Default: false)
	 */
	@Override
	public boolean onTuCameraFragmentCapturedAsync(TuCameraFragment fragment, TuSdkResult result)
	{
		TLog.d("onTuCameraFragmentCapturedAsync: %s", result);
		return false;
	}

	/**
	 * Request to jump to album component from camera component, only occurs when you set mDisplayAlbumPoster to true.
	 *
	 * @param fragment
	 *            A system album
	 */
	@Override
	public void onTuAlbumDemand(TuCameraFragment fragment)
	{

	}

	@Override
	public void onComponentError(TuFragment fragment, TuSdkResult result, Error error)
	{
		TLog.d("onComponentError: fragment - %s, result - %s, error - %s", fragment, result, error);
	}

}