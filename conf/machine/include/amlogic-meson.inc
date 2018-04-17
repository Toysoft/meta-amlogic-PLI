# Amlogic Meson SoCs configurations

IMAGE_CLASSES += "image_types_meson"

MACHINE_EXTRA_RRECOMMENDS = "kernel-modules kernel-devicetree"

SERIAL_CONSOLE = "115200 ttyAML0"

KERNEL_IMAGETYPE = "uImage"
UBOOT_LOADADDRESS = "0x1080000"
UBOOT_ENTRYSYMBOL = "0x1080000"

IMAGE_BOOT_FILES ?= "uImage"

IMAGE_ROOTFS_SIZE = "1048576"
IMAGE_FSTYPES = "ext4 sdcard"
WKS_FILE = "sdimage-bootpart.wks"
do_image_wic[depends] += "mtools-native:do_populate_sysroot dosfstools-native:do_populate_sysroot"

IMAGE_CMD_sdcard_append = " \
    mkdir -p ${DEPLOY_DIR_IMAGE}/${IMAGEDIR}; \
    mv ${DEPLOY_DIR_IMAGE}/${IMAGE_NAME}.rootfs.meson-sd.img ${DEPLOY_DIR_IMAGE}/${IMAGEDIR}/${IMAGE_NAME}.sdcard.img; \
    echo ${IMAGE_VER_STRING} > ${DEPLOY_DIR_IMAGE}/${IMAGEDIR}/imageversion; \
    cd ${DEPLOY_DIR_IMAGE}; \
    zip ${IMAGE_NAME}_sdcard.zip ${IMAGEDIR}/*; \
    rm -f ${DEPLOY_DIR_IMAGE}/*.ext4; \
    rm -f ${DEPLOY_DIR_IMAGE}/*.manifest; \
    rm -f ${DEPLOY_DIR_IMAGE}/.ext4; \
    rm -f ${DEPLOY_DIR_IMAGE}/.manifest; \
    rm -f ${DEPLOY_DIR_IMAGE}/.meson-sd.img; \
    rm -f ${DEPLOY_DIR_IMAGE}/*.rootfs.meson-sd.img; \
    rm -Rf ${IMAGEDIR}; \
    "
