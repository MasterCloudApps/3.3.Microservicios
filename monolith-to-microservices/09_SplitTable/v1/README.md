# Prerequisites

## Login to Dockerhub
```
docker login
```


## Build the Monolith docker image

```
docker build -t juaneb/split_table_monolith_v1 ./monolith
```
## Push the Order docker image

```
docker push juaneb/split_table_monolith_v1
```