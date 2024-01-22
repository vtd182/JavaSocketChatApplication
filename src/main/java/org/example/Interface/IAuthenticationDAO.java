package org.example.Interface;

import java.sql.SQLException;

public interface IAuthenticationDAO {

    String registerUser(String username, String password) throws SQLException;

    String authenticateUser(String username, String password) throws SQLException;

    void signOutUser();

    String verifyToken(String idToken);
}