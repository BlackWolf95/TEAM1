#!/bin/sh
cd "$(dirname "$0")"/.. || exit 1
dir1=`ls tests/test_ARM/Expected/*`
dir2=`ls tests/test_ARM/Actual/*`

for file1 in $dir1
do

  file2_1=${file1:24:-9}.actual
  file2=`ls tests/test_ARM/Actual/$file2_1`
  #echo $file2
  #echo
  #echo
  if diff -c $file1 $file2 >/dev/null ;
  then
    echo OK
  else
    echo KO
  fi

done
