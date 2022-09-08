package com.qianbajin.nn;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;

import java.util.List;

/**
 * @author qianbajin weiyitai
 * @date 2022/8/20  22:21
 */
public class ItemAdapter extends BaseAdapter {

    private List<ItemBean> mList;
    private LayoutInflater mInflater;

    public ItemAdapter(List<ItemBean> list) {
        mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public ItemBean getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            if (mInflater == null) {
                mInflater = LayoutInflater.from(parent.getContext());
            }
            view = mInflater.inflate(android.R.layout.simple_list_item_single_choice, null);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 150));
        } else {
            view = convertView;
        }
        ItemBean item = getItem(position);
        ((CheckedTextView) view).setText(item.getPkgName());
        ((CheckedTextView) view).setChecked(item.isCheck());
        return view;
    }
}
