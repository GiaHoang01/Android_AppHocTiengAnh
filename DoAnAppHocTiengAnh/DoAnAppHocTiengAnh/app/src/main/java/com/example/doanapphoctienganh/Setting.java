package com.example.doanapphoctienganh;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Set;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Setting#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Setting extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Switch switch_DoiMau;
    boolean nightMode;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    ImageView img_HuongDan,img_theme,img_PhanHoi,imgIcon;

    //khanh
    TextView txtPhanhoi,txt_HuongDan,txtName,txtDangXuat;

    public Setting() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Setting.
     */
    // TODO: Rename and change types and number of parameters
    public static Setting newInstance(String param1, String param2) {
        Setting fragment = new Setting();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        txtPhanhoi=(TextView) view.findViewById(R.id.txtPhanHoi);
        txt_HuongDan=(TextView)view.findViewById(R.id.txt_HuongDan);
        switch_DoiMau = view.findViewById(R.id.switch_DoiMau);
        img_theme=view.findViewById(R.id.img_theme);
        img_HuongDan=view.findViewById(R.id.img_HuongDan);
        img_PhanHoi=view.findViewById(R.id.img_PhanHoi);
        img_theme.setImageResource(R.drawable.moon);
        img_HuongDan.setImageResource(R.drawable.nguphap);
        img_PhanHoi.setImageResource(R.drawable.feedback);
        imgIcon = (ImageView) view.findViewById(R.id.imgIconName);
        imgIcon.setImageResource(R.drawable.icon);
        txtName = (TextView) view.findViewById(R.id.txt_Name);
        txtDangXuat = (TextView) view.findViewById(R.id.txtDangXuat);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null && activity.getSupportActionBar() != null) {
            ActionBar actionBar = activity.getSupportActionBar();
        }

        // Initialize SharedPreferences
        sharedPreferences = getContext().getSharedPreferences("MODE", Context.MODE_PRIVATE);
        nightMode = sharedPreferences.getBoolean("night", false);

        // Set switch state and night mode
        if (nightMode) {
            switch_DoiMau.setChecked(true);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            switch_DoiMau.setChecked(false);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        switch_DoiMau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                if (nightMode) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    editor.putBoolean("night", false);
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    editor.putBoolean("night", true);
                }
                editor.apply();
                nightMode = !nightMode;
                switch_DoiMau.setChecked(nightMode);
            }
        });
        txtPhanhoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFeedbackDialog();
            }
        });

        txt_HuongDan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Hướng Dẫn Sử Dụng");

                // Lấy nội dung văn bản từ string resource và chuyển đổi nó thành Spanned
                String htmlContent = getString(R.string.app_guide);
                Spanned formattedText;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    formattedText = Html.fromHtml(htmlContent, Html.FROM_HTML_MODE_LEGACY);
                } else {
                    formattedText = Html.fromHtml(htmlContent);
                }

                // Thiết lập nội dung của AlertDialog
                builder.setMessage(formattedText);

                // Thiết lập nút OK để tắt AlertDialog
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                // Tạo và hiển thị AlertDialog
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        txtDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDangXuatClick();
            }
        });

        // Lấy tên người dùng từ SharedPreferences và hiển thị
        SharedPreferences loginPrefs = getContext().getSharedPreferences("LoginPrefs", Context.MODE_PRIVATE);
        String userName = loginPrefs.getString("userName", "Guest");
        txtName.setText(userName);

        return view;
    }
    public void onDangXuatClick() {
        // Xóa trạng thái đăng nhập trong SharedPreferences
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("LoginPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("daDangNhap", false);
        editor.apply();

        // Chuyển về màn hình đăng nhập
        Intent intent = new Intent(getActivity(), DangNhap.class);
        startActivity(intent);

        // Đóng Activity hiện tại
        if (getActivity() != null) {
            getActivity().finish();
        }
    }


    //khanh

    private void showFeedbackDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.sfeedback_dialog, null);
        builder.setView(dialogView);

        EditText etContent = dialogView.findViewById(R.id.etContent);
        Button btnSendFeedback = dialogView.findViewById(R.id.btnSendFeedback);
        Button btnCloseFeedback = dialogView.findViewById(R.id.btnCloseFeedback);
        AlertDialog dialog = builder.create();

        btnSendFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = etContent.getText().toString().trim();

                sendEmail(content);

                dialog.dismiss();
            }
        });
        btnCloseFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void sendEmail(String content) {
        String subject = "Ý kiến đóng góp của người dùng";
        String message = "Content:\n" + content;
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        //emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("message/rfc822");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"huynhvuonghuukhanhb6@gmail.com"});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, message);

        try {
            startActivity(Intent.createChooser(emailIntent, "Send email using..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(getContext(), "No email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }
}