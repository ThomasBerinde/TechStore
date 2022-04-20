package bll.credentials;

import dao.DataAccessObject;
import exceptions.CredentialException;
import model.Credentials;

import java.util.List;

public class CredentialsCheck {

    /** Returns the id of the user that logs on or -1 in case of error */
    public static Long check(Credentials credentials) throws CredentialException {

        DataAccessObject<Credentials> dao = new DataAccessObject<>(Credentials.class);
        List<Credentials> users = dao.find("username", credentials.getUsername());

        if (users.size() == 1) {
            if (users.get(0).getPassword().equals(credentials.getPassword())) {
                return users.get(0).getId();
            } else {
                throw new CredentialException("Wrong password");
            }
        } else if (users.size() == 0) {
            throw new CredentialException("Wrong username");
        } else {
            throw new CredentialException("Duplicate usernames");
        }
    }
}
