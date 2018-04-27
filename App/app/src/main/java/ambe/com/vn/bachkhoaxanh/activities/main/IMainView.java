package ambe.com.vn.bachkhoaxanh.activities.main;

import java.util.ArrayList;

import ambe.com.vn.bachkhoaxanh.models.Cay;
import ambe.com.vn.bachkhoaxanh.models.DiemCapNuoc;
import ambe.com.vn.bachkhoaxanh.models.Point;
import ambe.com.vn.bachkhoaxanh.models.ThanhVien;

/**
 * Created by AMBE on 09/04/2018.
 */

public interface IMainView {

    void showListCay(ArrayList<Cay> arrayList);

    void showErrorGetListTree(String mess);

    void showListDiemCapNuoc(ArrayList<DiemCapNuoc> arrayList);

    void showErrorGetListDiemCapNuoc(String mess);

    void logoutSuccess(String mess);

    void logoutFail(String mess);

    void showDirectionFromTree(ArrayList<Point> arrayList);

    void showErrorDirectionFromTree(String mess);

    void showDirectionToDcn(ArrayList<Point> arrayList);

    void showErrorDirectionToDcn(String mess);

    void showDirectionFromListTrees(ArrayList<Point> arrayList);

    void showErrorDirectionFromListTrees(String mess);

    void showDirectionFromListWater(ArrayList<Point> arrayList);

    void showErrorDirectionFromListWater(String mess);

    void showKetQuaGuiBaoCao(String mess);

    void showListThanhVien(ArrayList<ThanhVien> arrayList);

    void showErrorGetListThanhVien(String mess);


}
