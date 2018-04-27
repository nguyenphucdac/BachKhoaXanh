package ambe.com.vn.bachkhoaxanh.presenters.login;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import ambe.com.vn.bachkhoaxanh.activities.login.ILoginView;
import ambe.com.vn.bachkhoaxanh.models.ThanhVien;
import ambe.com.vn.bachkhoaxanh.utils.Api;

/**
 * Created by AMBE on 10/04/2018.
 */

public class LoginPresenter implements ILoginPresenter {

    private ILoginView iLoginView;

    public LoginPresenter(ILoginView iLoginView) {
        this.iLoginView = iLoginView;
    }

    public void checkAccount(Context context, final String tenTaiKhoan, final String matKhau) {

        if (!tenTaiKhoan.equals("") && !matKhau.equals("")) {

            RequestQueue requestQueue = Volley.newRequestQueue(context);
            StringRequest stringRequest = new StringRequest(Request.Method.POST, Api.apiLogin, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    if (!response.equals("")) {

                        ThanhVien thanhVien = new Gson().fromJson(response, ThanhVien.class);
                        loginSuccess(thanhVien);

                    } else if (response.equals("")) {

                        loginFail("Sai tài khoản hoặc mật khẩu");

                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    loginFail("Lỗi kết nối!!! Thông cảm nhé :V ");
                    error.printStackTrace();

                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    HashMap<String, String> hashMap = new HashMap<>();
                    hashMap.put("tenTaiKhoan", tenTaiKhoan);
                    hashMap.put("matKhau", matKhau);
                    return hashMap;
                }
            };

            requestQueue.add(stringRequest);


        } else {
            loginFail("Bạn phải nhập tài khoản hoặc mật khẩu");
        }

    }

    @Override
    public void loginSuccess(ThanhVien thanhVien) {
        iLoginView.onLoginSuccess(thanhVien);

    }

    @Override
    public void loginFail(String mess) {
        iLoginView.onLoginFail(mess);

    }
}
