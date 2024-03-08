package com.eazybytes.accounts.dto;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Schema(name = "Error Response", description = "Schema to hold error response information")
@Data @AllArgsConstructor
public class ErrorResponseDto {
    
    @Schema(name = "API Path")
    private String apiPath;

    @Schema(name = "Error code")
    private HttpStatus errorCode;

    @Schema(name = "Error message")
    private String errorMessage;

    @Schema(name = "Error Time")
    private LocalDateTime errorTime;
}
