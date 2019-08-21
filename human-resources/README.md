  [![Build Status](https://travis-ci.org/brest-java-course-summer-2019/aliaksandr-fedasiuk.svg?branch=master)](https://travis-ci.org/brest-java-course-summer-2019/aliaksandr-fedasiuk)
  [![Coverage Status](https://coveralls.io/repos/github/brest-java-course-summer-2019/aliaksandr-fedasiuk/badge.svg)](https://coveralls.io/github/brest-java-course-summer-2019/aliaksandr-fedasiuk)
  
  # Human Resources Application

  Demo project for Human resource management with two entities: “Department” and “Employee”, related as one to many.
    
  ## Prerequisites
    
  jdk11  
  maven 3+  
  
  ## Installing  
  > mvn clean install  
  
  ## Running the tests  
  > mvn clean test  
  
  ### Server test
  
  For server test jetty plugin can be used
  
      mvn jetty:run 
      
  Open [http://localhost:8080](http://localhost:8080/hello) 
  
  
  ## REST server
  
  Start REST app:
    
    cd rest-app
    mvn jetty:run
  
  ### Try CURL requests
  
  Get all departments:
  
    curl -v localhost:8088/departments  
   
  Create new department via REST:
   
    curl -H "Content-Type: application/json" -X POST -d '{"departmentId":null,"departmentName":"xyz"}' -v localhost:8088/department 
  
  
