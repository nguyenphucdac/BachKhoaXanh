package ambe.com.vn.bachkhoaxanh.utils;

/**
 * Created by AMBE on 10/04/2018.
 */

public class Api {
    public static final String ip = "192.168.137.1";
    public static final String ipServerNodeJs = "192.168.137.1";
    public static final String URL_SERVER_NODEJS = "http://" + ipServerNodeJs + ":3000";

    public static final String apiGetListCay = "http://" + ip + ":9999/get-list-cay";
    public static final String apiGetListDiemCapNuoc = "http://" + ip + ":9999/get-list-dcn";
    public static final String apiLogin = "http://" + ip + ":9999/login";
    public static final String apiLogout = "http://" + ip + ":9999/logout";
    public static final String apiGetDirectionFromTree = "http://" + ip + ":9999/get-trace-cay-1";
    public static final String apiGetDirectionFromListTrees = "http://" + ip + ":9999/get-trace-cay-2";
    public static final String apiGetDirectionFromDcn = "http://" + ip + ":9999/get-trace-dcn-1";
    public static final String apiGetDirectionFromListDcns = "http://" + ip + ":9999/get-trace-dcn-2";
    public static final String apiGetLichSuTuoiCuaCay = "http://" + ip + ":9999/get-lich-su-tuoi-cay/";
    public static final String apiGetLichSuTuoiCuaNv = "http://" + ip + ":9999/get-lich-su-tuoi-thanh-vien/";
    public static final String apiBaoCaoCay = "http://" + ip + ":9999/bao-cao-cay/";
    public static final String apiBaoCaoDcn = "http://" + ip + ":9999/bao-cao-diem-cap-nuoc/";
    public static final String apiGetListThanhVien = "http://" + ip + ":9999/get-list-thanh-vien";
    public static final String apiCapNhatThanhVien = "http://" + ip + ":9999/cap-nhat-thanh-vien";
    public static final String apiTuoiNuoc = "http://" + ip + ":9999/cap-nhat-cay";
    public static final String apiRegister = "http://" + ip + ":9999/signin";
}
