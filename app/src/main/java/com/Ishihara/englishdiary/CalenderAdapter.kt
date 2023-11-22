import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.Ishihara.englishdiary.CalenderViewHolder
import com.Ishihara.englishdiary.MyModel
import com.Ishihara.englishdiary.R
import io.realm.Realm
import io.realm.kotlin.where
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class CalenderAdapter(
    private val daysOfMonth: ArrayList<String>, // 月の日数の配列
    private val onItemListener: OnItemListener, // アイテムクリックのリスナー
    private val selectedDate: LocalDate // 選択された日付
) : RecyclerView.Adapter<CalenderViewHolder>() {

    // ViewHolderを作成するメソッド
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalenderViewHolder {
        // レイアウトのインフレートとViewHolderの作成
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.calender_cell, parent, false)
        val layoutParams = view.layoutParams
        layoutParams.height = (parent.height * 0.166666666).toInt()
        return CalenderViewHolder(view, onItemListener)
    }

    // ViewHolderにデータをバインドするメソッド
    override fun onBindViewHolder(holder: CalenderViewHolder, position: Int) {
        holder.dayOfMonth.text = daysOfMonth[position] // 日付を設定

        val dayOfMonth = daysOfMonth[position] // リスト内の日付を取得

        // 日付が空でない場合の処理
        if (dayOfMonth.isNotEmpty()) {
            val year = selectedDate.year
            val month = selectedDate.monthValue
            val cellDate = "$year-${String.format("%02d", month)}-$dayOfMonth" // 日付をフォーマット

            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            val selectedDateFormatted = selectedDate.format(formatter)

            val realm = Realm.getDefaultInstance()
            val diaryData = realm.where<MyModel>().equalTo("date", cellDate).findFirst() // 日記のデータを取得

            // 日記の有無に応じて背景色を変更
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

    // アイテム数を返すメソッド
    override fun getItemCount(): Int {
        return daysOfMonth.size
    }

    // アイテムクリックのリスナー
    interface OnItemListener {
        fun onItemClick(position: Int, dayText: String)
    }
}
