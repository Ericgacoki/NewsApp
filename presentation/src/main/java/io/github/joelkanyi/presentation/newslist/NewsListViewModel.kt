package io.github.joelkanyi.presentation.newslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.joelkanyi.domain.usecase.GetNewsUseCase
import io.github.joelkanyi.presentation.utils.toISO3166Alpha2
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class NewsListViewModel
    @Inject
    constructor(
        private val getNewsUseCase: GetNewsUseCase,
    ) : ViewModel() {
        private val _uiState = MutableStateFlow(NewsListUiState())
        val uiState = _uiState.asStateFlow()

        fun getNews(
            country: String? = null,
            category: String? = null,
        ) {
            _uiState.update {
                it.copy(
                    news =
                        getNewsUseCase(
                            country = country?.toISO3166Alpha2(),
                            category = category?.lowercase(),
                        ).cachedIn(viewModelScope),
                )
            }
        }

        fun setFiltersBottomSheetState(value: Boolean) {
            _uiState.update {
                it.copy(
                    showNewsFilters = value,
                )
            }
        }

        fun setCountriesDialogState(value: Boolean) {
            _uiState.update {
                it.copy(
                    showCountryDialog = value,
                )
            }
        }

        fun selectCountry(country: String) {
            _uiState.update {
                it.copy(
                    selectedCountry = country,
                )
            }
        }

        fun selectCategory(category: String) {
            _uiState.update {
                it.copy(
                    selectedCategory = category,
                )
            }
        }

        init {
            getNews(
                country = uiState.value.selectedCountry,
                category = uiState.value.selectedCategory,
            )
        }
    }
