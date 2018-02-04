SUMMARY = "Automatically setup USB Ethernet at startup for the Brain-1"
SECTION = "bsp"

LICENSE = "MIT"
LICENSE_PATH += "files"
LIC_FILES_CHKSUM = "file://LICENSE;md5=126a99204a149adf7f2530603435b9aa"

COMPATIBLE_MACHINE = "syzygy-hub"

SRC_URI = "file://udhcpd.conf \
           file://usb-eth.sh \
           "

S = "${WORKDIR}"

inherit update-rc.d

INITSCRIPT_NAME = "usb-eth.sh"
INITSCRIPT_PARAMS = "defaults 80"

do_install () {
	install -d ${D}${sysconfdir}/init.d
	install -m 0755 ${S}/usb-eth.sh ${D}${sysconfdir}/init.d/usb-eth.sh
	install ${S}/udhcpd.conf ${D}${sysconfdir}/udhcpd.conf
}

