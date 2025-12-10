import java.util.Scanner;
class Account{
    private String accountNumber;
    private String pin;
    private double balance;

    public Account(String accountNumber, String pin, double initialBalance){
        this.accountNumber=accountNumber;
        this.pin=pin;
        this.balance=initialBalance;
    }
    public boolean verifyPin(String inputPin){
        return this.pin.equals(inputPin);
    }
    public double getBalance(){
        return balance;
    }
    public boolean withdraw(double amount){
        if(amount<=0){
            System.out.println("Enter an amount greater than 0");
        return false;
    }
    if(amount>balance){
        System.out.println("Entered amount exceeds the balance");
        return false;
    }
    balance -= amount;
    return true;
}
public boolean deposit(double amount){
    if(amount<=0){
        System.out.println("Enter an amount greater than 0");
        return false;
    }
    balance += amount;
    return true;
    }
    public String getAccountNumber(){
        return accountNumber;
    }
}
public class ATMSimulation{
    private static final int MAX_PIN_ATTEMPTS=3;
    private Account account;
    private Scanner scanner;

    public ATMSimulation(Account account){
        this.account= account;
        this.scanner=new Scanner(System.in);
    }
    public void start(){
        System.out.println("Welcome to the ATM World!");
        if(!authenticate()){
            System.out.println("Too many incorrect PIN attempts.");
            return;
        }

        boolean sessionActive= true;
        while(sessionActive){
            printMenu();
            int choice= readInt("Choose an option:");
            switch(choice){
                case 1:
                    showBalance();
                    break;
                case 2:
                    doDeposit();
                    break;
                case 3:
                    doWithdraw();
                    break;
                case 4:
                    System.out.println("Logging out. Goodbye!");
                    sessionActive=false;
                    break;
                default:
                    System.out.println("Invalid option. please try again.");
            }
            System.out.println();
        }
    }
    private boolean authenticate(){
        for(int attempt= 1; attempt <= MAX_PIN_ATTEMPTS; attempt++){
            String inputPin = readString("Enter your PIN:");
            if(account.verifyPin(inputPin)){
                System.out.println("PIN verified. Logged in to account"+account.getAccountNumber());
                return true;
            }else{
                System.out.printf("Incorrect PIN. Attempts left: %d%n", MAX_PIN_ATTEMPTS - attempt);
            }
            }
            return false;
        }
        private void printMenu(){
            System.out.println("===ATM MENU===");
            System.out.println("1.check balance");
            System.out.println("2.Deposit");
            System.out.println("3.Withdraw");
            System.out.println("4.Exit");
       }

       private void showBalance(){
        System.out.printf("Your current balance is: ₹%.2f%n ", account.getBalance());
       }

       private void doDeposit(){
        double amount=readDouble("Enter deposit amount:");
        if(account.deposit(amount)){
            System.out.printf("Deposit successful. New balance: ₹%.2f%n", account.getBalance());
        }
       }

       private void doWithdraw(){
        double amount=readDouble("Enter withdrawal amount:");
        if(account.withdraw(amount)){
            System.out.printf("Withdrawal successful. New balance: ₹%.2f%n", account.getBalance());
        }
       }

       private String readString(String prompt){
        System.out.println(prompt);
        return scanner.nextLine().trim();
        }

        private int readInt(String prompt){
            while(true){
                System.out.println(prompt);
                String line= scanner.nextLine().trim();
                try{
                    return Integer.parseInt(line);
                }catch (NumberFormatException e){
                    System.out.println("Please enter a valid integer."); 
                }
            }
        }

        private double readDouble(String prompt){
            while(true){
                System.out.println(prompt);
                String line=scanner.nextLine().trim();
                try{
                    return Double.parseDouble(line);
                }catch(NumberFormatException e){
                    System.out.println("Please enter a valid number (eg., 1000, 250).");
                }
            }
        }

        public static void main(String[] args){
            Account demoAccount= new Account("AC123456", "1234", 5000.00);
            ATMSimulation atm= new ATMSimulation(demoAccount);
            atm.start();
        }




       
}