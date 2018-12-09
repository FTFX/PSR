project (Algorithm)

option (ALGORITHM_LinkedList "Achievement of Linked List")
if(ALGORITHM_LinkedList)
    add_subdirectory (LinkedList)
endif()

option (ALGORITHM_SequenceList "Achievement of Sequence List")
if(ALGORITHM_SequenceList)
    add_subdirectory (SequenceList)
endif()

option (ALGORITHM_Queue "Achievement of Queue")
if(ALGORITHM_Queue)
    add_subdirectory (Queue)
endif()

option (ALGORITHM_Stack "Achievement of Stack")
if(ALGORITHM_Stack)
    add_subdirectory (Stack)
endif()