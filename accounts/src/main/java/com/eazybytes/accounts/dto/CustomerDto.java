package com.eazybytes.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
    name = "Customer",
    description = "Schema to hold Customer and Account information"
)
public class CustomerDto {

    @Schema(
        description = "Name of the customer", example = "HienVu"
    )
    @NotEmpty(message = "Name of customer can not be empty or null")
    @Size(min = 5, max = 30, message = "Name of customer must be between 5 and 30 characters")
    private String name;

    @Schema(description = "Email of the customer", example = "quyettranvu@gmail.com")
    @NotEmpty(message = "Email of customer can not be empty or null")
    @Email(message = "Email of customer must be valid")
    private String email;

    @Schema(description = "Mobile number of the customer", example = "0975940139")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
    private String mobileNumber;

    @Schema(description = "Account details of the customer")
    private AccountsDto accountDto;
}
