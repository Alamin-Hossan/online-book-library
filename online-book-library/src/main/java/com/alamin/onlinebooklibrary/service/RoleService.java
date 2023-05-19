package com.alamin.onlinebooklibrary.service;

import com.alamin.onlinebooklibrary.entity.Role;
import org.springframework.stereotype.Service;

@Service
public interface RoleService {
    Role getRoles(String name);
    void addRole(String name);
}
