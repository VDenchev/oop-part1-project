package contracts;

import java.io.IOException;
import java.util.List;

public interface AccountDAO {
    List<Account> getAll() throws IOException;
    Account get(String username, String password) throws IOException;
    void add(Account account) throws IOException;
    void remove(String username) throws IOException;
}
