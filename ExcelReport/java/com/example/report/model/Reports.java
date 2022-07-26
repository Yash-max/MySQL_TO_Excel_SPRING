package com.example.report.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "reports")
@Setter
@Getter
public class Reports extends BaseEntity {

    @Column(name = "device_name")
    private String deviceName;
    @Column(name = "app_version")
    private String appVersion;
    @Column(name = "platform_version")
    private String platformVersion;
    @Column(name = "platform_name")
    private String platformName;
    @Column(name = "test_status")
    private String testStatus;
    @Column(name = "test_failed_reason")
    private String testFailedReason;
    @Column(name = "script_name")
    private String scriptName;
    @Column(name = "script_start_time")
    private Date scriptStartTime;
    @Column(name = "script_end_time")
    private Date scriptEndTime;
    @Column(name = "total_time_taken")
    private Float totalTimeTaken;
    @Column(name = "device_type")
    private String deviceType;
    @Column(name = "network_name")
    private String networkName;
    @Column(name = "network_type")
    private String networkType;

    private String udid;
    private String ics;
    private Integer ram;


    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "reports")
    private List<Pages> pagesList;
}