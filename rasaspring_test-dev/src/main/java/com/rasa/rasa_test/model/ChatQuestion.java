package com.rasa.rasa_test.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ChatQuestion {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long qid;
	
	
	private String qname;
	private String qdescription; 
	private String qapi_name;    
	private String qis_active ;  
	private Date qcreated_date;
	private Date qupdated_date;
	private String qquery  ;     
	private long qcat_id;

}
