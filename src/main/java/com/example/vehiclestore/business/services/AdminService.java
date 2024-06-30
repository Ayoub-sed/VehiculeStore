package com.example.vehiclestore.business.services;
import com.example.vehiclestore.DAO.entities.Admin;

import java.util.List;

public interface AdminService {

    public List<Admin> getAllAdmins() ;
    public Admin getAdminById(Long id) ;
    public Admin createAdmin(Admin admin) ;
    public Admin updateAdmin(Long id, Admin admin);
    public void deleteAdmin(Long id);
}