package com.rasa.rasa_test.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class Dao {


    private static  String LAST_LOGIN;
    private static String TOTAL_EMP;
    private static String TOTAL_BALANCE;
    private static String LOAD_JOB_TRANSACTION_FAILED;
    private static String LOAD_JOB_STATUS_BY_MIN;

    @Autowired
    JdbcTemplate jdbcTemplate;

    static {
    	//LAST_LOGIN=""
        LAST_LOGIN="";
        TOTAL_EMP="";
        //TOTAL_BALANCE="SELECT BALANE FROM USER_DETAILS WHERE ID=?";
        TOTAL_BALANCE="";
        LOAD_JOB_TRANSACTION_FAILED="";
		LOAD_JOB_STATUS_BY_MIN="";
    }

    public String getLogin() {

        try {
			return jdbcTemplate.queryForObject(LAST_LOGIN, String.class, "test_robert_nonSLA");
		} catch (Exception e) {
			e.printStackTrace();
			return "Incorrect userid";
		}
    }
    
    public String getLoadJob() {

        try {
			return jdbcTemplate.queryForObject(LOAD_JOB_TRANSACTION_FAILED, String.class);
		} catch (Exception e) {
			e.printStackTrace();
			return "Incorrect userid";
		}
    }
    
    public String getLastTenActivities() {
        return "Display the Last 10 Activity Details";
    }
  
    public String getTotalActiveEmployees() {
        try {
			return jdbcTemplate.queryForObject(TOTAL_EMP, String.class);
		} catch (Exception e) {
			e.printStackTrace();
			return "Incorrect userid";
		}

    }

	public String getLoadJobStatusByMin() {
		try {
		//	return jdbcTemplate.queryForObject(LOAD_JOB_STATUS_BY_MIN, String.class);
			return "Good";
		} catch (Exception e) {
			e.printStackTrace();
			return "Incorrect userid";
		}

	}

    public String getRetailerBalance() {
    	try {
        return jdbcTemplate.queryForObject(TOTAL_BALANCE, String.class);
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    		return "Incorrect userid";
    	}
    	}
    
}
