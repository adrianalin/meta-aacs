SUMMARY = "bitbake-layers recipe"
DESCRIPTION = "AndroidAuto client" 
LICENSE = "GPL-3.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1ebbd3e34237af26da5dc08a4e440464"
SRC_URI = "gitsm://github.com/tomasz-grobelny/AACS.git;protocol=https;branch=master \
           file://0001-Fix-yocto-build-error.patch \
           "
SRCREV = "master"

DEPENDS += " protobuf protobuf-native boost libusbgx libusb libpcap gstreamer1.0 fmt libxtst elfutils snowmix"

RDEPENDS_${PN} += " protobuf boost libusbgx libpcap gstreamer1.0 gstreamer1.0-plugins-base gstreamer1.0-plugins-ugly fmt libxtst elfutils gstreamer1.0-libav"

inherit cmake

EXTRA_OECMAKE += "-DCMAKE_BUILD_TYPE=Release"
# this is a revision number that should be updated every time you alter this recipe
PR = "r0" 

S="${WORKDIR}/git"

do_install_append() {
	install -d ${D}${bindir}
	install -d ${D}/home/root
	install -d ${D}/home/root/AAServer
	install -d ${D}/home/root/AAClient
	install -d ${D}${sysconfdir}/udev/rules.d/
	install -d ${D}${libdir}/gstreamer-1.0/

	install -m 0755 ${S}/../build/AAServer/AAServer ${D}${bindir}
	install -m 0755 ${S}/../build/AAClient/AAClient ${D}${bindir}
	install -m 0755 ${S}/../build/GetEvents/GetEvents ${D}${bindir}
	install -m 0755 ${S}/../build/AAVideoSink/libAAVideoSink.so ${D}${libdir}/gstreamer-1.0/

	# Install systemd related configuration file
	if ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'true', 'false', d)}; then
		install -d ${D}${sysconfdir}/modules-load.d
		touch ${D}${sysconfdir}/modules-load.d/libcomposite.conf
		echo "libcomposite" >> ${D}${sysconfdir}/modules-load.d/libcomposite.conf
	fi

	touch ${D}${sysconfdir}/udev/rules.d/55-aoa-rule.rules
	echo "SUBSYSTEM==\"usb\", ATTRS{idVendor}==\"18d1\", ATTRS{idProduct}==\"2d00\", MODE=\"0666\"" > ${D}${sysconfdir}/udev/rules.d/55-aoa-rule.rules
	echo "SUBSYSTEM==\"usb\", ATTRS{idVendor}==\"12d1\", ATTRS{idProduct}==\"107e\", MODE=\"0666\"" >> ${D}${sysconfdir}/udev/rules.d/55-aoa-rule.rules

	install -m 0644 ${S}/AAServer/ssl/* ${D}/home/root/AAServer
	install -m 0644 ${S}/oe-workdir/aacs-git/AAServer/dhparam.pem ${D}/home/root/AAServer

	install -m 0644 ${S}/AAClient/ssl/* ${D}/home/root/AAClient
	install -m 0644 ${S}/oe-workdir/aacs-git/AAClient/dhparam.pem ${D}/home/root/AAClient
}

FILES_${PN} += "\
	${libdir} \
	${bindir} \
	/home/root/AAServer \
	/home/root/AAClient \
	${sysconfdir}/modules-load.d/libcomposite.conf \
	${sysconfdir}/udev/rules.d/55-aoa-rule.rules \
"