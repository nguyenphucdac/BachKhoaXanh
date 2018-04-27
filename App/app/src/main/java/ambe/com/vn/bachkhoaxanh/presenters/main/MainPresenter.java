package ambe.com.vn.bachkhoaxanh.presenters.main;

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
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import ambe.com.vn.bachkhoaxanh.activities.main.IMainView;
import ambe.com.vn.bachkhoaxanh.models.Cay;
import ambe.com.vn.bachkhoaxanh.models.DiemCapNuoc;
import ambe.com.vn.bachkhoaxanh.models.NewResponse;
import ambe.com.vn.bachkhoaxanh.models.Point;
import ambe.com.vn.bachkhoaxanh.models.ThanhVien;
import ambe.com.vn.bachkhoaxanh.utils.Api;

/**
 * Created by AMBE on 09/04/2018.
 */

public class MainPresenter implements IMainPresenter {


    private IMainView iMainView;

    public MainPresenter(IMainView iMainView) {
        this.iMainView = iMainView;
    }

    // lấy danh sách cây

    public void getListCay(Context context) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Api.apiGetListCay, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response != null) {

                    Type collectionType = new TypeToken<ArrayList<Cay>>() {
                    }.getType();
                    ArrayList<Cay> enums = new Gson().fromJson(response, collectionType);
                    getListTreeSucess(enums);
//                    String json="{"+response+"}";
//                    Log.e("LOI", response);
//                    listCay = new Gson().fromJson(json, GetListCay.class);
//                    getListTreeSucess(listCay.getArrayList());
////

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.e("LOI", error.toString());
                getListTreeFail(error.toString());

            }
        });
        requestQueue.add(stringRequest);
    }

    @Override
    public void getListTreeSucess(ArrayList<Cay> arrayList) {

        iMainView.showListCay(arrayList);

    }

    @Override
    public void getListTreeFail(String mess) {

        iMainView.showErrorGetListTree(mess);


    }

    // lấy danh sách điểm cấp nước

    public void getListDiemCapNuoc(Context context) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Api.apiGetListDiemCapNuoc, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response.length() > 2) {
                    Type collectionType = new TypeToken<ArrayList<DiemCapNuoc>>() {
                    }.getType();
                    ArrayList<DiemCapNuoc> enums = new Gson().fromJson(response, collectionType);
                    getListDiemCapNuocSuccess(enums);

                } else {
                    getListDiemCapNuocFail("Không có điểm cấp nước");
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.e("LOI", error.toString());
                getListDiemCapNuocFail(error.toString());

            }
        });

        requestQueue.add(stringRequest);
    }

    @Override
    public void getListDiemCapNuocSuccess(ArrayList<DiemCapNuoc> arrayList) {
        iMainView.showListDiemCapNuoc(arrayList);
    }

    @Override
    public void getListDiemCapNuocFail(String mess) {
        iMainView.showErrorGetListDiemCapNuoc(mess);

    }

    // đăng xuất
    public void logout(Context context, final String idThanhVien) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Api.apiLogout, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response != null) {
                    NewResponse newResponse = new Gson().fromJson(response, NewResponse.class);

                    if (newResponse.getCode().equals("201")) {
                        onLogoutFail(newResponse.getMessage());
                    } else if (newResponse.getCode().equals("200")) {
                        onLogoutSuccess(newResponse.getMessage());
                    }
                } else {
                    onLogoutFail("Đăng xuất thất bại");
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                onLogoutFail("Lỗi kết nối");

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("idThanhVien", idThanhVien);
                return hashMap;
            }
        };

        requestQueue.add(stringRequest);
    }

    @Override
    public void onLogoutSuccess(String mess) {

        iMainView.logoutSuccess(mess);

    }

    @Override
    public void onLogoutFail(String mess) {
        iMainView.logoutFail(mess);

    }

    // tìm kiếm đường đi ngắn nhất đến 1 cây đã biết trước

    public void getDirectionFromTree(Context context, final String idThanhVien, final String toaDoX, final String toaDoY, final String idCay) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Api.apiGetDirectionFromTree, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response.length() > 2) {
                    Type collectionType = new TypeToken<ArrayList<Point>>() {
                    }.getType();
                    ArrayList<Point> enums = new Gson().fromJson(response, collectionType);
                    getDirectionFromTreeSuccess(enums);


                } else {
                    getDirectionFromTreeFail("Không tìm được");
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.e("LOI", error.toString());
                getDirectionFromTreeFail("Lỗi kết nối");
                error.printStackTrace();


            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("idThanhVien", idThanhVien);
                hashMap.put("toaDoX", toaDoX);
                hashMap.put("toaDoY", toaDoY);
                hashMap.put("idCay", idCay);
                return hashMap;
            }

