package com.harm.unit.data.json;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class JacksonStudy001 {
    public static void main(String[] args) {

        Logger logger = LoggerFactory.getLogger(JacksonStudy001.class);
        String jsonStr = "{ id: \"22\", name: \"herdin\", level: \"45\", desc: \"rekt-developer\", timestamp: \"20200623111213\" }";
        logger.debug("target json string -> {}", jsonStr);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Champion champion = objectMapper.readValue(jsonStr, Champion.class);
            logger.debug("object mapper parse -> {}", champion);
        } catch (IOException e) {
            logger.error("object mapper parse error -> ", e);
        }

        /*
        com.fasterxml.jackson.core.JsonParseException: Unexpected character ('i' (code 105)): was expecting double-quote to start field name
        */

        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);

        try {
            Champion champion = objectMapper.readValue(jsonStr, Champion.class);
            logger.debug("object mapper parse -> {}", champion);
        } catch (IOException e) {
            logger.error("object mapper parse error -> ", e);
        }

        /*
        com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException: Unrecognized field "timestamp" (class com.harm.unit.data.json.JacksonStudy001$Champion)
        , not marked as ignorable (4 known properties: "desc", "id", "level", "name"])
        */

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //@JsonIgnoreProperties(ignoreUnknown = true)

        try {
            Champion champion = objectMapper.readValue(jsonStr, Champion.class);
            logger.debug("object mapper parse -> {}", champion);
        } catch (IOException e) {
            logger.error("object mapper parse error -> ", e);
        }


        try {
            ChampionWithTime champion = objectMapper.readValue(jsonStr, ChampionWithTime.class);
            logger.debug("object mapper parse -> {}", champion);
        } catch (IOException e) {
            logger.error("object mapper parse error -> ", e);
        }

        /*
        com.fasterxml.jackson.databind.exc.InvalidDefinitionException: Cannot construct instance of `java.time.LocalDateTime` (no Creators, like default construct, exist):
        no String-argument constructor/factory method to deserialize from String value ('20200623111213')
        */

//        objectMapper.registerModule(new JavaTimeModule()); //안됨
//        objectMapper.registerModule(new JavaTimeModule());
//        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        try {
            ChampionAlon champion = objectMapper.readValue(jsonStr, ChampionAlon.class);
            logger.debug("object mapper deserialize -> {}", champion);

            logger.debug("object mapper serialize -> {}", objectMapper.writeValueAsString(champion));
        } catch (IOException e) {
            logger.error("object mapper parse error -> ", e);
        }




    }

    public static class Champion {
        Integer id;
        String name;
        Long level;
        String desc;

        public Champion() {
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Long getLevel() {
            return level;
        }

        public void setLevel(Long level) {
            this.level = level;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        @Override
        public String toString() {
            return "Champion{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", level=" + level +
                    ", desc='" + desc + '\'' +
                    '}';
        }
    }

    public static class ChampionWithTime extends Champion {
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMddHHmmss")
        LocalDateTime timestamp;

        public ChampionWithTime() {
        }

        public LocalDateTime getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
        }

        @Override
        public String toString() {
            return "ChampionWithTime{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", level=" + level +
                    ", desc='" + desc + '\'' +
                    ", timestamp=" + timestamp +
                    '}';
        }
    }

    public static class ChampionAlon {
        private Integer id;
        private String name;
        private Long level;
        private String desc;
        @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
        @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
        private LocalDateTime timestamp;

        public ChampionAlon() {
        }

        public ChampionAlon(Integer id, String name, Long level, String desc, LocalDateTime timestamp) {
            this.id = id;
            this.name = name;
            this.level = level;
            this.desc = desc;
            this.timestamp = timestamp;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Long getLevel() {
            return level;
        }

        public void setLevel(Long level) {
            this.level = level;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public LocalDateTime getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
        }

        @Override
        public String toString() {
            return "ChampionAlon{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", level=" + level +
                    ", desc='" + desc + '\'' +
                    ", timestamp=" + timestamp +
                    '}';
        }
    }

    public static class CustomLocalDateTimeSerializer
            extends StdSerializer<LocalDateTime> {

        private static DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

        public CustomLocalDateTimeSerializer() {
            this(null);
        }

        public CustomLocalDateTimeSerializer(Class<LocalDateTime> t) {
            super(t);
        }

        @Override
        public void serialize(
                LocalDateTime value,
                JsonGenerator gen,
                SerializerProvider arg2)
                throws IOException, JsonProcessingException {

            gen.writeString(formatter.format(value));
        }
    }
    public static class CustomLocalDateTimeDeserializer extends StdDeserializer<LocalDateTime> {
        private static DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        public CustomLocalDateTimeDeserializer() {
            this(null);
        }
        @Override
        public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            String date = p.getText();
            return LocalDateTime.parse(date, formatter);
        }
        public CustomLocalDateTimeDeserializer(Class<LocalDateTime> t) {
            super(t);
        }
    }

}
