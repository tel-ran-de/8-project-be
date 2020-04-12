package de.telran.exception;

import lombok.Getter;
import lombok.Setter;

public class CustomerNotFoundException extends RuntimeException {

    @Getter
    private final Long customerId;

    public CustomerNotFoundException(Long customerId) {
      super("Customer not found");
      this.customerId = customerId;
    }
}
