package com.pqr.project.business.domain.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pqr.project.business.domain.constants.PqrConstants;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class ResponsesFactory {

    public static String getInternalServerErrorResponse(String message) {
        return ResponsesFactory.formatErrorResponse(
                String.format(PqrConstants.INTERNAL_SERVER_ERROR_MESSAGE, message));
    }

    public static String getResourceNotFoundResponse(String field) {
        return ResponsesFactory.formatErrorResponse(
                String.format(PqrConstants.NOT_FOUND_RESOURCE, field));
    }

    private static String formatErrorResponse(String message) {
        String messageErrorResponse = "";

        try {
            Map<String, Object> errorPayload = new HashMap<>();
            errorPayload.put("errorMessage", message);
            messageErrorResponse = new ObjectMapper().writeValueAsString(errorPayload);
        } catch (JsonProcessingException e) {
            messageErrorResponse = PqrConstants.INTERNAL_SERVER_ERROR_MESSAGE;
        }

        return messageErrorResponse;
    }
}
