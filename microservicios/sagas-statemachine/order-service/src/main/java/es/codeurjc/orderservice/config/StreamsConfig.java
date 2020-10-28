package es.codeurjc.orderservice.config;

import org.springframework.cloud.stream.annotation.EnableBinding;

import es.codeurjc.orderservice.stream.kafka.OrderStream;

@EnableBinding(OrderStream.class)
public class StreamsConfig {

}
