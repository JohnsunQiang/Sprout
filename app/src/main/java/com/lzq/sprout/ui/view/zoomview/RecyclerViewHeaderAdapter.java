package com.lzq.sprout.ui.view.zoomview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public abstract class RecyclerViewHeaderAdapter<V extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<V> {

    public static final int INT_TYPE_HEADER = 0;
    public static final int INT_TYPE_FOOTER = 1;

    private View mEmptyView;
    private final Context mContext;
    private final List<ExtraItem> mHeaders;
    private final List<ExtraItem> mFooters;

    public static class ExtraItem<V extends RecyclerView.ViewHolder> {
        public final int type;
        public final V view;

        public ExtraItem(int type, V view) {
            this.type = type;
            this.view = view;
        }
    }

    public RecyclerViewHeaderAdapter(Context context) {
        this.mContext = context;
        this.mHeaders = new ArrayList<ExtraItem>();
        this.mFooters = new ArrayList<ExtraItem>();
    }

    public Context getmContext() {
        return mContext;
    }

    public void setmEmptyView(View mEmptyView) {
        this.mEmptyView = mEmptyView;
        mEmptyView.setVisibility(getCount() == 0 ? View.VISIBLE : View.GONE);
    }

    public abstract int getCount();

    @Override
    public final int getItemCount() {
        int count = mHeaders.size();
        count += getCount();
        count += mFooters.size();

        if (mEmptyView != null) {
            mEmptyView.setVisibility(getCount() == 0 ? View.VISIBLE : View.GONE);
        }

        return count;
    }

    public ExtraItem addHeaderView(int type, V headerView) {
        ExtraItem item = new ExtraItem(type, headerView);
        addHeaderView(item);
        return item;
    }

    public void addHeaderView(ExtraItem headerView) {
        mHeaders.add(headerView);
        notifyItemInserted(mHeaders.size());
    }

    public void removeHeaderView(int type) {
        List<ExtraItem> indexesToRemove = new ArrayList<ExtraItem>();
        for (int i = 0; i < mHeaders.size(); i++) {
            ExtraItem item = mHeaders.get(i);
            if (item.type == type)
                indexesToRemove.add(item);
        }

        for (ExtraItem item : indexesToRemove) {
            int index = mHeaders.indexOf(item);
            mHeaders.remove(item);
            notifyItemRemoved(index);
        }
    }

    public void removeHeaderView(ExtraItem headerView) {
        int indexToRemove = mHeaders.indexOf(headerView);
        if (indexToRemove >= 0) {
            mHeaders.remove(indexToRemove);
            notifyItemRemoved(indexToRemove);
        }
    }

    public ExtraItem addFooterView(int type, V footerView) {
        ExtraItem item = new ExtraItem(type, footerView);
        addFooterView(item);
        return item;
    }

    public void addFooterView(ExtraItem footerView) {
        mFooters.add(footerView);
        notifyItemInserted(getItemCount());
    }

    public void removeFooterView(int type) {
        List<ExtraItem> indexesToRemove = new ArrayList<ExtraItem>();
        for (int i = 0; i < mFooters.size(); i++) {
            ExtraItem item = mFooters.get(i);
            if (item.type == type) {
                indexesToRemove.add(item);
            }
        }

        for (ExtraItem item : indexesToRemove) {
            int index = mFooters.indexOf(item);
            mFooters.remove(item);
            notifyItemRemoved(mHeaders.size() + getCount() + index);
        }
    }

    public void removeFooterView(ExtraItem footerView) {
        int indexToRemove = mFooters.indexOf(footerView);
        if (indexToRemove >= 0) {
            mFooters.remove(indexToRemove);
            notifyItemRemoved(mHeaders.size() + getCount() + indexToRemove);
        }
    }

    public int getViewType(int position) {
        return super.getItemViewType(position);
    }


    public ExtraItem getHeader(int mIntArgHeaderPos) {

        if (mHeaders != null && mHeaders.size() > mIntArgHeaderPos) {
            return mHeaders.get(mIntArgHeaderPos);
        }
        return null;
    }

    @Override
    public final int getItemViewType(int position) {
        if (position < mHeaders.size()) {
            return INT_TYPE_HEADER;
        } else {
            return INT_TYPE_FOOTER;
        }
    }

    @Override
    public final V onCreateViewHolder(ViewGroup parent, int viewType) {
        for (ExtraItem<V> item : mHeaders) {
            if (viewType == item.type) {
                return item.view;
            }
        }

        for (ExtraItem<V> item : mFooters) {
            if (viewType == item.type) {
                return item.view;
            }
        }

        return onCreateContentView(parent, viewType);
    }

    public abstract V onCreateContentView(ViewGroup parent, int viewType);

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position >= mHeaders.size() && (position - mHeaders.size()) < getCount()) {
            //noinspection unchecked
            onBindView((V) holder, position - mHeaders.size());
        } else {
            try {
                final StaggeredGridLayoutManager.LayoutParams lp =
                        (StaggeredGridLayoutManager.LayoutParams) holder.itemView.getLayoutParams();
                lp.setFullSpan(true);
                holder.itemView.setLayoutParams(lp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public abstract void onBindView(V view, int position);
}
