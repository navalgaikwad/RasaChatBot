package com.rasa.rasa_test.util;

public enum ApplicationType {
    LAST_LOGIN("Last Login"),
    LAST_TEN_ACTIVITIES("Last Ten Activities"),
    RETAILER_BALANCE("Retailer Balance"),
    TOTAL_ACTIVE_EMPLOYEES("Total Active Employees"),
    LOAD_JOB_STATUS_BY_MIN("Load Job Status by Min"),
	LOAD_JOB_TRANSACTION_FAILED("Load Job Transaction Failed");


    private String type;

    ApplicationType(String type) {
        this.type = type;

    }

    public String getType() {
        return type;
    }

}

