DESCRIPTION = "Linux kernel for the linkdroid device"
SECTION = "kernel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${S}/COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

COMPATIBLE_MACHINE = "alien5|k1pro|k2pro"

DEPENDS = "lzop-native virtual/${TARGET_PREFIX}gcc"

inherit kernel machine_kernel_pr

MACHINE_KERNEL_PR_append = ".6"

EXTRA_OEMAKE_k1pro = "LDFLAGS=''"
EXTRA_OEMAKE_k2pro = "LDFLAGS=''"

LOCALVERSION ?= ""
SRCDATE = "20180531"

SRC_URI[md5sum] = "af6a8b8ded0732e3300d530d2b94741d"
SRC_URI[sha256sum] = "7160060b83e4523fed9e5b7070f9f772d74b638d55216643ae83f8a26ce63022"

SRC_URI += "http://source.mynonpublic.com/linkdroid/linux-${PV}-${SRCDATE}.tar.gz \
    file://defconfig \
    file://${MACHINE}.dts \
"

SRC_URI_append_k1pro += "file://avl.patch"
SRC_URI_append_k2pro += "file://avl.patch"

S = "${WORKDIR}/common"
B = "${WORKDIR}/build"

do_configure_prepend () {
   cp -f ${WORKDIR}/${MACHINE}.dts ${S}/arch/arm64/boot/dts/amlogic/
}

