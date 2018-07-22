SUMMARY = "Mecool e2-procfs for ${MACHINE}"
SECTION = "base"
PRIORITY = "required"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${WORKDIR}/amlogic-e2-procfs-master/LICENSE;md5=d32239bcb673463ab874e80d47fae504"

PACKAGE_ARCH = "${MACHINE_ARCH}"

COMPATIBLE_MACHINE = "k2pro_s905"

SRC_URI[sha256sum] = "00243d07c121143ca1182ff98d137ea29d061df93bda5e4dd6c41cc741407d29"

SRC_URI = "https://github.com/PLi-metas/amlogic-e2-procfs/archive/master.tar.gz"

S = "${WORKDIR}/amlogic-e2-procfs-master/source/e2_procfs"

inherit module

EXTRA_OEMAKE = "KSRC=${STAGING_KERNEL_BUILDDIR}"

do_compile() {
	unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS
	oe_runmake -C "${STAGING_KERNEL_BUILDDIR}" M="${S}" modules
}

do_install() {
	install -d ${D}${base_libdir}/modules/${KERNEL_VERSION}/drivers/amlogic/enigma2
	install -m 0644 ${S}/e2_procfs.ko ${D}${base_libdir}/modules/${KERNEL_VERSION}/drivers/amlogic/enigma2/
}
