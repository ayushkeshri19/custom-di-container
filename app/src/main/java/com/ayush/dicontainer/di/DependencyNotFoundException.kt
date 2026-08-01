package com.ayush.dicontainer.di

import kotlin.reflect.KClass

/**
 * Thrown when there's no registered type for a given class
 */

class DependencyNotFoundException(
    type: KClass<*>
): Exception(
    "No registration found for ${type.simpleName}. Did you forget to call register() or registerSingleton()?"
)