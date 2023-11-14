import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.englishdiary.CalenderViewHolder
import com.example.englishdiary.MyModel
import com.example.englishdiary.R
import io.realm.Realm
import io.realm.kotlin.where
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class CalenderAdapter(
    private val daysOfMonth: ArrayList<String>,
    private val onItemListener: OnItemListener,
    private val selectedDate: LocalDate
) : RecyclerView.Adapter<CalenderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalenderViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.calender_cell, parent, false)
        val layoutParams = view.layoutParams
        layoutParams.height = (parent.height * 0.166666666).toInt()
        return CalenderViewHolder(view, onItemListener)
    }

    override fun onBindViewHolder(holder: CalenderViewHolder, position: Int) {
        holder.dayOfMonth.text = daysOfMonth[position]

        val dayOfMonth = daysOfMonth[position]

        if (dayOfMonth.isNotEmpty()) {
            val year = selectedDate.year
            val month = selectedDate.monthValue
            val cellDate = "$year-${String.format("%02d", month)}-$dayOfMonth"

            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            val selectedDateFormatted = selectedDate.format(formatter)

            val realm = Realm.getDefaultInstance()
            val diaryData = realm.where<MyModel>().equalTo("date", cellDate).findFirst()

            if (diaryData != null) {
                holder.itemView.setBackgroundColor(Color.YELLOW)
            } else {
                holder.itemView.setBackgroundColor(Color.WHITE)
            }
            realm.close()
        } else {
            holder.itemView.setBackgroundColor(Color.WHITE)
        }
    }

    override fun getItemCount(): Int {
        return daysOfMonth.size
    }

    interface OnItemListener {
        fun onItemClick(position: Int, dayText: String)
    }
}