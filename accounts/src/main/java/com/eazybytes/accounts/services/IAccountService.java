package com.eazybytes.accounts.services;

import com.eazybytes.accounts.dto.CustomerDto;

public interface IAccountService {

    /**
     * @param customerDto - CustomerDto Object 
     * 
    */
    void createAccount(CustomerDto customerDto);

    /**
     * @param mobileNumber - Input Mobile Number
     * 
    */
    CustomerDto fetchAccount(String mobileNumber);

    /**
     * @param customerDto - CustomerDto Object
     * 
     */
    boolean updateAccount(CustomerDto customerDto);

    /**
     * @param customerDto - Input Mobile Number
     * 
     */
    boolean deleteAccount(String mobileNumber);
}
