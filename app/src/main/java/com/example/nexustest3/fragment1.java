package com.example.nexustest3;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RadioGroup;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;


public class fragment1 extends Fragment  {

    public static final String BASE_URL = "https://yh-finance.p.rapidapi.com/";
    private YahooFinanceAPI yahooFinanceAPI;

    private LineChart lineChart;
    private TextInputLayout stockTickerTextInputLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootview = (ViewGroup) inflater.inflate(R.layout.fragment1,container, false);

        lineChart = rootview.findViewById(R.id.activity_main_linechart);
        stockTickerTextInputLayout = rootview.findViewById(R.id.activity_main_stockticker);

        setHasOptionsMenu(true);
        configureLineChart();
        setupApi();

        rootview.findViewById(R.id.activity_main_getprices).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getStockData();
            }
        });

        return rootview;
    }



    private void configureLineChart() {
        Description desc = new Description();
        desc.setText("Stock Price History");
        desc.setTextSize(28);
        lineChart.setDescription(desc);

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setValueFormatter(new ValueFormatter() {
            private final SimpleDateFormat mFormat = new SimpleDateFormat("dd MMM", Locale.ENGLISH);

            @Override
            public String getFormattedValue(float value) {
                long millis = (long) value * 1000L;

                return mFormat.format(new Date(millis));
            }
        });
    }

    private void setupApi() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        yahooFinanceAPI = new retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(YahooFinanceAPI.class);
    }

    private void getStockData() {
        yahooFinanceAPI.getHistoricalData(
                "history",
                stockTickerTextInputLayout.getEditText().getText().toString()
        ).enqueue(new Callback<HistoricalDataResponse>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<HistoricalDataResponse> call, Response<HistoricalDataResponse> response) {
                ArrayList<Entry> pricesClose = new ArrayList<>();

                if (response.body() != null) {
                    for (int i = 0; i < response.body().prices.size(); i++) {
                        float x = response.body().prices.get(i).date;
                        float y = response.body().prices.get(i).close;
                        if (y != 0f) {
                            pricesClose.add(new Entry(x, response.body().prices.get(i).close));
                        }
                    }
                    Comparator<Entry> comparator = new Comparator<Entry>() {
                        @Override
                        public int compare(Entry o1, Entry o2) {
                            return Float.compare(o1.getX(), o2.getX());
                        }
                    };

                    pricesClose.sort(comparator);

                    setLineChartData( pricesClose);
                }
            }

            @Override
            public void onFailure(Call<HistoricalDataResponse> call, Throwable t) {

            }
        });
    }



    private void setLineChartData(ArrayList<Entry> pricesClose) {
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();

        LineDataSet closeLineDataSet = new LineDataSet(pricesClose, stockTickerTextInputLayout.getEditText().getText().toString() + " Price (Close)");
        closeLineDataSet.setDrawCircles(true);
        closeLineDataSet.setCircleRadius(1);
        closeLineDataSet.setDrawValues(false);
        closeLineDataSet.setLineWidth(3);
        closeLineDataSet.setColor(Color.rgb(255, 165, 0));
        closeLineDataSet.setCircleColor(Color.rgb(255, 165, 0));
        dataSets.add(closeLineDataSet);

        LineData lineData = new LineData(dataSets);
        lineChart.setData(lineData);
        lineChart.invalidate();
    }

}
