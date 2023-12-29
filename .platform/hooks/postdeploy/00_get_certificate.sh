#!/bin/bash
amazon-linux-extras install epel -y
yum update -y --skip-broken

# Check if certbot is installed
if ! yum list installed certbot &>/dev/null; then
    yum install -y certbot
    yum install certbot-nginx -y
fi

sudo certbot -n -d universal-calculator.net --nginx --agree-tos --email ricorpwd@yahoo.co.za --redirect