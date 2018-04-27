package ambe.com.vn.bachkhoaxanh.presenters.lichsutuoi;

import java.util.ArrayList;

import ambe.com.vn.bachkhoaxanh.models.LichSuTuoiCay;

/**
 * Created by AMBE on 10/04/2018.
 */

public interface ILichSuTuoiCayPresenter {
    void getListLichSuTuoiSuccess(ArrayList<LichSuTuoiCay> arrayList);

    void getListLichSuTuoiFail(String mess);



}
