package com.github.nicholashauschild.cfg

/**
 * Author: nicholas.hauschild
 */


fun main(args : Array<String>) {
    // change function to try out different examples
    System.out.println(addResourceWithList())
}

/**
 * This function creates a basic template that shows the following:
 * 1. Creating a resource via constructor
 * 2. Adding the resource to the ResourceGatherer via the 'add' function
 */
fun addResourceByFunction() : String {
    val mergedTemplate =

            cftemplate {
                description = "my first template"

                resources {
                    // 1
                    val resource = Resource(name = "myresource1",
                            type = "AWS::EC2::Instance",
                            props = linkedMapOf("mykey1" to "myvalue1"))

                    // 2
                    add(resource)
                }
            }

    return mergedTemplate
}

/**
 * This function creates a basic template that shows the following:
 * 1. Creating a resource via constructor
 * 2. Note the different properties value types in use
 * 3. Adding the resource to the ResourceGatherer via the 'add' function
 */
fun addResourceWithOtherValueTypesInProperties() : String {
    val mergedTemplate =

            cftemplate {
                resources {
                    // 1
                    val resource = Resource(name = "myresource2",
                            type = "AWS::EC2::Instance",
                            // 2
                            props = linkedMapOf(
                                    "mykey1" to "myvalue1",
                                    "mykey2" to 31,
                                    "mykey3" to true,
                                    "mykey4" to linkedMapOf(
                                            "mysubkey1" to "mysubvalue1"
                                    )
                            )
                    )

                    // 3
                    add(resource)
                }
            }

    return mergedTemplate
}

/**
 * This function creates a basic template that shows the following:
 * 1. Creating a resource via constructor
 * 2. Note the use of the 'props' function rather than the 'linkedMapOf' function.
 * 3. Adding the resource to the ResourceGatherer via the 'add' function
 */
fun addPropertiesViaPropsFunction() : String {
    val mergedTemplate =

            cftemplate {
                resources {
                    // 1
                    val resource = Resource(name = "myresource3",
                            type = "AWS::EC2::Instance",
                            // 2
                            props = props(
                                    "mykey1" to "myvalue1",
                                    "mykey2" to 33,
                                    "mykey3" to false,
                                    "mykey4" to props(
                                            "mysubkey1" to "mysubvalue1"
                                    )
                            )
                    )

                    // 3
                    add(resource)
                }
            }

    return mergedTemplate
}

/**
 * This function creates a basic template that shows the following:
 * 1. Creating a resource via constructor
 * 2. Note the use of the 'props' function rather than the 'linkedMapOf' function.
 * 3. Adding it to the ResourceGatherer via the unaryPlus (+) operator
 */
fun addResourceByOperator() : String {
    val mergedTemplate =

            cftemplate {
                resources {
                    // 1
                    val resource = Resource(name = "myresource4",
                            type = "AWS::EC2::Instance",
                            // 2
                            props = props("mykey1" to "myvalue1"))

                    // 3
                    +resource
                }
            }

    return mergedTemplate
}

/**
 * This function creates a basic template that shows the following:
 * 1. Creating a resource via constructor
 * 2. Note the inclusion of a list type in props
 * 3. Note the use of the 'props' function rather than the 'linkedMapOf' function.
 * 4. Adding it to the ResourceGatherer via the unaryPlus (+) operator
 */
fun addResourceWithList() : String {
    val mergedTemplate =

            cftemplate {
                resources {
                    // 1
                    val resource = Resource(name = "myresource4",
                            type = "AWS::EC2::Instance",
                            // 2, 3
                            props = props("mykey1" to arrayListOf(
                                    props("list1key1" to "val1",
                                            "list1key2" to "val2",
                                            "list1key3" to "val3"
                                    ),
                                    props("list2key1" to "val4",
                                            "list2key2" to "val5",
                                            "list2key3" to "val6",
                                            "list2key4" to "val7"
                                    ),
                                    props("list3key1" to "val8")
                            )))

                    // 4
                    +resource
                }
            }

    return mergedTemplate
}