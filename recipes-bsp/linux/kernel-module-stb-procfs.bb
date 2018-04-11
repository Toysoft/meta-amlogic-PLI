SUMMARY = "STB-FAKE procfs module"
LICENSE = "none"
LIC_FILES_CHKSUM = "file://Makefile;md5=6c893dc2fac3542b98a1e5800bdae169"

inherit module machine_kernel_pr

SRC_URI = "file://stb-procfs.c \
	   file://stb-procfs.h \
	   file://Makefile \
"

S = "${WORKDIR}"

do_install() {
	install -d ${D}${base_libdir}/modules/${KERNEL_VERSION}/extra
	install -m 0644 ${S}/stb-procfs.ko* ${D}${base_libdir}/modules/${KERNEL_VERSION}/extra
}
