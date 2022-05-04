package es.codeurjc.inventoryservice.config;

import org.springframework.cloud.stream.annotation.EnableBinding;

import es.codeurjc.inventoryservice.stream.kafka.InventoryStream;

@EnableBinding(InventoryStream.class)
public class StreamsConfig {

}
