package com.eazybytes.accounts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eazybytes.accounts.constants.AccountsConstants;
import com.eazybytes.accounts.dto.AccountsContactInfoDto;
import com.eazybytes.accounts.dto.CustomerDto;
import com.eazybytes.accounts.dto.ErrorResponseDto;
import com.eazybytes.accounts.dto.ResponseDto;
import com.eazybytes.accounts.services.IAccountService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@Tag(
    name = "CRUD REST APIs for Accounts in EazyBytes Bank",
    description = "CRUD REST APIs for Accounts in EazyBytes Bank with Create, Read, Update, Delete"
)
@RestController
@RequestMapping(path="/api", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class AccountsController {
    
    private final IAccountService accountService;

    public AccountsController(IAccountService accountService) {
        this.accountService = accountService;
    }

    // @Value("${build.version}")
    // private String buildVersion;

    @Autowired
    private BuildProperties buildProperties;

    @Autowired
    public Environment environment;

    @Autowired
    public AccountsContactInfoDto accountsContactInfoDto;
    
    @Operation(
        summary = "Create Account API",
        description = "REST API to creat new customer and account inside bank"
    )
    @ApiResponse(
        responseCode = "201",
        description = "Http Status Created"
    )
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {
        accountService.createAccount(customerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
    }

    @Operation(summary = "Fetch Account API", description = "REST API to fetch customer and account inside bank")
    @ApiResponse(responseCode = "200", description = "Http Status Fetched")
    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> fetchAccountDetails(@RequestParam 
        @Pattern(regexp = "(^$|[0-9]{10})", message = "Account number must be 10 digits") String mobileNumber) {
        CustomerDto customerDto = accountService.fetchAccount(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(customerDto);
    }

    @Operation(summary = "Update Account API", description = "REST API to update customer and account inside bank")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Http Status Updated"),
        @ApiResponse(
            responseCode = "417", 
            description = "Http Status Update Error",
            content = @Content(
                schema = @Schema(implementation = ErrorResponseDto.class)
            )
        ),
    })
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccountDetails(@Valid @RequestBody CustomerDto customerDto) {
        boolean isUpdated = accountService.updateAccount(customerDto);
        if(isUpdated) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_UPDATE));
        }
    }

    @Operation(summary = "Delete Account API", description = "REST API to delete new customer and account inside bank")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Http Status Deleted"),
            @ApiResponse(
                responseCode = "417", 
                description = "Http Status Delete Error",
                content = @Content(
                    schema = @Schema(implementation = ErrorResponseDto.class)
                )
            ),
    })
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccountDetails(@RequestParam 
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Account number must be 10 digits") String mobileNumber) {
        boolean isDeleted = accountService.deleteAccount(mobileNumber);
        if(isDeleted) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_DELETE));
        }
    }
       
    @Operation(summary = "Get build version", description = "Reading configurations using @Value annotation")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Http Status Fetched"),
            @ApiResponse(responseCode = "500", description = "Http Status Fetch Error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
    })
    @GetMapping("/build-info") 
    public ResponseEntity<String> getBuildInfo() {
        return ResponseEntity.status(HttpStatus.OK).body(buildProperties.get("java.version"));
    }

    @Operation(summary = "Get java and version", description = "Reading configurations using Environment interface")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Http Status Fetched"),
            @ApiResponse(responseCode = "500", description = "Http Status Fetch Error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
    })
    @GetMapping("/java-info")
    public ResponseEntity<String> getJavaInfo() {
        return ResponseEntity.status(HttpStatus.OK).body(environment.getProperty("MAVEN_HOME"));
    }

    @Operation(summary = "Get contact info", description = "Reading configurations using @ConfigurationProperties annotation")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Http Status Fetched"),
            @ApiResponse(responseCode = "500", description = "Http Status Fetch Error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
    })
    @GetMapping("/contact-info")
    public ResponseEntity<AccountsContactInfoDto> getContactInfo() {
        return ResponseEntity.status(HttpStatus.OK).body(accountsContactInfoDto);
    }
}

