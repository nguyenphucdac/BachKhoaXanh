package ambe.com.vn.bachkhoaxanh.activities.register;

import android.app.Application;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import ambe.com.vn.bachkhoaxanh.R;
import ambe.com.vn.bachkhoaxanh.activities.login.LoginActivity;
import ambe.com.vn.bachkhoaxanh.presenters.register.RegisterPresenter;

public class RegisterActivity extends AppCompatActivity implements IRegisterView, View.OnClickListener {
    private EditText editSdt;
    private EditText editMk;
    private EditText editNhapLaiMk;
    private EditText editTenDayDu;

    private Button btnDangKy;
    private TextView txtThoat;
    private TextView txtQuayLai;
    private RegisterPresenter registerPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnDangKy.setOnClickListener(this);
        txtThoat.setOnClickListener(this);
        txtQuayLai.setOnClickListener(this);

    }

    private void addControls() {
        editSdt = findViewById(R.id.edit_sdt_dk);
        editMk = findViewById(R.id.edit_mat_khau_dk);
        editNhapLaiMk = findViewById(R.id.edit_nhap_lai_mat_khau_dk);
        editTenDayDu = findViewById(R.id.edit_ten_day_du_dk);
        btnDangKy = findViewById(R.id.btn_dang_ky);
        txtQuayLai = findViewById(R.id.txt_quay_lai);
        txtThoat = findViewById(R.id.txt_thoat_dk);

        registerPresenter = new RegisterPresenter(this);

    }

    @Override
    public void onRegisterSuccsess(String mess) {
        Toast.makeText(this, mess, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onRegisterFail(String mess) {
        Toast.makeText(this, mess, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.txt_thoat_dk:
                break;
            case R.id.txt_quay_lai:
                finish();
                break;
            case R.id.btn_dang_ky:
                xuLyDangKy();
                break;
        }

    }

    private void xuLyDangKy() {
        String tenTaiKhoan = editSdt.getText().toString().trim();
        String matKhau = editMk.getText().toString().trim();
        String nhapLaiMatKhau = editNhapLaiMk.getText().toString().trim();
        String tenDayDu = editTenDayDu.getText().toString().trim();
        if (tenTaiKhoan.equals("") || matKhau.equals("") || nhapLaiMatKhau.equals("") || tenDayDu.equals(" ")) {
            Toast.makeText(this, "Bạn vui lòng điền đầy đủ nội dung", Toast.LENGTH_SHORT).show();
        } else {
            if (!matKhau.equals(nhapLaiMatKhau)) {
                Toast.makeText(this, "Mật khẩu không trùng khớp.", Toast.LENGTH_SHORT).show();
            } else {
                registerPresenter.register(getApplicationContext(), tenTaiKhoan, matKhau, tenDayDu);
                finish();
            }
        }
    }
}
