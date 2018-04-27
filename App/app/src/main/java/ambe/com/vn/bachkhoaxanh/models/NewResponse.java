package ambe.com.vn.bachkhoaxanh.models;

import java.io.Serializable;

/**
 * Created by AMBE on 11/04/2018.
 */

public class NewResponse implements Serializable {
    private String code;
    private String message;

    public NewResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public NewResponse() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
