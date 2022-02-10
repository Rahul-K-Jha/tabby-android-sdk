package ai.tabby.android.ui

import ai.tabby.android.R
import ai.tabby.android.data.Currency
import ai.tabby.android.internal.ui.SimpleWebActivity
import android.content.Context
import android.text.Html
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import android.view.LayoutInflater
import androidx.appcompat.widget.AppCompatTextView
import java.math.BigDecimal
import java.util.*

class TabbySnippedWidget
    @JvmOverloads
    constructor(
        ctx: Context,
        attributeSet: AttributeSet? = null,
        defStyleAttr: Int = 0,
        defStyleRes: Int = 0
    ) : ConstraintLayout(ctx, attributeSet, defStyleAttr, defStyleRes) {

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.snippet_widget, this)
        view.setOnClickListener {
            val urlStr = resources.getString(R.string.snippet_widget__url)
            SimpleWebActivity.createIntent(context, urlStr).let {
                context.startActivity(it)
            }
        }
    }

    var amount: BigDecimal = BigDecimal.ZERO
        set(value) {
            field = value
            update()
        }

    var currency: Currency = Currency.AED
        set(value) {
            field = value
            update()
        }

    private fun update() {
        val payment = amount.toDouble() / 4
        val formattedPayment = String.format(Locale.getDefault(), "%.2f", payment)

        val currencyStrId = when (currency) {
            Currency.AED -> R.string.widget__currency__aed
            Currency.SAR -> R.string.widget__currency__sar
            Currency.BHD -> R.string.widget__currency__bhd
            Currency.KWD -> R.string.widget__currency__kwd
        }
        val currencyStr = context.getString(currencyStrId)

        val fullStr = resources.getString(R.string.snippet_widget__title, formattedPayment, currencyStr)
        findViewById<AppCompatTextView>(R.id.widgetTitle).text = Html.fromHtml(fullStr)
        requestLayout()
    }
}