/** 
 * TuSdkDemo
 * SimpleBase.java
 *
 * @author 		Clear
 * @Date 		2015-4-21 PM 12:52:59 
 * @Copyright 	(c) 2015 tusdk.com. All rights reserved.
 * 
 */
package org.lasque.tusdkdemo;

import org.lasque.tusdk.modules.components.TuSdkHelperComponent;
import org.lasque.tusdkdemo.SampleGroup.GroupType;

import android.app.Activity;

/**
 * Sample interface, an encapsulated component sample
 * 
 * @author Clear
 */
public abstract class SampleBase
{
	/** Group ID */
	public GroupType groupId;

	/** Title Resource ID */
	public int titleResId;
	
	/** Component helper class */
	// see-http://tusdk.com/docs/android/api/org/lasque/tusdk/impl/components/base/TuSdkHelperComponent.html
	public TuSdkHelperComponent componentHelper;

	/**
	 * Sample GroupHeader Info
	 * 
	 * @param groupId
	 *            Group ID
	 * @param titleResId
	 *            Title Resource ID
	 */
	public SampleBase(GroupType groupId, int titleResId)
	{
		this.groupId = groupId;
		this.titleResId = titleResId;
	}

	/** Show Sample */
	public abstract void showSample(Activity activity);
}