package com.common.project.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.common.project.CommonHead;
import com.common.project.Constants;
import com.common.project.MyApplication;
import com.common.project.R;
import com.common.project.entity.NewWordEntity;
import com.common.project.entity.WordEntity;
import com.common.project.util.InitWordUtil;
import com.common.project.util.ToastUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.common.project.Constants.SOUND_URL;

public class WordDetailActivity extends AppCompatActivity {

    @BindView(R.id.act_phone_head)
    CommonHead actPhoneHead;
    @BindView(R.id.wordLayout)
    RelativeLayout wordLayout;
    @BindView(R.id.putNewWordBookTextView)
    TextView putNewWordBookTextView;
    @BindView(R.id.goOnTextView)
    TextView goOnTextView;
    @BindView(R.id.newWordTextView)
    TextView newWordTextView;
    @BindView(R.id.voiceWordTextView)
    ImageView voiceWordTextView;
    @BindView(R.id.fanyiTextView)
    TextView fanyiTextView;
    @BindView(R.id.egTextView)
    TextView egTextView;
    int startRememberWordPosition;
    WordEntity wordEntity;
    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_detail);
        ButterKnife.bind(this);
        actPhoneHead.setLeftClick(new CommonHead.CommonHeadLeftClick() {
            @Override
            public void LeftClick() {
                finish();
            }
        });
        goOnTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        if (getIntent() != null) {
            startRememberWordPosition = getIntent().getIntExtra("startRememberWordPosition", 0);
            type = getIntent().getStringExtra("type");
            if (!TextUtils.isEmpty(type) && type.equals("word_book")) {
                putNewWordBookTextView.setVisibility(View.GONE);
                goOnTextView.setVisibility(View.GONE);
            }
        }
        wordEntity = Constants.wordEntityList.get(startRememberWordPosition);
        newWordTextView.setText(wordEntity.getHeadWord());
        fanyiTextView.setText(wordEntity.getContent().getWord().getContent().getTrans().get(0).getTranCn());
        egTextView.setText(getEgText());
        putNewWordBookTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NewWordEntity newWordEntity = new NewWordEntity();
                newWordEntity.setIndex(startRememberWordPosition);
                newWordEntity.setWordHead(wordEntity.getHeadWord());
                newWordEntity.setWordTrans(wordEntity.getContent().getWord().getContent().getTrans().get(0).getTranCn());
                if (isExist(startRememberWordPosition)) {
                    ToastUtil.showToast(WordDetailActivity.this, "生词本中已经包含了哦！");
                    return;
                }
                MyApplication.getInstance().getSession().getNewWordEntityDao().insert(newWordEntity);
                ToastUtil.showToast(WordDetailActivity.this, "单词已放入生词本中！");
            }
        });


        voiceWordTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uri = SOUND_URL + wordEntity.getHeadWord();
                InitWordUtil.playOnlineSound(uri);
            }
        });

    }

    private boolean isExist(int startRememberWordPosition) {
        List<NewWordEntity> newWordEntityList = MyApplication.getInstance().getSession().getNewWordEntityDao().queryBuilder().list();
        for (NewWordEntity wordEntity : newWordEntityList) {
            if (startRememberWordPosition == wordEntity.getIndex()) {
                return true;
            }
        }
        return false;
    }

    private String getEgText() {
        String eg = "";
        WordEntity.ContentBeanX.WordBean.ContentBean.SentenceBean sentence = wordEntity.getContent().getWord().getContent().getSentence();
        if (sentence == null) {
            return eg;
        }
        for (WordEntity.ContentBeanX.WordBean.ContentBean.SentenceBean.SentencesBean bean : sentence.getSentences()) {
            eg = eg + bean.getSContent() + "\n";
            eg = eg + bean.getSCn() + "\n";
        }
        return eg;
    }
}
