package com.hyugnmin.android.memowithnodejs;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.hyugnmin.android.memowithnodejs.domain.Data;
import com.hyugnmin.android.memowithnodejs.domain.Qna;

import java.util.List;

public class ListActivity extends AppCompatActivity {
    List<Qna> datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListActivity.this, WriteActivity.class);
                startActivity(intent);
            }
        });

        setList();
    }

    @Override
 protected void onResume() {
               super.onResume();
               adapter.notifyDataSetChanged();
    }

    ListView listView;
    CustomAdapter adapter;
    private  void setList() {
        listView = (ListView)findViewById(R.id.listview);
        DataStore dataStore = DataStore.getInstance();
        datas = dataStore.getDatas();
        adapter = new CustomAdapter(this, datas);
        listView.setAdapter(adapter);
    }

}

    class CustomAdapter extends BaseAdapter {
        List<Qna> datas;
        Context context;
        LayoutInflater inflater;

        public CustomAdapter(Context context , List<Qna> datas) {
            this.context = context;
            this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            this.datas = datas;
        }

        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public Object getItem(int position) {
            return datas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null) {
                convertView = inflater.inflate(R.layout.item_list, null);
            }

            Qna qna = datas.get(position);
            TextView textTitle = (TextView) convertView.findViewById(R.id.textTitle);
            TextView textName = (TextView) convertView.findViewById(R.id.textName);

            textTitle.setText(qna.getTitle());
            textName.setText(qna.getName());

            return convertView;
        }
    }