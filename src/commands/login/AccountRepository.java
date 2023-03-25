package commands.login;

import contracts.Account;
import contracts.Repository;
import enums.PermissionLevel;
import roles.Admin;
import roles.Client;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AccountRepository implements Repository<Account> {
    private List<Account> accounts;
    private BufferedReader br;
    private String separator;
    public AccountRepository(String file, String separator) {
        this.separator = separator;
        try {
            this.br = new BufferedReader(new FileReader(file));
            this.accounts = readUsersFromFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Account> readUsersFromFile() throws IOException {
        List<Account> accounts = new ArrayList<>();
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(separator);
            if (parts.length == 3) {
                String username = parts[0];
                String password = parts[1];
                PermissionLevel permissionLevel = PermissionLevel.valueOf(parts[2]);

                Account account;
                if (permissionLevel == PermissionLevel.ADMIN){
                    account = new Admin(username, password);
                } else {
                    account = new Client(username, password);
                }

                accounts.add(account);
            }
        }
        br.close();
        return accounts;
    }

    @Override
    public List<Account> getRepository() {
        return new ArrayList<>(accounts);
    }
}
