SUMMARY = "bwidget used by tcl/tk needed by Snowmix demo"
SECTION = "media"
LICENSE = "custom"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=af21afb4e406f3d8e15b91dd3fa0a978"

SRC_URI = "https://downloads.sourceforge.net/project/tcllib/BWidget/${PV}/bwidget-${PV}.tar.gz"

SRC_URI[md5sum] = "44d3b2b5d86e6b47441f68e898addacd"

S="${WORKDIR}/bwidget-${PV}"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install() {
    install -d ${D}${libdir}/tcl8.6/bwidget-${PV}/
    cp -r ${S}/* ${D}${libdir}/tcl8.6/bwidget-${PV}/
}

FILES_${PN} += "\
    ${libdir} \
"
