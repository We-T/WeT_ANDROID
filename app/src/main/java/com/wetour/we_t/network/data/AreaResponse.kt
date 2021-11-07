package com.wetour.we_t.network.data

data class AreaResponse(
    val type: String,
    val p_good_list: ArrayList<P_Good_List>,
    val hot_place: ArrayList<P_Good_List>,
    val my_good_list: ArrayList<PGood>,
    val trip_record: ArrayList<AreaTripRecord>?
)

data class P_Good_List(
    val title: String,
    val firstimage: String,
    val tag_list: ArrayList<String>
)

data class AreaTripRecord(
    val trip_name: String,
    val dDay: String,
    val day: String
)

//data class PGood(
//    val title: String,
//    val firstimage: String
//)

/*
* {"p_good_list":[
		{
		 "title": "안면도자연휴양림",
		 "firstimage": "http:\/\/tong.visitkorea.or.kr\/cms\/resource\/70\/2031770_image2_1.jpg",
		 "tag_list" : ["자연", "산"]
		},
		{
		 "title": "경기도자박물관",
		 "firstimage": "http:\/\/tong.visitkorea.or.kr\/cms\/resource\/08\/919408_image2_1.jpg",
		 "tag_list" : ["문화", "체험"]
		}
 ],
 "hot_place":[
		{
		 "title": "제주오성갈치조림",
		 "firstimage": "http://tong.visitkorea.or.kr/cms/resource/29/2755629_image2_1.jpg",
		 "tag_list" : ["음식점", "산"]
		},
		{
		 "title": "중문통갈치조림구이 색달식당",
		 "firstimage": "http://tong.visitkorea.or.kr/cms/resource/23/2755623_image2_1.jpg",
		 "tag_list" : ["자연", "산"]
		}
 ],
 "my_good_list":[
		{
		 "title": "제주오성갈치조림",
		 "firstimage": "http://tong.visitkorea.or.kr/cms/resource/29/2755629_image2_1.jpg",
		},
		{
		 "title": "중문통갈치조림구이 색달식당",
		 "firstimage": "http://tong.visitkorea.or.kr/cms/resource/23/2755623_image2_1.jpg",
		}
 ],
 "trip_record":{
		 "trip_name":"사랑하는 엄빠의 제주여행",
		 "start_day":"20210802",
		 "end_day":"20210805",
		}
}
* */