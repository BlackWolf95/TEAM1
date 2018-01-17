#!/usr/bin/env bash
cd "$(dirname "$0")"/.. || exit 1

for i in ARM/*.arm
do
    #echo $i
    qemu-arm $i > ${i%arm}actual
done

mv ARM/*.actual tests/test_ARM/Actual
