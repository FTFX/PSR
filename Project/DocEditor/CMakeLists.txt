project (DocEditor)

add_executable(DocEditor main.c)

macro (DocEditorTest arg result)
  add_test(NAME DocEditor${arg} COMMAND DocEditor ${arg})
  set_tests_properties (DocEditor${arg}
    PROPERTIES
    PASS_REGULAR_EXPRESSION ${result})
endmacro (DocEditorTest)

DocEditorTest (111 "111")
DocEditorTest (222 "222")
