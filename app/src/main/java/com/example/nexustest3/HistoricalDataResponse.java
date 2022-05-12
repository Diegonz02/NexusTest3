package com.example.nexustest3;

import java.util.List;

public class HistoricalDataResponse {
    List<StockPrice> prices;

    class StockPrice {
        long date;
        float close;
    }
}