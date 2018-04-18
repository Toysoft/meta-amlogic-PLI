FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append_wetekhub += " \
    file://wetekrcfix.patch \
"

SRC_URI_append_wetekplay += " \
    file://wetekrcfix.patch \
"

SRC_URI_append_wetekplay2 += " \
    file://wetekrcfix.patch \
"
