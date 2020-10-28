package es.codeurjc.gateway.orders;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import es.codeurjc.gateway.proxies.OrderInfo;
import es.codeurjc.gateway.proxies.ProductInfo;
import reactor.util.function.Tuple2;

public class OrderDetails {

	private OrderInfo orderInfo;
	private ProductInfo productInfo;

	public OrderDetails() {
	}

	public OrderDetails(OrderInfo orderInfo, ProductInfo productInfo) {
		this.orderInfo = orderInfo;
		this.productInfo = productInfo;
	}

	public OrderInfo getOrderInfo() {
		return orderInfo;
	}

	public void setOrderInfo(OrderInfo orderInfo) {
		this.orderInfo = orderInfo;
	}

	public ProductInfo getProductInfo() {
		return productInfo;
	}

	public void setProductInfo(ProductInfo productInfo) {
		this.productInfo = productInfo;
	}

	public static OrderDetails makeOrderDetails(Tuple2<OrderInfo, ProductInfo> info) {
		return new OrderDetails(info.getT1(), info.getT2());
	}

	@Override
	public boolean equals(Object o) {
		return EqualsBuilder.reflectionEquals(this, o);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
