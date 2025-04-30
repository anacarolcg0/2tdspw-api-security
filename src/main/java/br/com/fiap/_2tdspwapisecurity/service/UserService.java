package br.com.fiap._2tdspwapisecurity.service;

import br.com.fiap._2tdspwapisecurity.model.User;
import br.com.fiap._2tdspwapisecurity.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //CRUD

    // Create
    @Transactional
    @CachePut(value = "users", key = "#result.id")
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // Read Id
    @Cacheable(value = "users", key = "#id")
    public User readUserById(UUID id) {
        return userRepository.findById(id).orElse(null);
    }

    // Read All
    @Cacheable(value = "users", key = "'all'")
    public List<User> readAllUsers() {
        return userRepository.findAll();
    }

    // Update
    @Transactional
    @CachePut(value = "users", key = "#result.id")
    public User updateUser(UUID id, User user) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            return null;
        }
        user.setId(id);
        return userRepository.save(user);
    }

    // Delete
    @Transactional
    @CacheEvict(value = "users", key = "#id")
    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
        cleanCacheOfAllUsers();
        //cleanAllUsersFromCache(); //(um ou outro)
    }

    // Métodos auxiliares do CacheEvict
    // Atualiza o cache de usuários
    @CacheEvict(value = "users", key = "'all'")
    public void cleanCacheOfAllUsers() {}

    // Apaga todos os usuários do cache
    @CacheEvict(value = "users", allEntries = true)
    public void cleanAllUsersFromCache() {}
}
