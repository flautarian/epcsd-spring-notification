package edu.uoc.epcsd.notification.pojos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.sql.Time;
import java.util.Date;

public class Performance {
    private Date date;
    private Time time;
    private String streamingUrl;
    private Integer remainingSeats;
    private String status;
}
