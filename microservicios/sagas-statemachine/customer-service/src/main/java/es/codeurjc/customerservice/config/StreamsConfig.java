package es.codeurjc.customerservice.config;

import org.springframework.cloud.stream.annotation.EnableBinding;

import es.codeurjc.customerservice.stream.kafka.CustomerStream;

@EnableBinding(CustomerStream.class)
public class StreamsConfig {

}
