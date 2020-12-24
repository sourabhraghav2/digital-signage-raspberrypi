package com.screenshare;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


public interface Request  extends Serializable {
    public final  String requestType=null;
    public final  int id=0;
}
