package dk.cphbusiness.dummy.bank.control;

import dk.cphbusiness.bank.contract.BankManager;
import dk.cphbusiness.bank.contract.dto.AccountDetail;
import dk.cphbusiness.bank.contract.dto.AccountIdentifier;
import dk.cphbusiness.bank.contract.dto.AccountSummary;
import dk.cphbusiness.bank.contract.dto.CustomerIdentifier;
import dk.cphbusiness.bank.contract.dto.CustomerSummary;
import dk.cphbusiness.bank.contract.eto.InsufficientFundsException;
import dk.cphbusiness.bank.contract.eto.NoSuchAccountException;
import dk.cphbusiness.bank.contract.eto.TransferNotAcceptedException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class DummyBankManager implements BankManager {
  private final Map<String, Collection<AccountSummary>> allAccounts = new HashMap<>();
  private final Collection<CustomerSummary> customers = new ArrayList<>();

  public DummyBankManager() {
    customers.add(new CustomerSummary(
        "121256-0555", "Hr. Kurt Hansen",
        "Solvej 8, 4550 Greve", "12345467", "kurt@mail.dk"
        ));
    customers.add(new CustomerSummary(
        "111190-0444", "Fr. Sonja Jensen",
        "Månegade 8, 7777 Baron", "76543210", "sonja@mail.dk"
        ));
    customers.add(new CustomerSummary(
        "010256-0777", "Hr. James Bond",
        "Mars allé 17, 7007 Ølstykke", "20007007", "james@bond.dk"
        ));
    
    
    ArrayList<AccountSummary> kurtAccounts = new ArrayList<>();
    kurtAccounts.add(new AccountSummary("1234", "Checking", new BigDecimal(20000)));
    kurtAccounts.add(new AccountSummary("2345", "Time Deposit", new BigDecimal(30000)));
    ArrayList<AccountSummary> sonjaAccounts = new ArrayList<>();
    sonjaAccounts.add(new AccountSummary("3456", "Money Market", new BigDecimal(40000)));
    sonjaAccounts.add(new AccountSummary("4567", "Checking", new BigDecimal(50000)));
    ArrayList<AccountSummary> jamesAccounts = new ArrayList<>();
    jamesAccounts.add(new AccountSummary("007", "Money Market", new BigDecimal(10000000)));
    allAccounts.put("121256-0555", kurtAccounts);
    allAccounts.put("111190-0444", sonjaAccounts);
    allAccounts.put("010256-0777", jamesAccounts);
  
    }

  @Override
  public Collection<AccountSummary> listCustomerAccounts(CustomerIdentifier customer) {
    return allAccounts.get(customer.getCpr());
    }

  @Override
  public AccountDetail transferAmount(BigDecimal amount, AccountIdentifier source, AccountIdentifier target) throws NoSuchAccountException, TransferNotAcceptedException, InsufficientFundsException {
    throw new UnsupportedOperationException("Not supported yet.");
    }

  @Override
  public AccountDetail showAccountHistory(AccountIdentifier account) {
    throw new UnsupportedOperationException("Not supported yet.");
    }

  @Override
  public Collection<CustomerSummary> listCustomers() {
    return customers;
    }
  
  }
