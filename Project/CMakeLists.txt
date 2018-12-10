option (PROJECT_DocEditor "Document Editor")
if(PROJECT_DocEditor)
    add_subdirectory (DocEditor)
endif()

option (PROJECT_Test "Build system test")
if(PROJECT_Test)
    add_subdirectory (Test)
endif()
