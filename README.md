# zephyr (In progress)

 Vaccination tracker service inspired by e-VAX and DOMES Agenda:

* **Patient Administration :** Handle patient registration as well as there vaccination records. 
   
- **Vaccine Administration :** Logs a vaccination event while atomically validating and decrementing available inventory stock.
    
- **Traceability :** Links every administered dose to a specific practitioner, patient, and vaccine batch number.


## Key Features

* **Patient & Guardian Management:** Easily lookup patient profiles by National Register Number (NISS) and link them to guardians for unified family tracking.
* **Real-time Vaccine Inventory:** Monitor available vaccine stock levels, batch numbers, and expiration dates across health facilities.
* **Automated Stock Deduction:** Automatically update and decrement inventory counts as soon as a practitioner logs a new dose.
* **Complete Audit Trail & History:** Maintain a permanent, accurate record of every vaccination event, including administration date, practitioner details, and batch traceability.
* **Role-Based Security:** Protect sensitive healthcare records by ensuring only authorized medical personnel can log doses and view patient history.


## Tech stack 

* Backend: Java 21, Spring Boot 4, Spring Data JPA

* Database & Migrations: MySQL 8.0, Flyway

* Testing : JUnit 5, Mockito, Testcontainers (MySQL)

* Frontend: Angular 21 (Work in Progress)

* DevOps: Docker, Docker Compose

## Roadmap / Upcoming Work

- [x] Initial relational database design & Flyway migration setup
    
- [x] Spring Boot 4 backend architecture with JPA
    
- [x] Integration testing suite with JUnit 5 
    
- [ ] Angular 21 reactive dashboard for NISS lookup & stock administration
