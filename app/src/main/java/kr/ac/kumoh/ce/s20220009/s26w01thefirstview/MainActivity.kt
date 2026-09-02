package kr.ac.kumoh.ce.s20220009.s26w01thefirstview

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kr.ac.kumoh.ce.s20220009.s26w01thefirstview.databinding.ActivityMainBinding

//import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // viewBinding 객체 선언
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // viewBinding 초기화 후 화면에 연결
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // "전송" 버튼 클릭 이벤트
        binding.buttonSend.setOnClickListener {
            sendMessage()
        }
    }

    private fun sendMessage() {
        // 입력창의 글자 읽기 (XML의 snake_case id -> Kotlin의 camelCase 프로퍼티)
        val message = binding.editMessage.text.toString().trim()

        // 빈 메시지는 보내지 않음
        if (message.isEmpty()) return

        // 말풍선(TextView)을 만들어 메시지 영역에 추가
        addMessageBubble(message)

        // 입력창 비우기
        binding.editMessage.setText("")

        // 새 메시지가 보이도록 맨 아래로 스크롤
        binding.scrollMessages.post {
            binding.scrollMessages.fullScroll(View.FOCUS_DOWN)
        }
    }

    private fun addMessageBubble(message: String) {
        // 말풍선 역할을 할 TextView 생성
        val bubble = TextView(this).apply {
            text = message
            textSize = 16f
            setTextColor(0xFF000000.toInt())
            setPadding(24, 16, 24, 16)
            setBackgroundResource(R.drawable.bg_chat_bubble)
        }

        // 오른쪽 정렬 + 위아래 여백 설정
        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        ).apply {
            gravity = Gravity.END
            topMargin = 8
            bottomMargin = 8
        }
        bubble.layoutParams = params

        // 메시지 컨테이너(LinearLayout)에 말풍선 추가
        binding.layoutMessages.addView(bubble)
    }
}