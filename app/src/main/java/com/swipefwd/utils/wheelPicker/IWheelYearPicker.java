package com.swipefwd.utils.wheelPicker;

public interface IWheelYearPicker {

    void setYearFrame(int start, int end);

    int getYearStart();

    void setYearStart(int start);

    int getYearEnd();

    void setYearEnd(int end);

    int getSelectedYear();

    void setSelectedYear(int year);

    int getCurrentYear();
}