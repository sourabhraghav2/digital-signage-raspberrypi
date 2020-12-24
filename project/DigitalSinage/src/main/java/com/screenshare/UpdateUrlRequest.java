package com.screenshare;


import lombok.Getter;
import lombok.Setter;
public class UpdateUrlRequest implements Request {
    @Getter
    @Setter
    String url;
}
