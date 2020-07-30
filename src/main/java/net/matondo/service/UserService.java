package net.matondo.service;


import net.matondo.domain.User;

import javax.mail.MessagingException;
import java.util.List;

public interface UserService  {
    User register(String firstName, String lastName, String username, String email);

    List<User> getUsers();

    User findUserByUsername(String username);

    User findUserByEmail(String email);
}
