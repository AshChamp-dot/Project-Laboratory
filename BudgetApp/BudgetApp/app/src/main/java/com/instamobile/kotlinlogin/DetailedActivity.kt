package com.instamobile.kotlinlogin

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.widget.addTextChangedListener
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_add_transaction.*
import kotlinx.android.synthetic.main.activity_add_transaction.amountInput
import kotlinx.android.synthetic.main.activity_add_transaction.amountLayout
import kotlinx.android.synthetic.main.activity_add_transaction.closeBtn
import kotlinx.android.synthetic.main.activity_add_transaction.descriptionInput
import kotlinx.android.synthetic.main.activity_add_transaction.labelInput
import kotlinx.android.synthetic.main.activity_add_transaction.labelLayout
import kotlinx.android.synthetic.main.activity_detailed.*
import kotlinx.android.synthetic.main.activity_transactionpage.*
import kotlinx.android.synthetic.main.transaction_layout.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DetailedActivity : AppCompatActivity() {
    private lateinit var transaction : Transaction

    fun draw(view : View){

        val arrayList = ArrayList<Int>()
        arrayList.add(Integer.valueOf(47.toString()))
        arrayList.add(Integer.valueOf(23.toString()))
        arrayList.add(Integer.valueOf(35.toString()))
        arrayList.add(Integer.valueOf(20.toString()))

        val intent = Intent(this, PiechartActivity::class.java)
        intent.putIntegerArrayListExtra("arrayList",arrayList)
        startActivity(intent)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed)

        transaction = intent.getSerializableExtra("transaction") as Transaction

        labelInput.setText(transaction.label)
        amountInput.setText(transaction.amount.toString())
        descriptionInput.setText(transaction.description)


        rootView.setOnClickListener {
            this.window.decorView.clearFocus()

            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(it.windowToken, 0)
        }

        labelInput.addTextChangedListener {
            updateBtn.visibility = View.VISIBLE
            if(it!!.count() > 0)
                labelLayout.error = null
        }

        amountInput.addTextChangedListener {
            updateBtn.visibility = View.VISIBLE
            if(it!!.count() > 0)
                amountLayout.error = null
        }

        descriptionInput.addTextChangedListener {
            updateBtn.visibility = View.VISIBLE
        }

        updateBtn.setOnClickListener {
            val label = labelInput.text.toString()
            val description = descriptionInput.text.toString()
            val amount = amountInput.text.toString().toDoubleOrNull()

            if(label.isEmpty())
                labelLayout.error = "Please neter a valid label"

            else if(amount == null)
                amountLayout.error = "Please enter a valid amount"
            else {
                val transaction  = Transaction(transaction.id, label, amount, description)
                update(transaction)
            }
        }

        closeBtn.setOnClickListener {
            finish()
        }
    }

    private fun update(transaction: Transaction){
        val db = Room.databaseBuilder(this,
            AppDatabase::class.java,
            "transactions").build()

        GlobalScope.launch {
            db.transactionDao().update(transaction)
            finish()
        }
    }

}
