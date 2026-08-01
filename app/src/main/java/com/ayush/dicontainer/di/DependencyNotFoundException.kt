package com.ayush.dicontainer.di

import kotlin.reflect.KClass

class DependencyNotFoundException(
    type: KClass<*>
): Exception(
    "No registration found for ${type.simpleName}. Did you forget to call register() or registerSingleton()?"
)