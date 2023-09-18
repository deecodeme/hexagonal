package com.deecodeme.hexagonal.order.port.in;

public interface CancelOrderUseCase{
    public CancelOrderResult cancelOrder(CancelOrderCommand cancelOrderCommand);

    class CancelOrderCommand{
        private final String orderId;
        private CancelOrderCommand(String orderId){
            this.orderId = orderId;
        }

        public static CancelOrderCommand of(String orderId){
            return new CancelOrderCommand(orderId);
        }
        public String getOrderId(){
            return orderId;
        }
    }
}
