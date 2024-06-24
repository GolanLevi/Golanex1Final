package com.example.golans_ex1;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textview.MaterialTextView;

public class EndGameActivity extends AppCompatActivity {

    public MaterialTextView end_game_TEXT;
    public static final String KEY_STATUS = "KEY_STATUS";
    public static final String KEY_SCORE = "KEY_SCORE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);
        findView();
        initView();
    }


    private void findView() {
        end_game_TEXT =findViewById(R.id.end_game_TEXT);
    }

    private void initView() {
        Intent previousIntent = getIntent();
        int score = previousIntent.getIntExtra(KEY_SCORE,0);
        String status = previousIntent.getStringExtra(KEY_STATUS);
        end_game_TEXT.setText(status);

    }
}