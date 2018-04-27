package ambe.com.vn.bachkhoaxanh.activities.login;

import ambe.com.vn.bachkhoaxanh.models.ThanhVien;

/**
 * Created by AMBE on 10/04/2018.
 */

public interface ILoginView {
    void onLoginSuccess(ThanhVien thanhVien);

    void onLoginFail(String mess);
}
