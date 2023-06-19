# ssm-employee
this my first little project, it was made by SSM framework, and I'll add more function  in this project
The detailed description of the project is shown below:

#### Introduction:
This project is about a system for employee information management，The main function is to add, modify, and delete employee information.

#### Project environment
1.  java8
2. SSM dependencies
3. Maven Code Management Tool
4. MySQL5 database

#### Project features
1. Modify employee information
2. Delete employee information
3. Add employee information

#### Data
1. employee Table
2. Department Table (none)

#### Principle of functional implementation
1. Backend: Use Mybatis to implement persistence layer (dao) operations to operate on database information; Use Spring to operate on the service layer, verify and format the data transmitted by the persistence layer. Use springMVC to operate on the controller layer, and use a series of annotations to obtain the request information from the front-end page and hand it over to the business layer. Use Model and View for view rendering
2. Frontend: Contains HTML CSS JavaScript, which will add the Vue front-end framework in the future
