package com.filipe.estudo_spring.controllers.users;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.filipe.estudo_spring.models.dtos.user.UserResponse;
import com.filipe.estudo_spring.services.user.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<Page<UserResponse>> findAll(
            @PageableDefault(size = 10, sort = "name", direction = Sort.Direction.ASC) Pageable pageable) {

        for (Sort.Order order : pageable.getSort()) {
            String property = order.getProperty();
            if (!property.equals("name") && !property.equals("createdAt")) {
                throw new IllegalArgumentException("Request can only be sorted by name or creation date");
            }
        }

        if (pageable.getPageSize() > 100) {
            throw new IllegalArgumentException("Page size should be equal to or lower than 100");
        }

        Page<UserResponse> users = userService.findAll(pageable);

        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }
}
