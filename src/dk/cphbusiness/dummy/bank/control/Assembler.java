package dk.cphbusiness.dummy.bank.control;

import dk.cphbusiness.bank.contract.dto.AccountDetail;
import dk.cphbusiness.bank.contract.dto.AccountSummary;
import dk.cphbusiness.bank.contract.dto.CheckingAccountDetail;
import dk.cphbusiness.bank.contract.dto.CustomerDetail;
import dk.cphbusiness.bank.contract.dto.CustomerSummary;
import dk.cphbusiness.bank.contract.dto.TransferSummary;
import dk.cphbusiness.dummy.bank.model.Account;
import dk.cphbusiness.dummy.bank.model.CheckingAccount;
import dk.cphbusiness.dummy.bank.model.Person;
import dk.cphbusiness.dummy.bank.model.Transfer;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Assembler {
  
  public static CustomerSummary createCustomerSummary(Person customer) {
    return new CustomerSummary(
      customer.getCpr(),
      customer.getFirstName()+" "+customer.getLastName(),
      customer.getStreet()+", "+customer.getPostalCode()+" "+customer.getPostalDistrict(),
      customer.getPhone(),
      customer.getEmail()
      );
    }
  
  public static Collection<CustomerSummary> createCustomerSummaries(Collection<Person> customers) {
    Collection<CustomerSummary> summaries = new ArrayList<>();
    if (customers == null) return summaries;
    for (Person customer : customers) {
      summaries.add(createCustomerSummary(customer));
      }
    return summaries;
    }
  
  public static AccountSummary createAccountSummary(Account account) {
    return new AccountSummary(
        account.getNumber(),
        "Checking Account",
        new BigDecimal(100000)
        );
    }
  
  public static Collection<AccountSummary> createAccountSummaries(Collection<Account> accounts) {
    Collection<AccountSummary> summaries = new ArrayList<>();
    if (accounts == null) return summaries;
    for (Account account : accounts) summaries.add(createAccountSummary(account));
    return summaries;
    }

  public static TransferSummary createTransferSummary(Account account, Transfer transfer) {
    if (transfer.getSource() == account) 
        return new TransferSummary(
            transfer.getDate(),
            transfer.getAmount().negate(),
            transfer.getTarget().getNumber()
            );
    else 
        return new TransferSummary(
            transfer.getDate(),
            transfer.getAmount(),
            transfer.getSource().getNumber()
            );
    }

  public static AccountDetail createAccountDetail(Account account) {
    List<Transfer> transfers = new ArrayList<>();
    transfers.addAll(account.getIncoming());
    transfers.addAll(account.getOutgoing());
    Collections.sort(transfers);
    System.err.println("Transfers for #"+account.getNumber()+" "+transfers.size());
    Collection<TransferSummary> transferSummaries = new ArrayList<>();
    for (Transfer transfer : transfers) transferSummaries.add(createTransferSummary(account, transfer));
    return new CheckingAccountDetail(account.getNumber(), account.getInterest(), transferSummaries);
    }
  
  public static CustomerDetail createCustomerDetail(Person customer) {
    return new CustomerDetail(
        customer.getCpr(),
        customer.getTitle(),
        customer.getFirstName(),
        customer.getLastName(),
        customer.getStreet(),
        customer.getPostalCode(),
        customer.getPostalDistrict(),
        customer.getPhone(),
        customer.getEmail()
        );
    }
  
  public static Person createCustomerEntity(CustomerDetail detail) {
    return new Person(
        detail.getCpr(),
        detail.getTitle(),
        detail.getFirstName(),
        detail.getLastName(),
        detail.getStreet(),
        detail.getPostalCode(),
        detail.getPostalDistrict(),
        detail.getPhone(),
        detail.getEmail()
        );
    }
  
  public static CheckingAccount createCheckingAccountEntity(Person customer, CheckingAccountDetail detail) {
    return new CheckingAccount(customer, detail.getInterest());
    }
  
  }
