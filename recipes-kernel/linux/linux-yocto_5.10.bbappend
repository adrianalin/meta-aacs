FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-5.10:"
SUMMARY = "Add functionfs support"

inherit kernel

SRC_URI += " \
    file://fragment.cfg \
"
