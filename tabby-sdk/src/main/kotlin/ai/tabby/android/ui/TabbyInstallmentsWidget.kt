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
            updateAmount()
        }

    var currency: Currency = Currency.AED
        set(value) {
            field = value
            updateCurrency()
        }

    private fun updateAmount() {
        val installment = amount.toDouble() / 4
        val formatted = String.format(Locale.getDefault(), "%.2f", installment)
        findViewById<AppCompatTextView>(R.id.q1amount).text = formatted
        findViewById<AppCompatTextView>(R.id.q2amount).text = formatted
        findViewById<AppCompatTextView>(R.id.q3amount).text = formatted
        findViewById<AppCompatTextView>(R.id.q4amount).text = formatted
        requestLayout()
    }

    private fun updateCurrency() {
        val currencyStrId = when (currency) {
            Currency.AED -> R.string.widget__currency__aed
            Currency.SAR -> R.string.widget__currency__sar
            Currency.BHD -> R.string.widget__currency__bhd
            Currency.KWD -> R.string.widget__currency__kwd
        }
        val currencyStr = context.getString(currencyStrId)
        findViewById<AppCompatTextView>(R.id.q1currency).text = currencyStr
        findViewById<AppCompatTextView>(R.id.q2currency).text = currencyStr
        findViewById<AppCompatTextView>(R.id.q3currency).text = currencyStr
        findViewById<AppCompatTextView>(R.id.q4currency).text = currencyStr
        requestLayout()
    }
}