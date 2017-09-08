FILESEXTRAPATHS_prepend := "${THISDIR}/u-boot-xlnx:"

PR := "${PR}.1"

SRC_URI_append = "file://0001-Add-support-for-SYZYGY-Hub-board.patch \
                  file://0002-Add-support-for-Zynq-7000S-7007s-7012s-7014s.patch \
                  file://uEnv.txt"

LICENSE = "GPLv2+"

# with the patch above we are fully supported here
HAS_PS7INIT += "syzygy_hub_config"

UBOOT_ENV = "uEnv"
