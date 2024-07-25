import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

class BankAccount
{
 
    double balance;

 BankAccount(double initialBal){
    balance=initialBal;
 }

 double getBalance(){
    return balance;
 }
 
 void deposit(double amount){
    balance+= amount;
 }
 
 boolean withdraw(double amount){
    if(amount<=balance)
    {balance -= amount;
    return true;}
    else
    return false;
 }

}

class ATM extends JFrame implements ActionListener
{
    BankAccount account;
    JTextField balancefield;
    JTextField amountField;
    JTextArea outputArea;
    JButton chkbalButton;
    JButton depositButton;
    JButton withdrawButton;

    ATM(BankAccount account){
        this.account=account;
        setTitle("ATM Machine");
        setSize(500,300);
        setLayout(new GridLayout(3,1));
    
        
        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayout(2,2));

        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(1,3));
        
        JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayout(1,2));

        JLabel balancelabel=new JLabel("Available Balnce");
        balancefield = new JTextField(Double.toString(account.getBalance()));;
        balancefield.setEditable(false);

        JLabel amountlabel=new JLabel("Amount");
        amountField = new JTextField();

        chkbalButton = new JButton("CHECK BALANCE");
        chkbalButton.addActionListener(this);

        depositButton = new JButton("DEPOSIT");
        depositButton.addActionListener(this);

        withdrawButton = new JButton("WITHDRAW");
        withdrawButton.addActionListener(this);

        JLabel status = new JLabel("STATUS");
        outputArea=new JTextArea();
        outputArea.setEditable(false);

        panel1.add(balancelabel);
        panel1.add(balancefield);
        panel1.add(amountlabel);
        panel1.add(amountField);

        panel2.add(chkbalButton);
        panel2.add(depositButton);
        panel2.add(withdrawButton);

        panel3.add(status);
        panel3.add(outputArea);

        add(panel1);
        add(panel2);
        add(panel3);
    }
    public void actionPerformed(ActionEvent e){
        if(e.getActionCommand().equals("CHECK BALANCE" ))
        outputArea.setText("Your Balance is :"+account.getBalance());
        else if(e.getActionCommand().equals("DEPOSIT" ))
        {
            try 
            {
                double amount = Double.parseDouble(amountField.getText());
                if(amount >0)
                {account.deposit(amount);
                balancefield.setText(Double.toString(account.getBalance()));
                outputArea.setText("Deposit successful");
                amountField.setText("");
                }
                else
                outputArea.setText("Invalid Deposit amount.");
            }
                 catch (NumberFormatException ex)
                 {
                    outputArea.setText("Invalid input. Please enter a valid text.");
                 }
        }
         else if (e.getActionCommand().equals("WITHDRAW"))
        
        {
            try {
                double amount = Double.parseDouble(amountField.getText());
                if(amount >0 && account.withdraw(amount)) 
                {
                    balancefield.setText(Double.toString(account.getBalance()));
                    outputArea.setText("Withdrawl successful");
                    
                
                }
                else if(amount<0)
                outputArea.setText("Invalid Amount.");
                else 
                outputArea.setText("Insufficient Balance.");
                amountField.setText("");
                }    
            

            catch (NumberFormatException ex) {
                outputArea.setText("Invalid input. Please enter a valid text."); 
            }
        }
    }
  

}
public class Task3 {
    public static void main(String[] args) {
        BankAccount useraccount = new BankAccount(1000.00);
        ATM atm = new ATM(useraccount);
        atm.setVisible(true);
    }
}
