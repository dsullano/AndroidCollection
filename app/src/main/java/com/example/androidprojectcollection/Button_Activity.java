package com.example.androidprojectcollection;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Button_Activity extends AppCompatActivity {

    int toggle = 1;
    Button returnbtn;
    Button toastbtn;
    Button backgroundbtn;
    Button colorbtn;
    Button disappearbtn;
    LinearLayout container;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button);

        returnbtn =(Button) findViewById(R.id.returnbtn);
        toastbtn =(Button) findViewById(R.id.toastbtn);
        backgroundbtn =(Button) findViewById(R.id.backgroundbtn);
        colorbtn =(Button) findViewById(R.id.colorbtn);
        disappearbtn = (Button) findViewById(R.id.disappearbtn);
        container = (LinearLayout)findViewById(R.id.container);

        returnbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(
                        Button_Activity.this,
                        AfterClose.class);
                startActivity(intent1);
            }
        });

        toastbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CharSequence text = "This is a toast!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(Button_Activity.this, text, duration);
                toast.show();
            }
        });

        backgroundbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(toggle == 1){
                    container.setBackgroundResource(R.color.warm);
                }else {
                    container.setBackgroundResource(R.color.cool);
                }

            }
        });

       colorbtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResouceAsColor")
            @Override
            public void onClick(View view) {

                colorbtn.setBackgroundColor(getResources().getColor(R.color.warm));
            }
        });

       disappearbtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               disappearbtn.setVisibility(view.GONE);
           }
       });
    }



}