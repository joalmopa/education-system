package co.com.education.controller.converter;

import co.com.education.controller.converter.JsonDateTimeSerializer;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.time.LocalDateTime;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;

public class JsonDateTimeSerializerTest {

    JsonDateTimeSerializer jsonDateSerializer;

    @Before
    public void setup() {
        this.jsonDateSerializer = new JsonDateTimeSerializer();
    }

    @Test
    public void serializeTest() throws JsonProcessingException, IOException{
        Writer jsonWriter = new StringWriter();
        JsonGenerator jsonGenerator = new JsonFactory().createGenerator(jsonWriter);
        SerializerProvider serializerProvider = new ObjectMapper().getSerializerProvider();
        jsonDateSerializer.serialize(LocalDateTime.of(2018, Month.JANUARY, 21, 0, 0, 0), jsonGenerator, serializerProvider);
        jsonGenerator.flush();

        assertThat(jsonWriter.toString()).isEqualTo("\"21-01-2018 00:00:00\"");

    }

    @Test
    public void shouldReturnNullWhenIsNull() throws JsonProcessingException, IOException{
        Writer jsonWriter = new StringWriter();
        JsonGenerator jsonGenerator = new JsonFactory().createGenerator(jsonWriter);
        SerializerProvider serializerProvider = new ObjectMapper().getSerializerProvider();
        jsonDateSerializer.serialize(null, jsonGenerator, serializerProvider);
        jsonGenerator.flush();
        assertThat(jsonWriter.toString()).isEqualTo("null");
    }


}
