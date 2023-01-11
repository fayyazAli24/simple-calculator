package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {
    var tvInput:TextView?=null
    var lastNumeric=false
    var lastDot=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvInput=findViewById(R.id.tvInput)
    }

    fun onDigit(view:View){
        tvInput?.append((view as Button).text)
        lastNumeric=true
        lastDot=false
    }

    fun onClear(view:View){
        tvInput?.setText("")
    }

    fun onDecimalPoint(view:View){
        if(lastNumeric && !lastDot){
            tvInput?.append(".")
            lastNumeric=false
            lastDot=true
        }
    }


    fun onOperator(view:View){
        tvInput?.text?.let {
            if(lastNumeric && !isOperatorAdded(it.toString())){
                tvInput?.append((view as Button).text)
                lastNumeric=false
                lastDot=false
            }
        }
    }

    fun onEqual(view:View){
        if(lastNumeric){
            var tvValue = tvInput?.text.toString()
            var prefix=""
            try {
                if(tvValue.startsWith("-")){
                    prefix="-"
                    tvValue=tvValue.substring(1)
                }
                if(tvValue.contains("-")){
                    val splitValue= tvValue.split("-")
                    var one = splitValue[0]
                    var two=splitValue[1]

                    if(prefix.isNotEmpty()){
                        one = prefix+one
                    }
                    var calculatedValue = removeZeroAfterDot((one.toDouble()-two.toDouble()).toString())
                    tvInput?.text=calculatedValue
                }
                else  if(tvValue.contains("+")){
                    val splitValue= tvValue.split("+")
                    var one = splitValue[0]
                    var two=splitValue[1]

                    if(prefix.isNotEmpty()){
                        one = prefix+one
                    }
                    var calculatedValue = removeZeroAfterDot((one.toDouble()+two.toDouble()).toString())
                    tvInput?.text=calculatedValue
                }
                else  if(tvValue.contains("*")){
                    val splitValue= tvValue.split("*")
                    var one = splitValue[0]
                    var two=splitValue[1]

                    if(prefix.isNotEmpty()){
                        one = prefix+one
                    }
                    var calculatedValue = removeZeroAfterDot((one.toDouble()*two.toDouble()).toString())
                    tvInput?.text=calculatedValue
                }

                else  if(tvValue.contains("/")){
                    val splitValue= tvValue.split("/")
                    var one = splitValue[0]
                    var two=splitValue[1]

                    if(prefix.isNotEmpty()){
                        one = prefix+one
                    }
                    var calculatedValue = removeZeroAfterDot((one.toDouble()/two.toDouble()).toString())
                    tvInput?.text=calculatedValue
                }
            }catch(e:ArithmeticException){
                e.printStackTrace()
            }
        }

    }
    fun removeZeroAfterDot(result:String):String{
        var value = result
        if(result.contains(".0")){
            value= result.substring(0,result.length-2)
        }
        return value
    }

    fun isOperatorAdded(value:String):Boolean{
        if(value.startsWith("-")){
            return  false
        }
        else if(
            value.contains("/")
                    ||value.contains("+")
                    ||value.contains("*")
                    ||value.contains("-")
        ) {
            return true
        }
        return false
        }

}