public class Account {
    /**
     * member var
     */
    private String CardId;      //cardID
    private String  UserName;   //username
    private String  Password;   //pwd
    private double money;       //balance
    private double quotaMoney;  //withdrawal limit
    private double[] loans; //loans
    private int loanCount = 0;

    public Account() {
    }

    public Account(String cardId, String userName, String password, double quotaMoney) {
        CardId = cardId;
        UserName = userName;
        Password = password;
        this.quotaMoney = quotaMoney;
        loans = new double[3];
    }

    public Account(String cardId, String userName, String password, double money, double quotaMoney) {
        CardId = cardId;
        UserName = userName;
        Password = password;
        this.money = money;
        this.quotaMoney = quotaMoney;
        loans = new double[3];
    }

    public String getCardId() {
        return CardId;
    }

    public void setCardId(String cardId) {
        CardId = cardId;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public double getQuotaMoney() {
        return quotaMoney;
    }

    public void setQuotaMoney(double quotaMoney) {
        this.quotaMoney = quotaMoney;
    }


    public void addLoanCount() {
        this.loanCount += 1;
    }

    public void subtractLoanCount() {
        this.loanCount -= 1;
    }

    public int getLoanCount() {
        return loanCount;
    }

    public double getTotalLoan() {
        return loans[0] + loans[1] + loans[2];
    }

    public double[] getLoans() {
        return loans;
    }

    public String getLoanString() {
        return loans[0] + ", " + loans[1] + ", " + loans[2];
                //loans.toString();
    }

    public void setLoans(int i, double loan) {
        if(i > 2 || i < 0) {
            System.out.println("Invalid loan array entry");
        } else {
            this.loans[i] = loan;
        }
    }
}

