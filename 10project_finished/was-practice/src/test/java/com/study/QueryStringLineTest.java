package com.study;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class QueryStringLineTest {

    //List<QueryString>
    @Test
    void createTest() {
        QueryString qeuryString = new QueryString("operand1","11");
        assertThat(qeuryString).isNotNull();

    }



}
