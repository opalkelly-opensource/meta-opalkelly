Yocto overlay to add support for Opal Kelly development boards.

Currently support is only provided for the "Morty" version of yocto, though
future releases may be added in the future.


Supported Boards
================

* Zynq:
	* [SYZYGY Hub](http://www.syzygyfpga.io/hub) - 'syzygy-hub'


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
```

It is now possible to run `bitbake <desired image>` to build an image. A good,
basic image for starting is core-image-minimal.

To boot the board, an sdcard image can be generated using the 'wic' tool from
Yocto. Simply run:

```
wic create sdimage-bootpart -e <desired image>
```

This will generate an SD card image which can be written to an SD card using
either the dd command in Linux or a Windows disk imaging tool such as
[Win32 Disk Imager](http://sourceforge.net/projects/win32diskimager/).

Simply insert the SD card into the board and power it up to boot Linux.

Progress can be viewed on a serial terminal.

See the [Yocto Project](http://yoctoproject.org) website for more information.
