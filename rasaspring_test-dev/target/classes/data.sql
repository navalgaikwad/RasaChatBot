DROP TABLE IF EXISTS chat_category;

CREATE TABLE chat_category (
  cat_id INT AUTO_INCREMENT  PRIMARY KEY,
  cat_name VARCHAR(250) NOT NULL,
  cat_is_active VARCHAR(1) DEFAULT 'N',
  created_date DATE DEFAULT SYSDATE,
  updated_date DATE DEFAULT SYSDATE
);

INSERT INTO chat_category (cat_name, cat_is_active, created_date, updated_date) VALUES
  ('Profile Management', 'Y', sysdate, sysdate),
  ('Employee Management', 'Y', sysdate, sysdate),
  ('Load Management', 'Y', sysdate, sysdate);
  
-------------------------
DROP TABLE IF EXISTS chat_question;

CREATE TABLE chat_question (
  qid INT AUTO_INCREMENT  PRIMARY KEY,
  qname VARCHAR(250) NOT NULL,
  qdescription VARCHAR(2500) DEFAULT NULL,
  qapi_name VARCHAR(250) DEFAULT NULL,
  qis_active VARCHAR(1) DEFAULT 'N',
  qcreated_date DATE DEFAULT SYSDATE,
  qupdated_date DATE DEFAULT SYSDATE,
  qquery VARCHAR(5000) DEFAULT NULL,
  qcat_id INT NOT NULL,
  CONSTRAINT FK_chat_category FOREIGN KEY (qcat_id)
    REFERENCES chat_category(cat_id)
);

INSERT INTO chat_question (qname, qdescription, qapi_name, qis_active, qcreated_date, qupdated_date, qquery, qcat_id) VALUES
  ('Last Login', 'When is the user logged-in last time', 'last_login_api', 'Y',  sysdate, sysdate, '', 1),
  ('Last 10 Activities', 'Displays the last 10 activities performed by user', 'last_10act_api', 'Y',  sysdate, sysdate, '', 1),
  ('Search Emp By MSISDN', 'Search an employee using mobile number', 'search_by_msiadn_api', 'Y',  sysdate, sysdate, '', 2),
  ('Search Emp By Name', 'Search an employee using the employee name', 'search_by_name_api', 'Y',  sysdate, sysdate, '', 2),
  ('Retailer Balance', 'Get the latest Retailer Balance', 'get_ret_bal_api', 'Y',  sysdate, sysdate, '', 3),
  ('Total Active Employees', 'Displays total count of employees', 'total_emp_api', 'Y',  sysdate, sysdate, '', 2);


  DROP TABLE IF EXISTS USER_DETAILS;
  CREATE TABLE USER_DETAILS(ID VARCHAR(255) PRIMARY KEY, TOTAL VARCHAR(255), DATE VARCHAR(255),BALANE VARCHAR(255));

  INSERT INTO USER_DETAILS VALUES('gaganp', '500',' 01/04/2021 4:12:04 PM', '900000.00');

----------------------

