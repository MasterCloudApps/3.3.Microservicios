# Prerequisites

## Login to Dockerhub
```
docker login
```


## Build the monolith docker image

```
docker build -t juaneb/aggregate_exposing_monolith_monolith_v1 ./monolith
```
## Push the monolith docker image

```
docker push juaneb/aggregate_exposing_monolith_monolith_v1
```


## Build the invoice service docker image
```
docker build -t juaneb/aggregate_exposing_monolith_invoice_v1 ./invoice
```


## Push the invoice service docker image

```
docker push juaneb/aggregate_exposing_monolith_invoice_v1
```


