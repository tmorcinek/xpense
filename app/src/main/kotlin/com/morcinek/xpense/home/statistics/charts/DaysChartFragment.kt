package com.morcinek.xpense.home.statistics.charts

import android.os.Bundle
import android.view.View
import com.morcinek.xpense.R
import com.morcinek.xpense.common.fragments.BaseFragment
import com.morcinek.xpense.common.pager.PagerAdapter
import com.morcinek.xpense.common.utils.disableScroll
import com.morcinek.xpense.common.utils.enableScroll
import com.morcinek.xpense.data.expense.ExpenseManager
import kotlinx.android.synthetic.main.days_charts.*
import kotlinx.android.synthetic.main.home_content.*
import lecho.lib.hellocharts.model.*
import lecho.lib.hellocharts.util.ChartUtils
import java.util.*
import javax.inject.Inject

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class DaysChartFragment : BaseFragment(), PagerAdapter.Page {

    override val title = R.string.days_chart_label

    override fun getLayoutResourceId() = R.layout.days_charts

    @Inject
    lateinit var expenseManager: ExpenseManager

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity.toolbar.disableScroll()
        activity.fab.hide()
    }

    override fun onDetach() {
        super.onDetach()
        activity.toolbar.enableScroll()
        activity.fab.show()
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        chart.setLineChartData(generateLineChartData())
        chart.setZoomEnabled(false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    private fun generateLineChartData(): LineChartData {
        val numValues = 12

        val values = ArrayList<PointValue>()
        val axisValues = ArrayList<AxisValue>()

        val months = arrayOf("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec")

        for (i in 0..numValues - 1) {
            values.add(PointValue(i.toFloat(), Math.random().toFloat() * 100f))
            axisValues.add(AxisValue(i.toFloat()).setLabel(months[i]))
        }

        val line = Line(values)
        line.setColor(ChartUtils.COLOR_GREEN)

        val lines = ArrayList<Line>()
        lines.add(line)

        val data = LineChartData(lines)

        data.axisXBottom = Axis(axisValues).setHasLines(true)
        //            data.setAxisXBottom(new Axis().setName("Days"));
        data.axisYLeft = Axis().setName("Expense ($)").setHasLines(true)
        return data

    }
}