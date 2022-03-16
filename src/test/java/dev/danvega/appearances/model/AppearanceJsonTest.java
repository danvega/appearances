package dev.danvega.appearances.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
class AppearanceJsonTest {

    @Autowired
    private ApplicationContext context;

    @Autowired
    JacksonTester<Appearance> jacksonTester;

    @Autowired
    JacksonTester<List<Appearance>> jacksonTesterList;

    // not being used but an example of using Resource this way
    @Value("classpath:appearance.json")
    private Resource appearanceJsonContent;

    @BeforeEach
    void setUp() {
        //Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
    }

    @Test
    void contextLoads() {
        assertThat(context).isNotNull();
        assertThat(jacksonTester).isNotNull();
    }

    @Test
    void testSerializationWithJsonPath() throws IOException {
        var id = UUID.randomUUID().toString();
        var appearance = new Appearance(id,
                "SpringOne Tour Chicago",
                "Getting Started with GraphQL for Spring",
                LocalDate.of(2022,04,26),
                LocalDate.of(2022,04,27),
                Type.CONFERENCE,
                List.of("Spring Boot", "GraphQL"),
                "https://tanzu.vmware.com/developer/springone-tour/"
        );

        JsonContent<Appearance> json = jacksonTester.write(appearance);
        assertThat(json).extractingJsonPathStringValue("$.id").isEqualTo(id);
        assertThat(json).extractingJsonPathStringValue("$.title").isEqualTo("SpringOne Tour Chicago");
        assertThat(json).extractingJsonPathStringValue("$.description").isEqualTo("Getting Started with GraphQL for Spring");
        assertThat(json).extractingJsonPathStringValue("$.startDate").isEqualTo("2022-04-26");
        assertThat(json).extractingJsonPathStringValue("$.endDate").isEqualTo("2022-04-27");
        assertThat(json).extractingJsonPathStringValue("$.type").isEqualTo(Type.CONFERENCE.toString());
        assertThat(json).extractingJsonPathArrayValue("$.tags").isEqualTo(List.of("Spring Boot","GraphQL"));
        assertThat(json).extractingJsonPathStringValue("$.url").isEqualTo("https://tanzu.vmware.com/developer/springone-tour/");
    }

    @Test
    void testSerializationWithJsonFile() throws IOException {
        var id = "86c87daf-3395-4c8e-b63f-8cadb8f0245b";
        var appearance = new Appearance(id,
                "SpringOne Tour Chicago",
                "Getting Started with GraphQL for Spring",
                LocalDate.of(2022,04,26),
                LocalDate.of(2022,04,27),
                Type.CONFERENCE,
                List.of("Spring Boot", "GraphQL"),
                "https://tanzu.vmware.com/developer/springone-tour/"
        );

        JsonContent<Appearance> json = jacksonTester.write(appearance);
        assertThat(json).isEqualToJson(new ClassPathResource("appearance.json"));
        assertThat(json).isEqualToJson(getAppearanceJson());
    }

    @Test
    void testListAppearancesSerialization() throws IOException {
        var chicago = new Appearance("86c87daf-3395-4c8e-b63f-8cadb8f0245b",
                "SpringOne Tour Chicago",
                "Getting Started with GraphQL for Spring",
                LocalDate.of(2022,04,26),
                LocalDate.of(2022,04,27),
                Type.CONFERENCE,
                List.of("Spring Boot", "GraphQL"),
                "https://tanzu.vmware.com/developer/springone-tour/"
        );
        var toronto = new Appearance("8440f3dd-bab4-48a8-b0f1-9cd8183ea384",
                "SpringOne Tour Toronto",
                "Getting Started with GraphQL for Spring",
                LocalDate.of(2022,06,7),
                LocalDate.of(2022,06,8),
                Type.CONFERENCE,
                List.of("Spring Boot", "GraphQL"),
                "https://tanzu.vmware.com/developer/springone-tour/"
        );

        List<Appearance> appearances = List.of(chicago,toronto);
        JsonContent<List<Appearance>> json = jacksonTesterList.write(appearances);
        assertThat(json).isEqualToJson(new ClassPathResource("appearances.json"));
    }

    @Test
    void testDeserialization() throws IOException {
        var appearance = jacksonTester.read(new ClassPathResource("appearance.json")).getObject();

        assertThat(appearance.getId()).isEqualTo("86c87daf-3395-4c8e-b63f-8cadb8f0245b");
        assertThat(appearance.getTitle()).isEqualTo("SpringOne Tour Chicago");
        assertThat(appearance.getDescription()).isEqualTo("Getting Started with GraphQL for Spring");
        assertThat(appearance.getStartDate()).isEqualTo(LocalDate.of(2022,04,26));
        assertThat(appearance.getEndDate()).isEqualTo(LocalDate.of(2022,04,27));
        assertThat(appearance.getType()).isEqualTo(Type.CONFERENCE);
        assertThat(appearance.getTags()).isEqualTo(List.of("Spring Boot", "GraphQL"));
        assertThat(appearance.getUrl()).isEqualTo("https://tanzu.vmware.com/developer/springone-tour/");
    }

    private String getAppearanceJson() {
        return """
                {
                  "id": "86c87daf-3395-4c8e-b63f-8cadb8f0245b",
                  "title": "SpringOne Tour Chicago",
                  "description": "Getting Started with GraphQL for Spring",
                  "startDate": "2022-04-26",
                  "endDate": "2022-04-27",
                  "type": "CONFERENCE",
                  "tags": [
                    "Spring Boot",
                    "GraphQL"
                  ],
                  "url": "https://tanzu.vmware.com/developer/springone-tour/"
                }
                """;
    }

}