//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                Map<String,String> params = new HashMap<String, String>();
//                params.put("Content-Type","application/json; charset=utf-8");
//                return params;
//            }
        };

        requestQueue.add(stringRequest);

//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST,Api.apiGetDirectionFromTree,null, new Response.Listener<JSONArray>() {
//            @Override
//            public void onResponse(JSONArray response) {
//                if (response.length() > 0) {
//                    try {
//
//                        ArrayList<Point> arrayList = new ArrayList<>();
//                        for (int i = 0; i < response.length(); i++) {
//
//                            JSONObject jsonObject = response.getJSONObject(i);
//                            Point point = new Point();
//                            point.setX(jsonObject.getInt("x"));
//                            point.setY(jsonObject.getInt("y"));
//                            arrayList.add(point);
//
//
//                        }
//
//                        getDirectionFromTreeSuccess(arrayList);
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//
//                }
//
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//                error.printStackTrace();
//                getDirectionFromTreeFail("Lỗi kết nối !!!!");
//
//            }
//        }) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                HashMap<String, String> hashMap = new HashMap<>();
//                hashMap.put("idThanhVien", idThanhVien);
//                hashMap.put("toaDoX", toaDoX);
//                hashMap.put("toaDoY", toaDoY);
//                hashMap.put("idCay", idCay);
//                return hashMap;
//            }
//
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                Map<String, String> params = new HashMap<String, String>();
//                params.put("Content-Type", "application/json; charset=utf-8");
//                return params;
//            }
//
//            @Override
//            public String getBodyContentType() {
//                return "application/json; charset=utf-8";
//            }
//
//        };
//
//        requestQueue.add(jsonArrayRequest);
    }


    @Override
    public void getDirectionFromTreeSuccess(ArrayList<Point> arrayList) {

        iMainView.showDirectionFromTree(arrayList);

    }

    @Override
    public void getDirectionFromTreeFail(String mess) {

        iMainView.showErrorDirectionFromTree(mess);

    }


    // tìm kiếm đường đi ngắn nhất đến điểm cấp nước đã biết trước
    public void getDirectionToDCn(Context context, final String idThanhVien, final String toaDoX, final String toaDoY, final String idDiemCapNuoc) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Api.apiGetDirectionFromDcn, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("LOI", response.toString());

                if (response.length() > 2) {
                    Type collectionType = new TypeToken<ArrayList<Point>>() {
                    }.getType();

                    ArrayList<Point> enums = new Gson().fromJson(response, collectionType);
                    getDirectionToDcnSucces(enums);


                } else {
                    getDirectionToDcnFail("Không tìm được");
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.e("LOI", error.toString());
                getDirectionToDcnFail("Lỗi kết nối");
                error.printStackTrace();


            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("idThanhVien", idThanhVien);
                hashMap.put("toaDoX", toaDoX);
                hashMap.put("toaDoY", toaDoY);
                hashMap.put("idDiemCapNuoc", idDiemCapNuoc);
                return hashMap;
            }

//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                Map<String,String> params = new HashMap<String, String>();
//                params.put("Content-Type","application/json; charset=utf-8");
//                return params;
//            }
        };

        requestQueue.add(stringRequest);

