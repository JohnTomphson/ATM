package com.example.demoatm.service.template;

import com.example.demoatm.payload.ApiResponse;

public interface Crud {

    ApiResponse getAll();

    ApiResponse getById(Long id);

    ApiResponse delete(Long id);
}
