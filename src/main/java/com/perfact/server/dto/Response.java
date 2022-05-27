package com.perfact.server.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class Response {
    private String code;
    private String messages;
    private List<Object> data;


    @Builder
    public Response(String code, String messages, List<Object> data) {
        this.code = code;
        this.messages = messages;
        this.data = data;
    }
}

