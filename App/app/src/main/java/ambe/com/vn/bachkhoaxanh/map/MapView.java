package ambe.com.vn.bachkhoaxanh.map;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.animation.BounceInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.time.Clock;
import java.util.ArrayList;

import ambe.com.vn.bachkhoaxanh.R;
import ambe.com.vn.bachkhoaxanh.activities.main.IMainView;
import ambe.com.vn.bachkhoaxanh.activities.lichsutuoi.LichSuTuoiCayActivity;
import ambe.com.vn.bachkhoaxanh.activities.main.MainActivity;
import ambe.com.vn.bachkhoaxanh.models.Cay;
import ambe.com.vn.bachkhoaxanh.models.DiemCapNuoc;
import ambe.com.vn.bachkhoaxanh.models.Point;
import ambe.com.vn.bachkhoaxanh.models.ThanhVien;
import ambe.com.vn.bachkhoaxanh.models.UserMove;
import ambe.com.vn.bachkhoaxanh.presenters.main.MainPresenter;
import ambe.com.vn.bachkhoaxanh.utils.Api;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

/**
 * Created by AMBE on 18/03/2018.
 */

public class MapView extends View implements IMainView {
    private int x, y, bit;
    private float[] pts;
    private Path path;
    private String luongNuoc = "";

    private Socket socket;
    private ArrayList<UserMove> arrUserMove;


    void setUpSocket() {
        try {

            socket = IO.socket(Api.URL_SERVER_NODEJS);

        } catch (Exception e) {
            e.printStackTrace();
        }

        socket.on("add tree", addTree);
        socket.on("user login", userLogin);
        socket.on("user move", userMovie);

        socket.connect();

    }

    private Emitter.Listener userMovie = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            Log.e("LOI", args[0].toString());
            if (args[0].toString().length() > 0) {
                try {
                    arrUserMove.clear();
                    JSONObject jsonObject = (JSONObject) args[0];
                    UserMove userMove = new UserMove();
                    userMove.setId(jsonObject.getString("id"));
                    userMove.setX(jsonObject.getInt("x"));
                    userMove.setY(jsonObject.getInt("y"));
                    arrUserMove.add(userMove);
                    updateUserMove(arrUserMove);


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
    };

    private void updateUserMove(final ArrayList<UserMove> arrUserMove) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (ThanhVien thanhVien : arrThanhVien) {
                        for (UserMove userMove : arrUserMove) {
                            Thread.sleep(1000);
                            if (thanhVien.getIdThanhVien().equals(userMove.getId())) {
                                thanhVien.setToaDoX(userMove.getX() * 49);
                                thanhVien.setToaDoY(userMove.getY() * 49);
                                postInvalidate();
                            }
                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        }).start();
    }

