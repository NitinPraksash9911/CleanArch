#!/bin/sh
set -e
set -o pipefail
## FOLLOW THIS STEPS FIRS TO SET EVN VARIABLE FOR THE FOLLOWING COMMANDS

# Set android home path
export ANDROID_HOME=~/Library/Android/sdk

#1.  to install sdk-command-line tools
# https://stackoverflow.com/a/65477410/9076056

#2. this will set temporary env path for sdkmanager and avdmanager command
export PATH=$PATH:$ANDROID_HOME/cmdline-tools/latest/bin

#3.  this will set temporary env path for for emulator command
export PATH=$ANDROID_HOME/emulator:$ANDROID_HOME/tools:$PATH

#close running emulators
echo "Closing emulator"
adb kill-server

echo "Running macro-bench-mark script..."

sdkmanager --install "system-images;android-30;google_apis;x86"

echo "yes" | avdmanager --verbose create avd --force --name "pixel_4_xl" --device "pixel" --package "system-images;android-30;google_apis;x86" --tag "google_apis" --abi "x86"

emulator -avd pixel_4_xl -wipe-data & EMULATOR_PID=$!

# Wait for Android to finish booting
adb wait-for-device shell 'while [[ -z $(getprop sys.boot_completed) ]]; do sleep 1; done;'

# Unlock the Lock Screen
adb shell input keyevent 82

# Clear and capture logcat
adb logcat -c


echo "Rooting the device..."
#https://developer.android.com/topic/performance/baselineprofiles#creating-profile-rules
adb root

#clean
./gradlew clean


#https://github.com/googlesamples/android-testing-templates/blob/master/AndroidTestingBlueprint/README.md#custom-gradle-command-line-arguments
./gradlew benchmark:connectedFullBenchmarkAndroidTest -Pandroid.testInstrumentationRunnerArguments.class=com.nitin.benchmark.CleanArchAppStartupBaselineProfile


#pull baseline profile from device
adb pull "/sdcard/Android/media/com.nitin.benchmark/additional_test_output/CleanArchAppStartupBaselineProfile_startup-baseline-prof.txt"

# rename baseline profile and move it to app/src/main
mv CleanArchAppStartupBaselineProfile_startup-baseline-prof.txt ${pwd}app/src/main/baseline-prof.txt

kill $EMULATOR_PID

exit 0