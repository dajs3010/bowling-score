package com.jobsity.challenge.service;

import com.jobsity.challenge.exceptions.InvalidScoreException;

import java.io.IOException;
import java.util.List;

public interface IFileReaderService {

    List<String> readFile(String fileName) throws IOException, InvalidScoreException;

}
