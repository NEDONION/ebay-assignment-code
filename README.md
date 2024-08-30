# Pre-Interview Assignment: Implement a Flexible Calculator

<img width="1000" alt="image" src="https://github.com/user-attachments/assets/64f2eb58-510b-42e2-a60b-35cd1d364266">


## Features
- RESTful APIs CRUD
- addition, subtraction, multiplication and division
- Support integers, big integers, floating point numbers, etc.
- Structured log printing
- Graceful error handling and clear error messages

## Requisites
- Java
- Spring 2.x
- JUnit
- Log4j2

## Get Started
- The application will start on port `9090`

## Calculate - Sample Requests

### Addition Case 01
```shell
curl --location 'localhost:9090/v1/calculator/calculate' \
--header 'Content-Type: application/json' \
--data '{
        "op": "ADD",
        "num1": 1,
        "num2": 2
    }'
```

```json
{
  "code": "200",
  "message": "Calculation successful",
  "data": 3
}
```
### Addition Case 02
```shell
curl --location 'localhost:9090/v1/calculator/calculate' \
--header 'Content-Type: application/json' \
--data '{
        "op": "ADD",
        "num1": 1e308,
        "num2": 1e308
    }'
```

```json
{
    "code": "400 BAD_REQUEST",
    "message": "Double overflow or invalid result",
    "data": null
}
```

## Chain Calculate - Sample Requests

```shell
curl --location 'localhost:9090/v1/calculator/chain' \
--header 'Content-Type: application/json' \
--data '{
    "operations": [
        {
            "op": "ADD",
            "num": 5
        },
        {
            "op": "SUBTRACT",
            "num": 2
        },
        {
            "op": "MULTIPLY",
            "num": 3
        },
        {
            "op": "DIVIDE",
            "num": 4
        }
    ]
}'
```

```json
{
  "code": "200",
  "message": "Calculation successful",
  "data": 2.25
}
```
