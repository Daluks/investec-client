# Investec Client API

## Introduction

This is a rest api that allows for creating, updating and searching for a client.

Please note that The data is not persisted to any persistent storage but remains in cache during runtime.

## Prerequisite

1. JDK11 is required
2. Maven is required to build the project

## Building and running

To execute the project you'll need to execute
```shell

mvn clean package

```
The application should be accessible using the endpoint below once deployed.

 http://localhost:8080/investec-client-api/api/ping



