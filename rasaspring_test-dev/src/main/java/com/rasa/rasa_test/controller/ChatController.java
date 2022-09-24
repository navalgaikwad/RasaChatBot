package com.rasa.rasa_test.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.rasa.rasa_test.util.ApplicationType;

import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rasa.rasa_test.model.RasaReqModel;
import com.rasa.rasa_test.model.RasaResModel;
import com.rasa.rasa_test.service.Service;

@CrossOrigin
@RestController
public class ChatController {
	
	@Autowired
	private Service service;

	@PostMapping(path = "/hit", consumes ="application/json",produces="application/json")
	public ResponseEntity<List<RasaResModel>>  printHello(@RequestBody RasaReqModel model) {

		System.out.println("input model: "+model);
		
		try {
		Thread.sleep(2000);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		//Changes by gagan
		System.out.println(model.getFlag());
		if ((model.getFlag()!=null && model.getFlag().isEmpty()!=true)){
			return service.callToBackEnd(model, ApplicationType.valueOf(model.getFlag()));
		}
		

		ResponseEntity<List<RasaResModel>>reslist=getListResponseEntity(model);
		System.out.println(reslist);
		String text=reslist.getBody().get(0).getText();
		String flag=reslist.getBody().get(0).getFlag();
		
		if(text.isEmpty() && flag.isEmpty()!=true)
		//if(text.isBlank()==true && flag.isBlank()!=true)
		{
			return service.callToBackEnd(model, ApplicationType.valueOf(flag));
		}
		
		return reslist;
		

	}

	private ResponseEntity<List<RasaResModel>> getListResponseEntity(@RequestBody RasaReqModel model) {
		final String uri = "http://localhost:5005/webhooks/rest/webhook";

		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.postForObject(uri,model, String.class);

		System.out.println("Output model: "+result);
		JSONParser parser = new JSONParser(result);
		List<RasaResModel> responseList = new ArrayList<>();

		try {

			Iterator itr =  parser.parseArray().iterator();
			while (itr.hasNext())
			{
				RasaResModel s = new ObjectMapper().convertValue(itr.next(), RasaResModel.class);

				responseList.add(s);

			}
			for(RasaResModel r : responseList) {
				System.out.println("list: "+r);
			}

			return new ResponseEntity<List<RasaResModel>>(responseList, HttpStatus.OK);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	
	@GetMapping(path = "/init-menu/{sessionAttrib}", consumes ="application/json",produces="application/json")
	public ResponseEntity<List<RasaResModel>>  getInitialMenu(@PathVariable("sessionAttrib") String sessionAttrib) {
		System.out.println("Inside : getInitialMenu() sessionAttrib "+sessionAttrib);
		RasaReqModel req = new RasaReqModel();
		req.setMessage("menu");
		req.setSender(sessionAttrib);
		/*List<String> menuOptions = new ArrayList();
		menuOptions.add("1: Last Login");
		menuOptions.add("2: Last 10 Activities");                      
		menuOptions.add("3: Change Password");
		menuOptions.add("4: Revenue Loaded Today");*/
		System.out.println(printHello(req));
		
		return printHello(req);
		//return new ResponseEntity<List<String>>(menuOptions, HttpStatus.OK);
		//return null;
	}

}
