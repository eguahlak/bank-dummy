package dk.cphbusiness.dummy.bank.control;

import dk.cphbusiness.bank.contract.BankManager;
import dk.cphbusiness.bank.contract.dto.AccountDetail;
import dk.cphbusiness.bank.contract.dto.AccountIdentifier;
import dk.cphbusiness.bank.contract.dto.AccountSummary;
import dk.cphbusiness.bank.contract.dto.CheckingAccountDetail;
import dk.cphbusiness.bank.contract.dto.CustomerDetail;
import dk.cphbusiness.bank.contract.dto.CustomerIdentifier;
import dk.cphbusiness.bank.contract.dto.CustomerSummary;
import dk.cphbusiness.bank.contract.eto.CustomerBannedException;
import dk.cphbusiness.bank.contract.eto.InsufficientFundsException;
import dk.cphbusiness.bank.contract.eto.NoSuchAccountException;
import dk.cphbusiness.bank.contract.eto.NoSuchCustomerException;
import dk.cphbusiness.bank.contract.eto.TransferNotAcceptedException;
import dk.cphbusiness.dummy.bank.model.Account;
import dk.cphbusiness.dummy.bank.model.Person;
import java.math.BigDecimal;
import java.util.Collection;
import static dk.cphbusiness.dummy.bank.control.Assembler.*;
import dk.cphbusiness.dummy.bank.model.CheckingAccount;
import dk.cphbusiness.dummy.bank.model.Transfer;
import java.util.ArrayList;
import java.util.Date;

public class DummyBankManager implements BankManager {
  private Collection<String> accountTypes;

  public DummyBankManager() {
    accountTypes = new ArrayList<>();
    accountTypes.add("Checking Account");
    accountTypes.add("Money Market Account");
    accountTypes.add("Time Deposit Account");
    
    Person kurt = new Person(
        "121256-0555", "Hr.", "Kurt", "Hansen",
        "Solvej 8", "4550", "Greve", "12345467", "kurt@mail.dk"
        );
    Person sonja = new Person(
        "111190-0444", "Fr.", "Sonja", "Jensen",
        "Månegade 8", "7777", "Baron", "76543210", "sonja@mail.dk"
        );
    Person james = new Person(
        "010256-0777", "Hr.", "James", "Bond",
        "Mars allé 17", "7007", "Ølstykke", "20007007", "james@bond.dk"
        );
    
    Account kurt1 = new CheckingAccount(kurt, new BigDecimal(0.08));
    Account kurt2 = new CheckingAccount(kurt, new BigDecimal(0.025));
    new CheckingAccount(kurt, new BigDecimal(0.035));
    new CheckingAccount(kurt, new BigDecimal(0.026));
    
    Account sonja1 = new CheckingAccount(sonja, new BigDecimal(0.1));
    Account sonja2 = new CheckingAccount(sonja, new BigDecimal(0.01));
    
    Account james1 = new CheckingAccount(james, new BigDecimal(0.25));
    
    new Transfer(new BigDecimal(10000), "From Kurt to James", new Date(), kurt1, james1);
    new Transfer(new BigDecimal(12345), "From Sonja to James", new Date(), sonja1, james1);
    new Transfer(new BigDecimal(5000), "From Kurt to Sonja", new Date(), kurt1, sonja1);
  
    }

  @Override
  public Collection<AccountSummary> listCustomerAccounts(CustomerIdentifier identifier) {
    Person customer = Person.find(identifier.getCpr());
    if (customer == null) return createAccountSummaries(null);
    return createAccountSummaries(customer.getOwnedAccounts());
    }

  @Override
  public AccountDetail transferAmount(BigDecimal amount, AccountIdentifier source, AccountIdentifier target) throws NoSuchAccountException, TransferNotAcceptedException, InsufficientFundsException {
    Account sourceAccount = Account.find(source.getNumber());
    Account targetAccount = Account.find(target.getNumber());
    new Transfer(amount, "transfer", new Date(), sourceAccount, targetAccount);
    return createAccountDetail(sourceAccount);
    }

  @Override
  public AccountDetail showAccountHistory(AccountIdentifier account) {
    return createAccountDetail(Account.find(account.getNumber()));
    }

  @Override
  public Collection<CustomerSummary> listCustomers() {
    return createCustomerSummaries(Person.list());
    }

  @Override
  public Collection<AccountSummary> listAccounts() {
    return createAccountSummaries(Account.list());
    }

  @Override
  public CustomerDetail saveCustomer(CustomerDetail customer) {
    Person person = createCustomerEntity(customer);
    return createCustomerDetail(person);
    }

  @Override
  public AccountDetail createAccount(CustomerIdentifier customerId, AccountDetail detail) throws NoSuchCustomerException, CustomerBannedException {
    Person owner = Person.find(customerId.getCpr());
    if (owner == null) throw new NoSuchCustomerException(customerId);
    if (detail instanceof CheckingAccountDetail) {
      CheckingAccount account = createCheckingAccountEntity(owner, (CheckingAccountDetail)detail);
      return createAccountDetail(account);
      }
    throw new RuntimeException("Unknown Account type");
    }

  @Override
  public CustomerDetail showCustomer(CustomerIdentifier customer) throws NoSuchCustomerException {
    Person person = Person.find(customer.getCpr());
    if (person == null) throw new NoSuchCustomerException(customer);
    return createCustomerDetail(person);
    }

  @Override
  public Collection<String> listAccountTypes() {
    return accountTypes;
    }
  
  }
