package com.oleksiikravchuk.littlelemon

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MenuResponse (@SerialName("menu") val menuItem : List<MenuItemNetwork>)

@Serializable
data class MenuItemNetwork (
    val id  : Int,
    val title : String,
    val description : String,
    val price : String,
    val image : String,
    val category: String
)