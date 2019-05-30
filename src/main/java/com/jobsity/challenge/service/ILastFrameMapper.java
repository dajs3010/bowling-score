package com.jobsity.challenge.service;

import com.jobsity.challenge.model.Shot;

public interface ILastFrameMapper {

    Shot getLastFrameFirstShot(String value);

    Shot getLastFrameSecondShot(String actual, String next, Shot first);

    Shot getLastFrameLastShot(String afterNext, String next, Shot second);

}
