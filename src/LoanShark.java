/** A loan shark is a type of banker who offers loans to players. */

import java.util.Scanner;

public class LoanShark {

    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String BLUE = "\u001B[34m";
    public static final String YELLOW = "\u001b[33m";
    public static final String MAGENTA = "\u001b[35m";
    public static final String CYAN = "\u001b[36m";
    public static final String BLACK = "\u001B[30m";
    public static final String WHITEbkgnd = "\u001B[47m";
    public static final String YELLOWbkgnd = "\u001B[43m";

    public static final String BOLD = "\u001B[1m\n";

    protected double interestRate = .15;
    protected double interest;
    protected double loanAmount;
    protected double accountBalance;
    protected String name;

    public LoanShark(String name) { //loan shark constructor
        this.name = name;
    }

    public void loanToPlayer(Players player){ // method to loan to the player, only called when players wallet = 0
        double interestValue = interestRate * 100.0; // converting the interest to a percentage for display in the console
        System.out.println("A "+ this.name +" offers you a loan with a %"+ interestValue+ " interest rate.");
        System.out.println("How much would you like to borrow?");
        System.out.println("$:");
        Scanner scanner = new Scanner(System.in);
        loanAmount = scanner.nextDouble(); // getting a double value for the loan amount for smoother math using interest rates
        player.wallet += loanAmount; // adding the loan amount to the player wallet
        accountBalance += loanAmount; // adding the loan amount to the loan shark account balance
        setInterestRate(); // setting the interest rate for the loan shark
        getBalance();   // displaying the loan shark account balance
        CardGame.loanActive = true; // setting this boolean to true to indicate that the player has a loan active
    }

    public void setInterestRate() { // method to set the interest rate for the players loan
        this.interest = accountBalance * interestRate; //calculating the interest for the loan
        accountBalance += interest; // adding the interest to the account balance
        System.out.printf("%nYou have been loaned "+GREEN+"$"+ loanAmount + RESET+" with "+RED+"$" + this.interest+RESET+" of interest.");
    }

    public void collectLoan(Players player){ // method collects loan from player if their bank is above 3000
        System.out.printf("%nHow much would you like to pay back?");
        Scanner scanner = new Scanner(System.in);
        double payment = scanner.nextDouble(); //double value for the payment amount
        if(payment <= accountBalance){ // if the payment is less than or equal to the account balance
            accountBalance -= payment; // subtracting the payment from the account balance
            player.wallet -= payment; // subtracting the payment from the player wallet
            System.out.println("You have paid back $"+payment+".");
        } else if (payment >= accountBalance) { // if the payment is greater than the account balance, the player is paid off
            payment = accountBalance;// trying to make the payment equal to the account balance if the payment is greater than account balance.
            System.out.printf("You have paid off your loan of $%f." , payment);
            accountBalance -= payment; // subtracting the payment from the account balance
            player.wallet -= payment; // subtracting the payment from the player wallet
            CardGame.loanActive = false; //deactivating the loan shark
        }
        getBalance(); // show current balance
        if(accountBalance == 0){
            CardGame.loanActive = false; //deactivate the loan shark if the account balance is 0
        }
    }

    public void getBalance(){ //displays the loan shark account balance
        System.out.printf("%nYou now owe: "+RED+"$"+ accountBalance+RESET);
    }

    public String getName() {
        return this.name;
    }

}
