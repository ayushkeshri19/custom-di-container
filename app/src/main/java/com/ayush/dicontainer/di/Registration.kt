package com.ayush.dicontainer.di

import kotlin.reflect.KClass

sealed interface Registration {
    data class Provider(val implementation: KClass<*>) : Registration
    data class Instance(val value: Any) : Registration
}