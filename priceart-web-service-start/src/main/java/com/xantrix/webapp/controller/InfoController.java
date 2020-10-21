package com.xantrix.webapp.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xantrix.webapp.appconf.AppConfig;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value="shop", tags="Controller info configurazione profilo priceArt service")
public class InfoController
{
	@Autowired
	private AppConfig configuration;

	@GetMapping("/info")
	@ApiOperation(
		      value = "Ricerca linfo di configurazione", 
		      notes = "Restituisce i dati in formato JSON",
		      response = Map.class, 
		      produces = "application/json")
	@ApiResponses(value =
	{   @ApiResponse(code = 200, message = "L'info è stato trovata!"),
	    @ApiResponse(code = 404, message = "L'info cercato NON è stata trovata!"),
	    @ApiResponse(code = 403, message = "Non sei AUTORIZZATO ad accedere alle informazioni"),
	    @ApiResponse(code = 401, message = "Non sei AUTENTICATO")
	})
	public Map<String, String> getInfo()
	{
		Map<String, String> map = new HashMap<String, String>();
		map.put("listino", configuration.getListino());
		
		return map;
	}
}
