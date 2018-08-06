IMAGE_CLASSES += "image_types_wetek"

GST_VERSION = "0.10"

DVBPROVIDER = "kernel"
DVBSKYPROVIDER = "kernel"

IMAGEDIR ?= "${MACHINE}"

MACHINE_EXTRA_RRECOMMENDS += "\
    libamplayer \
    libamavutils \
    libamadec \
    libamcodec \
    "

IMAGE_FSTYPES += "ext4 sdcard"
IMAGE_ROOTFS_SIZE = "976562"
IMAGE_EXTRA_SPACE = "0"
IMAGE_OVERHEAD_FACTOR = "1.0"

# Serial console to be used
SERIAL_CONSOLE = "115200 ttyS0"

UBOOT_ENTRYPOINT  = "0x80008000"
UBOOT_LOADADDRESS = "0x80008000"

IMAGE_CMD_sdcard_append = "\
    mkdir -p ${DEPLOY_DIR_IMAGE}/${IMAGEDIR}; \
    mv ${DEPLOY_DIR_IMAGE}/${IMAGE_NAME}.rootfs.wetek-sd.img ${DEPLOY_DIR_IMAGE}/${IMAGEDIR}/${IMAGE_NAME}.sdcard.img; \
    echo ${IMAGE_VER_STRING} > ${DEPLOY_DIR_IMAGE}/${IMAGEDIR}/imageversion; \
    cd ${DEPLOY_DIR_IMAGE}; \
    zip ${IMAGE_NAME}_sdcard.zip ${IMAGEDIR}/*; \
    rm -f ${DEPLOY_DIR_IMAGE}/*.ext4; \
    rm -f ${DEPLOY_DIR_IMAGE}/*.manifest; \
    rm -f ${DEPLOY_DIR_IMAGE}/.ext4; \
    rm -f ${DEPLOY_DIR_IMAGE}/.manifest; \
    rm -f ${DEPLOY_DIR_IMAGE}/.wetek-sd.img; \
    rm -f ${DEPLOY_DIR_IMAGE}/*.rootfs.wetek-sd.img; \
    rm -Rf ${IMAGEDIR}; \
    "

MACHINE_FEATURES += "wlan bluetooth fb usbhost vfat alsa dvb-c nolcd wetek-pli"

PREFERRED_PROVIDER_virtual/libgles1 = "opengl-amlogic"
PREFERRED_PROVIDER_virtual/libgles2 = "opengl-amlogic"
PREFERRED_PROVIDER_virtual/egl = "opengl-amlogic"

PREFERRED_PROVIDER_jpeg = "libjpeg-turbo"
PREFERRED_PROVIDER_jpeg-native = "libjpeg-turbo-native"

EXTRA_OECONF_append_pn-enigma2 = " --with-alsa --with-alsacard=hw:0 --with-alsamixer=Master --with-fbdev=/dev/fb2 --with-lcddev=/dev/null --with-amlogic"
DEPENDS_append_pn-enigma2 = " libdvbcsa libamcodec libamadec"
EXTRA_OECONF_append_pn-tuxtxt-enigma2 = " --with-fbdev=/dev/fb2"
CC_append_pn-showiframe = " -DHAVE_AMLOGIC -I${STAGING_INCDIR}/amlogic/amcodec -lamcodec"
DEPENDS_append_pn-showiframe = " libamcodec"

include conf/machine/include/autoload-filesystems.inc

# We know about rtc
include conf/machine/include/build-extras.inc
