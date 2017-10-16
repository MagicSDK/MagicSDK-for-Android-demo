/** 
 * TuSdkDemo
 * SampleActivityBase.java
 *
 * @author 		Yanlin
 * @Date 		2016-1-21 下午16:01:05 
 * @Copyright 	(c) 2015 tusdk.com. All rights reserved.
 * 
 */
package org.lasque.tusdkdemo;

import org.lasque.tusdkdemo.SampleGroup.GroupType;

import android.app.Activity;

/**
 * Samples to demonstrate SDK's features
 * 
 * @author Yanlin
 */
public class SampleActivityBase extends SampleBase
{

	public SampleActivityBase (GroupType groupId, int titleResId)
	{
		super(groupId, titleResId);
	}

	/** Class of Sample's view*/
	public Class<?> activityClazz;
	
	/** Show Sample*/
	public void showSample(Activity activity)
	{
		// leave it blank
	}
}
