FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append_wetekhub += "\
    file://wetekrcfix.patch \
"

SRC_URI_append_wetekplay += "\
    file://wetekrcfix.patch \
"

SRC_URI_append_wetekplay2 += "\
    file://wetekrcfix.patch \
"

SRC_URI_append_alien5 += "\
    file://alien5-enigma2.patch \
"

SRC_URI_append_k1pro += "\
    file://alien5-enigma2.patch \
"

SRC_URI_append_k2pro += "\
    file://alien5-enigma2.patch \
"

SRC_URI_append_k2prov2 += "\
    file://alien5-enigma2.patch \
"

SRC_URI_append_k3pro += "\
    file://alien5-enigma2.patch \
"

SRC_URI_append_k1plus += "\
    file://alien5-enigma2.patch \
"
