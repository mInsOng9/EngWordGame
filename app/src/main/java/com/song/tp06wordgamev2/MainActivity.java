package com.song.tp06wordgamev2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Random rnd=new Random();
    TextView count,qText,ans1,ans2,ans3,ans4,result,tvScore;
    ImageView ivQ;
    Button btnNext,btnRestart;
    GridLayout ans;
    RelativeLayout score;
    int[] image=new int[]{
            R.drawable.car,R.drawable.compass,R.drawable.cruise,R.drawable.dice,
            R.drawable.flower, R.drawable.plane,R.drawable.rainbow, R.drawable.star,
            R.drawable.train, R.drawable.umbrella
    };
    String[] answer=new String[]{
            "자동차","나침반","크루즈","주사위",
            "꽃","비행기","무지개","별",
            "기차","우산"
    };
    String[] q=new String[]{
            "car","compass","cruise","dice",
            "flower","plane","rainbow","star",
            "train","umbrella"
    };
    int arr[]=new int[4];
    int round=0;
    int ansIndex;
    int correct;
    boolean onlyOnce=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        count = findViewById(R.id.tv_count);
        qText = findViewById(R.id.tv_qtext);
        ans = findViewById(R.id.ans);
        ans1 = findViewById(R.id.tv_ans1);
        ans2 = findViewById(R.id.tv_ans2);
        ans3 = findViewById(R.id.tv_ans3);
        ans4 = findViewById(R.id.tv_ans4);
        result = findViewById(R.id.tv_result);
        tvScore=findViewById(R.id.tv_score);

        btnNext = findViewById(R.id.btn_next);
        btnRestart = findViewById(R.id.btn_restart);

        ivQ = findViewById(R.id.iv_qpic);
        score = findViewById(R.id.score);

        ans1.setOnClickListener(listener);
        ans2.setOnClickListener(listener);
        ans3.setOnClickListener(listener);
        ans4.setOnClickListener(listener);

        btnNext.setOnClickListener(listener2);
        btnRestart.setOnClickListener(listener2);
    }

    View.OnClickListener listener2=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            onlyOnce=true;
            //when User selects 'restart' button
            if (btnRestart.isPressed()) {
                round = 0;
                correct = 0;
                ansIndex = 0;
                score.setVisibility(View.INVISIBLE);
                btnNext.setText("START");
            }
            //when all questions are done
            if (round > 9) {
                tvScore.setText(correct * 10 + "");
                score.setVisibility(View.VISIBLE);
                btnRestart.setVisibility(View.VISIBLE);
                btnNext.setVisibility(View.INVISIBLE);
            }
            else {
                btnRestart.setVisibility(View.INVISIBLE);
                btnNext.setVisibility(View.INVISIBLE);
                btnNext.setText("NEXT");
                result.setVisibility(View.INVISIBLE);
                ans.setVisibility(View.VISIBLE);
                ivQ.setVisibility(View.VISIBLE);
                count.setVisibility(View.VISIBLE);
                ivQ.setImageResource(image[round]);
                qText.setVisibility(View.VISIBLE);
                qText.setText(q[round]);
                for (int i = 0; i < arr.length; i++) {
                    arr[i] = rnd.nextInt(answer.length);
                    if (arr[i] == round) {
                        i--;
                        continue;
                    }
                    for (int k = 0; k < i; k++) {
                        if (arr[i] == arr[k]) i--;
                    }
                }
                ansIndex = rnd.nextInt(arr.length);
                arr[ansIndex] = round;

                ans1.setText(answer[arr[0]]);
                ans1.setTag(0);
                ans2.setText(answer[arr[1]]);
                ans2.setTag(1);
                ans3.setText(answer[arr[2]]);
                ans3.setTag(2);
                ans4.setText(answer[arr[3]]);
                ans4.setTag(3);
                round++;
                count.setText("" + round + "/10");
            }
        }
    };


    View.OnClickListener listener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String s=view.getTag().toString();
            int userAns=Integer.parseInt(s);

            if(onlyOnce==true) {
                if (userAns == ansIndex) {
                    qText.setText("GOOD");
                    correct++;
                } else qText.setText("BAD");

                qText.setVisibility(View.VISIBLE);
                btnNext.setVisibility(View.VISIBLE);
                onlyOnce=false;
            }
            else {
                Toast.makeText(MainActivity.this, "You can't try more than once", Toast.LENGTH_SHORT).show();
            }
        }
    };
}


