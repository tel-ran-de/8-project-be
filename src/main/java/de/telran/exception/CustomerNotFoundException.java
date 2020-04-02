package de.telran.exception;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException (){
      super("Customer with this id not found");
    }
}
