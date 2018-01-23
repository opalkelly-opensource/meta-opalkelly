SUMMARY = "Command line utility to convert bayer grid data to rgb data."
DESCRIPTION = "Command line utility to convert bayer grid data to rgb data."
SECTION = "graphics"

LICENSE = "LGPL-2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=fbc093901857fcd118f065f900982c24"

SRC_URI = "git://github.com/jdthomas/bayer2rgb"
SRCREV = "319dfe8a4f780df0c7f7112889c26428d8bdfe14"

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI_append = " file://0001-Fix-issue-with-char-c-for-platforms-with-unsigned-ch.patch"

PV = "+git${SRCPV}"

S = "${WORKDIR}/git"

do_compile () {
	${CC} -O3 -Wall -lm -Wl,-O1 -Wl,--hash-style=gnu -Wl,--as-needed ${S}/bayer.c ${S}/bayer2rgb.c -o ${WORKDIR}/bayer2rgb
}

do_install () {
	install -d ${D}${bindir}
	install -m 0755 ${WORKDIR}/bayer2rgb ${D}${bindir}/
}

