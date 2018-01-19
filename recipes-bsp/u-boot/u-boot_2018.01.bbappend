FILESEXTRAPATHS_prepend := "${THISDIR}/u-boot:"

PR := "${PR}.1"

SRC_URI_append = " file://0001-Updates-for-latest-template-project.patch \
                  file://uEnv.txt"

LICENSE = "GPLv2+"

# with the patch above we are fully supported here
HAS_PS7INIT += "syzygy_hub_config"

UBOOT_ENV = "uEnv"
