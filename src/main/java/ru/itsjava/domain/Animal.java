package ru.itsjava.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class Animal {
    private long id;
    private final String vie;
    private final int wei;
}
