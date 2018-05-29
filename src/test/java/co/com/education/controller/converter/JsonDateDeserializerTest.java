package co.com.education.controller.converter;

import co.com.education.controller.converter.JsonDateDeserializer;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

public class JsonDateDeserializerTest {

    JsonDateDeserializer deserializer;
    ObjectMapper mapper;

    @Before
    public void setup() {
        mapper = new ObjectMapper();
        deserializer = new JsonDateDeserializer();
    }

    @Test
    public void deserializeTest() {
        String jsonRequest = String.format("{\"birthDate\":%s}", "\"21-05-2014\"");
        LocalDate date = deserialiseDate(jsonRequest);
        Assertions.assertThat(date.toString()).isEqualTo("2014-05-21");
    }

    @SneakyThrows({JsonParseException.class, IOException.class})
    private LocalDate deserialiseDate(String json) {

        InputStream stream = new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8));
        JsonParser parser = mapper.getFactory().createParser(stream);
        DeserializationContext ctxt = mapper.getDeserializationContext();
        parser.nextToken();
        parser.nextToken();
        parser.nextToken();

        return deserializer.deserialize(parser, ctxt);
    }
}