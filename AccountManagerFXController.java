package accountmanagerfx;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.event.ActionEvent;
import gcubank.Transaction;
import gcubank.Customer;
import gcubank.Account;

public class AccountManagerFXController implements Initializable{

    private Account account;

    @FXML private TableView<Transaction> tableView;
    @FXML private TextField amountField;
    @FXML private TextField typeField;
    @FXML private TextField referenceField;
    @FXML private Label customer;
    @FXML private Label heading;

    @FXML
    private void addTransaction(){
        ObservableList<Transaction> data = tableView.getItems();

        // add new transaction to account and to data for view
        Transaction newTransaction = new Transaction(Double.parseDouble(amountField.getText()),
                typeField.getText(),
                referenceField.getText(),
                currentDateString()
        );
        data.add(newTransaction);
        account.addTransaction(newTransaction);

        // update controls
        amountField.setText("");
        typeField.setText("");
        referenceField.setText("");
        updateHeading();
    }

    public void createAccount(){
        Customer customer1 = new Customer("Fernando" , "Alonso");
        account = new Account(customer1 , "123");
        account.addTransaction(new Transaction(200.0 , "CREDIT" , "REF1" , "23/11/14"));
        account.addTransaction(new Transaction(500.0 , "DEBIT" , "REF2" , "24/11/14"));
        account.addTransaction(new Transaction(100.0 , "CREDIT" , "REF3" , "02/12/14"));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        createAccount();
        // set up data for data table in view
        ObservableList<Transaction> data = tableView.getItems();
        data.setAll(getData());

// show heading information
        customer.setText(account.getCustomerName());
        updateHeading();
    }




    public List<Transaction> getData(){
        return Arrays.asList(
                Arrays.copyOfRange(account.getTransactions(),
                        0,
                        account.getNumberOfTransactions())
        );
    }

    private String currentDateString(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/M/yy" );
        String dateString = formatter.format(new Date());
        return dateString;
    }

    private String formatAsCurrency(double amount){
        NumberFormat currencyFormatter;
        currencyFormatter = NumberFormat.getCurrencyInstance();
        return currencyFormatter.format(amount);
    }
    private void updateHeading(){
        heading.setText(String.format("Account: %s Balance: %s ",
                account.getAccountNumber(),
                formatAsCurrency(account.getBalance()))
        );
    }



}
