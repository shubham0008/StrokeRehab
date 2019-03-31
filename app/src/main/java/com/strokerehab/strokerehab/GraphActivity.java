package com.strokerehab.strokerehab;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.core.cartesian.series.Line;
import com.anychart.data.Mapping;
import com.anychart.data.Set;
import com.anychart.enums.Anchor;
import com.anychart.enums.MarkerType;
import com.anychart.enums.TooltipPositionMode;
import com.anychart.graphics.vector.Stroke;

import java.util.ArrayList;
import java.util.List;


public class GraphActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_graph );
        centerTitle();

        AnyChartView anyChartView = findViewById( R.id.any_chart_view );
        anyChartView.setProgressBar( findViewById( R.id.progress_bar ) );

        Cartesian cartesian = AnyChart.line();

        cartesian.animation( true );

        cartesian.padding( 10d, 20d, 5d, 20d );

        cartesian.crosshair().enabled( true );
        cartesian.crosshair()
                .yLabel( true )
                .yStroke( (Stroke) null, null, null, (String) null, (String) null );

        cartesian.tooltip().positionMode( TooltipPositionMode.POINT );

        cartesian.title( "Pressure VS Time Graph to depict the progress of patient" );

        cartesian.yAxis( 0 ).title( "Pressure Exerted" );
        cartesian.xAxis( 0 ).labels().padding( 5d, 5d, 5d, 5d );

        List<DataEntry> seriesData = new ArrayList<>();
        seriesData.add( new CustomDataEntry( "1986", 3.6, 2.3, 2.8 ) );
        seriesData.add( new CustomDataEntry( "1987", 7.1, 4.0, 4.1 ) );
        seriesData.add( new CustomDataEntry( "1988", 8.5, 6.2, 5.1 ) );
        seriesData.add( new CustomDataEntry( "1989", 9.2, 11.8, 6.5 ) );
        seriesData.add( new CustomDataEntry( "1990", 10.1, 13.0, 12.5 ) );
        seriesData.add( new CustomDataEntry( "1991", 11.6, 13.9, 18.0 ) );
        seriesData.add( new CustomDataEntry( "1992", 16.4, 18.0, 21.0 ) );
        seriesData.add( new CustomDataEntry( "1993", 18.0, 23.3, 20.3 ) );
        seriesData.add( new CustomDataEntry( "1994", 13.2, 24.7, 19.2 ) );
        seriesData.add( new CustomDataEntry( "1995", 12.0, 18.0, 14.4 ) );
        seriesData.add( new CustomDataEntry( "1996", 3.2, 15.1, 9.2 ) );
        seriesData.add( new CustomDataEntry( "1997", 4.1, 11.3, 5.9 ) );
        seriesData.add( new CustomDataEntry( "1998", 6.3, 14.2, 5.2 ) );
        seriesData.add( new CustomDataEntry( "1999", 9.4, 13.7, 4.7 ) );
        seriesData.add( new CustomDataEntry( "2000", 11.5, 9.9, 4.2 ) );
        Set set = Set.instantiate();
        set.data( seriesData );
        Mapping series1Mapping = set.mapAs( "{ x: 'x', value: 'value' }" );
        Mapping series2Mapping = set.mapAs( "{ x: 'x', value: 'value2' }" );
        Mapping series3Mapping = set.mapAs( "{ x: 'x', value: 'value3' }" );

        Line series1 = cartesian.line( series1Mapping );
        series1.name( "Finger1" );
        series1.hovered().markers().enabled( true );
        series1.hovered().markers()
                .type( MarkerType.CIRCLE )
                .size( 4d );
        series1.tooltip()
                .position( "right" )
                .anchor( Anchor.LEFT_CENTER )
                .offsetX( 5d )
                .offsetY( 5d );

        Line series2 = cartesian.line( series2Mapping );
        series2.name( "Finger2" );
        series2.hovered().markers().enabled( true );
        series2.hovered().markers()
                .type( MarkerType.CIRCLE )
                .size( 4d );
        series2.tooltip()
                .position( "right" )
                .anchor( Anchor.LEFT_CENTER )
                .offsetX( 5d )
                .offsetY( 5d );

        Line series3 = cartesian.line( series3Mapping );
        series3.name( "Finger3" );
        series3.hovered().markers().enabled( true );
        series3.hovered().markers()
                .type( MarkerType.CIRCLE )
                .size( 4d );
        series3.tooltip()
                .position( "right" )
                .anchor( Anchor.LEFT_CENTER )
                .offsetX( 5d )
                .offsetY( 5d );

        cartesian.legend().enabled( true );
        cartesian.legend().fontSize( 13d );
        cartesian.legend().padding( 0d, 0d, 10d, 0d );

        anyChartView.setChart( cartesian );

    }

    private void centerTitle() {
        ArrayList<View> textViews = new ArrayList<>();

        getWindow().getDecorView().findViewsWithText( textViews, getTitle(), View.FIND_VIEWS_WITH_TEXT );

        if (textViews.size() > 0) {
            AppCompatTextView appCompatTextView = null;
            if (textViews.size() == 1) {
                appCompatTextView = (AppCompatTextView) textViews.get( 0 );
            } else {
                for (View v : textViews) {
                    if (v.getParent() instanceof Toolbar) {
                        appCompatTextView = (AppCompatTextView) v;
                        break;
                    }
                }
            }

            if (appCompatTextView != null) {
                ViewGroup.LayoutParams params = appCompatTextView.getLayoutParams();
                params.width = ViewGroup.LayoutParams.MATCH_PARENT;
                appCompatTextView.setLayoutParams( params );
                appCompatTextView.setTextAlignment( View.TEXT_ALIGNMENT_CENTER );
            }
        }
    }

    private class CustomDataEntry extends ValueDataEntry {

        CustomDataEntry(String x, Number value, Number value2, Number value3) {
            super( x, value );
            setValue( "value2", value2 );
            setValue( "value3", value3 );
        }

    }
}
