#!/usr/bin/env bash
cd "$(dirname "$0")"/.. || exit 1

for i in tests/inputs/*.ml
do
    #echo $i
    ocaml $i > ${i%ml}expected
done

mv tests/inputs/*.expected tests/test_ARM/Expected
