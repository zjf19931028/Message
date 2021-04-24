package com.awesome.common.widget.recycler;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Alice on 2021/4/24
 */
public interface AdapterCallback<Data> {
    void update(Data data, RecyclerAdapter.ViewHolder<Data> holder);

}
