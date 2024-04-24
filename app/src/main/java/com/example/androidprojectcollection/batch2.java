package com.example.androidprojectcollection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class batch2 extends AppCompatActivity implements View.OnClickListener {

    public int player_turn = 0;
    TextView turn_indicator;

    LinearLayout bg;
    Button btn5, btn6, btn7, btn9, btn10, btn11, btn12, btn13, btn14, btnRes, midbtn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_batch2);

        turn_indicator = findViewById(R.id.turn_indicator);
        bg = findViewById(R.id.bg);

        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn9 = findViewById(R.id.btn9);
        btn10 = findViewById(R.id.btnA);
        btn11 = findViewById(R.id.btnB);
        btn12 = findViewById(R.id.btnC);
        btn13 = findViewById(R.id.btnD);
        btn14 = findViewById(R.id.btnE);
        btnRes = findViewById(R.id.btnRes);
        midbtn = findViewById(R.id.midbtn);

        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btn10.setOnClickListener(this);
        btn11.setOnClickListener(this);
        btn12.setOnClickListener(this);
        btn13.setOnClickListener(this);
        btn14.setOnClickListener(this);
        btnRes.setOnClickListener(view -> {
            btn5.setText("");
            btn6.setText("");
            btn7.setText("");
            btn9.setText("");
            btn10.setText("");
            btn11.setText("");
            btn12.setText("");
            btn13.setText("");
            btn14.setText("");

        });

    }

    @Override
    public void onClick(View v) {
        Button b = (Button) v;

        if (!b.getText().equals("")) {
            return;
        }
//        if(!b.getText().equals("Restart")){
//            btn5.setText("");
//            btn6.setText("");
//            btn7.setText("");
//            btn9.setText("");
//            btn10.setText("");
//            btn11.setText("");
//            btn12.setText("");
//            btn13.setText("");
//            btn14.setText("");
//
//
////            for(int i = 0; i < 3; i++){
////                for(int j = 0; j < 3; j++){
////                    int  col = j;
////                    int  row = i;
////                    btn[row][col].setText("");
////                    playerTurn = true;
////                    textMessage = "Player 0's turn";
////                    background.setBackgroundColor(COLOR[1]);
////                }
////            }
//        }

        if (player_turn == 0) {
            b.setText("O");
            turn_indicator.setText("Player X's turn");

            int color = ContextCompat.getColor(this, R.color.playerO);
            bg.setBackgroundColor(color);
        } else {
            b.setText("X");
            turn_indicator.setText("Player O's turn");

            int color = ContextCompat.getColor(this, R.color.playerX);
            bg.setBackgroundColor(color);
        }

        if (btn5.getText() == btn6.getText() && btn5.getText() == btn7.getText() && !btn5.getText().equals("")) {
            Toast.makeText(getApplicationContext(), "You wiN!", Toast.LENGTH_SHORT).show();
        }

        if (btn9.getText() == btn10.getText() && btn9.getText() == btn11.getText()&& !btn9.getText().equals("")) {
            Toast.makeText(getApplicationContext(), "You wiN!", Toast.LENGTH_SHORT).show();
        }

        if (btn12.getText() == btn13.getText() && btn12.getText() == btn14.getText()&& !btn12.getText().equals("")) {
            Toast.makeText(getApplicationContext(), "You wiN!", Toast.LENGTH_SHORT).show();
        }

        if (btn5.getText() == btn9.getText() && btn5.getText() == btn12.getText() && !btn5.getText().equals("")) {
            Toast.makeText(getApplicationContext(), "You wiN!", Toast.LENGTH_SHORT).show();
        }

        if (btn6.getText() == btn10.getText() && btn6.getText() == btn13.getText()&& !btn6.getText().equals("")) {
            Toast.makeText(getApplicationContext(), "You wiN!", Toast.LENGTH_SHORT).show();
        }

        if (btn7.getText() == btn11.getText() && btn7.getText() == btn14.getText()&& !btn7.getText().equals("")) {
            Toast.makeText(getApplicationContext(), "You wiN!", Toast.LENGTH_SHORT).show();
        }

        if (btn5.getText() == btn10.getText() && btn5.getText() == btn14.getText() && !btn5.getText().equals("")) {
            Toast.makeText(getApplicationContext(), "You wiN!", Toast.LENGTH_SHORT).show();
        }

        if (btn7.getText() == btn10.getText() && btn7.getText() == btn12.getText()&& !btn7.getText().equals("")) {
            Toast.makeText(getApplicationContext(), "You wiN!", Toast.LENGTH_SHORT).show();
        }


        nextPlayer();

    }

    private void nextPlayer() {
        player_turn += 1;
        player_turn %= 2;
    }


//    public void checkWin(Button b) {
//        String given = "";
//        for(int i=0; i<3; i++){
//            given = (String) buttons[i][0].getText();
//            for(int j=0; j<3; j++){
//                if(given.equals(b.getText())){
//
//                }
//            }
//        }
//    }
}