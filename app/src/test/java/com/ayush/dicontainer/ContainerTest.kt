package com.ayush.dicontainer

import com.ayush.dicontainer.di.Container
import com.ayush.dicontainer.di.DependencyNotFoundException
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotSame
import org.junit.Assert.assertSame
import org.junit.Assert.assertThrows
import org.junit.Test

private interface Engine {
    fun start(): String
}

private class ElectricEngine : Engine {
    override fun start() = "silent hum"
}

private class Car(val engine: Engine)

class ContainerTest {

    @Test
    fun resolvesRegisteredImplementationWithDependencies() {
        val container = Container()
        container.register<Engine, ElectricEngine>()
        container.register<Car, Car>()

        val car = container.resolve<Car>()

        assertEquals("silent hum", car.engine.start())
    }

    @Test
    fun registerSingletonReturnsTheExactSameInstance() {
        val container = Container()
        val instance = ElectricEngine()
        container.registerSingleton<Engine>(instance)

        val first = container.resolve<Engine>()
        val second = container.resolve<Engine>()

        assertSame(first, second)
    }

    @Test
    fun registerWithoutSingletonBuildsAFreshInstanceEachTime() {
        val container = Container()
        container.register<Engine, ElectricEngine>()

        val first = container.resolve<Engine>()
        val second = container.resolve<Engine>()

        assertNotSame(first, second)
    }

    @Test
    fun resolvingAnUnregisteredTypeThrows() {
        val container = Container()

        assertThrows(DependencyNotFoundException::class.java) {
            container.resolve<Car>()
        }
    }
}
