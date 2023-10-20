# HackWeek Project

This project is part of the HackWeek event developed for the [+Devs2Blu](https://www.devs2blu.com.br/) 
program.

## Table of Contents

- [Installation](#installation)
- [Configuration](#configuration)
- [Health Check](#health-check)

## Installation

1. Clone the repository:

```bash
git clone https://github.com/EduardoOrthmann/hackweek-devs2blu-backend.git
```

2. Install dependencies with Maven.

3. Configure the database connection in the application.properties file as mentioned here [Configuration](#configuration).

4. Run the application.

## Configuration

The following properties can be configured in the application.properties file:

```markdown
# DATABASE
spring.datasource.url=jdbc:postgresql://localhost:5432/your_database
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.datasource.driver-class-name=org.postgresql.Driver

# HEALTH CHECK
management.endpoint.health.show-details=always
```

## Health Check

The health check endpoint is available at http://localhost:8080/actuator/health

Below are the possible responses:

```
{
    "status": "UP",
    "components": {
        "db": {
            "status": "UP",
            "details": {
                "database": "PostgreSQL",
                "validationQuery": "isValid()"
            }
        },
        "diskSpace": {
            "status": "UP",
            "details": {
                "total": 499963174912,
                "free": 249963174912,
                "threshold": 10485760
            }
        },
        "ping": {
            "status": "UP"
        }
    }
}
```

```
{
    "status": "DOWN",
    "components": {
        "db": {
            "status": "DOWN",
            "details": {
                "database": "PostgreSQL",
                "validationQuery": "isValid()"
            }
        },
        "diskSpace": {
            "status": "UP",
            "details": {
                "total": 499963174912,
                "free": 249963174912,
                "threshold": 10485760
            }
        },
        "ping": {
            "status": "UP"
        }
    }
}
```