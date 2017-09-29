/** 
 * TuSdkDemo
 * AlbumComponentSimple.java
 *
 * @author 		Clear
 * @Date 		2015-4-21 PM 1:39:10 
 * @Copyright 	(c) 2015 Lasque. All rights reserved.
 * 
 */
package org.lasque.tusdkdemo.examples.component;

import org.lasque.tusdk.TuSdkGeeV1;
import org.lasque.tusdk.core.TuSdkResult;
import org.lasque.tusdk.core.utils.TLog;
import org.lasque.tusdk.impl.activity.TuFragment;
import org.lasque.tusdk.impl.components.TuAlbumMultipleComponent;
import org.lasque.tusdk.modules.components.TuSdkComponent.TuSdkComponentDelegate;
import org.lasque.tusdkdemo.R;
import org.lasque.tusdkdemo.SampleBase;
import org.lasque.tusdkdemo.SampleGroup.GroupType;

import android.app.Activity;

/**
 * Multiple Album Component Sample
 * 
 * @author Clear
 */
public class AlbumMultipleComponentSample extends SampleBase
{
	/**
	 * Multiple Album Component Sample
	 */
	public AlbumMultipleComponentSample()
	{
		super(GroupType.ComponentSample, R.string.sample_AlbumMultipleComponent);
	}

	/**
	 * Show Sample
	 * 
	 * @param activity
	 */
	@Override
	public void showSample(Activity activity)
	{
		if (activity == null) return;
		
		// @see-http://tusdk.com/docs/android/api/org/lasque/tusdk/impl/components/TuAlbumMultipleComponent.html
		TuAlbumMultipleComponent comp = TuSdkGeeV1.albumMultipleCommponent(activity,
				new TuSdkComponentDelegate()
				{
					@Override
					public void onComponentFinished(TuSdkResult result,
							Error error, TuFragment lastFragment)
					{
						// if (lastFragment != null)
						// lastFragment.dismissActivityWithAnim();
						// Get selected picture from parameter(result.images)
						TLog.d("onAlbumCommponentReaded: %s | %s", result,
								error);
					}
				},
				// Set that max number of selected picture is nine.
				9);

		// Component Option
		// @see-http://tusdk.com/docs/android/api/org/lasque/tusdk/impl/components/TuAlbumComponentOption.html
		// comp.componentOption()

		// @see-http://tusdk.com/docs/android/api/org/lasque/tusdk/impl/components/album/TuAlbumListOption.html
		// comp.componentOption().albumListOption()

		// @see-http://tusdk.com/docs/android/api/org/lasque/tusdk/impl/components/camera/TuCameraOption.html
		// comp.componentOption().cameraOption()
		
		// Set album photo sort way
		//comp.componentOption().albumListOption().setPhotosSortDescriptor(PhotoSortDescriptor.Date_Modified);
		
		// Set max size of selected picture Default：8000 * 8000
		// comp.componentOption().albumListOption().setMaxSelectionImageSize(new TuSdkSize(8000, 8000));

		
		// Set that component automatically closed after the component executes.
		comp.setAutoDismissWhenCompleted(true)
		// Show component
				.showComponent();
	}
}
