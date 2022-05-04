# Prerequisites

## Login to Dockerhub
```
docker login
```


## Build the customer docker image

```
docker build -t juaneb/split_table_customer_v2 ./customer
```
## Push the customer docker image

```
docker push juaneb/split_table_customer_v2
```


## Build the finance service docker image
```
docker build -t juaneb/split_table_finance_v2 ./finance
```


## Push the invoice service docker image

```
docker push juaneb/split_table_finance_v2
```


