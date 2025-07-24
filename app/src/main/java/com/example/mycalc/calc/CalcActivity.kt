package com.example.mycalc.calc

import android.annotation.SuppressLint
import android.os.Bundle
import android.provider.CalendarContract.Colors
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.example.mycalc.R
import com.example.mycalc.databinding.ActivityCalcBinding

class CalcActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var bind: ActivityCalcBinding
    private var firstNumber = 0.0
    private var operation = ""
    private var isNewOperation = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        bind = DataBindingUtil.setContentView(this, R.layout.activity_calc)

        bind.btn0.setOnClickListener(this)
        bind.btn1.setOnClickListener(this)
        bind.btn2.setOnClickListener(this)
        bind.btn3.setOnClickListener(this)
        bind.btn4.setOnClickListener(this)
        bind.btn5.setOnClickListener(this)
        bind.btn6.setOnClickListener(this)
        bind.btn7.setOnClickListener(this)
        bind.btn8.setOnClickListener(this)
        bind.btn9.setOnClickListener(this)
        bind.btnDot.setOnClickListener(this)
        bind.btnClear.setOnClickListener(this)
        bind.btnBackspace.setOnClickListener(this)
        bind.btnPercent.setOnClickListener(this)
        bind.btnDivide.setOnClickListener(this)
        bind.btnMutiply.setOnClickListener(this)
        bind.btnSubtract.setOnClickListener(this)
        bind.btnAdd.setOnClickListener(this)
        bind.btnEqual.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn0->{
                appendNumber("0")
            }
            R.id.btn1->{
                appendNumber("1")
            }
            R.id.btn2->{
                appendNumber("2")
            }
            R.id.btn3->{
                appendNumber("3")
            }
            R.id.btn4->{
                appendNumber("4")
            }
            R.id.btn5->{
                appendNumber("5")
            }
            R.id.btn6->{
                appendNumber("6")
            }
            R.id.btn7->{
                appendNumber("7")
            }
            R.id.btn8->{
                appendNumber("8")
            }
            R.id.btn9->{
                appendNumber("9")
            }
            R.id.btnDot->{
                appendNumber(".")
            }
            R.id.btnClear->{
                clearCalculate()
            }
            R.id.btnBackspace->{
                backspaceCalculate()
            }
            R.id.btnPercent->{
                setOperator("%")
            }
            R.id.btnDivide->{
                setOperator("÷")
            }
            R.id.btnMutiply->{
                setOperator("*")
            }
            R.id.btnSubtract->{
                setOperator("-")
            }
            R.id.btnAdd->{
                setOperator("+")
            }
            R.id.btnEqual->{
                calculateResult()
            }
        }
    }

    private fun appendNumber(num: String){
        if (isNewOperation){
            bind.resultCalTxt.text = num
            isNewOperation = false
        }else{
            bind.resultCalTxt.append(num)
        }
    }

    private fun setOperator(op: String){
        if (bind.previousCalTxt.text.toString().isNotEmpty() && isContainOperator() && !bind.previousCalTxt.text.toString().contains("=")){
            firstNumber = calculatePrevious(bind.previousCalTxt.text.toString(), bind.resultCalTxt.text.toString().toDouble())
            operation = op
            isNewOperation = true
            bind.previousCalTxt.text = "$firstNumber $operation"
            bind.resultCalTxt.text = "0"
        }else {
            firstNumber = bind.resultCalTxt.text.toString().toDouble()
            operation = op
            isNewOperation = true
            bind.previousCalTxt.text = "$firstNumber $operation"
            bind.resultCalTxt.text = "0"
        }
    }

    private fun calculatePrevious(str: String, nxtVal: Double): Double{
        var total: Double = 0.0
        val preVal: Double = str.toString().dropLast(2).toDouble()
        if (str.contains(" +")){
            total = preVal + nxtVal
        }else if (str.contains(" -")){
            total = preVal - nxtVal
        }else if (str.contains(" *")){
            total = preVal * nxtVal
        }else if (str.contains(" ÷")){
            total = preVal / nxtVal
        }else if (str.contains(" %")){
            total = preVal % nxtVal
        }
        return total
    }

    private fun isContainOperator(): Boolean {
        if (bind.previousCalTxt.text.toString().contains(" +") ||
            bind.previousCalTxt.text.toString().contains(" -") ||
            bind.previousCalTxt.text.toString().contains(" *") ||
            bind.previousCalTxt.text.toString().contains(" ÷") ||
            bind.previousCalTxt.text.toString().contains(" %"))
            return true
        else
            return false
    }

    @SuppressLint("ResourceAsColor")
    private fun calculateResult(){
        if (!isNewOperation && bind.previousCalTxt.text.toString().isNotEmpty() && !bind.previousCalTxt.text.toString().contains("=")) {
            try {
                val secondNum = bind.resultCalTxt.text.toString().toDouble()
                val totalResult: Double = when (operation) {
                    "+" -> (firstNumber + secondNum)
                    "-" -> (firstNumber - secondNum)
                    "*" -> (firstNumber * secondNum)
                    "÷" -> (firstNumber / secondNum)
                    "%" -> (firstNumber % secondNum)
                    else -> secondNum
                }

                bind.previousCalTxt.text = "$firstNumber $operation $secondNum ="
                bind.resultCalTxt.text = "$totalResult"
                isNewOperation = true
            } catch (e: Exception) {
                bind.resultCalTxt.text = "Error"
                //bind.resultCalTxt.setTextColor(R.color.clearColor)
            }
        }
    }

    private fun clearCalculate(){
        bind.previousCalTxt.text = ""
        bind.resultCalTxt.text = "0"
        firstNumber = 0.0
        operation = ""
        isNewOperation = true
    }

    private fun backspaceCalculate(){
        if (bind.previousCalTxt.text.toString().isNotEmpty() && bind.previousCalTxt.text.toString().contains("=")){
            bind.resultCalTxt.text = "0"
            bind.previousCalTxt.text = ""
            firstNumber = 0.0
            operation = ""
            isNewOperation = true
        }else{
            if (bind.resultCalTxt.text.isNotEmpty()) {
                bind.resultCalTxt.text = bind.resultCalTxt.text.dropLast(1)
                if (bind.resultCalTxt.text.isEmpty() && bind.previousCalTxt.text.isEmpty()) {
                    bind.resultCalTxt.text = "0"
                    bind.previousCalTxt.text = ""
                    firstNumber = 0.0
                    operation = ""
                    isNewOperation = true
                }
            }else{
                bind.resultCalTxt.text = bind.previousCalTxt.text.dropLast(2)
                bind.previousCalTxt.text = ""
                firstNumber = 0.0
                operation = ""
                isNewOperation = true
            }
        }
    }
}