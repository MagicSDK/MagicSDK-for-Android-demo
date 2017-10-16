/** 
 * TuSdkDemo
 * DemoListHeader.java
 *
 * @author 		Clear
 * @Date 		2015-4-21 PM 2:13:03 
 * @Copyright 	(c) 2015 tusdk.com. All rights reserved.
 * 
 */
package org.lasque.tusdkdemo.view;

import org.lasque.tusdk.core.view.listview.TuSdkCellRelativeLayout;
import org.lasque.tusdkdemo.R;
import org.lasque.tusdkdemo.SampleGroup.GroupHeader;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Demo List Group View
 * 
 * @author Clear
 */
public class DemoListHeader extends TuSdkCellRelativeLayout<GroupHeader>
{
	/** Layout ID */
	public static int getLayoutId()
	{
		return R.layout.demo_view_list_header;
	}

	public DemoListHeader(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
	}

	public DemoListHeader(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}

	public DemoListHeader(Context context)
	{
		super(context);
	}

	/** Title View */
	private TextView mTitleView;

	@Override
	public void loadView()
	{
		super.loadView();
		// Title View
		mTitleView = this.getViewById(R.id.lsq_titleLabel);
	}

	@Override
	protected void bindModel()
	{
		if (this.getModel() == null || this.getModel().titleResId == 0) return;
		mTitleView.setText(this.getModel().titleResId);
	}
}