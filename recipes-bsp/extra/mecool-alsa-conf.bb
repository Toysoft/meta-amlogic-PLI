COMPATIBLE_MACHINE = "k1pro|k2pro|k2prov2|k3pro|k1plus"

include conf/license/license-gplv2.inc

SRC_URI = " \
    file://asound.conf \
"

PACKAGE_ARCH = "${MACHINE}"

do_install() {
	install -d ${D}${sysconfdir}
    install -m 0755 ${WORKDIR}/asound.conf  ${D}${sysconfdir}/asound.conf
}
