package com.alertnews.app

enum class ImpactFilter {
    ALL, HIGH_ONLY, CRITICAL_ONLY
}

class FilterManager(
    var impactFilter: ImpactFilter = ImpactFilter.ALL,
    var selectedSymbols: List<String> = emptyList(),
    var selectedCurrencies: List<String> = emptyList(),
    var showFavoritesOnly: Boolean = false
) {
    companion object {
        @Volatile
        private var instance: FilterManager? = null

        fun getInstance(): FilterManager {
            if (instance == null) {
                synchronized(this) {
                    if (instance == null) {
                        instance = FilterManager()
                    }
                }
            }
            return instance!!
        }
    }

    fun reset() {
        impactFilter = ImpactFilter.ALL
        selectedSymbols = emptyList()
        selectedCurrencies = emptyList()
        showFavoritesOnly = false
    }
}