#!/bin/bash

$BUILD_TARGET = "-DALGORITHM_LinkedList=ON \
                -DALGORITHM_SequenceList=ON \
                -DALGORITHM_Queue=ON \
                -DALGORITHM_Stack=ON"

mkdir Build
cd Build
cmake ${BUILD_TARGET} ..
make -v
make VERBOSE=1
