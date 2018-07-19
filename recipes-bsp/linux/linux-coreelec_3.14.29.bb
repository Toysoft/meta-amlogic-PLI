DESCRIPTION = "Amlogic Kernel"
SECTION = "kernel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${S}/COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

inherit kernel machine_kernel_pr
require recipes-kernel/linux/linux-yocto.inc
DEPENDS = "xz-native bc-native u-boot-mkimage-native virtual/${TARGET_PREFIX}gcc"
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

COMPATIBLE_MACHINE = "k2pro_s905"


S = "${WORKDIR}/git"
B = "${WORKDIR}/build"

MACHINE_KERNEL_PR_append = ".1"

PV = "3.14.29+git${SRCPV}"

KBRANCH = "amlogic-3.14-nougat"
SRCREV ?= "a002c0fe1fe5991001130af6470a4a1233b5b46b"

BB_GIT_SHALLOW ?= "1"
# Keep only the top commit
BB_GIT_SHALLOW_DEPTH ?= "1"

SRC_URI = "git://github.com/CoreELEC/linux-amlogic.git;branch=${KBRANCH}"

SRC_URI += " \
  file://gxbb_p200_k2_pro.dts \
  file://defconfig \
  file://persianprince.patch \
  "

SRC_URI[sha256sum] = "4446b76bf4caed6411bf2b0459fa200ce2632fdf7bd74c900d85ce6148bf9fc2"

do_compile_append() {
  install -m 0644 ${WORKDIR}/gxbb_p200_k2_pro.dts ${S}/arch/arm64/boot/dts/amlogic/gxbb_p200_k2_pro.dts
  oe_runmake gxbb_p200_k2_pro.dtb
  cp ${B}/arch/arm64/boot/dts/amlogic/gxbb_p200_k2_pro.dtb ${B}/arch/arm64/boot/
}
