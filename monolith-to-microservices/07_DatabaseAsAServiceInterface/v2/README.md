# Prerequisites
## Login to Dockerhub
```
docker login
```

## Build the Order docker image
```
docker build -t juaneb/database_as_a_service_order_v2 ./order
```
## Push the Order docker image
```
docker push juaneb/database_as_a_service_order_v2
```

## Build the external consumer service docker image
```
docker build -t juaneb/database_as_a_service_external_consumer_v2 ./externalConsumer
```

## Push the external consumer service docker image
```
docker push juaneb/database_as_a_service_external_consumer_v2
```

## Registering a connector to monitor the inventory database

curl -i -X POST -H "Accept:application/json" -H "Content-Type:application/json" localhost:8083/connectors/ -d @conector-mysql.json