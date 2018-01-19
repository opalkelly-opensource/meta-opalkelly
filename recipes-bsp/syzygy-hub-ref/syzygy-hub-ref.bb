SUMMARY = "Reference design bitfile for syzygy-hub"
DESCRIPTION = "Contains the required bitfile for the syzygy-hub board"
SECTION = "bsp"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=cb830fb275f37ed40f7e636c01f4f550"

COMPATIBLE_MACHINE = "syzygy-hub"

PROVIDES = "virtual/bitstream"

SRC_URI = "git://github.com/SYZYGYfpga/syzygy-hub-helloworld.git;protocol=https;nobranch=1"
SRCREV = "2de5a351337c9c09d2ec05ccdc74b6c0b78a9e1d"

PV = "+git${SRCPV}"

S = "${WORKDIR}/git"

FILES_${PN} += "boot/download.bit"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit deploy

do_install() {
	install -d ${D}/boot
	install ${S}/binary/syzygy-helloworld.bit ${D}/boot/download.bit
}

do_deploy () {
	install -d ${DEPLOYDIR}
	if [ -e ${D}/boot/download.bit ]; then
		install ${D}/boot/download.bit ${DEPLOYDIR}/download.bit
	fi
}

addtask deploy before do_build after do_install

