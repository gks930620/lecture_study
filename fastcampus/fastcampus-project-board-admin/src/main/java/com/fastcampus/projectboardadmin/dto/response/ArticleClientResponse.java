package com.fastcampus.projectboardadmin.dto.response;

import com.fastcampus.projectboardadmin.dto.ArticleDto;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record ArticleClientResponse(
        @JsonProperty("_embedded") Embedded embedded,
        @JsonProperty("page") Page page
        ) {
    public static ArticleClientResponse empty(){
        return new ArticleClientResponse(
                new Embedded(List.of()),
                new Page(1,0,1,0)
        );
    }

    public static ArticleClientResponse of(List<ArticleDto> articles){
        return new ArticleClientResponse(
          new Embedded(articles),
          new Page(articles.size(),articles.size(),1,0)
        );
    }

    public List<ArticleDto> articles(){ return this.embedded().articles();}
    //api요청에서는 embedded 안에 articles가 있어서 root에서 바로 articles꺼내기 힘드니까 만들어줌


    public record Embedded(
            @JsonProperty("articles") List<ArticleDto> articles
    ){}
    public record Page(int size,long totalElements, int totalPages, int number){}
}
