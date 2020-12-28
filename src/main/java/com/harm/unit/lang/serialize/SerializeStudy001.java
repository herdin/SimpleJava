package com.harm.unit.lang.serialize;

import com.harm.unit.Unit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.time.LocalDateTime;
import java.util.Base64;

@Deprecated
public class SerializeStudy001 implements Unit {
    private Logger logger = LoggerFactory.getLogger(SerializeStudy001.class);
    @Override
    public Object execute(Object[] obj) throws Exception {
        Data data1 = new Data("id-1", "name-1", 10, LocalDateTime.now());
        logger.debug("before serialize -> {}", data1);
        String data1base64 = SerializeStudy001.serialize(data1);
        logger.debug("serialize and base64 -> {}", data1base64);

        Data data2 = SerializeStudy001.deserialize(data1base64);
        logger.debug("deserialize -> {}", data2);

        String testBase64 = "rO0ABXNyADNjb20uaGFybS51bml0Lmxhbmcuc2VyaWFsaXplLlNlcmlhbGl6ZVN0dWR5MDAxJERhdGHNBm4YsMUrOAIABUkAA2FnZUoAEHNlcmlhbFZlcnNpb25VSURMAAliaXJ0aFRpbWV0ABlMamF2YS90aW1lL0xvY2FsRGF0ZVRpbWU7TAACaWR0ABJMamF2YS9sYW5nL1N0cmluZztMAARuYW1lcQB+AAJ4cAAAAAoAAAAAAAAAZXNyAA1qYXZhLnRpbWUuU2VylV2EuhsiSLIMAAB4cHcOBQAAB+QDBg4rIzXE7Xx4dAAEaWQtMXQABm5hbWUtMQ==";
        testBase64 = "rO0ABXNyADNjb20uaGFybS51bml0Lmxhbmcuc2VyaWFsaXplLlNlcmlhbGl6ZVN0dWR5MDAxJERhdGFI0KwNC5OWuQIABEkAA2FnZUoAEHNlcmlhbFZlcnNpb25VSURMAAliaXJ0aFRpbWV0ABlMamF2YS90aW1lL0xvY2FsRGF0ZVRpbWU7TAACaWR0ABJMamF2YS9sYW5nL1N0cmluZzt4cAAAAAoAAAAAAAAAZXNyAA1qYXZhLnRpbWUuU2VylV2EuhsiSLIMAAB4cHcOBQAAB+QDBg8IFgKJSsB4dAAEaWQtMQ==";
        Data data3 = SerializeStudy001.deserialize(testBase64);
        logger.debug("deserialize -> {}", data3);

        return null;
    }
    public static String serialize(Object object) throws IOException {
        byte[] serialized;
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
                oos.writeObject(object);
                // serializedMember -> 직렬화된 member 객체
                serialized = baos.toByteArray();
            }
        }
        // 바이트 배열로 생성된 직렬화 데이터를 base64로 변환
        String serializedString = Base64.getEncoder().encodeToString(serialized);
        return serializedString;
    }
    public static <T> T deserialize(String base64) throws IOException, ClassNotFoundException {
        byte[] serialized = Base64.getDecoder().decode(base64);
        Object deserialized = null;
        try (ByteArrayInputStream bais = new ByteArrayInputStream(serialized)) {
            try (ObjectInputStream ois = new ObjectInputStream(bais)) {
                // 역직렬화된 Member 객체를 읽어온다.
                deserialized = ois.readObject();
            }
        }
        return (T)deserialized;
    }
    public static class Data implements Serializable {
        final long serialVersionUID = 101L;
        final String id;
        transient final String name;
        final int age;
        final LocalDateTime birthTime;

        public Data(String id, String name, int age, LocalDateTime birthTime) {
            this.id = id;
            this.name = name;
            this.age = age;
            this.birthTime = birthTime;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        public LocalDateTime getBirthTime() {
            return birthTime;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "\nserialVersionUID=" + serialVersionUID +
                    "\n, id='" + id + '\'' +
                    "\n, name='" + name + '\'' +
                    "\n, age=" + age +
                    "\n, birthTime=" + birthTime +
                    "\n}";
        }
    }
}
