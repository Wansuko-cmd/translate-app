package com.wsr.transferapp

import com.airbnb.epoxy.TypedEpoxyController

class MainEpoxyController : TypedEpoxyController<List<String>>() {
    override fun buildModels(data: List<String>) {

        data.forEach { historyText ->
            historyRow {
                id("History")
                text(historyText)
            }
        }
    }
}
