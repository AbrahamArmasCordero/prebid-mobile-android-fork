#! /bin/bash
set -e

cd ..

function echoX {
echo -e "✅ PREBID TESTLOG: $@"
}

BASEDIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
cd $BASEDIR

echoX "clean previous build"
./gradlew clean

echoX "start unit tests"
./gradlew PrebidMobile:testDebugUnitTest