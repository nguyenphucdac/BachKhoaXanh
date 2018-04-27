package ambe.com.vn.bachkhoaxanh.models;

import java.io.Serializable;

/**
 * Created by AMBE on 18/03/2018.
 */

public class LoaiCayObject implements Serializable {
    private String idLoaiCay;
    private String tenLoaiCay;
    private String anhLoaiCay;

    public LoaiCayObject(String idLoaiCay, String tenLoaiCay, String anhLoaiCay) {
        this.idLoaiCay = idLoaiCay;
        this.tenLoaiCay = tenLoaiCay;
        this.anhLoaiCay = anhLoaiCay;
    }

    public LoaiCayObject(String idLoaiCay, String tenLoaiCay) {
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

    public String getAnhLoaiCay() {
        return anhLoaiCay;
    }

    public void setAnhLoaiCay(String anhLoaiCay) {
        this.anhLoaiCay = anhLoaiCay;
    }
}
