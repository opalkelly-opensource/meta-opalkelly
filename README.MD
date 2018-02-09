Yocto overlay to add support for Opal Kelly development boards.

Currently support is only provided for the "Morty" version of yocto, though
future releases may be added in the future.


Supported Boards
================

* Zynq:
	* [SYZYGY Brain-1](http://www.syzygyfpga.io/hub) - 'syzygy-brain-1'


Dependencies
============

This layer depends on:

  URI: git://git.openembedded.org/bitbake
  branch: master

  URI: git://git.openembedded.org/openembedded-core
  layers: meta
  branch: morty

  URI: git://git.yoctoproject.org/meta-xilinx
  layers: meta-xilinx
  branch: morty


Patches
=======

Please submit any patches against this BSP as Github pull requests or contact
the maintainer directly:

Maintainer: Tom McLeod <tom.mcleod@opalkelly.com>


Building/Booting
================

To build for a machine provided in this layer, add the layer to the
conf/bblayers.conf file in the Yocto build directory like so:

```
BBLAYERS ?= " \
  work/poky/meta \
  work/poky/meta-poky \
  work/poky/meta-yocto-bsp \
  work/poky/meta-xilinx \
  work/poky/meta-opalkelly \
  "
```

Edit conf/local.conf and change the MACHINE variable to the desired machine.
You must also add the following lines to the bottom of the local.conf file
(above the CONF_VERSION variable):

```
KERNEL_IMAGETYPES_append = " fitImage"
KERNEL_CLASSES += "kernel-fitimage"

UBOOT_BINARY = "u-boot.img"
```

It is now possible to run `bitbake <desired image>` to build an image. A good,
basic image for starting is core-image-minimal.

To boot the board, an sdcard image can be generated using the 'wic' tool from
Yocto. Simply run:

```
wic create sdimage-bootpart -e <desired image>
```

This will generate an SD card image which can be written to an SD card using
either the dd command in Linux or a disk imaging tool such as
[Etcher](https://www.etcher.io).

Simply insert the SD card into the board and power it up to boot Linux.

Progress can be viewed on a serial terminal.

See the [Yocto Project](http://yoctoproject.org) website for more information.

Opal Kelly Reference Image
==========================

The official Brain-1 image from Opal Kelly is more full featured than the
minimal image provided by Yocto, including some text editors, a web server,
python3 along with a number of python libraries, and more.

To re-create the official image, add the following lines to the local.conf
file:

```
# Testing tools
CORE_IMAGE_EXTRA_INSTALL += "i2c-tools"
CORE_IMAGE_EXTRA_INSTALL += "ethtool"
#CORE_IMAGE_EXTRA_INSTALL += "iperf3"

# Python
CORE_IMAGE_EXTRA_INSTALL += "python3"
CORE_IMAGE_EXTRA_INSTALL += "python3-dev"
CORE_IMAGE_EXTRA_INSTALL += "python3-mmap"
CORE_IMAGE_EXTRA_INSTALL += "python3-numpy"
CORE_IMAGE_EXTRA_INSTALL += "python3-multiprocessing"
CORE_IMAGE_EXTRA_INSTALL += "python3-asyncio"
CORE_IMAGE_EXTRA_INSTALL += "python3-modules"
CORE_IMAGE_EXTRA_INSTALL += "python3-setuptools"
CORE_IMAGE_EXTRA_INSTALL += "pynq-minimal"

# SYZYGY projects
CORE_IMAGE_EXTRA_INSTALL += "brain-tools"
CORE_IMAGE_EXTRA_INSTALL += "brain-fs"
CORE_IMAGE_EXTRA_INSTALL += "brain-usb-eth"

# WiFi tools
CORE_IMAGE_EXTRA_INSTALL += "wpa-supplicant"
CORE_IMAGE_EXTRA_INSTALL += "linux-firmware"

# Camera tools
CORE_IMAGE_EXTRA_INSTALL += "imagemagick"
CORE_IMAGE_EXTRA_INSTALL += "bayer2rgb"

# ADC tools
CORE_IMAGE_EXTRA_INSTALL += "gnuplot"

# General tools
CORE_IMAGE_EXTRA_INSTALL += "lighttpd"
CORE_IMAGE_EXTRA_INSTALL += "lighttpd-module-cgi"
CORE_IMAGE_EXTRA_INSTALL += "git"
CORE_IMAGE_EXTRA_INSTALL += "openssh"
CORE_IMAGE_EXTRA_INSTALL += "vim"
CORE_IMAGE_EXTRA_INSTALL += "vim-syntax"
CORE_IMAGE_EXTRA_INSTALL += "vim-common"
CORE_IMAGE_EXTRA_INSTALL += "vim-help"
CORE_IMAGE_EXTRA_INSTALL += "vim-vimrc"
CORE_IMAGE_EXTRA_INSTALL += "vim-tools"
CORE_IMAGE_EXTRA_INSTALL += "nano"
```

The steps above to build the core-image-minimal image and SD card can now
be performed to re-create the Opal Kelly reference image.

