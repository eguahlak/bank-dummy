package dk.cphbusiness.dummy.bank.model;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Transfer implements Comparable<Transfer> {
  private static final Map<Integer, Transfer> items = new HashMap<>();
  private static int nextId = 1;
  
  public static Collection<Transfer> list() {
    return items.values();
    }
  
  public static Transfer find(int id) {
    return items.get(id);
    }
  
  private final int id;
  private final BigDecimal amount;
  private final String text;
  private final Date date;
  private final Account source;
  private final Account target;

  public Transfer(BigDecimal amount, String text, Date date, Account source, Account target) {
    this.id = nextId++;
    this.amount = amount;
    this.text = text;
    this.date = date;
    this.source = source;
    source.getOutgoing().add(this);
    this.target = target;
    target.getIncoming().add(this);
    items.put(this.id, this);
    }

  public int getId() {
    return id;
    }

  public BigDecimal getAmount() {
    return amount;
    }

  public Date getDate() {
    return date;
    }

  public String getText() {
    return text;
    }

  public Account getSource() {
    return source;
    }

  public Account getTarget() {
    return target;
    }

  @Override
  public int compareTo(Transfer other) {
    return this.date.compareTo(other.date);
    }
  
  }
