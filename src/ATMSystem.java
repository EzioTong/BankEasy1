import javafx.scene.transform.Scale;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
public class ATMSystem {
    public static void main(String[] args) {
        ArrayList<Account> accounts =new ArrayList<>();     //To srore the account information

        //A pre-set account information, for testing, can be deleted
        Scanner sc=new Scanner(System.in);
        Account account=new Account("12345678","123","123",0,100);
        accounts.add(account);

        //Main menu
        while(true){
            System.out.println("-----------Welcom to EasyBank Online System----------------");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.print("What would you like to do? (1/2/3):");
            int command=sc.nextInt();
            switch(command){
                case 1:
                    login(accounts,sc);
                    break;
                case 2:
                    register(accounts,sc);
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Please type again!");
            }
        }

    }
    /**
     * Register
     * @param accounts
     * @param sc
     */
    private static void register(ArrayList<Account> accounts, Scanner sc) {
        System.out.println("===============Welcome to the account opening screen=====================");
        System.out.print("Please type your username:");
        String userName = sc.next();
        String password = null;
        String password1 = null;
        while (true) {
            System.out.print("Please type your password:");
            password = sc.next();
            System.out.print("Please type your password again:");
            password1 = sc.next();
            if(password.equals(password1)){
                break;
            }else{
                System.out.println("Different passwords! Please type again!");
            }
        }
        System.out.print("Please set a daily cash withdrawal limit:");
        Double quotaMoney = sc.nextDouble();

        //Random 8-digit non-repeating account number (separate method)
        String cardId = getRandomId(accounts);

        Account account = new Account(cardId,userName,password,quotaMoney);
        accounts.add(account);
        System.out.println("Congratulations!"+userName+" Sir/Madam, account opening successful! Your CardID is:"+cardId);
    }
    /**
     * Generate a random 8-digit non-repeating account number
     * @param accounts
     * @return
     */
    private static String getRandomId(ArrayList <Account> accounts) {
        Random r=new Random();
        while (true) {
            String cardId="";
            for (int i = 0; i <8 ; i++) {
                cardId+=r.nextInt(10);      //0-9
            }
            Account acc = getAccountByCardId(accounts, cardId);
            if(acc==null){      //non-repeatable
                return cardId;
            }else{
                return null;
            }
        }
    }
    /**
     * Determine if an account exists based on card number
     * @param accounts
     * @param cardId
     * @return
     */
    private static Account getAccountByCardId(ArrayList <Account> accounts,String cardId){
        for (int i = 0; i < accounts.size(); i++) {
            Account a=accounts.get(i);
            if(a.getCardId().equals(cardId)){
                return a;
            }
        }
        return null;    //Account not found!

    }
    /**
     * Login
     * @param accounts
     * @param sc
     */
    private static void login(ArrayList <Account> accounts,Scanner sc) {//登录
        System.out.println("===============Welcome to login screen=====================");
        if(accounts.size()==0){
            System.out.println("There are no accounts in this system, please open an account first!");
            return;     //break this method
        }
        while (true) {
            System.out.print("Please type your CardID:");
            String cardId= sc.next();
            //Find if the account exists based on the cardID
            Account acc=getAccountByCardId(accounts,cardId);
            if(acc!=null){      //Exists
                while (true) {
                    System.out.print("Please type your password:");
                    String password= sc.next();
                    if (acc.getPassword().equals(password)) {
                        System.out.println("Congratulations! "+acc.getUserName()+" Login Successful! "+"CardID: "+acc.getCardId());
                        //Enquiry, transfer and withdrawal functions
                        showCommand(accounts,acc,sc);
                        return;     //Break Login
                        //break;
                    } else {
                        System.out.println("Wrong password! Please type again!");
                    }
                }
                // break;
            }else{
                System.out.println("This CardID account does not exist!");
            }
        }
    }
    /**
     * After Login
     * @param accounts
     * @param acc
     * @param sc
     */
    private static void showCommand(ArrayList accounts,Account acc,Scanner sc) {    //Operation Screen
        while (true) {
            System.out.println("===============Welcome to operation screen=====================");
            System.out.println("1. Display");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Change password");
            System.out.println("6. Exit");
            System.out.println("7. Cancel current account");
            System.out.println("Please select your action by entering: (1/2/3/4/5/6/7)");
            int command1=sc.nextInt();
            switch(command1){
                case 1:
                    query(acc);             //Display
                    break;
                case 2:
                    depositMoney(acc,sc);   //Deposit
                    break;
                case 3:
                    drawMoney(acc,sc);      //Withdraw
                    break;
                case 4:
                    zhuanzhang(accounts,acc,sc);//Transfer
                    break;
                case 5:
                    updatePassword(acc,sc); //Change password
                    return;     //Login again
                case 6:
                    System.out.println("Withdrawal successful, welcome to your next visit!");
                    return;     //break show
                case 7:
                    if(deleteAccount(accounts,acc,sc)){;    //cancel account
                        return;
                    }else{
                        break;
                    }

                default:
                    System.out.println("No such action!");

            }
        }

    }
    /**
     * Display account information
     * @param acc
     */
    private static void query(Account acc) {
        System.out.println("===============Your account information===============");
        System.out.println("CardID:"+acc.getCardId());
        System.out.println("Username:"+acc.getUserName());
        System.out.println("Balance:"+acc.getMoney());
        System.out.println("Daily withdrawal limit:"+acc.getQuotaMoney());
    }
    /**
     * Deposit
     * @param acc
     */
    private static void depositMoney(Account acc,Scanner sc) {
        System.out.println("======================Deposit=========================");
        System.out.print("Please enter the amount of money you want to deposit: ");
        acc.setMoney(sc.nextDouble()+acc.getMoney());
        System.out.println("Deposit successful!");
        query(acc);
    }
    /**
     * Withdrawal
     * @param acc
     * @param sc
     */
    private static void drawMoney(Account acc, Scanner sc) {
        System.out.println("======================Withdrawal=========================");
        if (acc.getMoney() < 100) {
            System.out.println("The account balance is less than €100 and cannot be withdrawn!");
            return;
        } else {
            while (true) {
                System.out.print("Please enter the amount of money you want to withdrawal:");
                double money = sc.nextDouble();
                if (money > acc.getQuotaMoney()) {  //Exceeds limit
                    System.out.println("Exceeds single withdrawal limit, maximal amount of withdrawal is €" + acc.getQuotaMoney() + "!");
                } else {
                    if (money > acc.getMoney()) {   //Exceeds balance
                        System.out.println("No sufficient balance in this account! The balance is €"+acc.getMoney()+"!");
                    } else {
                        System.out.println("Withdrawal of €"+money+" successful!");
                        acc.setMoney(acc.getMoney() - money);
                        query(acc);
                        return;     //break
                    }
                }
            }
        }
    }
    /**
     * Transfer( >= 2 accounts)
     * @param
     * @param acc
     * @param sc
     */
    private static void zhuanzhang(ArrayList accounts, Account acc, Scanner sc) {
        System.out.println("======================Transfer=========================");
        //1. Balance?
        if(accounts.size()<2){
            System.out.println("Less than 2 accounts in current system, cannot transfer money!");
            return;
        }
        //2. >= 2 accounts?
        if(acc.getMoney()==0){
            System.out.println("No balance in your account!");
            return;
        }
        while (true) {
            System.out.print("Please enter the CardID that you want to transfer to:");
            String cardId = sc.next();
            if(acc.getCardId().equals(cardId)){
                System.out.print("Cannot transfer to your own account!");
                continue;   //break loop, next loop
            }
            Account a=getAccountByCardId(accounts,cardId);      //if the cardid exists
            if(a==null){
                System.out.print("Account not found, please enter again:");
            }else{          //Exists
                String userName = a.getUserName();
                String after = userName.substring(1);
                while(true){
                    System.out.print("You will transfer to *"+after+"! Please type the username to continue: ");
                    String before=sc.next();
                    if(userName.startsWith(before)){
                        //if(userName.substring(0,1).equals(before)){
                        System.out.println("Account verification successful!");
                        while(true){
                            System.out.print("Please enter the amount of money that you want to transfer:");
                            Double money=sc.nextDouble();
                            if(money>acc.getMoney()){
                                System.out.print("Insufficient balance! The balance of your account is €"+acc.getMoney()+"!");
                            }else {
                                acc.setMoney(acc.getMoney() - money);
                                a.setMoney(a.getMoney() + money);
                                System.out.println("Transfer successful! Your balance is now €"+acc.getMoney() + ".");
                                return;
                            }
                        }
                    }else{
                        System.out.println("Verification unsuccessful, please enter again!");
                    }
                }
            }

        }
    }
    /**
     * change password
     * @param acc
     * @param sc
     */
    private static void updatePassword(Account acc, Scanner sc) {
        System.out.println("====================Change Password=========================");
        while (true) {
            System.out.print("Please enter your original password:");
            String oldPassword = sc.next();
            if(acc.getPassword().equals(oldPassword)) {
                while (true) {
                    System.out.print("Please enter your new password: ");
                    String newPassword = sc.next();
                    System.out.print("Please enter your new password again: ");
                    String newPassword1 = sc.next();

                    if (newPassword.equals(newPassword1)) {
                        acc.setPassword(newPassword);
                        System.out.println("Password changed successful, please login again!");
                        return;
                    } else {
                        System.out.println("Two entries do not match! ");
                    }
                }
            }else{
                System.out.print("Wrong original password!");
            }
        }
    }
    /**
     * Cancel account
     * @param accounts
     * @param acc
     * @param sc
     */
    private static boolean deleteAccount(ArrayList accounts, Account acc, Scanner sc) {
        System.out.println("====================Cancel Account=========================");
        System.out.print("Would you like to cancel your account? (Y/N)");
        String next = sc.next();
        switch(next){
            case "y":
                if(acc.getMoney()>0){
                    System.out.println("The balance of your account if €"+acc.getMoney()+", you cannot cancel your account!");
                }else{
                    System.out.println("Account cancel successful!");
                    accounts.remove(acc);
                    return true;
                }
                break;
            default:
                System.out.println("Account cancel unsuccessful, your account will still exist.");
        }
        return false;
    }
}
