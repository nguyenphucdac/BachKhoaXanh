package ambe.com.vn.bachkhoaxanh.activities.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import ambe.com.vn.bachkhoaxanh.R;
import ambe.com.vn.bachkhoaxanh.activities.main.MainActivity;
import ambe.com.vn.bachkhoaxanh.models.ThanhVien;
import ambe.com.vn.bachkhoaxanh.presenters.login.LoginPresenter;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, ILoginView {

    private EditText editSdt;
    private EditText editMatKhau;
    private Button btnDangNhap;
    private TextView txtDangKy;
    private TextView txtBoQua;
    private TextView txtQuenMatKhau;
    private LoginPresenter loginPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        addControls();
        addEvents();
    }

    private void addEvents() {

        txtBoQua.setOnClickListener(this);
        btnDangNhap.setOnClickListener(this);


    }

    private void addControls() {

        editSdt = findViewById(R.id.edit_sdt);
        editMatKhau = findViewById(R.id.edit_mat_khau);
        btnDangNhap = findViewById(R.id.btn_dang_nhap);
        txtBoQua = findViewById(R.id.txt_bo_qua_atv);
        txtDangKy = findViewById(R.id.txt_dang_ky_atv);
        txtQuenMatKhau = findViewById(R.id.txt_quen_mat_khau_atv);

        loginPresenter = new LoginPresenter(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.txt_bo_qua_atv:
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                Bundle bundle = new Bundle();
                intent.putExtra("BUN", bundle);
                bundle.putString("TNV", "TNV");
                startActivity(intent);
                break;
            case R.id.btn_dang_nhap:
                xuLyDangNhap();
                break;
        }

    }

    private void xuLyDangNhap() {
        String tenTaiKhoan = editSdt.getText().toString();
        String matKhau = editMatKhau.getText().toString();

        loginPresenter.checkAccount(getApplicationContext(), tenTaiKhoan, matKhau);

    }

    @Override
    public void onLoginSuccess(ThanhVien thanhVien) {

        Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        Bundle bundle = new Bundle();
        intent.putExtra("BUN", bundle);
        bundle.putSerializable("TV", thanhVien);
        bundle.putString("TNV","");
        startActivity(intent);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        editMatKhau.setText("");
    }

    @Override
    public void onLoginFail(String mess) {
        Toast.makeText(this, mess, Toast.LENGTH_SHORT).show();

    }
}
