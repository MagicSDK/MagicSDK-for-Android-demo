/** 
 * TuSdkDemo
 * SimpleGroup.java
 *
 * @author 		Clear
 * @Date 		2015-4-21 下午1:00:42 
 * @Copyright 	(c) 2015 tusdk.com. All rights reserved.
 * 
 */
package org.lasque.tusdkdemo;

import java.util.ArrayList;
import java.util.List;

/**
 * Sample Grouop
 * 
 * @author Clear
 */
public class SampleGroup
{
	public enum GroupType
	{
		/**
		 * Suit Sample
		 */
		SuiteSample,
		/**
		 *  Component Sample
		 */
		ComponentSample,
	}
	
	/** Sample Group Header Info */
	public static class GroupHeader
	{
		/** Group ID */
		public GroupType groupId;
		/** Title Resource ID */
		public int titleResId;
		/** Data List */
		public List<SampleBase> datas;

		/**
		 * Sample Group Header Info
		 * 
		 * @param groupId
		 *            Group ID
		 * @param titleResId
		 *            Title Resource ID
		 */
		public GroupHeader(GroupType groupId, int titleResId)
		{
			this.datas = new ArrayList<SampleBase>();
			this.groupId = groupId;
			this.titleResId = titleResId;
		}
	}

	/** Sample Group Header Info List */
	public final List<GroupHeader> headers;

	/** Sample Group */
	public SampleGroup()
	{
		// Sample Group Header Info List
		headers = new ArrayList<GroupHeader>();
		
		// Suit
		headers.add(new GroupHeader(GroupType.SuiteSample, R.string.sample_group_suite));
				
		// Common Component
		headers.add(new GroupHeader(GroupType.ComponentSample, R.string.sample_group_comp));
	}

	/** Add sample */
	public void appendSample(SampleBase sample)
	{
		if (sample == null) return;

		for (GroupHeader header : headers)
		{
			if (header.groupId == sample.groupId)
			{
				header.datas.add(sample);
			}
		}
	}
	
	public void appendSample(GroupType groupId, int titleResId, Class<?> clazz)
	{
		SampleActivityBase sample = new SampleActivityBase(groupId, titleResId);
		sample.activityClazz = clazz;
		
		appendSample(sample);
	}

	/**
	 * Get sample group header info
	 * 
	 * @param index
	 *            Index
	 * @return
	 */
	public GroupHeader getHeader(int index)
	{
		if (this.headers == null || this.headers.size() <= index) return null;
		return this.headers.get(index);
	}
}