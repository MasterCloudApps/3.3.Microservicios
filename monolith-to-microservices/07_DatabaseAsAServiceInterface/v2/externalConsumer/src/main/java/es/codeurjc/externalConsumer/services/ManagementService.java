package es.codeurjc.externalConsumer.services;


import es.codeurjc.externalConsumer.models.event.EventPayload;

public interface ManagementService {
  
  void manageProduct(EventPayload payload);
  
}
