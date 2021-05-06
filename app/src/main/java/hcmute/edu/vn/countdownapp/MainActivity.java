package hcmute.edu.vn.countdownapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button btnStart;
    private Button btnCancel;
    public static final Random RANDOM = new Random();
    private CountDownTimer countDownTimer;
    private EditText txtNum;
    Integer time;
    String begin;
    View root;

    private View.OnClickListener btnOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.btnStart:
                    start();
                    break;
                case R.id.btnCancel:
                    cancel();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        root = findViewById(R.id.root);
        btnStart = (Button)findViewById(R.id.btnStart);
        btnStart.setOnClickListener(btnOnClickListener);
        btnCancel = (Button)findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(btnOnClickListener);
        txtNum = (EditText)findViewById(R.id.txtNumber);
    }

    private void start() {
        try{
            time = Integer.parseInt(txtNum.getText().toString());
            countDownTimer = new CountDownTimer(time*1000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    txtNum.setText("" + millisUntilFinished/1000);
                    int red = RANDOM.nextInt(255);
                    int green = RANDOM.nextInt(255);
                    int blue = RANDOM.nextInt(255);

                    int color = Color.rgb(red, green, blue);

                    root.setBackgroundColor(color);

                }

                @Override
                public void onFinish() {
                    txtNum.setText("Done");
                }
            };

            countDownTimer.start();
        }
        catch (Exception ex)
        {
            Context context = getApplicationContext();
            int duration  = Toast.LENGTH_SHORT;
            CharSequence text= "Enter number";
            Toast.makeText(context,text, duration).show();
        }


    }

    private void cancel(){
        if(countDownTimer != null){
            countDownTimer.cancel();
            countDownTimer = null;
        }

    }
}