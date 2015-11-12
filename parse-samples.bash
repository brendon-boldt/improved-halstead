#!/usr/bin/env bash

directory=$1
samples=$(ls $directory)
for sample in $samples
do
  value=$(scala runtime.Main $directory/$sample)
  printf "%-8s\t%-8s\n" $sample $value
  #printf "%s" $sample
  #echo $(scala runtime.Main $directory/$sample)
done | column -t
