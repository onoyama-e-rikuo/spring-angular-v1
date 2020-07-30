package net.matondo.service.impl;

import net.matondo.domain.User;
import net.matondo.domain.UserPrincipal;
import net.matondo.exception.domain.EmailExistException;
import net.matondo.exception.domain.UserNotFoundException;
import net.matondo.exception.domain.UsernameExistException;
import net.matondo.repository.UserRepository;
import net.matondo.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
@Qualifier("userDetailsService")
public class UserServiceImpl implements UserService, UserDetailsService {
    private UserRepository userRepository;
    private Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
        if (user == null) {
            LOGGER.error("User not found by username" + username);
            throw new UsernameNotFoundException("User not found by username" + username);
        } else {
            user.setLastLoginDateDisplay(user.getLastLoginDate());
            user.setLastLoginDate(new Date());
            userRepository.save(user);
            UserPrincipal userPrincipal = new UserPrincipal(user);
            LOGGER.info("User not found by username" + username);
            return userPrincipal;
        }
    }

    @Override
    public User register(String firstName, String lastName, String username, String email) {
        return null;
    }

    private User validateNewUsernameAndEmail(String currentUsername, String newUsername, String newEmail) throws UserNotFoundException, UsernameExistException, EmailExistException {
        if (StringUtils.isNotBlank(currentUsername)) {
            User currentUser = findUserByUsername(currentUsername);
            if (currentUser == null) {
            throw new UserNotFoundException("No user found by username" + currentUsername);
            }

            User userByUsername = findUserByUsername(newUsername);
            if (userByUsername != null && currentUser.getId().equals(userByUsername.getId())) {
                throw new UsernameExistException("Username already exists");
            }

            User userByEmail = findUserByEmail(newEmail);
            if (userByEmail != null && currentUser.getId().equals(userByUsername.getId())) {
                throw new EmailExistException("Email already exists");
            }
            return currentUser;
        } else {
            User userByUsername = findUserByUsername(newUsername);
            if (userByUsername != null) {
                throw new UsernameExistException("Username already exists");
            }

            User userByEmail = findUserByEmail(newEmail);
            if (userByEmail != null) {
                throw new EmailExistException("Email already exists");
            }
            return null;
        }
    }

    @Override
    public List<User> getUsers() {
        return null;
    }

    @Override
    public User findUserByUsername(String username) {
        return null;
    }

    @Override
    public User findUserByEmail(String email) {
        return null;
    }
}
