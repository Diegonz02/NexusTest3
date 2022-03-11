package com.example.nexustest3;

public class HistoricalData  extends Thread{
    private static final String BASE_URL = "https://yh-finance.p.rapidapi.com/market/v2/get-quotes?region=US&symbols=AMD%2CIBM%2CAAPL";
    private static final String QUERY = "q=select%20*%20from%20yahoo.finance.historicaldata%20where%20symbol%20%3D%20%22";
    private static final String FORMAT = "&format=json";
    private static final String DIAGNOSTICS = "&diagnostics=true";
    private static final String ENV = "&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";
}
