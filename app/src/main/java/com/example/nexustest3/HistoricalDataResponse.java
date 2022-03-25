package com.example.nexustest3;

import java.util.List;

public class HistoricalDataResponse {
    List<StockPrice> prices;
    boolean isPending;
    long firstTradeDate;
    String id;

    class StockPrice {
        long date;
        float high;
        float low;
        float close;
    }
}