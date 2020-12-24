package com.screenshare;

import lombok.Getter;
import lombok.Setter;

public class ControlStateRequest implements Request {
    @Getter
    @Setter
    boolean stopApp;
}
