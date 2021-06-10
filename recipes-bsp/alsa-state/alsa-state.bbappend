# Extend the base recipe search path to $HERE
FILESEXTRAPATHS_prepend := "${THISDIR}/alsa-state:"

do_install_append () {
    install -m 0644 ${WORKDIR}/asound.conf ${D}/${sysconfdir}/
}