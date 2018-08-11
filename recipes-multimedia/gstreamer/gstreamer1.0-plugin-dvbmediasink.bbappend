SRC_URI = "${@bb.utils.contains("MACHINE_FEATURES", "amlogic-pli", "git://github.com/christophecvr/gstreamer1.0-plugin-multibox-dvbmediasink;branch=openatv-dev;protocol=git file://introduce-new-signal-get-video-codec.patch", "git://github.com/OpenPLi/gst-plugin-dvbmediasink.git;branch=gst-1.0", d)}"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
