package ambe.com.vn.bachkhoaxanh.models;

import java.io.Serializable;

/**
 * Created by AMBE on 18/03/2018.
 */

public class LoaiCay implements Serializable {
    private String idLoaiCay;
    private String tenLoaiCay;

    public LoaiCay(String idLoaiCay, String tenLoaiCay) {
        this.idLoaiCay = idLoaiCay;
        this.tenLoaiCay = tenLoaiCay;
    }

    public String getIdLoaiCay() {
        return idLoaiCay;
    }

    public void setIdLoaiCay(String idLoaiCay) {
        this.idLoaiCay = idLoaiCay;
    }

    public String getTenLoaiCay() {
        return tenLoaiCay;
    }

    public void setTenLoaiCay(String tenLoaiCay) {
        this.tenLoaiCay = tenLoaiCay;
    }
}
