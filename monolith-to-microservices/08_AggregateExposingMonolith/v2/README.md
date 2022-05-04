# Prerequisites

## Login to Dockerhub
```
docker login
```


## Build the employee docker image

```
docker build -t juaneb/aggregate_exposing_monolith_employee_v2 ./employee
```
## Push the employee docker image

```
docker push juaneb/aggregate_exposing_monolith_employee_v2
```

## Build the monolith docker image

```
docker build -t juaneb/aggregate_exposing_monolith_monolith_v2 ./monolith
```
## Push the monolith docker image

```
docker push juaneb/aggregate_exposing_monolith_monolith_v2
```


## Build the invoice service docker image
```
docker build -t juaneb/aggregate_exposing_monolith_catalog_v2 ./catalog
```


## Push the invoice service docker image

```
docker push juaneb/aggregate_exposing_monolith_catalog_v2
```


