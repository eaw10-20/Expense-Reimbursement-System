# Expense-Reimbursement-System (ERS) - Java CDE Full Stack

## Technologies Used
  
* Java
* JavaScript
* HTML/CSS
* JDBC
* AWS RDS
* PostgreSQL
* Javalin

## Features
	
#### Project Features

* Employees can log in and submit requests for reimbursement.
* Employees can view their past tickets and pending reimbursement requests.
* Finance Managers can log in and view all reimbursement requests and past history for all employees.
* Finance Managers can approve and deny requests for expense reimbursement.

#### To-do list

* Implement JUnit to test functionality of program.
* Hash passwords so that they can be stored securely in database.
* Add functionality to add a document/image representing receipt.
* Add email functionality to support existing features.

## Getting Started

1. Clone project using the following link: https://github.com/eaw10-20/Expense-Reimbursement-System.git
2. Open using Java supporting IDE such as Intellij.

## Usage

To start the Expense Reimbursement System application, run the main driver, found at the directory ./src/main/java/com/driver/MainDriver. After a short loading period, the console should tell you that it is listening at a given url, by default set to http://localhost:11235/

![1](https://user-images.githubusercontent.com/60686880/116499921-3555aa00-a862-11eb-8bfd-7017ca9e9a04.jpg)


If you open the link you should get a login page asking for a username and a password.

![2](https://user-images.githubusercontent.com/60686880/116499952-4acad400-a862-11eb-97b2-90665af6a1d6.jpg)


The application currently recognizes two types of accounts: employee accounts and manager accounts. Logging into an employee acount you will get a page like below.

![4](https://user-images.githubusercontent.com/60686880/116499967-574f2c80-a862-11eb-9ac0-03924390c31b.jpg)


The table in the center of the screen will show all of the reimbursement requests you have made as an employee. Information is shown such as the reimbursement type, its current status, the monetary amount, a brief description, the date submitted, and if the reimbursement has been resolved then a date resolved and resolver are also displayed. Below the table on the right side there is a form that will allow you to make a new request. Here you can set the reimbursement type to one of several predefined values, enter a numeric value representing the amount to be requested, and a type a brief description of your reimbursement. Once you are done you can submit it by hitting "Submit Request". The logout button on the top right allows you to logout and return to the login page.


Logging into a manager account will display a page like shown below.

![5](https://user-images.githubusercontent.com/60686880/116499973-5d450d80-a862-11eb-9122-5d0aad1ace8e.jpg)


In this view the table in the middle will show reimbursement requests from all employees sorted by date submitted. You can toggle the view to only show pending, approved, or denied requests using the buttons above the table. To resolve a request, first select a request using the checkbox on the right side of the table. Then on the bottom right there is an option to either approve or deny that request. Once chosen, you can submit that request for resolution using the submit button. Once again, you can logout using the button on the top right.
