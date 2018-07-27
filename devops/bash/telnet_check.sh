#!/bin/bash
# color variables
txtred2='\e[1;31m' # Red
txtgrn2='\e[1;32m' # Green
txtylw2='\e[1;33m' # Yellow
txtpur2='\e[1;35m' # Purple

#########################Notice##########################
#1. Port Scan script Using nmap                         #
#2. Time for scanning 1000 ports : 30 sec per Host      #
#3. Time for scanning 1-65535 ports : 15 min per Host   #
#########################################################

 # Configure
SCRIPT_PATH="/tmp/"                                                     # Save Path
EXECUTE_TIME=`date +"%y/%m/%d %H:%M:%S"`
START_SEC=`date +%s`                                            # Save start time
IPLIST=$1                                                               # IPLIST file name of Script
ARGC=$#
SCAN_OPTION=$2
MAILDATE=`date +"%y/%m/%d"`
DATE=`date +"%y_%m_%d"`
FILE=$SCRIPT_PATH"result/port_scan_"$DATE       # result file name : result/port_scan_YYYY_MM_DD
HOSTNUM=0                                                               # Save the number of hosts 
CRITICAL=0                                                              # Save the number of Critical ports
HOST=""                                                                 # Save IP number
EXCEPT_FILE=$SCRIPT_PATH"except.txt"            # Save Except file path


 # Check files, folders and commands
function need_check() {

         # Check dos2unix command
        which dos2unix > /dev/null 2>&1

        if [[ $? != 0 ]]
        then
                echo -e "${txtred2}Can not run the command 'dos2unix'${txtrst}"
                apt-get install tofrodos -y
                ln -s /usr/bin/fromdos /usr/bin/dos2unix
                exit
        fi

        # Check expect command
        which expect > /dev/null 2>&1

        if [[ $? != 0 ]]
        then
                echo -e "${txtred2}Can not run the command 'expect'${txtrst}"
                apt-get install expect -y
        fi


         # Check IPLIST file
        if [ ! -f ${IPLIST} ];then
                echo -e "${txtred2}Usage: port_scan [FILE]\n${txtrst}"
                echo -e "${txtred2}Example: port_scan iplist.txt${txtrst}"
                exit
        fi

         # Check result folder
        if ! test -e $SCRIPT_PATH"result"
        then
                echo -e "${txtgrn2}Create Result directory${txtrst}"
                mkdir $SCRIPT_PATH"result"
        fi

         # Make result file
        if [ ! -e $FILE ]
        then
                touch $FILE 
        else
                rm -rf $FILE
                touch $FILE    
        fi
}

 # Port Scan function
function port_scan() {

        #if [[ $ARGC -eq 1 ]]
        #then
                 # Input Option of port scan range
                echo -e "${txtgrn2}Do you want to check telnet connectivity?${txtrst}"
                echo -n "(y or n) : "
                read SCAN_OPTION
        #fi

        if [[ $SCAN_OPTION == 'y' ]] || [[ $SCAN_OPTION == 'Y' ]]
        then
                RESULT_SUB="Default port number is 23 - $EXECUTE_TIME"
                echo "You choice default port scan option"
        else
                echo "Bye~"
                exit
        fi

        dos2unix $IPLIST
        echo $IPLIST
        echo -e "${txtylw2}***************Port Scan***************${txtrst}" | tee -a $FILE
        echo -e "${txtgrn2}Executed Time - $EXECUTE_TIME${txtrst}" | tee -a $FILE
        echo -e "${txtylw2}---------------------------------------${txtrst}" | tee -a $FILE

         # Read iplist.txt by one, save in HOST
        while read HOST       
        do
                 # Temporary Exception Cause Not our IP
                if [[ $HOST == *"xxx.xxx.xxx.xxx"* ]]
                then
                        continue
                fi

        # Print HOST scan start time
        echo "$HOST Port Scanning - `date +"%y/%m/%d %H:%M:%S"`" | tee -a $FILE

                # Check Logic logic
expect <<EOF
        sleep 1
        spawn telnet $HOST 
        expect -re "login:"
                send \x03;
                puts [open $FILE a] $HOST;
        expect eof
EOF
        echo "---------------------------------------" | tee -a $FILE

         # Loop until read all Hosts
        done < $IPLIST      

        echo "Finished Time - `date +"%y/%m/%d %H:%M:%S"`" | tee -a $FILE
        echo -e "${txtylw2}*************Scan Finished*************${txtrst}" | tee -a $FILE

}


 # Check files, folders and commands
need_check

 # Execute Port Scan function
port_scan
FINISH_SEC=`date +%s`   # Save finish time
let DURING_SEC=$FINISH_SEC-$START_SEC
echo "Spend Time : $DURING_SEC sec"