//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST,Api.apiGetDirectionFromTree,null, new Response.Listener<JSONArray>() {
//            @Override
//            public void onResponse(JSONArray response) {
//                if (response.length() > 0) {
//                    try {
//
//                        ArrayList<Point> arrayList = new ArrayList<>();
//                        for (int i = 0; i < response.length(); i++) {
//
//                            JSONObject jsonObject = response.getJSONObject(i);
//                            Point point = new Point();
//                            point.setX(jsonObject.getInt("x"));
//                            point.setY(jsonObject.getInt("y"));
//                            arrayList.add(point);
//
//
//                        }
//
//                        getDirectionFromTreeSuccess(arrayList);
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//
//                }
//
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//                error.printStackTrace();
//                getDirectionFromTreeFail("Lỗi kết nối !!!!");
//
//            }
//        }) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                HashMap<String, String> hashMap = new HashMap<>();
//                hashMap.put("idThanhVien", idThanhVien);
//                hashMap.put("toaDoX", toaDoX);
//                hashMap.put("toaDoY", toaDoY);
//                hashMap.put("idCay", idCay);
//                return hashMap;
//            }
//
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                Map<String, String> params = new HashMap<String, String>();
//                params.put("Content-Type", "application/json; charset=utf-8");
//                return params;
//            }
//
//            @Override
//            public String getBodyContentType() {
//                return "application/json; charset=utf-8";
//            }
//
//        };
//
//        requestQueue.add(jsonArrayRequest);
    }


    @Override
    public void getDirectionToDcnSucces(ArrayList<Point> arrayList) {

        iMainView.showDirectionToDcn(arrayList);

    }

    @Override
    public void getDirectionToDcnFail(String mess) {

        iMainView.showErrorDirectionToDcn(mess);

    }


    // tìm kiếm đường đi ngắn nhất đến cây ( trong 1 list)

    public void getDirectionFromListTrees(Context context, final String idThanhVien, final String toaDoX, final String toaDoY) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Api.apiGetDirectionFromListTrees, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("LOI", response.toString());

                if (response.length() > 2) {
                    Type collectionType = new TypeToken<ArrayList<Point>>() {
                    }.getType();

                    ArrayList<Point> enums = new Gson().fromJson(response, collectionType);
                    getDirectionFromListTreesSuccess(enums);


                } else {
                    getDirectionFromListTreesFail("Không tìm được");
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.e("LOI", error.toString());
                getDirectionFromListTreesFail("Lỗi kết nối");
                error.printStackTrace();


            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("idThanhVien", idThanhVien);
                hashMap.put("toaDoX", toaDoX);
                hashMap.put("toaDoY", toaDoY);
                return hashMap;
            }

