package ch.ipt.kafka.producer;

import ch.ipt.kafka.techbier.Account;

import java.security.SecureRandom;

public enum AccountDataEnum {

    ACCOUNT1("1", "Max", "Muster", "Hauptstrasse 1", "Zurich", 1234567891123456L, "Debit"),
    ACCOUNT2("2", "Fritz", "Meier", "Bahnhofstrasse 7", "Bern", 9874236514953215L, "Credit"),
    ACCOUNT3("3", "Karl", "Schmidt", "Rathausgasse 42", "Chur", 9746215947623147L, "Credit"),
    ACCOUNT4("4", "Susi", "Schneider", "Langstrasse 123", "Genf", 7841354879531547L, "Credit"),
    ACCOUNT5("5", "Beat", "Fischer", "Poststrasse 79", "Basel", 1789453651489745L, "Debit"),
    ACCOUNT6("6", "Trudi", "Fischer", "Poststrasse 79", "Basel", 1549874612568745L, "Debit"),
    ACCOUNT7("7", "Frida", "Weber", "Schulstrasse 9", "Luzern", 1597845612348765L, "Credit"),
    ACCOUNT8("8", "Ernst", "Becker", "Kirchgasse 12", "St. Gallen", 3658478698456874L, "Debit");

    private final String accountId;
    private final String surname;
    private final String lastname;
    private final String street;
    private final String city;
    private final Long pan;
    private final String cardType;

    private AccountDataEnum(String accountId, String surname, String lastname, String street, String city, Long pan, String cardType) {
        this.accountId = accountId;
        this.surname = surname;
        this.lastname = lastname;
        this.street = street;
        this.city = city;
        this.pan = pan;
        this.cardType = cardType;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getSurname() {
        return surname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getCardType() {
        return cardType;
    }

    public long getPan() {
        return pan;
    }

    public static AccountDataEnum getRandomEnum(){
        return AccountDataEnum.values()[new SecureRandom().nextInt(AccountDataEnum.values().length)];
    }

    public static Account getAccount(AccountDataEnum accountEnum){
        return new Account(accountEnum.getAccountId(), accountEnum.getSurname(), accountEnum.getLastname(), accountEnum.getStreet(), accountEnum.getCity());
    }

}
