package com.lavsii.tyl.data

import com.lavsii.tyl.R
import java.io.Serializable

data class Countries(
    val name: String = "",
    val shortName: String = "",
    val src: Int?,
    var link: String?,
    val action: Boolean = false,
    ): Serializable

fun getCountries() = listOf(
    Countries("Узбекистан", "UZ",
        R.drawable.uz_flag, "https://llcrdct.net/go/s39o0hp42l", true),
    Countries("США", "US",R.drawable.us_flag, null, false ),
    Countries("Турция", "TR",R.drawable.tr_flag, null, false),
    Countries("Германия", "DE",R.drawable.de_flag, null, false),
    Countries("Сербия", "SR",R.drawable.rs_flag, null, false),

)