    private Emitter.Listener userLogin = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            mainPresenter.getListThanhVien(getContext());

        }
    };

    private Emitter.Listener addTree = new Emitter.Listener() {
        @Override
        public void call(Object... args) {

            mainPresenter.getListCay(getContext());

        }
    };


    // map cho xiaomi redmi 5plus

    int[][] map = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 5, 5, 5, 5, 5, 5, 0, 0, 5, 5, 5, 5, 0, 0, 5, 5, 5, 5, 5, 5, 0},
            {0, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 0},
            {0, 4, 2, 2, 2, 2, 4, 2, 4, 5, 2, 2, 5, 4, 2, 2, 2, 2, 2, 4, 5, 0},
            {0, 4, 4, 4, 4, 2, 4, 2, 4, 5, 0, 0, 5, 4, 2, 4, 4, 4, 2, 4, 5, 0},
            {0, 4, 5, 5, 4, 2, 4, 2, 4, 5, 0, 0, 5, 4, 2, 4, 5, 4, 2, 4, 5, 0},
            {0, 4, 5, 5, 4, 2, 4, 2, 4, 5, 0, 0, 5, 4, 2, 4, 5, 4, 2, 4, 5, 0},
            {0, 4, 5, 5, 4, 2, 4, 2, 4, 5, 0, 0, 5, 4, 2, 4, 5, 4, 2, 4, 5, 0},
            {0, 4, 5, 5, 4, 2, 4, 2, 4, 5, 0, 0, 5, 4, 2, 4, 5, 4, 2, 4, 5, 0},
            {0, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 0},
            {0, 4, 2, 2, 2, 2, 2, 2, 4, 5, 5, 5, 5, 4, 2, 2, 2, 2, 2, 2, 2, 0},
            {0, 4, 2, 2, 2, 2, 2, 2, 4, 5, 5, 5, 5, 4, 2, 2, 2, 2, 2, 2, 2, 0},
            {0, 4, 5, 2, 5, 5, 2, 5, 4, 5, 5, 5, 5, 4, 4, 4, 4, 4, 4, 3, 3, 0},
            {0, 4, 5, 2, 5, 5, 2, 5, 4, 5, 5, 5, 5, 4, 5, 5, 5, 5, 5, 3, 3, 0},
            {0, 4, 5, 2, 5, 4, 4, 4, 4, 4, 4, 1, 4, 4, 2, 2, 2, 2, 2, 2, 2, 0},
            {0, 4, 5, 2, 5, 5, 2, 5, 4, 5, 0, 0, 5, 4, 2, 2, 2, 2, 2, 2, 2, 0},
            {0, 4, 5, 2, 5, 5, 2, 5, 4, 5, 0, 0, 5, 4, 4, 4, 4, 4, 4, 4, 5, 0},
            {0, 4, 2, 2, 2, 2, 2, 2, 4, 5, 0, 0, 5, 4, 2, 2, 2, 2, 2, 4, 5, 0},
            {0, 4, 2, 2, 2, 2, 2, 2, 4, 5, 0, 0, 5, 4, 4, 4, 4, 4, 2, 4, 5, 0},
            {0, 4, 4, 4, 4, 4, 4, 4, 4, 5, 0, 0, 5, 4, 5, 1, 1, 4, 2, 4, 5, 0},
            {0, 4, 5, 1, 5, 1, 5, 1, 4, 5, 0, 0, 5, 4, 5, 1, 1, 4, 2, 4, 5, 0},
            {0, 4, 4, 4, 4, 4, 4, 4, 4, 5, 0, 0, 5, 4, 5, 1, 1, 4, 2, 4, 5, 0},
            {0, 4, 2, 2, 2, 4, 2, 2, 4, 5, 5, 5, 5, 4, 4, 4, 4, 4, 2, 4, 5, 0},
            {0, 4, 2, 2, 2, 4, 2, 2, 4, 4, 4, 4, 4, 4, 2, 2, 2, 2, 2, 4, 5, 0},
            {0, 4, 4, 4, 4, 4, 4, 4, 4, 5, 1, 1, 5, 4, 4, 4, 4, 4, 4, 4, 5, 0},
            {0, 4, 2, 2, 2, 4, 2, 2, 4, 5, 0, 0, 5, 4, 5, 4, 2, 4, 5, 4, 5, 0},
            {0, 4, 2, 2, 2, 4, 2, 2, 4, 5, 0, 0, 5, 4, 5, 4, 2, 4, 5, 4, 5, 0},
            {0, 4, 4, 4, 4, 4, 4, 4, 4, 5, 0, 0, 5, 4, 5, 4, 2, 4, 5, 4, 5, 0},
            {0, 4, 2, 2, 2, 4, 2, 2, 4, 5, 0, 0, 5, 4, 1, 4, 2, 4, 1, 4, 5, 0},
            {0, 4, 2, 2, 2, 4, 2, 2, 4, 5, 0, 0, 5, 4, 5, 4, 2, 4, 5, 4, 5, 0},
            {0, 4, 5, 5, 5, 4, 5, 5, 4, 5, 0, 0, 5, 4, 5, 4, 2, 4, 5, 4, 5, 0},
            {0, 4, 2, 2, 2, 4, 2, 2, 4, 5, 0, 0, 5, 4, 5, 4, 2, 4, 5, 4, 5, 0},
            {0, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 0},
            {0, 3, 3, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 3, 3, 3, 5, 5, 5, 0},
            {0, 3, 3, 5, 5, 5, 5, 0, 0, 5, 5, 5, 5, 0, 0, 3, 3, 3, 5, 5, 5, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0},
    };
    ;


    private Paint p;
    private ArrayList<Bitmap> arrBitMap;
    public ArrayList<Cay> arrCay;
    private ArrayList<DiemCapNuoc> arrDiemCapNuoc;
    private ArrayList<ThanhVien> arrThanhVien;
    private RelativeLayout viewGroup;
    private MainPresenter mainPresenter;

    public MapView(Context context, int x, int y, int bit) {
        super(context);
        this.x = x;
        this.y = y;
        this.bit = bit;


    }


    public MapView(Context context) {
        super(context);


    }


    public MapView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        p = new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setColor(getResources().getColor(R.color.colorAccent));
        p.setStrokeWidth(24);
        p.setStyle(Paint.Style.STROKE);
        path = new Path();
        mainPresenter = new MainPresenter(this);
        setUpSocket();

        arrCay = new ArrayList<>();
        arrDiemCapNuoc = new ArrayList<>();
        arrThanhVien = new ArrayList<>();
        arrUserMove = new ArrayList<>();


        mainPresenter.getListCay(getContext());
        mainPresenter.getListDiemCapNuoc(getContext());
        mainPresenter.getListThanhVien(getContext());

        arrBitMap = new ArrayList<>();
        arrBitMap.add(BitmapFactory.decodeResource(getResources(), R.drawable.brick));
        arrBitMap.add(BitmapFactory.decodeResource(getResources(), R.drawable.tree));
        arrBitMap.add(BitmapFactory.decodeResource(getResources(), R.drawable.rock));
        arrBitMap.add(BitmapFactory.decodeResource(getResources(), R.drawable.water));


    }

    public MapView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


