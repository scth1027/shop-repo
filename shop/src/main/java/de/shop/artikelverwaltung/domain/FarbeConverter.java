package de.shop.artikelverwaltung.domain;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class FarbeConverter implements AttributeConverter<Farbe, String> {
        @Override
        public String convertToDatabaseColumn(Farbe farbe) {
                if (farbe == null) {
                        return null;
                }
                return farbe.getInternal();
        }

        @Override
        public Farbe convertToEntityAttribute(String internal) {
                return Farbe.build(internal);
        }
}
