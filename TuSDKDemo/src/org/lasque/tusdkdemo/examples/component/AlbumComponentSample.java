/** 
 * TuSdkDemo
 * AlbumComponentSimple.java
 *
 * @author 		Clear
 * @Date 		2015-4-21 PM 1:39:10 
 * @Copyright 	(c) 2015 tusdk.com. All rights reserved.
 * 
 */
package org.lasque.tusdkdemo.examples.component;

import org.lasque.tusdk.TuSdkGeeV1;
import org.lasque.tusdk.core.TuSdkResult;
import org.lasque.tusdk.core.utils.TLog;
import org.lasque.tusdk.impl.activity.TuFragment;
import org.lasque.tusdk.impl.components.TuAlbumComponent;
import org.lasque.tusdk.modules.components.TuSdkComponent.TuSdkComponentDelegate;
import org.lasque.tusdkdemo.R;
import org.lasque.tusdkdemo.SampleBase;
import org.lasque.tusdkdemo.SampleGroup.GroupType;

import android.app.Activity;

/**
 * Album Component Sample
 * 
 * @author Clear
 */
public class AlbumComponentSample extends SampleBase
{
	/** Album Component Sample */
	public AlbumComponentSample()
	{
		super(GroupType.ComponentSample, R.string.sample_AlbumComponent);
	}

	/** Show sample */
	@Override
	public void showSample(Activity activity)
	{
		if (activity == null) return;

		// @see-http://tusdk.com/docs/android/api/org/lasque/tusdk/impl/components/TuAlbumComponent.html
		TuAlbumComponent comp = TuSdkGeeV1.albumCommponent(activity, new TuSdkComponentDelegate()
		{
			@Override
			public void onComponentFinished(TuSdkResult result, Error error, TuFragment lastFragment)
			{
				// if (lastFragment != null)
				// lastFragment.dismissActivityWithAnim();
				TLog.d("onAlbumCommponentReaded: %s | %s", result, error);
			}
		});

		// Component Option
		// @see-http://tusdk.com/docs/android/api/org/lasque/tusdk/impl/components/TuAlbumComponentOption.html
		// comp.componentOption()

		// @see-http://tusdk.com/docs/android/api/org/lasque/tusdk/impl/components/album/TuAlbumListOption.html
		// comp.componentOption().albumListOption()

		// @see-http://tusdk.com/docs/android/api/org/lasque/tusdk/impl/components/album/TuPhotoListOption.html
		// comp.componentOption().photoListOption()

		// Set max size of selected picture  Default：CGSize(8000,8000)
//		comp.componentOption().photoListOption().setMaxSelectionImageSize(new TuSdkSize(8000,8000));
		
		// Set that component automatically closed after the component executes.
		comp.setAutoDismissWhenCompleted(true)
		// Show component
				.showComponent();
	}
}