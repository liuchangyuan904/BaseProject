package com.common.project.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.common.project.CommonHead;
import com.common.project.R;
import com.common.project.entity.BookListEntity;
import com.common.project.http.HttpUtil;
import com.common.project.listener.HttpResponseListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChooseBookActivity extends AppCompatActivity {

    @BindView(R.id.act_phone_head)
    CommonHead actPhoneHead;
    @BindView(R.id.listView)
    ListView listView;
    BookListEntity bookListEntity;
    List<BookListEntity.CatesBean.BookListBean> bookListBeanList=new ArrayList<>();
    BookListAdapter bookListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_book);
        ButterKnife.bind(this);
        actPhoneHead.setLeftClick(new CommonHead.CommonHeadLeftClick() {
            @Override
            public void LeftClick() {
                finish();
            }
        });
        getBookList();
    }

    private void getBookList() {
        HttpUtil.getBookList(this, new HttpResponseListener() {
            @Override
            public void onSuccess(String json) {
                bookListEntity=new Gson().fromJson(json,BookListEntity.class);
                bookListBeanList.clear();
                for (BookListEntity.CatesBean catesBean :
                        bookListEntity.getCates()) {
                    bookListBeanList.addAll(catesBean.getBookList());
                }
                bookListAdapter=new BookListAdapter();
                listView.setAdapter(bookListAdapter);
            }

            @Override
            public void onFailed(String json) {

            }
        });
    }
    class BookListAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return bookListBeanList.size();
        }

        @Override
        public Object getItem(int i) {
            return bookListBeanList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            if (view==null){
                view=LayoutInflater.from(ChooseBookActivity.this).inflate(R.layout.list_choose_book_item,null,false);
                viewHolder=new ViewHolder();
                viewHolder.bookImg=view.findViewById(R.id.bookImg);
                viewHolder.bookNameTextView=view.findViewById(R.id.bookNameTextView);
                viewHolder.wordCountTextView=view.findViewById(R.id.wordCountTextView);
                viewHolder.layoutItem=view.findViewById(R.id.layoutItem);
                view.setTag(viewHolder);
            }else {
                viewHolder= (ViewHolder) view.getTag();
            }
            Glide.with(ChooseBookActivity.this).load(bookListBeanList.get(i).getCover()).into(viewHolder.bookImg);
            viewHolder.bookNameTextView.setText(bookListBeanList.get(i).getTitle());
            viewHolder.wordCountTextView.setText(bookListBeanList.get(i).getWordNum()+"个");
            viewHolder.layoutItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(ChooseBookActivity.this,MainActivity.class));
                }
            });
            return view;
        }
        class ViewHolder{
            ImageView bookImg;
            TextView bookNameTextView;
            TextView wordCountTextView;
            LinearLayout layoutItem;
        }
    }
}
