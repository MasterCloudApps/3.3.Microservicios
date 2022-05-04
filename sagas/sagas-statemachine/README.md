# Ejecución

## Arranque de mysql y kafka
docker-compose -f docker-compose-mysql.yaml up

## Parada de mysql y Kafka
docker-compose -f docker-compose-mysql.yaml down

## Terminal en gateway-service
mvn spring-boot:run

## Terminal en order-service
mvn spring-boot:run

## Terminal en inventory-service
mvn spring-boot:run

## Terminal en customer-service
mvn spring-boot:run

# API endpoints

Se adjunta "collection" en la carpeta postman 

POST customer (name + creditLimit)
GET customer (id)

POST product (name + reference + stockQuantity)
PUT product (id + name + reference + stockQuantity), en este caso se añade o resta el stockQuantity al ya persistido en base de datos.
GET product (id)

GET state order (id) devuelve el estado del pedido
GET order completo /orders/{orderId}/products/{productId}
POST order (orderTotal, customerId, productName, productReference, quantity), en este caso se solicita la ejecución de la saga del pedido.

Varios escenarios posibles:

# orderState    rejectionReason    comentario
  APPROVED                             el pedido cumple con todos los requisitos del sistema
  REJECTED        SOLD_OUT             el pedido se ha solicitado con un producto que no existe en el inventario o que la cantidad solicitada excede la que hay en el stock.
  REJECTED        UNKNOWN_CUSTOMER     el pedido se ha solicitado por un cliente que no existe en el sistema.   
  REJECTED        INSUFFICIENT_CREDIT  el pedido se ha solicitado por un total que excede el credito limite del cliente 
 
# Notas  

Implementación de la saga con los elementos que nos proporciona el stack de Spring, sin necesidad de utilizar el eventuate tram.

Esta prueba de concepto esta implementada con Spring Boot, Spring Statemachine y Spring Cloud Stream Kafka entre otras cosas.

Inspirado en el proyecto https://github.com/fernandoBRS/saga-coordinator-java. En este proyecto se contempla la persistencia de la maquina de estado en una redis y los eventos en una mongodb en caso de crash.