package com.example


fun <LAYER : Any> layerConfig(
    config: LayerConfigurer<LAYER>.() -> Unit,
): LayerResult<LAYER> {
    return object : LayerResult<LAYER> {}
}

interface LayerResult<LAYER : Any>

interface LayerConfigurer<LAYER : Any>

inline fun <LAYER : Any, reified EXACT_LAYER : LAYER> LayerConfigurer<LAYER>.subConfig(
    layerConfigFactory: LayerConfigFactory<EXACT_LAYER>,
    params: ParamsConfigLambda<EXACT_LAYER>,
) {}

fun interface LayerConfigFactory<EXACT_LAYER> {
    fun invoke(): EXACT_LAYER
}

fun interface ParamsConfigLambda<EXACT_LAYER : Any> {
    fun ParamTypeConfigurer<EXACT_LAYER>.configureParams()
}

interface ParamTypeConfigurer<EXACT_LAYER : Any> {
    fun <T : Any> paramTypeConfig(paramName: String, serde: ParamSerde<T>, resolver: ParamResolver<EXACT_LAYER, T>)
}

fun interface ParamResolver<EXACT_LAYER, PARAM> {
    fun resolve(LAYER: EXACT_LAYER): PARAM
}

interface ParamSerde<T : Any>

object INT : ParamSerde<Int>