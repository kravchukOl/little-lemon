package com.oleksiikravchuk.littlelemon.screens

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.util.copy
import com.oleksiikravchuk.littlelemon.ItemDao
import com.oleksiikravchuk.littlelemon.MenuItem
import com.oleksiikravchuk.littlelemon.MenuResponse
import com.oleksiikravchuk.littlelemon.components.CategoryState
import com.oleksiikravchuk.littlelemon.utils.DataEntry
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomescreenViewModel(
    private val ktorClient: HttpClient,
    private val itemDao: ItemDao,
    private val hasNetworkAccess: Boolean
) : ViewModel() {

    private val link = DataEntry.link
    var listOfMenuItemState = mutableStateOf(listOf<MenuItem>())
        private set

    var categoryListState = mutableStateListOf<CategoryState>()
        private set

    var isEmpty = mutableStateOf(false)
        private set

    var searchPhrase = mutableStateOf("")

    init {
        categoriesInit()
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

    private fun getRecipesFromCache() {
        viewModelScope.launch {
            listOfMenuItemState.value = itemDao.getAllItems()
        }
    }

    private suspend fun fetchRecipes(): List<MenuItem> {
        val response: MenuResponse = ktorClient.get(link).body()
        return if (response.menuItem.isNotEmpty()) response.menuItem.map {
            MenuItem(
                it.id, it.title, it.description, it.price, it.image, it.category
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

    private fun categoriesInit() {
        categoryListState.addAll(DataEntry.categories.map { CategoryState(it) })
    }


    fun updateSearchPhrase(search: String) {
        searchPhrase.value = search
    }

    fun filterByCategory(category: String) {
        if (!isEmpty.value) {
            viewModelScope.launch {
                listOfMenuItemState.value = itemDao.getAllItems()
                categoryListState.forEach { it.isActive = false }
                categoryListState.find { it.categoryName.contains(category, true) }?.isActive = true

                listOfMenuItemState.value = listOfMenuItemState.value.filter {
                    it.category.contains(
                        category, ignoreCase = true
                    )
                }
            }
        }
    }

    fun searchByString(searchPhrase : String) {

    }

}