package com.fastcampus.projectboardadmin.service;

import com.fastcampus.projectboardadmin.domain.constant.RoleType;
import com.fastcampus.projectboardadmin.dto.ArticleDto;
import com.fastcampus.projectboardadmin.dto.UserAccountDto;
import com.fastcampus.projectboardadmin.dto.properties.ProjectProperties;
import com.fastcampus.projectboardadmin.dto.response.ArticleClientResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.jfr.Enabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;


@ActiveProfiles("test")
@DisplayName("비즈니스 로직 - 게시글 관리 ")
class ArticleManagementServiceTest {

    //API를 진짜로 테스트, 가짜로 테스트 할 것인가..
    //여기서는 2개 다 해보자


    @DisplayName("API mocking 테스트")
    @EnableConfigurationProperties(ProjectProperties.class)
    @AutoConfigureWebClient(registerRestTemplate = true)
    //기본값은 false. RestTemplate을 등록하지 않고 테스트하는게 기본인데 여기서는 직접 해보자(우리가 빈 등록한 실제 객체를 사용해보자)
    @RestClientTest(ArticleManagementService.class)
    @Nested
    class RestTemplateTest {
        private final ArticleManagementService sut;

        private final ProjectProperties projectProperties;
        private final MockRestServiceServer server;  // 특정 API 접근하는 행위를 mocking
        private final ObjectMapper mapper;

        @Autowired
        public RestTemplateTest(ArticleManagementService sut, ProjectProperties projectProperties, MockRestServiceServer server, ObjectMapper mapper) {
            this.sut = sut;
            this.projectProperties = projectProperties;
            this.server = server;
            this.mapper = mapper;
        }

        @DisplayName("게시글 목록 API를 호출하면, 게시글을 가져온다.")
        void givenNothing_whenCallingArticlesApi_thenReturnArticleList() {
            ArticleDto expectedArticle= createArticleDto("제목","글");
            ArticleClientResponse expectedResponse= ArticleClientResponse.of(List.of(expectedArticle));

            server.expect(requestTo(projectProperties.board().url()
            +"/주소 "   ));    //이런 api 요청을 기대한다  기본 http://localhost:8080은 yaml-projectproperties 객체를 통해,
            //나머지 url은 직접 테스트에 명시해줘야됨.

            List<ArticleDto> result= sut.getArticles();
            assertThat(result).isNotEmpty();

        }

        private  ArticleDto createArticleDto(String title, String content){
            return ArticleDto.of(
              1L,createUserAccountDto(),
              title,
              content,
                    null,
                    LocalDateTime.now(),
                    "Uno",
                    LocalDateTime.now(),
                    "Uno"
            );
        }

        private UserAccountDto createUserAccountDto() {
            return UserAccountDto.of(
                    "unoTest",
                    "pw",
                    Set.of(RoleType.ADMIN),
                    "uno-test@email.com",
                    "uno-test",
                    "test memo"
            );
        }

    }
}
