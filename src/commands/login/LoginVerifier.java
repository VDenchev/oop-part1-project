package commands.login;

import contracts.Account;
import contracts.Repository;

public class LoginVerifier {
    public static final String FILE_PATH = "src\\files\\accounts.txt";
    public static final String SEPARATOR = ":";
    Repository<Account> repository;

    public LoginVerifier() {
        this.repository = new AccountRepository(FILE_PATH, SEPARATOR);
    }

    public Account getMatchingAccount(String username, String password){
        for (Account account: repository.getRepository()) {
            if (username.equalsIgnoreCase(account.getUsername()) && password.equals(account.getPassword())){
                return account;
            }
        }
        return null;
    }

    public boolean doesAccountExist(String username, String password){
        return getMatchingAccount(username, password) != null;
    }
}
