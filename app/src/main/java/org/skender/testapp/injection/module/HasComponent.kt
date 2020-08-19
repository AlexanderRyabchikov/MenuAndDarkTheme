package org.skender.testapp.injection.module

interface HasComponent<C> {
    fun getComponent(): C
}