#!/bin/bash
# description:

. /etc/init.d/functions

PIDFILE=/var/run/udhcpd.pid

start() {
        /usr/sbin/udhcpd -I 192.168.7.1 /etc/udhcpd.conf
}

stop() {
        start-stop-daemon -K --quiet --pidfile $PIDFILE
}

case "$1" in
        start)
                start
                ;;
        stop)
                stop
                ;;
        restart)
                stop
                start
                ;;
        *)
        echo "Usage: $0 {start|stop|restart}"
esac

