package com.github.nicholashauschild.cfg

/**
 * Author: nicholas.hauschild
 */

fun generateYml(template : CfTemplate) : String {
    val builder = StringBuilder()

    // process header
    builder.appendln("---")
    builder.appendln("AWSTemplateFormatVersion: 2010-09-09")
    builder.appendln("Description: ${template.description}")

    // process parameters

    // process resources
    builder.appendln("Resources:")
    template.resources.forEach {
        builder.appendln(indent(1, "${it.name}:"))
        builder.appendln(indent(2, "Type: ${it.type}"))
        builder.appendln(indent(2, "Properties:"))
        processProperties(3, it.props, builder)
    }
    return builder.toString()
}

private fun processProperties(indentLevel : Int, props : Map<String, Any>, builder : StringBuilder) {
    props.forEach {
        when(it.value) {
            is Map<*,*> -> {
                builder.appendln(indent(indentLevel, "${it.key}:"))
                processProperties(indentLevel + 1, it.value as Map<String, Any>, builder)
            }
            is List<*> -> {
                builder.appendln(indent(indentLevel, "${it.key}:"))
                processList(indentLevel + 1, it.value as List<Any>, builder)
            }
            is Int, is Boolean, is String -> {
                builder.appendln(indent(indentLevel, "${it.key}: ${it.value}"))
            }
            else -> {
                throw IllegalArgumentException("Unsupported type: ${it.value.javaClass}")
            }
        }
    }
}

private fun processList(indentLevel : Int, props : List<Any>, builder : StringBuilder) {
    props.forEach {
        when(it) {
            is Map<*,*> -> {
                builder.appendln(indent(indentLevel, "-"))
                processProperties(indentLevel + 1, it as Map<String, Any>, builder)
            }
            is List<*> -> {
                builder.appendln(indent(indentLevel, "-"))
                processList(indentLevel + 1, it as List<Any>, builder)
            }
            is Int, is Boolean, is String -> {
                builder.appendln(indent(indentLevel, "- $it"))
            }
            else -> {
                throw IllegalArgumentException("Unsupported type: ${it.javaClass}")
            }
        }
    }
}

private fun indent(indentLevel : Int, value : String) : String {
    val str = StringBuilder()
    for(i in 1..indentLevel) {
        str.append("  ")
    }
    str.append(value)
    return str.toString()
}