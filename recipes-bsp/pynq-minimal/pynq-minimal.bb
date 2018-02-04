SUMMARY = "Minimal PYNQ installation"
DESCRIPTION = "This provides a minimal PYNQ setup with just the gpio, ps, and mmio modules"
SECTION = "bsp"

LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=601522f2e6d5770e6640f4052da1bcdd"

FILES_${PN} = "${THISDIR}/pynq-minimal:"

SRC_URI = "git://github.com/Xilinx/PYNQ \
           file://0001-Comment-out-unused-imports.patch"
SRCREV = "72b2e505e6a6a6a2cc77321d006a2c6fff65371a"


PV = "+git${SRCPV}"

S = "${WORKDIR}/git"

inherit setuptools3

do_install () {
	install -d ${D}${PYTHON_SITEPACKAGES_DIR}
	install -d ${D}${PYTHON_SITEPACKAGES_DIR}/pynq
	install ${S}/pynq/__init__.py ${D}${PYTHON_SITEPACKAGES_DIR}/pynq
	install ${S}/pynq/gpio.py ${D}${PYTHON_SITEPACKAGES_DIR}/pynq
	install ${S}/pynq/interrupt.py ${D}${PYTHON_SITEPACKAGES_DIR}/pynq
	install ${S}/pynq/mmio.py ${D}${PYTHON_SITEPACKAGES_DIR}/pynq
	install ${S}/pynq/ps.py ${D}${PYTHON_SITEPACKAGES_DIR}/pynq
}

