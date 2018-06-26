DESCRIPTION = "Amlogic Kernel"
SECTION = "kernel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${S}/COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

SRC_URI[md5sum] = "9712aee820b5a9a7cd1dc6f57fc58bb8"
SRC_URI[sha256sum] = "2a6843b68142835e81f6e73f4b9e0c3bb1c11e59029a866ab5691c95c634e859"

inherit kernel machine_kernel_pr

DEPENDS = "xz-native bc-native u-boot-mkimage-native virtual/${TARGET_PREFIX}gcc"

# Avoid issues with Amlogic kernel binary components
INSANE_SKIP_${PN} += "already-stripped"
INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_STRIP = "1"
LINUX_VERSION ?= "3.10.93"
LINUX_VERSION_EXTENSION ?= "amlogic"

COMPATIBLE_MACHINE = "(x8hp)"

SRC_URI = "http://source.mynonpublic.com/wetek/linux-wetek-3.10.y-master.tar.gz \
    file://defconfig \
    file://kernel-add-support-for-gcc6.patch \
    file://tsynch265.patch \
    file://meson8m2_n200_2G.dtb \
    file://s812_autoscript \
    file://0008-geniatech_hdstar_modules.patch \
    file://aml_autoscript \
"

S = "${WORKDIR}/linux-wetek-3.10.y-master"
B = "${WORKDIR}/linux-wetek-3.10.y-master"

do_compile_prepend () {
    unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS MACHINE
    if test -n "${KERNEL_DEVICETREE}"; then
        for DTB in ${KERNEL_DEVICETREE}; do
            if echo ${DTB} | grep -q '/dts/'; then
                bbwarn "${DTB} contains the full path to the the dts file, but only the dtb name should be used."
                DTB=`basename ${DTB} | sed 's,\.dts$,.dtb,g'`
            fi
            oe_runmake ${DTB} CC="${KERNEL_CC}" LD="${KERNEL_LD}" ${KERNEL_EXTRA_ARGS}
        done
    # Create directory, this is needed for out of tree builds
    mkdir -p ${B}/arch/arm/boot/dts/amlogic/
    fi
}
do_compile_append () {
	install -d ${DEPLOY_DIR_IMAGE}
        install -m 0644 ${WORKDIR}/s812_autoscript ${DEPLOY_DIR_IMAGE}/s812_autoscript
        install -m 0644 ${WORKDIR}/aml_autoscript ${DEPLOY_DIR_IMAGE}/aml_autoscript
        install -m 0644 ${WORKDIR}/meson8m2_n200_2G.dtb ${DEPLOY_DIR_IMAGE}/meson8m2_n200_2G.dtb
}

do_rm_work() {
}
do_package_qa() {
}
