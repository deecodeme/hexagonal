package com.deecodeme.hexagonal.order.port.in;

public class CancelOrderResult {
    private final boolean success;
    private final String failureReason;

    private CancelOrderResult(boolean success, String failureReason) {
        this.success = success;
        this.failureReason = failureReason;
    }

    public static CancelOrderResult success() {
        return new CancelOrderResult(true, null);
    }

    public static CancelOrderResult failure(String failureReason) {
        return new CancelOrderResult(false, failureReason);
    }

    public boolean isSuccess() {
        return success;
    }

    public String getFailureReason() {
        return failureReason;
    }
}
