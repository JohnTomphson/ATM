package com.example.demoatm.service;

import com.example.demoatm.models.Addresses;
import com.example.demoatm.models.Role;
import com.example.demoatm.models.Users;
import com.example.demoatm.payload.ApiResponse;
import com.example.demoatm.payload.UserDTO;
import com.example.demoatm.projections.UserPro;
import com.example.demoatm.repository.AddressRepo;
import com.example.demoatm.repository.UserRepo;
import com.example.demoatm.service.template.Crud;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements Crud {
    final UserRepo userRepo;
    final AddressRepo addressRepo;


    /**
     * Method that gets all Users which are active true
     *
     * @return ApiResponse
     */
    @Override
    public ApiResponse getAll() {
        List<UserPro> userProList = userRepo.findAllByActiveTrue();
        return userProList != null ? new ApiResponse(true, "Success", userProList)
                : new ApiResponse(false, "Failed");
    }



    /**
     * Method that gets User by ID
     *
     * @param id
     * @return ApiResponse
     */
    @Override
    public ApiResponse getById(Long id) {
        Optional<Users> usersOptional = userRepo.findById(id);
        return usersOptional.map(orders -> new ApiResponse(true, "Success", orders))
                .orElseGet(() -> new ApiResponse(false, "Not Found"));
    }




    /**
     * Method which adds new User and Address
     *
     * @param userDTO
     * @return ApiResponse
     */
    public ApiResponse add(UserDTO userDTO) {
        Addresses addresses = new Addresses();
        addresses.setName(userDTO.getDistrictName());
        addresses.setZip_code(userDTO.getZip_code());
        Addresses save = addressRepo.save(addresses);

//        Set<ApplicationUserRole> roleEnum = new LinkedHashSet<>(Arrays.asList(ApplicationUserRole.ADMIN,ApplicationUserRole.USERS,ApplicationUserRole.BOSS
//        ,ApplicationUserRole.CUSTOMER));
//        Set<Role> enums = new HashSet<>(Arrays.asList(ApplicationUserRole.USERS));

        Users users = new Users();
        users.setName(userDTO.getUserName());
        users.setFirstName(userDTO.getFirstName());
        users.setMiddleName(userDTO.getMiddleName());
        users.setLastName(userDTO.getLastName());
//        users.setRoleList(enums);
        users.setAddresses(save);
        userRepo.save(users);
        return new ApiResponse(true, "Saved");
    }



    /**
     * Method which edits Bank:
     * 1) Address District name,
     * 2) User
     *
     * @param id
     * @param newuser
     * @return ApiReponse
     */
    public ApiResponse update(Long id, UserDTO newuser) {
        Optional<Users> usersOptional1 = userRepo.findById(id);
        if (!usersOptional1.isPresent())
            return ApiResponse.builder().message("This Bank isn't present").success(false).build();
        Users oldusers = usersOptional1.get();

        Optional<Addresses> addressesOptional = addressRepo.findById(oldusers.getAddresses().getId());
        if (!addressesOptional.isPresent() && !addressesOptional.isEmpty())
            return ApiResponse.builder().message("This Address isn't present or already exsit").success(false).build();

        Addresses oldAddresses = addressesOptional.get();

        Integer zip_code = newuser.getZip_code();
        Optional<Addresses> addressesOptional1 = addressRepo.findAddressesByZip_code(zip_code);
        if (addressesOptional1.isPresent())
            return ApiResponse.builder().success(false).message("This zip_Code already exsits").build();


        oldAddresses.setZip_code(zip_code);
        oldAddresses.setName(newuser.getDistrictName());
        Addresses save = addressRepo.save(oldAddresses);

        oldusers.setAddresses(save);
        oldusers.setFirstName(newuser.getFirstName());
        oldusers.setName(newuser.getUserName());
        oldusers.setMiddleName(newuser.getMiddleName());
        oldusers.setLastName(newuser.getLastName());
        oldusers.setPassword(newuser.getPassword());


         /**
           * Role with user have some errors

          */
//oldusers.setRoleList(newuser.ge);
        userRepo.save(oldusers);
        return new ApiResponse(true, "updated");
    }




    /**
     * Method which edits User
     * active 'true' to 'false' Bank by ID:
     * 1) User
     * 2) Address
     * @param id
     * @return
     */
    @Override
    public ApiResponse delete(Long id) {
        Optional<Users> optionalUsers = userRepo.findById(id);
        if (!optionalUsers.isPresent())
            return ApiResponse.builder().message("This User isn't present").success(false).build();
        Users oldusers = optionalUsers.get();

        Optional<Addresses> addressesOptional = addressRepo.findById(oldusers.getAddresses().getId());
        Addresses oldAddress = addressesOptional.get();

        oldusers.setActive(false);
        oldAddress.setActive(false);

        userRepo.save(oldusers);
        addressRepo.save(oldAddress);
        return new ApiResponse(true, "Deleted");
    }

    /**
     * This method created for DataLoader and some on
     *
     * @param districName
     * @param zip_code
     * @return
     */
    public Addresses addressesDorDataloader(String districName, Integer zip_code) {
        Addresses addresses1 = new Addresses();
        addresses1.setName(districName);
        addresses1.setZip_code(zip_code);
        return addresses1;
    }

    /**
     * This method created for DataLoader and some on
     *
     * @param districtName
     * @param zip_code
     * @param userName
     * @param firstName
     * @param middleName
     * @param lastName
     * @param roleList
     * @return
     */
    public Users addForDataloader(String districtName,
                                  Integer zip_code, String userName,
                                  String firstName, String middleName,
                                  String lastName, List<Role> roleList) {

        Addresses addresses = addressesDorDataloader(districtName, zip_code);
        Addresses save1 = addressRepo.save(addresses);

        Users users = new Users();
        users.setName(userName);
        users.setFirstName(firstName);
        users.setMiddleName(middleName);
        users.setLastName(lastName);
        users.setRoleList(roleList);
        users.setAddresses(save1);
        return users;
    }
}
