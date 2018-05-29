package co.com.education.controller.converter;

import co.com.education.controller.converter.JsonDateSerializer;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class JsonDateSerializerTest {

   public JsonDateSerializer jsonDateSerializer;

    ObjectMapper mapper;

    @Before
    public void setup() {
        mapper = new ObjectMapper();
        jsonDateSerializer = new JsonDateSerializer();
    }

    @Test
    public void testDateSerializer() throws IOException {
        Writer jsonWriter = new StringWriter();
        JsonGenerator jsonGenerator = new JsonFactory().createGenerator(jsonWriter);
        SerializerProvider serializerProvider = new ObjectMapper().getSerializerProvider();
        jsonDateSerializer.serialize(LocalDate.of(2018, 01, 23), jsonGenerator, serializerProvider);
        jsonGenerator.flush();
        assertThat(jsonWriter.toString(), is("\"2018-01-23\""));
    }

    @Test
    public void shouldReturnNullWhenIsNull() throws IOException {
        Writer jsonWriter = new StringWriter();
        JsonGenerator jsonGenerator = new JsonFactory().createGenerator(jsonWriter);
        SerializerProvider serializerProvider = new ObjectMapper().getSerializerProvider();
        jsonDateSerializer.serialize(null, jsonGenerator, serializerProvider);
        jsonGenerator.flush();;
        assertThat(jsonWriter.toString(), is("null"));
    }
}
