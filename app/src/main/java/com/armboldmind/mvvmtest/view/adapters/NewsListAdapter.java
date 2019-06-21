package com.armboldmind.mvvmtest.view.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.armboldmind.mvvmtest.R;
import com.armboldmind.mvvmtest.databinding.AdapterNewsListItemBinding;
import com.armboldmind.mvvmtest.model.newsModels.NewsListModel;

import java.util.List;

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.ViewHolder> {

    private final List<NewsListModel> mList;
    private final NewsListAdapter.OnItemClickListener mListener;

    public NewsListAdapter(List<NewsListModel> list, NewsListAdapter.OnItemClickListener listener) {
        mList = list;
        mListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final AdapterNewsListItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.adapter_news_list_item, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.setNews(mList.get(position));
        holder.binding.setClickListener(mListener);
    }

    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : 0;
    }

    public interface OnItemClickListener {
        void onClick(final long id);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final AdapterNewsListItemBinding binding;

        ViewHolder(@NonNull AdapterNewsListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}