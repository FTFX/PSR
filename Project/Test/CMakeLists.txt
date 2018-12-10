project (Test)

add_executable(Test main.c)

macro (TestUnit arg result)
  add_test(NAME TestUnit${arg} COMMAND Test ${arg})
  set_tests_properties (TestUnit${arg}
    PROPERTIES
    PASS_REGULAR_EXPRESSION ${result})
endmacro (TestUnit)

TestUnit (111 "111")
TestUnit (222 "222")
