package com.instamobile.kotlinlogin

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.graphics.Color
import android.widget.TextView
import com.androidplot.pie.Segment
import com.androidplot.pie.SegmentFormatter
import kotlinx.android.synthetic.main.activity_piechart.*
import org.eazegraph.lib.charts.PieChart
import org.eazegraph.lib.models.PieModel

class PiechartActivity : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_piechart)

        val arrayList = intent.getIntegerArrayListExtra("arrayList")

        val s1 = Segment("S1", arrayList?.get(0) ?: 10)
        val s2 = Segment("S2", arrayList?.get(1) ?: 5)
        val s3 = Segment("S3", arrayList?.get(2) ?: 25)
        val s4 = Segment("S4", arrayList?.get(3) ?: 30)

        val sf1 = SegmentFormatter(Color.BLUE)
        val sf2 = SegmentFormatter(Color.RED)
        val sf3 = SegmentFormatter(Color.GREEN)
        val sf4 = SegmentFormatter(Color.YELLOW)

        piechart.addSegment(s1,sf1)
        piechart.addSegment(s2,sf2)
        piechart.addSegment(s3,sf3)
        piechart.addSegment(s4,sf4)

    }









    }




