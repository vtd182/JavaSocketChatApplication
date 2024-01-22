package org.example.DAO;

import org.example.Interface.IAuthenticationDAO;

import java.sql.SQLException;

public class MySQLAuthenticationDAO implements IAuthenticationDAO {
    @Override
    public String registerUser(String username, String password) throws SQLException {
        return null;
    }

    @Override
    public String authenticateUser(String username, String password) throws SQLException {
        return null;
    }

    @Override
    public void signOutUser() {

    }

    @Override
    public String verifyToken(String idToken) {
        return null;
    }
}
