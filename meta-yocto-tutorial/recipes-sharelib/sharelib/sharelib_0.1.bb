SUMMARY = "Simple helloworld application"
DESCRIPTION = "Recipe created by bitbake-layers"
SECTION = "sharelib"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"
TARGET_CC_ARCH += "${LDFLAGS}"

#FILESEXTRAPATHS_prepend := "${THISDIR}:"
# This tells bitbake where to find the files we're providing on the local filesystem
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

#SRC_URI = "file://sharelib-0.1/*"
SRC_URI = "file://*.c"
#SRC_URI = "file://sharelib.c"

S = "${WORKDIR}"

inherit autotools

# The autotools configuration I am basing this on seems to have a problem with a race condition when parallel make is enabled
PARALLEL_MAKE = ""