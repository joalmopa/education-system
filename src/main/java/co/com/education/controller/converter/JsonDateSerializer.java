package co.com.education.controller.converter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static co.com.education.util.DateUtil.DATE_PATTERN;

@JsonComponent
public class JsonDateSerializer extends JsonSerializer<LocalDate> {

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);

    @Override
    public void serialize(LocalDate value, JsonGenerator gen, SerializerProvider serializers)
            throws IOException, JsonProcessingException {

        if (value != null) {
            //gen.writeString(value.format(formatter).toString());
            gen.writeString(value.toString());
        } else {
            gen.writeNull();
        }
    }
}
