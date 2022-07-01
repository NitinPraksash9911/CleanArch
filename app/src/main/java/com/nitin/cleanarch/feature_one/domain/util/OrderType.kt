package com.nitin.cleanarch.feature_one.domain.util

sealed class OrderType {
    object Ascending : OrderType()
    object Descending : OrderType()
}