package lk.ijse.userservice.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.userservice.convertion.ConversionData;
import lk.ijse.userservice.dto.UserDTO;
import lk.ijse.userservice.entity.UserEntity;
import lk.ijse.userservice.repostory.UserServiceDAO;
import lk.ijse.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserServiceIMPL implements UserService {
    @Autowired
    private ConversionData conversionData;
    @Autowired
    private UserServiceDAO userServiceDAO;
    @Override
    public void saveUser(UserDTO userDTO) {
        userServiceDAO.save(conversionData.mapTo(userDTO, UserEntity.class));
    }

    @Override
    public void updateUser(UserDTO userDTO) {
        if (!userServiceDAO.existsById(userDTO.getUserId())){
            return;
        }
        userServiceDAO.save(conversionData.mapTo(userDTO, UserEntity.class));
    }

    @Override
    public UserDTO getUser(String userId) {
        if (!userServiceDAO.existsById(userId)){
            return null;
        }
        return conversionData.mapTo(userServiceDAO.findById(userId).get(), UserDTO.class);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return conversionData.mapTo(userServiceDAO.findAll(),UserDTO.class);
    }

    @Override
    public void deleteUser(String userId) {
        userServiceDAO.deleteById(userId);

    }

    @Override
    public boolean isUserExists(String userId) {
        return userServiceDAO.existsById(userId);
    }

    @Override
    public String generateUserId() {
        return null;
    }
}
