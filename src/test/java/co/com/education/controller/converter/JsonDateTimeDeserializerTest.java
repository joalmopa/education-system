package co.com.education.controller.converter;

import co.com.education.controller.converter.JsonDateTimeDeserializer;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

public class JsonDateTimeDeserializerTest {

    JsonDateTimeDeserializer deserializer;
    ObjectMapper mapper;

    @Before
    public void setup() {
        mapper = new ObjectMapper();
        deserializer = new JsonDateTimeDeserializer();
    }

  /*  @Test
    public void deserializeTest() {
        String jsonRequest = String.format("{\"scheduledDeparture\":%s}", "\"24-01-2018 14:47:01\"");
        LocalDateTime dateTime = deserialiseDateTime(jsonRequest);
        Assertions.assertThat(dateTime.toString()).isEqualTo("2018-01-24T14:47:01");
    }

    @SneakyThrows({JsonParseException.class, IOException.class})
    private LocalDateTime deserialiseDateTime(String json) {
        InputStream stream = new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8));
        JsonParser parser = mapper.getFactory().createParser(stream);
        DeserializationContext ctxt = mapper.getDeserializationContext();
        parser.nextToken();
        parser.nextToken();
        parser.nextToken();
        return deserializer.deserialize(parser, ctxt);
    }*/
}
