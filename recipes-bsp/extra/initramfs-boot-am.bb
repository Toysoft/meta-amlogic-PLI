SUMMARY = "Extremely basic live image init script for amlogic"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

PR = "r4"

SRC_URI = "file://init \
	file://hdmi.sh \
	file://functions \
"

do_install() {
	install -d ${D}/etc/init.d
	install -m 0755 ${WORKDIR}/hdmi.sh  ${D}/etc/init.d/hdmi.sh
	install -m 0755 ${WORKDIR}/init  ${D}/init
	install -m 0755 ${WORKDIR}/functions  ${D}/functions
}

inherit allarch

FILES_${PN} += "/init /etc/init.d/hdmi.sh /functions"
