package com.rasa.rasa_test.service;

import com.rasa.rasa_test.dao.Dao;
import com.rasa.rasa_test.model.RasaReqModel;
import com.rasa.rasa_test.model.RasaResModel;
import com.rasa.rasa_test.util.ApplicationType;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Service
public class Service {

    private static Logger LOGGER = LoggerFactory.getLogger(Service.class);
    List<RasaResModel> responseList;

    @Autowired
    private Dao dao;

    public ResponseEntity<List<RasaResModel>> callToBackEnd(RasaReqModel model, ApplicationType applicationType) {

        switch (applicationType) {
            case LAST_LOGIN:
                // code block
                return getLogin(model);

            case LAST_TEN_ACTIVITIES:
                //code block
                return getLastTenActivities(model);

            case RETAILER_BALANCE:
                return getRetailerBalance(model);

            case LOAD_JOB_TRANSACTION_FAILED:
                return getLoadJob(model);

            case TOTAL_ACTIVE_EMPLOYEES:
                //code block
                return getTotalActiveEmployees(model);

            case LOAD_JOB_STATUS_BY_MIN:
                //code block
                return getLoadJobStatusByMin(model);
            //
            //break;


            default:
                // code block
        }
        return null;
    }

    public ResponseEntity<List<RasaResModel>> getLogin(RasaReqModel model) {
        responseList = new ArrayList<>();
        RasaResModel rasaResModel = new RasaResModel();

        String login = dao.getLogin();
        rasaResModel.setText("Last Login: " + login);
        System.out.println(login);
        responseList.add(rasaResModel);
        System.out.println("Response List : " + responseList);
        return new ResponseEntity(responseList, HttpStatus.OK);

    }

    public ResponseEntity<List<RasaResModel>> getLastTenActivities(RasaReqModel model) {
        responseList = new ArrayList<>();
        RasaResModel rasaResModel = new RasaResModel();

        String activities = dao.getLastTenActivities();
        System.out.println("last ten activities:" + activities);
        rasaResModel.setText("Last Ten Activities: " + activities);
        responseList.add(rasaResModel);

        return new ResponseEntity(responseList, HttpStatus.OK);

    }

    public ResponseEntity<List<RasaResModel>> getTotalActiveEmployees(RasaReqModel model) {
        responseList = new ArrayList<>();
        RasaResModel rasaResModel = new RasaResModel();

        String active_employees = dao.getTotalActiveEmployees();
        System.out.println("Total Active Employees:" + active_employees);
        rasaResModel.setText("Total Active Employees: " + active_employees);
        responseList.add(rasaResModel);

        return new ResponseEntity(responseList, HttpStatus.OK);

    }

    public ResponseEntity<List<RasaResModel>> getLoadJobStatusByMin(RasaReqModel model) {
        responseList = new ArrayList<>();
        RasaResModel rasaResModel = new RasaResModel();

        String active_employees = dao.getLoadJobStatusByMin();
        System.out.println("Total Active Employees:" + active_employees);
        rasaResModel.setText("Total Active Employees: " + active_employees);
        responseList.add(rasaResModel);

        return new ResponseEntity(responseList, HttpStatus.OK);

    }


    public ResponseEntity<List<RasaResModel>> getRetailerBalance(RasaReqModel model) {
        responseList = new ArrayList<>();
        RasaResModel rasaResModel = new RasaResModel();

        String resp = dao.getRetailerBalance();
        JSONObject obj = new JSONObject(resp);
        Double balance = obj.getJSONObject("Body").getDouble("retailerNewBalance");
        System.out.println("Retailer Balance:" + balance);
        rasaResModel.setText("Retailer Balance: P " + balance);
        responseList.add(rasaResModel);

        return new ResponseEntity(responseList, HttpStatus.OK);

    }

    public ResponseEntity<List<RasaResModel>> getLoadJob(RasaReqModel model) {
        responseList = new ArrayList<>();
        RasaResModel rasaResModel = new RasaResModel();

        String resp = dao.getLoadJob();
        JSONObject obj = new JSONObject(resp);
        String description = obj.getJSONObject("Body").getString("respCodeDesc");
        System.out.println("Load Job Failed due to :" + description);
        rasaResModel.setText("Load Job Failed due to : " + description);
        responseList.add(rasaResModel);

        return new ResponseEntity(responseList, HttpStatus.OK);

    }
}
