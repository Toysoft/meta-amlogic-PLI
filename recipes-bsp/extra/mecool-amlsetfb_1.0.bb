SUMMARY = "SysV init scripts for Amlogic framebuffer set-up"
DESCRIPTION = "Provides basic set-up for the amlogic framebuffer"
SECTION = "base"

PACKAGE_ARCH = "${MACHINE_ARCH}"

COMPATIBLE_MACHINE = "k1pro|k2pro|k2prov2|k3pro|k1plus"

include conf/license/license-gplv2.inc

PR = "r2"

inherit pkgconfig update-rc.d

INITSCRIPT_NAME = "amlsetfb"
INITSCRIPT_PARAMS = "start 03 S ."

INHIBIT_DEFAULT_DEPS = "1"
RDEPENDS_${PN} = "initscripts fbset fbset-modes"

S = "${WORKDIR}"

SRC_URI = "\
    file://amlsetfb \
"

SRC_URI_k1plus = "\
    file://amlsetfb \
    file://aaa \
"


do_install() {
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/amlsetfb  ${D}${sysconfdir}/init.d/amlsetfb
}

do_install_k1plus() {
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/amlsetfb  ${D}${sysconfdir}/init.d/amlsetfb
    install -m 0755 ${WORKDIR}/aaa  ${D}${sysconfdir}/init.d/aaa
}
