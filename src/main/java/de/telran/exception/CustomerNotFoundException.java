package de.telran.exception;

public class CustomerNotFoundException extends Exception {

  public   CustomerNotFoundException (){super("Customer with this id not found");}
}
