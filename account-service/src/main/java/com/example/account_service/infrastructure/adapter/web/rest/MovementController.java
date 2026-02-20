package com.example.account_service.infrastructure.adapter.web.rest;


import com.example.account_service.application.dto.CreateMovementRequest;
import com.example.account_service.application.dto.MovementResponse;
import com.example.account_service.application.usecase.CreateMovementUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movements")
@RequiredArgsConstructor
public class MovementController {

    private final CreateMovementUseCase createMovementUseCase;

    @PostMapping
    public MovementResponse createMovement(
            @RequestBody CreateMovementRequest request) {
        return createMovementUseCase.execute(request);
    }
}
