#!/bin/bash
# first shell script
# Hello World
#echo -e "Hello World \a \n"
#exit 0
#read -p "please input the first name:" firstname
#read -p "please input the last name:" lastname
#echo -e "\nthe name is : $firstname $lastname"

#read -p "please input the num1:" num1
#read -p "please input the num2:" num2
#declare -i sum=$(($num1 + $num2))
#echo -e "the sum is $sum"
#echo -e "multiply is $(($num1*$num2))"

#test -e helloWorld.sh && echo "Exist" || "Not exist"

#[ -z $HOME ];echo $?

echo "script name ==> $0"
echo "total parameter number ==> $#"
[ "$#" -lt 2 ] && echo "number is less then 2, stop here" && exit 0
echo "your parameter is ==> '$@'"
echo "your parameter2 is  ==> '$*'"
echo "The first param is ==> $1"
