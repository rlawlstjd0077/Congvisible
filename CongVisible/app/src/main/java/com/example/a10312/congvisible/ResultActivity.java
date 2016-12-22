package com.example.a10312.congvisible;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by 이재빈10312 on 2016-12-22.
 */

public class ResultActivity extends MainActivity{
    TextView ResultOf;
    TextView cornum;
    TextView wornum;
    String cor;
    String wor;
    int cor_num;
    int wor_num;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_results);
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
}