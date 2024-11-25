**POST: http://localhost:8080/api/v1/employees**
```
{
  "name": "John Doe",
  "email": "john.doe@example.com",
  "phone": "1234567890",
  "hireDate": "2024-01-10"
}
```
**Response**
```
{
  "id": 1,
  "name": "John Doe",
  "email": "john.doe@example.com",
  "phone": "1234567890",
  "hireDate": "2024-01-10",
  "services": [ ]
}
```
**Screenshot**
![](screenshots/Creat-New-Employee.png)


**POST: http://localhost:8080/api/v1/services**
```
{
  "employeeId": 1,
  "serviceName": "Oil Change",
  "cost": 120.00,
  "vehicleType": "Motorcycle"
}
```
**Response**
```
{
    "id": 1,
    "serviceName": "Oil Change",
    "cost": 120.0,
    "vehicleType": "Motorcycle"
}
```
**Screenshot**
![](screenshots/Assign-Service.png)

**GET: http://localhost:8080/api/v1/employees**
**Response**
```
[
    {
        "id": 1,
        "name": "John Doe",
        "email": "john.newemail@example.com",
        "phone": "0987654321",
        "hireDate": "2024-01-10",
        "services": [
            {
                "id": 1,
                "serviceName": "Oil Change",
                "cost": 120.0,
                "vehicleType": "Motorcycle"
            }
        ]
    }
]
```
**Screenshot**
![](screenshots/Get-All-Employees.png)

**POST: http://localhost:8080/api/v1/services**
```
{
  "employeeId": 1,
  "serviceName": "Tire Replacement",
  "cost": 180.50,
  "vehicleType": "Truck"
}
```

**Response**
{
    "id": 2,
    "serviceName": "Tire Replacement",
    "cost": 180.5,
    "vehicleType": "Truck"
}

**Screenshot**
![](screenshots/Assign-Service-2.png)

**GET: http://localhost:8080/api/v1/employees/1**
**Response**
```
{
    "id": 1,
    "name": "John Doe",
    "email": "john.doe@example.com",
    "phone": "1234567890",
    "hireDate": "2024-01-10",
    "services": [
        {
            "id": 1,
            "serviceName": "Oil Change",
            "cost": 120.0,
            "vehicleType": "Motorcycle"
        },
        {
            "id": 2,
            "serviceName": "Tire Replacement",
            "cost": 180.5,
            "vehicleType": "Truck"
        }
    ]
}
```
**Screenshot**
![](screenshots/Get-Employee-By-ID.png)
**GET: http://localhost:8080/api/v1/employees**
**Response**
```
[
    {
        "id": 1,
        "name": "John Doe",
        "email": "john.doe@example.com",
        "phone": "1234567890",
        "hireDate": "2024-01-10",
        "services": [
            {
                "id": 1,
                "serviceName": "Oil Change",
                "cost": 120.0,
                "vehicleType": "Motorcycle"
            },
            {
                "id": 2,
                "serviceName": "Tire Replacement",
                "cost": 180.5,
                "vehicleType": "Truck"
            }
        ]
    }
]
```
**Screenshot**
![](screenshots/Get-All-Employee-2.png)
**PATCH: http://localhost:8080/api/v1/employees/1**
```
{
  "email": "john.newemail@example.com",
  "phone": "0987654321"
}
```
**Response:**
```
{
    "id": 1,
    "name": "John Doe",
    "email": "john.newemail@example.com",
    "phone": "0987654321",
    "hireDate": "2024-01-10",
    "services": [
        {
            "id": 1,
            "serviceName": "Oil Change",
            "cost": 120.0,
            "vehicleType": "Motorcycle"
        },
        {
            "id": 2,
            "serviceName": "Tire Replacement",
            "cost": 180.5,
            "vehicleType": "Truck"
        }
    ]
}
```
**Screenshot**
![](screenshots/Partial-Update-Employee.png)

**Global Exception Handler**
![](screenshots/Resource-Not-Found-Exception.png)

**Unit testing**
![](screenshots/Unit-Test.png)