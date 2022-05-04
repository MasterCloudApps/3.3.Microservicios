# Basic saga example using REST

This example is based on 4 microservices (products, orders, customers and notifications). Also, there is a api-gateway service.

# Local deployment

## Prerequisites

* Java and Maven
* Node
* Docker

## Deploy zipkin

```
$ docker run -d -p 9411:9411 openzipkin/zipkin
```

## Build and deploy services

```
$ node start_local_services.js
```

# Kubernetes deployment

> NOTE: Currently not tested. Maybe it doesn't work

## Prerequisites

* Java and Maven
* Docker
* Minikube with ingress addon enabled

## Deploy zipkin

```
$ kubectl apply -f k8s-zipkin
```

## Build and deploy services

This script assumes there is a minikube cluster deployed to send docker images to docker engine of the minikube

```
$ ./build_and_deploy_minikube.sh
```

## Execute operations

Execute the operations using Postman. Import the file in folder `postman`.

## Review trace information in zipkin 

Open zipkin app in `http://localhost:9411` and take a look to requests flowing between services:

![zipkin_tolpology](images/Zipkin tolpology.png)

![zipkin_request](images/Zipkin requests.png)

