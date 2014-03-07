package dk.cphbusiness.dummy.bank.control;

import dk.cphbusiness.bank.contract.BankManager;
import dk.cphbusiness.bank.contract.dto.AccountDetail;
import dk.cphbusiness.bank.contract.dto.AccountIdentifier;
import dk.cphbusiness.bank.contract.dto.AccountSummary;
import dk.cphbusiness.bank.contract.dto.CustomerDetail;
import dk.cphbusiness.bank.contract.dto.CustomerIdentifier;
import dk.cphbusiness.bank.contract.dto.CustomerSummary;
import dk.cphbusiness.bank.contract.eto.CustomerBannedException;
import dk.cphbusiness.bank.contract.eto.ExistingCustomerException;
import dk.cphbusiness.bank.contract.eto.InsufficientFundsException;
import dk.cphbusiness.bank.contract.eto.NoSuchAccountException;
import dk.cphbusiness.bank.contract.eto.NoSuchCustomerException;
import dk.cphbusiness.bank.contract.eto.TransferNotAcceptedException;
import dk.cphbusiness.dummy.bank.model.Account;
import dk.cphbusiness.dummy.bank.model.Person;
import java.math.BigDecimal;
import java.util.Collection;
import static dk.cphbusiness.dummy.bank.control.Assembler.*;
import dk.cphbusiness.dummy.bank.model.Transfer;
import java.util.Date;

public class DummyBankManager implements BankManager {

  public DummyBankManager() {
    Person kurt = new Person(
        "121256-0555", "Kurt", "Hansen",
        "Solvej 8", "4550", "Greve", "12345467", "kurt@mail.dk"
        );
    Person sonja = new Person(
        "111190-0444", "Sonja", "Jensen",
        "Månegade 8", "7777", "Baron", "76543210", "sonja@mail.dk"
        );
    Person james = new Person(
        "010256-0777", "James", "Bond",
        "Mars allé 17", "7007", "Ølstykke", "20007007", "james@bond.dk"
        );
    
    Account kurt1 = new Account(new BigDecimal(0.08));
    Account kurt2 = new Account(new BigDecimal(0.025));
    kurt.getOwnedAccounts().add(kurt1);
    kurt.getOwnedAccounts().add(kurt2);
    kurt.getOwnedAccounts().add(new Account(new BigDecimal(0.035)));
    kurt.getOwnedAccounts().add(new Account(new BigDecimal(0.026)));
    
    Account sonja1 = new Account(new BigDecimal(0.1));
    Account sonja2 = new Account(new BigDecimal(0.01));
    sonja.getOwnedAccounts().add(sonja1);
    sonja.getOwnedAccounts().add(sonja2);
    
    Account james1 = new Account(new BigDecimal(0.25));
    james.getOwnedAccounts().add(james1);
    
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
  public CustomerDetail saveCustomer(CustomerDetail customer) throws ExistingCustomerException {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public AccountDetail createAccount(CustomerIdentifier customer, AccountDetail account) throws NoSuchCustomerException, CustomerBannedException {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }
  
  }
