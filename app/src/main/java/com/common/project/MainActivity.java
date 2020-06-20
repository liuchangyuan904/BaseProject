package com.common.project;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends Activity implements View.OnClickListener, View.OnLongClickListener {
    private static final String TAG = "MainActivity";
    // 最外层布局
    LinearLayout textviews;
    LinearLayout buttons;

    int[][] map = new int[10][10];

    // 用来隐藏所有Button
    List<Button> buttonList = new ArrayList<Button>();

    private Button resetBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textviews = (LinearLayout) findViewById(R.id.textviews);
        buttons = (LinearLayout) findViewById(R.id.buttons);
        resetBtn=findViewById(R.id.resetBtn);
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initData();
                initView();
                falgs.clear();
            }
        });
        initData();
        initView();
    }

    Set<Integer> randomDiLei;

    private void initData() {
        // 10个地雷 显示* 数组中是-1
        // 90个 雷的边上是数字，其他是空白 0 1-8
        // 100个数字 从里面随机取走10个
        // 初始化
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                map[i][j] = 0;
            }
        }
        // 抽取100个数 99
        randomDiLei = getRandom();
        // 丢入map
        for (Integer integer : randomDiLei) {
            int hang = integer / 10;// 98
            int lie = integer % 10;
            // 所有的地雷用-1代替
            map[hang][lie] = -1;
        }
        // 为所有的空白地点去设置数值
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (map[i][j] == -1)
                    continue; // 继续下次循环
                int sum = 0;
                // 左上角
                if (i != 0 && j != 0) {// 防止下标越界
                    if (map[i - 1][j - 1] == -1)
                        sum++;
                }
                // 上面
                if (j != 0) {
                    if (map[i][j - 1] == -1)
                        sum++;
                }
                // 右上角
                if (j != 0 && i != 9) {
                    if (map[i + 1][j - 1] == -1)
                        sum++;
                }
                // 左边
                if (i != 0) {
                    if (map[i - 1][j] == -1)
                        sum++;
                }
                // 右边
                if (i != 9) {
                    if (map[i + 1][j] == -1)
                        sum++;
                }
                // 左下角
                if (j != 9 && i != 0) {
                    if (map[i - 1][j + 1] == -1)
                        sum++;
                }
                if (j != 9) {
                    if (map[i][j + 1] == -1)
                        sum++;
                }
                if (j != 9 && i != 9) {
                    if (map[i + 1][j + 1] == -1)
                        sum++;
                }
                map[i][j] = sum;
            }
        }

        // 打印看看
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Log.d(TAG, "initData: " + map[i][j] + " ");
            }
        }
    }

    private Set<Integer> getRandom() {
        // 没有重复的
        Set<Integer> set = new HashSet<Integer>();
        while (set.size() != 10) {
            int random = (int) (Math.random() * 100);
            set.add(random);
        }
        return set;
    }

    // 创建视图
    private void initView() {
        buttonList.clear();
        textviews.removeAllViews();
        buttons.removeAllViews();
        int width = getResources().getDisplayMetrics().widthPixels / 10;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width,
                width);
        for (int i = 0; i < 10; i++) {
            // 每一条的布局
            LinearLayout tvs = new LinearLayout(this);
            tvs.setOrientation(LinearLayout.HORIZONTAL);
            LinearLayout btns = new LinearLayout(this);
            btns.setOrientation(LinearLayout.HORIZONTAL);
            // 添加内层的100个按钮和文本
            for (int j = 0; j < 10; j++) {
                // 底层的TextView
                TextView tv = new TextView(this);
                tv.setBackgroundResource(R.drawable.textview_bg);
                tv.setLayoutParams(params);
                tv.setGravity(Gravity.CENTER);
                if (map[i][j] == -1)
                    tv.setText("*");
                else if (map[i][j] != 0)
                    tv.setText(map[i][j] + "");
                tvs.addView(tv);
                // 底层的Button
                Button btn = new Button(this);
                btn.setBackgroundResource(R.drawable.ic_launcher_background);
                btn.setLayoutParams(params);
                btn.setTag(i * 10 + j);
                btn.setOnClickListener(this);
                btn.setOnLongClickListener(this);
                buttonList.add(btn);
                btns.addView(btn);
            }
            textviews.addView(tvs);
            buttons.addView(btns);
        }
    }

    @Override
    public void onClick(View v) {
        int id = (Integer) v.getTag();
        int hang = id / 10;
        int lie = id % 10;
        // 隐藏按钮，显示底下的数据
        v.setVisibility(View.INVISIBLE);
        isOver(hang, lie);
        // 判断点击的是否是一个数字
        if (map[hang][lie] == 0) {
            // 开始递归
            showAroundSpaceWhite(hang, lie);
        }
        if (isWin()) {
            Toast.makeText(this, "游戏胜利", Toast.LENGTH_SHORT).show();
        }
    }

    // 显示周围所有的button
    public void showAroundSpaceWhite(int i, int j) {
        // 检测周围的元素，如果为0 继续调用自身,并且显示
        // 不是，就显示button
        // 从左上角开始
        // 左上角
        // 先显示自己
        buttonList.get(i * 10 + j).setVisibility(View.INVISIBLE);
        if (falgs.contains(buttonList.get(i * 10 + j).getTag())) {
            // 如果使用drawableTop 对应的java代码的方法
            // setCompoundDrawablesWithIntrinsicBounds
            // int ArrayList.remove(int);//下标
            falgs.remove((Integer) buttonList.get(i * 10 + j).getTag());
        }
        if (i != 0 && j != 0) {// 防止下标越界
            if (map[i - 1][j - 1] == 0) {
                if (isNeedLoop(i - 1, j - 1))
                    showAroundSpaceWhite(i - 1, j - 1);
            } else {
                hidebutton(i - 1, j - 1);
            }
        }
        // 上面
        if (j != 0) {
            if (map[i][j - 1] == 0) {
                if (isNeedLoop(i, j - 1))
                    showAroundSpaceWhite(i, j - 1);
            } else {
                hidebutton(i, j - 1);
            }
        }
        // 右上角
        if (j != 0 && i != 9) {
            if (map[i + 1][j - 1] == 0) {
                if (isNeedLoop(i + 1, j - 1))
                    showAroundSpaceWhite(i + 1, j - 1);
            } else {
                hidebutton(i + 1, j - 1);
            }
        }
        // 左边
        if (i != 0) {
            if (map[i - 1][j] == 0) {
                if (isNeedLoop(i - 1, j))
                    showAroundSpaceWhite(i - 1, j);
            } else {
                hidebutton(i - 1, j);
            }
        }
        // 右边
        if (i != 9) {
            if (map[i + 1][j] == 0) {
                if (isNeedLoop(i + 1, j))
                    showAroundSpaceWhite(i + 1, j);
            } else {
                hidebutton(i + 1, j);
            }
        }
        // 左下角
        if (j != 9 && i != 0) {
            if (map[i - 1][j + 1] == 0) {
                if (isNeedLoop(i - 1, j + 1))
                    showAroundSpaceWhite(i - 1, j + 1);
            } else {
                hidebutton(i - 1, j + 1);
            }
        }
        if (j != 9) {
            if (map[i][j + 1] == 0) {
                if (isNeedLoop(i, j + 1))
                    showAroundSpaceWhite(i, j + 1);
            } else {
                hidebutton(i, j + 1);
            }
        }
        if (j != 9 && i != 9) {
            if (map[i + 1][j + 1] == 0) {
                if (isNeedLoop(i + 1, j + 1))
                    showAroundSpaceWhite(i + 1, j + 1);
            } else {
                hidebutton(i + 1, j + 1);
            }
        }

    }

    private void hidebutton(int i, int j) {
        int position = i * 10 + j;
        buttonList.get(position).setVisibility(View.INVISIBLE);
        int tag = (Integer) buttonList.get(position).getTag();
        if (falgs.contains(tag)) {
            // 如果使用drawableTop 对应的java代码的方法
            // setCompoundDrawablesWithIntrinsicBounds
            // int ArrayList.remove(int);//下标
            falgs.remove((Integer) tag);
        }
    }

    boolean isNeedLoop(int hang, int lie) {
        // 判断是否是现实的
        int position = hang * 10 + lie;
        if (buttonList.get(position).getVisibility() == View.INVISIBLE)
            return false;
        else
            return true;
    }

    private boolean isOver(int hang, int lie) {
        // OVER
        if (map[hang][lie] == -1) {
            Toast.makeText(this, "GameOver", Toast.LENGTH_SHORT).show();
            for (int i = 0; i < buttonList.size(); i++) {
                buttonList.get(i).setVisibility(View.INVISIBLE);
            }
            return true;
        }
        return false;
    }

    // 最多10个旗子
    List<Integer> falgs = new ArrayList<Integer>();

    @Override
    public boolean onLongClick(View v) {
        // 插旗子
        // 1. 有了旗子 就取消
        // 2. 没有就插旗
        Button btn = (Button) v;
        int tag = (Integer) v.getTag();
        if (falgs.contains(tag)) {
            // 如果使用drawableTop 对应的java代码的方法
            // setCompoundDrawablesWithIntrinsicBounds
            btn.setText("");
            // int ArrayList.remove(int);//下标
            falgs.remove((Integer) tag);
        } else {
            // 没有插旗就需要插旗，判断数量是否超过了上限
            if (falgs.size() != 10) {
                falgs.add(tag);
                btn.setText("∉");
                btn.setTextColor(Color.RED);
            } else {
                Toast.makeText(this, "没有旗子了", Toast.LENGTH_SHORT).show();
            }

        }
        // 消耗事件，
        return true;
    }

    // 是否胜利
    public boolean isWin() {
        int sum = 0;
        for (int i = 0; i < buttonList.size(); i++) {
            if (buttonList.get(i).getVisibility() == View.INVISIBLE)
                sum++;
        }
        if (sum == 90)
            return true;
        return false;
    }
}


