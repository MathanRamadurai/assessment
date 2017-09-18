Jumo Coding Test

Calculate the aggregate loans by the tuple of (Network, Product, and Month) with the total currency amounts and counts from the Loans.csv and output into a file CSV

Prerequisites

This project is tested with JDK 1.8 and it won’t work if JDK version is below 1.7. So please make sure to install JDK 1.7 or 1.8, before run this project.

•	JDK 1.8 or JDK 1.7 (Mandatory)

To install jdk follow the below link

https://www3.ntu.edu.sg/home/ehchua/programming/howto/JDK_Howto.html

Development setup

1.	Windows 7 OS
2.	JDK 1.8
3.	Eclipse Neon (4.6.3)
4.	apche maven 3.0 – Used for build Management

Project

This project developed using java 1.8. i choose java because it supports object oriented as well as function oriented programming language.
Also, it has lot of reusable apis that makes developer's life very easy. I have used few of the third party APIs to make this project production ready.

Furthermore, this project has been unit tested, flexible logger implemented, Code formatted and documented as per java standards.

Usage

java -jar [jar file] [input file]

java -jar [jar file] [input file] [output file]

[output file] ---> Output file argument is optional. If not given output file would be created in the name Output.csv

For Example:

java -jar target/assessment-1.0.0.jar target/Loans.csv
java -jar target/assessment-1.0.0.jar target/Loans.csv target/Output.csv

Flexibility around this are follows:

•	Input file or output file can have any name and can be anywhere in your system but only thing is the location should be accessible by you.

•	This project is written generically you may use it for other .csv files with the same structure as Loans.csv.


Assumptions

•	Input file extension (.csv) won’t change.
•	First row in excel will have header and second row onwards actual data values will be given
•	Colum order stays same
•	Date and other field’s format won’t change
•	Extra fields won’t be added or existing field wouldnot be removed
•	Input file won’t have large data. For example more than 200 MB of data. If it has more than that this project supposed to handle. If not it is very easy to fine tune the code to work.
•	Output file extension (.csv) won’t change.


Below are the libraries used in my project:

1.	junit - used for Unit Testing
2.	log4j - used for Logging
3.	opencsv - used for csv read and write
4.	jagg - used for aggregating java beans

Plugins

1.	Eclipse default Code Formatter and Styler (To align sour code as per standards)
2.	Jautodoc eclipse plugin (Used for java file documentations)


This solution has been developed as i do for production environment, Due to time constrains and busy schedule, few of things i thought to complete but i could not. Which i have listed below:
 
 1, Custom Exception with specific code for each exception and message
 2, SonarQube code review Tool – Thought to review my code through sonarqube. But I couldn’t. Basically it will check for code review rules from firebug, checklist etc.., and give you list of code review rules violation.
 

