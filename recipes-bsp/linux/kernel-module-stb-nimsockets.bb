SUMMARY = "STB-FAKE nimsockets module"
LICENSE = "none"
LIC_FILES_CHKSUM = "file://Makefile;md5=8a7f149c3705b8a11c5b3c062cc314fe"

inherit module machine_kernel_pr

SRC_URI = "file://stb-nimsockets.c \
	   file://stb-nimsockets.h \
	   file://Makefile \
"

S = "${WORKDIR}"

do_install() {
	install -d ${D}${base_libdir}/modules/${KERNEL_VERSION}/extra
	install -m 0644 ${S}/stb-nimsockets.ko* ${D}${base_libdir}/modules/${KERNEL_VERSION}/extra
}
