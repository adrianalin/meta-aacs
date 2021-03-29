SUMMARY = "Snowmix Live Video Mixer"
SECTION = "media"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c663b215e7158cff254704aeac315812"
DEPENDS = "libpng libsdl pango tk cairo gstreamer1.0 "

RDEPENDS_${PN} += " bash tcl tk bwidget"

inherit autotools pkgconfig

SRC_URI = "${SOURCEFORGE_MIRROR}/project/snowmix/Snowmix-${PV}.tar.gz \
           file://0001-Use-pkg-config.-Fix-demo-errors.patch \
           "

SRC_URI[md5sum] = "49df6c693e8c30cf609fc2adbf800d60"

S="${WORKDIR}/Snowmix-${PV}"

do_configure_prepend () {
    cp -r ${S}/* ${S}/../build
}

FILES_${PN} += "\
    ${bindir} \
    ${libdir} \
"