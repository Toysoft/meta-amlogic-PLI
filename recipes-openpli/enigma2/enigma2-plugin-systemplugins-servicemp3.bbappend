FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append_alien5 += "\
    file://alien5-servicemp3.patch \
"

SRC_URI_append_k1pro += "\
    file://alien5-servicemp3.patch \
"

SRC_URI_append_k2pro += "\
    file://alien5-servicemp3.patch \
"

SRC_URI_append_k2prov2 += "\
    file://alien5-servicemp3.patch \
"

SRC_URI_append_k3pro += "\
    file://alien5-servicemp3.patch \
"

SRC_URI_append_k1plus += "\
    file://alien5-servicemp3.patch \
"
