package com.jobsity.challenge.service.impl;

import com.jobsity.challenge.exceptions.InvalidScoreException;
import com.jobsity.challenge.service.IFileReaderService;
import com.jobsity.challenge.service.IValidator;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class FileReaderService implements IFileReaderService {

    private final IValidator validator;

    public FileReaderService(IValidator validator) {
        this.validator = validator;
    }

    public List<String> readFile(String fileName) throws IOException, InvalidScoreException {
        final Path path = Paths.get(fileName);
        final long size = Files.size(path);
        validator.validateFileSize(size);
        final Stream<String> lines = Files.lines(path);
        return lines.collect(Collectors.toList());
    }
}
