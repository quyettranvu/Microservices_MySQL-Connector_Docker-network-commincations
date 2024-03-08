package com.eazybytes.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

/*Dto: Data transfer object -> working as middle layer for decoupling business logic and data layer*/
@Data
@Schema(name = "Accounts", description = "Schema to hold Account information")
public class AccountsDto {
    
    @Schema(
        description = "Account number", example = "9012804567"
    )
    @NotEmpty(message = "Account number can not be null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Account number must be 10 digits")
    private Long accountNumber;

    @Schema(description = "Type of account", example = "Savings")
    @NotEmpty(message = "Account number can not be null or empty")
    private String accountType;

    @Schema(description = "Address branch", example = "Nha Trang")
    @NotEmpty(message = "Account number can not be null or empty")
    private String branchAddress;
    
}
