package com.example.asm1java5.service.impl;

import com.example.asm1java5.entity.Staff;
import com.example.asm1java5.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class StaffServiceImpl implements UserDetailsService {

    private StaffRepository staffRepository;

    @Autowired
    public void setStaffRepository(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        Staff staff = staffRepository.findByUsername(username);
        if (staff == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return User.withUsername(staff.getUsername())
                .password(staff.getPassword())
                .roles(staff.getRole())
                .build();
    }
}