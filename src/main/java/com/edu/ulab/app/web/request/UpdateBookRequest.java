package com.edu.ulab.app.web.request;

import lombok.Data;

@Data
public class UpdateBookRequest {
    private Long id;
    private String title;
    private String author;
    private long pageCount;
}
