SUMMARY = "AML remote setup"
LICENSE = "GPLv2"
MAINTAINER = "Persian Professionals"
SECTION = "base"
PRIORITY = "required"

require conf/license/license-gplv2.inc

inherit gitpkgv

PV = "git${SRCPV}"
PKGV = "git${GITPKGV}"

SRCREV = "${AUTOREV}"

SRC_URI = "git://github.com/PLi-metas/amremote.git;protocol=git;branch=master"
SRC_URI_alien5 = "git://github.com/PLi-metas/amremote.git;protocol=git;branch=alien5"

S = "${WORKDIR}/git"

do_compile() {
    oe_runmake
}

do_install() {
    install -d ${D}${bindir}
    install -d ${D}${sysconfdir}/amremote
    install -m 0755 ${S}/remotecfg ${D}${bindir}/
    if [ "${MACHINE}" = "wetekplay2" ]; then
	install -m 0644 ${S}/wetek_play2.conf ${D}${sysconfdir}/amremote/wetek.conf
    elif [ "${MACHINE}" = "alien5" ]; then
	install -m 0644 ${S}/alien5.conf ${D}${sysconfdir}/amremote/remote.conf
    else
    	install -m 0644 ${S}/wetek.conf ${D}${sysconfdir}/amremote/
    fi
    install -m 0644 ${S}/wetek1.conf ${D}${sysconfdir}/amremote/
    install -m 0644 ${S}/wetek2.conf ${D}${sysconfdir}/amremote/
    install -m 0644 ${S}/wetek3.conf ${D}${sysconfdir}/amremote/
    install -m 0644 ${S}/wetek_play2.conf ${D}${sysconfdir}/amremote/
    install -m 0644 ${S}/alien.conf ${D}${sysconfdir}/amremote/
    install -m 0644 ${S}/alien2.conf ${D}${sysconfdir}/amremote/
    install -m 0644 ${S}/alien5.conf ${D}${sysconfdir}/amremote/
    install -m 0644 ${S}/octagonsf8.conf ${D}${sysconfdir}/amremote/
    install -m 0644 ${S}/wetek_et10000remote.conf ${D}${sysconfdir}/amremote/
    install -m 0644 ${S}/wetek_hd2400remote.conf ${D}${sysconfdir}/amremote/
    install -m 0644 ${S}/wetek_tmnanoremote.conf ${D}${sysconfdir}/amremote/
    install -m 0644 ${S}/gilx3.conf ${D}${sysconfdir}/amremote/
    install -m 0644 ${S}/gb800ueplus.conf ${D}${sysconfdir}/amremote/
    install -m 0644 ${S}/zgemmastar.conf ${D}${sysconfdir}/amremote/
}

FILES_${PN} = "${bindir} ${sysconfdir}"
