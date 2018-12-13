#!/bin/bash

$BUILD_TARGET="-DALGORITHM_LinkedList=ON \
               -DALGORITHM_SequenceList=ON \
               -DALGORITHM_Queue=ON \
               -DALGORITHM_Stack=ON \
               -DPROJECT_DocEditor=ON"

mkdir Build
cd Build
cmake ${BUILD_TARGET} ..
make
