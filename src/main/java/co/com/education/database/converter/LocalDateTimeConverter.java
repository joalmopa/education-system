package co.com.education.database.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Converter(autoApply = true)
public class LocalDateTimeConverter implements AttributeConverter<LocalDateTime, Timestamp> {
    @Override
    public Timestamp convertToDatabaseColumn(LocalDateTime locDateTime) {
        return Optional.ofNullable(locDateTime)
                .map(Timestamp::valueOf)
                .orElse(null);
    }

    @Override
    public LocalDateTime convertToEntityAttribute(Timestamp sqlTimestamp) {
        return Optional.ofNullable(sqlTimestamp)
                .map(Timestamp::toLocalDateTime)
                .orElse(null);
    }
}
