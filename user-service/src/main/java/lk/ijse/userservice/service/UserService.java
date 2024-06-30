package lk.ijse.userservice.service;

import lk.ijse.userservice.dto.UserDTO;

import java.util.List;

public interface UserService {
    void saveUser(UserDTO userDTO);

    void updateUser(UserDTO userDTO);

    UserDTO getUser(String userId);

    List<UserDTO> getAllUsers();


}
