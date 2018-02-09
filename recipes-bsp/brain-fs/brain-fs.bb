SUMMARY = "SYZYGY Brain filesystem"
DESCRIPTION = "Contains the root home filesystem for the SYZYGY Brain-1"
SECTION = "bsp"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=126a99204a149adf7f2530603435b9aa"

COMPATIBLE_MACHINE = "syzygy-hub"

PROVIDES = "virtual/bitstream"

SRC_URI = "git://github.com/SYZYGYfpga/brain-fs"
SRCREV = "bd7e86e030c13f969ee9a8d0e3b46171e9d17ce1"

PV = "+git${SRCPV}"

S = "${WORKDIR}/git"

FILES_${PN} += "/home/root /home/root/* boot/download.bit"

RDEPENDS_${PN} = "python3-core"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit deploy

do_compile () {
	${CC} -Wl,-O1 -Wl,--hash-style=gnu -Wl,--as-needed ${S}/adc/software/capture_single.c -o ${S}/adc/software/capture_single
	${CC} -Wl,-O1 -Wl,--hash-style=gnu -Wl,--as-needed ${S}/camera/software/capture_single.c -o ${S}/camera/software/capture_single
	${CC} -lm -Wl,-O1 -Wl,--hash-style=gnu -Wl,--as-needed ${S}/dac/software/dac_set.c -o ${S}/dac/software/dac_set
}

do_install() {
	install -d ${D}/${ROOT_HOME}
	git clone --dissociate ${S} ${D}/${ROOT_HOME}/brain-fs
	oldpath=`pwd`
	cd ${D}/${ROOT_HOME}/brain-fs
	git remote set-url origin git://github.com/SYZYGYfpga/brain-fs
	cd $oldpath
	install -m 755 ${S}/adc/software/capture_single ${D}/${ROOT_HOME}/brain-fs/adc/software/capture_single
	install -m 755 ${S}/camera/software/capture_single ${D}/${ROOT_HOME}/brain-fs/camera/software/capture_single
	install -m 755 ${S}/dac/software/dac_set ${D}/${ROOT_HOME}/brain-fs/dac/software/dac_set
	chmod 755 ${D}/${ROOT_HOME}/brain-fs/camera/software/camera_regs.sh
	chmod 755 ${D}/${ROOT_HOME}/brain-fs/camera/software/capture_continuous.sh
	chmod 755 ${D}/${ROOT_HOME}/brain-fs/adc/software/capture_continuous.sh

	install -d ${D}/boot
	install ${S}/helloworld/syzygy-helloworld.bit ${D}/boot/download.bit
}

do_deploy () {
	install -d ${DEPLOYDIR}
	if [ -e ${D}/boot/download.bit ]; then
		install ${D}/boot/download.bit ${DEPLOYDIR}/download.bit
	fi
}

addtask deploy before do_build after do_install

