package com.github.nicholashauschild.cfg

import java.util.*
import kotlin.collections.ArrayList

/**
 * Author: nicholas.hauschild
 */

fun cftemplate(populate : CfTemplate.() -> Unit) : String {
    val template = CfTemplate()
    template.populate()
    return generateYml(template)
}

class CfTemplate {
    // public variables exposed as configuration in template
    var description : String = ""

    // private values populated via functions
    internal val resources : MutableList<Resource> = ArrayList()

    fun resources(gatherResources : ResourceGatherer.() -> Unit) {
        val resourceGatherer = ResourceGatherer()
        resourceGatherer.gatherResources()
        resources.addAll(resourceGatherer.resources)
    }
}

class ResourceGatherer(val resources : MutableList<Resource> = ArrayList()) {
    fun add(resource : Resource) = resources.add(resource)

    operator fun Resource.unaryPlus() = add(this)

    fun props(vararg pairs : Pair<String, Any>) = linkedMapOf(*pairs)

    operator fun String.invoke(name : String = "", evaluateDefinition : ResourceDefinition.() -> Unit) : Resource {
        val resourceDefinition = ResourceDefinition()
        resourceDefinition.evaluateDefinition()

        // figure out name
        val n : String = when {
            !name.isBlank()                     -> name
            !resourceDefinition.name.isBlank()  -> resourceDefinition.name
            else                                -> this.split(':')[4] + rand()
        }

        return Resource(name = n, type = this, props = resourceDefinition.properties)
    }

    private fun rand() = UUID.randomUUID().toString().filter { it.isLetterOrDigit() }
}

class ResourceDefinition(val properties : MutableMap<String, Any> = LinkedHashMap()) {
    var name : String = ""

    operator fun String.minus(value : String) {
        properties[this] = value
    }

    infix fun String.eq(value : String) = minus(value)

    operator fun String.minus(value : Boolean) {
        properties[this] = value
    }

    infix fun String.eq(value : Boolean) = minus(value)

    operator fun String.minus(value : Int) {
        properties[this] = value
    }

    infix fun String.eq(value : Int) = minus(value)

    operator fun String.minus(gatherProperties : PropertyGatherer.() -> Unit) {
        val propertyGatherer = PropertyGatherer()
        propertyGatherer.gatherProperties()
        propertyGatherer.validate(this)

        if(propertyGatherer.properties.isNotEmpty()) {
            properties[this] = propertyGatherer.properties
        } else {
            properties[this] = propertyGatherer.list
        }
    }

    infix fun String.eq(gatherProperties : PropertyGatherer.() -> Unit) = minus(gatherProperties)
}

class PropertyGatherer(val properties : MutableMap<String, Any> = LinkedHashMap(),
                       val list: MutableList<Any> = ArrayList()) {
    //******************
    // map operations
    //******************
    operator fun String.minus(value : String) {
        properties[this] = value
    }

    infix fun String.eq(value : String) = minus(value)

    operator fun String.minus(value : Boolean) {
        properties[this] = value
    }

    infix fun String.eq(value : Boolean) = minus(value)

    operator fun String.minus(value : Int) {
        properties[this] = value
    }

    infix fun String.eq(value : Int) = minus(value)

    operator fun String.minus(gatherProperties : PropertyGatherer.() -> Unit) {
        val propertyGatherer = PropertyGatherer()
        propertyGatherer.gatherProperties()
        propertyGatherer.validate(this)

        if(propertyGatherer.properties.isNotEmpty()) {
            properties[this] = propertyGatherer.properties
        } else {
            properties[this] = propertyGatherer.list
        }
    }

    infix fun String.eq(gatherProperties : PropertyGatherer.() -> Unit) = minus(gatherProperties)

    //******************
    // list operations
    //******************
    operator fun String.unaryMinus() {
        list.add(this)
    }

    operator fun __CfgNum.unaryMinus() {
        list.add(this.num)
    }

    operator fun Boolean.unaryMinus() {
        list.add(this)
    }

    operator fun __CfgProps.unaryMinus() {
        if(this.propertyGatherer.properties.isNotEmpty()) {
            list.add(this.propertyGatherer.properties)
        } else {
            list.add(this.propertyGatherer.list)
        }
    }

    fun props(gatherProperties : PropertyGatherer.() -> Unit) : __CfgProps {
        val propertyGatherer = PropertyGatherer()
        propertyGatherer.gatherProperties()
        propertyGatherer.validate("List Item")
        return __CfgProps(propertyGatherer)
    }

    internal fun validate(key : String) {
        if(list.isNotEmpty() && properties.isNotEmpty()) {
            throw IllegalArgumentException("Cannot mix map and list constructs under a single property: $key")
        }

        if(list.isEmpty() && properties.isEmpty()) {
            throw IllegalArgumentException("You must provide some parameters: $key")
        }
    }

    fun number(number : Int) = __CfgNum(number)
}

class __CfgNum(val num : Int)
class __CfgProps(val propertyGatherer : PropertyGatherer)

class Resource(
        val name : String,
        val type : String,
        val props : Map<String, Any>)

// test it out

fun main(args : Array<String>) {

    val template =


        cftemplate {
            description = "A new template"

            resources {
                +ec2_dhcpOptions("dhcpOptions") {
                    "DomainName" - "the_domain"
                    "DomainExists" - true
                    "Count" - 3
                    "a_map" - {
                        "tr:application-asset-insight-id" - "203981"
                        "tr:financial-identifier" - "64?"
                    }
                    "a_list" - {
                        - "something"
                        - number(5)
                        - true
                        - props {
                            "key" - "hey"
                            "value" - "hi"
                        }
                    }
                    "another" - {
                        - props {
                            "hello" - {
                                "another" - {
                                    "level" - "deep"
                                }
                            }
                        }
                    }
                }

                + ec2_dhcpOptions {
                    name = "dog"
                    "DomainName" - "the_domain"
                }

                + ec2_dhcpOptions ("what") {
                    name = "dog"
                    "DomainName" - "the_domain"
                }

                + ec2_dhcpOptions {
                    "DomainName" - "the_domain"
                }
            }
        }


    System.out.println(template)
}