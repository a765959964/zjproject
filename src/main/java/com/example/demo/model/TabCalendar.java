package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "tab_calendar")
public class TabCalendar {
    @Id
    private Integer id;

    /**
     * 年份
     */
    @Column(name = "calendar_year")
    private String calendarYear;

    /**
     * 日历节假日日期
     */
    @Column(name = "calendar_date")
    private Date calendarDate;

    /**
     * 月份
     */
    @Column(name = "calendar_month")
    private String calendarMonth;

    private Integer status;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取年份
     *
     * @return calendar_year - 年份
     */
    public String getCalendarYear() {
        return calendarYear;
    }

    /**
     * 设置年份
     *
     * @param calendarYear 年份
     */
    public void setCalendarYear(String calendarYear) {
        this.calendarYear = calendarYear == null ? null : calendarYear.trim();
    }

    /**
     * 获取日历节假日日期
     *
     * @return calendar_date - 日历节假日日期
     */
    public Date getCalendarDate() {
        return calendarDate;
    }

    /**
     * 设置日历节假日日期
     *
     * @param calendarDate 日历节假日日期
     */
    public void setCalendarDate(Date calendarDate) {
        this.calendarDate = calendarDate;
    }

    /**
     * 获取月份
     *
     * @return calendar_month - 月份
     */
    public String getCalendarMonth() {
        return calendarMonth;
    }

    /**
     * 设置月份
     *
     * @param calendarMonth 月份
     */
    public void setCalendarMonth(String calendarMonth) {
        this.calendarMonth = calendarMonth == null ? null : calendarMonth.trim();
    }

    /**
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
}