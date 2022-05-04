# Prerequisites

## Login to Dockerhub
```
docker login
```


## Build the monolith docker image

```
docker build -t juaneb/database_view_monolith_v2 ./monolith
```
## Push the monolith docker image

```
docker push juaneb/database_view_monolith_v2
```


## Build the loyalty service docker image
```
docker build -t juaneb/database_view_loyalty_v2 ./loyalty
```


## Push the loyalty service docker image

```
docker push juaneb/database_view_loyalty_v2
```


