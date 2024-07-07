package com.sparta.springcommon.domain.user.repository;

import com.sparta.springcommon.domain.user.entity.User;
import com.sparta.springcommon.exception.custom.user.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserAdapter {
    private final UserRepository userRepository;

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(UserNotFoundException::new);
    }
    public boolean existsByUsername(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    public void save(User user) {
        userRepository.save(user);
    }
}
