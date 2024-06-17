package com.example

import kotlin.test.Test

class Test {

    sealed interface TestLayer {
        data class ParametrizedLayer(val param: Int) : TestLayer
        data object DefaultLayer : TestLayer
    }
    val dummyFactory: () -> TestLayer.ParametrizedLayer = { TestLayer.ParametrizedLayer(5) }

    @Test
    fun K2_fail() {
        layerConfig {
            subConfig(
                layerConfigFactory = dummyFactory,
                params = { paramTypeConfig("param", INT) { it.param } }
            )
        }
    }

}