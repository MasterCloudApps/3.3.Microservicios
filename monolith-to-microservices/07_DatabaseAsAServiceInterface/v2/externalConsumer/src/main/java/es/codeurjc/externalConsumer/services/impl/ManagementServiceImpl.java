package es.codeurjc.externalConsumer.services.impl;

import es.codeurjc.externalConsumer.dtos.requests.OrderRequestDto;
import es.codeurjc.externalConsumer.enums.SourceDatabaseOperation;
import es.codeurjc.externalConsumer.models.event.EventPayload;
import es.codeurjc.externalConsumer.services.ManagementService;
import es.codeurjc.externalConsumer.services.OrderMapperKafkaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static es.codeurjc.externalConsumer.utils.Consts.UNDEFINED_OPERATION_ERROR_MESSAGE;


@Slf4j
@Service
public class ManagementServiceImpl implements ManagementService {

  private final OrderMapperKafkaService orderMapperKafkaService;

  public ManagementServiceImpl(OrderMapperKafkaService orderMapperKafkaService){
    this.orderMapperKafkaService = orderMapperKafkaService;
  }

  @Override
  public void manageProduct(EventPayload payload){
    SourceDatabaseOperation operation = SourceDatabaseOperation.fromId(payload.getOp());
    switch (operation){
      case CREATE:
      case UPDATE:
        OrderRequestDto orderRequestDto = OrderRequestDto
          .builder()
          .id(Long.valueOf(payload.getAfter().get("id").toString()))
          .dateOrder(payload.getAfter().get("dateorder").toString())
          .purchaser(payload.getAfter().get("purchaser").toString())
          .address(payload.getAfter().get("address").toString())
          .totalPrice(payload.getAfter().get("totalprice").toString())
          .build();
        log.info("Before create");
        orderMapperKafkaService.create(orderRequestDto);
        break;
      case DELETE:
        Long orderId = Long.valueOf(payload.getAfter().get("id").toString());
        orderMapperKafkaService.delete(orderId);
        break;
      default:
        log.error(UNDEFINED_OPERATION_ERROR_MESSAGE);
    }
  }

}
