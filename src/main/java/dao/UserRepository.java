package dao;

import org.springframework.data.jpa.repository.JpaRepository;

import entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    public User findById(int id);

    public User findByUsername(String username);

    public User findByAuthToken(String authToken);
}