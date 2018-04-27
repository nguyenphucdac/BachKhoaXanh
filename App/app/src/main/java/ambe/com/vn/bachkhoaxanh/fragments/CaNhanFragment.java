package ambe.com.vn.bachkhoaxanh.fragments;


import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import ambe.com.vn.bachkhoaxanh.R;
import ambe.com.vn.bachkhoaxanh.activities.login.LoginActivity;
import ambe.com.vn.bachkhoaxanh.activities.main.IMainView;
import ambe.com.vn.bachkhoaxanh.activities.main.MainActivity;
import ambe.com.vn.bachkhoaxanh.models.Cay;
import ambe.com.vn.bachkhoaxanh.models.DiemCapNuoc;
import ambe.com.vn.bachkhoaxanh.models.Point;
import ambe.com.vn.bachkhoaxanh.models.ThanhVien;
import ambe.com.vn.bachkhoaxanh.presenters.main.MainPresenter;
import ambe.com.vn.bachkhoaxanh.utils.Api;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class CaNhanFragment extends Fragment implements View.OnClickListener, IMainView {

    private View view;
    private CircleImageView imgAvatar;
    private TextView txtTenNv;
    private TextView txtId;
    private TextView txtAcc;
    private TextView txtBaoCaoCn;
    private TextView txtDoiMatKhau;
    private TextView txtDangXuat;
    private EditText editPass;
    private ImageView imgShowPass;
    private int show = 1;
    private RelativeLayout viewGroup;
    private RelativeLayout rllCaNhanTv;
    private LinearLayout lnlCaNhanTnv;
    private Button btnDangNhapTnv;
    private MainPresenter mainPresenter;


    public CaNhanFragment() {
        // Required empty public constructor
    }


    public static CaNhanFragment newInstance() {
        CaNhanFragment caNhanFragment = new CaNhanFragment();
        return caNhanFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_ca_nhan, container, false);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        txtTenNv = view.findViewById(R.id.txt_ten_nv);
        imgAvatar = view.findViewById(R.id.img_avatar_nv);
        txtId = view.findViewById(R.id.txt_ma_nv);
        txtAcc = view.findViewById(R.id.txt_tai_khoan);
        editPass = view.findViewById(R.id.edit_pass);
        imgShowPass = view.findViewById(R.id.img_show_pass);
        txtBaoCaoCn = view.findViewById(R.id.txt_bao_cao_cn);
        txtDoiMatKhau = view.findViewById(R.id.txt_doi_mat_khau);
        txtDangXuat = view.findViewById(R.id.txt_dang_xuat);
        rllCaNhanTv = view.findViewById(R.id.rll_ca_nhan_tv);
        lnlCaNhanTnv = view.findViewById(R.id.lnl_ca_nhan_tnv);
        btnDangNhapTnv = view.findViewById(R.id.btn_tnv_ca_nhan_dang_nhap);

        if (MainActivity.TNV.equals("TNV")) {
            rllCaNhanTv.setVisibility(View.GONE);
            lnlCaNhanTnv.setVisibility(View.VISIBLE);
        } else {
            rllCaNhanTv.setVisibility(View.VISIBLE);
            lnlCaNhanTnv.setVisibility(View.GONE);
        }


        mainPresenter = new MainPresenter(this);

        showInfoThanhVien();

        imgShowPass.setOnClickListener(this);
        txtBaoCaoCn.setOnClickListener(this);
        txtDoiMatKhau.setOnClickListener(this);
        txtDangXuat.setOnClickListener(this);
        btnDangNhapTnv.setOnClickListener(this);
    }

    private void showInfoThanhVien() {

        if (MainActivity.THANH_VIEN != null) {

            txtTenNv.setText(MainActivity.THANH_VIEN.getTenDayDu());
            txtId.setText(MainActivity.THANH_VIEN.getIdThanhVien());
            txtAcc.setText(MainActivity.THANH_VIEN.getTenTaiKhoan());
            editPass.setText(MainActivity.THANH_VIEN.getMatKhau());

            String urlAvatar = "http://" + Api.ip + ":9999" + MainActivity.THANH_VIEN.getAnhThanhVien();
            Log.d("LOI",urlAvatar);

            Picasso.with(getContext()).load(urlAvatar).error(R.drawable.unknown_user).into(imgAvatar);
        } else {

        }
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
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_show_pass:
                xuLyShowPass();
                break;
            case R.id.txt_bao_cao_cn:
                xuLyGuiBaoCao();
                break;
            case R.id.txt_doi_mat_khau:
                xuLyDoiMatKhau();
                break;
            case R.id.txt_dang_xuat:
                xuLyDangXuat();
                break;
            case R.id.btn_tnv_ca_nhan_dang_nhap:
                xuLyDangNhap();
                break;
        }

    }

    private void xuLyDangNhap() {
        Intent intent = new Intent(getContext(), LoginActivity.class);
        startActivity(intent);
    }

    private void xuLyDangXuat() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Hỏi tý ?");
        builder.setMessage("Muốn đăng xuất à !!!!!");
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                mainPresenter.logout(getContext(), MainActivity.THANH_VIEN.getIdThanhVien());


            }
        });

        builder.create().show();

    }

    private void xuLyDoiMatKhau() {

        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_doi_mat_khau);
        EditText editMkCu = dialog.findViewById(R.id.edit_mat_khau_cu);
        EditText editMkMoi = dialog.findViewById(R.id.edit_mat_khau_moi);
        EditText editNhapLaiMk = dialog.findViewById(R.id.edit_mat_khau_moi_nhap_lai);
        Button btnDong = dialog.findViewById(R.id.btn_dong_doi_mk);
        Button btnDongY = dialog.findViewById(R.id.btn_dong_y_doi_mk);
        viewGroup = dialog.findViewById(R.id.rll_dialog_doi_mk);

        btnDong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        animateDialog();

        dialog.show();

    }

    private void xuLyGuiBaoCao() {
        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_bao_cao);

        Button btnDong = dialog.findViewById(R.id.btn_dong_bao_cao);
        Button btnGui = dialog.findViewById(R.id.btn_guI_bao_cao);

        btnDong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        viewGroup = dialog.findViewById(R.id.rll_dialog_bao_cao);
        animateDialog();
        dialog.show();
    }

    private void xuLyShowPass() {
        if (show == 1) {
            show = 0;
            imgShowPass.setImageResource(R.drawable.show);
            editPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
        } else if (show == 0) {
            show = 1;
            imgShowPass.setImageResource(R.drawable.hide);
            editPass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        }
    }


    @Override
    public void showListCay(ArrayList<Cay> arrayList) {

    }

    @Override
    public void showErrorGetListTree(String mess) {

    }

    @Override
    public void showListDiemCapNuoc(ArrayList<DiemCapNuoc> arrayList) {

    }

    @Override
    public void showErrorGetListDiemCapNuoc(String mess) {

    }

    @Override
    public void logoutSuccess(String mess) {

        Toast.makeText(getContext(), mess, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getContext(), LoginActivity.class);
        startActivity(intent);

    }

    @Override
    public void logoutFail(String mess) {

        Toast.makeText(getContext(), mess, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showDirectionFromTree(ArrayList<Point> arrayList) {

    }

    @Override
    public void showErrorDirectionFromTree(String mess) {

    }

    @Override
    public void showDirectionToDcn(ArrayList<Point> arrayList) {

    }

    @Override
    public void showErrorDirectionToDcn(String mess) {

    }

    @Override
    public void showDirectionFromListTrees(ArrayList<Point> arrayList) {

    }

    @Override
    public void showErrorDirectionFromListTrees(String mess) {

    }

    @Override
    public void showDirectionFromListWater(ArrayList<Point> arrayList) {

    }

    @Override
    public void showErrorDirectionFromListWater(String mess) {

    }

    @Override
    public void showKetQuaGuiBaoCao(String mess) {

    }

    @Override
    public void showListThanhVien(ArrayList<ThanhVien> arrayList) {

    }

    @Override
    public void showErrorGetListThanhVien(String mess) {

    }
}
