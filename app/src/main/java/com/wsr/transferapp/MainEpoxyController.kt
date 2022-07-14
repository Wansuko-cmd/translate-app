package com.wsr.transferapp

import android.view.View
import com.airbnb.epoxy.TypedEpoxyController
import java.util.*

class MainEpoxyController(
    private val onClickCopyListener: (String) -> Unit,
) : TypedEpoxyController<List<String>>() {
    override fun buildModels(data: List<String>) {

        data.forEach { historyText ->
            historyRow {
                id(UUID.randomUUID().toString())
                text(historyText)
                onClick(View.OnClickListener { this@MainEpoxyController.onClickCopyListener(historyText) })
            }
        }
    }
}
