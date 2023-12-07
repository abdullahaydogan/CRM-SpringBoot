package com.CRM.crm.User;

import com.CRM.crm.Error.ApiError;
import com.CRM.crm.Exception.InvalidTokenException;
import com.CRM.crm.Shared.GenericMessage;
import com.CRM.crm.User.dto.UserCreate;
import com.CRM.crm.User.dto.UserDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    UserService userService;
    @PostMapping("/api/v1/users")
    GenericMessage createUser(@Valid @RequestBody UserCreate user) {

        userService.userSave(user.toUser());
    return new GenericMessage("User is Created");
    }
    @PatchMapping("/api/v1/users/{token}/active")
    GenericMessage activateUser(@PathVariable String token) {

        userService.activeUser(token);
        return new GenericMessage("User Activated");
    }
    @GetMapping("api/v1/users")
    Page<UserDTO> getUsers(Pageable page ){
        return userService.getUsers(page).map(UserDTO::new);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    ApiError handleMethodArgNotValidEx(MethodArgumentNotValidException exception){
        ApiError apiError=new ApiError();
        apiError.setStatus(400);
        apiError.setPath("/api/v1/usercreate");
        apiError.setMessage("Validation error");
        Map<String,String>validationError=new HashMap<>();
        for (var fieldError: exception.getBindingResult().getFieldErrors()){
            validationError.put(fieldError.getField(),fieldError.getDefaultMessage());
        }
        apiError.setValidationErrors(validationError);
        return apiError;
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidTokenException.class)
    ApiError handleInvalidTokenException(MethodArgumentNotValidException exception){
        ApiError apiError=new ApiError();
        apiError.setStatus(400);
        apiError.setPath("/api/v1/usercreate");
        apiError.setMessage("InvalidToken Exception");
      return ResponseEntity.status(400).body(apiError).getBody();
    }



}

