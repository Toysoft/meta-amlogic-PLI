SUMMARY = "Enigma2 procfs drivers for ${MACHINE}"
SECTION = "base"
PRIORITY = "required"
LICENSE = "CLOSED"
require conf/license/license-close.inc

COMPATIBLE_MACHINE = "(alien5|k1pro|k2pro)"

DEPENDS = "virtual/${TARGET_PREFIX}gcc"

RDEPENDS_${PN}_k1pro += "firmware-avl6211 firmware-avl6882"
RDEPENDS_${PN}_k2pro += "firmware-avl6211 firmware-avl6882"

KV = "3.14.29"
SRCDATE = "20180418"

PV = "${KV}+${SRCDATE}"

SRC_URI = "file://alien5-stb-${SRCDATE}.zip"

S = "${WORKDIR}"

inherit module

do_compile() {
}

do_install() {
    install -d ${D}${nonarch_base_libdir}/modules/${KV}/kernel/drivers/a5
    install -m 0755 ${WORKDIR}/a5stb.ko ${D}${nonarch_base_libdir}/modules/${KV}/kernel/drivers/a5/
}

do_package_qa() {
}
