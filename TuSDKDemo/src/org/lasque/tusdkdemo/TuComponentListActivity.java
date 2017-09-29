/** 
 * TuSdkDemo
 * DemoEntryActivity.java
 *
 * @author 		Clear
 * @Date 		2014-11-15 PM 4:30:52 
 * @Copyright 	(c) 2014 tusdk.com. All rights reserved.
 * @link 		Development document:http://tusdk.com/docs/android/api/
 */
package org.lasque.tusdkdemo;

import org.lasque.tusdk.core.TuSdk;
import org.lasque.tusdk.core.TuSdkContext;
import org.lasque.tusdk.core.secret.StatisticsManger;
import org.lasque.tusdk.core.utils.ContextUtils;
import org.lasque.tusdk.core.view.widget.TuSdkNavigatorBar.NavigatorBarButtonInterface;
import org.lasque.tusdk.core.view.widget.TuSdkNavigatorBar.NavigatorBarButtonType;
import org.lasque.tusdk.core.view.widget.TuSdkNavigatorBar.TuSdkNavigatorBarDelegate;
import org.lasque.tusdk.impl.activity.TuFragmentActivity;
import org.lasque.tusdk.impl.view.widget.TuNavigatorBar;
import org.lasque.tusdk.modules.components.ComponentActType;
import org.lasque.tusdkdemo.examples.component.AlbumComponentSample;
import org.lasque.tusdkdemo.examples.component.AlbumMultipleComponentSample;
import org.lasque.tusdkdemo.examples.suite.CameraComponentSample;
import org.lasque.tusdkdemo.examples.suite.EditMultipleComponentSample;
import org.lasque.tusdkdemo.view.DemoListView;
import org.lasque.tusdkdemo.view.DemoListView.DemoListItemAction;
import org.lasque.tusdkdemo.view.DemoListView.DemoListViewDelegate;

import android.content.Intent;
import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * @author Clear
 */
public class TuComponentListActivity extends TuFragmentActivity implements TuSdkNavigatorBarDelegate
{
	/** Layout ID */
	public static final int layoutId = R.layout.demo_component_list_activity;

	public TuComponentListActivity()
	{

	}

	/** Initialize Controller */
	@Override
	protected void initActivity()
	{
		super.initActivity();
		this.setRootView(layoutId, 0);
	}

	/** Navigation Bar */
	private TuNavigatorBar mNavigatorBar;

	/** Sample List View */
	private DemoListView mListView;

	/** Sliding Back Gesture */
	GestureDetector gdDetector;

	/**
	 * Initialize View
	 */
	@Override
	protected void initView()
	{
		super.initView();
		// SDK statistics code, please don't add it in your application.
		StatisticsManger.appendComponent(ComponentActType.sdkComponent);

		// Navigation bar 
		mNavigatorBar = this.getViewById(R.id.lsq_navigatorBar);
		mNavigatorBar.setTitle(String.format("%s %s", TuSdkContext.getString(R.string.lsq_sdk_name), TuSdk.SDK_VERSION));
		mNavigatorBar.setBackButtonId(R.id.lsq_backButton);
		mNavigatorBar.showBackButton(true);
		mNavigatorBar.delegate = this;

		mListView = this.getViewById(R.id.lsq_listView);
		mListView.setSimpleDelegate(mDemoListViewDelegate);

		// Sliding back gesture
		gdDetector = new GestureDetector(this, gestureListener);

		/**
		 * ！！！！！！！！！！！！！！！！！！！！！！！！！！ Further Hint ！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
		 * You can study how to use SDK function by refering to  the showSimple(Activity
		 * activity) method in the class in group.appendSimple() method 
		 */

		// Sample Group
		SampleGroup group = new SampleGroup();
		
		// Camera Component Sample
		group.appendSample(new CameraComponentSample());
		// Photo Beauty Edit Component Sample
		group.appendSample(new EditMultipleComponentSample());

		// Album Component Sample
		group.appendSample(new AlbumComponentSample());
		// Multi-Selection Album Component Sample
		group.appendSample(new AlbumMultipleComponentSample());

		// Load sample list
		mListView.loadSimples(group);
	}

	/** Sample list view delegate */
	private DemoListViewDelegate mDemoListViewDelegate = new DemoListViewDelegate()
	{
		@Override
		public void onDemoListViewSelected(DemoListView view, SampleBase simple, DemoListItemAction action)
		{
			onSelectedSimple(simple, action);
		}
	};

	/** Sliding back gesture listener */
	GestureDetector.SimpleOnGestureListener gestureListener = new GestureDetector.SimpleOnGestureListener()
	{
		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY)
		{
			// Move Distance
			if (e2.getRawX() - e1.getRawX() < ContextUtils.getScreenSize(TuComponentListActivity.this).width * MAX_SLIDE_DISTANCE) return false;

			// Slide Position
			if (e1.getRawX() > ContextUtils.getScreenSize(TuComponentListActivity.this).width * 0.2) return false;

			// Slide Speed
			if (Math.abs(velocityX) < Math.abs(velocityY) || velocityX < MAX_SLIDE_SPEED) return false;

			// Close Activity
			TuComponentListActivity.this.finish();

			return true;
		}
	};

	/** Select A Sample */
	private void onSelectedSimple(SampleBase sample, DemoListItemAction action)
	{
		if (sample == null || action == null) return;

		switch (action)
			{
			case ActionSelected:
				// Launch specified Activity
				if (sample.getClass() == SampleActivityBase.class)
				{
					Intent intent = new Intent(this, ((SampleActivityBase) sample).activityClazz);
					this.startActivity(intent);
				}
				else
				{
					sample.showSample(this);
				}
				break;
			default:
				break;
			}
	}

	/**
	 * Back Button
	 */
	public void onNavigatorBarButtonClicked(NavigatorBarButtonInterface button)
	{
		if (button.getType() == NavigatorBarButtonType.back)
		{
			this.finish();
		}
	}

	/**
	 * Touch Dispatching Event
	 */
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev)
	{
		gdDetector.onTouchEvent(ev);
		return super.dispatchTouchEvent(ev);
	}
}