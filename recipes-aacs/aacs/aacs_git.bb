SUMMARY = "bitbake-layers recipe"
DESCRIPTION = "AndroidAuto client" 
LICENSE = "GPL-3.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1ebbd3e34237af26da5dc08a4e440464"
SRC_URI = "gitsm://github.com/tomasz-grobelny/AACS.git;protocol=https;branch=master \
           file://0001-Fix-yocto-build-error.patch \
           "
SRCREV = "master"

DEPENDS += " protobuf protobuf-native boost libusbgx libusb libpcap gstreamer1.0 fmt"

RDEPENDS_${PN} += " protobuf boost libusbgx libpcap gstreamer1.0 gstreamer1.0-plugins-base gstreamer1.0-plugins-ugly fmt"

inherit cmake

EXTRA_OECMAKE += "-DCMAKE_BUILD_TYPE=Release"
# this is a revision number that should be updated every time you alter this recipe
PR = "r0" 

S="${WORKDIR}/git"

do_install_append() {
	install -d ${D}${bindir}
	install -d ${D}/home/root
	install -m 0755 ${S}/../build/AAServer/AAServer ${D}${bindir}

	# Install systemd related configuration file
	if ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'true', 'false', d)}; then
		install -d ${D}${sysconfdir}/modules-load.d
		touch ${D}${sysconfdir}/modules-load.d/libcomposite.conf
		echo "libcomposite" >> ${D}${sysconfdir}/modules-load.d/libcomposite.conf
	fi

	install -m 0644 ${S}/AAServer/ssl/* ${D}/home/root
	install -m 0644 ${S}/../build/AAServer/dhparam.pem ${D}/home/root
}

FILES_${PN} += "\
	${libdir} \
	${bindir} \
	/home/root \
"