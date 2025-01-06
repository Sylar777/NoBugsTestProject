package org.example.models;

import lombok.*;

@Data
@Getter
@Builder
public class CrudModel {
    private String userId;
    private String id;
    private String title;
    private String body;
}
