package com.deecodeme.hexagonal.account.application.port.out;

import com.deecodeme.hexagonal.account.domain.event.NewAccountCreatedEvent;

public interface PublishAccountEvent {
    void process(NewAccountCreatedEvent event);
}
