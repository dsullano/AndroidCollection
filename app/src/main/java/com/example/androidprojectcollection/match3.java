package com.example.androidprojectcollection;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.graphics.Color;
import android.widget.TextView;

import java.util.Random;

public class match3 extends AppCompatActivity {
    private Button btnSelected = null;
    Button[][] btn = new Button[5][5];
    TextView scoreTeller;
    int score = 0;
    int[] COLOR = {Color.BLUE,Color.YELLOW,Color.GREEN,Color.RED};
    int[][] tiles = new int[5][5];
    Random r = new Random();

    private void randomColor(){
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                int ctr = tiles[i][j];
                int color = COLOR[ctr];
                btn[i][j].setBackgroundColor(color);
            }
        }
    }

    private void randomizeColor(){
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                tiles[i][j] = r.nextInt(4);
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private void btnClickers(int row, int col) {

        if (btnSelected == null) {
            btnSelected = btn[row][col];
            return;
        }

        Button curr = btn[row][col];

        if (isAdjacent(btnSelected, curr)) {
            colorSwap(btnSelected, curr);
            while(checker()){
                score++;
                scoreTeller.setText(getString(R.string.score) + "   " + score);
            }
        }

        btnSelected = null;
    }

    private boolean isAdjacent(Button btn1, Button btn2){
        int row1 = getRowBtn(btn1);
        int col1 = getColBtn(btn1);
        int row2 = getRowBtn(btn2);
        int col2 = getColBtn(btn2);

        return (Math.abs(row1 - row2) == 1 && col1 == col2) || (row1 == row2 && Math.abs(col1 - col2) == 1);
    }

    private int getRowBtn(Button b) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (btn[i][j] == b) {
                    return i;
                }
            }
        }
        return -1;
    }

    private int getColBtn(Button b) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (btn[i][j] == b) {
                    return j;
                }
            }
        }
        return -1;
    }

    private void colorSwap(Button button1, Button button2) {

        int r1 = getRowBtn(button1);
        int c1 = getColBtn(button1);
        int r2 = getRowBtn(button2);
        int c2 = getColBtn(button2);

        int temp = tiles[r1][c1];
        tiles[r1][c1] = tiles[r2][c2];
        tiles[r2][c2] = temp;

        int color1 = COLOR[tiles[r1][c1]];
        int color2 = COLOR[tiles[r2][c2]];
        button1.setBackgroundColor(color1);
        button2.setBackgroundColor(color2);
    }
    private boolean checker(){

        boolean isMatch = false;

        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                if(i > 0 && i < 4){
                    if(tiles[i - 1][j] == tiles[i][j] && tiles[i][j] == tiles[i+1][j]) {
                        int ctr1 = 1;
                        int ctr2 = 1;
                        if((i == 2)){
                            if(tiles[i-2][j] == tiles[i][j]){
                                ctr1++;
                            }
                            if(tiles[i][j] == tiles[i+2][j]){
                                ctr2++;
                            }
                        }
                        for(int k = i-ctr1; k <= i + ctr2; k++){
                            tiles[k][j] = r.nextInt(4);
                            int tValue = tiles[k][j];
                            int color = COLOR[tValue];
                            btn[k][j].setBackgroundColor(color);
                        }
                        isMatch = true;
                    }
                }
                if(j > 0 && j < 4){
                    if(tiles[i][j - 1] == tiles[i][j] && tiles[i][j] == tiles[i][j+1]){
                        int ctr1 = 1;
                        int ctr2 = 1;
                        if((j == 2)){
                            if(tiles[i][j-2] == tiles[i][j]){
                                ctr1++;
                            }
                            if(tiles[i][j] == tiles[i][j+2]){
                                ctr2++;
                            }
                        }
                        for(int k = j-ctr1; k <= j + ctr2; k++){
                            tiles[i][k] = r.nextInt(4);

                            int tValue = tiles[i][k];
                            int color = COLOR[tValue];
                            btn[i][k].setBackgroundColor(color);
                        }
                        isMatch = true;
                    }
                }
            }
        }
        return isMatch;
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match3);

        scoreTeller = findViewById(R.id.scoreTeller);
        scoreTeller.setText(getString(R.string.score) + "   " + score);

        for(int row = 0; row < 5; row++){
            for(int col = 0; col < 5; col++){
                String res = "btn" + row + col;
                int resId = getResources().getIdentifier(res, "id", this.getPackageName());
                btn[row][col] = findViewById(resId);
            }
        }

        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                final int x = i;
                final int y = j;

                System.out.println(i + "   " + j);
                btn[i][j].setOnClickListener(view -> btnClickers(x, y));

            }
        }

        Button restart = findViewById(R.id.btn45);
        restart.setOnClickListener(view -> {
            do {
                randomizeColor();
                randomColor();
            } while (checker());
            score = 0;
            scoreTeller.setText(getString(R.string.score) + "   " + score);
            btnSelected = null;
        });

        do {
            randomizeColor();
            randomColor();
        } while (checker());
    }

}