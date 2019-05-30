package com.jobsity.challenge.service;

import com.jobsity.challenge.model.FramesResult;

import java.util.List;

public interface IPrinterService {

    void printResults(final List<FramesResult> framesResult);
}
