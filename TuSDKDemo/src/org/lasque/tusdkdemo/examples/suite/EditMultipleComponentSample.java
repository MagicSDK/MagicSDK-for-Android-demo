/** 
 * TuSdkDemo
 * EditMultipleComponentSimple.java
 *
 * @author 		Clear
 * @Date 		2015-4-21 PM 1:38:04 
 * @Copyright 	(c) 2015 tusdk.com. All rights reserved.
 * 
 */
package org.lasque.tusdkdemo.examples.suite;

import org.lasque.tusdk.TuSdkGeeV1;
import org.lasque.tusdk.core.TuSdkResult;
import org.lasque.tusdk.core.utils.TLog;
import org.lasque.tusdk.core.utils.TuSdkWaterMarkOption;
import org.lasque.tusdk.core.utils.TuSdkWaterMarkOption.TextPosition;
import org.lasque.tusdk.core.utils.TuSdkWaterMarkOption.WaterMarkPosition;
import org.lasque.tusdk.core.utils.image.BitmapHelper;
import org.lasque.tusdk.impl.activity.TuFragment;
import org.lasque.tusdk.impl.components.TuAlbumComponent;
import org.lasque.tusdk.impl.components.TuEditMultipleComponent;
import org.lasque.tusdk.modules.components.TuSdkComponent.TuSdkComponentDelegate;
import org.lasque.tusdk.modules.components.TuSdkHelperComponent;
import org.lasque.tusdkdemo.R;
import org.lasque.tusdkdemo.SampleBase;
import org.lasque.tusdkdemo.SampleGroup.GroupType;

import android.app.Activity;

/**
 * Photo Beauty Component Sample
 * 
 * @author Clear
 */
public class EditMultipleComponentSample extends SampleBase
{
	/** Photo Beauty Component Sample */
	public EditMultipleComponentSample()
	{
		super(GroupType.SuiteSample, R.string.sample_EditMultipleComponent);
	}

	/**
	 * This is the component entrance. In this sample, you select a picture from album as the input source before start the editor.
	 * You can start editor in other way according to your needs. For example, you can use the result of camera as the input source.
	 * Welcome to go to document center(http://tusdk.com/doc) to get more sample.
	 * 
	 * The following file formats are all supported in TuSDK: Bitmap | File | ImageSqlInfo.
	 * 
	 * // Set input image
	 * component.setImage(result.image)
	 *  		// Set system photo
	 *  		.setImageSqlInfo(result.imageSqlInfo)
	 *  		// Set temporary file
	 *  		.setTempFilePath(result.imageFile)
	 * 
	 * Priority: Image > TempFilePath > ImageSqlInfo
	 */
	@Override
	public void showSample(Activity activity)
	{
		if (activity == null) return;
		// see-http://tusdk.com/docs/android/api/org/lasque/tusdk/impl/components/base/TuSdkHelperComponent.html
		this.componentHelper = new TuSdkHelperComponent(activity);

		TuAlbumComponent component = TuSdkGeeV1.albumCommponent(activity, new TuSdkComponentDelegate()
		{
			@Override
			public void onComponentFinished(TuSdkResult result, Error error, TuFragment lastFragment)
			{
				openEditMultiple(result, error, lastFragment);
			}
		});
		
		// Set the max size of pictures (Default：8000 * 8000)
		// component.componentOption().photoListOption().setMaxSelectionImageSize(new TuSdkSize(8000, 8000));
		
		component.showComponent();
	}

	/** Open Photo Beauty Component */
	private void openEditMultiple(TuSdkResult result, Error error, TuFragment lastFragment)
	{
		if (result == null || error != null) return;

		// Component delegate
		TuSdkComponentDelegate delegate = new TuSdkComponentDelegate()
		{
			@Override
			public void onComponentFinished(TuSdkResult result, Error error, TuFragment lastFragment)
			{
				TLog.d("onEditMultipleComponentReaded: %s | %s", result, error);
				
				// Output Bitmap by default -> result.image
				
				// Output temporary file if you enable mSaveToTemp, get the result using result.imageFile
				// option.setSaveToTemp(true);  ->  result.imageFile

				// Output ImageSqlInfo if you enable mSaveToAlbum, get the result using result.image
				// option.setSaveToAlbum(true);  -> result.image
			}
		};

		// Component Option
		// @see-http://tusdk.com/docs/android/api/org/lasque/tusdk/impl/components/TuEditMultipleComponent.html
		TuEditMultipleComponent component = null;

		if (lastFragment == null)
		{
			component = TuSdkGeeV1.editMultipleCommponent(this.componentHelper.activity(), delegate);
		}
		else
		{
			component = TuSdkGeeV1.editMultipleCommponent(lastFragment, delegate);
		}

		// @see-http://tusdk.com/docs/android/api/org/lasque/tusdk/impl/components/TuEditMultipleComponentOption.html
		// component.componentOption()

		// @see-http://tusdk.com/docs/android/api/org/lasque/tusdk/impl/components/edit/TuEditMultipleOption.html
		// component.componentOption().editMultipleOption()
		
		// Set output picture with water mark(By default water mark option is null, if not null, output picture will have water mark.)
		// component.componentOption().editMultipleOption().setWaterMarkOption(getWaterMarkOption(this.componentHelper.activity()));

		// @see-http://tusdk.com/docs/android/api/org/lasque/tusdk/impl/components/edit/TuEditCuterOption.html
		// component.componentOption().editCuterOption()

		// @see-http://tusdk.com/docs/android/api/org/lasque/tusdk/impl/components/filter/TuEditFilterOption.html
		// component.componentOption().editFilterOption()

		// @see-http://tusdk.com/docs/android/api/org/lasque/tusdk/impl/components/filter/TuEditSkinOption.html
		// component.componentOption().editSkinOption()

		// @see-http://tusdk.com/docs/android/api/org/lasque/tusdk/impl/components/sticker/TuEditStickerOption.html
		// component.componentOption().editStickerOption()

		// @see-http://tusdk.com/docs/android/api/org/lasque/tusdk/impl/components/filter/TuEditAdjustOption.html
		// component.componentOption().editAdjustOption()

		// @see-http://tusdk.com/docs/android/api/org/lasque/tusdk/impl/components/filter/TuEditSharpnessOption.html
		// component.componentOption().editSharpnessOption()

		// @see-http://tusdk.com/docs/android/api/org/lasque/tusdk/impl/components/filter/TuEditApertureOption.html
		// component.componentOption().editApertureOption()

		// @see-http://tusdk.com/docs/android/api/org/lasque/tusdk/impl/components/filter/TuEditVignetteOption.html
		// component.componentOption().editVignetteOption()
		
		// @see-http://tusdk.com/docs/android/api/org/lasque/tusdk/impl/components/smudge/TuEditSmudgeOption.html
		// component.componentOption().editSmudgeOption()
		
		// @see-http://tusdk.com/docs/android/api/org/lasque/tusdk/impl/components/filter/TuEditWipeAndFilterOption.html
		// component.componentOption().editWipeAndFilterOption()
		
		// @see-http://tusdk.com/docs/android/api/org/lasque/tusdk/impl/components/filter/TuEditHDROption.html
		// component.componentOption().editHDROption()

		// Set input image
		component.setImage(result.image)
			// Set system picture
			.setImageSqlInfo(result.imageSqlInfo)
			// Set temporary file
			.setTempFilePath(result.imageFile)
			// Set automatically close the component when finish
			.setAutoDismissWhenCompleted(true)
			// Open component
			.showComponent();
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
}