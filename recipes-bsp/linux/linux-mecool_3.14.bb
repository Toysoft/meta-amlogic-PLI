DESCRIPTION = "Linux kernel for the mecool device"
SECTION = "kernel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

COMPATIBLE_MACHINE = "k1pro"

DEPENDS = "lzop-native virtual/${TARGET_PREFIX}gcc"

inherit kernel machine_kernel_pr

MACHINE_KERNEL_PR_append = ".2"

LOCALVERSION ?= ""

SRC_URI[md5sum] = "914bbd4442c3fe880a9d7f922bec0be3"
SRC_URI[sha256sum] = "c34cebda340545f9d3c9867994114ce4549cc22f4194d3ed07accfc10bc48a1e"

SRC_URI += "https://github.com/persianpros/linux-amlogic/archive/amlogic-3.14-osmc.zip \
    file://defconfig \
    file://${MACHINE}.dts \
"

S = "${WORKDIR}/linux-amlogic-amlogic-3.14-osmc"
B = "${WORKDIR}/build"

do_configure_prepend () {
   cp -f ${WORKDIR}/${MACHINE}.dts ${S}/arch/arm64/boot/dts/amlogic/
}

do_compile_prepend () {
  if test -n "${KERNEL_DEVICETREE}"; then
      for DTB in ${KERNEL_DEVICETREE}; do
          if echo ${DTB} | grep -q '/dts/'; then
              bbwarn "${DTB} contains the full path to the the dts file, but only the dtb name should be used."
              DTB=`basename ${DTB} | sed 's,\.dts$,.dtb,g'`
          fi
          oe_runmake ${DTB}
      done
  # Create directory, this is needed for out of tree builds
  mkdir -p ${B}/arch/arm64/boot/dts/amlogic/
  fi
}

do_compile_append() {
    cp ${B}/arch/arm64/boot/dts/amlogic/${MACHINE}.dtb ${B}/arch/arm64/boot/
}

do_rm-work() {
}
