package com.wetour.we_t.ui.placeDetail

import android.animation.ObjectAnimator
import android.graphics.Rect
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.wetour.we_t.R
import com.wetour.we_t.SticyScrollView
import com.wetour.we_t.data.CommentData
import com.wetour.we_t.data.PlaceDetailData
import com.wetour.we_t.ui.home.tourContents.HashTagAdpater
import kotlinx.android.synthetic.main.activity_place_detail.*
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView


class PlaceDetailActivity : AppCompatActivity(), View.OnClickListener {

    var hashTagData = mutableListOf<String>()
    var detailData = mutableListOf<PlaceDetailData>()
    var commentData = mutableListOf<CommentData>()
    lateinit var hashTagAdpater: HashTagAdpater
    lateinit var placeDetailAdapter: PlaceDetailAdapter
    lateinit var commentAdpater: CommentAdpater

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_place_detail)

        // Comment 부분의 User Image, Name, Rating 최상위로 올리기
        act_place_detail_layout_commentUser.bringToFront()

        initial()
        setMap()
        setScroll()
        setRv()
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.act_place_detail_btn_close_placeInfo -> {
                act_place_detail_text_placeInfo.isSingleLine =
                    !act_place_detail_text_placeInfo.isSingleLine
            }

            R.id.act_place_detail_btn_scroll_basicInfo -> {
                act_place_detail_btn_scroll_basicInfo.isSelected = true
                act_place_detail_btn_scroll_detailInfo.isSelected = false
                act_place_detail_btn_scroll_placeInfo.isSelected = false
                act_place_detail_btn_scroll_comment.isSelected = false
                act_place_detail_scroll_main.scrollToView(act_place_detail_pin_basicInfo)
            }
            R.id.act_place_detail_btn_scroll_detailInfo -> {
                act_place_detail_btn_scroll_basicInfo.isSelected = false
                act_place_detail_btn_scroll_detailInfo.isSelected = true
                act_place_detail_btn_scroll_placeInfo.isSelected = false
                act_place_detail_btn_scroll_comment.isSelected = false
                act_place_detail_scroll_main.scrollToView(act_place_detail_pin_detailInfo)
            }
            R.id.act_place_detail_btn_scroll_placeInfo -> {
                act_place_detail_btn_scroll_basicInfo.isSelected = false
                act_place_detail_btn_scroll_detailInfo.isSelected = false
                act_place_detail_btn_scroll_placeInfo.isSelected = true
                act_place_detail_btn_scroll_comment.isSelected = false
                act_place_detail_scroll_main.scrollToView(act_place_detail_pin_placeInfo)
            }
            R.id.act_place_detail_btn_scroll_comment -> {
                act_place_detail_btn_scroll_basicInfo.isSelected = false
                act_place_detail_btn_scroll_detailInfo.isSelected = false
                act_place_detail_btn_scroll_placeInfo.isSelected = false
                act_place_detail_btn_scroll_comment.isSelected = true
                act_place_detail_scroll_main.scrollToView(act_place_detail_pin_comment)
            }
        }
    }

    private fun initial() {
        act_place_detail_btn_scroll_basicInfo.isSelected = true
        act_place_detail_btn_scroll_detailInfo.isSelected = false
        act_place_detail_btn_scroll_placeInfo.isSelected = false
        act_place_detail_btn_scroll_comment.isSelected = false
    }

    private fun setMap() {
        val mapView = MapView(this)
        val marker = MapPOIItem()
        val mapViewContainer = findViewById<View>(R.id.act_place_detail_map) as ViewGroup

        mapViewContainer.addView(mapView)

        mapView.setMapCenterPoint(
            MapPoint.mapPointWithGeoCoord(
                33.308916574785165, 126.63376954043169
            ), false
        )

        marker.itemName = "Default Marker"
        marker.tag = 0
        marker.mapPoint = MapPoint.mapPointWithGeoCoord(33.308916574785165, 126.63376954043169)
        marker.markerType = MapPOIItem.MarkerType.BluePin // 기본으로 제공하는 BluePin 마커 모양.

//        marker.selectedMarkerType =
//            MapPOIItem.MarkerType.RedPin // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.

        mapView.addPOIItem(marker)
    }

    private fun setScroll() {
        act_place_detail_scroll_main.run {
            header = act_place_detail_scroll_sticky
            stickListener = { _ ->
                Log.d("LOGGER_TAG", "stickListener")
            }
            freeListener = { _ ->
                Log.d("LOGGER_TAG", "freeListener")
            }
        }
    }

    private fun setRv() {
        hashTagAdpater = HashTagAdpater(this)
        placeDetailAdapter = PlaceDetailAdapter(this)
        commentAdpater = CommentAdpater(this)
        act_place_detail_recyclerview_hashTag.adapter = hashTagAdpater
        act_place_detail_recyclerview_hashTag.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        act_place_detail_recyclerview_detailInfo.adapter = placeDetailAdapter
        act_place_detail_recyclerview_detailInfo.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        act_place_detail_recyclerview_comment.adapter = commentAdpater
        act_place_detail_recyclerview_comment.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        setData()
    }

    private fun setData() {
        hashTagData = arrayListOf("#숲", "#해시태그")
        hashTagAdpater.datas = hashTagData as ArrayList<String>
        hashTagAdpater.notifyDataSetChanged()

        detailData.apply {
            add(
                PlaceDetailData(
                    "문의 및 안내",
                    "064-732-2114"
                )
            )
            add(
                PlaceDetailData(
                    "개장일",
                    "2007년 05월 05일"
                )
            )
            add(
                PlaceDetailData(
                    "쉬는날",
                    "연중무휴"
                )
            )
            add(
                PlaceDetailData(
                    "체험안내",
                    "[감귤체험]\n" +
                            "제주감귤을 따는 방법도 배우고 직접 따서 먹으며 1kg 정도 가져갈 수 있다.\n" +
                            "\n" +
                            "[흑돼지&거위야놀자 공연 및 먹이주기체험]\n" +
                            "귀여운 흑돼지, 거위들이 미끄럼틀 타고 내려오는 재롱잔치와 직접 먹이주기체험 할 수 있다.\n" +
                            "※ 체험시간 오전10시 ~ 오후 5시까지 매시간 정각\n" +
                            "\n" +
                            "[동물먹이주기체험]\n" +
                            "우리에게 친근한 토끼, 아기 흑 돼지, 소, 닭 등 동물들을 가까이에서 직접 만나 교감하고 먹이주기 체험 할 수 있다.\n" +
                            "\n" +
                            "[곤충테마체험]\n" +
                            "제주에 자생하는 곤충과 애벌레 세계의 다양한 곤충 장수풍뎅이, 호랑나비, 거미, 사슴벌레, 무당벌레 등을 직접 관찰하고 학습할 수 있다.\n" +
                            "\n" +
                            "[화산송이맨발체험]\n" +
                            "휴애리 공원 전체 철분과 미네랄 성분이 많은 것으로 알려진 화산 송이로 이루어져 있다. 화산송이 위를 맨발로 걸으면서 자연스럽게 발 지압을 하며 건강도 챙기고 발의 피로도 풀 수 있다.\n" +
                            "\n" +
                            "[전통놀이 체험과 제주의 삶 경험하기]\n" +
                            "굴렁쇠 굴리기, 투호던지기, 돌 탑 쌓기 체험을 할 수 있다. 물허벅, 돗통시, 제주의 옛 초가집 등을 보고 제주의 옛 문화를 느낄 수 있다.\n" +
                            "\n" +
                            "[야생화 올레길 체험]\n" +
                            "계절마다 피는 아름다운 야생화와 함께 제주 올레길을 천천히 걸으며 여유로움을 만끽하며 제주의 자연을 느낄 수 있다.\n" +
                            "\n" +
                            "[개울물 맨발 체험]\n" +
                            "무더운 여름 시원한 화산 암반수에서 더위를 식힐 수 있습니다. (계절한정)"
                )
            )
            add(
                PlaceDetailData(
                    "체험가능연령",
                    "제한 없음"
                )
            )
            add(
                PlaceDetailData(
                    "수용인원",
                    "2,000명"
                )
            )
            add(
                PlaceDetailData(
                    "이용시간",
                    "하절기 (4월~10월) : 09:00~18:00 (입장마감 17:30분)\n" +
                            "동절기 (11월~3월) : 09:00~18:00 (입장마감 16:30분)\n" +
                            "※ 단, 감귤체험 이용시 17:00까지 입장해야 체험가능"
                )
            )
            add(
                PlaceDetailData(
                    "주차시설",
                    "주차 가능(버스 30대, 개인 200대)"
                )
            )
            add(
                PlaceDetailData(
                    "유모차 대여 여부",
                    "없음"
                )
            )
            add(
                PlaceDetailData(
                    "애완동물 동반 가능 여부",
                    "불가"
                )
            )
            add(
                PlaceDetailData(
                    "신용카드 가능 여부",
                    "가능"
                )
            )
        }
        placeDetailAdapter.datas = detailData
        placeDetailAdapter.notifyDataSetChanged()


        commentData.apply {
            add(
                CommentData(
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTm6ttaBB_hlOVfzpyY4HSSmBmtSJQg1X0P8Q&usqp=CAU",
                    "수지",
                    "2021.07.29",
                    5,
                    "동료들이랑 사진 찍으려고 방문했어요:)\n" +
                            "인스타에서 봤던 것 보다 훨씬 예쁘고 좋아요 사진명소!!"
                )
            )
            add(
                CommentData(
                    "data:image/jpeg;base64,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",
                    "여행객",
                    "2021.07.29",
                    2,
                    "다 좋은데...\n" +
                            "사람이 너무 많아요..."
                )
            )
            add(
                CommentData(
                    "data:image/jpeg;base64,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",
                    "우치치",
                    "2021.07.29",
                    4,
                    "엄마랑 언니랑 여행왔는데 너무 좋아요~\n" +
                            "다음에는 우치랑도 같이 와야겠어요^^"
                )
            )
        }

        commentAdpater.datas = commentData
        commentAdpater.notifyDataSetChanged()
    }

    private fun SticyScrollView.computeDistanceToView(view: View): Int {
        return kotlin.math.abs(
            calculateRectOnScreen(this).top - (this.scrollY + calculateRectOnScreen(view).top)
        )
    }

    private fun calculateRectOnScreen(view: View): Rect {
        val location = IntArray(2)
        view.getLocationOnScreen(location)
        return Rect(
            location[0],
            location[1],
            location[0] + view.measuredWidth,
            location[1] + view.measuredHeight
        )
    }

    private fun SticyScrollView.scrollToView(view: View) {
        val y = computeDistanceToView(view)
        ObjectAnimator.ofInt(this, "scrollY", y - act_place_detail_scroll_sticky.height).apply {
            duration = 1000L // 스크롤이 지속되는 시간을 설정한다. (1000 밀리초 == 1초)
        }.start()
    }
}