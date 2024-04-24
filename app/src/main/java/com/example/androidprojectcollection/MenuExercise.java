package com.example.androidprojectcollection;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Random;

public class MenuExercise extends AppCompatActivity {
    Button btnChanger;
    LinearLayout bg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menus);

        bg = findViewById(R.id.bg);

        btnChanger = findViewById((R.id.btnTransformingButton));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_exercise,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        if(item.getItemId() == R.id.mItemChange){
            Toast.makeText(this, "Edit Object Item is clicked", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.mItemReset){
            Toast.makeText(this, "Reset Object Item is clicked", Toast.LENGTH_SHORT).show();
            resetButton();
        } else if (item.getItemId() == R.id.mItemExit){
            finish();
        } else if (item.getItemId() == R.id.change_color_item){
            changeColorButton();
        } else if(item.getItemId() == R.id.change_size_item) {
            changeSizeButton();
        } else if (item.getItemId() == R.id.change_text_color){
            changeColorText();
        }else if(item.getItemId() == R.id.change_text){
            String s = generateRandomChars();
            btnChanger.setText(s);
        }else if(item.getItemId() == R.id.rotate_button){
            rotateButton();
        }

        return true;
    }

    public String generateRandomChars() {
        String candidates = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()-=_+";
        StringBuilder sb = new StringBuilder ();
        Random random = new Random ();
        for (int i = 0; i < 5; i ++) {
            sb.append (candidates.charAt(random.nextInt(candidates.length())));
        }
        return sb.toString ();
    }

    public void changeColorButton(){
        btnChanger.setBackgroundColor(ContextCompat.getColor(this, R.color.warm));
    }

    public void changeSizeButton(){
        ViewGroup.LayoutParams params = btnChanger.getLayoutParams();
        params.width = 300;
        params.height = 300;

        btnChanger.setLayoutParams(params);
    }

    public void changeSizeButton(int w, int h){
        ViewGroup.LayoutParams params = btnChanger.getLayoutParams();
        params.width = w;
        params.height = h;

        btnChanger.setLayoutParams(params);
    }

    public void changeColorText(){
        btnChanger.setTextColor(ContextCompat.getColor(this, R.color.black));
    }

    public void rotateButton(){
        btnChanger.setRotation(btnChanger.getRotation() + 45);
    }

    public void resetButton(){
        changeSizeButton(950,950);
        btnChanger.setBackgroundColor(ContextCompat.getColor(this, R.color.cool));
        btnChanger.setTextColor(ContextCompat.getColor(this, R.color.white));
        btnChanger.setText("OHAYO!");
        btnChanger.setRotation(360);
    }


}