// i =26==34
        drawMap(canvas);
        drawDiemCapNuoc(canvas);
        drawTree(canvas);
        drawDirectionFrommTree(canvas);
        drawDirectionToDcn(canvas);
        drawDirectionFromListTrees(canvas);

        if (MainActivity.THANH_VIEN != null) {
            MainActivity.THANH_VIEN.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.user));

            canvas.drawBitmap(MainActivity.THANH_VIEN.getBitmap(), MainActivity.THANH_VIEN.getToaDoY() * 49, MainActivity.THANH_VIEN.getToaDoX() * 49, p);
        }
        drawThanhVien(canvas);


    }


    private void drawDirectionFromListTrees(Canvas canvas) {
        canvas.drawPath(path, p);
    }

    private void drawDirectionToDcn(Canvas canvas) {
        canvas.drawPath(path, p);
    }

    private void drawDirectionFrommTree(Canvas canvas) {

        canvas.drawPath(path, p);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float x = event.getX();
        float y = event.getY();

        float a = MainActivity.THANH_VIEN.getToaDoX() * 49;
        float b = MainActivity.THANH_VIEN.getToaDoY() * 49;

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //Check if the x and y position of the touch is inside the bitmap
                if (x <= b + 49 && x >= b && y <= a + 49 && y >= a) {

                    showInfoMe();
                }
                for (ThanhVien thanhVien : arrThanhVien) {

                    if (x >= thanhVien.getToaDoY() && x <= thanhVien.getToaDoY() + 49 && y > thanhVien.getToaDoX() && y <= thanhVien.getToaDoX() + 49) {
                        showInfoThanhVien(thanhVien);
                    }

                }

                for (DiemCapNuoc diemCapNuoc : arrDiemCapNuoc) {
                    if (x >= diemCapNuoc.getToaDoY() && x <= diemCapNuoc.getToaDoY() + 49 && y >= diemCapNuoc.getToaDoX() && y <= diemCapNuoc.getToaDoX() + 49) {
                        showInfoDiemCapNuoc(diemCapNuoc);

                    }
                }
                for (Cay cay : arrCay) {
                    if (x >= cay.getToaDoY() && x <= cay.getToaDoY() + 49 && y >= cay.getToaDoX() && y <= cay.getToaDoX() + 49) {
                        showInfoCay(cay);
                    }
                }
                return true;
        }
        return false;

    }

    private void showInfoThanhVien(ThanhVien thanhVien) {
        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_thanh_vien);

        viewGroup = dialog.findViewById(R.id.rll_dialog_thanh_vien);
        TextView txtIdThanhVien = dialog.findViewById(R.id.txt_id_thanh_vien);
        TextView txtLuongNuoc = dialog.findViewById(R.id.txt_luong_nuoc_mang_theo);
        ImageView imgAvatar = dialog.findViewById(R.id.img_avtar_tv);
        TextView txtDong = dialog.findViewById(R.id.txt_dong_dialog_tv);
        TextView txtTenTv = dialog.findViewById(R.id.txt_ten_tv_dialog);

        animateDialog();

        txtIdThanhVien.setText(thanhVien.getIdThanhVien());
        txtLuongNuoc.setText(thanhVien.getLuongNuocMangTheo() + " Lit");
        String urlAvatar = "http://" + Api.ip + ":9999" + thanhVien.getAnhThanhVien();
        Picasso.with(getContext()).load(urlAvatar).error(R.drawable.unknown_user).into(imgAvatar);
        txtTenTv.setText(thanhVien.getTenDayDu());

        txtDong.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });


        dialog.show();


    }

    private void showInfoMe() {

        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_thanh_vien);

        viewGroup = dialog.findViewById(R.id.rll_dialog_thanh_vien);
        TextView txtIdThanhVien = dialog.findViewById(R.id.txt_id_thanh_vien);
        TextView txtLuongNuoc = dialog.findViewById(R.id.txt_luong_nuoc_mang_theo);
        ImageView imgAvatar = dialog.findViewById(R.id.img_avtar_tv);
        TextView txtDong = dialog.findViewById(R.id.txt_dong_dialog_tv);
        TextView txtTenTv = dialog.findViewById(R.id.txt_ten_tv_dialog);
        animateDialog();

        txtIdThanhVien.setText(MainActivity.THANH_VIEN.getIdThanhVien());
        txtLuongNuoc.setText(MainActivity.THANH_VIEN.getLuongNuocMangTheo() + " Lit");
        String urlAvatar = "http://" + Api.ip + ":9999" + MainActivity.THANH_VIEN.getAnhThanhVien();
        Picasso.with(getContext()).load(urlAvatar).error(R.drawable.unknown_user).into(imgAvatar);
        txtTenTv.setText("Tôi");


        txtDong.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });


        dialog.show();


    }

    private void showInfoCay(final Cay cay) {
        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_cay);
        viewGroup = dialog.findViewById(R.id.rll_dialog_cay);
        TextView txtIdCay = dialog.findViewById(R.id.txt_id_cay);
        final TextView txtTinhTrangCay = dialog.findViewById(R.id.txt_tinh_trang_cay);
        final TextView txtLuongNuocCan = dialog.findViewById(R.id.txt_luong_nuoc_can);
        TextView txtLuongNuocToiDa = dialog.findViewById(R.id.txt_luong_nuoc_toi_da);
        SeekBar seekBar = dialog.findViewById(R.id.seek_bar);
        Button btnChiDuong = dialog.findViewById(R.id.btn_chi_duong);
        final Button btnDaTuoi = dialog.findViewById(R.id.btn_da_tuoi);
        ImageView imgCay = dialog.findViewById(R.id.img_cay_dialog);
        Button btnBaoCao = dialog.findViewById(R.id.btn_bao_cao);
        ImageView imgLichSu = dialog.findViewById(R.id.img_lich_su_tuoi);
        animateDialog();


        txtIdCay.setText(cay.getIdCay());
        txtTinhTrangCay.setText(cay.getTinhTrang());
        txtLuongNuocCan.setText((cay.getLuongNuocToiDa() - cay.getLuongNuocDaTuoi()) + " Lít");
        txtLuongNuocToiDa.setText(cay.getLuongNuocToiDa() + " Lít");

        String urlAvatar = "http://" + Api.ip + ":9999" + cay.getLoaiCayObject().getAnhLoaiCay();
        Picasso.with(getContext()).load(urlAvatar).error(R.drawable.bk).into(imgCay);
        btnDaTuoi.setEnabled(true);


        seekBar.setMax(cay.getLuongNuocToiDa());
        seekBar.setProgress(cay.getLuongNuocDaTuoi());
        seekBar.setPressed(false);


        if (cay.getLuongNuocToiDa() - cay.getLuongNuocDaTuoi() == 0) {
            btnDaTuoi.setEnabled(false);

        } else {
            btnDaTuoi.setEnabled(true);
        }

        btnDaTuoi.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {


                btnDaTuoi.setEnabled(false);
                xuLyTuoiCay(cay);
                final int luongNuocCanTuoi = cay.getLuongNuocToiDa() - cay.getLuongNuocDaTuoi();
                if (luongNuocCanTuoi > MainActivity.THANH_VIEN.getLuongNuocMangTheo()) {

                    ((Activity) getContext()).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mainPresenter.tuoiNuoc(getContext(), MainActivity.THANH_VIEN.getIdThanhVien(), cay.getIdCay(), String.valueOf(MainActivity.THANH_VIEN.getLuongNuocMangTheo()));
                            MainActivity.THANH_VIEN.setLuongNuocMangTheo(0);

                            mainPresenter.layNuoc(getContext(),
                                    MainActivity.THANH_VIEN.getIdThanhVien(),
                                    String.valueOf(MainActivity.THANH_VIEN.getToaDoX()),
                                    String.valueOf(MainActivity.THANH_VIEN.getToaDoY()),
                                    String.valueOf(MainActivity.THANH_VIEN.getLuongNuocMangTheo()));
                        }
                    });


                } else {

                }
                dialog.dismiss();

            }
        });

        imgLichSu.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getContext(), LichSuTuoiCayActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("IDCAY", cay.getIdCay());
                intent.putExtra("BUNDLE", bundle);
                getContext().startActivity(intent);

            }
        });

        btnChiDuong.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                xuLyChiDuong(cay);
                dialog.dismiss();
            }
        });

        btnBaoCao.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                xuLyBaoCao(cay);
                dialog.dismiss();
            }
        });


        dialog.show();


    }

    private void xuLyTuoiCay(Cay cay) {


    }

    private void showInfoDiemCapNuoc(final DiemCapNuoc diemCapNuoc) {


        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_diem_cap_nuoc);
        viewGroup = dialog.findViewById(R.id.rll_dialog_dcn);

        TextView txtTenDcn = dialog.findViewById(R.id.txt_id_dcn);
        TextView txtTinhTrang = dialog.findViewById(R.id.txt_tinh_trang_dcn);
        Spinner spinner = dialog.findViewById(R.id.txt_spinner);
        Button btnChiDuong = dialog.findViewById(R.id.btn_chi_duong_dcn);
        Button btnLayNuoc = dialog.findViewById(R.id.btn_lay_nuoc);
        Button btnBaoCao = dialog.findViewById(R.id.btn_bao_cao_dcn);

        final ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(4);
        arrayList.add(5);
        arrayList.add(10);
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(
                getContext(),
                android.R.layout.simple_list_item_1,
                arrayList
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        animateDialog();


        txtTenDcn.setText(diemCapNuoc.getIdDiemCapNuoc());
        txtTinhTrang.setText(diemCapNuoc.getTinhTrang());

        btnChiDuong.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                xuLyChiDuongDenDcn(diemCapNuoc);
                dialog.dismiss();
            }
        });

        btnBaoCao.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                xuLyBaoCaoDcn(diemCapNuoc);
                dialog.dismiss();
            }
        });


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                luongNuoc = String.valueOf(arrayList.get(i));

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnLayNuoc.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                //           Toast.makeText(getContext(), luongNuoc + "Lit", Toast.LENGTH_SHORT).show();
                MainActivity.THANH_VIEN.setLuongNuocMangTheo(MainActivity.THANH_VIEN.getLuongNuocMangTheo() + Integer.parseInt(luongNuoc));
                mainPresenter.layNuoc(getContext(), MainActivity.THANH_VIEN.getIdThanhVien(), String.valueOf(MainActivity.THANH_VIEN.getToaDoX()), String.valueOf(MainActivity.THANH_VIEN.getToaDoY()), luongNuoc);
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void xuLyBaoCaoDcn(final DiemCapNuoc diemCapNuoc) {
        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_bao_cao);

        Button btnDong = dialog.findViewById(R.id.btn_dong_bao_cao);
        Button btnGui = dialog.findViewById(R.id.btn_guI_bao_cao);
        final EditText editNoiDung = dialog.findViewById(R.id.edit_noi_dung_bao_cao);

        btnDong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        btnGui.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String noiDung = editNoiDung.getText().toString().trim();

                if (!noiDung.equals("")) {
                    if (MainActivity.THANH_VIEN.getIdThanhVien() != null) {
                        mainPresenter.guiBaoCaoDcn(getContext(), MainActivity.THANH_VIEN.getIdThanhVien(), diemCapNuoc.getIdDiemCapNuoc(), noiDung);
                    } else {
                        mainPresenter.guiBaoCaoDcn(getContext(), "unKnow", diemCapNuoc.getIdDiemCapNuoc(), noiDung);
                    }
                    dialog.dismiss();
                } else {
                    Toast.makeText(getContext(), "Bạn phải nhập nội dung", Toast.LENGTH_SHORT).show();
                }


            }
        });

        viewGroup = dialog.findViewById(R.id.rll_dialog_bao_cao);
        animateDialog();
        dialog.show();
    }

    private void xuLyBaoCao(final Cay cay) {
        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_bao_cao);

        Button btnDong = dialog.findViewById(R.id.btn_dong_bao_cao);
        Button btnGui = dialog.findViewById(R.id.btn_guI_bao_cao);
        final EditText editNoiDung = dialog.findViewById(R.id.edit_noi_dung_bao_cao);

        btnDong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        btnGui.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String noiDung = editNoiDung.getText().toString().trim();

                if (!noiDung.equals("")) {
                    if (MainActivity.THANH_VIEN.getIdThanhVien() != null) {
                        mainPresenter.guiBaoCaoCay(getContext(), MainActivity.THANH_VIEN.getIdThanhVien(), cay.getIdCay(), noiDung);
                    } else {
                        mainPresenter.guiBaoCaoCay(getContext(), "unKnow", cay.getIdCay(), noiDung);
                    }
                    dialog.dismiss();
                } else {
                    Toast.makeText(getContext(), "Bạn phải nhập nội dung", Toast.LENGTH_SHORT).show();
                }


            }
        });

        viewGroup = dialog.findViewById(R.id.rll_dialog_bao_cao);
        animateDialog();
        dialog.show();
    }

    private void xuLyChiDuong(Cay cay) {
        if (MainActivity.THANH_VIEN != null) {
            mainPresenter.getDirectionFromTree(getContext(), MainActivity.THANH_VIEN.getIdThanhVien(), String.valueOf(MainActivity.THANH_VIEN.getToaDoX()), String.valueOf(MainActivity.THANH_VIEN.getToaDoY()), cay.getIdCay());

            invalidate();
        } else {
            Toast.makeText(getContext(), "ABCD", Toast.LENGTH_SHORT).show();
        }
    }

    public void xuLyChiDuongTuListCay() {
        if (MainActivity.THANH_VIEN != null) {
            mainPresenter.getDirectionFromListTrees(getContext(), MainActivity.THANH_VIEN.getIdThanhVien(), String.valueOf(MainActivity.THANH_VIEN.getToaDoX()), String.valueOf(MainActivity.THANH_VIEN.getToaDoY()));

            invalidate();
        }
    }

    public void xuLyChiDuongTuListDcn() {
        if (MainActivity.THANH_VIEN != null) {
            mainPresenter.getDirectionFromListWater(getContext(), MainActivity.THANH_VIEN.getIdThanhVien(), String.valueOf(MainActivity.THANH_VIEN.getToaDoX()), String.valueOf(MainActivity.THANH_VIEN.getToaDoY()));
            invalidate();
        }

    }

    private void xuLyChiDuongDenDcn(DiemCapNuoc diemCapNuoc) {
        if (MainActivity.THANH_VIEN != null) {
            mainPresenter.getDirectionToDCn(getContext(), MainActivity.THANH_VIEN.getIdThanhVien(), String.valueOf(MainActivity.THANH_VIEN.getToaDoX()), String.valueOf(MainActivity.THANH_VIEN.getToaDoY()), diemCapNuoc.getIdDiemCapNuoc());
        }
        invalidate();
    }

    private void animateDialog() {
        AnimatorSet set = new AnimatorSet();
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(viewGroup, "ScaleX", 0.7f, 1);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(viewGroup, "ScaleY", 0.7f, 1);
        set.playTogether(animatorX, animatorY);
        set.setInterpolator(new BounceInterpolator());
        set.setDuration(500);
        set.start();
    }

    @Override
    public void showListCay(ArrayList<Cay> arrayList) {

        for (Cay cay : arrayList) {
            cay.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.tree));
            cay.setToaDoX(cay.getToaDoX() * 49);
            cay.setToaDoY(cay.getToaDoY() * 49);
            arrCay.add(cay);

        }
        invalidate();

    }

    @Override
    public void showListThanhVien(ArrayList<ThanhVien> arrayList) {
        if (MainActivity.THANH_VIEN != null) {
            for (ThanhVien thanhVien : arrayList) {
                if (!thanhVien.getIdThanhVien().trim().equals(MainActivity.THANH_VIEN.getIdThanhVien().trim()) && thanhVien.getTrangThai().equals("on")) {
                    thanhVien.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.avatar));
                    thanhVien.setToaDoY(thanhVien.getToaDoY() * 49);
                    thanhVien.setToaDoX(thanhVien.getToaDoX() * 49);
                    arrThanhVien.add(thanhVien);
                }

            }
            invalidate();
        }

    }

    @Override
    public void showErrorGetListTree(String mess) {
        Toast.makeText(getContext(), mess, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showListDiemCapNuoc(ArrayList<DiemCapNuoc> arrayList) {

        for (DiemCapNuoc diemCapNuoc : arrayList) {
            diemCapNuoc.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.water));
            diemCapNuoc.setToaDoX(diemCapNuoc.getToaDoX() * 49);
            diemCapNuoc.setToaDoY(diemCapNuoc.getToaDoY() * 49);
            arrDiemCapNuoc.add(diemCapNuoc);
        }

        invalidate();

    }

    @Override
    public void showErrorGetListDiemCapNuoc(String mess) {

        Toast.makeText(getContext(), mess, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void logoutSuccess(String mess) {

    }

    @Override
    public void logoutFail(String mess) {

    }

    @Override
    public void showDirectionFromTree(ArrayList<Point> arrayList) {


        path.moveTo(arrayList.get(0).getY() * 49 + 24, arrayList.get(0).getX() * 49 + 24);
        for (int i = 1; i < arrayList.size(); i++) {
            path.lineTo(arrayList.get(i).getY() * 49 + 24, arrayList.get(i).getX() * 49 + 24);
        }

        invalidate();
        move(arrayList);

    }

    @Override
    public void showErrorDirectionFromTree(String mess) {

        Toast.makeText(getContext(), mess, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showDirectionToDcn(ArrayList<Point> arrayList) {
        path.moveTo(arrayList.get(0).getY() * 49 + 24, arrayList.get(0).getX() * 49 + 24);
        for (int i = 1; i < arrayList.size(); i++) {
            path.lineTo(arrayList.get(i).getY() * 49 + 24, arrayList.get(i).getX() * 49 + 24);
        }

        invalidate();
        move(arrayList);

    }

    @Override
    public void showErrorDirectionToDcn(String mess) {

        Toast.makeText(getContext(), mess, Toast.LENGTH_SHORT).show();


    }

    @Override
    public void showDirectionFromListTrees(ArrayList<Point> arrayList) {

        path.moveTo(arrayList.get(0).getY() * 49 + 24, arrayList.get(0).getX() * 49 + 24);
        for (int i = 1; i < arrayList.size(); i++) {
            path.lineTo(arrayList.get(i).getY() * 49 + 24, arrayList.get(i).getX() * 49 + 24);
        }

        invalidate();


        move(arrayList);


    }

    private void move(final ArrayList<Point> arrayList) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = arrayList.size() - 1; i >= 0; i--) {
                        Thread.sleep(1000);
                        MainActivity.THANH_VIEN.setToaDoX(arrayList.get(i).getX());
                        MainActivity.THANH_VIEN.setToaDoY(arrayList.get(i).getY());
                        socket.emit("user move", MainActivity.THANH_VIEN.getIdThanhVien(), MainActivity.THANH_VIEN.getToaDoX(), String.valueOf(MainActivity.THANH_VIEN.getToaDoY()));

                        postInvalidate();
                    }
                    mainPresenter.capNhatThanhVien(getContext(),
                            MainActivity.THANH_VIEN.getIdThanhVien(),
                            String.valueOf(MainActivity.THANH_VIEN.getToaDoX()),
                            String.valueOf(MainActivity.THANH_VIEN.getToaDoY()));


                    path.reset();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    @Override
    public void showErrorDirectionFromListTrees(String mess) {

        Toast.makeText(getContext(), mess, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showDirectionFromListWater(ArrayList<Point> arrayList) {
        path.moveTo(arrayList.get(0).getY() * 49 + 24, arrayList.get(0).getX() * 49 + 24);
        for (int i = 1; i < arrayList.size(); i++) {
            path.lineTo(arrayList.get(i).getY() * 49 + 24, arrayList.get(i).getX() * 49 + 24);
        }

        invalidate();
        move(arrayList);


    }

    @Override
    public void showErrorDirectionFromListWater(String mess) {
        Toast.makeText(getContext(), mess, Toast.LENGTH_SHORT).show();


    }

    @Override
    public void showKetQuaGuiBaoCao(String mess) {

        Toast.makeText(getContext(), mess, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showErrorGetListThanhVien(String mess) {
        Toast.makeText(getContext(), mess, Toast.LENGTH_SHORT).show();

    }


    private void drawTree(Canvas canvas) {
        for (Cay cay : arrCay) {
            canvas.drawBitmap(cay.getBitmap(), cay.getToaDoY(), cay.getToaDoX(), p);
        }

    }

    private void drawDiemCapNuoc(Canvas canvas) {
        for (DiemCapNuoc diemCapNuoc : arrDiemCapNuoc) {
            canvas.drawBitmap(diemCapNuoc.getBitmap(), diemCapNuoc.getToaDoY(), diemCapNuoc.getToaDoX(), p);
        }

    }

    private void drawMap(Canvas canvas) {
        for (int i = 0; i < 36; i++) {
            for (int j = 0; j < 22; j++) {
                int x = j * 49;
                int y = i * 49;
                int bit = map[i][j];
                if (bit < 4) {
                    canvas.drawBitmap(arrBitMap.get(bit), x, y, p);
                }


            }
        }
    }

    private void drawThanhVien(Canvas canvas) {
        for (ThanhVien thanhVien : arrThanhVien) {
            canvas.drawBitmap(thanhVien.getBitmap(), thanhVien.getToaDoY(), thanhVien.getToaDoX(), p);
        }

    }


}
