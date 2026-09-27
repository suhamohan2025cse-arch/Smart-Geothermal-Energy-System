# Smart Geothermal Energy Extraction & Heat Network Controller

Spring Boot + MySQL REST API project.

## Requirements

- Java 17
- Maven 3.9+
- MySQL 8+
- IntelliJ IDEA / VS Code
- Postman

## MySQL

You can either create the database manually:

```sql
CREATE DATABASE geothermal_db;
```

or allow Hibernate to create it by keeping:

```properties
createDatabaseIfNotExist=true
```

in `application.properties`.

Change the MySQL username/password if your installation is different.

## Run

```bash
mvn spring-boot:run
```

Server:

`http://localhost:8080`

## Main APIs

### Wells

POST `/api/geothermal/wells`

```json
{
  "wellName": "GW-001",
  "location": "Thermal Field Zone A",
  "depth": 2500,
  "temperature": 145.5,
  "pressure": 32.4,
  "status": "ACTIVE"
}
```

GET `/api/geothermal/wells`

GET `/api/geothermal/wells/1`

PUT `/api/geothermal/wells/1`

DELETE `/api/geothermal/wells/1`

### Telemetry

POST `/api/geothermal/telemetry`

```json
{
  "wellId": 1,
  "temperature": 148.2,
  "pressure": 31.8,
  "flowRate": 86.5
}
```

Pressure below 20 automatically gets:

`WARNING - PRESSURE DROP`

GET `/api/geothermal/telemetry`

GET `/api/geothermal/telemetry/well/1`

### Customers

POST `/api/geothermal/customers`

```json
{
  "name": "District Heating Board",
  "district": "Zone A",
  "contact": "9876543210"
}
```

GET `/api/geothermal/customers`

### Energy Sales

POST `/api/geothermal/sales`

```json
{
  "customerId": 1,
  "energyGJ": 450,
  "amount": 67500,
  "status": "PENDING"
}
```

GET `/api/geothermal/sales`

### Maintenance

POST `/api/geothermal/maintenance`

```json
{
  "wellId": 1,
  "vendor": "ABC Pump Services",
  "description": "Deep well pump servicing",
  "cost": 25000,
  "status": "COMPLETED"
}
```

GET `/api/geothermal/maintenance`

### Report

GET `/api/geothermal/reports/summary`

Example response:

```json
{
  "totalEnergySoldGJ": 450,
  "totalRevenue": 67500,
  "maintenanceExpense": 25000,
  "netOperationalAmount": 42500
}
```
