#!/bin/sh

#source /home/natalie/.profile
source /etc/profile

while true; do
        echo "uploading YAP"
        /home/ANT.AMAZON.COM/adiknob/yapproj/src/yap/yap api > /dev/null 2>&1 &
        #/home/natalie/tmp/PsychFeatures/yapproj/src/yap/yap api > /dev/null 2>&1 &
        process_id=$!
        sleep 35
        echo "YAP is supposed to be up"
        sleep 120
        echo "killing YAP"
        kill $process_id
done
