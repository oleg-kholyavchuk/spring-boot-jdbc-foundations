package ru.itsjava.services;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

@Service
public class IOAnimalServiceImpl implements IOAnimalService {
    private final BufferedReader reader;

    public IOAnimalServiceImpl(@Value("#{T(java.lang.System).in}") InputStream inputStream) {
        this.reader = new BufferedReader(new InputStreamReader(inputStream));
    }

    @Override
    @SneakyThrows
    public String input() {
        return reader.readLine();
    }

    @Override
    @SneakyThrows
    public int inputInt() {
        return Integer.parseInt(reader.readLine().strip());
    }
}
