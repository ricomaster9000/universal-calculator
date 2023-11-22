#!/bin/bash
# Ensure the script is placed in .platform/hooks/predeploy/01_setup_logs.sh
# Make sure it's executable: chmod +x .platform/hooks/predeploy/01_setup_logs.sh

mkdir -p /var/app/logs
chown webapp:webapp /var/app/logs
chmod 755 /var/app/logs
# You may also need to touch the file if logback does not create it automatically
touch /var/app/logs/app.log
chown webapp:webapp /var/app/logs/app.log
chmod 644 /var/app/logs/app.log