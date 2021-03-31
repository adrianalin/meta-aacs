SUMMARY = "Electron js"
SECTION = "media"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=85bf260d8b6de1588f57abc5dc66587c"

SRC_URI = "https://nodejs.org/dist/v${PV}/node-v${PV}-linux-arm64.tar.gz"

SRC_URI[md5sum] = "d5fe6bb9f83c60f610a8b58ba81fecba"

S="${WORKDIR}/node-v${PV}-linux-arm64"

RDEPENDS_${PN} += " bash libxscrnsaver nss nspr cups gtk+3"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install() {
    install -d ${D}${libdir}
    install -d ${D}${bindir}
    install -d ${D}${datadir}
    install -d ${D}${includedir}

    cp -r ${S}/bin/* ${D}${bindir}
    cp -r ${S}/include/* ${D}${includedir}
    cp -r ${S}/lib/* ${D}${libdir}
    cp -r ${S}/share/* ${D}${datadir}
}

FILES_${PN} += "\
    ${bindir} \
    ${libdir} \
    ${datadir} \
    ${includedir} \
"

INSANE_SKIP_${PN} += "file-rdeps"