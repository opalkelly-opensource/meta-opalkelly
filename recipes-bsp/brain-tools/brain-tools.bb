SUMMARY = "SmartVIO controller software for the SYZYGY Brain 1"
DESCRIPTION = "Contains the smart VIO controller software for the Brain 1 and startup script"
SECTION = "bsp"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=126a99204a149adf7f2530603435b9aa"

COMPATIBLE_MACHINE = "syzygy-hub"

SRC_URI = "git://github.com/SYZYGYfpga/brain-tools"
SRCREV = "d114aae572edf75e0718a2ce1b74376e35f0dfe1"

PV = "+git${SRCPV}"

S = "${WORKDIR}/git"

RDEPENDS_${PN} = "bash"

inherit update-rc.d

INITSCRIPT_NAME = "syzygy_smartvio.sh"
INITSCRIPT_PARAMS = "defaults 90"

do_compile () {
	cd ${S}
	make CFLAGS="-Wall -Wl,-O1 -Wl,--hash-style=gnu -Wl,--as-needed"
}

do_install () {
	install -d ${D}${bindir}
	install -m 0755 ${S}/smartvio-brain ${D}${bindir}/smartvio
	install -m 0755 ${S}/dna-writer ${D}${bindir}/dna-writer
	install -m 0755 ${S}/szg_i2cread ${D}${bindir}/szg_i2cread
	install -m 0755 ${S}/szg_i2cwrite ${D}${bindir}/szg_i2cwrite

	install -d ${D}${sysconfdir}/init.d
	install -m 0755 ${S}/syzygy_smartvio.sh ${D}${sysconfdir}/init.d/syzygy_smartvio.sh
}

