package ru.yandex.practicum.entity;

import lombok.Data;

import java.sql.Date;

@Data
public class Membership {
    private long id;
    private long clientId;
    private long membershipTypeId;
    private Date startDate;
    private Date endDate;
    private long membershipStatus;
    private int totalFreezeDays;
}
