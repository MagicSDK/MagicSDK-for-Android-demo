/** 
  * TuSdkDemo
 * DemoEntryActivity.java
 *
 * @author 		Clear
 * @Date 		2014-11-15 下午4:30:52 
 * @Copyright 	(c) 2014 tusdk.com. All rights reserved.
 * @link 		development document:http://tusdk.com/docs/android/api/
 */
package org.lasque.tusdkdemo;

import org.lasque.tusdk.core.TuSdk;
import org.lasque.tusdk.core.secret.StatisticsManger;
import org.lasque.tusdk.core.seles.tusdk.FilterManager;
import org.lasque.tusdk.core.seles.tusdk.FilterManager.FilterManagerDelegate;
import org.lasque.tusdk.impl.activity.TuFragmentActivity;
import org.lasque.tusdk.modules.components.ComponentActType;
import org.lasque.tusdkdemo.examples.suite.CameraComponentSample;
import org.lasque.tusdkdemo.examples.suite.EditMultipleComponentSample;

import android.view.View;

/**
 * @author Clear
 */
public class DemoEntryActivity extends TuFragmentActivity
{
	/** Layout ID */
	public static final int layoutId = R.layout.demo_entry_activity;

	public DemoEntryActivity()
	{
		/**
		 ************************* Three Steps To Integrate With TuSDK *************************
		 * 
		 * 1. Register a developer account in official website
		 * 
		 * 2. Download SDK and sample project
		 * 
		 * 3. Create application, get appkey and resource files
		 * 
		 ************************* Three Steps To Integrate With TuSDK ************************* 
		 * 
		 * Development Documents: http://tusdk.com/doc
		 * 
		 */
	}

	/** Initialize Controller */
	@Override
	protected void initActivity()
	{
		super.initActivity();
		this.setRootView(layoutId, 0);

		// Set the info id when exit application. 
		this.setAppExitInfoId(R.string.lsq_exit_info);
	}

	/** Camera button view */
	private View mCameraButtonView;

	/** Editor button view */
	private View mEditorButtonView;
	
	/**
	 * Initialize view
	 */
	@Override
	protected void initView()
	{
		super.initView();
		// SDK's statistics code, please don't add to your project.
		StatisticsManger.appendComponent(ComponentActType.sdkComponent);
		
		// Initialize filter manager in asynchronous mode
		// (Attention: You don't need to do this unless you need to use TuSDK components 
		// as soon as you open your application)
		// You should not use TuSDK's function until the filter manager has initialized successfully.
		TuSdk.messageHub().setStatus(this, R.string.lsq_initing);
		TuSdk.checkFilterManager(mFilterManagerDelegate);

		mCameraButtonView = this.getViewById(R.id.lsq_entry_camera);
		mEditorButtonView = this.getViewById(R.id.lsq_entry_editor);
		mCameraButtonView.setOnClickListener(mButtonClickListener);
		mEditorButtonView.setOnClickListener(mButtonClickListener);
	}
    
	/** Filter Manager Delegate */
	private FilterManagerDelegate mFilterManagerDelegate = new FilterManagerDelegate()
	{
		@Override
		public void onFilterManagerInited(FilterManager manager)
		{
			TuSdk.messageHub().showSuccess(DemoEntryActivity.this, R.string.lsq_inited);
		}
	};

	/** Button Click Listener*/
	private View.OnClickListener mButtonClickListener = new View.OnClickListener()
	{
		public void onClick(View v)
		{

			if (v == mCameraButtonView)
			{
				showCameraComponent();
			}
			else if (v == mEditorButtonView)
			{
				showEditorComponent();
			}
		}
	};

	/** Open Camera Component */
	private void showCameraComponent()
	{
		new CameraComponentSample().showSample(this);
	}

	/** Open Beauty Editor Component */
	private void showEditorComponent()
	{
		new EditMultipleComponentSample().showSample(this);
	}
}