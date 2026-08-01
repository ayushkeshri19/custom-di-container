package com.ayush.dicontainer.di

import kotlin.reflect.KClass

/**
 * To signify whether the container class already has an object for the requested type or it has build it
 */

sealed interface Registration {
    data class Provider(val implementation: KClass<*>) : Registration
    data class Instance(val value: Any) : Registration
}