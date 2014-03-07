package dk.cphbusiness.dummy.bank.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public abstract class Account {
  private static final Map<String, Account> items = new HashMap<>();
  private static long nextId = 1000;
  
  private final String number;
  private BigDecimal interest;
  private Collection<Transfer> outgoing = new ArrayList<Transfer>();
  private Collection<Transfer> incoming = new ArrayList<Transfer>();
  private final Person customer;
  
  public static Collection<Account> list() {
    return items.values();
    }
  
  public static Account find(String number) {
    return items.get(number);
    }

  public Account(Person customer, BigDecimal interest) {
    this.number = "4711-"+(nextId++);
    this.interest = interest;
    this.customer = customer;
    customer.getOwnedAccounts().add(this);
    items.put(this.number, this);
    }

  public String getNumber() {
    return number;
    }
  
  public BigDecimal getInterest() {
    return interest;
    }

  public void setInterest(BigDecimal interest) {
    this.interest = interest;
    }

  public Collection<Transfer> getIncoming() {
    return incoming;
    }

  public Collection<Transfer> getOutgoing() {
    return outgoing;
    }

  }
