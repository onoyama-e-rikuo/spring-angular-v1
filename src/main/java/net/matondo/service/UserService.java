package net.matondo.service;


import net.matondo.domain.User;
import net.matondo.exception.domain.EmailExistException;
import net.matondo.exception.domain.UserNotFoundException;
import net.matondo.exception.domain.UsernameExistException;

import javax.mail.MessagingException;
import java.util.List;

public interface UserService  {
    User register(String firstName, String lastName, String username, String email) throws UserNotFoundException, UsernameExistException, EmailExistException, MessagingException;

    List<User> getUsers();

    User findUserByUsername(String username);

    User findUserByEmail(String email);
}
