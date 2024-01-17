package com.oleksiikravchuk.littlelemon.screens

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oleksiikravchuk.littlelemon.ItemDao
import com.oleksiikravchuk.littlelemon.MenuItem
import com.oleksiikravchuk.littlelemon.MenuResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.launch

class HomescreenViewModel(
    private val ktorClient: HttpClient,
    private val itemDao: ItemDao,
    private val hasNetworkAccess: Boolean
) : ViewModel() {

    private val link =
        "https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json"
    var listOfMenuItemState = mutableStateOf(listOf<MenuItem>())
        private set

    var isEmpty = mutableStateOf(false)
        private set

    init {
        processRecipes()
    }

    private fun processRecipes() {
        viewModelScope.launch {
            isEmpty.value = itemDao.getItemsCount() == 0

            if (hasNetworkAccess) {
                listOfMenuItemState.value = fetchRecipes()
                saveToDatabase(listOfMenuItemState.value)
            } else if (!isEmpty.value) {
                listOfMenuItemState.value = itemDao.getAllItems()
            }
        }
    }

    private fun getRecipesFromCache(): List<MenuItem> {
        var recipeList = listOf<MenuItem>()
        viewModelScope.launch{
            recipeList = itemDao.getAllItems()
        }
        return recipeList
    }

    private suspend fun fetchRecipes(): List<MenuItem> {
        val response: MenuResponse = ktorClient.get(link).body()
        return if (response.menuItem.isNotEmpty())
            response.menuItem.map {
                MenuItem(
                    it.id,
                    it.title,
                    it.description,
                    it.price,
                    it.image,
                    it.category
                )
            } else {
            listOf<MenuItem>()
        }
    }

    private fun saveToDatabase(menu: List<MenuItem>) {
        viewModelScope.launch {
            menu.forEach {
                itemDao.insert(it)
            }
        }
    }

}