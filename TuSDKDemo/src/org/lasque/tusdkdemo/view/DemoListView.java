/** 
 * TuSdkDemo
 * DemoListView.java
 *
 * @author 		Clear
 * @Date 		2015-4-21 PM 2:11:42 
 * @Copyright 	(c) 2015 tusdk.com. All rights reserved.
 * 
 */
package org.lasque.tusdkdemo.view;

import java.util.ArrayList;
import java.util.List;

import org.lasque.tusdk.core.view.listview.TuSdkCellViewInterface;
import org.lasque.tusdk.core.view.listview.TuSdkIndexPath;
import org.lasque.tusdk.core.view.listview.TuSdkIndexPath.TuSdkDataSource;
import org.lasque.tusdk.impl.view.widget.listview.TuGroupListView;
import org.lasque.tusdkdemo.SampleBase;
import org.lasque.tusdkdemo.SampleGroup;
import org.lasque.tusdkdemo.SampleGroup.GroupHeader;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * Demo List View
 * 
 * @author Clear
 */
public class DemoListView extends TuGroupListView<SampleBase, DemoListCell, GroupHeader, DemoListHeader>
{
	/** Demo List Item Action */
	public enum DemoListItemAction
	{
		/** Selected state */
		ActionSelected,
		/** Configure */
		ActionConfig,
	}

	/** Demo List View Delegate */
	public interface DemoListViewDelegate
	{
		/**
		 * Select demo list view
		 * 
		 * @param view
		 *            list view
		 * @param simple
		 *            sample
		 * @param action
		 *            
		 */
		void onDemoListViewSelected(DemoListView view, SampleBase simple, DemoListItemAction action);
	}

	public DemoListView(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
	}

	public DemoListView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}

	public DemoListView(Context context)
	{
		super(context);
	}

	/** Demo list view delegate */
	private DemoListViewDelegate mSimpleDelegate;

	/** Get demo list view delegate */
	public DemoListViewDelegate getSimpleDelegate()
	{
		return mSimpleDelegate;
	}

	/** Set demo list view delegate */
	public void setSimpleDelegate(DemoListViewDelegate mDelegate)
	{
		this.mSimpleDelegate = mDelegate;
	}

	@Override
	protected void initView()
	{
		super.initView();
		// Set demo list cell layout ID
		this.setCellLayoutId(DemoListCell.getLayoutId());
		// Set demo list group header layout ID
		this.setHeaderLayoutId(DemoListHeader.getLayoutId());
		// Set demo list item click listener
		this.setItemClickListener(new DemoListViewItemClick());
	}

	@Override
	protected void onGroupListViewCreated(DemoListCell view, TuSdkIndexPath indexPath)
	{

	}

	@Override
	protected void onGroupListHeaderCreated(DemoListHeader view, TuSdkIndexPath indexPath)
	{

	}

	/** Load sample group */
	public void loadSimples(SampleGroup group)
	{
		this.setDataSource(new SimpleGroupDataSource(group));
	}

	/** Select one sample*/
	private void onSelectedItem(SampleBase itemData)
	{
		if (this.getSimpleDelegate() == null || itemData == null) return;
		this.getSimpleDelegate().onDemoListViewSelected(this, itemData, DemoListItemAction.ActionSelected);
	}

	/** Demo list view item click action */
	private class DemoListViewItemClick implements GroupListViewItemClickListener<SampleBase, DemoListCell>
	{
		@Override
		public void onGroupItemClick(SampleBase itemData, DemoListCell itemView, TuSdkIndexPath indexPath)
		{
			onSelectedItem(itemData);
		}
	}

	/** The data source of sample group */
	private class SimpleGroupDataSource implements TuSdkDataSource
	{
		/** Index list */
		private List<TuSdkIndexPath> mIndexPaths;

		/** Sample group */
		private SampleGroup mGroup;

		private int mTotal;

		/**
		 * The constructor
		 * 
		 * @param group
		 *            Sample group
		 */
		public SimpleGroupDataSource(SampleGroup group)
		{
			this.splitDatas(group);
		}

		/** Sample group data */
		private void splitDatas(SampleGroup group)
		{
			if (group == null || group.headers == null) return;
			mGroup = group;

			List<TuSdkIndexPath> indexPaths = new ArrayList<TuSdkIndexPath>();

			mTotal = 0;
			int sectionIndex = 0;
			for (GroupHeader header : group.headers)
			{
				if (header.datas == null) continue;

				indexPaths.add(new TuSdkIndexPath(sectionIndex, -1, 1));
				mTotal += header.datas.size();

				for (int i = 0, j = header.datas.size(); i < j; i++)
				{
					indexPaths.add(new TuSdkIndexPath(sectionIndex, i, 0));
				}

				sectionIndex++;
			}

			this.mIndexPaths = indexPaths;
		}

		/** List index*/
		public List<TuSdkIndexPath> getIndexPaths()
		{
			if (mIndexPaths == null)
			{
				mIndexPaths = new ArrayList<TuSdkIndexPath>(0);
			}
			return mIndexPaths;
		}

		/** Get index path by index */
		public TuSdkIndexPath getIndexPath(int index)
		{
			if (mIndexPaths == null || index >= mIndexPaths.size()) return null;
			return mIndexPaths.get(index);
		}

		/** View types count */
		public int viewTypes()
		{
			return 2;
		}

		/** Sample group count */
		public int sectionCount()
		{
			if (mGroup != null && mGroup.headers != null)
			{
				return mGroup.headers.size();
			}
			return 1;
		}

		/**
		 * Row count
		 * 
		 * @param section
		 *            index
		 * @return
		 */
		public int rowCount(int section)
		{
			if (mGroup == null) return 0;

			GroupHeader header = mGroup.getHeader(section);
			if (header == null || header.datas == null) return 0;
			return header.datas.size();
		}

		/** data source count */
		public int count()
		{
			return mTotal;
		}

		/** Create View */
		public void onViewBinded(TuSdkIndexPath indexPath, View view)
		{
			if (!(view instanceof TuSdkCellViewInterface)) return;

			Object mode = this.getItem(indexPath);

			if (view instanceof DemoListCell)
			{
				((DemoListCell) view).setModel((SampleBase) mode);
			}
			else if (view instanceof DemoListHeader)
			{
				((DemoListHeader) view).setModel((GroupHeader) mode);
			}
		}

		/** Get item  */
		public Object getItem(TuSdkIndexPath indexPath)
		{
			if (indexPath == null || mGroup == null) return 0;

			GroupHeader header = mGroup.headers.get(indexPath.section);

			if (header == null) return null;

			if (indexPath.viewType == 1)
			{
				return header;
			}
			else if (indexPath.viewType == 0 && header.datas != null && header.datas.size() > indexPath.row)
			{
				return header.datas.get(indexPath.row);
			}
			return null;
		}
	}
}