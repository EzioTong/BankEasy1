public class Account {
    /**
     * member var
     */
    private String CardId;      //cardID
    private String  UserName;   //username
    private String  Password;   //pwd
    private double money;       //balance
    private double quotaMoney;  //withdrawal limit

    public Account() {
    }

    public Account(String cardId, String userName, String password, double quotaMoney) {
        CardId = cardId;
        UserName = userName;
        Password = password;
        this.quotaMoney = quotaMoney;
    }

    public Account(String cardId, String userName, String password, double money, double quotaMoney) {
        CardId = cardId;
        UserName = userName;
        Password = password;
        this.money = money;
        this.quotaMoney = quotaMoney;
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
}

