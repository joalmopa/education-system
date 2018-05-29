package co.com.education.controller.converter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static co.com.education.util.DateUtil.DATE_TIME_PATTERN;

@JsonComponent
public class JsonDateTimeSerializer extends JsonSerializer<LocalDateTime>{

    private static DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);

    @Override
    public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider serializers)
            throws IOException, JsonProcessingException {
        if (value != null) {
            gen.writeString(value.format(formatter).toString());
        } else {
            gen.writeNull();
        }
    }
}
