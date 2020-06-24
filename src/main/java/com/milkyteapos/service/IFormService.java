package com.milkyteapos.service;

import com.milkyteapos.common.ServerResponse;

public interface IFormService {
    ServerResponse lineChart(String createYearStr);
    ServerResponse barChart(String createYearStr);
    ServerResponse pieChart(String createYearStr);
}
