#!/bin/bash
cd "$(dirname "$0")/../tests"

MINCAMLC=java/mincamlc

for file in tests/syntax/valid/*.ml
do
        echo "testing parser on: (basename $file)"
        $MINCAMLC -p $file 2> /dev/null 1> /dev/null
        if [ $? -ne 0 ]; then
                echo 1
        else
                echo 0
        fi
done

for file in tests/syntax/invalid/*.ml
do
        echo "testing parser on: (basename $file)"
        $MINCAMLC -p $file 2> /dev/null 1> /dev/null
        if [ $? -ne 0 ]; then
                echo 1
        else
                echo 0
        fi
done
