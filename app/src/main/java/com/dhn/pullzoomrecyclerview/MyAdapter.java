package com.dhn.pullzoomrecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dhn.library.HeaderViewAdapter;

import java.util.List;

/**
 * Created by DHN on 2016/8/26.
 */
public class MyAdapter extends HeaderViewAdapter<ItemViewHolder, String> {
    Context mContext;

    public MyAdapter(List i, Context context) {
        super(i, context);
        mContext = context;
    }

    @Override
    protected void onBindItemViewHolder(ItemViewHolder holder, int position) {
        holder.textView.setText(getItem(position));
    }

    @Override
    protected ItemViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item, parent, false);
        return new ItemViewHolder(view);
    }
}

class ItemViewHolder extends RecyclerView.ViewHolder {
    TextView textView;

    public ItemViewHolder(View itemView) {
        super(itemView);

        textView = (TextView) itemView.findViewById(R.id.tv);
    }
}
