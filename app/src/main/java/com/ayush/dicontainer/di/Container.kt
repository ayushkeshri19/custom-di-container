package com.ayush.dicontainer.di

import kotlin.reflect.KClass

class Container {

    @PublishedApi
    internal val storage = mutableMapOf<KClass<*>, Registration>()

    inline fun <reified Interface : Any, reified Impl : Interface> register() {
        storage[Interface::class] = Registration.Provider(Impl::class)
    }

    inline fun <reified T : Any> registerSingleton(instance: T) {
        storage[T::class] = Registration.Instance(instance)
    }

    inline fun <reified T : Any> resolve(): T {
        return resolveInternal(T::class) as T
    }

    @PublishedApi
    internal fun resolveInternal(type: KClass<*>): Any {
        val registration = storage[type] ?: throw DependencyNotFoundException(type)

        return when (registration) {
            is Registration.Instance -> registration.value
            is Registration.Provider -> {
                // Get the first constructor
                val constructor = registration.implementation.java.constructors.first()
                // Get its parameter types
                val paramTypes = constructor.parameterTypes

                // For each type, do the same
                val args = paramTypes.map { resolveInternal(it.kotlin) }

                // "*" is a spread operator that lets us pass arrays wherever varargs are expected
                constructor.newInstance(*args.toTypedArray())
            }
        }
    }

}