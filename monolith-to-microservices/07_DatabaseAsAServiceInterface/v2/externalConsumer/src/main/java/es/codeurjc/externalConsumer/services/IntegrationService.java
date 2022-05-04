package es.codeurjc.externalConsumer.services;

import es.codeurjc.externalConsumer.models.event.Event;

public interface IntegrationService {

  void processKafkaEvent(Event event);

}
