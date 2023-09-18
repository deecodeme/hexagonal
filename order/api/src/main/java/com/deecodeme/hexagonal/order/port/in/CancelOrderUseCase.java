package com.deecodeme.hexagonal.order.port.in;

public interface CancelOrderUseCase{
    public CancelOrderResult cancelOrder(CancelOrderCommand cancelOrderCommand);

    class CancelOrderCommand{
        private final String orderId;
        public CancelOrderCommand(String orderId){
            this.orderId = orderId;
        }
        public String getOrderId(){
            return orderId;
        }
    }
}