do_compile_prepend () {
  install -d ${B}/drivers/amlogic/amports/
  cp -fr ${S}/drivers/amlogic/amports/amstream.o  ${B}/drivers/amlogic/amports/
  install -d ${B}/drivers/amlogic/dvb_tv/
  cp -fr ${S}/drivers/amlogic/dvb_tv/aml.o  ${B}/drivers/amlogic/dvb_tv/
  cp -fr ${S}/drivers/amlogic/dvb_tv/aml_fe.o  ${B}/drivers/amlogic/dvb_tv/
  install -d ${B}/drivers/amlogic/dvb_tv/smartcard
  cp -fr ${S}/drivers/amlogic/dvb_tv/smartcard/smartcard.o  ${B}/drivers/amlogic/dvb_tv/smartcard/
  install -d ${B}/drivers/amlogic/dvb_tv/dm6k/
  cp -fr ${S}/drivers/amlogic/dvb_tv/dm6k/dm6000_fe.o  ${B}/drivers/amlogic/dvb_tv/dm6k/
  install -d ${B}/drivers/media/dvb-core/
  cp -fr ${S}/drivers/media/dvb-core/dvb-core.o  ${B}/drivers/media/dvb-core/
  install -d ${B}/sound/soc/aml/m8/
  cp -fr ${S}/sound/soc/aml/m8/aml_audio_hw.o  ${B}/sound/soc/aml/m8/
  cp -fr ${S}/sound/soc/aml/m8/aml_audio_hw_pcm.o  ${B}/sound/soc/aml/m8/
  cp -fr ${S}/sound/soc/aml/m8/aml_dmic.o  ${B}/sound/soc/aml/m8/
  cp -fr ${S}/sound/soc/aml/m8/aml_g9tv.o  ${B}/sound/soc/aml/m8/
  cp -fr ${S}/sound/soc/aml/m8/aml_i2s.o  ${B}/sound/soc/aml/m8/
  cp -fr ${S}/sound/soc/aml/m8/aml_i2s_dai.o  ${B}/sound/soc/aml/m8/
  cp -fr ${S}/sound/soc/aml/m8/aml_m8.o  ${B}/sound/soc/aml/m8/
  cp -fr ${S}/sound/soc/aml/m8/aml_notify.o  ${B}/sound/soc/aml/m8/
  cp -fr ${S}/sound/soc/aml/m8/aml_pcm.o  ${B}/sound/soc/aml/m8/
  cp -fr ${S}/sound/soc/aml/m8/aml_pcm_dai.o  ${B}/sound/soc/aml/m8/
  cp -fr ${S}/sound/soc/aml/m8/aml_snd_iomap.o  ${B}/sound/soc/aml/m8/
  cp -fr ${S}/sound/soc/aml/m8/aml_spdif_codec.o  ${B}/sound/soc/aml/m8/
  cp -fr ${S}/sound/soc/aml/m8/aml_spdif_dai.o  ${B}/sound/soc/aml/m8/
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

do_compile_prepend_k1pro () {
  oe_runmake  ${S}/drivers/amlogic/dvb-avl/
  install -d ${B}/drivers/amlogic/dvb-avl/
  cp -fr ${S}/drivers/amlogic/dvb-avl/aml.o  ${B}/drivers/amlogic/dvb-avl/
  cp -fr ${S}/drivers/amlogic/dvb-avl/aml_dmx.o  ${B}/drivers/amlogic/dvb-avl/
  cp -fr ${S}/drivers/amlogic/dvb-avl/aml_dvb.o  ${B}/drivers/amlogic/dvb-avl/
  cp -fr ${S}/drivers/amlogic/dvb-avl/aml_fe.o  ${B}/drivers/amlogic/dvb-avl/
  cp -fr ${S}/drivers/amlogic/dvb-avl/avl6862.o  ${B}/drivers/amlogic/dvb-avl/
  cp -fr ${S}/drivers/amlogic/dvb-avl/r848a.o  ${B}/drivers/amlogic/dvb-avl/
  oe_runmake  ${S}/drivers/amlogic/wetek/
  install -d ${B}/drivers/amlogic/wetek/
  cp -fr ${S}/drivers/amlogic/wetek/ascot3.o  ${B}/drivers/amlogic/wetek/
  cp -fr ${S}/drivers/amlogic/wetek/avl6211.o  ${B}/drivers/amlogic/wetek/
  cp -fr ${S}/drivers/amlogic/wetek/cxd2837.o  ${B}/drivers/amlogic/wetek/
  cp -fr ${S}/drivers/amlogic/wetek/cxd2841er_wetek.o  ${B}/drivers/amlogic/wetek/
  cp -fr ${S}/drivers/amlogic/wetek/mn88436.o  ${B}/drivers/amlogic/wetek/
  cp -fr ${S}/drivers/amlogic/wetek/mxl603.o  ${B}/drivers/amlogic/wetek/
  cp -fr ${S}/drivers/amlogic/wetek/nimdetect.o  ${B}/drivers/amlogic/wetek/
  cp -fr ${S}/drivers/amlogic/wetek/wetek_dmx.o  ${B}/drivers/amlogic/wetek/
  cp -fr ${S}/drivers/amlogic/wetek/wetekdvb.o  ${B}/drivers/amlogic/wetek/
  cp -fr ${S}/drivers/amlogic/wetek/wetek_dvb.o  ${B}/drivers/amlogic/wetek/
  cp -fr ${S}/drivers/amlogic/wetek/wetekplay.o  ${B}/drivers/amlogic/wetek/
  install -d ${B}/drivers/amlogic/amports/
  cp -fr ${S}/drivers/amlogic/amports/amstream.o  ${B}/drivers/amlogic/amports/
  install -d ${B}/drivers/amlogic/dvb_tv/
  cp -fr ${S}/drivers/amlogic/dvb_tv/aml.o  ${B}/drivers/amlogic/dvb_tv/
  cp -fr ${S}/drivers/amlogic/dvb_tv/aml_fe.o  ${B}/drivers/amlogic/dvb_tv/
  oe_runmake  ${S}/drivers/amlogic/dvb_tv/
  oe_runmake  ${S}/drivers/amlogic/dvb_tv/avl6211/
  install -d ${B}/drivers/amlogic/dvb_tv/avl6211/
  cp -fr ${S}/drivers/amlogic/dvb_tv/avl6211/avl6211_fe.o  ${B}/drivers/amlogic/dvb_tv/avl6211/
  cp -fr ${S}/drivers/amlogic/dvb_tv/avl6211/avlfrontend.o  ${B}/drivers/amlogic/dvb_tv/avl6211/
  install -d ${B}/drivers/amlogic/dvb_tv/avl6211/src/
  cp -fr ${S}/drivers/amlogic/dvb_tv/avl6211/src/avl.o  ${B}/drivers/amlogic/dvb_tv/avl6211/src/
  cp -fr ${S}/drivers/amlogic/dvb_tv/avl6211/src/avl_dvbsx.o  ${B}/drivers/amlogic/dvb_tv/avl6211/src/
  cp -fr ${S}/drivers/amlogic/dvb_tv/avl6211/src/IBase.o  ${B}/drivers/amlogic/dvb_tv/avl6211/src/
  cp -fr ${S}/drivers/amlogic/dvb_tv/avl6211/src/IBlindScan.o  ${B}/drivers/amlogic/dvb_tv/avl6211/src/
  cp -fr ${S}/drivers/amlogic/dvb_tv/avl6211/src/IBlindscanAPI.o  ${B}/drivers/amlogic/dvb_tv/avl6211/src/
  cp -fr ${S}/drivers/amlogic/dvb_tv/avl6211/src/IBSP.o  ${B}/drivers/amlogic/dvb_tv/avl6211/src/
  cp -fr ${S}/drivers/amlogic/dvb_tv/avl6211/src/IDiseqc.o  ${B}/drivers/amlogic/dvb_tv/avl6211/src/
  cp -fr ${S}/drivers/amlogic/dvb_tv/avl6211/src/II2C.o  ${B}/drivers/amlogic/dvb_tv/avl6211/src/
  cp -fr ${S}/drivers/amlogic/dvb_tv/avl6211/src/II2CRepeater.o  ${B}/drivers/amlogic/dvb_tv/avl6211/src/
  cp -fr ${S}/drivers/amlogic/dvb_tv/avl6211/src/IRx.o  ${B}/drivers/amlogic/dvb_tv/avl6211/src/
  cp -fr ${S}/drivers/amlogic/dvb_tv/avl6211/src/LockSignal_Api.o  ${B}/drivers/amlogic/dvb_tv/avl6211/src/
  install -d ${B}/drivers/amlogic/dvb_tv/avl6211/AV2011/
  cp -fr ${S}/drivers/amlogic/dvb_tv/avl6211/AV2011/ExtAV2011.o  ${B}/drivers/amlogic/dvb_tv/avl6211/AV2011/
  install -d ${B}/drivers/media/dvb-core/
  cp -fr ${S}/drivers/media/dvb-core/dvb-core.o  ${B}/drivers/media/dvb-core/
  install -d ${B}/sound/soc/aml/m8/
  cp -fr ${S}/sound/soc/aml/m8/aml_audio_hw.o  ${B}/sound/soc/aml/m8/
  cp -fr ${S}/sound/soc/aml/m8/aml_audio_hw_pcm.o  ${B}/sound/soc/aml/m8/
  cp -fr ${S}/sound/soc/aml/m8/aml_dmic.o  ${B}/sound/soc/aml/m8/
  cp -fr ${S}/sound/soc/aml/m8/aml_g9tv.o  ${B}/sound/soc/aml/m8/
  cp -fr ${S}/sound/soc/aml/m8/aml_i2s.o  ${B}/sound/soc/aml/m8/
  cp -fr ${S}/sound/soc/aml/m8/aml_i2s_dai.o  ${B}/sound/soc/aml/m8/
  cp -fr ${S}/sound/soc/aml/m8/aml_m8.o  ${B}/sound/soc/aml/m8/
  cp -fr ${S}/sound/soc/aml/m8/aml_notify.o  ${B}/sound/soc/aml/m8/
  cp -fr ${S}/sound/soc/aml/m8/aml_pcm.o  ${B}/sound/soc/aml/m8/
  cp -fr ${S}/sound/soc/aml/m8/aml_pcm_dai.o  ${B}/sound/soc/aml/m8/
  cp -fr ${S}/sound/soc/aml/m8/aml_snd_iomap.o  ${B}/sound/soc/aml/m8/
  cp -fr ${S}/sound/soc/aml/m8/aml_spdif_codec.o  ${B}/sound/soc/aml/m8/
  cp -fr ${S}/sound/soc/aml/m8/aml_spdif_dai.o  ${B}/sound/soc/aml/m8/
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

do_compile_prepend_k2pro () {
  oe_runmake  ${S}/drivers/amlogic/dvb-avl/
  install -d ${B}/drivers/amlogic/dvb-avl/
  cp -fr ${S}/drivers/amlogic/dvb-avl/aml.o  ${B}/drivers/amlogic/dvb-avl/
  cp -fr ${S}/drivers/amlogic/dvb-avl/aml_dmx.o  ${B}/drivers/amlogic/dvb-avl/
  cp -fr ${S}/drivers/amlogic/dvb-avl/aml_dvb.o  ${B}/drivers/amlogic/dvb-avl/
  cp -fr ${S}/drivers/amlogic/dvb-avl/aml_fe.o  ${B}/drivers/amlogic/dvb-avl/
  cp -fr ${S}/drivers/amlogic/dvb-avl/avl6862.o  ${B}/drivers/amlogic/dvb-avl/
  cp -fr ${S}/drivers/amlogic/dvb-avl/r848a.o  ${B}/drivers/amlogic/dvb-avl/
  oe_runmake  ${S}/drivers/amlogic/wetek/
  install -d ${B}/drivers/amlogic/wetek/
  cp -fr ${S}/drivers/amlogic/wetek/ascot3.o  ${B}/drivers/amlogic/wetek/
  cp -fr ${S}/drivers/amlogic/wetek/avl6211.o  ${B}/drivers/amlogic/wetek/
  cp -fr ${S}/drivers/amlogic/wetek/cxd2837.o  ${B}/drivers/amlogic/wetek/
  cp -fr ${S}/drivers/amlogic/wetek/cxd2841er_wetek.o  ${B}/drivers/amlogic/wetek/
  cp -fr ${S}/drivers/amlogic/wetek/mn88436.o  ${B}/drivers/amlogic/wetek/
  cp -fr ${S}/drivers/amlogic/wetek/mxl603.o  ${B}/drivers/amlogic/wetek/
  cp -fr ${S}/drivers/amlogic/wetek/nimdetect.o  ${B}/drivers/amlogic/wetek/
  cp -fr ${S}/drivers/amlogic/wetek/wetek_dmx.o  ${B}/drivers/amlogic/wetek/
  cp -fr ${S}/drivers/amlogic/wetek/wetekdvb.o  ${B}/drivers/amlogic/wetek/
  cp -fr ${S}/drivers/amlogic/wetek/wetek_dvb.o  ${B}/drivers/amlogic/wetek/
  cp -fr ${S}/drivers/amlogic/wetek/wetekplay.o  ${B}/drivers/amlogic/wetek/
  install -d ${B}/drivers/amlogic/amports/
  cp -fr ${S}/drivers/amlogic/amports/amstream.o  ${B}/drivers/amlogic/amports/
  install -d ${B}/drivers/amlogic/dvb_tv/
  cp -fr ${S}/drivers/amlogic/dvb_tv/aml.o  ${B}/drivers/amlogic/dvb_tv/
  cp -fr ${S}/drivers/amlogic/dvb_tv/aml_fe.o  ${B}/drivers/amlogic/dvb_tv/
  install -d ${B}/drivers/media/dvb-core/
  cp -fr ${S}/drivers/media/dvb-core/dvb-core.o  ${B}/drivers/media/dvb-core/
  install -d ${B}/sound/soc/aml/m8/
  cp -fr ${S}/sound/soc/aml/m8/aml_audio_hw.o  ${B}/sound/soc/aml/m8/
  cp -fr ${S}/sound/soc/aml/m8/aml_audio_hw_pcm.o  ${B}/sound/soc/aml/m8/
  cp -fr ${S}/sound/soc/aml/m8/aml_dmic.o  ${B}/sound/soc/aml/m8/
  cp -fr ${S}/sound/soc/aml/m8/aml_g9tv.o  ${B}/sound/soc/aml/m8/
  cp -fr ${S}/sound/soc/aml/m8/aml_i2s.o  ${B}/sound/soc/aml/m8/
  cp -fr ${S}/sound/soc/aml/m8/aml_i2s_dai.o  ${B}/sound/soc/aml/m8/
  cp -fr ${S}/sound/soc/aml/m8/aml_m8.o  ${B}/sound/soc/aml/m8/
  cp -fr ${S}/sound/soc/aml/m8/aml_notify.o  ${B}/sound/soc/aml/m8/
  cp -fr ${S}/sound/soc/aml/m8/aml_pcm.o  ${B}/sound/soc/aml/m8/
  cp -fr ${S}/sound/soc/aml/m8/aml_pcm_dai.o  ${B}/sound/soc/aml/m8/
  cp -fr ${S}/sound/soc/aml/m8/aml_snd_iomap.o  ${B}/sound/soc/aml/m8/
  cp -fr ${S}/sound/soc/aml/m8/aml_spdif_codec.o  ${B}/sound/soc/aml/m8/
  cp -fr ${S}/sound/soc/aml/m8/aml_spdif_dai.o  ${B}/sound/soc/aml/m8/
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
