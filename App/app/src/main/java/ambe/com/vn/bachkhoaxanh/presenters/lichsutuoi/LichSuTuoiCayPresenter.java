package ambe.com.vn.bachkhoaxanh.presenters.lichsutuoi;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import ambe.com.vn.bachkhoaxanh.activities.lichsutuoi.ILichSuTuoiCayView;
import ambe.com.vn.bachkhoaxanh.models.Cay;
import ambe.com.vn.bachkhoaxanh.models.LichSuTuoiCay;
import ambe.com.vn.bachkhoaxanh.utils.Api;

/**
 * Created by AMBE on 10/04/2018.
 */

public class LichSuTuoiCayPresenter implements ILichSuTuoiCayPresenter {


    private ILichSuTuoiCayView iLichSuTuoiCayView;

    public LichSuTuoiCayPresenter(ILichSuTuoiCayView iLichSuTuoiCayView) {
        this.iLichSuTuoiCayView = iLichSuTuoiCayView;
    }

    public void getLichSuTuoiCuaCay(Context context, String param, String api) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, (api + param), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response.length() > 2) {
                    Type collectionType = new TypeToken<ArrayList<LichSuTuoiCay>>() {
                    }.getType();
                    ArrayList<LichSuTuoiCay> enums = new Gson().fromJson(response, collectionType);
                    getListLichSuTuoiSuccess(enums);
                } else if (response.length() == 2) {
                    getListLichSuTuoiFail("Cây chưa ai từng tưới");
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.e("LOI", error.toString());
                getListLichSuTuoiFail(error.toString());

            }
        });

        requestQueue.add(stringRequest);
    }


    @Override
    public void getListLichSuTuoiSuccess(ArrayList<LichSuTuoiCay> arrayList) {
        iLichSuTuoiCayView.showListLichSuTuoi(arrayList);

    }

    @Override
    public void getListLichSuTuoiFail(String mess) {
        iLichSuTuoiCayView.showListLichSuTuoiFail(mess);

    }
}
