SUMMARY = "SYZYGY Tools"
DESCRIPTION = "Contains various tools that can be helpful when creating SYZYGY compatible devices"
SECTION = "bsp"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=126a99204a149adf7f2530603435b9aa"

COMPATIBLE_MACHINE = "syzygy-hub"

SRC_URI = "git://github.com/SYZYGYfpga/syzygy-tools"
SRCREV = "62fdb3b970047f87e5c59fc37d6a827e6fbce128"

PV = "+git${SRCPV}"

S = "${WORKDIR}/git"

RDEPENDS_${PN} = "bash"

do_compile () {
	cd ${S}
	make CFLAGS="-Wall -Wl,-O1 -Wl,--hash-style=gnu -Wl,--as-needed"
}

do_install () {
	install -d ${D}${bindir}
	install -m 0755 ${S}/dna-writer ${D}${bindir}/dna-writer
}

