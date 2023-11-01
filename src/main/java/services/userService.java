package services;

import dao.UserRepository;
import entities.User;
import exceptions.CustomException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class userService {

    @Autowired
    private UserRepository userRepository;

    private String superAdminKey = "dummy key";

    // get user by id
    public User getUserById(int id) {
        User user = this.userRepository.findById(id);
        return user;
    }

    // creating new user
    public User createUser(User user, String key) {
        if (key == this.superAdminKey) {
            User result = userRepository.save(user);
            return result;
        } else {
            throw new CustomException("Invalid key");
        }
    }

    public User getUserByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    public User getUserByAuthToken(String authToken) {
        return this.userRepository.findByAuthToken(authToken);
    }

    public String login(String username, String password) {
        User user = this.userRepository.findByUsername(username);
        if (user != null) {
            if (user.validatePassword(password)) {
                String authToken = UUID.randomUUID().toString();
                user.setAuthToken(authToken);
                this.userRepository.save(user);
                return authToken;
            } else {
                throw new CustomException("Invalid password");
            }
        } else {
            throw new CustomException("Invalid username");
        }
    }

    public void logout(String authToken) {
        User user = this.userRepository.findByAuthToken(authToken);
        if (user != null) {
            user.logout();
            this.userRepository.save(user);
        }
    }

}
