# Account Replenishment
Technologies used: Java, Spring MVC, Spring Security, Spring Data JPA, Pagination, Hibernate, MySQL, JPA/JSTL, jQuery, Tomcat. 

--- After start application, you can fill the DB data from: resources/Fill_DB.SQL
(and change "hibernate.hbm2ddl.auto" in RootContextCofig to "validate")---

System of replenisment balance of users.
User have a few roles: GUEST, USER and ADMIN. Start page have a form for enter to account or button to create a new account. 
GUEST can create a new account and after this-enter to his user page.
User can see his balance in special page (/userpage). ADMIN have the right to review list of users, 
can fill money to balance current user 
(click to E-mail and enter balance fill sum), can search from e-mail. From the link, 
admin can review journal of users replenishment sum, date and search from time period. 
All page with list of info have a pagination.
