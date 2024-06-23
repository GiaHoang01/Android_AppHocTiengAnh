package com.example.doanapphoctienganh.ActivityNghe;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.doanapphoctienganh.Database.Class.Nghe;
import com.example.doanapphoctienganh.R;

import java.util.concurrent.TimeUnit;

public class HienThiBaiNghe extends AppCompatActivity {

    private ImageButton btn_TuaToi, backwardbtn, pausebtn, playbtn;
    private MediaPlayer mPlayer;
    ScrollView scr_DapAn;
    ImageButton img_GiaiThich;


    private TextView startTime, songTime,txt_TenChuDe,txt_NoiDung;
    private SeekBar songPrgs;
    private static
    int oTime =0, sTime =0, eTime =0, fTime = 5000, bTime = 5000;
    private Handler hdlr = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_hien_thi_bai_nghe);
        addControls();
        img_GiaiThich.setImageResource(R.drawable.bongden);
        Intent myit = getIntent();
        Nghe nghe = (Nghe) myit.getSerializableExtra("Nghe");
        txt_NoiDung.setText(nghe.getDoanNghe());
        txt_TenChuDe.setText(nghe.getChuDeNghe());
        addEvent(nghe.getFileNghe());
        addEventGiaiThich();
    }

    public void addEventGiaiThich()
    {
        img_GiaiThich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (scr_DapAn.getVisibility() == View.GONE) {
                    scr_DapAn.setVisibility(View.VISIBLE);
                } else {
                    scr_DapAn.setVisibility(View.GONE);
                }
            }
        });

    }
    public void addControls()
    {
        backwardbtn = (ImageButton)findViewById(R.id.btn_TuaLai);
        btn_TuaToi = (ImageButton)findViewById(R.id.btn_TuaToi);
        playbtn = (ImageButton)findViewById(R.id.btn_Phat);
        pausebtn = (ImageButton)findViewById(R.id.btn_Dung);
        startTime = (TextView)findViewById(R.id.txt_ThoiGianDangChay);
        songTime = (TextView)findViewById(R.id.txt_ThoiGianKT);
        songPrgs = (SeekBar)findViewById(R.id.thanhTG);

        txt_TenChuDe=findViewById(R.id.txt_TenChuDe);
        txt_NoiDung=findViewById(R.id.txt_NoiDungBaiNghe);

        scr_DapAn=findViewById(R.id.scr_DapAn);
        img_GiaiThich=findViewById(R.id.img_GiaiThich);
        songPrgs.setClickable(false);
        pausebtn.setEnabled(false);
    }
    public void addEvent(String filenghe)
    {
        int resId = getResources().getIdentifier(filenghe, "raw", getPackageName());

        if (resId != 0) {
            // Tạo MediaPlayer với tài nguyên tương ứng
            mPlayer = MediaPlayer.create(this, resId);
            playbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(HienThiBaiNghe.this, "Playing Audio", Toast.LENGTH_SHORT).show();
                    mPlayer.start();
                    //etime chứa thời gian tổng thể của video
                    eTime = mPlayer.getDuration();
                    //stime chứa thời gian video hiện tại tới đâu
                    sTime = mPlayer.getCurrentPosition();
                    if(oTime == 0){
                        songPrgs.setMax(eTime);
                        oTime =1;
                    }
                    songTime.setText(String.format("%02d:%02d",
                            TimeUnit.MILLISECONDS.toMinutes(eTime),
                            TimeUnit.MILLISECONDS.toSeconds(eTime) -
                                    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS. toMinutes(eTime)))
                    );
                    startTime.setText(String.format("%02d:%02d",
                            TimeUnit.MILLISECONDS.toMinutes(sTime),
                            TimeUnit.MILLISECONDS.toSeconds(sTime) -
                                    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS. toMinutes(sTime)))
                    );
                    songPrgs.setProgress(sTime);
                    //hdlr hiệu ứng cái thanh chạy
                    hdlr.postDelayed(UpdateSongTime, 100);
                    pausebtn.setEnabled(true);
                    playbtn.setEnabled(false);
                }
            });
            pausebtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPlayer.pause();
                    pausebtn.setEnabled(false);
                    playbtn.setEnabled(true);
                    Toast.makeText(getApplicationContext(),"PausingAudio", Toast.LENGTH_SHORT).show();
                }
            });
            btn_TuaToi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if((sTime + fTime) <= eTime)
                    {
                        sTime = sTime + fTime;
                        // chuyển am thanh đến đoạn
                        mPlayer.seekTo(sTime);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Cannotjump forward 5 seconds", Toast.LENGTH_SHORT).show();
                    }
                    if(!playbtn.isEnabled()){
                        playbtn.setEnabled(true);
                    }
                }
            });
            backwardbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if((sTime - bTime) > 0)
                    {
                        sTime = sTime - bTime;
                        mPlayer.seekTo(sTime);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Cannot jump backward 5 seconds", Toast.LENGTH_SHORT).show();
                    }
                    if(!playbtn.isEnabled()){
                        playbtn.setEnabled(true);
                    }
                }
            });
        } else {
            // Xử lý khi không tìm thấy tài nguyên
            System.out.println("File không tồn tại trong thư mục raw.");
        }


    }
    private Runnable UpdateSongTime = new Runnable() {
        @Override
        public void run() {
            sTime = mPlayer.getCurrentPosition();
            startTime.setText(String.format("%02d:%02d",
                    TimeUnit.MILLISECONDS.toMinutes(sTime),
                    TimeUnit.MILLISECONDS.toSeconds(sTime) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(sTime))) );
            songPrgs.setProgress(sTime);
            hdlr.postDelayed(this, 100);
        }
    };
}