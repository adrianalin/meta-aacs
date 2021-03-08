do_deploy_append() {
	# needed for USB otg
	echo "dtoverlay=dwc2" >>${DEPLOYDIR}/${BOOTFILES_DIR_NAME}/config.txt
}