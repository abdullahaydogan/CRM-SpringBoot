package com.CRM.crm.Customer;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue
    Long id;
    @NotBlank
    String customerName;
    @NotBlank
    String customerSurname;
    @NotBlank
    String customerClass;
    @Email
    String customerMail;
    @NotBlank
    String customerAdress;
    @NotBlank
    String customerPhone;
    @NotBlank
    Long price;
    @NotBlank
    Long installment;

    public void setId(Long id) {
        this.id = id;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setCustomerSurname(String customerSurname) {
        this.customerSurname = customerSurname;
    }

    public void setCustomerClass(String customerClass) {
        this.customerClass = customerClass;
    }

    public void setCustomerMail(String customerMail) {
        this.customerMail = customerMail;
    }

    public void setCustomerAdress(String customerAdress) {
        this.customerAdress = customerAdress;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public Long getInstallment() {
        return installment;
    }

    public void setInstallment(Long installment) {
        this.installment = installment;
    }
}
