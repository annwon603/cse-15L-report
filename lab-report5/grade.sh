CPATH='.:lib/hamcrest-core-1.3.jar:lib/junit-4.13.2.jar'
RPATH='.:lib/hamcrest-core-1.3.jar:lib/junit-4.13.2.jar org.junit.runner.JUnitCore'


javac -cp $CPATH PublicTester.java
java -cp $RPATH PublicTester > output

NUMOFTESTRUN=$(grep -Eo "Tests run: [0-9]+" output | grep -Eo "[0-9]+")

NUMFAIL=$(grep -Eo "Failures: [0-9]+" output | grep -Eo "[0-9]+")

echo $NUMOFTESTRUN
echo $NUMFAIL

let NUMSUCCESS=$(($NUMOFTESTRUN-$NUMFAIL))

OK=$(grep OK output)
if [[ $OK = "" ]]
then
    # echo $NUMOFTESTRUN
    # echo $NUMFAIL
    echo "Test Pass: ${NUMSUCCESS}/${NUMOFTESTRUN}"
    echo "Test Fail: ${NUMFAIL}/${NUMOFTESTRUN}"

else
    echo 100 points!
fi

