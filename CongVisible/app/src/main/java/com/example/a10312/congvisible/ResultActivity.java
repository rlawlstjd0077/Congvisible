package com.example.a10312.congvisible;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by 이재빈10312 on 2016-12-22.
 */

public class ResultActivity extends MainActivity{
    TextView ResultOf;
    TextView cornum;
    TextView wornum;
    Button regame_btn;
    String cor;
    String wor;
    int cor_num;

    @Override
    protected void onPause() {
        super.onPause();
    }

    int wor_num;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_results);
        cornum = (TextView)findViewById(R.id.r_cornum);
        wornum = (TextView)findViewById(R.id.r_wornum);
        ResultOf = (TextView)findViewById(R.id.Result);
        regame_btn = (Button)findViewById(R.id.regame_btn);
        regame_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Intent intent = new Intent(this.getIntent());
        cor_num = intent.getIntExtra("cor_num", 0);
        wor_num = intent.getIntExtra("wor_num", 0);

        cor = String.valueOf(cor_num);
        wor = String.valueOf(wor_num);
        Round_cnt = 1;
        cornum.setText(cor);
        wornum.setText(wor);
        if(cor_num>wor_num) {
            ResultOf.setText("WIN");
        }
        if(cor_num<wor_num) {
            ResultOf.setText("LOSE");
        }
        if(cor_num==wor_num) {
            ResultOf.setText("DRAW");
        }
    }
