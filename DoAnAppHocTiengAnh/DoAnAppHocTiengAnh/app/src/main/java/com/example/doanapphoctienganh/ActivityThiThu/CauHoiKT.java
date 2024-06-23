package com.example.doanapphoctienganh.ActivityThiThu;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.doanapphoctienganh.Database.Class.CauHoi;
import com.example.doanapphoctienganh.Database.Handle.ThiThuHandle;
import com.example.doanapphoctienganh.Database.Handle.VietHandle;
import com.example.doanapphoctienganh.R;

import java.util.ArrayList;
import java.util.Locale;

public class CauHoiKT extends AppCompatActivity {
    private CountDownTimer countDownTimer;

    ThiThuHandle thiThuHandle;

    private long timeLeftInMillis;
    private int cauHoiCounter, cauHoiSize, Score, idBaiKiemTra;
    private String tenBaiKiemTra;
    LinearLayout linear_Nop;
    Button btn_Nop;
    private Button btn_A, btn_B, btn_C, btn_D,btn_NoSubmit, btn_YesSubmit;
    private ImageButton btn_back, btn_stop;
    private ArrayList<CauHoi> cauHoiList;
    private CauHoi cauHoiHienTai;
    private TextView tvQuestion, tvOptionA, tvOptionB, tvOptionC, tvOptionD, tvQuestionProgress, tvQuestionCountTime, tvScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cau_hoi);
        addControls();

        thiThuHandle=new ThiThuHandle(getApplicationContext(),"Android.db", null,1);

        idBaiKiemTra = getIntent().getIntExtra("IDBaiKiemTra", 1);
        tenBaiKiemTra = getIntent().getStringExtra("EXAM_NAME");
        ThiThuHandle thiThuHandle = new ThiThuHandle(this, "Android.db", null, 1);
        cauHoiList = thiThuHandle.getCauHoiByBaiKiemTraId(idBaiKiemTra);
        cauHoiSize = cauHoiList.size();
        hienThiCauHoi();
        timeLeftInMillis=0;
        startCountDown(1800000);

    }
    private void addControls(){
        tvQuestion = findViewById(R.id.tv_question);
        tvOptionA = findViewById(R.id.tvOptionA);
        tvOptionB = findViewById(R.id.tvOptionB);
        tvOptionC = findViewById(R.id.tvOptionC);
        tvOptionD = findViewById(R.id.tvOptionD);
        linear_Nop=findViewById(R.id.Linear_Nop);
        btn_Nop=findViewById(R.id.btn_Nop);

        tvQuestionProgress = findViewById(R.id.tv_question_progress);
        tvQuestionCountTime = findViewById(R.id.txt_countdown);
        tvScore = findViewById(R.id.txt_score);
        btn_YesSubmit = findViewById(R.id.ok_button);
        btn_NoSubmit = findViewById(R.id.nono_button);
        btn_A = findViewById(R.id.btn_option_a);
        btn_B = findViewById(R.id.btn_option_b);
        btn_C = findViewById(R.id.btn_option_c);
        btn_D = findViewById(R.id.btn_option_d);
        btn_back = findViewById(R.id.btn_back);
        btn_stop = findViewById(R.id.btn_stop);

        btn_A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(1);
            }
        });

        btn_B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(2);
            }
        });

        btn_C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(3);
            }
        });

        btn_D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(4);
            }
        });
        btn_Nop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogWindow();
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent= new Intent(CauHoiKT.this, LoadCauHoi.class);
                myintent.putExtra("IDBaiKiemTra", idBaiKiemTra);
                myintent.putExtra("EXAM_NAME", tenBaiKiemTra);
                finish();
            }
        });

        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PauseTime();
                showPopupWindow();
            }
        });
    }
    private void hienThiCauHoi() {
        if (cauHoiCounter < cauHoiSize) {
            cauHoiHienTai = cauHoiList.get(cauHoiCounter);
            tvQuestion.setText(cauHoiHienTai.getCauHoi());
            tvOptionA.setText("A. " + cauHoiHienTai.getDapAnA());
            tvOptionB.setText("B. " + cauHoiHienTai.getDapAnB());
            tvOptionC.setText("C. " + cauHoiHienTai.getDapAnC());
            tvOptionD.setText("D. " + cauHoiHienTai.getDapAnD());

            cauHoiCounter++;

            tvQuestionProgress.setText(cauHoiCounter + "/ " + cauHoiSize);
        }
        else {
            linear_Nop.setVisibility(View.VISIBLE);
            btn_A.setEnabled(false);
            btn_B.setEnabled(false);
            btn_C.setEnabled(false);
            btn_D.setEnabled(false);
        }
    }

    private void showDialogWindow() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_layout, null);

        int width = ViewGroup.LayoutParams.WRAP_CONTENT;
        int height = ViewGroup.LayoutParams.WRAP_CONTENT;
        boolean focusable = true;
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setOutsideTouchable(true);

        popupWindow.showAtLocation(btn_Nop, Gravity.CENTER, 0, 0);

        dimBehind(popupWindow);

        Button NoButton = popupView.findViewById(R.id.nono_button);
        Button YesButton = popupView.findViewById(R.id.ok_button);
        YesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (thiThuHandle.updateTrangThai(tenBaiKiemTra, "Hoàn thành")) {

                    Toast.makeText(getApplicationContext(), "Đã hoàn thành bài làm", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Cập nhật trạng thái không thành công", Toast.LENGTH_SHORT).show();
                }
                finishQuestion();
                popupWindow.dismiss();
            }
        });
        NoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                ResumeTime();
            }
        });
    }
    private void finishQuestion() {
        Intent resultIntent=new Intent();
        resultIntent.putExtra("score",Score);
        resultIntent.putExtra("TenBaiKiemTra",tenBaiKiemTra);
        setResult(RESULT_OK,resultIntent);
        finish();
    }
    private void showPopupWindow() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.dialog_layout, null);

        int width = ViewGroup.LayoutParams.WRAP_CONTENT;
        int height = ViewGroup.LayoutParams.WRAP_CONTENT;
        boolean focusable = true;
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setOutsideTouchable(true);

        popupWindow.showAtLocation(btn_stop, Gravity.CENTER, 0, 0);

        dimBehind(popupWindow);

        Button popupButton = popupView.findViewById(R.id.popup_button);
        popupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                ResumeTime();
            }
        });
    }
    private void dimBehind(PopupWindow popupWindow) {
        View container = popupWindow.getContentView().getRootView();
        Context context = popupWindow.getContentView().getContext();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        WindowManager.LayoutParams p = (WindowManager.LayoutParams) container.getLayoutParams();
        p.flags |= WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        p.dimAmount = 0.5f; // Set the amount of dimming here
        wm.updateViewLayout(container, p);
    }
    private void startCountDown(long time) {
        countDownTimer = new CountDownTimer(time,1000) {
            @Override
            public void onTick(long l) {
                timeLeftInMillis=l;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                timeLeftInMillis=0;
                updateCountDownText();
            }
        }.start();
    }
    public void PauseTime() {
        countDownTimer.cancel();
    }
    private void ResumeTime()
    {
        startCountDown(timeLeftInMillis);
    }
    private void updateCountDownText() {
        int minutes=(int) ((timeLeftInMillis/1000)/60);
        int seconds=(int) ((timeLeftInMillis/1000)%60);
        String timeFormatted=String.format(Locale.getDefault(),"%02d:%02d",minutes,seconds);
        tvQuestionCountTime.setTextColor(Color.parseColor("#69E1A9"));
        tvQuestionCountTime.setText("Thời gian: " + timeFormatted);
        if(timeLeftInMillis<300000)
        {
            tvQuestionCountTime.setTextColor(Color.RED);
        }
        else{
            tvQuestionCountTime.setTextColor(Color.BLACK);
        }
    }
    private void checkAnswer(int selectedAnswer) {
        int correctAnswer = cauHoiHienTai.getDapAnDung();

        if (selectedAnswer == correctAnswer) {
            Score=Score+10;

            tvScore.setText("Điểm: " + Score);
        }
        hienThiCauHoi();
    }
}