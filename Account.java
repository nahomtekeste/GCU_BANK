package gcubank;

/**
 * represents a bank account
 *
 */
public class Account
{
    // the customer
    private Customer customer;
    // the account number
    private String accountNumber;
    // the account balance
    private double balance;
    // the maximum number of transactions
    private final int MAX_TRANSACTIONS = 20;         
    // array to hold transactions
    private Transaction[] transactions;   
    // the number of Transactions in the array                                 
    private int numberOfTransactions;       


    /**
     * Constructor for objects of type Account
     *
     * @param customer the customer
     * @param accountNumber the account number
     */
    public Account(Customer customer, String accountNumber)
    {
        transactions = new Transaction[MAX_TRANSACTIONS];   
        numberOfTransactions = 0;
        this.customer = customer;
        this.accountNumber = accountNumber;
        balance = 0.0f;
    }
    
     /**
     * returns the account number
     *
     * @return the account number
     */
    public String getAccountNumber()
    {
        return accountNumber;
    }

    /**
     * returns the customer name
     *
     * @return the customer name
     */
    public String getCustomerName()
    {
        return (customer.getFirstName() + " " + customer.getLastName());
    }

    /**
     * returns the account balance
     *
     * @return the balance
     */
    public double getBalance()
    {
        return balance;
    }
    
    /**
     * returns the number of transactions
     *
     * @return the number of transactions
     */
    public int getNumberOfTransactions()
    {
        return numberOfTransactions;
    }
    
    /**
     * prints details of the account and its current transactions
     * 
     */
    public void printDetails()  
    { 
        System.out.println("This Account object (" + this.toString() + 
          ") contains the following Transaction objects:");
        for(int i=0;i<numberOfTransactions;i++)
        {
          System.out.println(transactions[i].toString());
        }
    }

    /**
     * adds a new Transaction object to the array of contained objects
     *
     * @param newTransaction  the new Transaction to add
     */
    public void addTransaction(Transaction newTransaction)
    {
        if(numberOfTransactions < MAX_TRANSACTIONS-1)
        {
            transactions[numberOfTransactions] = newTransaction;
            numberOfTransactions++;
        }
        updateBalance();
    }

    /**
     * gets the array of contained Transaction objects
     *
     * @return    the array of contained Transaction objects
     */
    public Transaction[] getTransactions()
    {
        return transactions;
    }
    
    /**
     * gets a contained Transaction object specified by its reference property.
     *
     * @param reference  the reference property value of the target object
     * @return    the target Transaction object
     */
    public Transaction getTransaction(String reference)
    {
       Transaction target = null;
       int i = 0;
       while(i<numberOfTransactions)
       {
           if(transactions[i].getReference().equals(reference))
           {
               target = transactions[i];
               break;
           }
           i++;
       }
       return target;
    }
    
   /**
     * removes a contained Transaction object specified by its reference property.
     *
     * @param reference  the reference property value of the target object
     */
    public void removeTransaction(String reference)
    {
       int i = 0;
       boolean found = false;
       while(i<numberOfTransactions)
       {
           if(transactions[i].getReference().equals(reference))
           {
               found = true;
               break;
           }
           i++;
       }
       if(found)
       {
           for(int j=i;j<numberOfTransactions;j++)
           {
               transactions[j] = transactions[j+1];
           }
           transactions[numberOfTransactions]=null;
           numberOfTransactions--;
       }
       updateBalance();
    }

    private void updateBalance()
    {
        balance = 0.0f;
        for(int i=0;i<numberOfTransactions;i++)
        {
            Transaction trans = transactions[i];
            if (trans.getType().equals("CREDIT"))
            {
                balance = balance + trans.getAmount();
            }
            else if(trans.getType().equals("DEBIT"))
            {
                balance = balance - trans.getAmount();
            }
        }
    }

   /**
     * returns a String representation of this object
     *
     * @return  a String represention of this object
     */
    public String toString()
    {
        return String.format("Account number: %s, Customer: %s", 
            accountNumber, this.getCustomerName());
    }

}


/*
 *Source file generated by patternCoder for BlueJ Version 0.5.3.004.
 *For more info, please visit http://www.patterncoder.org.
 */