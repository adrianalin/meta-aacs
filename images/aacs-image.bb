SUMMARY = "A basic image with wifi, ssh, aacs"

include recipes-core/images/core-image-base.bb

IMAGE_FEATURES += "package-management splash"

IMAGE_INSTALL_append = " aacs-packagegroup"

export IMAGE_BASENAME = "aacs-image"