package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewTreeObserver
import android.widget.Toast
import com.example.calculator.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Error

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private var operator :Char = '+'

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onNumberClicked()
        onOperatorClicked()

    }

    private fun onNumberClicked(){

        binding.btnZero.setOnClickListener{
            if (binding.txtAnswerBig.text.isNotEmpty()){
                if (binding.txtAnswer.text.isNotEmpty()){
                    binding.txtAnswer.text = ""
                    binding.txtAnswerBig.text = ""
                }else{
                    appendText("0")
                }
            }

        }

        binding.btnOne.setOnClickListener{
            appendText("1")
        }

        binding.btnTwo.setOnClickListener{
            appendText("2")
        }

        binding.btnThree.setOnClickListener{
            appendText("3")
        }

        binding.btnFour.setOnClickListener{
            appendText("4")
        }

        binding.btnFive.setOnClickListener{
            appendText("5")
        }

        binding.btnSix.setOnClickListener{
            appendText("6")
        }

        binding.btnSeven.setOnClickListener{
            appendText("7")
        }

        binding.btnEight.setOnClickListener{
            appendText("8")
        }

        binding.btnNine.setOnClickListener{
            appendText("9")
        }

        binding.btnDot.setOnClickListener{
            if (binding.txtAnswerBig.text.isEmpty() || binding.txtAnswer.text.isNotEmpty()){
                appendText("0.")
            }else if (!binding.txtAnswerBig.text.contains(".")){
                appendText(".")
            }
        }

    }

    private fun appendText(newText :String){
        if (binding.txtAnswer.text.isNotEmpty()){
            binding.txtAnswerBig.text = ""
            binding.txtAnswer.text = ""

            binding.txtAnswerBig.append(newText)
        }else{
            binding.txtAnswerBig.append(newText)
        }

        val vto :ViewTreeObserver = binding.horizontalScroll.viewTreeObserver
        vto.addOnGlobalLayoutListener ( object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                binding.horizontalScroll.viewTreeObserver.removeOnGlobalLayoutListener(this)
                binding.horizontalScroll.scrollTo(binding.txtAnswerBig.width, 0)
            }
        })
    }

    private fun onOperatorClicked(){

        binding.btnPlus.setOnClickListener{
            if ( binding.txtAnswer.text.isEmpty() && binding.txtAnswerBig.text.isNotEmpty() ){
                val myChar = binding.txtAnswerBig.text.last()

                if (myChar != '+' && myChar != '-' && myChar != '*' && myChar != '/'){
                    appendText("+")
                }
            }
        }

        binding.btnMinus.setOnClickListener{
            if ( binding.txtAnswer.text.isEmpty() && binding.txtAnswerBig.text.isNotEmpty() ){
                val myChar = binding.txtAnswerBig.text.last()

                if (myChar != '+' && myChar != '-' && myChar != '*' && myChar != '/'){
                    appendText("-")
                }
            }
        }

        binding.btnMultiple.setOnClickListener{
            if ( binding.txtAnswer.text.isEmpty() && binding.txtAnswerBig.text.isNotEmpty() ){
                val myChar = binding.txtAnswerBig.text.last()

                if (myChar != '+' && myChar != '-' && myChar != '*' && myChar != '/'){
                    appendText("*")
                }
            }
        }

        binding.btnDivision.setOnClickListener{
            if ( binding.txtAnswer.text.isEmpty() && binding.txtAnswerBig.text.isNotEmpty() ){
                val myChar = binding.txtAnswerBig.text.last()

                if (myChar != '+' && myChar != '-' && myChar != '*' && myChar != '/'){
                    appendText("/")
                }
            }
        }

        binding.btnPClose.setOnClickListener{
            appendText(")")
        }

        binding.btnPOpen.setOnClickListener{
            appendText("(")
        }

        binding.btnAC.setOnClickListener{
            binding.txtAnswer.text = ""
            binding.txtAnswerBig.text = ""
        }

        binding.btnClear.setOnClickListener{
            val oldText = binding.txtAnswerBig.text.toString()

            if (oldText.isNotEmpty()){
                binding.txtAnswerBig.text = oldText.substring(0, oldText.length-1)
            }
        }

        binding.btnEqual.setOnClickListener{

            try {
                val expression = ExpressionBuilder( binding.txtAnswerBig.text.toString() ).build()
                val result = expression.evaluate()

                val longResult = result.toLong()

                if (result == longResult.toDouble()){
                    binding.txtAnswer.text = longResult.toString()
                }else{
                    binding.txtAnswer.text = result.toString()
                }
            }catch (e : Exception ){
                binding.txtAnswerBig.text = ""
                binding.txtAnswer.text = ""
                Toast.makeText(this, "پدسگ درست بزن!!!!!!!!", Toast.LENGTH_LONG).show()
            }

        }

    }
}