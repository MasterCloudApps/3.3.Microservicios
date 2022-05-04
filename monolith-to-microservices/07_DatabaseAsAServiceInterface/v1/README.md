# Prerequisites

## Login to Dockerhub
```
docker login
```


## Build the Order docker image

```
docker build -t dreyg/database_as_a_service_order_v1 ./order
```
## Push the Order docker image

```
docker push dreyg/database_as_a_service_order_v1
```


## Build the external consumer service docker image
```
docker build -t dreyg/database_as_a_service_external_consumer_v1 ./externalConsumer
```


## Push the external consumer service docker image

```
docker push dreyg/database_as_a_service_external_consumer_v1
```


