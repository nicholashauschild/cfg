# Kotlin CFG
A kotlin CloudFormation generator using a custom Kotlin DSL.

This project is currently under (occasional) development.  Take a look at the Future Plans
section for potentially upcoming features. 

### Features
1. Simple Kotlin DSL that is compiled and can be used within Kotlin code.
2. Easy to learn short-hand syntax for creating CF resources.
3. Use Kotlin control structures within the DSL to assist with generating the cloudformation
that you need.

### Future Plans

###### missing functionality
- Add support for additional properties for resources
  - DependsOn
  - CreationPolicy
  - UpdatePolicy
  - DeletionPolicy
  - Metadata
- Add support for Parameters, Mappings, etc

###### dsl functionality
- Implement native policy document support (DSL)
- Implement native tag support (Short-hand)

###### flexibility
- Allow for rendering options (JSON vs YML)
  - Use JSON/YML libraries rather than building by hand

###### CF schema
- use [cf schema](https://d1uauaxba7bl26.cloudfront.net/latest/gzip/CloudFormationResourceSpecification.json)
 provided by AWS for further enhancements
   - add code generation support to allow for compile time constants
   - runtime validation of schema
   - compile time validation of schema