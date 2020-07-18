package com.common.project.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

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

public class StudyPlanActivity extends AppCompatActivity {

    @BindView(R.id.act_phone_head)
    CommonHead actPhoneHead;
    @BindView(R.id.bookNameTextView)
    TextView bookNameTextView;
    @BindView(R.id.wordCountTextView)
    TextView wordCountTextView;
    @BindView(R.id.changeBookTextView)
    TextView changeBookTextView;
    @BindView(R.id.modifyCountTextView)
    TextView modifyCountTextView;
    @BindView(R.id.resetTextView)
    TextView resetTextView;
    String bookListJson = "{\"reason\":\"succ\",\"code\":200,\"cates\":[{\"cateName\":\"出国考试\",\"bookList\":[{\"cover\":\"https://nos.netease.com/ydschool-online/youdao_IELTS_2.jpg\",\"bookOrigin\":{\"originUrl\":\"\",\"desc\":\"来源于\",\"originName\":\"有道词典\"},\"size\":1597545,\"wordNum\":3427,\"id\":\"IELTSluan_2\",\"title\":\"雅思词汇\",\"offlinedata\":\"http://ydschool-online.nos.netease.com/1521164624473_IELTSluan_2.zip\",\"cateName\":\"出国考试\",\"version\":\"2\"},{\"cover\":\"https://nos.netease.com/ydschool-online/youdao_TOEFL_2.jpg\",\"bookOrigin\":{\"originUrl\":\"\",\"desc\":\"来源于\",\"originName\":\"有道词典\"},\"size\":4327321,\"wordNum\":9213,\"id\":\"TOEFL_2\",\"title\":\"TOEFL词汇\",\"offlinedata\":\"http://ydschool-online.nos.netease.com/1521164640451_TOEFL_2.zip\",\"cateName\":\"出国考试\",\"version\":\"2\"},{\"cover\":\"https://nos.netease.com/ydschool-online/youdao_GMAT_2.jpg\",\"bookOrigin\":{\"originUrl\":\"\",\"desc\":\"来源于\",\"originName\":\"有道词典\"},\"size\":1509190,\"wordNum\":3254,\"id\":\"GMATluan_2\",\"title\":\"GMAT词汇\",\"offlinedata\":\"http://ydschool-online.nos.netease.com/1521164629611_GMATluan_2.zip\",\"cateName\":\"出国考试\",\"version\":\"2\"}]}]}";
    @BindView(R.id.dayStudyCountTextView)
    TextView dayStudyCountTextView;
    private BookListEntity bookListEntity;
    List<BookListEntity.CatesBean.BookListBean> bookListBeanList = new ArrayList<>();
    private int bookId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_plan);
        ButterKnife.bind(this);
        bookListEntity = new Gson().fromJson(bookListJson, BookListEntity.class);
        bookListBeanList.clear();
        for (BookListEntity.CatesBean catesBean :
                bookListEntity.getCates()) {
            bookListBeanList.addAll(catesBean.getBookList());
        }
        bookId = Integer.valueOf(SharePreferenceUtil.getString(this, Constants.CHOOSE_BOOK));
        bookNameTextView.setText("单词书名称：" + bookListBeanList.get(bookId).getTitle());
        wordCountTextView.setText("单词数量：    " + bookListBeanList.get(bookId).getWordNum() + "个");
        dayStudyCountTextView.setText("每日学习:       "+SharePreferenceUtil.getString(this,Constants.PLAN_STUDY_COUNT)+"个");
    }
}
