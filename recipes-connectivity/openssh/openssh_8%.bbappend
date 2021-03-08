# Extend the base recipe search path to $HERE
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

do_install_append () {
	install -m 0644 ${WORKDIR}/sshd_config ${D}/${sysconfdir}/ssh
}