package com.alamin.onlinebooklibrary.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {
    private Long id;
    private String name;
    private String author;
    private double price;
    private int year;
    private int quantity;
    private String description;
}
