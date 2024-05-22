package com.shanks.request;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class CreateEventRequest {
    private String name;
    private String image;
    private String description;
    private String location;
    private Long restaurantId;

    private LocalDateTime startedAt;
    private LocalDateTime endsAt;
}
