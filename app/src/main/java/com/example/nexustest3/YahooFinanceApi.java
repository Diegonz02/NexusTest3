package com.example.nexustest3;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

interface YahooFinanceAPI {
    @Headers({
            "x-rapidapi-host: yh-finance.p.rapidapi.com",
            "x-rapidapi-key: 2518301bbcmshbea44452901123ep17b88djsn8c2977f58571"
    })
    @GET("/stock/v3/get-historical-data")
    Call<HistoricalDataResponse> getHistoricalData(
//            @Query("frequency") String frequency,
            @Query("filter") String filter,
//            @Query("period1") String period1,
//            @Query("period2") String period2,
            @Query("symbol") String symbol
    );
}
