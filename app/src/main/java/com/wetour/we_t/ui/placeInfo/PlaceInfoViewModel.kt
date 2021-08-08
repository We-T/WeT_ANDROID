package com.wetour.we_t.ui.placeInfo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wetour.we_t.data.PlaceInfoData

class PlaceInfoViewModel: ViewModel() {

    val data = mutableListOf<PlaceInfoData>()
    private val _placeLiveData = MutableLiveData<List<PlaceInfoData>>()
    val itemList: LiveData<List<PlaceInfoData>> = _placeLiveData

    init {
        _placeLiveData.value = data
    }

    fun getPlaceData(): LiveData<List<PlaceInfoData>> {
        return itemList
    }
}