//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                Map<String,String> params = new HashMap<String, String>();
//                params.put("Content-Type","application/json; charset=utf-8");
//                return params;
//            }
        };

        requestQueue.add(stringRequest);

    }

    @Override
    public void getDirectionFromListTreesSuccess(ArrayList<Point> arrayList) {
        iMainView.showDirectionFromListTrees(arrayList);

    }

    @Override
    public void getDirectionFromListTreesFail(String mess) {
        iMainView.showErrorDirectionFromListTrees(mess);

    }

    // tìm kiếm đường đi ngắn nhất đến dcn ( trong 1 list )

    public void getDirectionFromListWater(Context context, final String idThanhVien, final String toaDoX, final String toaDoY) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Api.apiGetDirectionFromListDcns, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("LOI", response.toString());

                if (response.length() > 2) {
                    Type collectionType = new TypeToken<ArrayList<Point>>() {
                    }.getType();

                    ArrayList<Point> enums = new Gson().fromJson(response, collectionType);
                    getDirectionFromListWaterSuccess(enums);


                } else {
                    getDirectionFromListWaterFail("Không tìm được");
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.e("LOI", error.toString());
                getDirectionFromListWaterFail("Lỗi kết nối");
                error.printStackTrace();


            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("idThanhVien", idThanhVien);
                hashMap.put("toaDoX", toaDoX);
                hashMap.put("toaDoY", toaDoY);
                return hashMap;
            }
        };

        requestQueue.add(stringRequest);


    }

    @Override
    public void getDirectionFromListWaterSuccess(ArrayList<Point> arrayList) {
        iMainView.showDirectionFromListWater(arrayList);

    }

    @Override
    public void getDirectionFromListWaterFail(String mess) {
        iMainView.showErrorDirectionFromListWater(mess);

    }


    // gui bao cao cay

    public void guiBaoCaoCay(Context context, final String idThanhVien, final String idCay, final String noiDung) {

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Api.apiBaoCaoCay, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response != null) {
                    NewResponse newResponse = new Gson().fromJson(response, NewResponse.class);
                    guiBaoCaoCayThanhCong(newResponse.getMessage());

                } else {
                    guiBaoCaoCayThatBai("Không gưi được đâu");
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                error.printStackTrace();
                guiBaoCaoCayThatBai("Lỗi kết nối :V thông cảm tý nào :V");

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("idThanhVien", idThanhVien);
                hashMap.put("idCay", idCay);
                hashMap.put("noiDung", noiDung);
                return hashMap;
            }
        };

        requestQueue.add(stringRequest);

    }

    @Override
    public void guiBaoCaoCayThanhCong(String mess) {
        iMainView.showKetQuaGuiBaoCao(mess);

    }

    @Override
    public void guiBaoCaoCayThatBai(String mess) {
        iMainView.showKetQuaGuiBaoCao(mess);


    }

    // gui bao cao dcn

    public void guiBaoCaoDcn(Context context, final String idThanhVien, final String idDiemCapNuoc, final String noiDung) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Api.apiBaoCaoDcn, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response != null) {
                    NewResponse newResponse = new Gson().fromJson(response, NewResponse.class);
                    guiBaoCaoDCnThanhCong(newResponse.getMessage());

                } else {
                    guiBaoCaoDcnThatBai("Không gửi được");
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                guiBaoCaoDcnThatBai("Lỗi kết nối");

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("idThanhVien", idThanhVien);
                hashMap.put("idDiemCapNuoc", idDiemCapNuoc);
                hashMap.put("noiDung", noiDung);
                return hashMap;
            }
        };

        requestQueue.add(stringRequest);


    }

    @Override
    public void guiBaoCaoDCnThanhCong(String mess) {

        iMainView.showKetQuaGuiBaoCao(mess);

    }

    @Override
    public void guiBaoCaoDcnThatBai(String mess) {

        iMainView.showKetQuaGuiBaoCao(mess);

    }


    // lay danh sach thanh vien dang online
    public void getListThanhVien(Context context) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Api.apiGetListThanhVien, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response.length() > 2) {
                    Type collectionType = new TypeToken<ArrayList<ThanhVien>>() {
                    }.getType();
                    ArrayList<ThanhVien> enums = new Gson().fromJson(response, collectionType);
                    getListThanhVienThanhCong(enums);

                } else {
                    getListThanhVienThatBai("Không có thành viên");
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                error.printStackTrace();
                getListThanhVienThatBai("Lỗi kết nối");

            }
        });

        requestQueue.add(stringRequest);
    }

    @Override
    public void getListThanhVienThanhCong(ArrayList<ThanhVien> arrayList) {
        iMainView.showListThanhVien(arrayList);

    }

    @Override
    public void getListThanhVienThatBai(String mess) {
        iMainView.showErrorGetListThanhVien(mess);

    }

    // lay nuoc

    public void layNuoc(Context context, final String idThanhVien, final String toaDoX, final String toaDoY, final String luongNuocMangTheo) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Api.apiCapNhatThanhVien, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response != null) {

                    NewResponse newResponse = new Gson().fromJson(response, NewResponse.class);


                    //        layNuocThanhCong("");

                } else {
                    layNuocThatBai(" Lỗi");
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                error.printStackTrace();
                layNuocThatBai("Lỗi kết nối");

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("idThanhVien", idThanhVien);
                hashMap.put("toaDoX", toaDoX);
                hashMap.put("toaDoY", toaDoY);
                hashMap.put("luongNuocMangTheo", luongNuocMangTheo);
                return hashMap;
            }
        };

        requestQueue.add(stringRequest);
    }

    @Override
    public void layNuocThanhCong(String mess) {

        iMainView.showKetQuaGuiBaoCao(mess);

    }

    @Override
    public void layNuocThatBai(String mess) {
        iMainView.showKetQuaGuiBaoCao(mess);

    }

    // tuoi nuoc cho cay

    public void tuoiNuoc(Context context, final String idThanhVien, final String idCay, final String luongNuoc) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Api.apiTuoiNuoc, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response != null) {
                    NewResponse newResponse = new Gson().fromJson(response, NewResponse.class);
                    if (newResponse.getCode().equals("200")) {
                        tuoiCayThanhCong(newResponse.getMessage());
                    } else {
                        tuoiCayThatBai(newResponse.getMessage());
                    }

                } else {
                    tuoiCayThatBai("Lỗi cập nhật cây.");
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                tuoiCayThatBai("Lỗi kết nối");
                error.printStackTrace();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("idThanhVien", idThanhVien);
                hashMap.put("idCay", idCay);
                hashMap.put("luongNuoc", luongNuoc);
                return hashMap;
            }
        };

        requestQueue.add(stringRequest);
    }


    @Override
    public void tuoiCayThanhCong(String mess) {

        iMainView.showKetQuaGuiBaoCao(mess);

    }

    @Override
    public void tuoiCayThatBai(String mess) {

        iMainView.showKetQuaGuiBaoCao(mess);

    }

    public void capNhatThanhVien(Context context, final String idThanhVien, final String toaDoX, final String toaDoY) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Api.apiCapNhatThanhVien, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response != null) {
                    NewResponse newResponse = new Gson().fromJson(response, NewResponse.class);


                } else {
                    capNhatThanhVienThatBai("Lỗi cập nhật.");
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                capNhatThanhVienThatBai("Lỗi kết nối");
                error.printStackTrace();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("idThanhVien", idThanhVien);
                hashMap.put("toaDoX", toaDoX);
                hashMap.put("toaDoY", toaDoY);
                return hashMap;
            }
        };

        requestQueue.add(stringRequest);

    }

    @Override
    public void capNhatThanhVienThanhCong(String mess) {

    }

    @Override
    public void capNhatThanhVienThatBai(String mess) {

    }


}
