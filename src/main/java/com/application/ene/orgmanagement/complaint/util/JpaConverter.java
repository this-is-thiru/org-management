package com.application.ene.orgmanagement.complaint.util;

import com.application.ene.orgmanagement.complaint.dto.ComplaintStatusUpdate;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.util.List;


public class JpaConverter {
    @Converter
    public static class StringListToJsonBlobConverter implements AttributeConverter<List<String>, String> {

        private final ObjectMapper objectMapper = new ObjectMapper();

        @Override
        public String convertToDatabaseColumn(List<String> attribute) {
            return objectMapper.writeValueAsString(attribute);
        }

        @Override
        public List<String> convertToEntityAttribute(String dbData) {
            return dbData == null ? null : objectMapper.readValue(dbData, new TypeReference<>() {
            });
        }
    }

    @Converter
    public static class ComplaintStatusToJsonBlobConverter implements AttributeConverter<List<ComplaintStatusUpdate>, String> {

        private final ObjectMapper objectMapper = new ObjectMapper();

        @Override
        public String convertToDatabaseColumn(List<ComplaintStatusUpdate> attribute) {
            return objectMapper.writeValueAsString(attribute);
        }

        @Override
        public List<ComplaintStatusUpdate> convertToEntityAttribute(String dbData) {
            return dbData == null ? null : objectMapper.readValue(dbData, new TypeReference<>() {
            });
        }
    }

}
