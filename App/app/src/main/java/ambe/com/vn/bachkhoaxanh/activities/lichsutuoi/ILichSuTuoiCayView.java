package ambe.com.vn.bachkhoaxanh.activities.lichsutuoi;

import java.util.ArrayList;

import ambe.com.vn.bachkhoaxanh.models.LichSuTuoiCay;

/**
 * Created by AMBE on 10/04/2018.
 */

public interface ILichSuTuoiCayView {
    void showListLichSuTuoi(ArrayList<LichSuTuoiCay> arrayList);

    void showListLichSuTuoiFail(String mess);
}
