package ambe.com.vn.bachkhoaxanh.presenters.main;

import com.android.volley.toolbox.StringRequest;

import java.util.ArrayList;

import ambe.com.vn.bachkhoaxanh.models.Cay;
import ambe.com.vn.bachkhoaxanh.models.DiemCapNuoc;
import ambe.com.vn.bachkhoaxanh.models.Point;
import ambe.com.vn.bachkhoaxanh.models.ThanhVien;

/**
 * Created by AMBE on 09/04/2018.
 */

public interface IMainPresenter {
    void getListTreeSucess(ArrayList<Cay> arrayList);

    void getListTreeFail(String mess);

    void getListDiemCapNuocSuccess(ArrayList<DiemCapNuoc> arrayList);

    void getListDiemCapNuocFail(String mess);

    void onLogoutSuccess(String mess);

    void onLogoutFail(String mess);

    void getDirectionFromTreeSuccess(ArrayList<Point> arrayList);

    void getDirectionFromTreeFail(String mess);

    void getDirectionToDcnSucces(ArrayList<Point> arrayList);

    void getDirectionToDcnFail(String mess);

    void getDirectionFromListTreesSuccess(ArrayList<Point> arrayList);

    void getDirectionFromListTreesFail(String mess);


    void getDirectionFromListWaterSuccess(ArrayList<Point> arrayList);

    void getDirectionFromListWaterFail(String mess);

    void guiBaoCaoCayThanhCong(String mess);

    void guiBaoCaoCayThatBai(String mess);

    void guiBaoCaoDCnThanhCong(String mess);

    void guiBaoCaoDcnThatBai(String mess);

    void getListThanhVienThanhCong(ArrayList<ThanhVien> arrayList);

    void getListThanhVienThatBai(String mess);

    void layNuocThanhCong(String mess);

    void layNuocThatBai(String mess);

    void tuoiCayThanhCong(String mess);

    void tuoiCayThatBai(String mess);

    void capNhatThanhVienThanhCong(String mess);

    void capNhatThanhVienThatBai(String mess);


}
