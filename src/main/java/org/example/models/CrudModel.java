package org.example.models;

import lombok.*;

@Data
@Getter
@Builder
public class CrudModel {
    private int userId;
    private int id;
    private String title;
    private String body;
}
