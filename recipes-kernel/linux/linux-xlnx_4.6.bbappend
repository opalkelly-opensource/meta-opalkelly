FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

PR := "${PR}.1"

COMPATIBLE_MACHINE_syzygy-hub = "syzygy-hub"

SRC_URI_append = "file://0001-Add-SYZYGY-Hub-devicetree.patch \
                  file://0002-Reserve-correct-amount-of-non-DMA-RAM.patch \
                  file://gadget-eth.cfg"
