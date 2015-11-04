package com.lms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lms.model.dao.RoleDao;
import com.lms.model.entity.Role;
import com.lms.service.RoleService;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleDao roleDao;

    @Override
    public Map<String, String> getAllRolesMap() {
        List<Role> roles = roleDao.findAll();
        Map<String, String> rolesMap = new HashMap<>();
        for (Role role : roles) {
            rolesMap.put(role.getId().toString(), role.getName());
        }
        return rolesMap;
    }
}
