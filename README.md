Online market project

Environment:
1.Java 8
2.Spring 5.0.8.RELEASE
3.Hibernate 5.3.5.Final
4.Tomcat 9.0.11
5.Git(Bitbucket or GitHub)
6.Apache Maven 3.5.4
7.MySQL 8.0.12

Description:
Online shopping is a form of electronic commerce which allows consumers to
directly buy goods or services from a seller over the Internet using a web browser.
Consumers find a product of interest by visiting the website of the retailer directly or
by searching among alternative vendors using a shopping search engine, which
displays the same product's availability and pricing at different e-retailers.

Roles and functionality:

Role				Functionality

For all users 			➔ Login
				➔ Registration
				➔ Logout

Security user 			➔ Users: change role/password, disable, delete
				➔ Audit: show events(USER, ITEM, ORDER, NEWS, COMMENTS)

Sale user 			➔ News: create, update, delete, delete comments.
				➔ Items: create, copy, remove (soft delete), upload with help of xml
				➔ Orders: show orders, change status(NEW, REVIEWING, IN_PROGRESS,DELIVERED)
Customer user			➔ News: show, create comment for news.
				➔ Orders: add item to bucket, create order, show orders
				➔ Profile: change information except email, change password

API user 			➔ Items: create, update, remove (soft delete), delete if not exists in any orderDatabase schema:

https://drive.google.com/file/d/18UGgyApP5PFPGrYiArL35eZ4wmoryL_g/view

Requirements:
1.DDL and DML scripts for database
2.3-level three-module development architecture
3.Validation on backend for all entities
4.Security rules for access

// create database store character set utf8 collate utf8_general_ci;