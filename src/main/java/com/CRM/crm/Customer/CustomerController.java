package com.CRM.crm.Customer;

import com.CRM.crm.Customer.dto.CustomerCreate;
import com.CRM.crm.Error.ApiError;
import com.CRM.crm.Shared.GenericMessage;
import com.CRM.crm.User.User;
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
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping("/api/v1/customersave")
    GenericMessage createCustomer(@Valid @RequestBody CustomerCreate customer) {
        customerService.saveCustomer(customer.toCustomer());
        return new GenericMessage("Customer is Created");
    }
    @GetMapping("api/v1/customers")
    Page<Customer> getCustomer(Pageable page ){
        return customerService.getCustomer(page);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<ApiError> handleMethodArgNotValidEx(MethodArgumentNotValidException exception) {
        ApiError apiError = new ApiError();
        apiError.setPath("/api/v1/customer");
        apiError.setMessage("validation Error");
        apiError.setStatus(400);
        Map<String, String> validationErrors = new HashMap<>();
        for (var fieldError : exception.getBindingResult().getFieldErrors()) {
            validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        apiError.setValidationErrors(validationErrors);
        return ResponseEntity.badRequest().body(apiError);
    }


}
