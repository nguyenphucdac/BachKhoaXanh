package ambe.com.vn.bachkhoaxanh.presenters.register;

import android.content.Context;

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

import ambe.com.vn.bachkhoaxanh.activities.register.IRegisterView;
import ambe.com.vn.bachkhoaxanh.models.NewResponse;
import ambe.com.vn.bachkhoaxanh.utils.Api;

/**
 * Created by AMBE on 09/05/2018.
 */

public class RegisterPresenter implements IRegisterPresenter {

    private IRegisterView iRegisterView;

    public RegisterPresenter(IRegisterView iRegisterView) {
        this.iRegisterView = iRegisterView;
    }

    public void register(Context context, final String tenTaiKhoan, final String matKhau, final String tenDayDu) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Api.apiRegister, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response != null) {
                    NewResponse newResponse = new Gson().fromJson(response, NewResponse.class);
                    registerSuccess(newResponse.getMessage());
                } else {
                    registerFail("Lỗi kết nối");
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                registerFail("Lỗi kết nối");

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("tenTaiKhoan", tenTaiKhoan);
                hashMap.put("matKhau", matKhau);
                hashMap.put("tenDayDu", tenDayDu);
                return hashMap;
            }
        };

        requestQueue.add(stringRequest);
    }

    @Override
    public void registerSuccess(String mess) {

        iRegisterView.onRegisterSuccsess(mess);


    }

    @Override
    public void registerFail(String mess) {
        iRegisterView.onRegisterFail(mess);

    }
}
