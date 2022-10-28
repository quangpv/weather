package com.quangpv.weather.shared.exception

class SearchQueryException :
    RuntimeException("Query to search should be blank or have at least 3 characters")