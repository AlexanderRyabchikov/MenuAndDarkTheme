package org.skender.testapp.domain.mapper

import java.util.ArrayList

abstract class AbstractMapper<Q, V> {
    abstract fun map(value: Q): V

    fun map(values: List<Q>?): List<V>? {
        if (values == null) {
            return null
        }

        val returnValues = ArrayList<V>(values.size)
        for (value in values) {
            returnValues.add(map(value))
        }
        return returnValues
    }
}