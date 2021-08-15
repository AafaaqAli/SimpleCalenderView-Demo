package studio.devcode.demo

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import studio.devcode.simplecalender.CalenderViewDialogBuilder
import studio.devcode.simplecalender.SimpleCalenderManager
import studio.devcode.simplecalender.entities.enum.CalenderParams
import studio.devcode.simplecalender.mediators.CalenderCallback
import studio.devcode.views.StatusBar
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var calenderManager: SimpleCalenderManager
    private lateinit var button: Button
    private lateinit var statusBar: StatusBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button = findViewById(R.id.buttonShowCalender)
        statusBar = findViewById(R.id.status_bar)



        buildCalender()
        showDialog()
    }

    private fun buildCalender() {
        calenderManager = SimpleCalenderManager(CalenderViewDialogBuilder.Builder()
                .setCalenderCallBack(object : CalenderCallback {
                    override fun onDateSelected(date: Long) {
                        Toast.makeText(this@MainActivity, "Date Selected ${
                            getDate(date, "dd/MM/yyyy hh:mm:ss.SSS")
                        }", Toast.LENGTH_SHORT).show()
                    }

                    override fun onCalenderOpened() {
                        Toast.makeText(this@MainActivity, "On Opened", Toast.LENGTH_SHORT).show()
                    }

                    override fun onCalenderDismissed() {
                        Toast.makeText(this@MainActivity, "On Dismissed", Toast.LENGTH_SHORT).show()
                    }

                    override fun onError(errorMessage: String) {
                        Toast.makeText(this@MainActivity, errorMessage, Toast.LENGTH_SHORT).show()
                    }
                })
                .setBottomCornersRound(false)
                .setCalenderGravity(CalenderParams.BOTTOM)
                .setDismiss(CalenderParams.DISABLE_DISMISS_ON_TOUCH)
                .setTopCornersRound(true)
                .withContext(this)
                .Build())
    }

    private fun showDialog() {
        button.setOnClickListener {
            calenderManager.show()
        }
    }

    fun getDate(milliSeconds: Long, dateFormat: String?): String? {
        // Create a DateFormatter object for displaying date in specified format.
        val formatter = SimpleDateFormat(dateFormat)

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        val calendar: Calendar = Calendar.getInstance()
        calendar.setTimeInMillis(milliSeconds)
        return formatter.format(calendar.getTime())
    }
}