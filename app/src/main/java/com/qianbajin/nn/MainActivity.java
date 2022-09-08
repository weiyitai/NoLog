package com.qianbajin.nn;

import android.app.ListActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends ListActivity {
    public static final String CHECKLIST = "check";
    private List<ItemBean> mList;
    private SharedPreferences mSp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSp = PreferenceManager.getDefaultSharedPreferences(this);
        Set<String> set = mSp.getStringSet(CHECKLIST, new HashSet<>());
        mList = Arrays.asList(new ItemBean("fsdfsf", true), new ItemBean("13545", false));
        ItemAdapter adapter = new ItemAdapter(mList);
        setListAdapter(adapter);
        getListView().setOnItemClickListener((parent, view, position, id) -> {
            ItemBean item = adapter.getItem(position);
            item.setCheck(!item.isCheck());
            adapter.notifyDataSetChanged();
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.pop_save, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_save) {
            saveData();
            Toast.makeText(this, R.string.save_success, Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    private List<String> saveData() {
        List<String> checkPkg = new ArrayList<>();
        if (mList != null) {
            for (ItemBean itemBean : mList) {
                if (itemBean.isCheck()) {
                    checkPkg.add(itemBean.getPkgName());
                }
            }
        }
        return checkPkg;
    }
}
