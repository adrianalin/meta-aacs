DESCRIPTION = "AACS package group"
SUMMARY = "AACS package group"

PACKAGE_ARCH = "${MACHINE_ARCH}"
INSANE_SKIP_${PN} = "dev-deps"

inherit packagegroup

CORE_OS = " \
    libinput-dev openssh openssh-keygen openssh-sftp-server \
"

WIFI_SUPPORT = " \
	packagegroup-base \
	iw \
	linux-firmware-bcm43430 \
	wpa-supplicant \
	init-ifupdown \
	wpa-supplicant \
"

GST_DEV = " \
	gstreamer1.0-plugins-base \
	gstreamer1.0-plugins-bad \
	gstreamer1.0-plugins-good \
	gstreamer1.0-plugins-ugly \
"

RDEPENDS_${PN} = " \
	${CORE_OS} \
	${WIFI_SUPPORT} \
	${GST_DEV} \
	sudo \
	aacs \
	strace \
	snowmix \
"

INSANE_SKIP_${PN} = "dev-deps"