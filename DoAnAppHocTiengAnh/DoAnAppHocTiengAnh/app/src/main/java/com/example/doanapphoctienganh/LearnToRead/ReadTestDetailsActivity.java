package com.example.doanapphoctienganh.LearnToRead;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.doanapphoctienganh.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class ReadTestDetailsActivity extends AppCompatActivity {
    private TextView textViewQuestion,textViewScore,textViewQuestionCount,textViewCategory,textViewCountDown,tvBaiDoc,tvResultQu;
    ImageView imgResultQu;
    private RadioGroup rdoGroup;
    private RadioButton rb1,rb2,rb3,rb4;
    private Button btnConfirmNext;
    private CountDownTimer countDownTimer;
    private ArrayList<ReadTest> questionList;
    private long timeLeftInMillis;
    private int questionCounter,questionSize,Score;
    private boolean answered;
    private ReadTest currentQuestion;
    private int count=0;
    ListReadsHandle readsHandle;
    ReadExercise exercise = new ReadExercise() ;
    private long totalTimeSpent = 0;
    private long startTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_read_test_details);
        startTime = System.currentTimeMillis();
        addControl();
        Bundle bundle = getIntent().getBundleExtra("package");
        exercise.setId(bundle.getInt("id"));
        exercise.setTenBaiDoc(bundle.getString("tenBai"));
        exercise.setIdBaiHoc(bundle.getInt("idBaiHoc"));
        exercise.setNoiDungBaiDoc(bundle.getString("noiDung"));
        textViewCategory.setText("Chủ đề: "+exercise.getTenBaiDoc());
        tvBaiDoc.setText(exercise.getNoiDungBaiDoc());

        readsHandle =new ListReadsHandle(getApplicationContext(),"Android.db", null, 1);

        questionList = readsHandle.getAllQuestion(exercise.getId());
        questionSize = questionList.size();
        Collections.shuffle(questionList);
        showNextQuestion();
        addenvent();
    }
    @Override
    protected void onStart() {
        super.onStart();
        startTime = System.currentTimeMillis();

    }



    void addenvent(){
        btnConfirmNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!answered){
                    if(rb1.isChecked() || rb2.isChecked() || rb3.isChecked() || rb4.isChecked())
                    {
                        checkAnswer();
                    }
                    else{
                        Toast.makeText(ReadTestDetailsActivity.this,"Hãy chọn đáp án",Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    showNextQuestion();
                }
            }
        });

    }
    private void showNextQuestion() {
        rb1.setTextColor(Color.WHITE);
        rb2.setTextColor(Color.WHITE);
        rb3.setTextColor(Color.WHITE);
        rb4.setTextColor(Color.WHITE);
        tvResultQu.setVisibility(View.GONE);
        imgResultQu.setVisibility(View.GONE);
        rdoGroup.clearCheck();
        //Nếu còn câu hỏi
        if(questionCounter<questionSize){
            currentQuestion=questionList.get(questionCounter);
            questionCounter++;
            textViewQuestion.setText("Câu Hỏi "+questionCounter+" : "+currentQuestion.getQuestion());
            rb1.setText(currentQuestion.getOption1());
            rb2.setText(currentQuestion.getOption2());
            rb3.setText(currentQuestion.getOption3());
            rb4.setText(currentQuestion.getOption4());
            textViewQuestionCount.setText("Câu hỏi: "+questionCounter+"/ "+questionSize);
            //giá trị false, chưa trả lời, đang show
            answered=false;
            //gắn tên cho button
            btnConfirmNext.setText("Xác nhận");
            //Thời gian chạy 30s
            timeLeftInMillis=30000;
            //đếm ngược thời gian trả lời
            startCountDown();
        }
        else{
            finishQuestion();
        }
    }
    //phương thức thời gian đếm ngược
    private void startCountDown() {
        countDownTimer=new CountDownTimer(timeLeftInMillis,1000) {
            @Override
            public void onTick(long l) {
                timeLeftInMillis=l;
                //update time
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                //hết giời
                timeLeftInMillis=0;
                updateCountDownText();
                //phương thức kiểm tra đáp án
                checkAnswer();
            }
        }.start();
    }
    private void updateCountDownText() {
        //tính phút
        int minutes=(int) ((timeLeftInMillis/1000)/60);
        int seconds=(int) ((timeLeftInMillis/1000)%60);
        String timeFormatted=String.format(Locale.getDefault(),"%02d:%02d",minutes,seconds);
        //hiển thị lên màn hình
        textViewCountDown.setText(timeFormatted);
        //nếu thời gian dưới 10s thì sẽ chuyển màu đỏ
        if(timeLeftInMillis<10000)
        {
            textViewCountDown.setTextColor(Color.RED);
        }
        else{
            textViewCountDown.setTextColor(Color.WHITE);
        }
    }
    //Kiểm tra đáp án
    private void checkAnswer() {
        //true đã trả lời
        answered=true;
        //trả về radiobutton trong fvGroup được check
        RadioButton rbselected=(RadioButton) findViewById(rdoGroup.getCheckedRadioButtonId());
        //vị trí của câu đã chọn
        int answer=rdoGroup.indexOfChild(rbselected)+1;
        //nếu trả lời đúng
        tvResultQu.setVisibility(View.VISIBLE);
        imgResultQu.setVisibility(View.VISIBLE);
        if(answer==currentQuestion.getAnswer()){
            Score=Score+10;
            tvResultQu.setText("Chính Xác");
            imgResultQu.setImageResource(R.drawable.celebrationw);
            textViewScore.setText("Điểm: "+Score);
        }
        else {
            tvResultQu.setText("Sai Rồi");
            imgResultQu.setImageResource(R.drawable.closer);
        }
        //Hiển thị đáp án
        showSolution(answer);
    }
    //Đáp án
    private void showSolution(int answer) {
        rb1.setTextColor(Color.GRAY);
        rb2.setTextColor(Color.GRAY);
        rb3.setTextColor(Color.GRAY);
        rb4.setTextColor(Color.GRAY);
        switch (answer) {
            case 1:
                rb1.setTextColor(Color.RED);
                break;
            case 2:
                rb2.setTextColor(Color.RED);
                break;
            case 3:
                rb3.setTextColor(Color.RED);
                break;
            case 4:
                rb4.setTextColor(Color.RED);
                break;
        }
        //Kiểm tra đáo abs set màu và hiển thị đáp án lên màn hình
        switch (currentQuestion.getAnswer()){
            case 1:
                rb1.setTextColor(Color.GREEN);
                break;
            case 2:
                rb2.setTextColor(Color.GREEN);
                break;
            case 3:
                rb3.setTextColor(Color.GREEN);
                break;
            case 4:
                rb4.setTextColor(Color.GREEN);
                break;
        }
        //Nếu còn câu trả lời thì sẽ hiển thị câu tiếp
        if(questionCounter<questionSize){
            btnConfirmNext.setText("Câu tiếp");
        }
        else {
            btnConfirmNext.setText("Hoàn thành");
        }
        countDownTimer.cancel();
    }



    private void finishQuestion() {
        long endTime = System.currentTimeMillis(); // Lấy thời điểm khi Activity dừng
        totalTimeSpent = endTime - startTime; // Tính thời gian đã trôi qua từ khi Activity bắt đầu
        readsHandle.updateBaiHocTrangThai(exercise.getIdBaiHoc(),"Đã Hoàn Thành");
        Intent intent = new Intent(getApplicationContext(),ResultTestReadActivity.class);
        Bundle bundle= new Bundle();
        bundle.putInt("core",Score);
        bundle.putInt("id",exercise.getId());
        bundle.putLong("time",totalTimeSpent);
        intent.putExtra("package",bundle);
        startActivity(intent);
    }

    //back
    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {
        count++;
        if (count >= 1) {
            finishQuestion();
        }
        count = 0;
    }

    //ánh xạ id
    private void addControl(){
        textViewQuestion=(TextView) findViewById(R.id.txt_question);
        textViewScore=(TextView) findViewById(R.id.txtscore);
        textViewQuestionCount=(TextView) findViewById(R.id.txtquestion_count);
        textViewCategory=(TextView) findViewById(R.id.txt_category);
        textViewCountDown=(TextView) findViewById(R.id.txt_countdown);
        tvBaiDoc = (TextView) findViewById(R.id.txt_BaiDoc);
        rdoGroup=(RadioGroup) findViewById(R.id.radio_group);
        rb1=(RadioButton) findViewById(R.id.radio_button1);
        rb2=(RadioButton) findViewById(R.id.radio_button2);
        rb3=(RadioButton) findViewById(R.id.radio_button3);
        rb4=(RadioButton) findViewById(R.id.radio_button4);
        btnConfirmNext=(Button) findViewById(R.id.btn_confirm_next);
        tvResultQu = (TextView) findViewById(R.id.txt_resultQu);
        imgResultQu = (ImageView) findViewById(R.id.imgResultQu);
    }
    public void expand(View view) {
        int v = (tvBaiDoc.getVisibility() == View.GONE)? View.VISIBLE: View.GONE;
        tvBaiDoc.setVisibility(v);
    }
}