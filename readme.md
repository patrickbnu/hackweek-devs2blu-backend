# HackWeek Project

This project is part of the HackWeek event developed for the [+Devs2Blu](https://www.devs2blu.com.br/) 
program.

## Table of Contents

- [Installation](#installation)
- [Configuration](#configuration)
- [Authenticating to GitHub Packages](#authenticating-to-github-packages)
- [Health Check](#health-check)

## Installation

1. Clone the repository:

```bash
git clone https://github.com/EduardoOrthmann/hackweek-devs2blu-backend.git
```

2. Install dependencies with Maven.

> Note: The project uses pluggy-java as a dependency. This dependency is only available in the GitHub Packages repository
> to which you must be authenticated. To authenticate, follow the instructions as mentioned here [Authenticating to GitHub Packages](#authenticating-to-github-packages).

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

- Replace USERNAME with your GitHub username.
- Replace TOKEN with the personal access token you created.

# HEALTH CHECK
management.endpoint.health.show-details=always
```

# Authenticating to GitHub Packages

- Create a personal access token in your GitHub account. [Creating a personal access token](https://docs.github.com/en/github/authenticating-to-github/creating-a-personal-access-token)
- Create a file named settings.xml in the .m2 folder in your user directory. [Settings](https://maven.apache.org/settings.html)
- Add the following configuration to the settings.xml file:

```markdown
<?xml version="1.0" encoding="UTF-8"?>
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 http://maven.apache.org/xsd/settings-1.0.0.xsd">
<activeProfiles>
<activeProfile>github</activeProfile>
</activeProfiles>

  <profiles>
    <profile>
      <id>github</id>
      <repositories>
        <repository>
          <id>central</id>
          <url>https://repo1.maven.org/maven2</url>
        </repository>
        <repository>
          <id>github</id>
          <url>https://maven.pkg.github.com/pluggyai/*</url>
          <snapshots>
            <enabled>true</enabled>
          </snapshots>
        </repository>
      </repositories>
    </profile>
  </profiles>

  <servers>
    <server>
      <id>github</id>
      <username>USERNAME</username>
      <password>TOKEN</password>
    </server>
  </servers>
</settings>
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