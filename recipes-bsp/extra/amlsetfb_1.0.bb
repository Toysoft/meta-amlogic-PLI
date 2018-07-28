SUMMARY = "SysV init scripts for Amlogic framebuffer set-up"
DESCRIPTION = "Provides basic set-up for the amlogic framebuffer"
SECTION = "base"

include conf/license/license-gplv2.inc

inherit update-rc.d
INITSCRIPT_PACKAGES = "amlsetfb aaa"
INITSCRIPT_NAME_amlsetfb = "amlsetfb.sh"
INITSCRIPT_PARAMS_amlsetfb = "start 03 S ."
INITSCRIPT_NAME_aaa = "aaa.sh"
INITSCRIPT_PARAMS_aaa = "start 0 S ."

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

INHIBIT_DEFAULT_DEPS = "1"
RDEPENDS_${PN} = "initscripts fbset fbset-modes"

S = "${WORKDIR}"

SRC_URI = " \
    file://amlsetfb.sh \
    file://aaa.sh \
"

COMPATIBLE_MACHINE = "(wetekplay|wetekplay2|wetekhub|x8hp)"

do_install() {
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/amlsetfb.sh  ${D}${sysconfdir}/init.d/amlsetfb.sh
    install -m 0755 ${WORKDIR}/aaa.sh  ${D}${sysconfdir}/init.d/aaa.sh
}
