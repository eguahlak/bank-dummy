package dk.cphbusiness.dummy.bank.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Person {
  private static final Map<String, Person> items = new HashMap<>();
  
  private String cpr;
  private String name;
  
  public static Collection<Person> list() {
    return items.values();
    }
  
  public static Person find(String cpr) {
    return items.get(cpr);
    }

  public Person(String cpr, String name) {
    this.cpr = cpr;
    this.name = name;
    items.put(cpr, this);
    }

  public String getCpr() {
    return cpr;
    }

  public String getName() {
    return name;
    }

  public void setName(String name) {
    this.name = name;
    }
  
  }
