package com.ldm.swapi.apiconsumer;

import lombok.Getter;

@Getter
public class ParseSpecField {

    private String sourceField;
    private String targetField;
    private DataType dataType;

    public ParseSpecField(String sourceField, String targetField, DataType dataType) {
        this.sourceField = sourceField;
        this.targetField = targetField;
        this.dataType = dataType;
    }
}
