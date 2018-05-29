package co.com.education.controller.converter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.node.TextNode;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static co.com.education.util.DateUtil.DATE_PATTERN;

@JsonComponent
public class JsonDateDeserializer extends JsonDeserializer<LocalDate> {

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);

    @Override
    public LocalDate deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {

        final ObjectCodec oc = jp.getCodec();
        final TextNode node = (TextNode) oc.readTree(jp);
        final String dateString = node.textValue();

        LocalDate localDate = null;

        if (dateString != null  && !dateString.isEmpty()) {
            localDate =  LocalDate.parse(dateString.toUpperCase(), formatter);
        }
        return  localDate;
    }
}
