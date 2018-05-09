package ambe.com.vn.bachkhoaxanh.presenters.register;

import ambe.com.vn.bachkhoaxanh.models.ThanhVien;

/**
 * Created by AMBE on 09/05/2018.
 */

public interface IRegisterPresenter {
    void registerSuccess(String mess);

    void registerFail(String mess);
}
