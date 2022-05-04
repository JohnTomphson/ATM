package com.example.demoatm.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApiResponse {
    private boolean success;
    private String message;
    private Object object;

    public ApiResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
