package com.filipe.estudo_spring.services.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.filipe.estudo_spring.models.dtos.user.UserResponse;
import com.filipe.estudo_spring.models.entities.User;
import com.filipe.estudo_spring.repositories.users.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Page<UserResponse> findAll(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);

        int lastPage = users.getTotalPages() - 1;
        if (users.getTotalElements() <= 0) {
            return Page.empty(pageable);
        }

        if (pageable.getPageNumber() > lastPage) {
            Pageable novoPageable = PageRequest.of(lastPage, pageable.getPageSize(), pageable.getSort());
            users = userRepository.findAll(novoPageable);
        }

        return users.map(user -> new UserResponse(user));
    }
}
