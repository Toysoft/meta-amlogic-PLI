FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append += "${@bb.utils.contains("MACHINE_FEATURES", "amlogic-pli", "file://alien5-servicemp3.patch", "", d)}"

DEPENDS_${PN} += "\
	${@bb.utils.contains("MACHINE_FEATURES", "amlogic-pli", "linkdroid-libamcodec-alien5" , "", d)} \
	"
