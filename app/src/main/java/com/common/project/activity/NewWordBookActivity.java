package com.common.project.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.common.project.CommonHead;
import com.common.project.MyApplication;
import com.common.project.R;
import com.common.project.entity.NewWordEntity;
import com.common.project.view.SwipeItemLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewWordBookActivity extends AppCompatActivity {

    @BindView(R.id.act_phone_head)
    CommonHead actPhoneHead;
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    List<NewWordEntity> list=new ArrayList<>();
    MyAdapter myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_word_book);
        ButterKnife.bind(this);
        actPhoneHead.setLeftClick(new CommonHead.CommonHeadLeftClick() {
            @Override
            public void LeftClick() {
                finish();
            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addOnItemTouchListener(new SwipeItemLayout.OnSwipeItemTouchListener(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setColorSchemeColors(Color.RED);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 2000);
            }
        });
        List<NewWordEntity> list = MyApplication.getInstance().getSession().getNewWordEntityDao().queryBuilder().list();
        this.list.addAll(list);
        myAdapter=new MyAdapter(this);
        recyclerView.setAdapter(myAdapter);
    }
    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.Holder> {
        private Context mContext;

        public MyAdapter(Context context) {
            mContext = context;

        }

        @Override
        public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            View root = LayoutInflater.from(mContext).inflate(R.layout.item_recycler, parent, false);
            return new Holder(root);
        }

        @Override
        public void onBindViewHolder(Holder holder, int position) {
            NewWordEntity wordEntity=list.get(position);
            holder.wordHeadTextView.setText(wordEntity.getWordHead());
            holder.transTextView.setText(wordEntity.getWordTrans());
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class Holder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
            private TextView wordHeadTextView;
            private TextView transTextView;

            Holder(View itemView) {
                super(itemView);
                wordHeadTextView = (TextView) itemView.findViewById(R.id.wordHeadTextView);
                transTextView = (TextView) itemView.findViewById(R.id.transTextView);
                View main = itemView.findViewById(R.id.main);
                main.setOnClickListener(this);
                main.setOnLongClickListener(this);

                View delete = itemView.findViewById(R.id.delete);
                delete.setOnClickListener(this);

                View mark=itemView.findViewById(R.id.mark);
                mark.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.main:
                        Intent intent = new Intent(NewWordBookActivity.this, WordDetailActivity.class);
                        intent.putExtra("startRememberWordPosition", list.get(getAdapterPosition()).getIndex());
                        intent.putExtra("type","word_book");
                        startActivity(intent);
                        break;
                    case R.id.delete:
                        int pos = getAdapterPosition();
                        MyApplication.getInstance().getSession().getNewWordEntityDao().delete(list.get(pos));
                        list.remove(pos);
                        notifyDataSetChanged();
                        break;
                    case R.id.mark:
                        break;
                }
            }

            @Override
            public boolean onLongClick(View v) {
                switch (v.getId()) {
                    case R.id.main:

                        break;
                }
                return false;
            }
        }
    }

}
