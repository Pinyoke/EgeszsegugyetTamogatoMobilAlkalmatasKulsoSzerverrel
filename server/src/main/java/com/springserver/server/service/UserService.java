package com.springserver.server.service;

import com.springserver.server.model.ScanResult;
import com.springserver.server.model.Sensitivity;
import com.springserver.server.model.User;

import java.util.List;

public interface UserService {
    public User modify(User user);
    public User save(User user);
    public User findActualUser();
    //Sensitivity
    public List<Sensitivity> getSensitivitys();
    public Sensitivity createSensitivity(Sensitivity sensitivity);
    public void deleteSensitivity(Long id);
    public ScanResult scan(String gtin13);
}
