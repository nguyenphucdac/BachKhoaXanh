package ambe.com.vn.bachkhoaxanh.presenters.login;

import android.content.Context;

import ambe.com.vn.bachkhoaxanh.models.ThanhVien;

/**
 * Created by AMBE on 10/04/2018.
 */

public interface ILoginPresenter {
    void loginSuccess(ThanhVien thanhVien);
    void loginFail(String mess);

}
