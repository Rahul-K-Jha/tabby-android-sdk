package ai.tabby.android.ui

import ai.tabby.android.R
import ai.tabby.android.data.Currency
import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import android.view.LayoutInflater
import androidx.appcompat.widget.AppCompatTextView
import java.math.BigDecimal
import java.util.*

class TabbyInstallmentsWidget
    @JvmOverloads
    constructor(
        ctx: Context,
        attributeSet: AttributeSet? = null,
        defStyleAttr: Int = 0,
        defStyleRes: Int = 0
    )
    : ConstraintLayout(ctx, attributeSet, defStyleAttr, defStyleRes) {

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.installments_widget, this)
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

        val fullStr = resources.getString(R.string.installments_widget__amount, formattedPayment, currencyStr)
        findViewById<AppCompatTextView>(R.id.q1amount).text = fullStr
        findViewById<AppCompatTextView>(R.id.q2amount).text = fullStr
        findViewById<AppCompatTextView>(R.id.q3amount).text = fullStr
        findViewById<AppCompatTextView>(R.id.q4amount).text = fullStr
        requestLayout()
    }
}