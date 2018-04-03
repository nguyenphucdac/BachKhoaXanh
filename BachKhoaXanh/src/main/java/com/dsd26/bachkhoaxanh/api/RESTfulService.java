package com.dsd26.bachkhoaxanh.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;


import com.dsd26.bachkhoaxanh.model.CayMD;
import com.google.gson.Gson;

/*
 * author: Nguyễn Phúc Đạc
 */

// class demo goi api tra ve json
@Path("/get_cay")
public class RESTfulService {
	
  
	@Path("{idCay}")
	@GET
	@Produces("application/json")
	public String getWeather_JSON(@PathParam("idCay") String idCay) {
		System.out.println("value of :" + idCay);
		CayMD cayMD = new CayMD("Xa_Cu_001", "Than_Go", 2, 3, 10, "Còn sống");

		Gson gson = new Gson();

		String jsonObject = gson.toJson(cayMD);

		return jsonObject;
	}
 
}
