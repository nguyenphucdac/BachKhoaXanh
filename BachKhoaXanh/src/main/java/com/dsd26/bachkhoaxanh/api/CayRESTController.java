package com.dsd26.bachkhoaxanh.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dsd26.bachkhoaxanh.dao.ICayDAO;
import com.dsd26.bachkhoaxanh.entity.Cay;
import com.dsd26.bachkhoaxanh.model.CayMD;
import com.google.gson.Gson;

//
// author : Nguyen Phuc Dac
//
// 

@RestController
public class CayRESTController {
	@Autowired
	private ICayDAO iCayDAO;

	@RequestMapping(value = "/get_cay/{idCay}", //
			method = RequestMethod.GET)
	@ResponseBody
	public String getCay(@PathVariable("idCay") String idCay) {
		Cay cay = iCayDAO.timKiem(idCay);
		if (cay == null) {
			return "bad request";
		}
		CayMD cayMD = new CayMD(cay);
		Gson gson = new Gson();
		String jsonObjectt = gson.toJson(cayMD);

		return jsonObjectt;
	}
}
