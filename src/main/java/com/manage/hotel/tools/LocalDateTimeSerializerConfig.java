package com.manage.hotel.tools;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Configuration
public class LocalDateTimeSerializerConfig {

    @Bean
    public Converter<String, LocalDateTime> localDateTimeConvertr(){
        return new Converter<String, LocalDateTime>() {
            @Override
            public LocalDateTime convert(String source) {
                source = source.replace("T"," ");
                DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime dateTime = null;
                try {
                    switch (source.length()){
                        case 10:
                            source=source+"00:00:00";
                            break;
                        case 13:
                            source=source+":00:00";
                            break;
                        case 16:
                            source=source+":00";
                            break;
                    }
                    dateTime = LocalDateTime.parse(source, df);
                }catch (Exception e){
                    e.printStackTrace();
                }
                return dateTime;
            }
        };
    }

    @Bean
    public Converter<String, LocalDate> localDateConverter(){
        return new Converter<String, LocalDate>() {
            @Override
            public LocalDate convert(String source) {
                DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate date = null;
                try {
                    date = LocalDate.parse(source, df);
                }catch (Exception e){
                    e.printStackTrace();
                }
                return date;
            }
        };
    }
}
