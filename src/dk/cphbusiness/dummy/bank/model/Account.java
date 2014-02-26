package dk.cphbusiness.dummy.bank.model;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Account {
  private static final Map<String, Account> items = new HashMap<>();
  private long nextId = 1000;
  
  private String number;
  private BigDecimal interest;
  
  public static Collection<Account> list() {
    return items.values();
    }
  
  public static Account find(String number) {
    return items.get(number);
    }
  
  
  
  }
