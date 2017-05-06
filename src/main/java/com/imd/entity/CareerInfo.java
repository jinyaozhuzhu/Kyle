package com.imd.entity;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * Created by jinyao on 2017/4/15.
 */

public class CareerInfo implements Serializable{

    private Integer id;

    private String url;

    private String title;

    private String careerName;

    private Date endDate;

    private Integer endMilli;

    private Date startDate;

    private Integer startMilli;

    private String content;

    private String school;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCareerName() {
        return careerName;
    }

    public void setCareerName(String careerName) {
        this.careerName = careerName;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getEndMilli() {
        return endMilli;
    }

    public void setEndMilli(Integer endMilli) {
        this.endMilli = endMilli;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Integer getStartMilli() {
        return startMilli;
    }

    public void setStartMilli(Integer startMilli) {
        this.startMilli = startMilli;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public CareerInfo() {
    }

    public CareerInfo(Integer id, String url, String title, String careerName,
                      Date endDate, Integer endMilli, Date startDate, Integer startMilli,
                      String content, String school) {
        this.id = id;
        this.url = url;
        this.title = title;
        this.careerName = careerName;
        this.endDate = endDate;
        this.endMilli = endMilli;
        this.startDate = startDate;
        this.startMilli = startMilli;
        this.content = content;
        this.school = school;
    }

    @Override
    public String toString() {
        return "CareerInfo{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", title='" + title + '\'' +
                ", careerName='" + careerName + '\'' +
                ", endDate=" + endDate +
                ", endMilli=" + endMilli +
                ", startDate=" + startDate +
                ", startMilli=" + startMilli +
                ", content='" + content + '\'' +
                ", school='" + school + '\'' +
                '}';
    }
}
