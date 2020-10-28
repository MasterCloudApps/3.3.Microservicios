package es.codeurjc.shop.orders.saga;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;

import es.codeurjc.shop.orders.dto.CustomerDto;
import es.codeurjc.shop.orders.dto.OperationEnum;
import es.codeurjc.shop.orders.dto.ProductDto;
import es.codeurjc.shop.orders.entity.Order;
import es.codeurjc.shop.orders.exception.NotEnoughCreditException;
import es.codeurjc.shop.orders.exception.NotEnoughStockException;
import es.codeurjc.shop.orders.proxies.CustomersService;
import es.codeurjc.shop.orders.proxies.ProductsService;
import es.codeurjc.shop.orders.repository.OrderRepository;

@Component
public class CreateOrderSaga {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private CustomersService customers;

	@Autowired
	private ProductsService products;

	public Order createOrder(final Order newOrder) throws JsonProcessingException {

		CustomerDto customer = customers.fetchCustomer(newOrder.getCustomerId());
		ProductDto product = products.fetchProduct(newOrder.getProductId());

		if (!product.hasEnoughStock(newOrder.getProductQuantity())) {
			throw new NotEnoughStockException();
		}
		
		if (!customer.hasEnoughCredit(newOrder.getTotalCost())) {
			throw new NotEnoughCreditException();
		}
		
		products.modifyProductStock(product.getId(), OperationEnum.DEDUCT.toString(),
				newOrder.getProductQuantity());
		
		try {
		
			customers.modifyCustomerCredit(customer.getId(), OperationEnum.DEDUCT.toString(), newOrder.getTotalCost());

		} catch (NotEnoughCreditException e) {
			
			// Compensation: Refund product stock
			products.modifyProductStock(product.getId(), OperationEnum.ADD.toString(),
					newOrder.getProductQuantity());
			
			throw e;
		}
		
		return orderRepository.save(newOrder);
	}

}