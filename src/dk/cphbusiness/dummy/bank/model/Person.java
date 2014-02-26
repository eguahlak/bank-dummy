package dk.cphbusiness.dummy.bank.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Person {
  private static final Map<String, Person> items = new HashMap<>();
  
  private final String cpr;
  private String firstName;
  private String lastName;
  private String street;
  private String postalCode;
  private String postalDistrict;
  private String phone;
  private String email;
  private final Collection<Account> ownedAccounts = new ArrayList<>();
  
  public static Collection<Person> list() {
    return items.values();
    }
  
  public static Person find(String cpr) {
    return items.get(cpr);
    }

  public Person(String cpr,
      String firstName,
      String lastName,
      String street,
      String postalCode,
      String postalDistrict,
      String phone,
      String email
      ) {
    this.cpr = cpr;
    this.firstName = firstName;
    this.firstName = firstName;
    this.street = street;
    this.postalCode = postalCode;
    this.postalDistrict = postalDistrict;
    this.phone = phone;
    this.email = email;
    items.put(cpr, this);
    }

  public String getCpr() {
    return cpr;
    }

  public String getFirstName() {
    return firstName;
    }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
    }

  public String getLastName() {
    return lastName;
    }

  public void setLastName(String lastName) {
    this.lastName = lastName;
    }

  public String getStreet() {
    return street;
    }

  public void setStreet(String street) {
    this.street = street;
    }

  public String getPostalCode() {
    return postalCode;
    }

  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
    }

  public String getPostalDistrict() {
    return postalDistrict;
    }

  public void setPostalDistrict(String postalDistrict) {
    this.postalDistrict = postalDistrict;
    }

  public String getPhone() {
    return phone;
    }

  public void setPhone(String phone) {
    this.phone = phone;
    }

  public String getEmail() {
    return email;
    }

  public void setEmail(String email) {
    this.email = email;
    }

  public Collection<Account> getOwnedAccounts() {
    return ownedAccounts;
    }
  
  }
