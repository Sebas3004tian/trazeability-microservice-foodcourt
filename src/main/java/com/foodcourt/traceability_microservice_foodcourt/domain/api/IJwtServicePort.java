package com.foodcourt.traceability_microservice_foodcourt.domain.api;

import io.jsonwebtoken.Claims;

public interface IJwtServicePort {
    Long getAuthenticatedUserId();

    Claims extractClaims(String token);
}
