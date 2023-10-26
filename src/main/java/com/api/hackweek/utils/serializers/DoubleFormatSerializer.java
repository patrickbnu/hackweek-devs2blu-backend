package com.api.hackweek.utils.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.DecimalFormat;

public class DoubleFormatSerializer extends JsonSerializer<Double> {
    private final DecimalFormat df = new DecimalFormat("#.##");

    @Override
    public void serialize(Double value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(df.format(value));
    }
}
