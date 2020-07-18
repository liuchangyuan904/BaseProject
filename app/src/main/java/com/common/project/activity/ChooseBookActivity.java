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
import com.common.project.Constants;
import com.common.project.R;
import com.common.project.entity.BookListEntity;
import com.common.project.util.SharePreferenceUtil;
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
    List<BookListEntity.CatesBean.BookListBean> bookListBeanList = new ArrayList<>();
    BookListAdapter bookListAdapter;
    String bookListJson = "{\"reason\":\"succ\",\"code\":200,\"cates\":[{\"cateName\":\"出国考试\",\"bookList\":[{\"cover\":\"https://nos.netease.com/ydschool-online/youdao_IELTS_2.jpg\",\"bookOrigin\":{\"originUrl\":\"\",\"desc\":\"来源于\",\"originName\":\"有道词典\"},\"size\":1597545,\"wordNum\":3427,\"id\":\"IELTSluan_2\",\"title\":\"雅思词汇\",\"offlinedata\":\"http://ydschool-online.nos.netease.com/1521164624473_IELTSluan_2.zip\",\"cateName\":\"出国考试\",\"version\":\"2\"},{\"cover\":\"https://nos.netease.com/ydschool-online/youdao_TOEFL_2.jpg\",\"bookOrigin\":{\"originUrl\":\"\",\"desc\":\"来源于\",\"originName\":\"有道词典\"},\"size\":4327321,\"wordNum\":9213,\"id\":\"TOEFL_2\",\"title\":\"TOEFL词汇\",\"offlinedata\":\"http://ydschool-online.nos.netease.com/1521164640451_TOEFL_2.zip\",\"cateName\":\"出国考试\",\"version\":\"2\"},{\"cover\":\"https://nos.netease.com/ydschool-online/youdao_GMAT_2.jpg\",\"bookOrigin\":{\"originUrl\":\"\",\"desc\":\"来源于\",\"originName\":\"有道词典\"},\"size\":1509190,\"wordNum\":3254,\"id\":\"GMATluan_2\",\"title\":\"GMAT词汇\",\"offlinedata\":\"http://ydschool-online.nos.netease.com/1521164629611_GMATluan_2.zip\",\"cateName\":\"出国考试\",\"version\":\"2\"}]}]}";

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
        bookListEntity = new Gson().fromJson(bookListJson, BookListEntity.class);
        bookListBeanList.clear();
        for (BookListEntity.CatesBean catesBean :
                bookListEntity.getCates()) {
            bookListBeanList.addAll(catesBean.getBookList());
        }
        bookListAdapter = new BookListAdapter();
        listView.setAdapter(bookListAdapter);
    }

    class BookListAdapter extends BaseAdapter {

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
        public View getView(final int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            if (view == null) {
                view = LayoutInflater.from(ChooseBookActivity.this).inflate(R.layout.list_choose_book_item, null, false);
                viewHolder = new ViewHolder();
                viewHolder.bookImg = view.findViewById(R.id.bookImg);
                viewHolder.bookNameTextView = view.findViewById(R.id.bookNameTextView);
                viewHolder.wordCountTextView = view.findViewById(R.id.wordCountTextView);
                viewHolder.layoutItem = view.findViewById(R.id.layoutItem);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }

            Glide.with(ChooseBookActivity.this).load(bookListBeanList.get(i).getCover()).into(viewHolder.bookImg);
            viewHolder.bookNameTextView.setText(bookListBeanList.get(i).getTitle());
            viewHolder.wordCountTextView.setText(bookListBeanList.get(i).getWordNum() + "个");
            viewHolder.layoutItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SharePreferenceUtil.saveString(ChooseBookActivity.this,Constants.CHOOSE_BOOK,""+i);
                    startActivity(new Intent(ChooseBookActivity.this, SetStudyCountPlanActivity.class));
                    finish();

                }
            });
            return view;
        }

        class ViewHolder {
            ImageView bookImg;
            TextView bookNameTextView;
            TextView wordCountTextView;
            LinearLayout layoutItem;
        }
    }
}
